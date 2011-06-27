<%-- 
    Document   : error
    Created on : Nov 20, 2010, 3:15:27 PM
    Author     : System Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<jsp:include page="admin/header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);


%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LibMS</title>
    </head>
    <body>
        <p class="err"><%=resource.getString("admin.error.error")%></p>
    </body>
</html>
