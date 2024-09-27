/*
 * Copyright  2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package lk.ijse.view.tdm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ItemTm {
    @Id
    private int iId;
    private String iName;
    private double price;
    private double qty;


    public ItemTm() {
    }

    public ItemTm(int iId, String iName, double price, double qty) {
        this.iId = iId;
        this.iName = iName;
        this.price = price;
        this.qty = qty;
    }

    public int getIId() {
        return iId;
    }

    public void setIId(int iId) {
        this.iId = iId;
    }

    public String getIName() {
        return iName;
    }

    public void setIName(String iName) {
        this.iName = iName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "iId=" + iId +
                ", iName='" + iName + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
