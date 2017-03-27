package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public interface UserController extends CrudController<User, Long> {

    @PreAuthorize("hasAuthority('GUEST')")
    @GetMapping("me")
    ResponseEntity<?> getUserInfo(@AuthenticationPrincipal User user);

}
