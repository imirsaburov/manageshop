package uz.imirsaburov.manage.shop.service;

import org.springframework.data.domain.Page;
import uz.imirsaburov.manage.shop.dto.category.CategoryDTO;
import uz.imirsaburov.manage.shop.dto.category.CategoryFilter;
import uz.imirsaburov.manage.shop.dto.category.CreateCategoryDTO;
import uz.imirsaburov.manage.shop.dto.category.UpdateCategoryDTO;

public interface CategoryService {

    CategoryDTO create(CreateCategoryDTO dto);

    CategoryDTO update(Long id, UpdateCategoryDTO dto);

    CategoryDTO getById(Long id);

    CategoryDTO changeStatus(Long id, Boolean status);

    Page<CategoryDTO> getList(CategoryFilter filter);
}
