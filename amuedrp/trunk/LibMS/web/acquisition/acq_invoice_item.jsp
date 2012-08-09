<!--
NEW ARRIVAL JSP PAGE OPAC
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*,com.myapp.struts.hbm.*,com.myapp.struts.Acquisition.InvoiceList"%>
<jsp:include page="/admin/header.jsp" flush="true" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>



<script language="javascript">
  function fun()
 {
    document.getElementById("form1").action = "<%=request.getContextPath()%>/invoice_item1.do"
    document.getElementById("form1").method="post";
    document.getElementById("form1").target="f1";
    document.getElementById("form1").submit();
 }

</script>

<script language="javascript">

function validate()
{   var list2,discount,net_amt;
    list2 =top.f1.document.f.list.value;
    document.getElementById('list').value=list2;
    discount =top.f2.document.f.discount.value;
    net_amt =top.f2.document.f.net_amt.value;
    document.getElementById('discount').value=discount;
    document.getElementById('net_amt').value=net_amt;

    return true;
}

</script>


<%
List<InvoiceList> listforinvoice=(List<InvoiceList>)session.getAttribute("listforinvoice");
List<InvoiceList> l=(List<InvoiceList>)request.getAttribute("l");
String invoice_no=(String)request.getAttribute("invoice_no");
String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("msg1");
%>

</head>

<body  style="background-color:  #e0e8f5;" >
  <div
     style="  top:110px;
     left:5px;
     right:5px;
     position: absolute;
     visibility: show;">
<html:form  method="post" action="/acquisition/invoice_item" target="f1" styleId="form1">
          <table  align="center" width="90%"   class="datagrid"  style="border:solid 1px black;height: 400px "  class="txt">



  <tr class="header"><td  width="100%" height="25px"   align="center">Invoice Item(s)</td></tr>
  <tr><td align="center" width="1200px" height="15px" >
          <table class="datagrid" >
              <tr><td>Search By Receiving No.</td>
    <td width="200px">
        <html:select property="receiving_no"  styleClass="selecthome"  styleId="receiving_no" onchange="fun()">
      <html:option value="all">Receiving No.</html:option>
      <html:options collection="listforinvoice"  property="recieving_no" labelProperty="recieving_no"/>
    </html:select>

                  </td>


<td>Order No.</td>
<td>
    <html:select property="order_no" styleClass="selecthome"  styleId="order_no" onchange="fun()">
      <html:option value="onumber">Order No.</html:option>
      <html:options collection="l"  property="order_no"  labelProperty="order_no"/>
    </html:select>

                  </td>

                  <%--<td width="500px"  align="center">

                      <input type="checkbox"   id="checkbox" name="checkbox" onchange="fun()"> All Orders</td>--%>
                  <td><input type="submit" class="buttonhome" name="b1" value="Go"  class="txt2"></td></tr></table>


      </td></tr>
  </html:form>

  <tr style="background-color:#e0e8f5;"><td align="center"  valign="top">
        <hr/>

          <IFRAME  style="margin:0px 0px 0px 0px;" src="<%=request.getContextPath()%>/acquisition/acq_invoice_item1.jsp"   style="background-color:#e0e8f5;"  frameborder=0 height="400px" width="100%" scrolling="no"  name="f1" id="f1"></IFRAME>
        </td>
  </tr>


  <tr>
      <td align="center" width="1200px" height="15px" >
   <html:form styleId="Form2"  action="/invoice_item3.do"   method="post" onsubmit="return validate();">



  <table class="datagrid" class="text" >
   <tr>
      <td align="left">Invoice No.</td>
      <td align="left" width="200px">
          <input type="text" name="invoice_no"  styleClass="selecthome"  id="invoice_no" value="<%=invoice_no%>" readonly="true" />
      </td>
      
      <td>Invoicing Date</td>
      <td width="200px">
           <input type="text" name="invoicing_date" styleClass="selecthome"  id="invoicing_date" />
      </td>
            <td><input type="submit" name="button" value="Submit" Class="txt1" > </td>
   </tr>
   

 </table>
     <html:hidden property="discount" styleId="discount" />
     <html:hidden property="net_amt" styleId="net_amt" />
     <html:hidden property="list" styleId="list" />
 </html:form>

      </td>

  </tr>

<tr style="background-color:#e0e8f5;"><td   valign="top">
        <hr/>

             <IFRAME  style="margin:0px 0px 0px 0px;" src="<%=request.getContextPath()%>/acquisition/acq_invoice_item2.jsp" style="background-color:#e0e8f5;"  frameborder=0 height="110px" width="100%" scrolling="no" name="f2" id="f2"></IFRAME>


      </td>


</tr>

          </table>


</div>

</body>

<script language="javascript">
  function hello(string){
   var name=string;
  // alert("aaaaaaaaaa"+name);
   <%--var d=document.getElementById('dis').value;
   if(d=="")
     document.getElementById('dis').value=name;
   else
     document.getElementById('dis').value=document.getElementById('dis').value+","+name;
--%>
   if(top.f1.document.f.disc.value=="")
    {
        top.f1.document.f.disc.value=name;
        
    }
   else
       top.f1.document.f.disc.value=name;


   }


  <%-- function hello1(string){
   var name=string;
   top.f2.document.f.sizearray.value=name;
   alert("aqeelaqeel");
   }--%>
  </script>
</html>
