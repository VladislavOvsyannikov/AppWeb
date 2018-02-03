package system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.*;
import system.model.Basket;

import java.util.List;

@Service
public class ShopService {

    @Autowired              //чтобы спринг сам вытянул ProductDao
    private ProductDao productDao;

    @Autowired
    private StockDao stockDao;

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private BasketDao basketDao;

    @Autowired
    private ProductInOrderDao productInOrderDao;

    @Autowired
    private UserDao userDao;


    public List getProducts(){
        return productDao.getProducts();
    }

    public List getStocks(){
        return stockDao.getStocks();
    }

    public List getTypes(){
        return typeDao.getTypes();
    }

    public List getBaskets(){
        return basketDao.getBaskets();
    }

    public Basket getLastBasket(){
        return basketDao.getLastBasket();
    }

    public List getProductsInOrders(){
        return productInOrderDao.getProductsInOrders();
    }

    public List getUsers(){
        return userDao.getUsers();
    }
}
