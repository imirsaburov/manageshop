package uz.imirsaburov.manage.shop.dto.product.productSize;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.product.ProductEntity;
import uz.imirsaburov.manage.shop.entity.product.ProductSizeEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateProductSizeDTO extends BaseDTO {

    @NotNull
    private Long productId;
    @NotNull
    private Long sizeId;
    @NotNull
    private Long count;

    public static ProductSizeEntity toEntity(CreateProductSizeDTO dto) {
        ProductSizeEntity entity = new ProductSizeEntity();
        entity.setProductId(dto.getProductId());
        entity.setSizeId(dto.getSizeId());
        entity.setCount(dto.getCount());
        return entity;
    }

}
