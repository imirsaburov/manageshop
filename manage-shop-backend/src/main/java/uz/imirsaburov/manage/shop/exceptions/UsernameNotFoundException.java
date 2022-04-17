package uz.imirsaburov.manage.shop.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameNotFoundException extends HttpStatusCodeException {
    public UsernameNotFoundException(String username) {
        super("username.notfound", HttpStatus.NOT_FOUND, username);
    }
}
