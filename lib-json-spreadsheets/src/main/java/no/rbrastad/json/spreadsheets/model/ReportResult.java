package no.rbrastad.json.spreadsheets.model;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by runar on 29.06.16.
 */
public class ReportResult {

    private long length;
    private String uuid;
    private byte[]  data;
    private JsonNode config;

    public JsonNode getConfig() {
        return config;
    }

    public void setConfig(JsonNode config) {
        this.config = config;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }


    public byte[]  getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
