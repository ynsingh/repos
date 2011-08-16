<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.utility.DateCalculation"  %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%! boolean read=false;%>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String button=(String)request.getAttribute("button");
DateCalculation doc=new DateCalculation();
String cdate=doc.now();
//request.setAttribute("back", request.getAttribute("back"));

if (button.equals("View")||button.equals("Delete"))
read=true;

else
    read=false;
String msg1=(String) request.getAttribute("msg1");
%>
<script type="text/javascript">
function add(){
    var a=parseInt(document.getElementById('rcvd').value);
    var b=parseInt(document.getElementById('op').value);
    var c=document.getElementById('tm');
    c.value=a+b;

        document.getElementById('netbal').value=a+b;


}
function check1()
{


     if(document.getElementById('op').value=="")
    {
        alert("Enter Opening Bal in Rupee or 0 for None");

        document.getElementById('op').focus();

        return false;
    }
     if(document.getElementById('rcvd').value=="")
    {
        alert("Enter Receiving Amount in Rupee or 0 for None");

        document.getElementById('rcvd').focus();

        return false;
    }
return true;
  }

function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }

  function confirm1()
{
var answer = confirm ("Do you want to delete record?")
if (answer!=true)
    {
        document.getElementById('button1').focus();
        return false;
    }
   else{ return true;}
}
function send()
{
    window.location="<%=request.getContextPath()%>/acquisition/acq_budget_allocation.jsp";
    return false;
}
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="common" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
    <body>


   <%if(msg1!=null){%>   <span style=" position:absolute; top: 120px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>
   <div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
   <html:form method="post" action="/add_budget_location"  onsubmit="return check1()" >
       <table border="1" class="table" align="center">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>Budget Head Allocation Detail Entry</b></td></tr>
                <tr><td>
                        <table width="400px" border="0" cellspacing="4" cellpadding="1" align="left">
                        <tr>
<html:hidden  property="transaction_id" name="AcqBudgetActionForm"/>

                        </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
    <td align="left" class="txtStyle"><strong>Budget Head Id:</strong></td>
    <td><html:text readonly="true" property="budgethead_id" name="AcqBudgetActionForm" styleClass="textBoxWidth" /></td>
</tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
<tr>
    <td align="left" class="txtStyle"><strong>Financial year:</strong></td>
    <td><html:text readonly="true" property="financial_yr" name="AcqBudgetActionForm" styleClass="textBoxWidth" /></td>
</tr>
<%--<tr>
    <td width="150" align="left" class="txtStyle"><strong>Budget Head Name:<a class="star">*</a></strong> </td>
    <td><html:text readonly="<%=read%>"  property="budget_name" name="AcqBudgetActionForm" styleClass="textBoxWidth" styleId="budget_name" />
    </td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
    <td align="left" class="txtStyle"><strong>Budget Head Description:</strong></td>
  <td><html:text readonly="<%=read%>" property="budgethead_description" name="AcqBudgetActionForm" styleClass="textBoxWidth" />
  </td>
  </tr>--%>
<tr><td colspan="5" height="5px"></td>
</tr>
<tr>
   <td align="left" class="txtStyle"><strong>Opening Balance:*</strong></td>
   <td><html:text readonly="<%=read%>" styleId="op" property="opening_balance"  onkeypress="return isNumberKey(event)" name="AcqBudgetActionForm" styleClass="textBoxWidth"/>
  </td>
</tr>
<tr><td colspan="5" height="5px"></td>
</tr>

<tr>
   <td align="left" class="txtStyle"><strong>Recieved Amount:*</strong></td>
   <td><html:text readonly="<%=read%>" styleId="rcvd" property="recieved_amount"  onkeypress="return isNumberKey(event)" name="AcqBudgetActionForm" styleClass="textBoxWidth" onblur="return add()" />
  </td>
</tr>
<tr><td colspan="5" height="5px"></td>
</tr>

<tr>
   <td align="left" class="txtStyle"><strong>Total Amount:</strong></td>
   <td><html:text readonly="true" styleId="tm" property="total_amount"  name="AcqBudgetActionForm" styleClass="textBoxWidth" />
  </td>
</tr>
<tr><td colspan="5" height="5px"></td>
</tr>
<tr>
   <td align="left" class="txtStyle"><strong>Date:</strong></td>
  <td><html:text readonly="false" property="date" name="AcqBudgetActionForm" value="<%=cdate%>" styleClass="textBoxWidth" />
  </td>
</tr>
<tr>
   <td align="left" class="txtStyle"><strong>Estimiated Expense Based on Generated Approval List:</strong></td>
  <td><html:text readonly="true" property="estimited_exp" name="AcqBudgetActionForm" styleClass="textBoxWidth" />
  </td>
</tr>
<tr>
   <td align="left" class="txtStyle"><strong>Net Bal:</strong></td>
   <td><html:text readonly="true" property="net_bal" name="AcqBudgetActionForm" styleId="netbal"  styleClass="textBoxWidth" />
  </td>
</tr>
<tr><td colspan="5" height="5px"></td>
</tr>
<tr>
   <td align="left" class="txtStyle"><strong>Remarks:</strong></td>
  <td><html:textarea readonly="<%=read%>" property="remarks" name="AcqBudgetActionForm" styleClass="textBoxWidth" />
  </td>
</tr>

<tr><td></td><td></td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>

<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
<td align="center" colspan="5">
    <%if(button.equals("Update")){%>
    <input id="button1"  name="button" type="submit" value="<%=button%>"/>
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Back" onclick="return send()" />
    <%}else if(button.equals("Delete")){%>
    <input id="button1"  name="button" type="submit" onClick="return confirm1()" value="<%=button%>"  />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" onclick="return send()"  value="Back" />
   <%}else if(button.equals("Add")){%>
    <input id="button1"  name="button" type="submit" value="Submit" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Back" onclick="return send()" />
    <%}else{%>
    <input  name="button" type="submit" value="Back"/><%}%>
	</td>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
</table>
</td></tr> </table>

  </html:form>
       </div>
    </body>
</html>
