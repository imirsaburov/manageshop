package uz.imirsaburov.manage.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.imirsaburov.manage.shop.dto.user.UserFilter;
import uz.imirsaburov.manage.shop.dto.user.moderator.CreateModeratorDTO;
import uz.imirsaburov.manage.shop.dto.user.moderator.ModeratorChangePasswordDTO;
import uz.imirsaburov.manage.shop.dto.user.moderator.ModeratorDTO;
import uz.imirsaburov.manage.shop.dto.user.moderator.UpdateModeratorDTO;
import uz.imirsaburov.manage.shop.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/user/moderator")
@RequiredArgsConstructor
public class ModeratorController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ModeratorDTO> create(@Valid@RequestBody CreateModeratorDTO dto) {
        ModeratorDTO moderator = userService.createModerator(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(moderator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModeratorDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateModeratorDTO dto) {
        ModeratorDTO moderator = userService.updateModerator(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(moderator);
    }

    @PatchMapping("/enabled/{id}")
    public ResponseEntity<ModeratorDTO> changeStatus(@PathVariable Long id, @RequestParam Boolean status) {
        ModeratorDTO moderator = userService.changeEnabled(id, status);
        return ResponseEntity.status(HttpStatus.OK).body(moderator);
    }

    @PatchMapping("/password/{id}")
    public ResponseEntity<ModeratorDTO> changePassword(@PathVariable Long id, @Valid @RequestBody ModeratorChangePasswordDTO dto) {
        ModeratorDTO moderator = userService.changePasswordModerator(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(moderator);
    }

    @GetMapping
    public ResponseEntity<Page<ModeratorDTO>> getList(UserFilter userFilter) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.getModeratorList(userFilter));
    }
}
