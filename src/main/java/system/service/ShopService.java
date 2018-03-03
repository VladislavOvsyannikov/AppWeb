package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import system.dao.*;
import system.model.*;

import java.util.List;

@Service
public class ShopService {

    private GenericDao genericDao;

    @Autowired
    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    //    @Secured("ROLE_USER")
    public void addProductInOrder(Product product){
        Product product1 = (Product) genericDao.getElement("from Product where id=:n", product.getId());
        product1.setQuantity(product1.getQuantity()-1);
        genericDao.update(product1);

        User user = (User) genericDao.getElement("from User where name=:n", getUserName());
        List<Basket> baskets = user.getBasket();
        ProductInOrder productInOrder = new ProductInOrder();
        if (!baskets.isEmpty()) {
            Basket basket = baskets.get(0);
            for (Basket b : baskets) {
                if (b.getId() > basket.getId()) basket = b;
            }
            if (basket.getStatus().equals("0")) {
                productInOrder.setBasket(basket);
                productInOrder.setProduct(product1);
                basket.setCost(basket.getCost() + product1.getPrice());
                genericDao.update(basket);
                genericDao.save(productInOrder);
            } else {
                Basket basket1 = new Basket();
                basket1.setStatus("0");
                basket1.setUser(user);
                basket1.setCost(product1.getPrice());
                genericDao.save(basket1);
                productInOrder.setBasket(basket1);
                productInOrder.setProduct(product1);
                genericDao.save(productInOrder);
            }
        }
        if (baskets.isEmpty()) {
            Basket basket2 = new Basket();
            basket2.setStatus("0");
            basket2.setUser(user);
            basket2.setCost(product1.getPrice());
            genericDao.save(basket2);
            productInOrder.setBasket(basket2);
            productInOrder.setProduct(product1);
            genericDao.save(productInOrder);
        }
    }

    //    @Secured("ROLE_USER")
    public void deleteProductInOrder(ProductInOrder productInOrder){
        ProductInOrder productInOrder1 = (ProductInOrder) genericDao.
                getElement("from ProductInOrder where id=:n", productInOrder.getId());

        Product product = productInOrder1.getProduct();
        product.setQuantity(product.getQuantity()+1);
        genericDao.update(product);

        Basket basket = productInOrder1.getBasket();
        basket.setCost(basket.getCost() - product.getPrice());
        basket.getProductInOrder().remove(productInOrder1);
        genericDao.update(basket);
        genericDao.delete(productInOrder1);
    }

    //    @Secured("ROLE_USER")
    public void confirmBasket(){
        Basket basket = getLastUserBasket();
        basket.setStatus("1");
        basket.setStatus2("0");
        genericDao.update(basket);
    }

    public List<Product> searchProduct(Product product){
        List<Product> products;
        if (!product.getType().getName().equals("-")){
            Type type = (Type) genericDao.getElement("from Type where name=:n", product.getType().getName());
            products = type.getProduct();
        }else{
            products = getAll("Product");
        }

        if (!product.getName().equals("")){
            for (int i=0; i<products.size(); i++){
                if (!products.get(i).getName().equals(product.getName())){
                    products.remove(products.get(i));
                    i--;
                }
            }
        }

        if (product.getPrice()!=0) {
            for (int i=0; i<products.size(); i++){
                if (product.getPrice() < products.get(i).getPrice()) {
                    products.remove(products.get(i));
                    i--;
                }
            }
        }

        if (product.getQuantity()!=0) {
            for (int i=0; i<products.size(); i++){
                if (product.getQuantity() > products.get(i).getQuantity()) {
                    products.remove(products.get(i));
                    i--;
                }
            }
        }

        return products;
    }


    public boolean addUser(User user){
        List<User> users = genericDao.getList("from User where name=:n", user.getName());
        if (!user.getName().equals("") && !user.getPassword().equals("") && users.isEmpty()) {
            User user1 = new User();
            user1.setName(user.getName());
            user1.setPassword(user.getPassword());
            user1.setRole("ROLE_USER");
            genericDao.save(user1);
            return true;
        }
        return false;
    }

    public String getUserName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")){
            return "Гость";
        }else return auth.getName();
    }

    public List<Basket> getUserBaskets(){
        User user = (User) genericDao.getElement("from User where name=:n" , getUserName());
        return user.getBasket();
    }

    public Basket getLastUserBasket(){
        User user = (User) genericDao.getElement("from User where name=:n", getUserName());
        List<Basket> baskets = user.getBasket();
        Basket basket=null;
        if (!baskets.isEmpty()) {
            basket = baskets.get(0);
            for (Basket b : baskets) {
                if (b.getId() > basket.getId()) basket = b;
            }
        }
        return basket;
    }


    public void addProduct(Product product) {
        Stock stock;
        List<Stock> stocks = genericDao.getList("from Stock where address=:n", product.getStock().getAddress());
        if (stocks.isEmpty()) {
            stock = new Stock();
            stock.setAddress(product.getStock().getAddress());
            genericDao.save(stock);
        } else {
            stock = stocks.get(0);
        }

        Type type;
        List<Type> types = genericDao.getList("from Type where name=:n", product.getType().getName());
        if (types.isEmpty()) {
            type = new Type();
            type.setName(product.getType().getName());
            genericDao.save(type);
        } else {
            type = types.get(0);
        }

        Product product1 = new Product();
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setQuantity(product.getQuantity());
        product1.setStock(stock);
        product1.setType(type);
        genericDao.save(product1);
    }

    public void changeProduct(Product product){
        Product product1 = (Product) genericDao.getElement("from Product where id=:n", product.getId());
        product1.setQuantity(product.getQuantity());
        genericDao.update(product1);
    }

    public int numberOfOrdersForConfirm(){
        List<Basket> baskets = getAll("Basket");
        int count=0;
        for (Basket b:baskets){
            if (b.getStatus2()!=null && b.getStatus2().equals("0")) count++;
        }
        return count;
    }

    public void adminConfirm(Basket basket){
        Basket basket1 = (Basket) genericDao.getElement("from Basket where id=:n", basket.getId());
        basket1.setStatus2("1");
        genericDao.update(basket1);

        for (ProductInOrder p : basket1.getProductInOrder()){
            Product product = p.getProduct();
            product.setQuantity(product.getQuantity()+1);
            genericDao.update(product);
        }
    }

    public void adminConfirm2(Basket basket){
        Basket basket1 = (Basket) genericDao.getElement("from Basket where id=:n", basket.getId());
        basket1.setStatus2("2");
        genericDao.update(basket1);
    }

    public void deleteUser(User user){
        User user1 = (User) genericDao.getElement("from User where id=:n", user.getId());
        genericDao.delete(user1);
    }


    public List getAll(String s){
        return genericDao.getAll(s);
    }
}
