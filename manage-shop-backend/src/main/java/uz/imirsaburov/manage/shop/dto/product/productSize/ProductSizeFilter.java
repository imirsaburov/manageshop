package uz.imirsaburov.manage.shop.dto.product.productSize;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseFilterPageable;
import uz.imirsaburov.manage.shop.entity.product.ProductEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductSizeFilter extends BaseFilterPageable {
   private Long id;
   private Long productId;
   private Long sizeId;
}
