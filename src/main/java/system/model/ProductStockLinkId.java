package system.model;

import java.io.Serializable;

public class ProductStockLinkId implements Serializable {

    private int productId;
    private int stockId;

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

    public int hashCode() {
        return (productId + stockId);
    }

    public boolean equals(Object object) {
        if (object instanceof ProductStockLinkId) {
            ProductStockLinkId otherId = (ProductStockLinkId) object;
            return (otherId.productId == this.productId) && (otherId.stockId == this.stockId);
        }
        return false;
    }
}
