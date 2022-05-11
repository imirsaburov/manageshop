package uz.imirsaburov.manage.shop.repository;

import org.springframework.stereotype.Repository;
import uz.imirsaburov.manage.shop.base.BaseRepository;
import uz.imirsaburov.manage.shop.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends BaseRepository<CategoryEntity> {

    boolean existsByNameEqualsIgnoreCase(String name);

    boolean existsByNameEqualsIgnoreCaseAndIdNot(String name, Long id);
}
