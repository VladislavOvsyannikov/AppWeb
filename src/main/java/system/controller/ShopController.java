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

//    @RequestMapping(value = "/product", method = RequestMethod.GET)
//    public ModelAndView shopProduct(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("types", shopService.getAll("Type"));
//        modelAndView.addObject("productFromServer", new Product());
//        modelAndView.addObject("userName", shopService.getUserName());
//        modelAndView.setViewName("shop_product_page.jsp");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/product2", method = RequestMethod.POST)
//    public  @ResponseBody ModelAndView addProductInOrder(@ModelAttribute("productFromServer") Product product){
//        shopService.addProductInOrder(product);
//        ModelAndView modelAndView = new ModelAndView();
////        modelAndView.addObject("types", shopService.getTypes());
////        modelAndView.addObject("productFromServer", new Product());
////        modelAndView.setViewName("shop_product_page");
//        modelAndView.addObject("basket", shopService.getLastUserBasket());
//        modelAndView.addObject("productInOrderFromServer", new ProductInOrder());
//        modelAndView.addObject("userName", shopService.getUserName());
//        modelAndView.setViewName("shop_basket_page");
//        return modelAndView;
//    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String shopRegistration() {
        return "/user_product.html";
    }

//    @RequestMapping(value = "/basket", method = RequestMethod.GET)
//    public ModelAndView shopOrder(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("basket", shopService.getLastUserBasket());
//        modelAndView.addObject("productInOrderFromServer", new ProductInOrder());
//        modelAndView.addObject("userName", shopService.getUserName());
//        modelAndView.setViewName("shop_basket_page.jsp");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/basket", method = RequestMethod.POST)
//    public @ResponseBody ModelAndView deleteProductInOrder(@ModelAttribute("productInOrderFromServer") ProductInOrder productInOrder){
//        shopService.deleteProductInOrder(productInOrder);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("basket", shopService.getLastUserBasket());
//        modelAndView.addObject("productInOrderFromServer", new ProductInOrder());
//        modelAndView.addObject("userName", shopService.getUserName());
//        modelAndView.setViewName("shop_basket_page");
//        return modelAndView;
//    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String shopBasket() {
        return "/user_basket.html";
    }

//    @RequestMapping(value = "/history", method = RequestMethod.GET)
//    public ModelAndView history(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("baskets", shopService.getUserBaskets());
//        modelAndView.addObject("userName", shopService.getUserName());
//        modelAndView.setViewName("shop_history_page.jsp");
//        return modelAndView;
//    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String history() {
        return "/user_history.html";
    }

//    @RequestMapping(value = "/history", method = RequestMethod.POST)
//    public @ResponseBody ModelAndView confirmBasket(){
//        shopService.confirmBasket();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("baskets", shopService.getUserBaskets());
//        modelAndView.addObject("userName", shopService.getUserName());
//        modelAndView.setViewName("shop_history_page");
//        return modelAndView;
//    }

//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public ModelAndView shopSearch(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("searchFromServer", new Product());
//        modelAndView.addObject("productFromServer", new Product());
//        modelAndView.addObject("types", shopService.getAll("Type"));
//        modelAndView.addObject("res", new ArrayList<Product>());
//        modelAndView.addObject("userName", shopService.getUserName());
//        modelAndView.setViewName("shop_search_page");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/search", method = RequestMethod.POST)
//    public @ResponseBody ModelAndView searchProduct(@ModelAttribute("searchFromServer") Product product) {
//        List<Product> searchRes = shopService.searchProduct(product);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("searchFromServer", new Product());
//        modelAndView.addObject("productFromServer", new Product());
//        modelAndView.addObject("types", shopService.getAll("Type"));
//        modelAndView.addObject("res", searchRes);
//        modelAndView.addObject("userName", shopService.getUserName());
//        modelAndView.setViewName("shop_search_page");
//        return modelAndView;
//    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String shopSearch() {
        return "/user_search.html";
    }


    @RequestMapping(value = "/getTypes", method = RequestMethod.GET)
    public @ResponseBody List<Type> getTypes(){
        return shopService.getAll("Type");
    }

    @RequestMapping(value = "/getProducts", method = RequestMethod.GET)
    public @ResponseBody List<Product> getProducts(){
        return shopService.getAll("Product");
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
                                                     @RequestParam("typename") String typename){
        if (product.getName()==null) product.setName("");
        Type type = new Type(); type.setName(typename); product.setType(type);
        return shopService.searchProduct(product);
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public @ResponseBody void addProduct(@RequestBody Product product){
        shopService.addProductInOrder(product);
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public @ResponseBody void deleteProduct(@RequestBody ProductInOrder productInOrder){
        shopService.deleteProductInOrder(productInOrder);
    }

    @RequestMapping(value = "/confirmBasket", method = RequestMethod.POST)
    public @ResponseBody void confirmBasket(){
        shopService.confirmBasket();
    }
}
