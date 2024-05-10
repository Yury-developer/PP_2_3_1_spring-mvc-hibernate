package academy.kata.dao;

import org.springframework.stereotype.Repository;
import academy.kata.model.User;

import java.util.List;


@Repository
public interface UserDao {

    void add(User user);

    User get(Integer id);

    List<User> get(Integer start_id, Integer count);

    List<User> get();

    void update(User user);

    void delete(Integer id);


    void deletePhone(Integer phoneId);

    void deleteEmail(Integer emailId);
}
