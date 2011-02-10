<%--
    Document   : Simple.jsp
    Created on : Jun 18, 2010, 7:46:24 AM
    Author     : Mayank Saxena
--%>
    <%@page import="com.myapp.struts.admin.StaffDoc"%>
    <jsp:include page="header.jsp" flush="true" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

    <title>View Staff Details</title>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
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
    var query = "/LibMS-Struts/admin/index2.jsp?id=(select * from staff_detail where staff_id='"+id+"')";
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

<body bgcolor="#FFFFFF">
 <table width="600px" height="600px"  valign="top" align="center" >

        <tr><td valign="top" height="600px" width="800px" align="center">


<fieldset style="border:solid 1px brown;height:300px;padding-left: 10px;padding-right: 20px" ><legend><img src="/LibMS-Struts/images/StaffViewAll.PNG" /></legend>
                    <br>
                    <br>

<%!
   
   
   StaffDoc Ob;
   ArrayList staffList;
   int fromIndex=0, toIndex;
%>
 <%

 ResultSet rs = (ResultSet)(session.getAttribute("simple_resultset"));

       

   staffList = new ArrayList ();
   int tcount =0;
   int perpage=4;
   int tpage=0;
 /*Create a connection by using getConnection() method
   that takes parameters of string type connection url,
   user name and password to connect to database.*/
rs.beforeFirst();


   while (rs.next()) {
	tcount++;
	Ob = new StaffDoc ();
	Ob.setStaff_id(rs.getString("Staff_id"));
	Ob.setfirst_name(rs.getString("first_name"));
	Ob.setlast_name(rs.getString("last_name"));
	Ob.setemail_id(rs.getString("emai_id"));
        
   staffList.add(Ob);

  // System.out.println("tcount="+tcount);
		     }


//System.out.println("tcount="+tcount);

%>
       
<%
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= staffList.size ())
   toIndex = staffList.size();
   request.setAttribute ("staffList", staffList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Record Found</p>
<%}
else
{%>
<br><br>
<table width="400px"><tr><td width="400px">
<ui:dataGrid items="${staffList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">
    
  <columns>
      
    <column width="50">
      <header value="" hAlign="left" styleClass="header"/>
    </column>

    <column width="50">
      <header value="Staff_ID" hAlign="left" styleClass="header"/>
      <item   value="${doc.staff_id}" hyperLink="/LibMS-Struts/admin/index2.jsp?id=${doc.staff_id}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="100">
      <header value="First_Name" hAlign="left" styleClass="header"/>
      <item   value="${doc.first_name}" hAlign="left" hyperLink="/LibMS-Struts/admin/index2.jsp?id=${doc.staff_id}"  styleClass="item"/>
    </column>

    <column width="100">
      <header value="Last_Name" hAlign="left" styleClass="header"/>
      <item   value="${doc.last_name}" hyperLink="/LibMS-Struts/admin/index2.jsp?id=${doc.staff_id}"  hAlign="left" styleClass="item"/>
    </column>
   
    <column width="100">
      <header value="Email_ID" hAlign="left" styleClass="header"/>
      <item   value="${doc.email_id}" hyperLink="/LibMS-Struts/admin/index2.jsp?id=${doc.staff_id}"  hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
<table width="400" style="font-family: arial; font-size: 10pt" border=0>
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

      </td></tr></table>
</fieldset>
 </table>
    </body>
</div>
  
</html>
