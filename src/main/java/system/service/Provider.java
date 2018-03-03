package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import system.model.User;

import java.util.ArrayList;
import java.util.List;

@Service("provider")
public class Provider implements AuthenticationProvider{

    private ShopService shopService;

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }


    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = null;
        for (Object user1 : shopService.getAll("User")){
            if (((User) user1).getName().equals(login) && ((User) user1).getPassword().equals(password)){
                user = (User) user1;
            }
        }
        if (user!=null){
            List<GrantedAuthority> grantedAuth = new ArrayList<GrantedAuthority>();
            grantedAuth.add(new SimpleGrantedAuthority(user.getRole()));
            if (user.getRole().equals("ROLE_ADMIN")) grantedAuth.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(login, password, grantedAuth);
        }else {
            return null;
        }
    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
