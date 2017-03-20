package no.rbrastad.json.spreadsheets;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.io.Files;
import no.rbrastad.json.spreadsheets.model.ReportResult;

import java.io.File;

/**
 * Created by runar on 20.03.17.
 */
public class ConfigurationHelper {

    public void execute(JsonNode config, ReportResult report){
        doReportsSave(config, report);

    }

    public void doReportsSave( JsonNode config, ReportResult report  ){
        try {
            if (config.get("work").has("reportsDirectory") && config.has("reportFileName") ) {
                String reportsDirectory = config.get("work").get("reportsDirectory").asText();
                if( !reportsDirectory.endsWith( File.separator ) )
                    reportsDirectory += File.separator;

                File file = new File(reportsDirectory + config.get("reportFileName").asText());
                Files.write(report.getData(), file);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public String getTemplateFile( JsonNode config  ){
        if( config.get("work").has("templatesDirectory") ) {
            String templatesDirectory = config.get("work").get("templatesDirectory").asText();
            if( config.get("template").has("fileName") ) {
                if( !templatesDirectory.endsWith(File.separator) )
                    templatesDirectory += File.separator;

                return templatesDirectory + config.get("template").get("fileName").asText();
            }
        }

        return null;
    }

}
