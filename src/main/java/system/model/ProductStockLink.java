package system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(ProductStockLinkId.class)
@Table(name = "product_stock_link", schema = "shop2")
public class ProductStockLink implements Serializable {

    @Id
    private int productId;

    @Id
    private int stockId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "productId", updatable = false, insertable = false, referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "stockId", updatable = false, insertable = false, referencedColumnName = "id")
    private Stock stock;

    @Column(name = "quantity")
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
