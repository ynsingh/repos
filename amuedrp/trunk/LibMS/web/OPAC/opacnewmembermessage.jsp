<%--
    Document   : test
    Created on : Jun 28, 2010, 8:11:42 PM
    Author     : Dushyant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
//String mem_id=(String)request.getAttribute("mem_id");
String msg=(String)request.getAttribute("msg");
//String msg1=(String)request.getAttribute("msg1");
//String msg2=(String)request.getAttribute("msg2");
//String mem_name=(String)request.getAttribute("mem_name");
//String mail_id=(String)request.getAttribute("mail_id");
//String last_name=(String)request.getAttribute("last_name");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/LibMS/css/page.css"/>
        <title>LibMS</title>
    </head>
    
    <body>

               
                    <p  class="mess"><script>
                          <% if(msg!=null)
                    {
                          %>alert("<%=msg%>");location.href="<%=request.getContextPath()%>/OPAC/OPACmain1.jsp";
                       
<%
                                         }%>
</script>

</p>
   

    </body>
</html>
