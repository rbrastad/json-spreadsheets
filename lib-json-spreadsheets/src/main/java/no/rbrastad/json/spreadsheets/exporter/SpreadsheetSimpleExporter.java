package no.rbrastad.json.spreadsheets.exporter;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Joiner;
import org.jxls.template.SimpleExporter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class SpreadsheetSimpleExporter {

    private static Logger logger = Logger.getLogger(SpreadsheetSimpleExporter.class.getName());

    public ByteArrayOutputStream getAsSpreadsheet(JsonNode report) throws IOException {
        List<String> headersList = new ArrayList<String>();
        List<String> dataFields = new ArrayList<String>();
        for(JsonNode header : report.get("header") ){
            headersList.add( header.get("title").asText());
            dataFields.add( header.get("name").asText() );
        }

        return getAsSpreadsheet(headersList, dataFields,report.get("data"));
    }

    public ByteArrayOutputStream getAsSpreadsheet(List<String> headers, List<String> dataFields, JsonNode data) throws  IOException{
        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            SimpleExporter exporter = new SimpleExporter();
            exporter.gridExport(headers, (Iterable) JsonDataUtil.getJSonDataAsMap(data),  Joiner.on(",").join( dataFields )  , bos);

            return bos;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }
}