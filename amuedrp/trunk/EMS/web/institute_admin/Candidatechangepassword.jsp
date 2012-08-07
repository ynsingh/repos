<%-- 
    Document   : institute_admin_details
    Created on : Mar 12, 2011, 4:26:08 PM
    Author     : Edrp-04
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>

<%@page pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<%@page import="com.myapp.struts.hbm.*,java.util.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>




<%

Login user=new Login();
user = (Login)session.getAttribute("resultset1");

%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EMS</title>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
%>

<%
try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
    <script>
function quit()
   {
      window.location="/EMS/login.do"


   }
function  checkPassword()
    {

       var pass = document.getElementById("pass").value;

       var pass1=document.getElementById("pass1").value;
      // if(pass=="")
          // alert("Please enter pasword");
        if(pass==pass1){

            return true;}
        else{
            if(pass1==" " || pass1== "")
             {
                 document.getElementById("repasswordErr").innerHTML="<p><%=resource.getString("please_reenter_password")%></p>";
            document.getElementById("pass1").value="";
            document.getElementById("pass1").focus();
            return false;}
        else{
        document.getElementById("repasswordErr").innerHTML="<p><%=resource.getString("password_mismatch")%></p>";
            document.getElementById("pass1").value="";
            document.getElementById("pass1").focus();
            return false;}
        }
    }


    function validation()
    {


  var password=document.getElementById('pass');



var str="";




       if(password.value=="")
       {  str+="\n <%=resource.getString("password_required")%> ";
       alert(str);
            document.getElementById('pass').focus();
            return false;
       }



if(str=="")
    return true;





    }


</script>
<link rel="stylesheet" href="/EMS/css/page.css"/>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
 .btn
    {
font-family:Tahoma;
    font-size:13px;
    letter-spacing:1px;
     padding-left:10px;
     color:brown;
     text-align:center;
     width:100px;


     font-weight:bold;
    }
.txt
{
font-family: Arial,Helvetica,sans-serif;
font-size:15px;
letter-spacing:1px;
color:brown;
font-weight:bold;
}
</style>
</head>
<body>
    <html:form  method="post" action="/changeadminpassword" onsubmit="return validation();"  >



            

<%
  
  
  
     String user_id = user.getUserId();
     %>
        
            <table width="70%" class="txt" align="center" style="font-size:13px" dir="<%=rtl%>">
                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("userid")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="user_id" value="<%=user_id%>" readonly="true" /></td>
                </tr>
                <tr>

                    <td dir="<%=rtl%>">
                    <%=resource.getString("login.managesuperadminaccount.newpassword")%>
                    </td>
                    <%-- <td dir="<%=rtl%>"><input type="password" name="password"> </td>--%>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:password property="password" styleId="pass" name="AdminViewActionForm"/></td>
                     
                </tr>
                <tr>
                    <td dir="<%=rtl%>">
                       <%=resource.getString("login.managesuperadminaccount.repassword")%> 
                    </td>
                    <td dir="<%=rtl%>">
                        <input type="password" name="repassword" id="pass1">
                    </td>
                    <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>">  <div id="repasswordErr"></div>
             </td>
                </tr>
                <tr>
                     <td ></td>
                     <td><input type="submit" value="<%=resource.getString("login.managesuperadminaccount.changepassword")%>" class="txt2" onclick="return checkPassword();"></td>

                </tr>
            </table>
     





    </html:form>

</body>


 
</html>




