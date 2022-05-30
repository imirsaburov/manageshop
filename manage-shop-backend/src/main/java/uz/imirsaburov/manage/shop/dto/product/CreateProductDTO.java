package uz.imirsaburov.manage.shop.dto.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.product.ProductEntity;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateProductDTO extends BaseDTO {
    @NotBlank(message = "{validation.create.product.title}")
    private String title;

    private Long incomingPrice;

    private Long categoryId;

    private Long imageId;

    private Long minSellPrice;

    private Long sellPrice;

    private ProductEntity.STATUS status;

    public static ProductEntity toEntity(CreateProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        entity.setTitle(dto.getTitle());
        entity.setImageId(dto.getImageId());
        entity.setIncomingPrice(dto.getIncomingPrice());
        entity.setCategoryId(dto.getCategoryId());
        entity.setMinSellPrice(dto.getMinSellPrice());
        entity.setSellPrice(dto.getSellPrice());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
