package uz.imirsaburov.manage.shop.dto.product.sold;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;
import uz.imirsaburov.manage.shop.base.BaseDTO;
import uz.imirsaburov.manage.shop.entity.product.ProductSizeEntity;
import uz.imirsaburov.manage.shop.entity.product.SoldProductEntity;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class SoldProductDTO extends BaseDTO {
    private Long id;
    private Long productId;
    private Long sizeId;
    private String sizeName;
    private Long price;

    public static SoldProductDTO toDTO(SoldProductEntity entity) {
        SoldProductDTO dto = new SoldProductDTO();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setProductId(entity.getProductId());
        dto.setSizeId(entity.getSizeId());
        dto.setSizeName(entity.getSizeEntity().getName());
        return dto;
    }

    public static List<SoldProductDTO> toDTO(List<SoldProductEntity> list) {
        return list.stream().map(SoldProductDTO::toDTO).collect(Collectors.toList());
    }

    public static Page<SoldProductDTO> toDTO(Page<SoldProductEntity> page) {
        return page.map(SoldProductDTO::toDTO);
    }
}
