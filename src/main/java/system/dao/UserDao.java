package system.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import system.model.User;
import system.service.HibernateSessionFactory;

import java.util.List;

@Repository
public class UserDao {

    private List<User> users;

    public List<User> getUsers(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User");
        List<User> users = query.list();
        session.getTransaction().commit();
        session.close();
        return users;
    }
}
