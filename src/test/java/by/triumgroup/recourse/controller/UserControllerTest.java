package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.controller.impl.UserControllerImpl;
import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.entity.supplier.impl.UserSupplier;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.util.TestBeansSupplier;


public class UserControllerTest extends CrudControllerTest<User, Integer> {

    public UserControllerTest() {
        super(new UserSupplier(), new TestBeansSupplier<>(UserControllerImpl.class, UserService.class), "user");
    }
}
