package system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(BasketProductLinkId.class)
@Table(name = "basket_product_link", schema = "shop2")
public class BasketProductLink implements Serializable{

    @Id
    private int basketId;

    @Id
    private int productId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "basketId", updatable = false, insertable = false, referencedColumnName = "id")
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "productId", updatable = false, insertable = false, referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
