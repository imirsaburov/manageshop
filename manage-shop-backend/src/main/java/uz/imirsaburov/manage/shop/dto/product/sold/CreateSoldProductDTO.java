package uz.imirsaburov.manage.shop.dto.product.sold;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.product.ProductSizeEntity;
import uz.imirsaburov.manage.shop.entity.product.SoldProductEntity;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateSoldProductDTO extends BaseDTO {

    @NotNull
    private Long productId;
    @NotNull
    private Long sizeId;
    @NotNull
    private Long price;

    public static SoldProductEntity toEntity(CreateSoldProductDTO dto) {
        SoldProductEntity entity = new SoldProductEntity();
        entity.setProductId(dto.getProductId());
        entity.setSizeId(dto.getSizeId());
        entity.setPrice(dto.getPrice());
        return entity;
    }

}
