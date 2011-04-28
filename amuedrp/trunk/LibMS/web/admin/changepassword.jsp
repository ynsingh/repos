<%@page import="java.sql.*,com.myapp.struts.hbm.*,com.myapp.struts.AdminDAO.*,java.util.*"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/adminheader.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
Login  rst=(Login)request.getAttribute("account_resultset");


String login_id=null;
String staff_id=null;


String user_name=null;



if(rst!=null)
    {

    staff_id=rst.getId().getStaffId();
    user_name=rst.getUserName();
   
    login_id=rst.getLoginId();
   
   
    }


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS : Change Password</title>
    </head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/newformat.css"/>
    <body>
      <html:form  action="/changepassword" method="post" onsubmit="return check();">
      <hr/>
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table  class="table" width="400px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Change Password</td></tr>
                <tr><td valign="top" align="center"> <br/>
                    <table width="400px" align="center">
                       
                        <tr><td class="txtStyle">Staff ID</td><td><input type="text" id="staff_id" name="staff_id"  readonly   value="<%=staff_id%>"></td></tr>
                        <tr><td class="txtStyle">User Name</td><td><input type="text" id="user_name" name="user_name"  readonly   value="<%=user_name%>"></td></tr>
                        
                        <tr><td class="txtStyle" >Login ID</td><td><input type="text" id="email_id" name="login_id" readonly     value="<%=login_id%>"/>


                            </td></tr>
                        
                        <tr><td class="txtStyle">Enter New Password</td><td>            <input type="password" id="password" onblur="check1();"  name="password"  value="">
                           <div align="left" id="searchResult" class="err" style="border:#000000; "></div>
                           </td></tr>
                   
                       <tr><td class="txtStyle">Re-enter New Password</td><td>            <input type="password" id="password1"  name="password1"  value=""></td></tr>
                       <tr><td class="txtStyle" align="center" colspan="2">
                       <br/>
                        <input type="submit" id="button" name="button" value="Submit" class="txt2" />
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
        check1();
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
                document.getElementById('password').value="";
                document.getElementById('password1').value="";
                document.getElementById('password').focus();
                return false;
            }
            else
                return true;


    }

function check1()
{
    KeyValue=document.getElementById('email_id').value;
        KeyValue1=document.getElementById('password').value;

        if(KeyValue==KeyValue1)
            {
               availableSelectList = document.getElementById("searchResult");

               availableSelectList.innerHTML="LoginId and Password Should not be same";
                document.getElementById('password').value="";

            }
else{
 availableSelectList = document.getElementById("searchResult");

               availableSelectList.innerHTML="";

}

}
      function send1()
    {
        <%
        if(rst.getRole().contains("admin")||rst.getRole().contains("Admin"))
            {
        %>
        window.location="<%=request.getContextPath()%>/checksystemsetup.do";
        <%}else{%>
            window.location="<%=request.getContextPath()%>/admin/main.jsp";<%}%>
        return true;

    }


    </script>

</html>
