package system.dao;

import org.springframework.stereotype.Repository;
import system.model.Basket;

import java.util.List;

@Repository
public class BasketDao extends GenericDao<Basket>{

    public List<Basket> getAllBaskets(){
        return getAll("Basket");
    }

    public Basket getBasket(int id){
        return getElement("from Basket where id=:n", id);
    }

    public void updateBasket(Basket basket){
        update(basket);
    }

    public void saveBasket(Basket basket){
        save(basket);
    }
}
