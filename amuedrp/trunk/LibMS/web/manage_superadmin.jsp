<%-- 
    Document   : manage_superadmin
    Created on : Jan 12, 2011, 7:34:19 PM
    Author     : System Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link rel="Stylesheet" href="/LibMS-Struts/css/page.css"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script language="javascript" type="text/javascript">
               function check()
    {

        var x=document.getElementById('user_id1');
        if(x.value=="")
            {
                alert("Old Login ID should not be blank");
                 document.getElementById('user_id1').focus();
                return false;
            }
            var x=document.getElementById('user_id2');
        if(x.value=="")
            {
                alert("New Login ID should not be blank");
                 document.getElementById('user_id2').focus();
                return false;
            }




        var x=document.getElementById('password1');
        if(x.value=="")
            {
                alert("Existing Password should not be blank");
                 document.getElementById('password1').focus();
                return false;
            }
     if(document.getElementById('password2').value=="")
    {
        alert("Enter New password...");

        document.getElementById('password2').focus();

        return false;
    }
     if(document.getElementById('password3').value=="")
    {
        alert("Enter Re-enter password...");

        document.getElementById('password3').focus();

        return false;
    }
      var x1=document.getElementById('password2');
        var x2=document.getElementById('password3');
        if(x1.value!=x2.value)
            {
                alert("password mismatch");
                document.getElementById('password3').focus();
                return false;
            }
            else
                return true;




                return true;


    }


            </script>
    </head>
    <body>
        <html:form  action="/manage_superadmin.do" method="post">
           <table  align="left" width="500px" height="150px" style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">
           <tr><td style="background-color: #c0003b;color:white;" colspan="2" class="btn1" height="30px"><b>Super Admin Account</b> </td></tr>
           <tr><td width="100px" class="btn3" align="left">Old Login ID</td><td><html:text styleId="user_id1" property="user_id1"/></td></tr>
           <tr><td width="100px" class="btn3" align="left">New Login ID</td><td><html:text styleId="user_id2" property="user_id2"/></td></tr>
        <tr><td class="btn3" align="left">Old Password</td><td><html:password styleId="password1" property="password1"/>
                 <tr><td class="btn3" align="left">New Password</td><td><html:password styleId="password2" property="password2"/>
                          </td></tr>
                <tr><td class="btn3" align="left">Re-enter Password</td><td><input type="password" id="password3"/>
                <br>
   <%
   String   message="";
     message =(String) request.getAttribute("msg");
    if(message!=null) {%>
            <font size="2px" align=center color=red><b><%=message%></b></font>
            <script type="text/javascript" language="javascript">
                alert("Please Re-Login to Continue with New Login ID");
                parent.location.href="/LibMS-Struts/login.jsp";
                </script>

   <% }else
        message="";
    %>




            </td></tr>
                <tr><td></td><td width="300px"><br><html:submit styleClass="txt2" onclick="return check();"> Change Password</html:submit>
                        <html:reset  styleClass="btn2" value="Clear"  />
            </td></tr>
        <tr><td></td><td  align="left" width="200px">
                  
                <br><br></td></tr>
    </table>
        </html:form>
    </body>
</html>
