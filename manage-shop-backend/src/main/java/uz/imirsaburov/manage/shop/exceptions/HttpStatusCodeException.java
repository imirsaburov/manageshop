package uz.imirsaburov.manage.shop.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Getter
public class HttpStatusCodeException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String localeCode;
    private final String[] params;

    public HttpStatusCodeException(String localeCode, HttpStatus httpStatus, String... params) {
        this.httpStatus = httpStatus;
        this.localeCode = localeCode;
        this.params = params;
    }

    public HttpStatusCodeException(String localeCode, HttpStatus httpStatus) {
        this(localeCode, httpStatus, new String[0]);
    }

    public HttpStatusCodeException(String localeCode) {
        this(localeCode, HttpStatus.INTERNAL_SERVER_ERROR, new String[0]);
    }
}
