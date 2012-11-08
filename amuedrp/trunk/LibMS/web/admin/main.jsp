<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<html><head>
    </head>
    <body>

<jsp:include page="header.jsp" flush="true" />
<div
   style="
      top: 19%;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">



           


<%

session.removeAttribute("page");
String msg=(String)request.getAttribute("msg");
if (msg!=null)
    {
%>
<%=msg%>
<%}%>


       


                </div>

      </body>
</html>
