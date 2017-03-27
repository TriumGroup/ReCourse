package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.User;

public interface UserService extends CrudService<User, Long> {

    User findById(Long id);

    User findByEmail(String email);

}
