<%-- 
    Document   : test
    Created on : Jun 28, 2010, 8:11:42 PM
    Author     : Dushyant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="/admin/header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String staff_id=(String)request.getAttribute("staff_id");
String msg=(String)request.getAttribute("msg");
String staff_name=(String)request.getAttribute("staff_name");

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        <title>LibMS : Manage Staff</title>
    </head>
    <body>
   
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">
   
   
                   

                    <p  class="mess">
                          <% if(msg!=null){%>
                            <%=msg%><%}%>
                            <b><%=staff_name%></b>
                   
</p>


    

        </div>
   

    </body>
</html>
