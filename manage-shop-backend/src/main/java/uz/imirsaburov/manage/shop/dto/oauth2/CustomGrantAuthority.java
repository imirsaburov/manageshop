package uz.imirsaburov.manage.shop.dto.oauth2;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import uz.imirsaburov.manage.shop.enums.AuthorityType;

@Getter
public class CustomGrantAuthority implements GrantedAuthority {
    private final String authority;
    private final AuthorityType authorityType;

    public CustomGrantAuthority(String authority, AuthorityType authorityType) {
        this.authority = authority;
        this.authorityType = authorityType;
    }
}
