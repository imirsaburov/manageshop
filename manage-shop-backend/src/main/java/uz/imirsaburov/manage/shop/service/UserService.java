package uz.imirsaburov.manage.shop.service;

import org.springframework.data.domain.Page;
import uz.imirsaburov.manage.shop.dto.user.MeDTO;
import uz.imirsaburov.manage.shop.dto.user.moderator.CreateModeratorDTO;
import uz.imirsaburov.manage.shop.dto.user.moderator.ModeratorChangePasswordDTO;
import uz.imirsaburov.manage.shop.dto.user.moderator.ModeratorDTO;
import uz.imirsaburov.manage.shop.dto.user.UserFilter;
import uz.imirsaburov.manage.shop.dto.user.moderator.UpdateModeratorDTO;
import uz.imirsaburov.manage.shop.entity.user.UserEntity;

public interface UserService {

    MeDTO getWithMeDTO(String username);

    UserEntity getByUsername(String username);

    Page<ModeratorDTO> getModeratorList(UserFilter userFilter);

    ModeratorDTO createModerator(CreateModeratorDTO dto);

    ModeratorDTO updateModerator(Long id, UpdateModeratorDTO dto);

    ModeratorDTO changeEnabled(Long id, Boolean enabled);
    ModeratorDTO changePasswordModerator(Long id, ModeratorChangePasswordDTO dto);
}
