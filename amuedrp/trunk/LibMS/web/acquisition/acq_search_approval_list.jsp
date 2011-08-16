<%--
    Document   : cat_viewAll_biblio
    Created on : Mar 15, 2011, 12:05:56 PM
    Author     : EdRP-04
--%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
      <title></title>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript">
function send()
{
    top.location="<%=request.getContextPath()%>/admin/main.jsp";
    return false;
}
function send1()
{
    top.location="<%=request.getContextPath()%>/acquisition/titleShow1.do?id=${doc.id.titleId}";
    return false;
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
<body bgcolor="#FFFFFF">
<%!  ArrayList opacList;
   int fromIndex, toIndex;
%>
<%
opacList = new ArrayList();
int tcount =0;
   int perpage=4;
   int tpage=0;

 opacList=(ArrayList)session.getAttribute("opacList");
System.out.println("opacList="+opacList.size());
tcount = opacList.size();
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= opacList.size())
   toIndex = opacList.size();
   request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
<table style="position:absolute; left: 10%; top: 10%;">
<div>
<%
if(tcount==0)
{
%>
<p class="err">No record Found</p>
<%}
else
{%>
<tr><td>
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid" >

  <columns>
         <column width="70">
      <header value="Title Id" hAlign="left" styleClass="header"/>
      <item   value="${doc.id.titleId}"  hAlign="left"
	      styleClass="item"/>
       </column>

      <column width="150">
      <header value="Title" hAlign="left" styleClass="header"/>
      <item   value="${doc.title}"  hAlign="left"
	      styleClass="item"/>
    </column>
    <column width="150">
      <header value="Author" hAlign="left" styleClass="header"/>
      <item   value="${doc.author}"  hAlign="left"
	      styleClass="item"/>
    </column>
    <column width="70">
      <header value="Action" hAlign="left" styleClass="header"/>
      <item   value="Initiate Approval"   hAlign="left" hyperLink="/LibMS/acquisition/titleShow1.do?id=${doc.id.titleId}"
	      styleClass="item" hyperLinkTarget="_parent" />
    </column>
  </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
<alternateRows styleClass="alternaterows"/>
<paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
</td></tr>
<tr><td>
<table width="750" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="33%">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
</c:if>&nbsp;
</td>
<td align="center" width="33%">
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
<td align="middle" width="33%">&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>">Next</a>
</c:if>
<%}%>
</td>
</tr>
</table>
</div>
<tr><td height="20px;"></td></tr>
<tr> <td align="center">
<input type="button" onclick="return send()" name="button" value="Back" Class="txt1"/>
                 </td></tr>
</table>
</body>
</html>
