package uz.imirsaburov.manage.shop.exceptions.user;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;
import uz.imirsaburov.manage.shop.exceptions.AppException;

public class UsernameExistException extends AppException {

    public UsernameExistException(String username) {
        super(ExceptionEnum.USERNAME_EXIST_EXCEPTION, username);
    }
}
