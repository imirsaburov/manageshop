package uz.imirsaburov.manage.shop.repository;

import org.springframework.stereotype.Repository;
import uz.imirsaburov.manage.shop.base.BaseRepository;
import uz.imirsaburov.manage.shop.entity.product.ProductSizeEntity;

@Repository
public interface ProductSizeRepository extends BaseRepository<ProductSizeEntity> {

    boolean existsBySizeIdAndProductId(Long sizeId, Long productId);
    boolean existsBySizeIdAndProductIdAndIdNot(Long sizeId, Long productId, Long id);
}
