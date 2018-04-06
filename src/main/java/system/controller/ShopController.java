package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.model.*;
import system.service.ShopService;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private ShopService shopService;

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String shopRegistration() {
        return "/user_product.html";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String shopBasket() {
        return "/user_basket.html";
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String history() {
        return "/user_history.html";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String shopSearch() {
        return "/user_search.html";
    }


    @RequestMapping(value = "/getTypes", method = RequestMethod.GET)
    public @ResponseBody List<Type> getTypes(){
        return shopService.getAllTypes();
    }

    @RequestMapping(value = "/getProducts", method = RequestMethod.GET)
    public @ResponseBody List<Product> getProducts(){
        return shopService.getAllProducts();
    }

    @RequestMapping(value = "/getUsername", method = RequestMethod.GET)
    public @ResponseBody String getUsername(){
        return shopService.getUserName();
    }

    @RequestMapping(value = "/getLastUserBasket", method = RequestMethod.GET)
    public @ResponseBody Basket getLastUserBasket(){
        return shopService.getLastUserBasket();
    }

    @RequestMapping(value = "/getUserBaskets", method = RequestMethod.GET)
    public @ResponseBody List<Basket> getUserBaskets(){
        return shopService.getUserBaskets();
    }

    @RequestMapping(value = "/searchProduct", method = RequestMethod.POST)
    public @ResponseBody List<Product> searchProduct(@RequestBody Product product,
                                                     @RequestParam("typename") String typename,
                                                     @RequestParam("quantity") int quantity){
        if (product.getName()==null) product.setName("");
        Type type = new Type(); type.setName(typename); product.setType(type);
        return shopService.searchProduct(product, quantity);
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public @ResponseBody void addProduct(@RequestBody Product product){
        shopService.addProductInOrder(product);
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public @ResponseBody void deleteProduct(@RequestBody Product product){
        shopService.deleteProductInOrder(product);
    }

    @RequestMapping(value = "/confirmBasket", method = RequestMethod.POST)
    public @ResponseBody void confirmBasket(){
        shopService.confirmBasket();
    }
}
