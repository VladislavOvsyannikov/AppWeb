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

    @OneToMany(mappedBy = "basket", fetch = FetchType.EAGER)
    private List<ProductInOrder> productInOrder;

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

    public List<ProductInOrder> getProductInOrder() {
        return productInOrder;
    }

    public void setProductInOrder(List<ProductInOrder> productInOrder) {
        this.productInOrder = productInOrder;
    }
}
