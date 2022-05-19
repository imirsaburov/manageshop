package uz.imirsaburov.manage.shop.specification;

import uz.imirsaburov.manage.shop.base.BaseSpecification;
import uz.imirsaburov.manage.shop.base.CriteriaOperation;
import uz.imirsaburov.manage.shop.base.SearchCriteria;
import uz.imirsaburov.manage.shop.base.SpecificationBuilder;
import uz.imirsaburov.manage.shop.dto.user.UserFilter;
import uz.imirsaburov.manage.shop.entity.user.UserEntity;
import uz.imirsaburov.manage.shop.exceptions.SpecificationCustomMethodNotReadyException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserSpecification extends BaseSpecification<UserEntity> {
    @Override
    public List<Predicate> toCustomPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, List<SearchCriteria> criteriaList) {
        throw new SpecificationCustomMethodNotReadyException(UserEntity.class);
    }

    @Override
    public boolean isQueryDistinct() {
        return true;
    }

    public static SpecificationBuilder<UserSpecification, UserEntity> builder() {
        return SpecificationBuilder.create(UserSpecification.class);
    }

    public static BaseSpecification<UserEntity> findAll(UserFilter filter) {
        SpecificationBuilder<UserSpecification, UserEntity> builder = builder();

        if (filter.getSearch() != null && !filter.getSearch().isEmpty() && !filter.getSearch().isBlank()) {
            builder.addOrCriteria("firstName", CriteriaOperation.LIKE, filter.getSearch());
            builder.addOrCriteria("lastName", CriteriaOperation.LIKE, filter.getSearch());
            builder.addOrCriteria("username", CriteriaOperation.LIKE, filter.getSearch());
        }
        if (filter.getEnabled() != null)
            builder.addAndCriteria("enabled", CriteriaOperation.EQUAL, filter.getEnabled());

        if (filter.getId() != null)
            builder.addAndCriteria("id", CriteriaOperation.EQUAL, filter.getId());

        if (filter.getRole() != null)
            builder.addAndCriteria("role", CriteriaOperation.EQUAL, filter.getRole());

        return builder.build();
    }
}
