package uz.imirsaburov.manage.shop.repository;

import org.springframework.stereotype.Repository;
import uz.imirsaburov.manage.shop.base.BaseRepository;
import uz.imirsaburov.manage.shop.entity.SizeEntity;

@Repository
public interface SizeRepository extends BaseRepository<SizeEntity> {

    boolean existsByNameEqualsIgnoreCase(String name);

    boolean existsByNameEqualsIgnoreCaseAndIdNot(String name, Long id);
}
