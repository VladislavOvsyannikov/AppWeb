package system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.ProductDao;

import java.util.List;

@Service
public class ProductService {

    @Autowired              //чтобы спринг сам вытянул ProductDao
    private ProductDao productDao;

    public List getProducts(){
        return productDao.getProducts();
    }
}
