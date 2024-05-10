package academy.kata.service;

import academy.kata.dao.*;
import academy.kata.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService, TestData {

    private final UserDao userDao;


    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImplMySQL") UserDao userDao) {   // @Qualifier("userDaoImplArrayList") // Или userDaoImplMySQL
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User get(Integer id) {
        return userDao.get(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> get(Integer startId, Integer count) {
        return userDao.get(startId, count);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> get() {
        return userDao.get();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }


    @Override
    public void generateTestData() {
        Arrays.stream(USERS).forEach(userDao::add);
        printUsers(get().toArray(User[]::new));
    }
}
