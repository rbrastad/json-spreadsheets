package no.rbrastad.json.spreadsheets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.rbrastad.json.spreadsheets.exporter.SpreadsheetSimpleExporter;
import no.rbrastad.json.spreadsheets.model.ReportResult;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class JSONSpreadsheets {

    public JsonNode getSpreadsheet(String jsonConfig) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode config = mapper.readTree(jsonConfig);

        ReportResult reportFile = getSimpleReport( config );

        return mapper.valueToTree( reportFile );
    }


    public ReportResult getSimpleReport(JsonNode config) throws IOException{
        SpreadsheetSimpleExporter excelSimpleReportExporter = new SpreadsheetSimpleExporter();

        ByteArrayOutputStream bos = excelSimpleReportExporter.getAsSpreadsheet(config);

        ReportResult report = new ReportResult();
        report.setData(  bos.toByteArray()  );
        report.setConfig( config );

        excelSimpleReportExporter = null;

        return report;
    }

}
