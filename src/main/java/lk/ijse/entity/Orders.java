/*
 * Copyright  2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package lk.ijse.entity;

import jakarta.persistence.*;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {
    @Id
      private String oId;
      private LocalDate date;

      @ManyToOne
      private Customer customer;

      @OneToMany(mappedBy = "order")
      List<OrderDetails> ordersList = new ArrayList<>();

    public Orders() {
    }

    public Orders(String oId, LocalDate date, Customer customer) {
        this.oId = oId;
        this.date = date;
        this.customer = customer;
    }


    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oId=" + oId +
                ", date=" + date +
                ", customer=" + customer +
                '}';
    }
}
