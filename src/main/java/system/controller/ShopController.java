package system.controller;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import system.model.OrderEntity;
import system.model.ProductEntity;
import system.service.HibernateSessionFactory;
import system.service.OrderService;
import system.service.ProductService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    private  ArrayList<ProductEntity> searchRes;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView shopProduct(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", productService.getProducts());
        modelAndView.addObject("productFromServer", new ProductEntity());
        modelAndView.setViewName("shop_product_page");
        return modelAndView;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView shopOrder(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", orderService.getOrders());
        modelAndView.addObject("orderFromServer", new OrderEntity());
        modelAndView.setViewName("shop_order_page");
        return modelAndView;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public @ResponseBody ModelAndView addProductInOrder(@ModelAttribute("productFromServer") ProductEntity product) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName(product.getName());
        orderEntity.setPrice(product.getPrice());
        orderEntity.setKind(product.getKind());
        orderEntity.setAvailability(product.getAvailability());
        session.save(orderEntity);
        session.getTransaction().commit();
        session.close();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", productService.getProducts());
        modelAndView.addObject("productFromServer", new ProductEntity());
        modelAndView.setViewName("shop_product_page");
        return modelAndView;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public @ResponseBody ModelAndView deleteProductInOrder(@ModelAttribute("orderFromServer") OrderEntity order) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(order);
        session.getTransaction().commit();
        session.close();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", orderService.getOrders());
        modelAndView.addObject("orderFromServer", new OrderEntity());
        modelAndView.setViewName("shop_order_page");
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView shopSearch(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("searchFromServer", new ProductEntity());
        modelAndView.addObject("res", searchRes);
        modelAndView.setViewName("shop_search_page");
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody ModelAndView searchProduct(@ModelAttribute("searchFromServer") ProductEntity product) {
        String name = product.getName();
        int price = product.getPrice();
        String kind = product.getKind();
        String availability = product.getAvailability();

        String[] str = {name, Integer.toString(price), kind, availability};
        List products = productService.getProducts();
        searchRes = new ArrayList<ProductEntity>();

        if (!str[0].equals("") || !str[1].equals("0") || !str[2].equals("") || !str[3].equals("")) {
            for (int j = 0; j < products.size(); j++) {
                ProductEntity prod = (ProductEntity) products.get(j);
                String[] str1 = {prod.getName(), Integer.toString(prod.getPrice()), prod.getKind(), prod.getAvailability()};
                int k = 0;
                for (int i = 0; i < str.length; i++) {
                    if (str[i].equals("") || str[i].equals("0")) {
                        k++;
                    } else if (str[i].equals(str1[i])) {
                        k++;
                    }
                }
                if (k == str.length) searchRes.add(prod);
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("searchFromServer", new ProductEntity());
        modelAndView.addObject("res", searchRes);
        modelAndView.setViewName("shop_search_page");
        return modelAndView;
    }
}
