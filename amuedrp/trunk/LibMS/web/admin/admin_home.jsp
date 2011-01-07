<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <jsp:include page="adminheader.jsp" flush="true" />
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.sql.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
 
<%
//pending
ResultSet rst =(ResultSet)session.getAttribute("resultset");
int count=(Integer)session.getAttribute("count");
 int i=1;
// session.setAttribute("resultset", rst);
 //session.setAttribute("count",count);

//approved
//ResultSet rst1 =(ResultSet)session.getAttribute("resultset1");
//int count1=(Integer)request.getAttribute("count1");
 //int j=1;
 //session.setAttribute("resultset1", rst1);
 //session.setAttribute("count1",count1);

//view all
//ResultSet rst2 =(ResultSet)request.getAttribute("resultset2");
//int count2=(Integer)request.getAttribute("count2");
// int k=1;
//session.setAttribute("resultset2", rst2);
//session.setAttribute("count2",count2);



%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>

<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<style type="text/css">
<!--
.style7 {font-size: 12px}
.style8 {color: #C65A12}
.style9 {color: #990000}
-->
    </style>



</head>

<div
   style="  top:140px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
  
<table border=0 cellpadding=0 cellspacing=0 width="100%">
<tr><td height="25px" width="600px" bgcolor="#c0003b">
  <a href="/LibMS-Struts/admin/admin_home.jsp"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white"> &nbsp;&nbsp;Home</a>&nbsp;|&nbsp;</b>
 <a href="/LibMS-Struts/admin/view_pending.jsp" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white"> View Pending List</a>&nbsp;|&nbsp;</b>
 <a href="/LibMS-Struts/admin/view_approved.jsp" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white"> View Approved List</a>&nbsp;|&nbsp;</b>
<a href="/LibMS-Struts/admin/view_all.jsp" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white">View All </a>&nbsp;|&nbsp;</b>
          <a href="/LibMS-Struts/admin/update_admin.jsp" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white"> Modify Institute Record</a>&nbsp;|&nbsp;</b>
<a href="/LibMS-Struts/admin/search_admin.jsp"  target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white">Search Institute  </a></b>&nbsp;

      </td>
  </tr>
  <tr><td>
            <font color="blue" size="+1"><b><br>
Pending Requests for Institute Registration (<%=count%>)&nbsp;<a href="/LibMS-Struts/admin/view_pending.jsp" target="f3"> view pending </a>

    </b>
</font>

      </td></tr>
  <tr><td align="left" style=" padding-left: 200px;">
            <IFRAME  name="f3" src="#" frameborder=0 scrolling="yes" style="color:deepskyblue;left:5px;height:500px;width:800px;visibility:true;" id="f3"></IFRAME>


      </td></tr>
  
  <tr><td><hr></td></tr>
        <jsp:include page="adminfooter.jsp" />
      
</table>
    </div>
        </body>
   </html>
     
       



  