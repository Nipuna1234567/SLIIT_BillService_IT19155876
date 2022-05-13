<%@page import="com.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bills Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Bills.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Bill Service Management</h1>
<form id="formBill" name="formBill">
 Customer ID:
 <input id="cusbID" name="cusbID" type="text"
 class="form-control form-control-sm">
 <br> Payment ID:
 <input id="paymentID" name="paymentID" type="text"
 class="form-control form-control-sm">
 <br> Account No:
 <input id="accountNo" name="accountNo" type="text"
 class="form-control form-control-sm">
 <br> Issued Date:
 <input id="bDate" name="bDate" type="text"
 class="form-control form-control-sm">
 <br> Price Per Unit
 <input id="ppUnit" name="ppUnit" type="text"
 class="form-control form-control-sm">
 <br> Used Unit 
 <input id="usedUnit" name="usedUnit" type="text"
 class="form-control form-control-sm">
 <br> Total Amount
 <input id="tbAmount" name="tbAmount" type="text"
 class="form-control form-control-sm">
 <br>
 
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidBillIDSave"
 name="hidBillIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divBillsGrid">
 <%
 Bill BillObj = new Bill();
 out.print(BillObj.readBills());
 %>
</div>
</div> </div> </div>
</body>
</html>