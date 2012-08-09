<!--
NEW ARRIVAL JSP PAGE OPAC
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*,com.myapp.struts.hbm.*"%>
<jsp:include page="/admin/header.jsp" flush="true" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>



<script language="javascript">
  function fun()
 {
    alert("aqeel");
    document.getElementById("form1").action = "<%=request.getContextPath()%>/receive_item1.do"
    document.getElementById("form1").method="post";
    document.getElementById("form1").target="f1";
    document.getElementById("form1").submit();
 }

</script>

<script language="javascript">

function validate()
{
    var list =top.f1.document.f.list.value;
    var title =top.f2.document.f.title.value;
    var no_of_copies =top.f2.document.f.no_of_copies.value;
    var recieved_copies =top.f2.document.f.recieved_copies.value;
    var recieved_now =top.f2.document.f.recieved_now.value;
    var pending_copies =top.f2.document.f.pending_copies.value;
    var control_no =top.f2.document.f.control_no.value;
    var order_no =top.f2.document.f.order_no.value;
    var title_id =top.f2.document.f.title_id.value;
    var unit_price =top.f2.document.f.unit_price.value;
    var acq_mode =top.f2.document.f.acq_mode.value;
    var vendor_id =top.f2.document.f.vendor_id.value;

   
    document.getElementById('list').value=list;
    document.getElementById('no_of_copies').value=no_of_copies;
    document.getElementById('recieved_copies').value=recieved_copies;
    document.getElementById('recieved_now').value=recieved_now;
    document.getElementById('pending_copies').value=pending_copies;
    document.getElementById('control_no').value=control_no;
    document.getElementById('order_no1').value=order_no;
    document.getElementById('title_id').value=title_id;
    document.getElementById('unit_price').value=unit_price;
    document.getElementById('acq_mode').value=acq_mode;
    document.getElementById('vendor_id1').value=vendor_id;
   
    return true;
}

</script>


<%
List<AcqVendor> acqvendor=(List<AcqVendor>)request.getAttribute("acqvendor");
List<AcqOrderHeader> acqvendor1=(List<AcqOrderHeader>)request.getAttribute("acqvendor1");
String recieving_no=(String)request.getAttribute("recieving_no");

//System.out.println("aqeel1.jsp"+acqvendor.get(0).getVendor());
//System.out.println("aqeel2.jsp"+acqvendor1.get(0).getId().getOrderNo());
%>

</head>

<body  style="background-color:  #e0e8f5;" >
  <div
     style="  top:110px;
     left:5px;
     right:5px;
     position: absolute;
     visibility: show;">
<html:form  method="post" action="/receive_item" target="f1" styleId="form1">
          <table  align="center" width="90%" class="datagrid"  style="border:solid 1px black; "  class="txt">



  <tr class="header"><td  width="100%" height="25px"   align="center">Receive Item(s)</td></tr>
  <tr><td align="center" width="1200px" height="15px" >
          <table class="datagrid" >
              <tr><td>Supplier Name</td>
    <td width="200px">
        <html:select property="sname"  styleClass="selecthome"  styleId="sname" onchange="fun()">
      <html:option value="all">Supplier Name</html:option>
      <html:options collection="acqvendor"  property="id.vendorId" labelProperty="vendor"/>
    </html:select>

                  </td>


<td>Order No.</td>
<td>
    <html:select property="order_no" styleClass="selecthome"  styleId="order_no" onchange="fun()">
      <html:option value="onumber">Order No.</html:option>
      <html:options collection="acqvendor1" property="id.orderNo" labelProperty="id.orderNo"/>
    </html:select>

                  </td>

                  <td width="500px"  align="center">

                      <input type="checkbox"   id="checkbox" name="checkbox" onchange="fun()"> All Orders</td>
                  <td><input type="submit" class="buttonhome" name="b1" value="Go"  class="txt2"></td></tr></table>


      </td></tr>
  </html:form>

  <tr style="background-color:#e0e8f5;"><td align="left"  valign="top">
        <hr/>

          <IFRAME  style="margin:0px 0px 0px 0px" src="<%=request.getContextPath()%>/acquisition/acq_receive_item1.jsp"  style="background-color:#e0e8f5;"  frameborder=0 height="400px" width="100%" scrolling="no" name="f1" id="f1"></IFRAME>
        </td>
  </tr>
  <tr>
      <td align="center" width="1200px" height="15px" ><hr/>
          <html:form styleId="Form2"  action="/receive_item3.do" target="f2"  method="post" onsubmit="return validate();">
              <html:hidden property="list" styleId="list" />
              <html:hidden property="no_of_copies" styleId="no_of_copies" />
              <html:hidden property="recieved_copies" styleId="recieved_copies" />
              <html:hidden property="recieved_now" styleId="recieved_now" />
              <html:hidden property="pending_copies" styleId="pending_copies" />
              <html:hidden property="control_no" styleId="control_no" />
              <html:hidden property="title_id" styleId="title_id"/>
              <html:hidden property="order_no1" styleId="order_no1" />
              <html:hidden property="unit_price" styleId="unit_price" />
              <html:hidden property="acq_mode" styleId="acq_mode" />
              <html:hidden property="vendor_id1" styleId="vendor_id1" />

  <table class="datagrid" class="text" >
   <tr>
      <td align="left">Receiving No.</td>
      <td align="left" width="200px">
          <input type="text" name="recieving_no"  styleClass="selecthome"  id="recieving_no" readonly="true" value="<%=recieving_no%>"/>
      </td>
      <td>Received Date</td>
      <td width="200px">
           <input type="text" name="recieved_date" styleClass="selecthome"  id="recieved_date" />
      </td>
      <td>Received By</td>
      <td width="200px">
           <input type="text" name="recieved_by"  styleClass="selecthome"  id="recieving_no" />
      </td>
      
      <td><input type="submit" name="button" value="Submit" Class="txt1" > </td>
      
   </tr>

 </table>
 </html:form>

      </td>

  </tr>
  
<tr style="background-color:#e0e8f5;"><td   valign="top">
        <hr/>

             <IFRAME  style="margin:0px 0px 0px 0px;" src="<%=request.getContextPath()%>/acquisition/acq_receive_item2.jsp" style="background-color:#e0e8f5;"  frameborder=0 height="100px" width="100%" scrolling="no" name="f2" id="f2"></IFRAME>


      </td>


</tr>

          </table>

















</div>


    


</body>
</html>
