/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Midterm;

import java.util.ArrayList;

/**
 *
 * @author Marilyn
 */
public class Customers {
    private ArrayList<Customer> customers = new ArrayList();

    public Customers() {
    }

    public Customers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
    
    
}
