package uz.imirsaburov.manage.shop.dto.category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseDTO;

import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateCategoryDTO extends BaseDTO {
    @Pattern(regexp = "[A-z]{3,50}", message = "{validation.create.category.name}")
    private String name;
    private Boolean status;
}
