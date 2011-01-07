
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


    }

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS</title>
    </head>
    <script>
        function pcheck(str) {



return true;
}

        </script>
    <body>
      <html:form  action="/createaccount" method="post" onsubmit="return check();">
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
                    <legend><img src="/LibMS-Struts/images/StaffAccountLogin.png"/></legend><br><br>
                    <table width="400px" align="center">
                        <%
                        if(rst1!=null)
                            {
                            %>
                        <tr><td class="btn">Staff ID</td><td><input type="text" id="staff_id" name="staff_id"  readonly   value="<%=staff_id%>"></td></tr>
                        <tr><td class="btn">User Name</td><td><input type="text" id="user_name" name="user_name"  readonly   value="<%=user_name%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="btn" >Email/Login ID</td><td><input type="text" id="email_id" name="email_id" readonly     value="<%=email_id%>"/> </td></tr>
                        <tr><td colspan="2" height="5px" class="txt2"></td></tr>
                         
                          <%  if(button.equals("View Account")||button.equals("Delete Account"))
                        {

                        %>    
                    <tr><td class="txt2">Password</td><td>            <input type="text" id="password"  name="password" readonly  value="<%=password%>"></td></tr>
                            <%}
                            else
                               {%>
                             <tr><td class="txt2">Password</td><td>            <input type="password" id="password" readonly name="password"  value="<%=password%>"></td></tr>
                              <tr><td colspan="2" height="5px"></td></tr>
                             <tr><td class="txt2">Reenter Password</td><td>            <input type="text" id="password1"  name="password1"  value=""></td></tr>

                            <%}%>
                            


                         <tr><td colspan="2" height="5px"></td></tr>
                         <tr><td colspan="2" align="center"></td></tr>
                                <%
                                }
                             if(rst!=null)
                                 {
                                 %>
                        <tr><td class="txt2">Staff ID</td><td><input type="text" id="staff_id" name="staff_id"  readonly   value="<%=staff_id%>"></td></tr>
                         <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt2">User Name</td><td><input type="text" id="user_name" name="user_name" readonly   value="<%=first_name%><%=last_name%> "></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt2" >Email/Login ID</td><td><input type="text" id="email_id" name="email_id" readonly    value="<%=email_id%>"/> </td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                       <tr><td class="txt2">Password</td><td><input type="password" id="password"  name="password"  onblur="return search();"  value=""></td></tr>
                        <tr><td colspan="2" height="5px">
                       
                       <tr><td class="txt2">Reenter Password</td><td><input type="password" id="password1"  name="password1" onblur="return search1();" value=""></td></tr>

                       <tr><td colspan="2" height="5px"></td></tr>
                       <tr><td colspan="2" height="5px">
                       <div align="left" id="searchResult1" class="err" style="border:#000000; "></div></td></tr>
                       


                    </table>


                        <%}%>
                                <br>
                                <br>
                        <%
                        if(button.equals("View Account"))
                        {

                        %>

                        <input type="button" id="Button3" name="" value="Back" onclick=" return send1()" class="txt2"/>
                      <%
                        }
                         else if(button.equals("Change Password"))
                            {
                        %>
                        <input type="submit" id="Button1" name="button" value="<%=button%>" class="txt2" />
                         <input type="button" id="Button3" name="" value="Back" onclick=" return send1()" class="txt2"/>
                        <%
                         }
                        else if(button.equals("Delete Account"))
                        {%>
                         <input type="submit" id="Button1" name="button" value="<%=button%>" class="txt2" onclick="return confirm1()"/>

                         <input type="button" id="Button3" name="" value="Back" onclick=" return send1()" class="txt2"/>

                           <%}
                           else
                            {%>
                            <input type="submit" id="Button1" name="button" value="<%=button%>" class="txt2" />

                         <input type="button" id="Button3" name="" value="Back" onclick=" return send1()" class="txt2"/>
                       <%}%>

                           



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
    function search()
    {
     availableSelectList = document.getElementById("searchResult1");
  availableSelectList.innerHTML = "";
    var keyValue = document.getElementById("password").value;



    if (pcheck(keyValue)==false)
    {
		password.value="";
		password.focus();
		return false;
	}
        else
            return true;
    }
      function search1()
    {
     availableSelectList = document.getElementById("searchResult1");
  availableSelectList.innerHTML = "";
    var keyValue = document.getElementById("password1").value;



    if (pcheck(keyValue)==false)
    {
		password1.value="";
		password1.focus();
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
