package system.dao;

import org.springframework.stereotype.Repository;
import system.model.Product;

import java.util.List;

@Repository
public class ProductDao extends GenericDao<Product> {

    public List<Product> getAllProducts(){
        return getAll("Product");
    }

    public Product getProduct(int id){
        return getElement("from Product where id=:n", id);
    }

    public void updateProduct(Product product){
        update(product);
    }

    public void saveProduct(Product product){
        save(product);
    }

}
