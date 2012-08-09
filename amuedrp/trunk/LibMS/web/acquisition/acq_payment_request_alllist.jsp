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
    <jsp:include page="/admin/header.jsp" flush="true" />

<html>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Aqeel Ahmad Khan" content="MCA,AMU">
      <title></title>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<%
 String msg=(String)request.getAttribute("msg");
  String msg1=(String)request.getAttribute("msg1");
%>
<script type="text/javascript">
function send()
{
    top.location="<%=request.getContextPath()%>/acquisition/acq_payment_request.jsp";
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
     style="  top:110px;
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
   int perpage=9;
   int tpage=0;

 opacList=(ArrayList)request.getAttribute("acqprn");
System.out.println("opacList="+opacList.size());
tcount = opacList.size();
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+9) >= opacList.size())
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
<%
String path= request.getContextPath();
pageContext.setAttribute("path", path);
%>
<form id="f" name="f">

<tr><td>

<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid" >
  <columns>
     <column width="80">
        <header value="Prn No" hAlign="left" styleClass="header"/>
        <item   value="${doc.id.prn}"  hAlign="left"   styleClass="item"/>
    </column>
    <column width="80">
        <header value="Prn Dtae" hAlign="left" styleClass="header"/>
        <item   value="${doc.prnDate}"  hAlign="left"   styleClass="item"/>
    </column>

    <column width="80">
        <header value="Vendor Id" hAlign="left" styleClass="header"/>
        <item   value="${doc.vendorId}"  hAlign="left"  styleClass="item"/>
    </column>
    <column width="100">
        <header value="Total Amount" hAlign="left" styleClass="header"/>
        <item   value="${doc.totalAmount}"  hAlign="left"  styleClass="item"/>
    </column>
    <column width="120">
        <header value="No of Invoice" hAlign="left" styleClass="header"/>
        <item   value="${doc.noOfInvoices}"  hAlign="left"  styleClass="item"/>
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

<tr> <td align="center">
<input type="button" onclick="return send()" name="button" value="Back" Class="txt1"/>
                 </td></tr>

</form>
</div>
</table
</div>
</body>



</html>