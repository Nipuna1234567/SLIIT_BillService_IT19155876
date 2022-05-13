$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();
// Form validation-------------------
var status = validateBillForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid------------------------
var type = ($("#hidBillIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "BillsAPI",
type : type,
data : $("#formBill").serialize(),
dataType : "text",
complete : function(response, status)
{
onBillSaveComplete(response.responseText, status);
}
});
});



function onBillSaveComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();
$("#divBillsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while saving.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while saving..");
$("#alertError").show();
}

$("#hidBillIDSave").val("");
$("#formBill")[0].reset();
}



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidBillIDSave").val($(this).closest("tr").find('#hidBillIDUpdate').val());
 $("#cusbID").val($(this).closest("tr").find('td:eq(0)').text());
 $("#paymentID").val($(this).closest("tr").find('td:eq(1)').text());
 $("#accountNo").val($(this).closest("tr").find('td:eq(2)').text());
 $("#bDate").val($(this).closest("tr").find('td:eq(3)').text());
 $("#ppUnit").val($(this).closest("tr").find('td:eq(4)').text());
 $("#usedUnit").val($(this).closest("tr").find('td:eq(5)').text());
 $("#tbAmount").val($(this).closest("tr").find('td:eq(6)').text());
});

// DELETE==========================================
$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
url : "BillsAPI",
type : "DELETE",
data : "billID=" + $(this).data("billid"),
dataType : "text",
complete : function(response, status)
{
onBillDeleteComplete(response.responseText, status);
}
});
});



function onBillDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divBillsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}



// CLIENT-MODEL================================================================
function validateBillForm()
{
// CUSID
if ($("#cusbID").val().trim() == "")
{
return "Insert Customer ID.";
}
// PaymentID
if ($("#paymentID").val().trim() == "")
{
return "Insert Payment ID.";
}

// AccountNO-------------------------------
if ($("#accountNo").val().trim() == "")
{
return "Insert Account No.";
}
// is numerical value
var tmpAccountNo = $("#accountNo").val().trim();
if (!$.isNumeric(tmpAccountNo))
{
return "Insert a numerical value for Account No.";
}
// Issued Date
if ($("#bDate").val().trim() == "")
{
return "Insert Issued Date.";
}
// Price Per Unit
if ($("#ppUnit").val().trim() == "")
{
return "Insert Price Per Unit.";
}
// Used Unit
if ($("#usedUnit").val().trim() == "")
{
return "Insert Used Unit.";
}
// Total Amount
if ($("#tbAmount").val().trim() == "")
{
return "Insert Total Amount.";
}
return true;
}