/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Midterm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author marilyn
 */
public class JDBC {
    
    public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = getConnection();
        System.out.println("Successfully Connected");
        
        try {
            Customer customer = new Customer();
            //adds customer to DB
            AddCustomer(conn, customer);

            //retrieve Customers
            GetCustomers(conn);

        }
        catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }   
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String databaseURL = "jdbc:derby://localhost:1527/MidtermDB";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Set user and password properties
            Properties properties = new Properties();
            properties.setProperty("user", "TEST_USER");
            properties.setProperty("password", "password");
        // Get a connection
        conn = DriverManager.getConnection(databaseURL, properties);
        } catch (Exception e) {
            System.out.println("Connection Exception");
            e.printStackTrace();
        }
        
        return conn;
    }

    public static void AddCustomer(Connection conn, Customer customer) throws SQLException {
        
        String INSERT = "INSERT INTO CUSTOMER_TB (FIRST_NAME, LAST_NAME, PRIME_CUSTOMER)\n" +
            "    values (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(INSERT);
            
        try {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2,customer.getLastName());
            pstmt.setString(3, customer.getPrimeCustomer());

            int count = pstmt.executeUpdate();
            System.out.println(" Number of rows created = " + count);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pstmt.close();
        }
        //call methods to add cust info to other tables
        AddAddress(conn, customer.getAddress());
        AddOrder(conn, customer.getOrder());
    }
    
    public static void AddAddress(Connection conn, Address address) throws SQLException {
        //get customerID from last customer entry
        PreparedStatement pstmt2 = conn.prepareStatement("SELECT max(CUSTOMER_ID) from CUSTOMER_TB");
        int cust_id = 0;
        String INSERT = "INSERT INTO ADDRESS_TB (STREET, CITY, STATE, ZIPCODE, CUSTOMER_ID)\n" +
            "    values (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(INSERT);
            
        try {
            ResultSet rs = pstmt2.executeQuery();
            while(rs.next()) {
                cust_id = rs.getInt(1);
            }           
            pstmt.setString(1, address.getStreet());
            pstmt.setString(2, address.getCity());
            pstmt.setString(3, address.getState());
            pstmt.setString(4, address.getZipcode());
            pstmt.setInt(5, cust_id);

            int count = pstmt.executeUpdate();
            System.out.println(" Number of rows created = " + count);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pstmt.close();
            pstmt2.close();
        }
       
    }
    public static void AddOrder(Connection conn, Order order) throws SQLException {
        //get customerID from last customer entry
        PreparedStatement pstmt2 = conn.prepareStatement("SELECT max(CUSTOMER_ID) from CUSTOMER_TB");
        int cust_id = 0;
        String INSERT = "INSERT INTO ORDER_TB (ORDER_NUM, PAYMENT_TYPE, ACCOUNT_NUM, ORDER_TOTAL, CUSTOMER_ID)\n" +
            "    values (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(INSERT);
            
        try {
            ResultSet rs = pstmt2.executeQuery();
            while(rs.next()) {
                cust_id = rs.getInt(1);
            }           
            pstmt.setString(1, order.getOrderNum());
            pstmt.setString(2, order.getPaymentType());
            pstmt.setString(3, order.getAccountNum());
            pstmt.setDouble(4, order.getOrderTotal());
            pstmt.setInt(5, cust_id);

            int count = pstmt.executeUpdate();
            System.out.println(" Number of rows created = " + count);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pstmt.close();
            pstmt2.close();
        }
        AddOrderItems(conn, order);
    }
    
    public static void AddOrderItems(Connection conn, Order order) throws SQLException {
        //get orderID from last order entry
        PreparedStatement pstmt2 = conn.prepareStatement("SELECT max(ORDER_ID) from ORDER_TB");
        int order_id = 0;
        String INSERT = "INSERT INTO ORDER_ITEMS_TB (ITEM_NAME, ITEM_QTY, ORDER_ID)\n" +
            "    values (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(INSERT);
        
        try {
            ResultSet rs = pstmt2.executeQuery();
            while(rs.next()) {
                order_id = rs.getInt(1);
            }           
            ArrayList<OrderItems> items = order.getOrderItems();
            for (int i=0; i<=items.size(); i++) {
                OrderItems item = (OrderItems) items.get(i);
                pstmt.setString(1, item.getItemName());
                pstmt.setInt(2, item.getItemQty());
                pstmt.setInt(3, order_id);
                int count = pstmt.executeUpdate();
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pstmt.close();
            pstmt2.close();
        }
       
    }
    
    public static ArrayList<Customer> GetCustomers(Connection conn) throws SQLException {
        String SELECT = "Select CUSTOMER_ID, FIRST_NAME, LAST_NAME, PRIME_CUSTOMER from TEST_USER.customer_tb order by customer_id";
        PreparedStatement pstmt = conn.prepareStatement(SELECT);

        ResultSet rs = pstmt.executeQuery();
        
        Customer cust = new Customer();
        ArrayList<Customer> customers = new ArrayList();

        try {
            while(rs.next()) {
                int id = rs.getInt(1);
                cust.setFirstName(rs.getString(2));
                cust.setLastName(rs.getString(3));
                cust.setPrimeCustomer(rs.getString(4));
              
                //call to get address & orders
                cust.setAddress(GetAddress(conn, id));
                cust.setOrder(GetOrders(conn, id));
                //add to customers list
                customers.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pstmt.close();
            rs.close();
        }
        return customers;
    }
    
    public static Address GetAddress(Connection conn, int cust_id) throws SQLException {
        String SELECT = "Select STREET, CITY, STATE, ZIPCODE from TEST_USER.ADDRESS_TB where CUSTOMER_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(SELECT);
        pstmt.setInt(1, cust_id);
        ResultSet rs = pstmt.executeQuery();
        
        Address address = new Address();

        try {
            while(rs.next()) {
                address.setStreet(rs.getString(1));
                address.setCity(rs.getString(2));
                address.setState(rs.getString(3));
                address.setZipcode(rs.getString(4));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pstmt.close();
            rs.close();
        }
        return address;
    }
    
    public static Order GetOrders(Connection conn, int cust_id) throws SQLException {
        String SELECT = "Select ORDER_ID, ORDER_NUM, PAYMENT_TYPE, ACCOUNT_NUM, ORDER_TOTAL from TEST_USER.ORDER_TB where CUSTOMER_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(SELECT);
        pstmt.setInt(1, cust_id);
        ResultSet rs = pstmt.executeQuery();
        
        Order order = new Order();
        order.setOrderItems(new ArrayList());

        try {
            while(rs.next()) {
                int orderID = rs.getInt(1);
                order.setOrderNum(rs.getString(2));
                order.setPaymentType(rs.getString(3));
                order.setAccountNum(rs.getString(4));
                order.setOrderTotal(rs.getDouble(5));
                //call to set OrderItems list
                order.setOrderItems(GetOrderItems(conn, orderID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pstmt.close();
            rs.close();
        }
        return order;
    }
    
    public static ArrayList<OrderItems> GetOrderItems(Connection conn, int orderID) throws SQLException {
        String SELECT = "Select ITEM_NAME, ITEM_QTY from TEST_USER.ORDER_ITEMS_TB where ORDER_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(SELECT);
        pstmt.setInt(1, orderID);
        ResultSet rs = pstmt.executeQuery();
        
        
        ArrayList<OrderItems> items = new ArrayList();

        try {
            while(rs.next()) {
                OrderItems item = new OrderItems();
                item.setItemName(rs.getString(1));
                item.setItemQty(rs.getInt(2));
                items.add(item);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pstmt.close();
            rs.close();
        }
        return items;
    }
    
    public static ArrayList<Customer> getEntities() throws Exception {
        Connection conn = getConnection();
        return GetCustomers(conn);
    }
    
    public static void createEntities(Customer cust) throws Exception {
        Connection conn = getConnection();      
        AddCustomer(conn, cust);
    }
}
    
    
    
