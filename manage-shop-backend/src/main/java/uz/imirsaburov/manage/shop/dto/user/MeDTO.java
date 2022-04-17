package uz.imirsaburov.manage.shop.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.dto.oauth2.CustomGrantAuthority;
import uz.imirsaburov.manage.shop.entity.user.UserEntity;
import uz.imirsaburov.manage.shop.enums.AuthorityType;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class MeDTO extends BaseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private List<CustomGrantAuthority> grantAuthorities = new ArrayList<>();
}
