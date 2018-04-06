package system.dao;

import org.springframework.stereotype.Repository;
import system.model.User;

import java.util.List;

@Repository
public class UserDao extends GenericDao<User> {

    public List<User> getAllUsers(){
        return getAll("User");
    }

    public User getUser(String name){
        return getElement("from User where name=:n", name);
    }

    public void saveUser(User user){
        save(user);
    }

    public List<User> getUsers(String name){
        return getList("from User where name=:n", name);
    }

    public void deleteUser(User user){
        delete(user);
    }
}
