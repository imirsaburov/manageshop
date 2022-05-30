package uz.imirsaburov.manage.shop.specification;

import uz.imirsaburov.manage.shop.base.BaseSpecification;
import uz.imirsaburov.manage.shop.base.CriteriaOperation;
import uz.imirsaburov.manage.shop.base.SearchCriteria;
import uz.imirsaburov.manage.shop.base.SpecificationBuilder;
import uz.imirsaburov.manage.shop.dto.product.ProductFilter;
import uz.imirsaburov.manage.shop.entity.product.ProductEntity;
import uz.imirsaburov.manage.shop.exceptions.SpecificationCustomMethodNotReadyException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductSpecification extends BaseSpecification<ProductEntity> {
    @Override
    public List<Predicate> toCustomPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, List<SearchCriteria> criteriaList) {
        throw new SpecificationCustomMethodNotReadyException(ProductEntity.class);
    }

    @Override
    public boolean isQueryDistinct() {
        return true;
    }

    public static SpecificationBuilder<ProductSpecification, ProductEntity> builder() {
        return SpecificationBuilder.create(ProductSpecification.class);
    }

    public static BaseSpecification<ProductEntity> findAll(ProductFilter filter) {
        SpecificationBuilder<ProductSpecification, ProductEntity> builder = builder();

        if (filter.getTitle() != null && !filter.getTitle().isEmpty() && !filter.getTitle().isBlank())
            builder.addAndCriteria("name", CriteriaOperation.LIKE, filter.getTitle());

        if (filter.getStatus() != null)
            builder.addAndCriteria("status", CriteriaOperation.EQUAL, filter.getStatus());

        return builder.build();
    }
}
