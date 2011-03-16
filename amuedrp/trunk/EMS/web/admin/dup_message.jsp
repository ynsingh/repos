<%-- 
    Document   : test
    Created on : Jun 28, 2010, 8:11:42 PM
    Author     : Dushyant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String msg=(String)request.getAttribute("msg");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>EMS</title>
    </head>
    <body>
   
<script language="javascript">
      history.back(-1);
    //window.location="admin_view.jsp"
      <%if(msg!=null)
          {%>
     alert("<%=msg%>");
     <%}%>

        
</script>



    </body>
</html>
