package uz.imirsaburov.manage.shop.utils;

import org.junit.jupiter.api.Test;
import uz.imirsaburov.manage.shop.dto.oauth2.CustomUserDetails;
import uz.imirsaburov.manage.shop.util.CurrentUserUtils;

public class CurrentUserUtilsTest {

    @Test
    public void itShouldAnonymousUser(){

        CustomUserDetails currentUser = CurrentUserUtils.getCurrentUser();

        System.out.println(currentUser);



    }
}
