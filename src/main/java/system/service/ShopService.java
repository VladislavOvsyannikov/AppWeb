package system.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import system.dao.*;
import system.model.*;

import java.util.List;

@Service
public class ShopService {

    @Autowired
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

    //    @Secured("ROLE_USER")
    public void addProductInOrder(Product product){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query;

        query = session.createQuery("from Product where id=:n2");
        query.setParameter("n2", product.getId());
        Product product1 = (Product) query.list().get(0);
        product1.setQuantity(product1.getQuantity()-1);

        User user;
        String userName = getUserName();
        query = session.createQuery("from User where name=:n");
        query.setParameter("n", userName);
        user = (User) query.list().get(0);

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
                session.save(productInOrder);
            } else {
                Basket basket1 = new Basket();
                basket1.setStatus("0");
                basket1.setUser(user);
                basket1.setCost(product1.getPrice());
                session.save(basket1);
                productInOrder.setBasket(basket1);
                productInOrder.setProduct(product1);
                session.save(productInOrder);
            }
        }
        if (baskets.isEmpty()) {
            Basket basket1 = new Basket();
            basket1.setStatus("0");
            basket1.setUser(user);
            basket1.setCost(product1.getPrice());
            session.save(basket1);
            productInOrder.setBasket(basket1);
            productInOrder.setProduct(product1);
            session.save(productInOrder);
        }
        session.getTransaction().commit();
        session.close();
    }

    //    @Secured("ROLE_USER")
    public void deleteProductInOrder(ProductInOrder productInOrder){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query;
        query = session.createQuery("from ProductInOrder where id=:n");
        query.setParameter("n", productInOrder.getId());
        ProductInOrder productInOrder1 = (ProductInOrder) query.list().get(0);
        productInOrder1.getProduct().setQuantity(productInOrder1.getProduct().getQuantity()+1);
        productInOrder1.getBasket().setCost(productInOrder1.getBasket().getCost() -
                productInOrder1.getProduct().getPrice());

        Basket basket = productInOrder1.getBasket();
        basket.getProductInOrder().remove(productInOrder1);

        session.delete(productInOrder1);
        session.getTransaction().commit();
        session.close();
    }

    //    @Secured("ROLE_USER")
    public void confirmBasket(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query;
        Basket basket = getLastUserBasket();

        query = session.createQuery("update Basket set status=:n1 where id=:n2");
        query.setParameter("n1", "1");
        query.setParameter("n2", basket.getId());
        query.executeUpdate();

        query = session.createQuery("update Basket set status2=:n3 where id=:n4");
        query.setParameter("n3", "0");
        query.setParameter("n4", basket.getId());
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    public List<Product> searchProduct(Product product){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query;

        List<Product> products;
        if (!product.getType().getName().equals("-")){
            query = session.createQuery("from Type where name=:n");
            query.setParameter("n", product.getType().getName());
            Type type = (Type) query.list().get(0);
            products = type.getProduct();
        }else{
            products = getProducts();
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

        session.getTransaction().commit();
        session.close();
        return products;
    }


    public boolean addUser(User user){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where name=:n2");
        query.setParameter("n2", user.getName());
        List<User> users = query.list();
        session.getTransaction().commit();
        session.close();

        if (!user.getName().equals("") && !user.getPassword().equals("") && users.isEmpty()) {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();

            User user1 = new User();
            user1.setName(user.getName());
            user1.setPassword(user.getPassword());
            user1.setRole("ROLE_USER");
            session.save(user1);

            session.getTransaction().commit();
            session.close();
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
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query;

        User user;
        String userName = getUserName();
        query = session.createQuery("from User where name=:n");
        query.setParameter("n", userName);
        user = (User) query.list().get(0);

        return user.getBasket();
    }

    public Basket getLastUserBasket(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query;

        User user;
        String userName = getUserName();
        query = session.createQuery("from User where name=:n");
        query.setParameter("n", userName);
        user = (User) query.list().get(0);

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
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query;

        Stock stock;
        query = session.createQuery("from Stock where address=:addr");
        query.setParameter("addr", product.getStock().getAddress());
        List<Stock> stocks = query.list();
        if (stocks.isEmpty()) {
            stock = new Stock();
            stock.setAddress(product.getStock().getAddress());
            session.save(stock);
        } else {
            stock = stocks.get(0);
        }

        Type type;
        query = session.createQuery("from Type where name=:name");
        query.setParameter("name", product.getType().getName());
        List<Type> types = query.list();
        if (types.isEmpty()) {
            type = new Type();
            type.setName(product.getType().getName());
            session.save(type);
        } else {
            type = types.get(0);
        }

        Product product1 = new Product();
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setQuantity(product.getQuantity());
        product1.setStock(stock);
        product1.setType(type);
        session.save(product1);

        session.getTransaction().commit();
        session.close();
    }

    public void changeProduct(Product product){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query;

        query = session.createQuery("from Product where id=:n");
        query.setParameter("n", product.getId());
        Product product1 = (Product) query.list().get(0);
        product1.setQuantity(product.getQuantity());

        session.getTransaction().commit();
        session.close();
    }

    public int numberOfOrdersForConfirm(){
        List<Basket> baskets = getBaskets();
        int count=0;
        for (Basket b:baskets){
            if (b.getStatus2()!=null && b.getStatus2().equals("0")) count++;
        }
        return count;
    }

    public void adminConfirm(Basket basket){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query;

        query = session.createQuery("update Basket set status2=:n1 where id=:n2");
        query.setParameter("n1", "1");
        query.setParameter("n2", basket.getId());
        query.executeUpdate();

        query = session.createQuery("from Basket where id=:n");
        query.setParameter("n", basket.getId());
        Basket basket1 = (Basket) query.list().get(0);
        for (ProductInOrder p:basket1.getProductInOrder()){
            p.getProduct().setQuantity(p.getProduct().getQuantity()+1);
        }

        session.getTransaction().commit();
        session.close();
    }

    public void adminConfirm2(Basket basket){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query;

        query = session.createQuery("update Basket set status2=:n1 where id=:n2");
        query.setParameter("n1", "2");
        query.setParameter("n2", basket.getId());
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    public void deleteUser(User user){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query;

        query = session.createQuery("from User where id=:n");
        query.setParameter("n", user.getId());
        User user1 = (User) query.list().get(0);
        session.delete(user1);

        session.getTransaction().commit();
        session.close();
    }


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

    public List getProductsInOrders(){
        return productInOrderDao.getProductsInOrders();
    }

    public List getUsers(){
        return userDao.getUsers();
    }
}
