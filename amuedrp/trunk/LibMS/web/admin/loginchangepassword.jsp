<%@page import="java.sql.*,com.myapp.struts.hbm.*,com.myapp.struts.AdminDAO.*,java.util.*"%>


 <jsp:include page="/admin/header.jsp" flush="true" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%



String login_id=null;
String staff_id=null;
String library_id=null;
String user_name=null;
    staff_id=(String)session.getAttribute("staff_id");
    library_id=(String)session.getAttribute("library_id");
    user_name=(String)session.getAttribute("username");
    login_id=(String)session.getAttribute("login_id");
 
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS : Change Password</title>
    </head>
 
    <body>
      <html:form  action="/admin/password" method="post" onsubmit="return check();">
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Change Password</td></tr>
                <tr><td valign="top" align="center"> <br/>
                      <table width="400px" align="center">
                       
                        <tr><td class="btn">Staff ID</td><td><input type="text" id="staff_id" name="staff_id"  readonly   value="<%=staff_id%>"></td></tr>
                        <tr><td class="btn">User Name</td><td><input type="text" id="user_name" name="user_name"  readonly   value="<%=user_name%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="btn" >Email/Login ID</td><td><input type="text" id="email_id" name="email_id" readonly     value="<%=login_id%>"/> </td></tr>
                        <tr><td colspan="2" height="5px" class="txt2"></td></tr>
                       <tr><td class="btn">Enter New Password</td><td>            <input type="password" id="password"  name="password"  value=""></td></tr>
                       <tr><td colspan="2" height="5px"></td></tr>
                       <tr><td class="btn">Reenter New Password</td><td>            <input type="password" id="password1"  name="password1"  value=""></td></tr>
                       <tr><td colspan="2" align="center">
                        <input type="submit" id="button" name="button" value="submit" class="txt2" />
                         <input type="button" id="button" name="" value="Skip" onclick=" return send1()" class="txt2"/>
                           </td></tr>
               
                    </table>

                           








</td></tr>

    </table>
        </div>
   

</html:form>
    </body>

<script language="javascript" type="text/javascript">

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
        window.location="<%=request.getContextPath()%>/admin/main.jsp";
        return false;

    }


    </script>

</html>
