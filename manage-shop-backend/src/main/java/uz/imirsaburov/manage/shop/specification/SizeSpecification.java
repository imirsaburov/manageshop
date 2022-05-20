package uz.imirsaburov.manage.shop.specification;

import uz.imirsaburov.manage.shop.base.BaseSpecification;
import uz.imirsaburov.manage.shop.base.CriteriaOperation;
import uz.imirsaburov.manage.shop.base.SearchCriteria;
import uz.imirsaburov.manage.shop.base.SpecificationBuilder;
import uz.imirsaburov.manage.shop.dto.size.SizeFilter;
import uz.imirsaburov.manage.shop.entity.SizeEntity;
import uz.imirsaburov.manage.shop.exceptions.size.SizeNotFoundException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class SizeSpecification extends BaseSpecification<SizeEntity> {
    @Override
    public List<Predicate> toCustomPredicate(Root<SizeEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, List<SearchCriteria> criteriaList) {
        throw new SizeNotFoundException(0L);
    }

    @Override
    public boolean isQueryDistinct() {
        return true;
    }

    public static SpecificationBuilder<SizeSpecification, SizeEntity> builder() {
        return SpecificationBuilder.create(SizeSpecification.class);
    }

    public static BaseSpecification<SizeEntity> findAll(SizeFilter filter) {
        SpecificationBuilder<SizeSpecification, SizeEntity> builder = builder();

        if (filter.getName() != null && !filter.getName().isEmpty() && !filter.getName().isBlank())
            builder.addAndCriteria("name", CriteriaOperation.LIKE, filter.getName());

        if (filter.getStatus() != null)
            builder.addAndCriteria("status", CriteriaOperation.EQUAL, filter.getStatus());

        return builder.build();
    }
}
