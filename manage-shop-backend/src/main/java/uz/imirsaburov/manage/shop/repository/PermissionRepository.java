package uz.imirsaburov.manage.shop.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import uz.imirsaburov.manage.shop.base.BaseRepository;
import uz.imirsaburov.manage.shop.entity.PermissionEntity;
import uz.imirsaburov.manage.shop.entity.user.UserEntity;
import uz.imirsaburov.manage.shop.enums.PermissionEnum;

import java.util.Optional;

@Repository
public interface PermissionRepository extends BaseRepository<PermissionEntity> {


    Optional<PermissionEntity> findByName(PermissionEnum name);
}
