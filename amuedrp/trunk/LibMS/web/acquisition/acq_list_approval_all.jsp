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
<jsp:include page="/admin/header.jsp"/>

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
function print()
{
    top.location="<%=request.getContextPath()%>/acq_approvalprint2.do";
    return false;
}
function printapproved()
{
    top.location="<%=request.getContextPath()%>/acq_approvalprint3.do";
    return false;
}

function send1()
{
    top.location="<%=request.getContextPath()%>/acquisition/titleShow.do?id=${doc.id.titleId}";
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

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
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
<table style="position:absolute; left: 20%; top: 20%;" class="table">
    <tr><td class="headerStyle" align="center" height="25px">Approved List</td></tr>

    <br><br>
<%
if(tcount==0)
{
%>
<p class="err">No record Found</p>
<%}
else
{%>

<tr><td style="padding-left: 12px;">
        <br><br>
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0"
    cellSpacing="0" styleClass="datagrid"  >

  <columns>
       <column width="100">
      <header value="Control No." hAlign="left" styleClass="header"/>
      <item   value="${doc.id.controlNo}"  hAlign="left"  styleClass="item"/>
    </column>


    <column width="100">
      <header value="Title" hAlign="left" styleClass="header"/>
      <item   value="${doc.acqBibliography.title}"  hAlign="left"   styleClass="item"/>
    </column>

    <column width="100">
      <header value="Author" hAlign="left" styleClass="header"/>
      <item   value="${doc.acqBibliography.author}"   hAlign="left"  styleClass="item"/>
    </column>
     <column width="100">
      <header value="ISBN" hAlign="left" styleClass="header"/>
      <item   value="${doc.acqBibliography.isbn}"  hAlign="left"  styleClass="item"/>
    </column>
      <column width="100">
      <header value="No Of Copies" hAlign="left" styleClass="header"/>
      <item   value="${doc.noOfCopies}"  hAlign="left"  styleClass="item"/>
    </column>
        <column width="100">
      <header value="Vendor" hAlign="left" styleClass="header"/>
      <item   value="${doc.vendor}"  hAlign="left"  styleClass="item"/>
    </column>
     
      <column width="200">
      <header value="Acquisition Mode" hAlign="left" styleClass="header"/>
      <item   value="${doc.acqMode}"  hAlign="left"  styleClass="item"/>
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

<tr><td height="20px;"></td></tr>
<tr> <td align="left" style="padding-left:12px;">

<input type="button" onclick="return print()" name="button" value="Print FirmOrder Report" />

                 </td></tr>
<tr>
    <td style="padding-left:12px;">
        <iframe scrolling="no" src="./acq_approved.do"  frameborder="0" height="200px" width="800px"/>

    </td>
</tr>
</table>
</div>
</body>
</html>
