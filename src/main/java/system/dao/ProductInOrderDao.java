package system.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import system.model.ProductInOrder;
import system.service.HibernateSessionFactory;

import java.util.List;

@Repository
public class ProductInOrderDao {

    private List<ProductInOrder> productsInOrders;

    public List<ProductInOrder> getProductsInOrders(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ProductInOrder");
        List<ProductInOrder> productsInOrders = query.list();
        session.getTransaction().commit();
        session.close();
        return productsInOrders;
    }
}
