package academy.kata.dao;

import academy.kata.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@Component
public class UserDaoImplArrayList implements UserDao, TestData {

    private List<User> userList;


    public UserDaoImplArrayList(List<User> users) {
//        this.userList = users;
        fillTestData();
    }



    @Override
    public void add(User user) {
        userList.add(user);
    }

    @Override
    public User get(Integer id) {
        return userList.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<User> get(Integer start_id, Integer count) {
        return userList.subList(start_id, start_id + count);
    }

    @Override
    public List<User> get() {
        return userList;
    }

    @Override
    public void update(User user) {
        User userToBeUpdated  = get(user.getId());
        if (userToBeUpdated == null) {
            user.setId(userToBeUpdated.getId());
            userList.set(userToBeUpdated.getId(), user);
        }
    }

    @Override
    public void delete(Integer id) {
        userList.remove(get(id));
    }


    private void fillTestData() {
        userList = new ArrayList<>();
        for (int i = 0; i < USERS.length; i++) {
            USERS[i].setId(i);
            userList.add(USERS[i]);
        }
    }
}
