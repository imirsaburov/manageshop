package uz.imirsaburov.manage.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.imirsaburov.manage.shop.dto.oauth2.CustomUserDetails;
import uz.imirsaburov.manage.shop.entity.user.UserEntity;
import uz.imirsaburov.manage.shop.service.AuthorityService;
import uz.imirsaburov.manage.shop.service.UserService;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userService.getByUsername(username);

        CustomUserDetails userDetailsDTO = new CustomUserDetails();
        userDetailsDTO.setUsername(username);
        userDetailsDTO.setId(userEntity.getId());
        userDetailsDTO.setPassword(userEntity.getPassword());
        userDetailsDTO.setEnabled(userEntity.getEnabled());
        userDetailsDTO.setAccountNonExpired(userEntity.getEnabled());
        userDetailsDTO.setAccountNonLocked(userEntity.getEnabled());
        userDetailsDTO.setCredentialsNonExpired(userEntity.getEnabled());
        userDetailsDTO.setGrantedAuthorities(authorityService.getAuthorities(username));
        return userDetailsDTO;
    }
}
