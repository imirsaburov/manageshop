package uz.imirsaburov.manage.shop.exceptions;

import uz.imirsaburov.manage.shop.enums.ExceptionEnum;

public class UsernameNotFoundException extends AppException {

    public UsernameNotFoundException(String username) {
        super(ExceptionEnum.USERNAME_NOT_FOUND_EXCEPTION, username);
    }
}
