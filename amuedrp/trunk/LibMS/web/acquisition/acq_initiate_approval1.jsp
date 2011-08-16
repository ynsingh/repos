
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>
<//jsp:include page="/admin/header.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<title> </title>
<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />

<% String approval_no=(String)request.getAttribute("approval_no") ;
   String msg=(String)request.getAttribute("msg");
%>

<script language="javascript">

function validate()
{
    var list =top.f1.document.f.list.value;
    alert ("List selected="+list);
  document.getElementById("list").value = list;
  if (isEmpty(list)!=true)
  {

    alert(list);
        return true;
  }
  else
     return false;
}

function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }


</script>
</head>
<body>

 <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">


     <form name="Form1" action="acq_initiate_approval2.do" method="post">
   <table width="700" border="0" cellspacing="4" cellpadding="1" valign="top" align="center" style="border:#cccccc solid 1px;">
    <tr><td colspan="4"  style="background-color:#cccccc;color:black;font-family:Tahoma;font-size:12px" height="25px" width="50px" align="center">



          <b>Approval List Management</b>





        </td></tr>

     <tr>
      <td width="150" align="right"><strong>Approval No: </strong> </td>
      <td><html:text property="approval_no" styleClass="textBoxWidth"/> </td>
      <td width="150" align="right"><strong>Date: </strong> </td>
      <td><input type="text" name="approval_date" styleClass="textBoxWidth" /> </td>
     </tr>
     <tr><td height="5px" colspan="4" ></td></tr>
     <tr>
      <td width="150" align="right"><strong>Recommended By: </strong> </td>
      <td><input type="text" name="recommended_by" styleClass="textBoxWidth" /> </td>
      <td width="150" align="right"><strong>Order Yr:</strong> </td>
      <td><input type="text" name="order_yr" styleClass="textBoxWidth" /> </td>
     </tr>
     <tr><td height="5px" colspan="4" ></td></tr>
     <tr>
     <td width="150" align="right"><strong>Approved By: </strong> </td>
     <td><input type="text" name="approving_authority" styleClass="textBoxWidth" /> </td>
     <td width="150" align="right"><strong>No of Titles: </strong> </td>
     <td><input type="text" name="serials" styleClass="textBoxWidth" /> </td>
     </tr>

 </table>



<IFRAME  name="f1"  src="acq_initiate_approval3.do" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:120px;left:310px;height:470px;width:1200px;visibility:true;" id="f1"></IFRAME>
<div align="center">
    <input type="hidden" name="list" id="list" value="" />
    <input type="submit" name="button" value="Submit" onclick="return validate();" />
    <input type="button" name="button" value="Back" onclick="return quit()"/>
</div>
</form>
</div>
</body>
<div
   style="
      top: 750px;

      position: absolute;

      visibility: show;">

<jsp:include page="/admin/footer.jsp"/>
  </div>
</html>