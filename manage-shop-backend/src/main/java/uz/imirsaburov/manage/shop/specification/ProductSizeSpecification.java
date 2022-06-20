package uz.imirsaburov.manage.shop.specification;

import uz.imirsaburov.manage.shop.base.BaseSpecification;
import uz.imirsaburov.manage.shop.base.CriteriaOperation;
import uz.imirsaburov.manage.shop.base.SearchCriteria;
import uz.imirsaburov.manage.shop.base.SpecificationBuilder;
import uz.imirsaburov.manage.shop.dto.product.productSize.ProductSizeFilter;
import uz.imirsaburov.manage.shop.entity.product.ProductSizeEntity;
import uz.imirsaburov.manage.shop.exceptions.product.size.ProductSizeNotFoundException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductSizeSpecification extends BaseSpecification<ProductSizeEntity> {
    @Override
    public List<Predicate> toCustomPredicate(Root<ProductSizeEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, List<SearchCriteria> criteriaList) {
        throw new ProductSizeNotFoundException(0L);
    }

    @Override
    public boolean isQueryDistinct() {
        return true;
    }

    public static SpecificationBuilder<ProductSizeSpecification, ProductSizeEntity> builder() {
        return SpecificationBuilder.create(ProductSizeSpecification.class);
    }

    public static BaseSpecification<ProductSizeEntity> findAll(ProductSizeFilter filter) {
        SpecificationBuilder<ProductSizeSpecification, ProductSizeEntity> builder = builder();

        if (filter.getProductId()!=null)
            builder.addAndCriteria("productId", CriteriaOperation.EQUAL, filter.getProductId());

//        if (filter.getName() != null && !filter.getName().isEmpty() && !filter.getName().isBlank())
//            builder.addAndCriteria("name", CriteriaOperation.LIKE, filter.getName());
//
//        if (filter.getStatus() != null)
//            builder.addAndCriteria("status", CriteriaOperation.EQUAL, filter.getStatus());

        return builder.build();
    }
}
