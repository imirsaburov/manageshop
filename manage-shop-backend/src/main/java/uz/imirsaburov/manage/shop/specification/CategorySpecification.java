package uz.imirsaburov.manage.shop.specification;

import uz.imirsaburov.manage.shop.base.BaseSpecification;
import uz.imirsaburov.manage.shop.base.CriteriaOperation;
import uz.imirsaburov.manage.shop.base.SearchCriteria;
import uz.imirsaburov.manage.shop.base.SpecificationBuilder;
import uz.imirsaburov.manage.shop.dto.category.CategoryFilter;
import uz.imirsaburov.manage.shop.entity.CategoryEntity;
import uz.imirsaburov.manage.shop.exceptions.category.CategoryNotFoundException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CategorySpecification extends BaseSpecification<CategoryEntity> {
    @Override
    public List<Predicate> toCustomPredicate(Root<CategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, List<SearchCriteria> criteriaList) {
        throw new CategoryNotFoundException(0L);
    }

    @Override
    public boolean isQueryDistinct() {
        return true;
    }

    public static SpecificationBuilder<CategorySpecification, CategoryEntity> builder() {
        return SpecificationBuilder.create(CategorySpecification.class);
    }

    public static BaseSpecification<CategoryEntity> findAll(CategoryFilter filter) {
        SpecificationBuilder<CategorySpecification, CategoryEntity> builder = builder();

        if (filter.getName() != null && !filter.getName().isEmpty() && !filter.getName().isBlank())
            builder.addAndCriteria("name", CriteriaOperation.LIKE, filter.getName());

        if (filter.getStatus() != null)
            builder.addAndCriteria("status", CriteriaOperation.EQUAL, filter.getStatus());

        return builder.build();
    }
}
