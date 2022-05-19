package uz.imirsaburov.manage.shop.dto.user.moderator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.user.UserEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateModeratorDTO extends BaseDTO {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
    @NotNull
    private Boolean enabled;

    public static UserEntity toEntity(UpdateModeratorDTO dto, UserEntity entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEnabled(dto.getEnabled());
        return entity;
    }
}
