package controller;

import entity.User;
import io.avaje.http.api.Controller;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import service.UserService;

import java.util.List;

@Controller
@Singleton
@Slf4j
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Inject
    public UserControllerImpl(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public User getById(final Long id) {
        return userService.getById(id);
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

    @Override
    public void save(final User user) {
        log.info("User: {}/{}", user.id(), user.name());
        userService.save(user);
    }

}
