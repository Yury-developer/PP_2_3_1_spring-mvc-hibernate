package academy.kata.service;

import academy.kata.model.User;

import java.util.List;


public interface UserService {

    void add(User user);

    User getById(Integer id);

    List<User> getAll();

    void update(User user);

    void delete(Integer id);


    void generateTestData();
}
