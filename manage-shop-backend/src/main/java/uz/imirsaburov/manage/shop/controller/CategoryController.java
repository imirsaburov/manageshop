package uz.imirsaburov.manage.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.imirsaburov.manage.shop.dto.category.CategoryDTO;
import uz.imirsaburov.manage.shop.dto.category.CategoryFilter;
import uz.imirsaburov.manage.shop.dto.category.CreateCategoryDTO;
import uz.imirsaburov.manage.shop.dto.category.UpdateCategoryDTO;
import uz.imirsaburov.manage.shop.enums.PermissionEnum;
import uz.imirsaburov.manage.shop.exceptions.category.CategoryNotFoundException;
import uz.imirsaburov.manage.shop.service.AuthoritySerivce;
import uz.imirsaburov.manage.shop.service.CategoryService;
import uz.imirsaburov.manage.shop.util.CurrentUserUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;
    private final AuthoritySerivce authoritySerivce;

    @PreAuthorize("hasAnyAuthority('ADMIN','CATEGORY_CREATE')")
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CreateCategoryDTO dto) {
        CategoryDTO categoryDTO = service.create(dto);
        return ResponseEntity.ok(categoryDTO);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','CATEGORY_UPDATE')")
    @PutMapping("{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateCategoryDTO dto) {
        CategoryDTO categoryDTO = service.update(id, dto);
        return ResponseEntity.ok(categoryDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CATEGORY_CHANGE_STATUS')")
    @PatchMapping("status/{id}")
    public ResponseEntity<CategoryDTO> changeStatus(@PathVariable Long id, @RequestParam Boolean status) {
        CategoryDTO categoryDTO = service.changeStatus(id, status);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        CategoryDTO categoryDTO = service.getById(id);

        String username = CurrentUserUtils.getUsername();

        if (!authoritySerivce.checkAdmin(username))
            throw new CategoryNotFoundException(id);

        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getList(@Valid CategoryFilter filter) {
        String username = CurrentUserUtils.getUsername();

        boolean isAdmin = authoritySerivce.checkAdmin(username);
        if (!isAdmin)
            filter.setStatus(true);

        if (!isAdmin) {
            boolean isList = authoritySerivce.checkPermission(username, PermissionEnum.CATEGORY_LIST);
            if (!isList)
                filter.setStatus(true);
        }

        Page<CategoryDTO> categoryDTO = service.getList(filter);


        return ResponseEntity.ok(categoryDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CATEGORY_DELETE')")
    @DeleteMapping("{id}")
    public ResponseEntity<CategoryDTO> deleteById(@PathVariable Long id) {
        CategoryDTO categoryDTO = service.delete(id);
        return ResponseEntity.ok(categoryDTO);
    }


}
