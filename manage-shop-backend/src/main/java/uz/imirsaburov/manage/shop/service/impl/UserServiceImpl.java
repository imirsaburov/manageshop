package uz.imirsaburov.manage.shop.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.imirsaburov.manage.shop.base.BaseFilterPageable;
import uz.imirsaburov.manage.shop.dto.oauth2.CustomGrantAuthority;
import uz.imirsaburov.manage.shop.dto.user.MeDTO;
import uz.imirsaburov.manage.shop.dto.user.moderator.CreateModeratorDTO;
import uz.imirsaburov.manage.shop.dto.user.moderator.ModeratorChangePasswordDTO;
import uz.imirsaburov.manage.shop.dto.user.moderator.ModeratorDTO;
import uz.imirsaburov.manage.shop.dto.user.UserFilter;
import uz.imirsaburov.manage.shop.dto.user.moderator.UpdateModeratorDTO;
import uz.imirsaburov.manage.shop.entity.user.UserEntity;
import uz.imirsaburov.manage.shop.enums.RoleEnum;
import uz.imirsaburov.manage.shop.exceptions.user.PasswordsNotEqualsException;
import uz.imirsaburov.manage.shop.exceptions.user.UserNotFoundException;
import uz.imirsaburov.manage.shop.exceptions.user.UsernameExistException;
import uz.imirsaburov.manage.shop.exceptions.user.UsernameNotFoundException;
import uz.imirsaburov.manage.shop.repository.UserRepository;
import uz.imirsaburov.manage.shop.service.AuthorityService;
import uz.imirsaburov.manage.shop.service.UserService;
import uz.imirsaburov.manage.shop.specification.UserSpecification;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           @Lazy AuthorityService authorityService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }

    public UserEntity getByUsername(String username) {
        return userRepository.findByUsernameEqualsIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public Page<ModeratorDTO> getModeratorList(UserFilter userFilter) {
        userFilter.setRole(RoleEnum.MODERATOR);
        Page<UserEntity> all = userRepository.findAll(UserSpecification.findAll(userFilter), BaseFilterPageable.getPageAble(userFilter));
        return ModeratorDTO.toDTO(all);
    }

    @Override
    public ModeratorDTO createModerator(CreateModeratorDTO dto) {
        checkUsername(dto.getUsername());

        UserEntity entity = CreateModeratorDTO.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setRole(RoleEnum.MODERATOR);
        userRepository.save(entity);
        return ModeratorDTO.toDTO(entity);
    }

    @Override
    public ModeratorDTO updateModerator(Long id, UpdateModeratorDTO dto) {
        UserEntity entity = getById(id);
        UpdateModeratorDTO.toEntity(dto, entity);
        userRepository.save(entity);
        return ModeratorDTO.toDTO(entity);
    }

    @Override
    public ModeratorDTO changeEnabled(Long id, Boolean enabled) {
        UserEntity entity = getById(id);
        entity.setEnabled(enabled);
        userRepository.save(entity);
        return ModeratorDTO.toDTO(entity);
    }

    @Override
    public ModeratorDTO changePasswordModerator(Long id, ModeratorChangePasswordDTO dto) {
        UserEntity entity = getById(id);
        if (!dto.getPassword().equals(dto.getRepeatPassword()))
            throw new PasswordsNotEqualsException(dto.getPassword(), dto.getRepeatPassword());

        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(entity);
        return ModeratorDTO.toDTO(entity);
    }

    public MeDTO getWithMeDTO(String username) {

        UserEntity userEntity = getByUsername(username);
        List<CustomGrantAuthority> allAuthorities = authorityService.getAuthorities(username);

        MeDTO meDTO = new MeDTO();
        meDTO.setId(userEntity.getId());
        meDTO.setLastName(userEntity.getLastName());
        meDTO.setFirstName(userEntity.getFirstName());
        meDTO.setUsername(userEntity.getUsername());
        meDTO.setGrantAuthorities(allAuthorities);

        return meDTO;
    }

    public void checkUsername(String username) {
        if (userRepository.existsByUsernameEqualsIgnoreCase(username))
            throw new UsernameExistException(username);
    }

    public UserEntity getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

}
