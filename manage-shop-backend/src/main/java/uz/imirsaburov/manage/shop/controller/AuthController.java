package uz.imirsaburov.manage.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.imirsaburov.manage.shop.dto.user.MeDTO;
import uz.imirsaburov.manage.shop.service.UserService;
import uz.imirsaburov.manage.shop.util.CurrentUserUtils;

@RestController
@RequestMapping("v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("user/me")
    public MeDTO me() {
        String username = CurrentUserUtils.getUsername();
        return userService.getWithMeDTO(username);
    }

}
