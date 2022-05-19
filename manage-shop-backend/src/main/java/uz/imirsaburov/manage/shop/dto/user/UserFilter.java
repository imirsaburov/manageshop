package uz.imirsaburov.manage.shop.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import springfox.documentation.annotations.ApiIgnore;
import uz.imirsaburov.manage.shop.base.BaseFilterPageable;
import uz.imirsaburov.manage.shop.enums.RoleEnum;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserFilter extends BaseFilterPageable {

    private Long id;

    private String search;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private RoleEnum role;

    private Boolean enabled;
}
