
<%@page import="java.sql.*;"%>
 <jsp:include page="adminheader.jsp" flush="true" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
ResultSet  rst=(ResultSet)request.getAttribute("account_resultset");


String email_id=null;
String staff_id=null;


String user_name=null;
String library_id=(String)session.getAttribute("library_id");
String password=null;

if(rst!=null)
    {

    staff_id=rst.getString("staff_id");
    user_name=rst.getString("user_name");
   
    email_id=rst.getString("user_id");
    password=rst.getString("password");
   
    }


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS</title>
    </head>
    <script>
        

        </script>
    <body>
      <html:form  action="/changepassword" method="post" onsubmit="return check();">
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table width="400px" height="600px"  valign="top" align="center">
        <tr><td   width="400px" height="500px" valign="top" style="" align="center">
                <br><br>
                <fieldset style="border:solid 1px brown;height:300px;width:300px;padding-left: 5px">
                    <legend><img src="/LibMS-Struts/images/ChangePassword.png"/></legend><br><br>
                    <table width="400px" align="center">
                        <%
                        if(rst!=null)
                            {
                            %>
                        <tr><td class="btn">Staff ID</td><td><input type="text" id="staff_id" name="staff_id"  readonly   value="<%=staff_id%>"></td></tr>
                        <tr><td class="btn">User Name</td><td><input type="text" id="user_name" name="user_name"  readonly   value="<%=user_name%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="btn" >Email/Login ID</td><td><input type="text" id="email_id" name="email_id" readonly     value="<%=email_id%>"/> </td></tr>
                        <tr><td colspan="2" height="5px" class="txt2"></td></tr>
                       <tr><td class="btn">Password</td><td>            <input type="password" id="password"  name="password"  value=""></td></tr>
                       <tr><td colspan="2" height="5px"></td></tr>
                       <tr><td class="btn">Reenter Password</td><td>            <input type="password" id="password1"  name="password1"  value=""></td></tr>
                       <tr><td colspan="2" align="center">
                        <input type="submit" id="button" name="button" value="submit" class="txt2" />
                         <input type="button" id="button" name="" value="Skip" onclick=" return send1()" class="txt2"/>
                           </td></tr>
                <%}    %>
                    </table>

                           



</fieldset>











</td></tr>

    </table>
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

<script>

    function check()
    {
        var x=document.getElementById('password');
        if(x.value=="")
            {
                alert("Password should not be blank");
                return false;
            }
     if(document.getElementById('password1').value=="")
    {
        alert("Enter Reenter password...");

        document.getElementById('password1').focus();

        return false;
    }
          var x1=document.getElementById('password');
        var x2=document.getElementById('password1');
        if(x1.value!=x2.value)
            {
                alert("password mismatch");
                document.getElementById('password1').focus();
                return false;
            }
            else
                return true;


    }
 
   
      function send1()
    {
        window.location="/LibMS-Struts/admin/main.jsp";
        return false;

    }
 
   
    </script>
































