package uz.imirsaburov.manage.shop.dto.category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseFilterPageable;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryFilter extends BaseFilterPageable {
    private String name;
    private Boolean status;
}
