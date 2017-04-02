package by.triumgroup.recourse.entity.supplier.impl;

import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.entity.supplier.EntitySupplier;

public class UserSupplier implements EntitySupplier<User,Integer> {
    @Override
    public User getValidEntity() {
        User user = new User();
        user.setName("Ivan");
        user.setSurname("Shimko");
        user.setRole(User.Role.ORGANIZER);
        user.setEmail("a@b.com");
        user.setGender(User.Gender.MALE);
        return user;
    }

    @Override
    public User getInvalidEntity() {
        return null;
    }
}
