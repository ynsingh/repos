<%--
    Document   : Simple.jsp
    Created on : Jun 18, 2010, 7:46:24 AM
    Author     : Mayank Saxena
<jsp:include page="adminheader.jsp" flush="true" />
--%>
 
    <%@page import="com.myapp.struts.admin.RequestDoc"%>
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

    <title>View Request</title>

<script language="javascript" >
function b1click()
{
location.href="login.jsp";
}
function b2click()
{
f.action="login.jsp";
f.method="post";
f.target="_self";
f.submit();
}
function getQuery(id)
{
    var query = "/LibMS-Struts/admin/index3.jsp?id="+id;
    return query;
}
</script>
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

<body>
 <div
   style="  top:0px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
<%!
   
   
   RequestDoc Ob;
   ArrayList requestList;
   int fromIndex=0, toIndex;
%>
 <%

 ResultSet rs = (ResultSet)(session.getAttribute("resultset2"));

       

   requestList = new ArrayList ();
   int tcount =0;
   int perpage=4;
   int tpage=0;
 /*Create a connection by using getConnection() method
   that takes parameters of string type connection url,
   user name and password to connect to database.*/

rs.beforeFirst();


   while (rs.next()) {
	tcount++;
	Ob = new RequestDoc ();
	Ob.setRegistration_id(rs.getInt(1));
	Ob.setInstitute_name(rs.getString(2));
	Ob.setLibrary_name(rs.getString(21));
	Ob.setAdmin_email(rs.getString(17));
        Ob.setStatus(rs.getString(19));
        
   requestList.add(Ob);

   //System.out.println("tcount="+tcount);
		     }

System.out.println("tcount="+tcount);

%>
       
<%
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
<br><br>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Record Found</p>
<%}
else
{%>
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="2" cellSpacing="0" styleClass="datagrid">
    
  <columns>
      
    <column width="10">
      <header value="" hAlign="left" styleClass="header"/>
    </column>

    <column width="100">
      <header value="Registration_ID" hAlign="left" styleClass="header"/>
      <item   value="${doc.registration_id}" hyperLink="index3.jsp?id=${doc.registration_id}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="200">
      <header value="Institute Name" hAlign="left" styleClass="header"/>
      <item   value="${doc.institute_name}" hAlign="left" hyperLink="index3.jsp?id=${doc.registration_id}"  styleClass="item"/>
    </column>

    <column width="200">
      <header value="Library Name" hAlign="left" styleClass="header"/>
      <item   value="${doc.library_name}" hyperLink="index3.jsp?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
   
    <column width="100">
      <header value="Admin_Email" hAlign="left" styleClass="header"/>
      <item   value="${doc.admin_email}" hyperLink="index3.jsp?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
       <column width="100">
      <header value="Status" hAlign="left" styleClass="header"/>
      <item   value="${doc.status}" hyperLink="index3.jsp?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
<table width="600px" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="100px">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>">Next</a>
</c:if>
</td><td align="center" >


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
 </div>
    </body>





</html>
