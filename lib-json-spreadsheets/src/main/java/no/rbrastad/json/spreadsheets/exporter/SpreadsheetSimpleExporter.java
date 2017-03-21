package no.rbrastad.json.spreadsheets.exporter;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Joiner;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import org.jxls.template.SimpleExporter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class SpreadsheetSimpleExporter {

    private static Logger logger = Logger.getLogger(SpreadsheetSimpleExporter.class.getName());

    public ByteArrayOutputStream getAsSpreadsheet(JsonNode report) throws IOException {
        return getAsSpreadsheet(report,null);
    }

    public ByteArrayOutputStream getAsSpreadsheet(JsonNode report, ByteSource source) throws IOException{
        List<String> headersList = new ArrayList<String>();
        List<String> dataFields = new ArrayList<String>();
        for(JsonNode header : report.get("header") ){
            headersList.add( header.get("title").asText());
            dataFields.add( header.get("name").asText() );
        }

        return getAsSpreadsheet(headersList, dataFields,report.get("data"), source);
    }

    public ByteArrayOutputStream getAsSpreadsheet(List<String> headers, List<String> dataFields, JsonNode data, ByteSource source) throws  IOException{
        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            SimpleExporter exporter = new SimpleExporter();
            if(source != null){
                File templateFile = File.createTempFile( "jsonSpreadSheetsTemplateTMP-" + System.nanoTime() , ".xlsx" );
                Files.write( source.read(), templateFile);
                InputStream is = Files.asByteSource(templateFile).openStream();

                exporter.registerGridTemplate( is );
            }

            exporter.gridExport(headers, (Iterable) JsonDataUtil.getJSonDataAsMap(data),  Joiner.on(",").join( dataFields )  , bos);

            return bos;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }
}