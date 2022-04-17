package uz.imirsaburov.manage.shop.dto.oauth2;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.imirsaburov.manage.shop.base.BaseDTO;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Setter
public class CustomUserDetails extends BaseDTO implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
