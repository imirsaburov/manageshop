package uz.imirsaburov.manage.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.imirsaburov.manage.shop.dto.oauth2.CustomGrantAuthority;
import uz.imirsaburov.manage.shop.entity.user.UserEntity;
import uz.imirsaburov.manage.shop.entity.user.UserPermissionEntity;
import uz.imirsaburov.manage.shop.enums.AuthorityType;
import uz.imirsaburov.manage.shop.enums.PermissionEnum;
import uz.imirsaburov.manage.shop.enums.RoleEnum;
import uz.imirsaburov.manage.shop.repository.UserPermissionRepository;
import uz.imirsaburov.manage.shop.service.AuthorityService;
import uz.imirsaburov.manage.shop.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final UserService userService;
    private final UserPermissionRepository userPermissionRepository;


    @Override
    public boolean checkPermission(String username, PermissionEnum permissionEnum) {
        return getAuthorities(username)
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
        return getAuthorities(username)
                .stream()
                .filter(e -> e.getAuthorityType().equals(AuthorityType.ROLE))
                .anyMatch((e) -> e.getAuthority().equals(role.name()));
    }

    @Override
    public List<CustomGrantAuthority> getAuthorities(String username) {
        UserEntity userEntity = userService.getByUsername(username);

        List<CustomGrantAuthority> grantAuthorities = new ArrayList<>();

        grantAuthorities.add(new CustomGrantAuthority(userEntity.getRole().name(), AuthorityType.ROLE));

        List<UserPermissionEntity> userPermissionEntityList = userPermissionRepository.findAllByUserId(userEntity.getId());

        if (!userPermissionEntityList.isEmpty())
            userPermissionEntityList.forEach(entity -> {
                grantAuthorities.add(new CustomGrantAuthority(entity.getPermissionEntity().getName().name(), AuthorityType.PERMISSION));
            });

        return grantAuthorities;
    }
}
