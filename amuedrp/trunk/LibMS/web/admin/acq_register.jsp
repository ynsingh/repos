<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <jsp:include page="header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
String library_id=(String)session.getAttribute("library_id");
String msg1=(String)request.getAttribute("msg1");

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Staff Login Page</title>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>

</head>
<body>
 
    <html:form method="post" onsubmit="return check1()" action="/staffRegistration">
       
<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table width="400px" height="500px"  valign="top" align="center">
        <tr><td   width="400px" height="400px" valign="top" style="" align="center">
                <fieldset style="border:solid 1px brown;height:200px;padding-left: 5px">
                    <legend><img src="/LibMS-Struts/images/StaffLogin.png"></legend>
                <table cellspacing="10px">

                    <tr><td rowspan="5" class="txt2">Enter Staff ID<br><br>
                        <input type="text" id="staff_id" name="staff_id" value=""/>
                        </td><td width="150px" align="center"> <input type="submit" class="btn" id="Button1" name="button" value="Register" /></td></tr>
                    <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Update"  /></td></tr>
 <tr><td width="150px" align="center"><input type="submit" id="Button3" name="button" value="View" class="btn"  /></td></tr>
 <tr><td width="150px" align="center"><input type="submit" id="Button4" name="button" value="Delete" class="btn" /></td></tr>
 <tr><td width="150px" align="center"><input type="button" id="Button5" name="button" value="Back" class="btn" onclick="return quit()"/></td></tr>
 

                </table>
       
</fieldset>

    <input type="hidden" name="library_id" value="<%=library_id%>">
   








</td></tr></table>
        </div>
    <div
   style="
      top: 650px;

      position: absolute;

      visibility: show;">
        <jsp:include page="footer.jsp" />

</div>
</html:form>

</body>
</html>
<script language="javascript" type="text/javascript">
   
    
function check1()
{
    if(document.getElementById('staff_id').value=="")
    {
        alert("Enter Staff Id...");
       
        document.getElementById('staff_id').focus();
       
        return false;
    }

  }

 


 
  function quit()
  {

      window.location="/LibMS-Struts/admin/main.jsp";
      return false;
  }


 
    </script>
      <%     if (msg1!=null){
 %>
 <script language="javascript">
 window.location="/LibMS-Struts/admin/acq_register.jsp";
 alert("<%=msg1%>");
 </script>
 <%
}

%>