package system.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import system.model.OrderEntity;
import system.service.HibernateSessionFactory;

import java.util.List;

@Repository
public class OrderDao {

    private List<OrderEntity> orders;

    public List<OrderEntity> getOrders(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            session.beginTransaction().begin();
            Criteria criteria = session.createCriteria(OrderEntity.class);
            orders = (List<OrderEntity>) criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (session != null) session.close();
        return orders;
    }
}
