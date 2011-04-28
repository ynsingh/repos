<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
      This jsp page is used for Showing Alert Window Whenever Success or failure Action class is Called.
      This jsp page is Intermediate page For every Activity  for one Complete Process  of member Registration.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="/admin/header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String mem_id=(String)request.getAttribute("mem_id");
String lib=(String)request.getAttribute("lib");
String err=(String)request.getAttribute("err");
String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("msg1");
String mem_name=(String)request.getAttribute("mem_name");
String mail_id=(String)request.getAttribute("mail_id");
String last_name=(String)request.getAttribute("last_name");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
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
               
 <p class="mess"><b>Member Details:-</b><br>
     <br>
                    Member ID    :<b><%=mem_id%></b><br><br>
                    Member Name  :<b><%=mem_name%></b>
 </p>
 <br>

               
                
                    <p  class="mess">
                          <% if(msg!=null){%>
                    <script type="text/javascript">
                           alert("<%=msg%>");
                           window.location="<%=request.getContextPath()%>/circulation/cir_ask_for_createaccount.jsp?mem_id=<%=mem_id%>&mem_name=<%=mem_name%>&mail_id=<%=mail_id%>&last_name=<%=last_name%>";

                        </script>                      <%}%>


</p>
<br>
<p  class="mess">
                          <% if(lib!=null){%>

                       <%=lib%>


                                            <%}%>


</p>

<p  class="mess">
                          <% if(msg1!=null){%>
                    
                       <%=msg1%>
                      

                                            <%}%>


</p>
<p  class="err">
                          <% if(err!=null){%>

                       <%=err%>


                                            <%}%>


</p>




        </div>
   

    </body>
</html>
