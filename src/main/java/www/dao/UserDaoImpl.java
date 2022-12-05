package www.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import www.model.User;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(@Qualifier("entityManagerFactory") @NotNull EntityManagerFactory EntityFactory) {
        this.entityManager = EntityFactory.createEntityManager();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = entityManager.createQuery("from User", User.class).getResultList();
        //TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return allUsers;
    }

    @Override
    public User getUser(int id) {
        TypedQuery<User> query = entityManager.createQuery("from User where id = :user_id", User.class)
                .setParameter("user_id", id);
        return query.getSingleResult();
    }

    @Override
    public void updateUser(int id, User user) {
        User tmp = getUser(id);
        tmp.setSurName(user.getSurName());
        tmp.setDepartment(user.getDepartment());
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
