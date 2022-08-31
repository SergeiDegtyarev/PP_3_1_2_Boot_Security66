package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void addUser(User user) {
        entityManager.persist(user);
    }

    public User findById(Integer id) {
        return entityManager.find(User.class, id);
    }
    public void updateUser(User user) {
        entityManager.merge(user);
    }


    public void deleteUser(Integer id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User findUserName(String name) {
        TypedQuery<User> q = (entityManager.createQuery("select u from User u " +
                "join fetch u.roles where u.name = :name",User.class));
        q.setParameter("name",name);
        return q.getResultList().stream().findFirst().orElse(null);
    }


    public List<User> getAllUsers() {
        return  entityManager.createQuery("from User",User.class).getResultList();
    }

}