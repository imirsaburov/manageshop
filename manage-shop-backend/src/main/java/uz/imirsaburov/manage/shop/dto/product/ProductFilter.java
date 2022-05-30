package uz.imirsaburov.manage.shop.dto.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.imirsaburov.manage.shop.base.BaseFilterPageable;
import uz.imirsaburov.manage.shop.entity.product.ProductEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductFilter extends BaseFilterPageable {
    private String title;
    private String minPrice;
    private String maxPrice;
    private List<Long> sizeIdList;
    private ProductEntity.STATUS status;
}
