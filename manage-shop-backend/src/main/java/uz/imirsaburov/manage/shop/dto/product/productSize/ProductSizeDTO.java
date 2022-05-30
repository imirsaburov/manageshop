package uz.imirsaburov.manage.shop.dto.product.productSize;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductSizeDTO extends BaseDTO {
    private Long productId;
    private Long sizeId;
    private String sizeName;
    private Long count;
    private Long soldCount;
}
