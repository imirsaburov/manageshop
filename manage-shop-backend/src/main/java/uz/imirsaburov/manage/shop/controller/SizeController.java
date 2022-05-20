package uz.imirsaburov.manage.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.imirsaburov.manage.shop.dto.size.SizeDTO;
import uz.imirsaburov.manage.shop.dto.size.SizeFilter;
import uz.imirsaburov.manage.shop.dto.size.CreateSizeDTO;
import uz.imirsaburov.manage.shop.dto.size.UpdateSizeDTO;
import uz.imirsaburov.manage.shop.enums.PermissionEnum;
import uz.imirsaburov.manage.shop.exceptions.size.SizeNotFoundException;
import uz.imirsaburov.manage.shop.service.AuthorityService;
import uz.imirsaburov.manage.shop.service.SizeService;
import uz.imirsaburov.manage.shop.util.CurrentUserUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/size")
@RequiredArgsConstructor
public class SizeController {

    private final SizeService service;
    private final AuthorityService authorityService;

    @PreAuthorize("hasAnyAuthority('ADMIN','SIZE_CREATE')")
    @PostMapping
    public ResponseEntity<SizeDTO> create(@Valid @RequestBody CreateSizeDTO dto) {
        SizeDTO sizeDTO = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sizeDTO);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','SIZE_UPDATE')")
    @PutMapping("{id}")
    public ResponseEntity<SizeDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateSizeDTO dto) {
        SizeDTO sizeDTO = service.update(id, dto);
        return ResponseEntity.ok(sizeDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SIZE_CHANGE_STATUS')")
    @PatchMapping("status/{id}")
    public ResponseEntity<SizeDTO> changeStatus(@PathVariable Long id, @RequestParam Boolean status) {
        SizeDTO sizeDTO = service.changeStatus(id, status);
        return ResponseEntity.ok(sizeDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<SizeDTO> getById(@PathVariable Long id) {
        SizeDTO sizeDTO = service.getById(id);

        String username = CurrentUserUtils.getUsername();

        if (!authorityService.checkAdmin(username))
            throw new SizeNotFoundException(id);

        return ResponseEntity.ok(sizeDTO);
    }

    @GetMapping
    public ResponseEntity<Page<SizeDTO>> getList(@Valid SizeFilter filter) {
        String username = CurrentUserUtils.getUsername();

        boolean isAdmin = authorityService.checkAdmin(username);
        if (!isAdmin)
            filter.setStatus(true);

        if (!isAdmin) {
            boolean isList = authorityService.checkPermission(username, PermissionEnum.SIZE_LIST);
            if (!isList)
                filter.setStatus(true);
        }

        Page<SizeDTO> sizeDTO = service.getList(filter);


        return ResponseEntity.ok(sizeDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SIZE_DELETE')")
    @DeleteMapping("{id}")
    public ResponseEntity<SizeDTO> deleteById(@PathVariable Long id) {
        SizeDTO sizeDTO = service.delete(id);
        return ResponseEntity.ok(sizeDTO);
    }


}
