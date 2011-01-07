<%--
    Document   : Simple.jsp
    Created on : Jun 18, 2010, 7:46:24 AM
    Author     : Mayank Saxena
--%>
    <%@page import="com.myapp.struts.admin.LoginDoc"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" flush="true" />
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>View Staff Account Details</title>

<script language="javascript" >
function b1click()
{
location.href="/LibMS-Struts/admin/main.jsp";
}
function b2click()
{
f.action="/LibMS-Struts/admin/main.jsp";
f.method="post";
f.target="_self";
f.submit();
}
function getQuery(id)
{
    var query = "/LibMS-Struts/admin/index4.jsp?id='"+id+"')";
    return query;
}
</script>
    <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
 <style>
    th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold }
     .alternaterows { background-color: #efefef }
     .header        { background-color: #c0003b; color: #FFFFFF;font-weight: bold }

     .datagrid      { border: 1px solid #C7C5B2; font-family: arial; font-size: 9pt;
	    font-weight: normal }
</style>
</head>

<div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
         <body>
 <table width="600px" height="800px"  valign="top" align="center">

        <tr><td valign="top" height="800px" width="600px" align="center">


<fieldset style="border:solid 1px brown;padding-left: 10px;padding-right: 10px" ><legend><img src="/LibMS-Struts/images/StaffViewAllAccount.PNG"></legend>





<%!
   
   
   LoginDoc Ob;
   ArrayList loginList;
   int fromIndex=0, toIndex;
%>
 <%

 ResultSet rs = (ResultSet)(session.getAttribute("simple_resultset"));

       

   loginList = new ArrayList ();
   int tcount =0;
   int perpage=4;
   int tpage=0;
 /*Create a connection by using getConnection() method
   that takes parameters of string type connection url,
   user name and password to connect to database.*/
rs.beforeFirst();


   while (rs.next()) {
	tcount++;
	Ob = new LoginDoc ();
	Ob.setuser_id(rs.getString("user_id"));
        Ob.setuser_name(rs.getString("user_name"));
	Ob.setpassword(rs.getString("password"));
	Ob.setquestion(rs.getString("question"));
	Ob.setlibrary_id(rs.getString("library_id"));
        Ob.setStaff_id(rs.getString("staff_id"));
        Ob.setans(rs.getString("ans"));
        
   loginList.add(Ob);

   System.out.println("tcount="+tcount);
		     }


//System.out.println("tcount="+tcount);

%>
       
<%
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= loginList.size ())
   toIndex = loginList.size();
   request.setAttribute ("loginList", loginList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
<br><br>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Record Found</p>
<%}
else
{%>
<br><br>
<table width="500px"><tr><td width="400px">
<ui:dataGrid items="${loginList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">
    
  <columns>
      
    <column width="50">
      <header value="" hAlign="left" styleClass="header"/>
    </column>

    <column width="150">
      <header value="User_ID" hAlign="left" styleClass="header"/>
      <item   value="${doc.user_id}" hyperLink="/LibMS-Struts/admin/index4.jsp?id='${doc.staff_id}'"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="100">
      <header value="user_Name" hAlign="left" styleClass="header"/>
      <item   value="${doc.user_name}" hAlign="left" hyperLink="/LibMS-Struts/admin/index4.jsp?id='${doc.staff_id}'"  styleClass="item"/>
    </column>

    <column width="100">
      <header value="question" hAlign="left" styleClass="header"/>
      <item   value="${doc.question}" hyperLink="/LibMS-Struts/admin/index4.jsp?id='${doc.staff_id}'"  hAlign="left" styleClass="item"/>
    </column>
   
    <column width="100">
      <header value="ans" hAlign="left" styleClass="header"/>
      <item   value="${doc.ans}" hyperLink="/LibMS-Struts/admin/index4.jsp?id='${doc.staff_id}'"  hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
<table width="500" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>">Next</a>
</c:if>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<c:forEach items="${pages}" var="page">
<c:choose>
  <c:when test="${page.current}">
    <b><a href="<c:out value="${page.url}"/>"><c:out value="${page.index}"/></a></b>
  </c:when>
  <c:otherwise>
    <a href="<c:out value="${page.url}"/>"><c:out value="${page.index}"/></a>
  </c:otherwise>
</c:choose>
</c:forEach>
</td>

</tr>
</table>
<%}%>
  <br><br><br>
  </td></tr>
  <tr><td align="center" width="400px">
<form name="f">

    <input type="button" name="b1" value="Back" onclick="b1click()" class="txt2">
    <input type="button" name="b2" value="Home" onclick="b2click()" class="txt2">
</form>

      </td></tr></table><br><br>
</fieldset></td></tr></table>
    </body>
</div>
</html>
