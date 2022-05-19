package uz.imirsaburov.manage.shop.dto.user.moderator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.user.UserEntity;
import uz.imirsaburov.manage.shop.enums.RoleEnum;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class ModeratorDTO extends BaseDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private Boolean enabled;

    public static ModeratorDTO toDTO(UserEntity entity) {
        ModeratorDTO userDTO = new ModeratorDTO();
        userDTO.setId(entity.getId());
        userDTO.setFirstName(entity.getFirstName());
        userDTO.setLastName(entity.getLastName());
        userDTO.setUsername(entity.getUsername());
        userDTO.setEnabled(entity.getEnabled());
        return userDTO;
    }

    public static List<ModeratorDTO> toDTO(List<UserEntity> entityList) {
        return entityList.stream().map(ModeratorDTO::toDTO).collect(Collectors.toList());
    }

    public static Page<ModeratorDTO> toDTO(Page<UserEntity> entityPage) {
        return new PageImpl<>(
                toDTO(entityPage.getContent()),
                entityPage.getPageable(),
                entityPage.getTotalElements());
    }

}
