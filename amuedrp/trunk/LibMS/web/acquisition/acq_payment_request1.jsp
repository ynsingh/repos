<!--
NEW ARRIVAL JSP PAGE OPAC
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*,com.myapp.struts.hbm.*,com.myapp.struts.Acquisition.InvoiceList,com.myapp.struts.Acquisition.AllInvoiceList"%>
<jsp:include page="/admin/header.jsp" flush="true" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

<%
  List<AllInvoiceList> allinvoice=(List<AllInvoiceList>)session.getAttribute("allinvoice");
  String prn=(String)request.getAttribute("prn");
  
%>

<script language="javascript">
  function fun()
 {
    alert("aqeel");
    document.getElementById("form1").action = "<%=request.getContextPath()%>/acquisition/payment_request1.do"
    document.getElementById("form1").method="post";
    document.getElementById("form1").target="f1";
    document.getElementById("form1").submit();
 }

</script>

<script language="javascript">

function validate()
{
    var list =top.f1.document.f.list.value;
    document.getElementById('list').value=list;
    alert("testing testing testing");
    return true;
}

</script>




</head>

<body  style="background-color:  #e0e8f5;" >
  <div
     style="  top:110px;
     left:5px;
     right:5px;
     position: absolute;
     visibility: show;">
      <html:form  method="post" action="/payment_request2"  styleId="form1" onsubmit="return validate();">
  <table  align="center" width="90%"   class="datagrid"  style="border:solid 1px black;height: 400px "  class="txt">
      <tr class="header"><td  width="100%" height="25px"   align="center">Request   For   Payment</td></tr>
      <tr><td align="center" width="1200px" height="15px" >
          <table class="datagrid" >
              <tr><td>Search By Invoice No.</td>
                  <td width="200px">
                      <html:select property="invoice_no"  styleClass="selecthome"  styleId="invoice_no" onchange="fun()">
                        <html:option value="i_no">Invoice No.</html:option>
                        <html:options collection="allinvoice"  property="invoice_no" labelProperty="invoice_no"/>
                      </html:select>
                  </td>
                  <td>Search By  Order No.</td>
                  <td width="200px">
                     <html:select property="order_no" styleClass="selecthome"  styleId="order_no" onchange="fun()">
                       <html:option value="o_no">Order No.</html:option>
                       <html:options collection="allinvoice"  property="order_no"  labelProperty="order_no"/>
                     </html:select>
                  </td>
                  <td>Search By  Vendor Id</td>
                  <td width="200px">
                    <html:select property="vendor_id" styleClass="selecthome"  styleId="vendor_id" onchange="fun()">
                      <html:option value="v_id">Vendor Id</html:option>
                      <html:options collection="allinvoice"  property="vendor_id"  labelProperty="vendor_id"/>
                    </html:select>
                  </td>
               
              </tr>
          </table>
          </td>
      </tr>
      <tr style="background-color:#e0e8f5;">
          <td align="center"  valign="top"><hr/>
          <IFRAME  style="margin:0px 0px 0px 0px;" src="<%=request.getContextPath()%>/acquisition/acq_payment_request2.jsp"   style="background-color:#e0e8f5;"  frameborder=0 height="300px" width="100%" scrolling="no"  name="f1" id="f1"></IFRAME>
          </td>
      </tr>
      <tr>
          <td align="center" width="1200px" height="15px" ><hr/>
            <table class="datagrid" >
              <tr> <td align="left" wwidth="200px">Payment Request Number(PRN) </td>
                   <td><html:text property="prn" styleClass="textBoxWidth" value="<%=prn%>" readonly="true"/></td>
                   <td align="right" width="200px">Payment Request Date</td>
                   <td><html:text property="prn_date" styleClass="textBoxWidth" styleId="prn_date"/></td>
                   <td><input type="Submit" name="button" value="Submit" Class="txt1"/></td>
              </tr>
                       <html:hidden property="list" styleId="list"/>

            
            </table>
         </td>
     </tr>
  </table>
</html:form>
</div>
</body>
</html>
