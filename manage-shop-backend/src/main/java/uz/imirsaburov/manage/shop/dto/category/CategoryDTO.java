package uz.imirsaburov.manage.shop.dto.category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDTO extends BaseDTO {

    private Long id;
    private String name;
    private Boolean status;
}
