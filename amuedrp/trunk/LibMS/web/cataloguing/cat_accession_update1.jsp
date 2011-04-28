<%--
    Document   : cat_accession_entry
    Created on : Feb 28, 2011, 5:15:03 PM
    Author     : Asif Iqubal
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
<jsp:include page="/admin/header.jsp"/>
<html>
    <head>
        <script>
            function send()
{
    window.location="<%=request.getContextPath()%>/cataloguing/cat_accession.jsp";
    return false;
}</script>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String path= request.getContextPath();
 pageContext.setAttribute("path", path);
%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    <link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
    </head>
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
    <body>
        <html:form action="/ownaccessionEntry1" method="post">
            <html:hidden property="library_id" name="BibliographicDetailEntryActionForm1" value="<%=library_id%>"/>
            <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm1" value="<%=sub_library_id%>" />
            <html:hidden property="biblio_id" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="document_type" name="BibliographicDetailEntryActionForm1" value="Book"/>
            <table width="100%" border="0" style="position: absolute; top: 20%">
            <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" colspan="8">Bibliographic Details</td></tr>
            <tr><td colspan="8" height="5px;"></td></tr>
            <tr><td bgcolor="#AFC7C7" colspan="4" align="center"><strong>Title Information</strong></td><td bgcolor="#CFECEC" colspan="4" align="center"><strong>Document Information</strong></td></tr>
            <tr><td align="right">Title:</td><td><html:text property="title" name="BibliographicDetailEntryActionForm1" readonly="true" /></td><td align="right">Subtitle:</td><td><html:text property="subtitle" name="BibliographicDetailEntryActionForm1" readonly="true"/></td><td align="right">Document Category:</td><td><html:text property="book_type" name="BibliographicDetailEntryActionForm1" readonly="true"/></td><td align="right">Subjects:</td><td><html:text property="subject" name="BibliographicDetailEntryActionForm1" readonly="true"/></td></tr>
            <tr><td align="right">Alternate Title:</td><td><html:text property="alt_title" name="BibliographicDetailEntryActionForm1" readonly="true"/></td><td></td><td></td><td align="right">Edition:</td><td><html:text property="edition" name="BibliographicDetailEntryActionForm1" readonly="true"/></td><td align="right">No Of Copies:</td><td><html:text property="no_of_copies" name="BibliographicDetailEntryActionForm1" readonly="true"/></td></tr>
            <tr><td></td><td></td><td></td><td></td><td align="right">Series:</td><td colspan="2"><html:text property="ser_note" name="BibliographicDetailEntryActionForm1" readonly="true" style="width:300px;"/></td><td></td></tr>
            <tr><td bgcolor="#AFC7C7" colspan="4" align="center"><strong>Statement Responsibility</strong></td><td bgcolor="#CFECEC" colspan="4" align="center"><strong>Publication Information</strong></td></tr>
            <tr><td align="right">Statement Responsibility:</td><td><html:text property="statement_responsibility" name="BibliographicDetailEntryActionForm1" readonly="true"/></td><td align="right">Main Entry:</td><td><html:text property="main_entry" name="BibliographicDetailEntryActionForm1" readonly="true"/></td><td align="right">Publisher:</td><td><html:text property="publisher_name" name="BibliographicDetailEntryActionForm1" readonly="true"/></td><td align="right">Publication Place:</td><td><html:text property="publication_place" name="BibliographicDetailEntryActionForm1" readonly="true"/></td></tr>
            <tr><td align="right">Added Entry:</td><td><html:text property="added_entry" name="BibliographicDetailEntryActionForm1" readonly="true"/></td>&nbsp;<td><html:text property="added_entry0" name="BibliographicDetailEntryActionForm1" readonly="true"/></td><td></td><td align="right">Publishing Year:</td><td><html:text property="publishing_year" name="BibliographicDetailEntryActionForm1" readonly="true"/></td><td></td><td></td></tr>
            <tr><td></td><td><html:text property="added_entry1" name="BibliographicDetailEntryActionForm1" readonly="true"/></td>&nbsp;<td><html:text property="added_entry2" name="BibliographicDetailEntryActionForm1" readonly="true"/></td><td></td><td></td><td></td><td></td><td></td></tr>
            <tr><td bgcolor="#AFC7C7" colspan="8" align="center">Title Identification</td></tr>
            <tr><td align="right">LCC No:</td><td><html:text property="LCC_no" name="BibliographicDetailEntryActionForm1" readonly="true"/></td><td align="right">Call No:</td><td><html:text property="call_no" name="BibliographicDetailEntryActionForm1" readonly="true"/></td><td align="right">ISBN-10:</td><td><html:text property="isbn10" name="BibliographicDetailEntryActionForm1" readonly="true"/></td><td align="right">ISBN-13:</td><td><html:text property="isbn13" name="BibliographicDetailEntryActionForm1" readonly="true"/></td></tr>
            <tr><td height="5px;" colspan="8"></td></tr>
            <tr><td height="10px;" colspan="8"></td></tr>
            <tr><td colspan="8" height="30px;"></td></tr>
            <tr><td colspan="8">
            <table>
<div>
<%
if(tcount==0)
{
%>
<p class="err">No record Found</p>
<%}
else
{%>
<tr><td align="center"><span class="headerStyle">Accession Detail View</span><br><br></td></tr>
<tr><td>
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid" >

  <columns>
         <column width="70">
      <header value="Record No" hAlign="left" styleClass="header"/>
      <item   value="${doc.recordNo}"  hAlign="left"
	      styleClass="item"/>
       </column>
    <column width="200">
      <header value="Accession No" hAlign="left" styleClass="header"/>
      <item   value="${doc.id.accessionNo}"  hAlign="left"
	      styleClass="item"/>
    </column>
      <column width="250">
      <header value="Location" hAlign="left" styleClass="header"/>
      <item   value="${doc.location}"  hAlign="left"
	      styleClass="item"/>
    </column>
      <column width="50">
       <header value="Action" hAlign="left" styleClass="header"/>
          <item   value="View"  hAlign="left"   hyperLink="${path}/cataloguing/accessionEdit.do?id=${doc.accessionNo}"
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
           </div>
<tr><td height="30px;"></td></tr>
<tr><td align="center"><input type="button" name="button" value="Back" Class="txt1" onclick="return send()"/></td></tr>
</table>
 </td>
    </tr>
            </table>    </html:form>
    </body>
</html>
