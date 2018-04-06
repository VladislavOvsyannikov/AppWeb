package system.dao;

import org.springframework.stereotype.Repository;
import system.model.Stock;

import java.util.List;

@Repository
public class StockDao extends GenericDao<Stock> {

    public List<Stock> getAllStocks(){
        return getAll("Stock");
    }

    public void saveStock(Stock stock){
        save(stock);
    }

    public List<Stock> getStocks(String adress){
        return getList("from Stock where address=:n", adress);
    }
}
