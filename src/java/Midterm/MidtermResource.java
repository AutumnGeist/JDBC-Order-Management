/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Midterm;

import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author marilyn
 */

@Path("Midterm")
public class MidtermResource {

    private static ArrayList<Customer> customerJSON = new ArrayList();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MidtermResource
     */
    public MidtermResource() {
    }

    /**
     * Retrieves representation of an instance of Midterm.MidtermResource
     * @return an instance of java.lang.String
     */
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("greeting")
    public String getXml() {
        //TODO return proper representation object
        return "Greetings!";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("addCustomerJSON")
    public String addCustomerJSON(Customer cust) throws Exception {
        System.out.println(" Received Customer = " + cust);
        customerJSON.add(cust);
        
        try {
            //call method to create class objects
            JDBC.createEntities(cust);               
            return "Success";
        
        }catch(Exception e) {
            return "Failure";
        }
    }
    
    @GET
    @Path("getCustomerJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public Customers getCustomerJSON() throws Exception {
        System.out.println(" JSON Customer List Size = " + customerJSON.size());
        //return new Customers(customerJSON);
        return new Customers(JDBC.getEntities());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of MidtermResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
