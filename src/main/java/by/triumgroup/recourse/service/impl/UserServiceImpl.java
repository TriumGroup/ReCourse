package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.exception.ServiceException;

import static by.triumgroup.recourse.service.exception.wrapper.ServiceExceptionWrapper.tryCallJPA;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public <S extends User> S save(S entity) throws ServiceException {
        return tryCallJPA(() -> userRepository.save(entity));
    }

    @Override
    public void delete(Long id) throws ServiceException {
        tryCallJPA(() -> userRepository.delete(id));
    }

    @Override
    public User findByEmail(String email) throws ServiceException {
        return tryCallJPA(() -> userRepository.findByEmail(email));
    }
}
