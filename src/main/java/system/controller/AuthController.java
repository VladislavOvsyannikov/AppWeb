package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public String shopLogin() {
            return "/login.html";
    }

    @RequestMapping(value = "/error403", method = RequestMethod.GET)
    public String shop403() {
        return "/error403.html";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String shopRegistration() {
        return "/registration.html";
    }


    @RequestMapping(value = "/submitRegistration", method = RequestMethod.POST)
    public @ResponseBody boolean shopRegistration(@RequestBody User user){
        return shopService.addUser(user);
    }
}
