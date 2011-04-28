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
        <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
        <title>LibMS</title>
    </head>
    <body>

<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">
    <br><br>

    <%-- <p class="mess"><b>Member Registration Details:-</b><br>
     <br>
         Member ID    :<b><%=mem_id%></b><br><br>
                    Member Name  :<b><%=mem_name%></b>
 </p>
 <br>--%>

                    <br>
                    <p  class="mess">
                          <% if(msg!=null){%>
                    <script type="text/javascript">
                           alert("<%=msg%>");
                       

                        </script>                      <%}%>


</p>
<br>
<%--
<p  class="mess">
                          <% if(msg1!=null){%>
                    <script type="text/javascript">
                           alert("<%=msg1%>");
                           window.location="/LibMS-Struts/circulation/cir_member_reg.jsp";

                        </script>                      <%}%>


</p>

<p  class="mess">
                          <% if(msg2!=null){%>
                    <script type="text/javascript">
                           alert("<%=msg2%>");
                           window.location="/LibMS-Struts/circulation/cir_member_reg.jsp";

                        </script>                      <%}%>


</p>--%>

        </div>
    <div
   style="
      top: 650px;

      position: absolute;

      visibility: show;">
        

</div>

    </body>
</html>
