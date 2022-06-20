package uz.imirsaburov.manage.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.imirsaburov.manage.shop.dto.product.sold.CreateSoldProductDTO;
import uz.imirsaburov.manage.shop.dto.product.sold.SoldProductDTO;
import uz.imirsaburov.manage.shop.dto.product.sold.SoldProductFilter;
import uz.imirsaburov.manage.shop.exceptions.product.sold.SoldProductNotFoundException;
import uz.imirsaburov.manage.shop.service.AuthorityService;
import uz.imirsaburov.manage.shop.service.SoldProductService;
import uz.imirsaburov.manage.shop.util.CurrentUserUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/product/sold")
@RequiredArgsConstructor
public class SoldProductController {

    private final SoldProductService service;
    private final AuthorityService authorityService;

    @PreAuthorize("hasAnyAuthority('ADMIN','PRODUCT_SIZE_CREATE')")
    @PostMapping
    public ResponseEntity<SoldProductDTO> create(@Valid @RequestBody CreateSoldProductDTO dto) {
        SoldProductDTO SoldProductDTO = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(SoldProductDTO);
    }


    @GetMapping("{id}")
    public ResponseEntity<SoldProductDTO> getById(@PathVariable Long id) {
        SoldProductDTO SoldProductDTO = service.getById(id);

        String username = CurrentUserUtils.getUsername();

        return ResponseEntity.ok(SoldProductDTO);
    }

    @GetMapping
    public ResponseEntity<Page<SoldProductDTO>> getList(@Valid SoldProductFilter filter) {
        String username = CurrentUserUtils.getUsername();

        Page<SoldProductDTO> SoldProductDTO = service.getList(filter);


        return ResponseEntity.ok(SoldProductDTO);
    }


}
