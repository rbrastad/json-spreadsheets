package no.rbrastad.json.spreadsheets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import no.rbrastad.json.spreadsheets.model.Report;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by runar on 28.06.16.
 */
public class JSONSpreadsheetConfigurationModelMarshaller {

    private  ObjectMapper mapper = null;

    public JSONSpreadsheetConfigurationModelMarshaller(){
        mapper = new ObjectMapper();
    }

    public String marhallInputStreamToString(final InputStream is) throws IOException {
        ByteSource byteSource = new ByteSource() {
            @Override
            public InputStream openStream() throws IOException {
                return is;
            }
        };

       return byteSource.asCharSource(Charsets.UTF_8).read();
    }

    public JsonNode marshallInputStreamToJSonNode(final InputStream is) throws IOException {
         return mapper.readTree(  marhallInputStreamToString(is) );
    }

    public Report marshallInpuStreamToReportModel(final InputStream is) throws IOException {
        Report report = mapper.treeToValue(  marshallInputStreamToJSonNode(is) , Report.class );

       return report;
    }
}
