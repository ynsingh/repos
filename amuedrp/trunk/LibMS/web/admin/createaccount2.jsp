
<%@page import="java.sql.*;"%>
 <jsp:include page="header.jsp" flush="true" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
ResultSet  rst=(ResultSet)request.getAttribute("account_resultset");
ResultSet  rst1=(ResultSet)request.getAttribute("account_resultset1");
String button=(String)request.getAttribute("button");
String email_id=null;
String staff_id=null;
String first_name=null;
String last_name=null;
String user_name=null;
String role=null;
String library_id=(String)session.getAttribute("library_id");
String password=null;

if(rst!=null)
    {

    staff_id=rst.getString(1);
    first_name=rst.getString(2);
    last_name=rst.getString(3);
    email_id=rst.getString(4);
  
    }
if(rst1!=null)
    {
    staff_id=rst1.getString(1);
    email_id=rst1.getString(2);
    user_name=rst1.getString(3);
    password=rst1.getString(4);
    role=rst1.getString("role");

    }

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS</title>
    </head>
    <body>
      <html:form  action="/updateaccount" method="post" onsubmit="return check();">
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table width="500px" height="600px"  valign="top" align="center">
        <tr><td   width="500px" height="500px" valign="top" style="" align="center">
                <fieldset style="border:solid 1px brown;height:300px;width:400px;padding-left: 5px;padding-right: 5px">
                    <legend><img src="images/StaffAccountLogin.png"/></legend>
                    <br><br><br>
                    <table width="400px" align="center">
                        <%
                        if(rst1!=null)
                            {
                            %>
                      
                        <tr><td colspan="2" height="5px"></td></tr>

                        <tr><td class="btn">Staff ID</td><td><input type="text" id="staff_id" name="staff_id"  readonly   value="<%=staff_id%>"></td></tr>
                         <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="btn">User Name</td><td><input type="text" id="user_name" name="user_name"  readonly   value="<%=user_name%>"></td></tr>
                        <tr><td colspan="2" height="5px" width="300px"></td></tr>
                        <tr><td class="btn" >Email/Login ID</td><td><input type="text" id="email_id" name="email_id" readonly     value="<%=email_id%>"/> </td></tr>
                        <tr><td colspan="2" height="5px" align="center"></td></tr>
                        <tr><td class="btn" width="250px">Select Role</td><td width="250px">

                                 <%  if(button.equals("View Account")||button.equals("Delete Account"))
                                 {
                                %>
                                <input type="hidden" name="role" value="<%=role%>"/>
                            <select id="role" size="1" name="role" disabled>
                                      <%  }else{%>
                                        <select id="role" size="1" name="role" >
                                      <%}%>
                               <%if(role.equals("admin")){%>
                                    <option value="Select">Select Role</option>
                                    <option selected value="admin">Admin</option>
                                    <option value="staff">Staff</option>
                                    <%}else if(role.equals("staff")){%>
                                    <option value="Select">Select Role</option>
                                    <option value="admin">Admin</option>
                                    <option selected value="staff">Staff</option>
                                    <%}else{%>
                                    <option value="Select">Select Role</option>
                                    <option selected value="insti-admin">Institute Admin</option>
                                    <option value="staff">staff</option>
                                    <option value="admin">Admin</option>
                                    <%}%>

                                </select>
                         </td></tr>
                             <tr><td colspan="2" height="5px" align="center"></td></tr>
                         
                          <%  if(button.equals("View Account")||button.equals("Delete Account"))
                        {

                        %>    
                    <tr><td class="btn">Password</td><td>            <input type="password" id="password"  name="password" readonly  value="<%=password%>"></td></tr>
                            <%}
                            else
                               {%>
                    <tr><td class="btn">Password</td><td>            <input type="password" id="password"  name="password" readonly  value="<%=password%>"></td></tr>
                              

                            <%}%>
                            


                         <tr><td colspan="2" height="5px"></td></tr>

                         <tr><td colspan="2" align="center">
                                <%
                                }
                             if(rst!=null)
                                 {
                                 %>
                        <tr><td class="btn">Staff ID</td><td><input type="text" id="staff_id" name="staff_id"  readonly   value="<%=staff_id%>"></td></tr>
                        <tr><td class="btn">User Name</td><td><input type="text" id="user_name" name="user_name" readonly   value="<%=first_name%><%=last_name%> "></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="btn" >Email/Login ID</td><td><input type="text" id="email_id" name="email_id" readonly    value="<%=email_id%>"/> </td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                       <tr><td class="btn">Password</td><td><input type="text" id="password"  name="password"    value=""></td></tr>
                       <tr><td class="btn">Reenter Password</td><td><input type="text" id="password1"  name="password1"  value=""></td></tr>

                       <tr><td colspan="2" height="5px"></td></tr>
                         <tr><td colspan="2" height="20px"></td></tr>
                       <tr><td colspan="2" align="center" width="">

                        <%}%>
                        <br><br>
                        <%
                        if(button.equals("View Account"))
                        {

                        %>
                        
                        <input type="button" id="Button3" name="" value="Back" onclick=" return send1()" class="txt2"/>
                      <%
                        }
                         else if(button.equals("Update Account"))
                            {
                        %>
                        <input type="submit" id="Button1" name="button" value="<%=button%>" class="txt2"/>
                         <input type="button" id="Button3" name="" value="Back" onclick=" return send1()" class="txt2"/>
                        <%
                         }
                        else if(button.equals("Delete Account"))
                        {%>
                         <input type="submit" id="Button1" name="button" value="Confirm" class="txt2" onclick="return confirm1()"/>

                         <input type="button" id="Button3" name="" value="Back" onclick=" return send1()" class="txt2"/>
                       
                           <%}
                           else
                            {%>
                            <input type="submit" id="Button1" name="button" value="<%=button%>" class="txt2" />

                         <input type="button" id="Button3" name="" value="Back" onclick=" return send1()" class="txt2"/>
                       <%}%>
                         
                            </td></tr>


                    </table>





</fieldset>











</td></tr></table>
        </div>
   
</html:form>
        <script language="javascript" type="text/javascript">

    function check()
    {
        var role=document.getElementById('role').options[document.getElementById('role').selectedIndex].value;

    if(role=="Select")
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

      function send1()
    {
        window.location="/LibMS-Struts/admin/account.jsp";
        return false;

    }

    function confirm1()
{
   var answer = confirm ("Do you want to Delete Record?")
if (answer!=true)
    {
        document.getElementById('Button1').focus();
        return false;
    }
    else
        return true;


}
    </script>


    </body>
</html>

