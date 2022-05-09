package uz.imirsaburov.manage.shop.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionEnum {

    // generals 000***
    INTERNAL_SERVER_ERROR("server.error", HttpStatus.INTERNAL_SERVER_ERROR, "0000001"),
    VALIDATION("", HttpStatus.BAD_REQUEST, "0000002"),

    //user 002***
    USERNAME_NOT_FOUND_EXCEPTION("user.username.notfound", HttpStatus.NOT_FOUND, "0020001"),

    //category 003***
    CATEGORY_NOT_FOUND_EXCEPTION("category.notfound", HttpStatus.NOT_FOUND, "0030001"),

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
