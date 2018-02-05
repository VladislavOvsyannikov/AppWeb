package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import system.model.Basket;
import system.model.Product;
import system.service.ShopManager;
import system.service.ShopService;

@Controller
@RequestMapping("/shop")
public class AdminController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopManager shopManager;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", new Product());
        modelAndView.setViewName("admin_add");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public @ResponseBody ModelAndView addProduct(@ModelAttribute("product") Product product){
        shopManager.addProduct(product);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", new Product());
        modelAndView.setViewName("admin_add");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/confirm", method = RequestMethod.GET)
    public ModelAndView confirm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("baskets", shopService.getBaskets());
        modelAndView.addObject("basket", new Basket());
        modelAndView.setViewName("admin_confirm");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/confirm", method = RequestMethod.POST)
    public @ResponseBody ModelAndView confirmBasket(@ModelAttribute("basket") Basket basket){
        shopManager.adminConfirm(basket);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("baskets", shopService.getBaskets());
        modelAndView.addObject("basket", new Basket());
        modelAndView.setViewName("admin_confirm");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/confirm2", method = RequestMethod.POST)
    public @ResponseBody ModelAndView confirmBasket2(@ModelAttribute("basket") Basket basket){
        shopManager.adminConfirm2(basket);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("baskets", shopService.getBaskets());
        modelAndView.addObject("basket", new Basket());
        modelAndView.setViewName("admin_confirm");
        return modelAndView;
    }


//    @RequestMapping(value = "/generate", method = RequestMethod.POST)
//    public ModelAndView generateProduct(){
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("product", new Product());
//        modelAndView.setViewName("shop_add_page");
//        return modelAndView;
//    }
}