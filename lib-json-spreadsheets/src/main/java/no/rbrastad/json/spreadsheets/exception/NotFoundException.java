package no.rbrastad.json.spreadsheets.exception;

/**
 * Created by runar on 29.06.16.
 */
public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}