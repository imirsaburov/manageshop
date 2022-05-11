package uz.imirsaburov.manage.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.imirsaburov.manage.shop.enums.AuthorityType;
import uz.imirsaburov.manage.shop.enums.PermissionEnum;
import uz.imirsaburov.manage.shop.enums.RoleEnum;
import uz.imirsaburov.manage.shop.service.AuthoritySerivce;
import uz.imirsaburov.manage.shop.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthoritySerivceImpl implements AuthoritySerivce {

    private final UserService userService;


    @Override
    public boolean checkPermission(String username, PermissionEnum permissionEnum) {
        return userService.getAllAuthorities(username)
                .stream()
                .filter(e -> e.getAuthorityType().equals(AuthorityType.PERMISSION))
                .anyMatch((e) -> e.getAuthority().equals(permissionEnum.name()));

    }

    @Override
    public boolean checkAdmin(String username) {
        return checkRole(username, RoleEnum.ADMIN);
    }

    @Override
    public boolean checkRole(String username, RoleEnum role) {
        return userService.getAllAuthorities(username)
                .stream()
                .filter(e -> e.getAuthorityType().equals(AuthorityType.ROLE))
                .anyMatch((e) -> e.getAuthority().equals(role.name()));
    }
}
