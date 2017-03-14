package no.rbrastad.json.spreadsheets.exception;

/**
 * Created by runar on 29.06.16.
 */

public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
