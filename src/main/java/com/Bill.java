package com;
import java.sql.*;
public class Bill
{
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 con =
 DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ electrogrid", "paf", "12345678");
 }
 catch (Exception e)
 {
 e.printStackTrace();
 }
 return con;
 }
public String readBills()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for reading.";
 }
 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Customer ID</th><th>Payment ID</th><th>Account No</th><th>Issued Date</th><th>Price Per Unite</th><th>Used Unite</th>" + "<th>Total Amount</th><th>Update</th><th>Remove</th></tr>";
 String query = "select * from bill";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);

 // iterate through the rows in the result set
 while (rs.next())
 {
 String billID = Integer.toString(rs.getInt("billID"));
 String cusbID = rs.getString("cusbID");
 String paymentID = rs.getString("paymentID");
 String accountNo = rs.getString("accountNo");
 String bDate = rs.getString("bDate");
 String ppUnit = rs.getString("ppUnit");
 String usedUnit = rs.getString("usedUnit");
 String tbAmount = rs.getString("tbAmount");
 

 // Add into the html table
output += "<tr><td><input id='hidBillIDUpdate'name='hidBillIDUpdate'type='hidden' value='" +billID+ "'>" + cusbID + "</td>";
output += "<td>" + paymentID + "</td>";
output += "<td>" + accountNo + "</td>";
output += "<td>" + bDate + "</td>";
output += "<td>" + ppUnit + "</td>";
output += "<td>" + usedUnit + "</td>";
output += "<td>" + tbAmount + "</td>";
 
// buttons
output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"+"<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-Billid='"+ billID + "'>" + "</td></tr>";
 }
 con.close();
 
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the items.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String insertBill(String cusbID, String paymentID,String accountNo, String bDate,String ppUnit,String usedUnit,String tbAmount)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for inserting.";
 }
 
 // create a prepared statement
 String query = " insert into bill(`billID`,`cusbID`,`paymentID`,`accountNo`,`bDate`,`ppUnit`,`usedUnit`,`tbAmount`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, cusbID);
 preparedStmt.setString(3, paymentID);
 preparedStmt.setString(4, accountNo);
 preparedStmt.setString(5, bDate);
 preparedStmt.setString(6, ppUnit);
 preparedStmt.setString(7, usedUnit);
 preparedStmt.setString(8, tbAmount);
 
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newBills = readBills();
 output = "{\"status\":\"success\", \"data\": \"" +newBills + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Bill.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updateBill(String billID,String cusbID, String paymentID,String accountNo, String bDate,String ppUnit,String usedUnit,String tbAmount)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for updating.";
 }
 
 // create a prepared statement
 String query = "UPDATE bill SET cusbID=?,paymentID=?,accountNo=?,bDate=?,accountNo=?,ppUnit=?,usedUnit=?,tbAmount=? WHERE billID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 
 // binding values
 preparedStmt.setString(1, cusbID);
 preparedStmt.setString(2, paymentID);
 preparedStmt.setString(3, accountNo);
 preparedStmt.setString(4, bDate);
 preparedStmt.setString(5, accountNo);
 preparedStmt.setString(6, ppUnit);
 preparedStmt.setString(7, usedUnit);
 preparedStmt.setString(8, tbAmount);
 preparedStmt.setInt(9, Integer.parseInt(billID));

 //execute the statement
preparedStmt.execute();
con.close();
String newBills = readBills();
output = "{\"status\":\"success\", \"data\": \"" +newBills + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while updating the Bill.\"}";
System.err.println(e.getMessage());
}
return output;
}
public String deleteBill(String billID)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for deleting.";
}

// create a prepared statement
String query = "delete from bill where billID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);

// binding values
preparedStmt.setInt(1, Integer.parseInt(billID));

// execute the statement
preparedStmt.execute();
con.close();
String newBills = readBills();
output = "{\"status\":\"success\", \"data\": \"" +newBills + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while deleting the Bill.\"}";
System.err.println(e.getMessage());
}
return output;
}
}