<%--
    Document   : acq_order_process
    Created on : Apr 18, 2011, 5:55:05 PM
    Author     : maqbool
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.AcquisitionDao.*,com.myapp.struts.hbm.*,com.myapp.struts.Acquisition.*,java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
AcqOrderHeader orderheader=(AcqOrderHeader)session.getAttribute("orderheader");

List<ApprovalList> get=(List<ApprovalList>)session.getAttribute("listordered");
for(int i=0;i<get.size();i++)
    System.out.println(get.get(i));

String list=(String)request.getAttribute("list");
System.out.println(list);

VendorDAO ven=new VendorDAO();
AcqVendor acqvendor=(AcqVendor)ven.search2VendorId(orderheader.getVendorId(),library_id,sub_library_id);

 BaseCurrency cur=BudgetDAO.getBaseCurrency(library_id);
 AcqCurrency acq=null;
 if(cur.getId().getBaseCurrencySymbol().equalsIgnoreCase(acqvendor.getVendorCurrency())==true){
 acq=(AcqCurrency)BudgetDAO.getConversionRate(library_id,acqvendor.getVendorCurrency());
 }

String max=(String)request.getAttribute("maxitemid");
String msg1=(String) request.getAttribute("msg1");

%>

<script type="text/javascript">
function send()
{
    window.location="<%=request.getContextPath()%>/acquisition/acq_update_approval_list.jsp";
    return false;
}
</script>

<script language="javascript">

function validate()
{
    var list=null;
        list=document.getElementById("list").value;


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



           window.location="/LibMS/acq_order.do?orderno="+<%=orderheader.getId().getOrderNo() %>;




  }


</script>
  <script language="javascript">

function get_calculate()
{

var a,b,d=0;
var c=null;
var x = document.getElementById("f").price.length;
if(x==undefined)
{

    a=document.getElementById("noc").value;
    b= document.getElementById("price").value;
    c=a*(b*"<%=acq!=null?acq.getConversionRate():1%>");
  d=c;

}else
{
    for (var i=0; i < document.getElementById("f").price.length; i++)
         {
                a=document.getElementById("f").noc[i].value;
                b= document.getElementById("f").price[i].value;
              c=a*(b*"<%=acq!=null?acq.getConversionRate():1%>");
             document.getElementById("f").total[i].value=c;
             d=d+c;

       }
   }
    document.getElementById("f").total.value=c;
    document.getElementById("total1").value=d;
    document.getElementById("discount").value=0;
   // document.getElementById("shippingcost").value=0;
    //document.getElementById("othercost").value=0;
    document.getElementById("taxrate").value=0;
    document.getElementById("taxamount").value=0;
    document.getElementById("grandtotal").value=d;

}

function get_discount()
{

 var tot= document.getElementById("total1").value;
 var dis= document.getElementById("discount").value;
  
 tot=(parseFloat(dis)*tot)/100;
 var t=document.getElementById("total1").value;
 document.getElementById("grandtotal").value=t-tot;
}

function get_shippingcost()
{

 var a= document.getElementById("grandtotal").value;
 var b= document.getElementById("shippingcost").value;
 var tot=parseFloat(a)+parseFloat(b);
 document.getElementById("grandtotal").value=tot;
}
function get_othercost()
{
 var a= document.getElementById("grandtotal").value;
 var b= document.getElementById("othercost").value;
 var tot=parseFloat(a)+parseFloat(b);
 document.getElementById("grandtotal").value=tot;
}
function get_VAT()
{
 var total=document.getElementById("total1").value;
 var tot=document.getElementById("taxrate").value;
 var tax=(parseFloat(tot)*total)/100;
 var gt=document.getElementById("grandtotal").value;
 document.getElementById("taxamount").value=tax;
 var res=parseFloat(gt)+tax
 document.getElementById("grandtotal").value=res;
}
function check1()
{
    if(document.getElementById('orderby').value=="")
    {
        alert("Enter Ordered Person Name");

        document.getElementById('orderby').focus();

        return false;
    }
     if(document.getElementById('due_date').value=="")
    {
        alert("Enter due date");

        document.getElementById('due_date').focus();

        return false;
    }
 if(document.getElementById('email').value=="")
    {
        alert("Enter Email Id");

        document.getElementById('email').focus();

        return false;
    }
    return true;

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
    <body onload="get_calculate();">
        <html:form method="post" onsubmit="return check1();" styleId="f"  action="/acq_purchase" style="position:absolute; left:20%; top:20%;">

             <table border="1" class="table" width="900" align="center">
            <tr>
           <td colspan="2" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Purchase Order</td>
       </tr>
        <tr><td align="right" colspan="2">
                           <table>
                               <tr><td>Date</td><td><html:text readonly="true" property="order_date" value="<%=orderheader.getOrderDate() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                               <tr><td>Order No</td><td><html:text readonly="true" property="order_no" value="<%=orderheader.getId().getOrderNo() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                               <html:hidden property="order_item_id" value="<%=max %>" name="AcqPurchaseOrderActionForm"/>
                           </table>
                 </td></tr>
           <tr><td width="450px">
                               <table width="100%">
                                   <tr><td colspan="2" align="center" class="headerStyle" bgcolor="#E0E8F5">Vendor</td></tr>
                                   <tr><td>Contact Person</td><td><html:text readonly="true" property="vendor_name" value="<%=acqvendor.getContactPerson() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>Company Name</td><td><html:text readonly="true" property="vendor_company" value="<%=acqvendor.getVendor() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /><td></td></tr>
                                   <tr><td>Address</td><td><html:text readonly="true" property="vendor_address" value="<%=acqvendor.getAddress() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>PIN Code</td><td><html:text readonly="true" property="vendor_pin" value="<%=acqvendor.getPin() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>Email</td><td><html:text readonly="true" property="email" value="<%=acqvendor.getEmail() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>Vendor Currency</td><td><html:text readonly="true" property="vendor_currency" value="<%=acqvendor.getVendorCurrency() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>

                               </table>

                           </td><td>
                                <table width="100%">
                                   <tr><td colspan="2" align="center" class="headerStyle" bgcolor="#E0E8F5">Ship To</td></tr>
                                   <tr><td>Name</td><td><html:text  property="ship_contact_person1"  name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>Company Name</td><td><html:text  property="ship_company1"  name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>Address</td><td><html:text  property="ship_address1"  name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>PIN Code</td><td><html:text  property="ship_pin1"  name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>FAX</td><td><html:text  property="ship_fax1"  name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>Email</td><td><html:text  property="ship_email1"  name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>

                               </table>



                           </td></tr>

           <tr><td colspan="2">
                   <table width="100%">
                       <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5">
                   Shiping Method</td><td align="center" class="headerStyle" bgcolor="#E0E8F5">Shiping Terms </td><td align="center" class="headerStyle" bgcolor="#E0E8F5"></td></tr>
                       <tr><td align="center"><html:text  property="ship_method"  name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td><td align="center"><html:text  property="ship_terms"  name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td><td align="center"></td></tr>
                   </table>
               </td></tr>

           <tr><td colspan="2" height="300px" valign="top">
                   <table width="100%" border="1" >
                       <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5">ISBN</td><td align="center" class="headerStyle" bgcolor="#E0E8F5">Title/Author</td><td align="center" class="headerStyle" bgcolor="#E0E8F5">No of Copies</td><td align="center" class="headerStyle" bgcolor="#E0E8F5">Unit Price</td><td align="center" class="headerStyle" bgcolor="#E0E8F5">Total</td></tr>
                  <logic:iterate  id="ApprovalList"  name="listordered">
                <tr>
                    <td align="right"><bean:write name="ApprovalList" property="isbn"/>

                        <input type="hidden" value="<bean:write name="ApprovalList" property="control_no"/>"/></td>

                    <td align="right"><bean:write name="ApprovalList" property="title"/>/<bean:write name="ApprovalList" property="author"/></td>
                    
                    <td align="right"><bean:write name="ApprovalList" property="no_of_copies"/>
                    <input type="hidden" id="noc" value="<bean:write name="ApprovalList" property="no_of_copies"/>"   />
                    </td>
                    <td align="right"><bean:write name="ApprovalList" property="unit_price"/>
                     <input type="hidden" id="price" value="<bean:write name="ApprovalList" property="unit_price"/>"   />

                    </td>
                    <td align="left"><input type="textbox" id="total"  name="total"   /></td>
                </tr>

        </logic:iterate>

          <tr>
              <td align="right" height="15px" colspan="4" rowspan="3"></td><td></td>

                </tr>
                   <tr>
              <td height="15px"></td>

                </tr>
                  <tr>
              <td height="15px"></td>

                </tr>
                       <tr><td colspan="3" rowspan="7" align="left">
                               <table width="100%">
                                   <tr><td align="center" class="headerStyle" style="border:1px solid black;">Notes and Instructions</td></tr>
                                   <tr><td><html:textarea rows="5" cols="80"  property="notes"  name="AcqPurchaseOrderActionForm" styleClass="" /></td></tr>
                                </table>


                          </td><td  class="headerStyle"  bgcolor="#E0E8F5" align="right">Total</td>
                          <td><input type="text"  id="total1" property="total"  name="AcqPurchaseOrderActionForm" styleClass="" /></td>
                          </tr>
                       <tr><td    class="headerStyle" bgcolor="#E0E8F5" align="right">Discount(in% Ex 5%)</td>
                           <td>
                               <html:text styleId="discount" onblur="get_discount();"  property="discount" name="AcqPurchaseOrderActionForm" styleClass="" />
                           </td></tr>
                       <%--<tr><td  class="headerStyle" bgcolor="#E0E8F5" align="right">Shiping Cost</td>
                           <td>
                               <html:text styleId="shippingcost"   onblur="get_shippingcost();" property="ship_cost"  name="AcqPurchaseOrderActionForm" styleClass="" />
                           </td></tr>
                       <tr><td  class="headerStyle" bgcolor="#E0E8F5" align="right">Other Cost</td>
                           <td>
                               <html:text styleId="othercost"  property="other_cost" onblur="get_othercost();" name="AcqPurchaseOrderActionForm" styleClass="" />
                    </td></tr>--%>
                       <tr><td  class="headerStyle" bgcolor="#E0E8F5" align="right">TAX/VAT Rate(in% Ex 5%)</td>
                           <td>
                              <html:text styleId="taxrate"   property="tax_rate" onblur="get_VAT();"  name="AcqPurchaseOrderActionForm" styleClass="" />
                           </td></tr>
                       <tr><td  class="headerStyle" bgcolor="#E0E8F5" align="right">TAX/VAT Amt</td>
                           <td>
                              <html:text styleId="taxamount"   property="tax_amount"  name="AcqPurchaseOrderActionForm" styleClass="" />
                           </td></tr>
                       <tr><td  class="headerStyle" bgcolor="#E0E8F5" align="right">Grand Total</td>
                           <td>
                               <html:text styleId="grandtotal" property="grand_total" name="AcqPurchaseOrderActionForm" styleClass="" />
                           </td></tr>
                   </table>
                    <tr>
<td align="right" >
    <input type="submit"   name="button" value="Generate Order"  size="200px" /></td>
                    <td><input align="left" type="button" name="button" value="Cancel Order" class="txt1" onclick="quit()"/></td>
                     </tr>
</table>
         <html:hidden property="library_id" name="AcqPurchaseOrderActionForm" value="<%=library_id%>" />
         <html:hidden property="sub_library_id" name="AcqPurchaseOrderActionForm" value="<%=sub_library_id%>" />
 </html:form>
 </body>
</html>
