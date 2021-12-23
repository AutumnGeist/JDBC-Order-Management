# JDBC-Order-Management
Restful web service that consumes/produces JSON data and stores in Derby DB using JDBC

The business domain modeled deals with order management that includes Customer, Address, Order, and Order Items entities.

All entity objects are mapped to related Derby database tables. Utilizes Insert and Get operations.

REST API
AddCustomer- creates a new customer and associated entities from JSON data and saves the object hierarchy in the associated Apache Derby DB 
GetCustomer- returns all customer data saved in the database. Returned list includes full object hierachy assembled as a JSON object
