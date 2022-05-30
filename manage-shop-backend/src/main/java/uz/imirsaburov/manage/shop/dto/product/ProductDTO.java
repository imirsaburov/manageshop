package uz.imirsaburov.manage.shop.dto.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.product.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDTO extends BaseDTO {
    private Long id;

    private String title;

    private Long imageId;

    private Long incomingPrice;

    private Long categoryId;

    private String categoryName;

    private Long minSellPrice;

    private Long sellPrice;

    private ProductEntity.STATUS status;

    public static ProductDTO toDTO(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        dto.setStatus(entity.getStatus());
        dto.setId(entity.getId());
        dto.setCategoryId(entity.getCategoryId());
        dto.setImageId(entity.getImageId());

        if (entity.getCategoryEntity() != null)
            dto.setCategoryName(entity.getCategoryEntity().getName());

        dto.setTitle(entity.getTitle());
        dto.setIncomingPrice(entity.getIncomingPrice());
        dto.setMinSellPrice(entity.getMinSellPrice());
        dto.setSellPrice(entity.getSellPrice());

        return dto;
    }

    public static List<ProductDTO> toDTO(List<ProductEntity> list) {
        return list
                .stream()
                .map(ProductDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static Page<ProductDTO> toDTO(Page<ProductEntity> page) {
        return new PageImpl<>(toDTO(page.getContent()), page.getPageable(), page.getTotalElements());
    }
}
