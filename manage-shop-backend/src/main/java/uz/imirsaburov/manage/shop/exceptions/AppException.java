package uz.imirsaburov.manage.shop.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import uz.imirsaburov.manage.shop.enums.ExceptionEnum;

@EqualsAndHashCode(callSuper = true)
@Getter
public class AppException extends RuntimeException {

    private final ExceptionEnum exceptionEnum;
    private final String[] params;

    public AppException(ExceptionEnum exceptionEnum, String... params) {
        this.exceptionEnum = exceptionEnum;
        this.params = params;
    }

    public AppException(ExceptionEnum exceptionEnum) {
        this(exceptionEnum, new String[0]);
    }
}
