package controller;

import entity.User;
import io.avaje.http.api.Get;
import io.avaje.http.api.Path;
import io.avaje.http.api.Post;

import java.util.List;

@Path("/users")
public interface UserController {

    @Get
    List<User> findAll();

    @Get("/{id}")
    User getById(Long id);

    @Post
    // @Header
    void save(User user);
}
