package academy.kata.dao;

import academy.kata.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao, TestData {

    private EntityManager entityManager;


    @PersistenceContext
    public void setEntityManager (EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    public void add(User user) {
        entityManager.persist(user);
    }


    @Override
    public User get(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> get() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }


    @Override
    public void update(User user) {
        entityManager.merge(user);
    }


    @Override
    public void delete(Integer id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
