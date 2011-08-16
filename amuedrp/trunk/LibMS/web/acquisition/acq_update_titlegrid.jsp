<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
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
  <jsp:include page="/admin/header.jsp"/>
<html>
    <head>
             <%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String title=(String)session.getAttribute("title");
String doc_type=(String)session.getAttribute("doc_type");

%>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
      <title></title>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript">
function send()
{
    window.location="<%=request.getContextPath()%>/acquisition/acq_new_entry.jsp";
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
<table style="position:absolute; left: 10%; top: 25%;">
<div>
<%
if(tcount==0)
{
%>
<p class="err">No record Found</p>
<%}
else
{%>
<tr><td align="center"><span class="headerStyle">Duplicate Title </span><br><br></td></tr>
<tr><td>
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid" >

  <columns>
         <column width="70">
      <header value="Title Id" hAlign="left" styleClass="header"/>
      <item   value="${doc.id.titleId}"  hAlign="left"
	      styleClass="item"/>
       </column>
    <column width="100">
      <header value="Document Type" hAlign="left" styleClass="header"/>
      <item   value="${doc.docType}"  hAlign="left"
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
      <item   value="Edit"  hAlign="left"   hyperLink="/LibMS/acquisition/viewOwn1.do?id=${doc.id.titleId}"
	      styleClass="item"/>
      
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
</td>
</tr>
</table>
<%}%>
</td></tr>
            <html:form action="/acqmerge" method="post">
          <html:hidden property="library_id" name="AcqBiblioActionForm" value="<%=library_id%>"/>
                <html:hidden property="sublibrary_id" name="AcqBiblioActionForm" value="<%=sub_library_id%>" />
                <html:hidden property="author" name="AcqBiblioActionForm" value="1"/>
                <html:hidden property="document_type" name="AcqBiblioActionForm" value="<%=doc_type%>"/>
                <html:hidden property="title" name="AcqBiblioActionForm" value="<%=title%>"/>


                     <tr><td height="30px;"></td></tr>
                     <tr> <td align="center"> 
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" onclick="return send()" name="button" value="Cancel" Class="txt1"/>
                 </td></tr>

</html:form>

</div>
</table> </body>
</html>
