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
}
function check1()
{


     if(document.getElementById('fn').value=="")
    {
        alert("Enter Formal Name For example dollar for $");

        document.getElementById('fn').focus();

        return false;
    }
     var keyValue = document.getElementById('dir').options[document.getElementById('dir').selectedIndex].value;

    if (keyValue=="Select")

    {
        alert("Select Direction");

        document.getElementById('dir').focus();

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
    window.location="<%=request.getContextPath()%>/acquisition/acq_base_currency.jsp";
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
   <html:form method="post" action="/acq_currency_conversion"  onsubmit="return check1()" >
       <table border="1" class="table" align="center">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>Set Base Currency</b></td></tr>
                <tr><td>
                        <table width="400px" border="0" cellspacing="4" cellpadding="1" align="left">
                        <tr>


                        </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
    <td align="left" class="txtStyle"><strong>Source Currency Name:</strong></td>
    <td><html:text readonly="true" property="base_currency_symbol" styleId="currency_id" name="AcqCurrencyActionForm" styleClass="textBoxWidth" /></td>
</tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
<tr>
    <td align="left" class="txtStyle"><strong>Formal Name:<a class="star">*</a></strong></td>
    <td><html:text readonly="<%=read%>" property="formal_name" styleId="fn" name="AcqCurrencyActionForm" styleClass="textBoxWidth" /></td>
</tr>
<tr>
     <td align="left" class="txtStyle"><strong>Date:</strong></td>
  <td>
       
      <html:text readonly="true" property="date" name="AcqBudgetActionForm" value="<%=cdate%>" styleClass="textBoxWidth" />
      
  </td>
</tr>


<tr><td colspan="5" height="5px"></td>
</tr>





<tr>
   <td align="left" class="txtStyle"><strong>Direction:<a class="star">*</a></strong></td>
   <td>
       <% if(button.equalsIgnoreCase("Add")){%>
       
       <html:select   property="direction" value="Select"  name="AcqCurrencyActionForm" styleId="dir">
             <html:option value="b">Before Amount</html:option>
           <html:option value="a">After Amount</html:option>
            <html:option value="Select">Select</html:option>
       </html:select>
           <%}else if(button.equalsIgnoreCase("Update")){%>
           <html:select  property="direction"   name="AcqCurrencyActionForm" styleId="dir" >
               

           <html:option value="b">Before Amount</html:option>
           <html:option value="a">After Amount</html:option>
            <html:option value="Select">Select</html:option>
       </html:select>
            <%}else{%>
            <html:select  property="direction"  disabled="true" name="AcqCurrencyActionForm" styleId="dir" >
               

           <html:option value="b">Before Amount</html:option>
           <html:option value="a">After Amount</html:option>
            <html:option value="Select">Select</html:option>
       </html:select>
            <%}%>
  </td>
</tr>
<tr><td colspan="5" height="5px"></td>
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
<tr><td colspan="5" height="5px" class="mandatory" align="right"><a class="star">*</a>indicated fields are mandatory</td></tr>
</table>
</td></tr> </table>

  </html:form>
       </div>
    </body>
</html>
