<%-- 
    Document   : manage_superadmin
    Created on : Jan 12, 2011, 7:34:19 PM
    Author     : System Administrator
--%>
<%@page import="java.util.*,java.io.*,java.net.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link rel="Stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<%
   String role=(String)session.getAttribute("login_role");
   if(role.equalsIgnoreCase("Election Manager") || role.equalsIgnoreCase("Election Manager,voter")){
   %>
   <jsp:include page="/election_manager/login.jsp"/>
   <%} else if(role.equalsIgnoreCase("insti-admin") || role.equalsIgnoreCase("insti-admin,voter")){%>
   <jsp:include page="/institute_admin/adminheader.jsp"/>
<%}%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <%
try{
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }
String contextPath = request.getContextPath();
String user=(String)session.getAttribute("user_id");
%>
        <script language="javascript" type="text/javascript">
               function check()
    {

        var x=document.getElementById('user_id1');
        if(x.value=="")
            {
                alert("User ID should not be blank");
                 document.getElementById('user_id1').focus();
                return false;
            }
     if(document.getElementById('password').value=="")
            {
                alert("Password should not be blank");
                 document.getElementById('password').focus();
                return false;
            }

                return true;


    }

function clearme()
{
   
   
    document.getElementById("user_id1").value = "";
    
    return true;
}
            </script>
    </head>
    <body>
        <html:form styleId="form1"  action="/reset_password.do" method="post">
            <table  align="<%=align%>" dir="<%=rtl%>" width="500px" height="150px" style="background-color: white;border:#C7C5B2 1px solid;margin:0px 0px 0px 0px;">
                <tr><td dir="<%=rtl%>" style="background-color: #7697BC;color:white;" colspan="2" class="btn1" height="30px"><b>Reset Password </b> </td></tr>
                <tr><td width="50%" class="btn3" dir="<%=rtl%>" align="right">User Id</td><td><html:text styleId="user_id1" style="width: 200px" property="user_id1" value=""/></td></tr>
           
           <tr><td width="50%" class="btn3" dir="<%=rtl%>" align="right">Password</td><td><html:password styleId="password" style="width: 200px" property="password1" value=""/></td></tr>

           
               
                <tr><td class="btn3" dir="<%=rtl%>" align="<%=align%>" colspan="2">
                <br>
   <%
   String   message="";
     message =(String) request.getAttribute("msg");
    if(message!=null) {%>
               <%=message%>
               
            
          
          
         
        
        
                
   <% }
     String clear="clear";
    %>

 <%
   message="";
     message =(String) request.getAttribute("msg1");
    if(message!=null) {%>
               <%=message%>








   <% }else
     clear="clear";
    %>



            </td></tr>
           <tr><td><html:submit styleClass="btn2" style="float: right" onclick="return check();"> Reset Password</html:submit></td><td dir="<%=rtl%>" width="300px">
                   <input type="button" onclick="return clearme();"   class="btn2" dir="<%=rtl%>" value="<%=resource.getString(clear)%>" />
            </td></tr>
        <tr><td></td><td  align="<%=align%>" width="200px">
                  
                <br><br></td></tr>
    </table>
        </html:form>
    </body>
</html>
