package uz.imirsaburov.manage.shop.dto.product.productSize;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.product.ProductEntity;
import uz.imirsaburov.manage.shop.entity.product.ProductSizeEntity;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductSizeDTO extends BaseDTO {
    private Long id;
    private Long productId;
    private Long sizeId;
    private String sizeName;
    private Long count;
    private Long soldCount;

    public static ProductSizeDTO toDTO(ProductSizeEntity entity) {
        ProductSizeDTO dto = new ProductSizeDTO();
        dto.setId(entity.getId());
        dto.setCount(entity.getCount());
        dto.setSoldCount(entity.getSoldCount());
        dto.setProductId(entity.getProductId());
        dto.setSizeId(entity.getSizeId());
        dto.setSizeName(entity.getSizeEntity().getName());
        return dto;
    }

    public static List<ProductSizeDTO> toDTO(List<ProductSizeEntity> list) {
        return list.stream().map(ProductSizeDTO::toDTO).collect(Collectors.toList());
    }

    public static Page<ProductSizeDTO> toDTO(Page<ProductSizeEntity> page) {
        return page.map(ProductSizeDTO::toDTO);
    }
}
