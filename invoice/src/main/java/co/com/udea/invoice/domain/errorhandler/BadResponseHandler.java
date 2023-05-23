package co.com.udea.customer.domain.errorhandler;

import org.springframework.http.HttpStatus;

public class BadResponseHandler extends RuntimeException{

    private final String code;
    private final HttpStatus httpStatus;

    public BadResponseHandler (String message, String code, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
