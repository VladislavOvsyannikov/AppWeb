package system.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import system.model.Basket;
import system.service.HibernateSessionFactory;

import java.util.List;

@Repository
public class BasketDao {

    private List<Basket> baskets;

    private Basket lastBasket;

    public List<Basket> getBaskets(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Basket");
        List<Basket> baskets = query.list();
        session.getTransaction().commit();
        session.close();
        return baskets;
    }

    public Basket getLastBasket(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query;
        query = session.createQuery("select max(b.id) from Basket b");
        List<Integer> id = query.list();
        int maxId = id.get(0);
        query = session.createQuery("from Basket where id=:n1");
        query.setParameter("n1", maxId);
        lastBasket = (Basket) query.list().get(0);
        session.getTransaction().commit();
        session.close();
        return lastBasket;
    }
}
