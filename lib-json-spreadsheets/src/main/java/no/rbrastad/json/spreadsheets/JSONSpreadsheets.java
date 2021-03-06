package no.rbrastad.json.spreadsheets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import no.rbrastad.json.spreadsheets.exporter.SpreadsheetSimpleExporter;
import no.rbrastad.json.spreadsheets.exporter.SpreadsheetTemplateExporter;
import no.rbrastad.json.spreadsheets.model.ReportResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class JSONSpreadsheets {


    public JsonNode getSpreadsheet(String jsonConfig) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode config = mapper.readTree(jsonConfig);

        ReportResult reportFile = getReport( config , null, false);

        doWorkConfig( config, reportFile );

        return mapper.valueToTree( reportFile );
    }

    public JsonNode  getSpreadsheetSimple(String jsonConfig, ByteSource source) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode config = mapper.readTree(jsonConfig);

        ReportResult reportFile = getReport( config , source, true);

        return mapper.valueToTree( reportFile );
    }

    public JsonNode  getSpreadsheet(String jsonConfig, ByteSource source) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode config = mapper.readTree(jsonConfig);

        ReportResult reportFile = getReport( config , source, false);

        return mapper.valueToTree( reportFile );
    }


    private ReportResult getReport(JsonNode config, ByteSource source, boolean simple) throws Exception{
        ByteArrayOutputStream bos = null;
        if( simple ) {
            SpreadsheetSimpleExporter excelSimpleReportExporter = new SpreadsheetSimpleExporter();
            if(source != null){
                bos = excelSimpleReportExporter.getAsSpreadsheet(config, source);
            }else{
                bos = excelSimpleReportExporter.getAsSpreadsheet(config);
            }
        }else {
            SpreadsheetTemplateExporter exporter = new SpreadsheetTemplateExporter();
            if( config.has("work") && config.get("work").has("templatesDirectory")){
                bos =  exporter.getAsSpreadsheet(config, getTemplateFromDirectory( config ) );
            }else{
                bos = exporter.getAsSpreadsheet(config, source);
            }

        }

        ReportResult report = new ReportResult();
        report.setData(  bos.toByteArray()  );
        report.setConfig( config );

        if(config.has( "work" )) {
            doWorkConfig(config, report);
        }

        return report;
    }


    private void doWorkConfig(JsonNode config, ReportResult reportFile) {
        if(config.has( "work" )){
            ConfigurationHelper helper = new ConfigurationHelper();
            helper.doReportsSave( config, reportFile );
        }
    }

    private ByteSource getTemplateFromDirectory(JsonNode config )throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ConfigurationHelper configurationHelper = new ConfigurationHelper();
        String templateFilePath = configurationHelper.getTemplateFile(config);

        File file = new File(templateFilePath);
        return Files.asByteSource(file);
    }
}
