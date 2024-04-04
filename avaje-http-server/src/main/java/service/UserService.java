package service;

import entity.User;

import java.util.List;

public interface UserService {

    User getById(Long id);

    List<User> findAll();

    void save(User user);
}
