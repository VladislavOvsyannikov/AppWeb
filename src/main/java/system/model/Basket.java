package system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "basket", schema = "shop2")
public class Basket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "userConfirmStatus")
    private String userConfirmStatus;

    @Column(name = "adminConfirmStatus")
    private String adminConfirmStatus;

    @Column(name = "cost", nullable = false)
    private int cost;

    @OneToMany(mappedBy = "basket", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BasketProductLink> basketProductLinks;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserConfirmStatus() {
        return userConfirmStatus;
    }

    public void setUserConfirmStatus(String status) {
        this.userConfirmStatus = status;
    }

    public String getAdminConfirmStatus() {
        return adminConfirmStatus;
    }

    public void setAdminConfirmStatus(String status2) {
        this.adminConfirmStatus = status2;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<BasketProductLink> getBasketProductLinks() {
        return basketProductLinks;
    }

    public void setBasketProductLinks(List<BasketProductLink> basketProductLinks) {
        this.basketProductLinks = basketProductLinks;
    }
}
