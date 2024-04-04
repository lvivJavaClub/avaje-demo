package service;

import entity.User;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Singleton
@Slf4j
public class UserServiceImpl implements UserService {

    private final Map<Long, User> userMap = new HashMap<>();

    @Override
    public User getById(final Long id) {
        return userMap.get(id);
    }

    @Override
    public List<User> findAll() {
        return userMap.values().stream().toList();
    }

    @Override
    public void save(final User user) {
        userMap.put(user.id(), user);
    }

}
