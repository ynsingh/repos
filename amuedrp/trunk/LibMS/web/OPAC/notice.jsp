<%--
    Document   : Notice.jsp
    Created on : Jun 6, 2010, 4:15:37 PM
    Author     : Mayank Saxena
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ page import="java.sql.*" %>
<%@ page contentType="text/html"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>NOTICE...</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
%>
<%
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>



</head>
<body style="width:150px">
    <%if(page.equals(true)){%>
  <div id="IframeScroller" style="position:absolute; left:200px; top: 200px; width:150px;">
<iframe id="NewsWindow" src="scroll.jsp" width="50px" height="250" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" style="border: #000000 1px solid;"></iframe>
</div>
<font size="3" color="teal" align="center">

</font>
    <%}else{%>

    <div id="IframeScroller" style="position:absolute; right:200px; top: 200px; width:150px;">
<iframe id="NewsWindow" src="scroll.jsp" width="50px" height="250" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" style="border: #000000 1px solid;"></iframe>
</div>
<font size="3" color="teal" align="right">

</font>



    <%}%>

</body>
</html>