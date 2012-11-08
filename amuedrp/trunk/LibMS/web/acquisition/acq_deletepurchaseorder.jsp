<%-- 
    Document   : acq_order_process
    Created on : Apr 18, 2011, 5:55:05 PM
    Author     : maqbool
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.AcquisitionDao.*,com.myapp.struts.hbm.*,com.myapp.struts.hbm.AcqOrder1,java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%! boolean read=false;%>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
AcqOrderHeader orderheader=(AcqOrderHeader)session.getAttribute("orderhead");
List<AcqOrder1> listordered=(List<AcqOrder1>)session.getAttribute("orderlist");




VendorDAO ven=new VendorDAO();
AcqVendor acqvendor=(AcqVendor)ven.search2VendorId(orderheader.getVendorId(),library_id,sub_library_id);
BudgetDAO buddao=new BudgetDAO();
 BaseCurrency cur=buddao.getBaseCurrency(library_id);
 AcqCurrency acq=null;
 if(cur.getId().getBaseCurrencySymbol().equalsIgnoreCase(acqvendor.getVendorCurrency())==false){
 acq=(AcqCurrency)buddao.getConversionRate(library_id,acqvendor.getVendorCurrency());
 }
 
String button=(String)request.getAttribute("button");

if (button.equals("View Order")||button.equals("Cancel Order"))
read=true;

else
    read=false;


%>



<script language="javascript">



function quit()
  {

      window.location="<%=request.getContextPath()%>/acquisition/acq_initiate_order.jsp";
      return false;
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
   document.getElementById("total").value=c;
    document.getElementById("total1").value=d;

}else{


        for (var i=0; i < document.getElementById("f").price.length; i++)
         {
                a=document.getElementById("f").noc[i].value;
                b= document.getElementById("f").price[i].value;
              c=a*(b*"<%=acq!=null?acq.getConversionRate():1%>");
             document.getElementById("f").total[i].value=c;
             d=d+c;

       }
        document.getElementById("total1").value=d;
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
    <body onload="get_calculate();">
        <html:form method="post" onsubmit="return check1();" styleId="f"  action="/acq_delete_purchase" style="position:absolute; left:20%; top:20%;">

             <table border="1" class="table" width="900" align="center">
            <tr>
           <td colspan="2" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Purchase Order</td>
       </tr>
        <tr><td align="right" colspan="2">
                           <table>
                               <tr><td>Date</td><td><html:text readonly="true" property="order_date" value="<%=orderheader.getOrderDate() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                               <tr><td>Order No</td><td><html:text readonly="true" property="order_no" value="<%=orderheader.getId().getOrderNo() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                              
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
                                   <tr><td>Name</td><td><html:text  readonly="true" property="ship_contact_person" value="<%=orderheader.getShipContactName() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>Company Name</td><td><html:text readonly="true" property="ship_company" value="<%=orderheader.getShipCompanyName() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>Address</td><td><html:text readonly="true" property="ship_address" value="<%=orderheader.getShipAddress() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>PIN Code</td><td><html:text readonly="true" property="ship_pin" value="<%=orderheader.getShipPin() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>FAX</td><td><html:text  readonly="true" property="ship_fax" value="<%=orderheader.getShipFax() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                                   <tr><td>Email</td><td><html:text  readonly="true" property="ship_email" value="<%=orderheader.getShipEmail() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>

                               </table>



                           </td></tr>

           <tr><td colspan="2">
                   <table width="100%">
                       <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5">
                   Shiping Method</td><td align="center" class="headerStyle" bgcolor="#E0E8F5">Shiping Terms </td></tr>
                       <tr><td align="center"><html:text readonly="true" property="ship_method" value="<%=orderheader.getShippingMethod()%>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td><td align="center"><html:text readonly="true"  property="ship_terms" value="<%=orderheader.getShippingTerms() %>" name="AcqPurchaseOrderActionForm" styleClass="textBoxWidth" /></td></tr>
                   </table>
               </td></tr>
           
           <tr><td colspan="2" height="300px" valign="top">
                   <table width="100%"  >
                          <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5">ISBN</td><td align="center" class="headerStyle" bgcolor="#E0E8F5">Title/Author</td><td align="center" class="headerStyle" bgcolor="#E0E8F5">No of Copies</td><td align="center" class="headerStyle" bgcolor="#E0E8F5">Unit Price</td><td align="center" class="headerStyle" bgcolor="#E0E8F5">Total</td></tr>
                       <logic:iterate  id="ApprovalList"  name="orderlist">
                <tr>
                    <td align="right"><bean:write name="ApprovalList" property="isbn"/>
                        
                        <input type="hidden" value="<bean:write name="ApprovalList" property="control_no"/>"/></td>

                    <td align="right"><bean:write name="ApprovalList" property="title"/>/<bean:write name="ApprovalList" property="author"/></td>
                    <td align="right"><bean:write name="ApprovalList" property="no_of_copies"/>
                    <input type="hidden" id="noc" value="<bean:write name="ApprovalList" property="no_of_copies"/>"   />
                    <input type="hidden" id="conversion_rate" value="<bean:write name="ApprovalList" property="conversion_rate"/>"   />
                    </td>
                    <td align="right"><bean:write name="ApprovalList" property="unit_price"/>
                     <input type="hidden" id="price" value="<bean:write name="ApprovalList" property="unit_price"/>"   />

                    </td>
                    <td align="left"><input type="textbox" id="total"  readonly name="total"   /></td>
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
                                   <tr><td><html:textarea rows="5" cols="80" readonly="true"  property="notes" value="<%=orderheader.getComments()%>" name="AcqPurchaseOrderActionForm" styleClass="" /></td></tr>
                                </table>


                          </td><td  class="headerStyle"  bgcolor="#E0E8F5" align="right">Total</td>
                          <td><html:text styleId="total1" readonly="true" property="total"  name="AcqPurchaseOrderActionForm" styleClass="" /></td>
                          </tr>
                       <tr><td    class="headerStyle" bgcolor="#E0E8F5" align="right">Discount(in% Ex 5%)</td>
                           <td>
                               <html:text styleId="discount" readonly="true"  property="discount" value="<%=orderheader.getDiscount()%>" name="AcqPurchaseOrderActionForm" styleClass="" />
                           </td></tr>
                       <tr><td  class="headerStyle" bgcolor="#E0E8F5" align="right">Shiping Cost</td>
                           <td>
                              <html:text styleId="shippingcost" readonly="true"   property="shiping_cost" value="<%=orderheader.getShipCost() %>"  name="AcqPurchaseOrderActionForm" styleClass="" />
                           </td></tr>
                       <tr><td  class="headerStyle" bgcolor="#E0E8F5" align="right">Other Cost</td>
                           <td>
                               <html:text styleId="othercost" readonly="true" property="other_cost" value="<%=orderheader.getShipCost() %>" name="AcqPurchaseOrderActionForm" styleClass="" />
                           </td></tr>
                       <tr><td  class="headerStyle" bgcolor="#E0E8F5" align="right">TAX/VAT Rate(in% Ex 5%)</td>
                           <td>
                              <html:text styleId="taxrate" readonly="true"  property="tax_rate"  value="<%=orderheader.getTaxRate() %>" name="AcqPurchaseOrderActionForm" styleClass="" />
                           </td></tr>
                       <tr><td  class="headerStyle" bgcolor="#E0E8F5" align="right">TAX/VAT Amt</td>
                           <td>
                               <html:text styleId="taxamount" readonly="true"  property="tax_amount"  value="<%=orderheader.getTaxAmount() %>"  name="AcqPurchaseOrderActionForm" styleClass="" />
                           </td></tr>
                       <tr><td  class="headerStyle" bgcolor="#E0E8F5" align="right">Grand Total</td>
                           <td>
                              <html:text styleId="grandtotal" readonly="true"  property="grand_total"  value="<%=orderheader.getGrandTotal() %>"  name="AcqPurchaseOrderActionForm" styleClass="" />
                           </td></tr>
                   </table>
 <tr>
             
     <td align="right" >
         <%if(button.equals("Cancel Order")){%>
         <input type="submit"   name="button" value="Cancel Order" class="txt1" />
         <%}%>
        
     </td>
                    <td><input align="left" type="button" name="button" value="Back" class="txt1" onclick="return quit();"/></td>

         
                   </tr>

       


             </table>


                <html:hidden property="library_id" name="AcqPurchaseOrderActionForm" value="<%=library_id%>" />
        
        <html:hidden property="sub_library_id" name="AcqPurchaseOrderActionForm" value="<%=sub_library_id%>" />
       
     
        </html:form>
 </body>
</html>
