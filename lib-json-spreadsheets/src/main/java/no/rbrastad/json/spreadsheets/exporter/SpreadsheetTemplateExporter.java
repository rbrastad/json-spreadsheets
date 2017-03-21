package no.rbrastad.json.spreadsheets.exporter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import org.jxls.util.JxlsHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Created by runar on 27.06.16.
 */
public class SpreadsheetTemplateExporter {

    private static Logger logger = Logger.getLogger(SpreadsheetTemplateExporter.class.getName());

    private ObjectMapper mapper = null;

    public SpreadsheetTemplateExporter(){
        mapper = new ObjectMapper();
    }

    public ByteArrayOutputStream getAsSpreadsheet(JsonNode report, ByteSource source) throws Exception {

        File templateFile = File.createTempFile( "jsonSpreadSheetsTemplateTMP-" + System.nanoTime() , ".xlsx" );
        Files.write( source.read(), templateFile);

        InputStream is = Files.asByteSource(templateFile).openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            JxlsHelper.getInstance().processTemplate(is, bos, JsonDataUtil.getJSonDataAsContext(report.get("data")));
        }catch (Exception e){
            e.printStackTrace();
        }

        is.close();
        templateFile.delete();

        return bos;
    }
}