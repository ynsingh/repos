<%-- 
    Document   : institute_admin_details
    Created on : Mar 12, 2011, 4:26:08 PM
    Author     : Edrp-04
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>

<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.admin.AdminReg_Institute,com.myapp.struts.hbm.*,java.util.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
try{
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));

%>

<%
String role=(String)session.getAttribute("login_role");
if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
%>
<jsp:include page="/institute_admin/adminheader.jsp"/>
<%}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Institute_Admin_Account</title>

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
String user_id=(String)session.getAttribute("user_id");
    %>
<link rel="stylesheet" href="/EMS/css/page.css"/>

</head>
<body>
    <html:form  method="post" action="/changeadminpassword" onsubmit="return validation();"  >


<div id="main" style="visibility: visible; top:-50; overflow: auto" >
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
                     <td><input type="submit" value="<%=resource.getString("login.managesuperadminaccount.changepassword")%>" class="txt2" onclick="return checkPassword();"><input type="button" onclick="quit();" class="txt2" value="<%=resource.getString("cancel")%>" ></td>

                </tr>
            </table>
        </div>






    </html:form>

</body>


 



<script>
    function quit()
    {
        location.href="<%=request.getContextPath()%>/institute_admin/institute_admin_home.jsp";
    }

</script>
<script type="text/javascript">



    function validation()
    {

   
  var password=document.getElementById('pass');

  var password1=document.getElementById('pass1');

var str="";




       if(password.value=="")
       {  str+="\n PLease Enter Password ";
       alert(str);
            document.getElementById('pass').focus();
            return false;
       }
    if(password1.value=="")
       {  str+="\n PLease Enter Password ";
       alert(str);
            document.getElementById('pass1').focus();
            return false;
       }
       if(password.value!=password1.value)
       {  str+="\n Password mismatch ";
       alert(str);
            document.getElementById('pass').focus();
            return false;
       }
    
if(str=="")
    return true;





    }



</script>

</html>
<%
String msg1=(String)request.getAttribute("msg");

if(msg1!=null)
    {
    %>
    <script>
        alert("<%=msg1%>");
        location.href = "<%=request.getContextPath()%>"+"/login.jsp"
    </script>
<%}%>



<%
}
else{%>
<%
String msg1=(String)request.getAttribute("msg");

if(msg1!=null)
    {
    %>
    <script>
        alert("<%=msg1%>");
        location.href = "<%=request.getContextPath()%>"+"/login.jsp"
    </script>
<%}%><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

%>