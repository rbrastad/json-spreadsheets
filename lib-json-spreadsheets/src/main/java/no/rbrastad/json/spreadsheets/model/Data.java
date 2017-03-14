package no.rbrastad.json.spreadsheets.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by runar on 29.06.16.
 */
public class Data {

    List<?> values = new ArrayList<Object>();

    public List<?> getValues() {
        return values;
    }

    public void setValues(List<?> values) {
        this.values = values;
    }



}
