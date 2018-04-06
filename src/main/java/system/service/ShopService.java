package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import system.dao.*;
import system.model.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {

    private ProductDao productDao;

    private BasketDao basketDao;

    private TypeDao typeDao;

    private UserDao userDao;

    private StockDao stockDao;

    @Autowired
    public void setStockDao(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    @Autowired
    public void setBasketDao(BasketDao basketDao) {
        this.basketDao = basketDao;
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }


    public void addProductInOrder(Product product) {
        Product product1 = productDao.getProduct(product.getId());
        for (int i=0; i<product1.getProductStockLinks().size(); i++){
            if (product1.getProductStockLinks().get(i).getQuantity()!=0){
                product1.getProductStockLinks().get(i).setQuantity(
                        (product1.getProductStockLinks().get(i).getQuantity()-1));
                break;
            }
        }
        productDao.updateProduct(product1);

        User user = userDao.getUser(getUserName());
        Basket basket = getLastUserBasket();
        if (basket==null || basket.getUserConfirmStatus().equals("1")){
            Basket basket1 = new Basket();
            basket1.setUserConfirmStatus("0");
            basket1.setUser(user);
            basket1.setCost(product1.getPrice());
            basketDao.saveBasket(basket1);
            BasketProductLink basketProductLink = new BasketProductLink();
            basketProductLink.setQuantity(1);
            basketProductLink.setProductId(product1.getId());
            basketProductLink.setBasketId(basket1.getId());
            List<BasketProductLink> basketProductLinks = new ArrayList<BasketProductLink>();
            basketProductLinks.add(basketProductLink);
            basket1.setBasketProductLinks(basketProductLinks);
            basketDao.updateBasket(basket1);
        }else{
            List<BasketProductLink> basketProductLinks = basket.getBasketProductLinks();
            boolean bool = true;
            for (BasketProductLink basketProductLink:basketProductLinks){
                if (basketProductLink.getProductId()==product1.getId()){
                    basketProductLink.setQuantity(basketProductLink.getQuantity()+1);
                    bool = false;
                    break;
                }
            }
            if (bool){
                BasketProductLink basketProductLink = new BasketProductLink();
                basketProductLink.setQuantity(1);
                basketProductLink.setProductId(product1.getId());
                basketProductLink.setBasketId(basket.getId());
                basketProductLinks.add(basketProductLink);
            }
            basket.setCost(basket.getCost() + product1.getPrice());
            basketDao.updateBasket(basket);
        }
    }

    public void deleteProductInOrder(Product product) {
        Product product1 = productDao.getProduct(product.getId());
        product1.getProductStockLinks().get(0).setQuantity(
                (product1.getProductStockLinks().get(0).getQuantity()+1));
        productDao.updateProduct(product1);

        Basket basket = getLastUserBasket();
        basket.setCost(basket.getCost() - product1.getPrice());
        List<BasketProductLink> basketProductLinks = basket.getBasketProductLinks();
        for (BasketProductLink basketProductLink:basketProductLinks){
            if (basketProductLink.getProductId()==product1.getId()){
//                if (basketProductLink.getQuantity()==1) basketProductLinks.remove(basketProductLink);
                basketProductLink.setQuantity(basketProductLink.getQuantity()-1);
                break;
            }
        }
        basket.setBasketProductLinks(basketProductLinks);
        basketDao.updateBasket(basket);
    }

    public void confirmBasket() {
        Basket basket = getLastUserBasket();
        basket.setUserConfirmStatus("1");
        basket.setAdminConfirmStatus("0");
        basketDao.updateBasket(basket);
    }

    public List<Product> searchProduct(Product product, int quantity) {
        List<Product> products;
        if (!product.getType().getName().equals("-")) {
            Type type = typeDao.getType(product.getType().getName());
            products = type.getProduct();
        } else {
            products = productDao.getAllProducts();
        }

        if (!product.getName().equals("")) {
            for (int i = 0; i < products.size(); i++) {
                if (!products.get(i).getName().equals(product.getName())) {
                    products.remove(products.get(i));
                    i--;
                }
            }
        }

        if (product.getPrice() != 0) {
            for (int i = 0; i < products.size(); i++) {
                if (product.getPrice() < products.get(i).getPrice()) {
                    products.remove(products.get(i));
                    i--;
                }
            }
        }

        if (quantity != 0) {
            for (int i = 0; i < products.size(); i++) {
                int q = 0;
                for (ProductStockLink productStockLink:products.get(i).getProductStockLinks()){
                    q+=productStockLink.getQuantity();
                }
                if (quantity > q) {
                    products.remove(products.get(i));
                    i--;
                }
            }
        }

        return products;
    }


    public boolean addUser(User user) {
        List<User> users = userDao.getUsers(user.getName());
        if (!user.getName().equals("") && !user.getPassword().equals("") && users.isEmpty()) {
            User user1 = new User();
            user1.setName(user.getName());
            user1.setPassword(user.getPassword());
            user1.setRole("ROLE_USER");
            userDao.saveUser(user1);
            return true;
        }
        return false;
    }

    public String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            return "Гость";
        } else return auth.getName();
    }

    public List<Basket> getUserBaskets() {
        User user = userDao.getUser(getUserName());
        return user.getBasket();
    }

    public Basket getLastUserBasket() {
        User user = userDao.getUser(getUserName());
        List<Basket> baskets = user.getBasket();
        Basket basket = null;
        if (!baskets.isEmpty()) {
            basket = baskets.get(0);
            for (Basket b : baskets) {
                if (b.getId() > basket.getId()) basket = b;
            }
        }
        return basket;
    }


    public void changeProduct(Product product) {
        Product product1 = productDao.getProduct(product.getId());
        product1.setName(product.getName());
        productDao.updateProduct(product1);
    }

    public int numberOfOrdersForConfirm() {
        List<Basket> baskets = basketDao.getAllBaskets();
        int count = 0;
        for (Basket b : baskets) {
            if (b.getAdminConfirmStatus() != null && b.getAdminConfirmStatus().equals("0")) count++;
        }
        return count;
    }

    public void adminConfirm(Basket basket) {
        Basket basket1 = basketDao.getBasket(basket.getId());
        basket1.setAdminConfirmStatus("1");
        basketDao.updateBasket(basket1);

        List<BasketProductLink> basketProductLinks = basket1.getBasketProductLinks();
        for (BasketProductLink basketProductLink:basketProductLinks){
            if (basketProductLink.getQuantity()!=0) {
                Product product = basketProductLink.getProduct();
                product.getProductStockLinks().get(0).setQuantity(
                        product.getProductStockLinks().get(0).getQuantity() + 1);
                productDao.updateProduct(product);
            }
        }
    }

    public void adminConfirm2(Basket basket) {
        Basket basket1 = basketDao.getBasket(basket.getId());
        basket1.setAdminConfirmStatus("2");
        basketDao.updateBasket(basket1);
    }

    public void deleteUser(User user) {
        User user1 = userDao.getUser(user.getName());
        userDao.deleteUser(user1);
    }


    public List getAllProducts() {
        return productDao.getAllProducts();
    }

    public List getAllBaskets() {
        return basketDao.getAllBaskets();
    }

    public List getAllTypes(){
        return typeDao.getAllTypes();
    }

    public List getAllUsers(){
        return  userDao.getAllUsers();
    }
}
