<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="header.jsp" flush="true" />
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String staff_id=(String)request.getParameter("staff_id");
String first_name=(String)request.getParameter("first_name");
String last_name=(String)request.getParameter("last_name");
String email_id=(String)request.getParameter("email_id");
session.setAttribute("first_name", first_name);
session.setAttribute("last_name", last_name);
String login_role=(String)session.getAttribute("login_role");
%>
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
        <title>LibMS</title>
    </head>
    <body >
    

  <html:form  action="/caccount" method="post" onsubmit="return check1()">
      <table width="400px" height="600px"  valign="top" align="center" >
        <tr><td   width="400px" height="500px" valign="top" style="" align="center">
                <fieldset style="border:solid 1px brown;height:300px;width:300px;padding-left: 5px">
                    
                    <table width="500px" align="left">
                       
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="btn" width="250px">Staff ID</td><td width="250px"><input type="text" id="staff_id" name="staff_id" readonly  name="Editbox1" value="<%=staff_id%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="btn">User Name</td><td><input type="text" id="user_name" name="user_name"   readonly  value="<%=first_name%> <%=last_name%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="btn" >User/Login ID</td><td><input type="text" id="email_id" name="email_id"  readonly  name="Editbox2" value="<%=email_id%>"/> </td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                         <tr><td class="btn" width="250px">Select Role</td><td width="250px">
                                <html:select styleId="role" property="role">
                                    <%
                                    if(login_role.equals("insti-admin"))
                                    {%>

                                    <html:option value="Select">Select Role</html:option>
                                    <html:option value="staff">Staff</html:option>
                                    <html:option value="admin">Admin</html:option>


                                    <%}
                                    else if(login_role.equals("admin"))
                                    {%>
                                    <html:option value="Select">Select Role</html:option>
                                    <html:option value="staff">Staff</html:option>

                                    <%}%>



                                </html:select>
                                </td></tr>
                         <tr><td colspan="2" height="5px"></td></tr>
                 <!--  <tr><td class="btn">Password</td><td><input type="password" id="password"  name="password"    value=""></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="btn">Reenter Password</td><td><input type="password" id="password1"  name="password1"    value=""></td></tr>
                 -->
                        <tr><td colspan="2" align="center">
                                <br>
                                <br>
                         <input type="submit" id="Button1"  value="Submit" class="btn">
                         <input type="reset" id="Button2" value="Reset" onclick=" " class="btn">
                         <input type="button" id="Button3"  value="Back" onclick=" return quit()" class="btn">

                            </td></tr>

<input type="hidden" value="<%=first_name%>" name="first_name"/>
<input type="hidden" value="<%=last_name%>" name="last_name"/>
                    </table>





</fieldset>











</td></tr></table>
                        </html:form>
        </div>
   


    </body>
   

   <script language="javascript" type="text/javascript">
function check1()
{
   var role1=document.getElementById('role').options[document.getElementById('role').selectedIndex].value;
    if(role1=="Select")
        {
            alert("Select Role");
            document.getElementById('role').focus();
            return false;
        }



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
    function quit()
    {
       window.location="/LibMS-Struts/admin/main.jsp";
       return false;

    }

    </script>


</html>
