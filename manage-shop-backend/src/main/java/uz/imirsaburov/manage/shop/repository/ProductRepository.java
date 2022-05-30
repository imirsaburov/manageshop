package uz.imirsaburov.manage.shop.repository;

import org.springframework.stereotype.Repository;
import uz.imirsaburov.manage.shop.base.BaseRepository;
import uz.imirsaburov.manage.shop.entity.product.ProductEntity;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity> {
}
