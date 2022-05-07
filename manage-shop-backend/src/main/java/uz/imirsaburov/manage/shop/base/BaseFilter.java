package uz.imirsaburov.manage.shop.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Sort;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseFilter extends BaseDTO {
    private String sortField;
    private Sort.Direction direction;
}
