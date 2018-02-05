package system.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "basket", schema = "shop2")
public class Basket {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "status")
    private String status;

    @Column(name = "status2", nullable = true)
    private String status2;

    @OneToMany(mappedBy = "basket", fetch = FetchType.EAGER)
    private List<ProductInOrder> productInOrder;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
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

    public List<ProductInOrder> getProductInOrder() {
        return productInOrder;
    }

    public void setProductInOrder(List<ProductInOrder> productInOrder) {
        this.productInOrder = productInOrder;
    }
}
