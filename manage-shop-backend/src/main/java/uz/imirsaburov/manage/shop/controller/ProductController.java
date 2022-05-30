package uz.imirsaburov.manage.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.imirsaburov.manage.shop.dto.product.CreateProductDTO;
import uz.imirsaburov.manage.shop.dto.product.ProductDTO;
import uz.imirsaburov.manage.shop.dto.product.ProductFilter;
import uz.imirsaburov.manage.shop.dto.product.UpdateProductDTO;
import uz.imirsaburov.manage.shop.entity.product.ProductEntity;
import uz.imirsaburov.manage.shop.enums.PermissionEnum;
import uz.imirsaburov.manage.shop.exceptions.product.ProductNotFoundException;
import uz.imirsaburov.manage.shop.service.AuthorityService;
import uz.imirsaburov.manage.shop.service.ProductService;
import uz.imirsaburov.manage.shop.util.CurrentUserUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final AuthorityService authorityService;

    @PreAuthorize("hasAnyAuthority('ADMIN','PRODUCT_CREATE')")
    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody CreateProductDTO dto) {
        ProductDTO productDTO = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','PRODUCT_UPDATE')")
    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateProductDTO dto) {
        ProductDTO productDTO = service.update(id, dto);
        return ResponseEntity.ok(productDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','PRODUCT_CHANGE_STATUS')")
    @PatchMapping("status/{id}")
    public ResponseEntity<ProductDTO> changeStatus(@PathVariable Long id, @RequestParam ProductEntity.STATUS status) {
        ProductDTO productDTO = service.changeStatus(id, status);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        ProductDTO productDTO = service.getById(id);

        String username = CurrentUserUtils.getUsername();

        if (!authorityService.checkAdmin(username))
            throw new ProductNotFoundException(id);

        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getList(@Valid ProductFilter filter) {
        String username = CurrentUserUtils.getUsername();

        boolean isAdmin = authorityService.checkAdmin(username);
        if (!isAdmin)
            filter.setStatus(ProductEntity.STATUS.ACTIVE);

        if (!isAdmin) {
            boolean isList = authorityService.checkPermission(username, PermissionEnum.PRODUCT_LIST);
            if (!isList)
                filter.setStatus(ProductEntity.STATUS.ACTIVE);
        }

        Page<ProductDTO> productDTO = service.getList(filter);


        return ResponseEntity.ok(productDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','PRODUCT_DELETE')")
    @DeleteMapping("{id}")
    public ResponseEntity<ProductDTO> deleteById(@PathVariable Long id) {
        ProductDTO productDTO = service.delete(id);
        return ResponseEntity.ok(productDTO);
    }


}
