package uz.imirsaburov.manage.shop.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import uz.imirsaburov.manage.shop.base.BaseRepository;
import uz.imirsaburov.manage.shop.entity.user.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {


    Optional<UserEntity> findByUsernameEqualsIgnoreCase(String username);

    boolean existsByUsernameEqualsIgnoreCase(String username);
}
