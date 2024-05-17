package academy.kata.service;

import academy.kata.dao.*;
import academy.kata.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@Service
public class UserServiceImpl implements UserService, TestData {

    private final UserDao userDao;


    @Autowired
    public UserServiceImpl(UserDao userDao) {   // @Qualifier("userDaoImplArrayList") // Или userDaoImplMySQL
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        userDao.delete(id);
    }


    @Override
    @Transactional
    public void generateTestData() {
        Arrays.stream(USERS).forEach(userDao::add);
    }
}
