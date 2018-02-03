package system.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import system.model.Product;
import system.service.HibernateSessionFactory;

import java.util.List;

@Repository
public class ProductDao {

     private List<Product> products;

     public List<Product> getProducts(){
         Session session = HibernateSessionFactory.getSessionFactory().openSession();
         session.beginTransaction();
         Query query = session.createQuery("from Product");
         List<Product> products = query.list();
         session.getTransaction().commit();
         session.close();
         return products;
     }
}
