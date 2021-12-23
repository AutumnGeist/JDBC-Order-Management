/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Midterm;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author marilyn
 */
@XmlRootElement(name="Customer", namespace="Midterm")
@XmlType(propOrder= {"firstName", "lastName", "primeCustomer", "address", "order"})
public class Customer {
    private String firstName;
    private String lastName;
    private String primeCustomer;
    private Address address;
    private Order order;

    public Customer(String firstName, String lastName, String primeCustomer, Address address, Order order) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.primeCustomer = primeCustomer;
        this.address = address;
        this.order = order;
    }

    public Customer() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrimeCustomer() {
        return primeCustomer;
    }

    public void setPrimeCustomer(String primeCustomer) {
        this.primeCustomer = primeCustomer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Customer{" + "firstName=" + firstName + ", lastName=" + lastName + ", primeCustomer=" + primeCustomer + ", address=" + address + ", order=" + order + '}';
    }
    
    
}


