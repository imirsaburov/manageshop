package uz.imirsaburov.manage.shop.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionEnum {

    // generals
    INTERNAL_SERVER_ERROR("server.error", HttpStatus.INTERNAL_SERVER_ERROR, "000"),
    VALIDATION("", HttpStatus.BAD_REQUEST, "001"),
    //user
    USERNAME_NOT_FOUND_EXCEPTION("user.username.notfound", HttpStatus.NOT_FOUND, "002"),

    //category
    CATEGORY_NOT_FOUND_EXCEPTION("category.notfound", HttpStatus.NOT_FOUND, "003"),

    ;

    private final String localeCode;
    private final HttpStatus httpStatus;
    private final String errorCode;

    ExceptionEnum(String localeCode, HttpStatus httpStatus, String errorCode) {
        this.localeCode = localeCode;
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}
