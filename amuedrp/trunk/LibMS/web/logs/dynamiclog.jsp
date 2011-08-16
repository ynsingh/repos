<%-- 
    Document   : dynamiclog
    Created on : Jul 5, 2011, 9:05:38 AM
    Author     : System Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.Logs,java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <link rel="stylesheet" href="/LibMS/css/page.css"/>
        <h1>Dynamic Log Report</h1>
        <%
 List<Logs> logs=(List<Logs>)session.getAttribute("loglist");
  %>
  <script type="text/javascript">


function printlog()
{
    window.location="<%=request.getContextPath()%>/jasperlog.do";
    return false;
}

function printlog1()
{
    window.location="<%=request.getContextPath()%>/call.do";
    return false;
}
      </script>
  <form  name="form1">
       <input type="submit" value="View Report" onclick="return printlog();"/>
         <input type="submit" value="View Report" onclick="return printlog1();"/>
  <table border="1" class="table" width="100%">



        <%
        if(!logs.isEmpty()){
  for(int i=0;i<logs.size();i++){
  %>
  <tr><td><%=i+1%></td><td><%=logs.get(i).getUserId() %></td><td><%=logs.get(i).getDate()%></td><td><%=logs.get(i).getTime()%></td><td><%=logs.get(i).getClassname()%></td><td><%=logs.get(i).getUrl()%></td>
        <td><%=logs.get(i).getActionMessage()%></td><td><%=logs.get(i).getActionResult()%></td><td><%=logs.get(i).getLibraryId()%></td><td><%=logs.get(i).getSublibraryId()%></td>
    </tr>
    <tr>
        <td>
           

        </td>
    </tr>
        <%

  }
}else
{
out.println("No Record Found");
}
  
%>

 </table>
  </form>
    </body>
</html>
