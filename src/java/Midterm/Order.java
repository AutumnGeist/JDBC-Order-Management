/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Midterm;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author marilyn
 */
public class Order {
    private String orderNum;
    private String paymentType;
    private String accountNum;
    private Double orderTotal;
    private ArrayList<OrderItems> orderItems;

    public Order(String orderNum, String paymentType, String accountNum, Double orderTotal, ArrayList<OrderItems> orderItems) {
        this.orderNum = orderNum;
        this.paymentType = paymentType;
        this.accountNum = accountNum;
        this.orderTotal = orderTotal;
        this.orderItems = orderItems;
    }

    public Order() {
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public ArrayList<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" + "orderNum=" + orderNum + ", paymentType=" + paymentType + ", accountNum=" + accountNum + ", orderTotal=" + orderTotal + ", orderItems=" + orderItems + '}';
    }
    
    
}
