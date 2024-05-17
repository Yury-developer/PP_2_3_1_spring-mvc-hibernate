package academy.kata.service;

import academy.kata.model.User;

import java.util.List;


public interface UserService {

    void add(User user);

    User get(Integer id);

    List<User> get();

    void update(User user);

    void delete(Integer id);

    User deletePhone(Integer userId, Integer phoneId);

    User deleteEmail(Integer userId, Integer emailId);


    void generateTestData();
}
