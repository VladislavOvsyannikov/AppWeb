package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import system.model.User;
import system.service.ShopService;

@Controller
public class AuthController {

    private ShopService shopService;

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView shopLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/error403", method = RequestMethod.GET)
    public ModelAndView shopProduct403(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error403");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView shopRegistration(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public @ResponseBody ModelAndView shopRegistration(@ModelAttribute("user") User user){
        boolean reg = shopService.addUser(user);
        ModelAndView modelAndView = new ModelAndView();
        if (reg) modelAndView.setViewName("login");
        else modelAndView.setViewName("errorReg");
        return modelAndView;
    }

}
