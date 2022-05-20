package uz.imirsaburov.manage.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.imirsaburov.manage.shop.base.BaseFilterPageable;
import uz.imirsaburov.manage.shop.base.BaseSpecification;
import uz.imirsaburov.manage.shop.dto.category.CategoryDTO;
import uz.imirsaburov.manage.shop.dto.category.CategoryFilter;
import uz.imirsaburov.manage.shop.dto.category.CreateCategoryDTO;
import uz.imirsaburov.manage.shop.dto.category.UpdateCategoryDTO;
import uz.imirsaburov.manage.shop.entity.CategoryEntity;
import uz.imirsaburov.manage.shop.exceptions.category.CategoryNameAlreadyExistException;
import uz.imirsaburov.manage.shop.exceptions.category.CategoryNotFoundException;
import uz.imirsaburov.manage.shop.repository.CategoryRepository;
import uz.imirsaburov.manage.shop.service.CategoryService;
import uz.imirsaburov.manage.shop.specification.CategorySpecification;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public CategoryDTO create(CreateCategoryDTO dto) {
        if (repository.existsByNameEqualsIgnoreCase(dto.getName()))
            throw new CategoryNameAlreadyExistException(dto.getName());

        CategoryEntity entity = CreateCategoryDTO.toEntity(dto, new CategoryEntity());

        repository.save(entity);

        return CategoryDTO.toDTO(entity);
    }

    @Override
    public CategoryDTO update(Long id, UpdateCategoryDTO dto) {
        if (repository.existsByNameEqualsIgnoreCaseAndIdNot(dto.getName(), id))
            throw new CategoryNameAlreadyExistException(dto.getName());

        CategoryEntity entity = get(id);
        UpdateCategoryDTO.toEntity(dto, entity);
        repository.save(entity);

        return CategoryDTO.toDTO(entity);
    }

    @Override
    public CategoryDTO getById(Long id) {
        CategoryEntity entity = get(id);
        return CategoryDTO.toDTO(entity);
    }

    @Override
    public CategoryDTO changeStatus(Long id, Boolean status) {

        CategoryEntity entity = get(id);
        entity.setStatus(status);
        repository.save(entity);

        return CategoryDTO.toDTO(entity);
    }

    @Override
    public Page<CategoryDTO> getList(CategoryFilter filter) {
        BaseSpecification<CategoryEntity> all = CategorySpecification.findAll(filter);
        Pageable pageAble = BaseFilterPageable.getPageAble(filter);

        Page<CategoryEntity> page = repository.findAll(all, pageAble);

        return CategoryDTO.toDTO(page);
    }

    @Override
    public CategoryDTO delete(Long id) {
        CategoryEntity entity = get(id);
        CategoryDTO dto = CategoryDTO.toDTO(entity);
        repository.delete(entity);

        return dto;
    }

    protected CategoryEntity get(Long id) {
        return repository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
