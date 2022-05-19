package uz.imirsaburov.manage.shop.exceptions.user;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;
import uz.imirsaburov.manage.shop.exceptions.AppException;

public class UsernameNotFoundException extends AppException {

    public UsernameNotFoundException(String username) {
        super(ExceptionEnum.USERNAME_NOT_FOUND_EXCEPTION, username);
    }
}
