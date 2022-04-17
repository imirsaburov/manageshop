package uz.imirsaburov.manage.shop.util;

import org.springframework.security.core.context.SecurityContextHolder;
import uz.imirsaburov.manage.shop.dto.oauth2.CustomUserDetails;

public class CurrentUserUtils {

    public static CustomUserDetails getCurrentUser() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
