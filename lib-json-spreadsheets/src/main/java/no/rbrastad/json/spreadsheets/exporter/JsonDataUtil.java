package no.rbrastad.json.spreadsheets.exporter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jxls.common.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by runar on 20.03.17.
 */
public abstract class JsonDataUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static Object getJSonDataAsMap(JsonNode data) {
        List<Object> values = new ArrayList<Object>();
        if( data.isValueNode() ){
            return data.asText();
        }else {
            for (JsonNode node : data) {
                Map<String, Object> result = mapper.convertValue(node, Map.class);
                values.add(result);
            }

            return values;
        }
    }

    public static Context getJSonDataAsContext(JsonNode data) {
       Context context = new Context();

       Iterator<Map.Entry<String, JsonNode>> dataMap = data.fields();
       while( dataMap.hasNext() ){
           Map.Entry<String, JsonNode> map = dataMap.next();
           context.putVar(map.getKey(), getJSonDataAsMap( map.getValue() ) );
       }

        return  context;
    }

}
