package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.model.Product;
import system.model.ProductInOrder;
import system.service.ShopManager;
import system.service.ShopService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopManager shopManager;

//    private List<Product> searchRes;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView shopProduct(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("types", shopService.getTypes());
        modelAndView.addObject("productFromServer", new Product());
        modelAndView.setViewName("shop_product_page");
        return modelAndView;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public  @ResponseBody ModelAndView addProductInOrder(@ModelAttribute("productFromServer") Product product){
        shopManager.addProductInOrder(product);
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("types", shopService.getTypes());
//        modelAndView.addObject("productFromServer", new Product());
//        modelAndView.setViewName("shop_product_page");
        modelAndView.addObject("basket", shopService.getLastBasket());
        modelAndView.addObject("productInOrderFromServer", new ProductInOrder());
        modelAndView.setViewName("shop_basket_page");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", new Product());
        modelAndView.setViewName("shop_add_page");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ModelAndView addProduct(@ModelAttribute("product") Product product){
        shopManager.addProduct(product);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", new Product());
        modelAndView.setViewName("shop_add_page");
        return modelAndView;
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public ModelAndView shopOrder(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("basket", shopService.getLastBasket());
        modelAndView.addObject("productInOrderFromServer", new ProductInOrder());
        modelAndView.setViewName("shop_basket_page");
        return modelAndView;
    }

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public @ResponseBody ModelAndView deleteProductInOrder(@ModelAttribute("productInOrderFromServer") ProductInOrder productInOrder){
        shopManager.deleteProductInOrder(productInOrder);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("basket", shopService.getLastBasket());
        modelAndView.addObject("productInOrderFromServer", new ProductInOrder());
        modelAndView.setViewName("shop_basket_page");
        return modelAndView;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ModelAndView history(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("baskets", shopService.getBaskets());
        modelAndView.setViewName("shop_history_page");
        return modelAndView;
    }

    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public @ResponseBody ModelAndView confirmBasket(){
        shopManager.confirmBasket();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("baskets", shopService.getBaskets());
        modelAndView.setViewName("shop_history_page");
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView shopSearch(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("searchFromServer", new Product());
        modelAndView.addObject("productFromServer", new Product());
        modelAndView.addObject("types", shopService.getTypes());
        modelAndView.addObject("res", new ArrayList<Product>());
        modelAndView.setViewName("shop_search_page");
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody ModelAndView searchProduct(@ModelAttribute("searchFromServer") Product product) {
        List<Product> searchRes = shopManager.searchProduct(product);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("searchFromServer", new Product());
        modelAndView.addObject("productFromServer", new Product());
        modelAndView.addObject("types", shopService.getTypes());
        modelAndView.addObject("res", searchRes);
        modelAndView.setViewName("shop_search_page");
        return modelAndView;
    }
}
