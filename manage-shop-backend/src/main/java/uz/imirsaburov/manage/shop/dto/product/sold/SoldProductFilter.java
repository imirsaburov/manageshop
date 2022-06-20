package uz.imirsaburov.manage.shop.dto.product.sold;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseFilterPageable;

@EqualsAndHashCode(callSuper = true)
@Data
public class SoldProductFilter extends BaseFilterPageable {
   private Long id;
   private Long productId;
   private Long sizeId;
}
