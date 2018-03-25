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

    @Column(name = "status")
    private String status;

    @Column(name = "status2")
    private String status2;

    @Column(name = "cost", nullable = false)
    private int cost;

    @OneToMany(mappedBy = "basket", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProductInOrder> productInOrder;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<ProductInOrder> getProductInOrder() {
        return productInOrder;
    }

    public void setProductInOrder(List<ProductInOrder> productInOrder) {
        this.productInOrder = productInOrder;
    }
}
