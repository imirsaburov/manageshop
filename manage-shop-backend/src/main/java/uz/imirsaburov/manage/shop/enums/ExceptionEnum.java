package uz.imirsaburov.manage.shop.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionEnum {

    // generals 000***
    INTERNAL_SERVER_ERROR("server.error", HttpStatus.INTERNAL_SERVER_ERROR, "0000001"),
    VALIDATION("", HttpStatus.BAD_REQUEST, "0000002"),
    SPECIFICATION_CUSTOM_METHOD_IS_NOT_READY("specification.custom.method.not.ready", HttpStatus.BAD_REQUEST, "0000002"),

    //user 002***
    USERNAME_NOT_FOUND_EXCEPTION("user.username.notfound", HttpStatus.NOT_FOUND, "0020001"),
    USERNAME_EXIST_EXCEPTION("user.username.exist", HttpStatus.BAD_REQUEST, "0020002"),
    USER_NOT_FOUND_EXCEPTION("user.notfound", HttpStatus.NOT_FOUND, "0020003"),
    PASSWORD_NOT_EQUALS_EXCEPTION("user.password.not.equals", HttpStatus.BAD_REQUEST, "0020004"),

    //category 003***
    CATEGORY_NOT_FOUND_EXCEPTION("category.notfound", HttpStatus.NOT_FOUND, "0030001"),
    CATEGORY_NAME_ALREADY_EXIST_EXCEPTION("category.name.exist", HttpStatus.BAD_REQUEST, "0030001"),

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
