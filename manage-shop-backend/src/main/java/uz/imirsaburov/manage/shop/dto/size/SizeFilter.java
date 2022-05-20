package uz.imirsaburov.manage.shop.dto.size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseFilterPageable;

@EqualsAndHashCode(callSuper = true)
@Data
public class SizeFilter extends BaseFilterPageable {
    private String name;
    private Boolean status;
}
