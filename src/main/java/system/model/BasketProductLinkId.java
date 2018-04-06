package system.model;

import java.io.Serializable;

public class BasketProductLinkId implements Serializable{

    private int basketId;
    private int productId;

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

    public int hashCode() {
        return (productId + basketId);
    }

    public boolean equals(Object object) {
        if (object instanceof BasketProductLinkId) {
            BasketProductLinkId otherId = (BasketProductLinkId) object;
            return (otherId.productId == this.productId) && (otherId.basketId == this.basketId);
        }
        return false;
    }

}
