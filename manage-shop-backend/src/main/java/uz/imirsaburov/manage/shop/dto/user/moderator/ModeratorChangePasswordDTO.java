package uz.imirsaburov.manage.shop.dto.user.moderator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseDTO;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class ModeratorChangePasswordDTO extends BaseDTO {
    @NotBlank
    private String password;
    @NotBlank
    private String repeatPassword;
}
