/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Midterm;

/**
 *
 * @author marilyn
 */
public class OrderItems {
    private String itemName;
    private int itemQty;

    public OrderItems(String itemName, int itemQty) {
        this.itemName = itemName;
        this.itemQty = itemQty;
    }

    public OrderItems() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    @Override
    public String toString() {
        return "OrderItems{" + "itemName=" + itemName + ", itemQty=" + itemQty + '}';
    }
    
    
}
