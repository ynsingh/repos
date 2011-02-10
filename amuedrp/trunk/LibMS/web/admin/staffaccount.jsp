<%-- 
    Document   : test
    Created on : Jun 28, 2010, 8:11:42 PM
    Author     : Dushyant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String email_id=(String)request.getAttribute("user_id");
String staff_id=(String)request.getAttribute("staff_id");
String user_name=(String)request.getAttribute("user_name");
String role=(String)request.getAttribute("role");

String lib=(String)request.getAttribute("library_id");
String msg=(String)request.getAttribute("msg");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS</title>
    </head>
    <body onload="return message()">
   
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

  
                   
                    <br><br>

                    <p align="left" class="mess"> Login Id:<b><%=email_id%></b>
                    <br><br>
                  <p align="left" class="mess"> User Role:<b><%=role%></b><br>
                           <br>
                    Staff Name:<b><%=user_name%></b><br><br>
                    <input type="hidden" name="user_name" value="<%=user_name%>"/>
                    Library ID:<b><%=lib%></b><br><br>
                    
                    The confirmation email sent successfully.

                    <%
                    session.setAttribute("staff_name",user_name);
                    %>



    </p>










        </div>
   

    </body>
    <script language="javascript">
    function message()
    {
        var a=alert("Login Account Created Successfully for:<%=user_name%>");


        location.href="/LibMS-Struts/admin/ask_for_assign_privilege.jsp?staff_id=<%=staff_id%>";

        return false;
    }
</script>
</html>
