package system.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stock", schema = "shop2")
public class Stock {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "stock")
    private List<Product> product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
