package uz.imirsaburov.manage.shop.exceptions.user;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;
import uz.imirsaburov.manage.shop.exceptions.AppException;

public class PasswordsNotEqualsException extends AppException {

    public PasswordsNotEqualsException(String pass1, String pass2) {
        super(ExceptionEnum.PASSWORD_NOT_EQUALS_EXCEPTION, pass1, pass2);
    }
}
