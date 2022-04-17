package uz.imirsaburov.manage.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.imirsaburov.manage.shop.dto.oauth2.CustomGrantAuthority;
import uz.imirsaburov.manage.shop.dto.user.MeDTO;
import uz.imirsaburov.manage.shop.entity.user.UserEntity;
import uz.imirsaburov.manage.shop.entity.user.UserPermissionEntity;
import uz.imirsaburov.manage.shop.enums.AuthorityType;
import uz.imirsaburov.manage.shop.exceptions.UsernameNotFoundException;
import uz.imirsaburov.manage.shop.repository.UserPermissionRepository;
import uz.imirsaburov.manage.shop.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserPermissionRepository userPermissionRepository;

    public UserEntity getByUsername(String username) {
        return userRepository.findByUsernameEqualsIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public List<CustomGrantAuthority> getAllAuthorities(String username) {
        UserEntity userEntity = getByUsername(username);

        List<CustomGrantAuthority> grantAuthorities = new ArrayList<>();

        grantAuthorities.add(new CustomGrantAuthority(userEntity.getRole().name(), AuthorityType.ROLE));

        List<UserPermissionEntity> userPermissionEntityList = userPermissionRepository.findAllByUserId(userEntity.getId());

        if (!userPermissionEntityList.isEmpty())
            userPermissionEntityList.forEach(entity -> {
                grantAuthorities.add(new CustomGrantAuthority(entity.getPermissionEntity().getName().name(), AuthorityType.PERMISSION));
            });

        return grantAuthorities;
    }

    public MeDTO getWithMeDTO(String username) {

        UserEntity userEntity = getByUsername(username);
        List<CustomGrantAuthority> allAuthorities = getAllAuthorities(username);

        MeDTO meDTO = new MeDTO();
        meDTO.setId(userEntity.getId());
        meDTO.setLastName(userEntity.getLastName());
        meDTO.setFirstName(userEntity.getFirstName());
        meDTO.setUsername(userEntity.getUsername());
        meDTO.setGrantAuthorities(allAuthorities);

        return meDTO;
    }
}
