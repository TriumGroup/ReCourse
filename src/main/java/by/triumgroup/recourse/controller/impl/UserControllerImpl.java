package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.AbstractCrudController;
import by.triumgroup.recourse.controller.UserController;
import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public class UserControllerImpl extends AbstractCrudController<User, Long> implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
