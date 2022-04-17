package uz.imirsaburov.manage.shop.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.imirsaburov.manage.shop.entity.PermissionEntity;
import uz.imirsaburov.manage.shop.entity.user.UserEntity;
import uz.imirsaburov.manage.shop.enums.PermissionEnum;
import uz.imirsaburov.manage.shop.enums.RoleEnum;
import uz.imirsaburov.manage.shop.properties.AdminProperties;
import uz.imirsaburov.manage.shop.repository.PermissionRepository;
import uz.imirsaburov.manage.shop.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AdminProperties adminProperties;
    private final PasswordEncoder passwordEncoder;
    private final PermissionRepository permissionRepository;

    @Override
    public void run(String... args) {
        initializePermissions();
        initializeAdmin();
    }

    public void initializeAdmin() {
        UserEntity userEntity = userRepository.findByUsernameEqualsIgnoreCase(adminProperties.getUsername()).orElseGet(UserEntity::new);
        userEntity.setFirstName(adminProperties.getUsername());
        userEntity.setLastName(adminProperties.getUsername());
        userEntity.setUsername(adminProperties.getUsername());
        userEntity.setPassword(passwordEncoder.encode(adminProperties.getPassword()));
        userEntity.setRole(RoleEnum.ADMIN);
        userEntity.setEnabled(true);
        userRepository.save(userEntity);
    }

    public void initializePermissions() {
        for (PermissionEnum value : PermissionEnum.values()) {
            PermissionEntity permissionEntity = permissionRepository.findByName(value).orElseGet(PermissionEntity::new);
            permissionEntity.setName(value);
            permissionEntity.setDescription(value.getDescription());
            permissionRepository.save(permissionEntity);
        }
    }
}
