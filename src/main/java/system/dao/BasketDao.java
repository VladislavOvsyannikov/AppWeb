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

    public List<Basket> getBaskets(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Basket");
        List<Basket> baskets = query.list();
        session.getTransaction().commit();
        session.close();
        return baskets;
    }
}
