package academy.kata.service;

import academy.kata.dao.*;
import academy.kata.model.User;
import academy.kata.model.entry.EmailEntry;
import academy.kata.model.entry.PhoneEntry;
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
    public User get(Integer id) {
        return userDao.get(id);
    }

    @Override
    public List<User> get() {
        return userDao.get();
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
    public User deletePhone(Integer userId, Integer phoneId) {
        User user = get(userId);
        user.getPhones().remove(user.getPhones().get(phoneId));
        if (user.getPhones().isEmpty()) {
            user.getPhones().add(new PhoneEntry("", ""));
        }
        update(user);
        return user;
    }

    @Override
    @Transactional
    public User deleteEmail(Integer userId, Integer emailId) {
        User user = get(userId);
        user.getEmails().remove(user.getEmails().get(emailId));
        if (user.getEmails().isEmpty()) {
            user.getEmails().add(new EmailEntry("", ""));
        }
        update(user);
        return user;
    }


    @Override
    @Transactional
    public void generateTestData() {
        Arrays.stream(USERS).forEach(userDao::add);
    }
}
