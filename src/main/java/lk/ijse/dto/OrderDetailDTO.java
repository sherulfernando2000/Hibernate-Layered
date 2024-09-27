/*
 * Copyright  2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package lk.ijse.dto;

public class OrderDetailDTO {
    private String oId;
    private int iId;
    private double qty;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String oId, int iId, double qty) {
        this.oId = oId;
        this.iId = iId;
        this.qty = qty;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "oId=" + oId +
                ", iId=" + iId +
                ", qty=" + qty +
                '}';
    }
}
