package lk.ijse.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderItemId implements Serializable {
    private int orderId;
    private int itemId;

    public OrderItemId() {
    }

    public OrderItemId(int orderId, int itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    // Getters, Setters, equals() and hashCode() methods

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}