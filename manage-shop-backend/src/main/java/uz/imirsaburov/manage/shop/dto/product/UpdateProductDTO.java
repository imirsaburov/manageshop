package uz.imirsaburov.manage.shop.dto.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.product.ProductEntity;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateProductDTO extends BaseDTO {
    @NotBlank(message = "{validation.update.product.title}")
    private String title;

    private Long incomingPrice;

    private Long imageId;

    private Long categoryId;

    private Long minSellPrice;

    private Long sellPrice;

    private ProductEntity.STATUS status;

    public static ProductEntity toEntity(UpdateProductDTO dto, ProductEntity entity) {
        entity.setTitle(dto.getTitle());
        entity.setImageId(dto.getImageId());
        entity.setIncomingPrice(dto.getIncomingPrice());
        entity.setStatus(dto.getStatus());
        entity.setMinSellPrice(dto.getMinSellPrice());
        entity.setCategoryId(dto.getCategoryId());
        entity.setSellPrice(dto.getSellPrice());
        return entity;
    }
}
