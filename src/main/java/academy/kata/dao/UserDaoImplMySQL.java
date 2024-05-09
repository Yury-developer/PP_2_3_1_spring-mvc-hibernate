package academy.kata.dao;

import academy.kata.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDaoImplMySQL implements UserDao, TestData {

    private EntityManager entityManager;


    @PersistenceContext
    public void setEntityManager (EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void add(User user) {
//        entityManager.merge(user);
        user.setId(null);
        entityManager.persist(user);
    }


    @Override
    public User get(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> get(Integer start_id, Integer count) {
        String queryStr = "SELECT u FROM User u WHERE u.id >= :startId ORDER BY u.id ASC";
        TypedQuery<User> query = entityManager.createQuery(queryStr, User.class);
        query.setParameter("startId", start_id);
        query.setMaxResults(count);
        return query.getResultList();
    }

    @Override
    public List<User> get() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
//        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }


    @Override
    public void update(User user) {
        entityManager.merge(user);
    }


    @Override
    public void delete(Integer id) {
        Query query = entityManager.createQuery("DELETE FROM User u WHERE u.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
