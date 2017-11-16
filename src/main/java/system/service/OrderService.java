package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.OrderDao;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public List getOrders(){
        return orderDao.getOrders();
    }
}
