package system.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import system.model.Type;
import system.service.HibernateSessionFactory;

import java.util.List;

@Repository
public class TypeDao {

    private List<Type> types;

         public List<Type> getTypes(){
         Session session = HibernateSessionFactory.getSessionFactory().openSession();
         session.beginTransaction();
         Query query = session.createQuery("from Type");
         List<Type> types = query.list();
         session.getTransaction().commit();
         session.close();
         return types;
     }
}
