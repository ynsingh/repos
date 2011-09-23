<%-- 
    Document   : acq_order_process
    Created on : Apr 18, 2011, 5:55:05 PM
    Author     : maqbool
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.utility.DateCalculation" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String msg1=(String) request.getAttribute("msg1");
DateCalculation doc=new DateCalculation();
String cdate=doc.now();
%>


<script language="javascript">

function validate()
{
    var list=null;
        list=document.getElementById("list").value;
   
   // alert ("List selected="+list);
  document.getElementById("list").value  = list;
  
  if (list=="")
  {

         alert("You Need To Select the List");
        return false;
  }
  
   
     return true;
 
}

function quit()
  {

       window.location="<%=request.getContextPath()%>/acquisition/acq_initiate_order.jsp";
      return false;
  }


</script>
  <script language="javascript">



function get_check_value4()
{


var c;
var x = document.getElementById("f").check.length;
if(x==undefined)
{

  c= document.getElementById("check").value;

}else{

        for (var i=0; i < document.getElementById("f").check.length; i++)
         {


      if (document.getElementById("f").check[i].checked)
      {

           if(c==null)
            c =document.getElementById("f").check[i].value;
           else
           {
              c = c +";"+ document.getElementById("f").check[i].value;
           }
       }
   }
}
 document.getElementById("f").list.value=c;
}




function check1()
{
    var order_date=document.getElementById('order_date');
    var due_date=document.getElementById('due_date');
    var str="";
    
    if(document.getElementById('orderby').value=="")
    {
        alert("Enter Ordered Person Name");

        document.getElementById('orderby').focus();

        return false;
    }
     if(due_date.value=="")
    {
        alert("Enter due date");

        document.getElementById('due_date').focus();

        return false;
    }

    if(IsDateGreater(order_date.value,due_date.value)==true)
    {
       str+="\nDue Date Should be greater than or equal to Order Date";
       alert(str);
         document.getElementById('due_date').focus();
         return false;
    }
    return true;
    }

function IsDateGreater(DateValue1, DateValue2)
{

var DaysDiff;
Date1 = new Date(DateValue1);
Date2 = new Date(DateValue2);
DaysDiff = Math.floor((Date1.getTime() - Date2.getTime())/(1000*60*60*24));

if(DaysDiff >=0)
{

return true;
}
else
{

return false;
}
}

</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>
<style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>
<script>


$(document).ready(function()
{
   var jQueryDatePicker1Opts =
   {
      dateFormat: 'yy-mm-dd',
      changeMonth: false,
      changeYear: false,
      showButtonPanel: false,
      showAnim: 'show'
   };
   $("#due_date").datepicker(jQueryDatePicker1Opts);
  
});
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      </head>
    <body>
        <html:form method="post" onsubmit="return check1();" styleId="f" action="/acq_initiate_order_process" style="position:absolute; left:20%; top:20%;">
            <table border="1" class="table" width="900" align="center">
                <html:hidden property="library_id" name="AcqOrderManagementActionForm" value="<%=library_id%>" />
        
        <html:hidden property="sub_library_id" name="AcqOrderManagementActionForm" value="<%=sub_library_id%>" />
            <tr>
           <td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Order Process</td>
           </tr>
            <tr><td><br>
               <table width="700" border="0">
                   <tr>
                       <td align="right"> <strong>Order No: </strong></td>
                       <td> <html:text readonly="true" property="order_no" name="AcqOrderManagementActionForm" styleClass="textBoxWidth" >

  </html:text>
</td>
                     <td width="150" align="right" class="txtStyle"><strong>Order date:</strong> </td>
                     <td><html:text readonly="false" value="<%=cdate%>" property="order_date" styleId="order_date" name="AcqOrderManagementActionForm" styleClass="textBoxWidth" />

    </td>
                   </tr>
                   <tr>
                       <td align="right" class="txtStyle"><strong>Ordered By:</strong></td>
                       <td><html:text readonly="false" styleId="orderby" property="ordered_by" name="AcqOrderManagementActionForm" styleClass="textBoxWidth" />

                        </td>
                         <td align="right" class="txtStyle"><strong>Due date:</strong></td>
                         <td><html:text readonly="false"  styleId="due_date" property="due_date" name="AcqOrderManagementActionForm" styleClass="textBoxWidth" />

                        </td>
                        
                   </tr>
                   <tr>
                      <td align="right" class="txtStyle"><strong>Vendor:</strong></td>
                       <td><html:text readonly="true" property="vendor" name="AcqOrderManagementActionForm" styleClass="textBoxWidth" />
                         </td>
                   </tr>
                  
        </table>


 </td></tr>
        <tr>
                       <td align="center"> <input type="hidden" name="list" id="list" value="" />
                       <input type="submit"   name="button" value="Process Order" class="txt1" onclick="return validate();" />
                    <input align="left" type="button" name="button" value="Cancel" class="txt1" onclick="return quit();"/></td>

                       
                   </tr>
        <tr>
       <td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Approved List For Order</td>
       </tr>
        <tr><td>
               <table>
                   <tr><td>
         <table>
             <tr bgcolor="#E0E8F5" ><td width="100" >Control No</td><td width="200">Title</td><td width="200">Author</td><td width="200">UnitPrice</td><td width="200">No of Copies</td><td width="200">Approval Type</td><td width="200">Select</td></tr>
             <logic:iterate id="ApprovalList" name="opacList">
                <tr>
                    <td><%--<bean:write name="ApprovalList" property="isbn"/>--%>
                   <bean:write name="ApprovalList" property="control_no"/>
                    </td>
                    <td><bean:write name="ApprovalList" property="title"/></td>
                    <td><bean:write name="ApprovalList" property="author"/></td>
                    <td><bean:write name="ApprovalList" property="unit_price"/></td>

                   
                    <td><bean:write name="ApprovalList" property="no_of_copies"/></td>
                    <td><bean:write name="ApprovalList" property="status"/><bean:write name="ApprovalList" property="acq_mode"/></td>
                    <td><input type="checkbox" id="check" value="<bean:write name="ApprovalList" property="control_no"/>,<bean:write name="ApprovalList" property="approval_no"/>"  onclick="get_check_value4()"/></td>
                </tr>

</logic:iterate>
               
 </table>
                           
                           </td></tr>
               </table>
                
            </td></tr>

            </table>
        </html:form>
 </body>
</html>
