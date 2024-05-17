package academy.kata.dao;

import org.springframework.stereotype.Repository;
import academy.kata.model.User;

import java.util.List;


@Repository
public interface UserDao {

    void add(User user);

    User getById(Integer id);

    List<User> getAll();

    void update(User user);

    void delete(Integer id);
}
