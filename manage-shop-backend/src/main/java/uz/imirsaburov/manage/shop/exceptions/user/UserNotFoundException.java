package uz.imirsaburov.manage.shop.exceptions.user;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;
import uz.imirsaburov.manage.shop.exceptions.AppException;

public class UserNotFoundException extends AppException {

    public UserNotFoundException(Long id) {
        super(ExceptionEnum.USER_NOT_FOUND_EXCEPTION, id.toString());
    }
}
