package system.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import system.model.Stock;
import system.model.Type;
import system.service.HibernateSessionFactory;

import java.util.List;

@Repository
public class StockDao {

    private List<Stock> stocks;

    public List<Stock> getStocks() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Stock");
        List<Stock> stocks = query.list();
        session.getTransaction().commit();
        session.close();
        return stocks;
    }
}
