package br.com.scsoftware.afinese.domains.auth.controller.api.v1;

import br.com.scsoftware.afinese.domains.auth.controller.api.v1.response.UserAvatarResponse;
import br.com.scsoftware.afinese.domains.auth.controller.api.v1.response.UserResponse;
import br.com.scsoftware.afinese.domains.auth.converter.UserConverter;
import br.com.scsoftware.afinese.domains.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author samuel-cruz
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> listUser(@PathVariable final String username) {

        return ResponseEntity.ok(UserConverter.toResponse(userService.listUser(username)));
    }

    @GetMapping("/{id}/avatar")
    public ResponseEntity<UserAvatarResponse> getAvatar(@PathVariable final Long id) {

        return ResponseEntity.ok(UserConverter.toAvatarResponse(id, userService.getAvatar(id)));
    }
}
