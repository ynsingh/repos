<%--
    Document   : test
    Created on : Jun 28, 2010, 8:11:42 PM
    Author     : Dushyant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
 <jsp:include page="header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String staff_id=(String)request.getParameter("staff_id");


    String staff_name=(String)request.getParameter("staff_name");


%>
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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS : Manage Privileges</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
    </head>
    <body >

<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table width="400px" height="600px"  valign="top" dir="<%=rtl%>" align="<%=align%>" id="tab1">
        <tr><td   width="300px" height="400px" valign="top" class="mess"  dir="<%=rtl%>" align="<%=align%>" >


                       <%=resource.getString("admin.privilegemessage1.note1")%>  <br><br><%=resource.getString("staffid")%> :<b><%=staff_id%></b> <br><br><%=resource.getString("admin.staff_register_message.name")%> :<b><%=staff_name%></b>

                    





</td></tr></table>
        </div>
   
    </body>
</html>






                