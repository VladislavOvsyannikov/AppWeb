package system.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import system.model.ProductEntity;
import system.service.HibernateSessionFactory;

import java.util.List;

@Repository
public class ProductDao {

     private List<ProductEntity> products;

     public List<ProductEntity> getProducts(){
         Session session = HibernateSessionFactory.getSessionFactory().openSession();
         try {
             session.beginTransaction().begin();
             Criteria criteria = session.createCriteria(ProductEntity.class);
             products = (List<ProductEntity>) criteria.list();
             session.getTransaction().commit();
         } catch (Exception e) {
             e.printStackTrace();
         }
         if (session != null) session.close();
         return products;
     }
}
