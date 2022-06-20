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

    //category 004***
    SIZE_NOT_FOUND_EXCEPTION("size.notfound", HttpStatus.NOT_FOUND, "0040001"),
    SIZE_NAME_ALREADY_EXIST_EXCEPTION("size.name.exist", HttpStatus.BAD_REQUEST, "0040001"),


    //product 005***
    PRODUCT_NOT_FOUND_EXCEPTION("product.notfound", HttpStatus.NOT_FOUND, "0050001"),


    //product 006***
    PRODUCT_SIZE_NOT_FOUND_EXCEPTION("product.size.notfound", HttpStatus.NOT_FOUND, "0060001"),
    PRODUCT_SIZE_EXIST_EXCEPTION("product.size.exist", HttpStatus.NOT_FOUND, "0060002"),
    PRODUCT_SIZE_INSUFFICIENT_EXCEPTION("product.size.count.insufficient", HttpStatus.BAD_REQUEST, "0060003"),

    //product 007***
    SOLD_PRODUCT_NOT_FOUND_EXCEPTION("product.sold.notfound", HttpStatus.NOT_FOUND, "0070001"),



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
