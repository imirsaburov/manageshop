package uz.imirsaburov.manage.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.imirsaburov.manage.shop.dto.product.productSize.CreateProductSizeDTO;
import uz.imirsaburov.manage.shop.dto.product.productSize.ProductSizeDTO;
import uz.imirsaburov.manage.shop.dto.product.productSize.ProductSizeFilter;
import uz.imirsaburov.manage.shop.dto.product.productSize.UpdateProductSizeDTO;
import uz.imirsaburov.manage.shop.exceptions.product.size.ProductSizeNotFoundException;
import uz.imirsaburov.manage.shop.service.AuthorityService;
import uz.imirsaburov.manage.shop.service.ProductSizeService;
import uz.imirsaburov.manage.shop.util.CurrentUserUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/product/size")
@RequiredArgsConstructor
public class ProductSizeController {

    private final ProductSizeService service;
    private final AuthorityService authorityService;

    @PreAuthorize("hasAnyAuthority('ADMIN','PRODUCT_SIZE_CREATE')")
    @PostMapping
    public ResponseEntity<ProductSizeDTO> create(@Valid @RequestBody CreateProductSizeDTO dto) {
        ProductSizeDTO productSizeDTO = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productSizeDTO);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','PRODUCT_SIZE_UPDATE')")
    @PutMapping("{id}")
    public ResponseEntity<ProductSizeDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateProductSizeDTO dto) {
        ProductSizeDTO productSizeDTO = service.update(id, dto);
        return ResponseEntity.ok(productSizeDTO);
    }
//
//    @PreAuthorize("hasAnyAuthority('ADMIN','PRODUCT_SIZE_CHANGE_STATUS')")
//    @PatchMapping("status/{id}")
//    public ResponseEntity<ProductSizeDTO> changeStatus(@PathVariable Long id, @RequestParam Boolean status) {
//        ProductSizeDTO productSizeDTO = service.changeStatus(id, status);
//        return ResponseEntity.ok(productSizeDTO);
//    }

    @GetMapping("{id}")
    public ResponseEntity<ProductSizeDTO> getById(@PathVariable Long id) {
        ProductSizeDTO productSizeDTO = service.getById(id);

        String username = CurrentUserUtils.getUsername();

        if (!authorityService.checkAdmin(username))
            throw new ProductSizeNotFoundException(id);

        return ResponseEntity.ok(productSizeDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductSizeDTO>> getList(@Valid ProductSizeFilter filter) {
        String username = CurrentUserUtils.getUsername();

//        boolean isAdmin = authorityService.checkAdmin(username);
//        if (!isAdmin)
//            filter.setStatus(true);
//
//        if (!isAdmin) {
//            boolean isList = authorityService.checkPermission(username, PermissionEnum.PRODUCT_SIZE_LIST);
//            if (!isList)
//                filter.setStatus(true);
//        }

        Page<ProductSizeDTO> productSizeDTO = service.getList(filter);


        return ResponseEntity.ok(productSizeDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','PRODUCT_SIZE_DELETE')")
    @DeleteMapping("{id}")
    public ResponseEntity<ProductSizeDTO> deleteById(@PathVariable Long id) {
        ProductSizeDTO productSizeDTO = service.delete(id);
        return ResponseEntity.ok(productSizeDTO);
    }


}
