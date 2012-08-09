<%--
    Document   : cat_update_titles
    Created on : Jan 12, 2011, 10:49:28 AM
    Author     : Asif Iqubal
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*,com.myapp.struts.Acquisition.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LibMS:Acquisition Module</title>
             <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
<%
  String msg=(String)request.getAttribute("msg");
  String msg1=(String)request.getAttribute("msg1");
  List acqforpaymentupdate=(List)session.getAttribute("acqforpaymentupdate");
%>

 <script type="text/javascript">
  function send()
  {
    top.location="<%=request.getContextPath()%>/acquisition/acq_payment_update.jsp";
    return false;
  }
 </script>

   </head>
   <jsp:include page="/admin/header.jsp"/>
    <body>
        <html:form method="post" action="/invoice_payment_update1" styleId="f"  style="position:absolute; left:30%; top:30%">
            <table border="1" class="table" width="400">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>Payment Update</b></td></tr>

                <tr><td>
                <table border="0" cellspacing="4" cellpadding="1" align="center">
                    <tr><td><br><br></td></tr>
                    <tr>

                       <td rowspan="8" width="100">
               <strong>Select PRN:<a class="star">*</a></strong><br>

                 <html:select property="prn" styleClass="selecthome"   styleId="prn">
                   <html:option value="all">Select</html:option>
                   <html:options collection="acqforpaymentupdate" property="prn"/>
                 </html:select><br>
                 
                       </td></tr>
                   
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="Process" Class="txt1" onclick="return check();"/></td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="Processed List" Class="txt1" /></td></tr>
                    <tr><td width="40"></td><td><input type="button" name="button" value="Back" Class="txt1" onclick="return send();"/></td></tr>
                        <tr><td height="20px;"></td></tr>
                    <tr><td colspan="2" align="center"><br><br></td></tr>
                    <tr><td colspan="2">

                        </td> </tr>

                </table>
                    </td></tr>
                <tr><td class="mess">
                  <%
                    if (msg!=null)
                    {

                  %>
                  <p class="mess">  <%=msg%></p>

                 <%
                    }
                 %>
                 <%if (msg1!=null)
                   {
                 %>
                  <p class="err">  <%=msg1%></p>
                 <%
                   }
                 %>

                    </td></tr>
               <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>PRN -> Payment Request Number</b></td></tr>
            </table>
    </html:form>
 </body>
<script language="javascript">
function check()
{

  var keyValue = document.getElementById('prn').options[document.getElementById('prn').selectedIndex].value;
   if (keyValue=="all")
    {

        alert("Select PRN");

        document.getElementById('prn').focus();

        return false;
    }
}



</script>

</html>
