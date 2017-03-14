package no.rbrastad.json.spreadsheets.exporter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import org.jxls.template.SimpleExporter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class SpreadsheetSimpleExporter {

    private static Logger logger = Logger.getLogger(SpreadsheetSimpleExporter.class.getName());

    private List<Object> getJSonDataAsMap(JsonNode data) {
        List<Object> values = new ArrayList<Object>();
        ObjectMapper mapper = new ObjectMapper();
        for(JsonNode node : data){
            Map<String, Object> result = mapper.convertValue(node, Map.class);
            values.add(result);
        }
        return values;
    }

    public ByteArrayOutputStream getAsSpreadsheet(JsonNode report) throws IOException {
        List<String> headersList = new ArrayList<String>();
        List<String> dataFields = new ArrayList<String>();
        for(JsonNode header : report.get("header") ){
            logger.finest("header node: " + header.get("name"));

            headersList.add( header.get("title").asText());
            dataFields.add( header.get("name").asText() );
        }

        return getAsSpreadsheet(headersList, dataFields,report.get("data"));
    }

    public ByteArrayOutputStream getAsSpreadsheet(List<String> headers, List<String> dataFields, JsonNode data) throws  IOException{

        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            SimpleExporter exporter = new SimpleExporter();
            exporter.gridExport(headers,  getJSonDataAsMap(data) ,  Joiner.on(",").join( dataFields )  , bos);

            return bos;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }
}
