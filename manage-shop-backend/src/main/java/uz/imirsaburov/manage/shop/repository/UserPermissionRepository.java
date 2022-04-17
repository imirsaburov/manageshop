package uz.imirsaburov.manage.shop.repository;

import org.springframework.stereotype.Repository;
import uz.imirsaburov.manage.shop.base.BaseRepository;
import uz.imirsaburov.manage.shop.entity.user.UserEntity;
import uz.imirsaburov.manage.shop.entity.user.UserPermissionEntity;

import java.util.List;

@Repository
public interface UserPermissionRepository extends BaseRepository<UserPermissionEntity> {
    List<UserPermissionEntity> findAllByUserId(Long userId);
}
