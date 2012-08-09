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

      <title></title>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

       
</head>
<body bgcolor="#FFFFFF">
<%!  ArrayList opacList;
   int fromIndex, toIndex;
%>
<%
String msg1=(String)request.getAttribute("msg1");
String msg2=(String)request.getAttribute("msg2");
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
 <table  align="center" width="70%" class="datagrid"  style="border:solid 1px black;position: absolute;top:20%;left: 15%;">



          <tr class="headerStyle"><td  width="100%"  height="25px" align="center" colspan="2">


		Demand List Refer from OPAC



<%
if(tcount==0)
{
%>
<p class="err">No record Found</p>
<%}
else
{%>

        </td></tr>
<div>

<tr><td align="center" style="margin:0px 0px 0px 0px" width="100%">
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid" >

  <columns>

       <column width="10%">
      <header value="Member Id " hAlign="left" styleClass="header1"/>
      <item   value="${doc.id.memId}"  hAlign="left"
	      styleClass="item"/>
       </column>
          <column width="10%">
      <header value="Demand Id " hAlign="left" styleClass="header1"/>
      <item   value="${doc.id.demandId}"  hAlign="left"
	      styleClass="item"/>
       </column>
   <column width="10%">
      <header value="Member Type " hAlign="left" styleClass="header1"/>
      <item   value="${doc.memberType}"  hAlign="left"
	      styleClass="item"/>
       </column>

<column width="10%">
      <header value="Sub Member Type " hAlign="left" styleClass="header1"/>
      <item   value="${doc.subMemberType}"  hAlign="left"
	      styleClass="item"/>
       </column>

<column width="10%">
      <header value="Member Name " hAlign="left" styleClass="header1"/>
      <item   value="${doc.memName}"  hAlign="left"
	      styleClass="item"/>
       </column>


      <column width="20%">
      <header value="Title " hAlign="left" styleClass="header1"/>
      <item   value="${doc.id.title}"  hAlign="left"
	      styleClass="item"/>
       </column>
      <column width="10%">
      <header value="Author" hAlign="left" styleClass="header1"/>
      <item   value="${doc.author}"  hAlign="left"
	      styleClass="item"/>
    </column>

       <column width="10%">
      <header value="Document Type " hAlign="left" styleClass="header1"/>
      <item   value="${doc.category}"  hAlign="left"
	      styleClass="item"/>
       </column>
      
 <column width="20%">
      <header value="Action" hAlign="left" styleClass="header1"/>
      <item   value="Initiate Acquisition"  hAlign="left" hyperLink="/LibMS/acquisition/titleShow5.do?id=${doc.id.demandId}"
	      styleClass="item" hyperLinkTarget="_parent"   />
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
<table width="100%" style="font-family: arial; font-size: 10pt" border=0>
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
 <tr><td colspan="2"><hr>
                        <%  if(msg1!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>
<%}%>
    <%  if(msg2!=null)
    {%>
    <span style="font-size:12px;font-weight:bold;color:blue;" ><%=msg2%></span>
    <script>
window.refresh();
    </script>
<%}%>
                        </td> </tr>
            <html:form action="/acqmerge" method="post">
          <html:hidden property="library_id" name="AcqBiblioActionForm" value="<%=library_id%>"/>
                <html:hidden property="sublibrary_id" name="AcqBiblioActionForm" value="<%=sub_library_id%>" />
                <html:hidden property="author" name="AcqBiblioActionForm" value="1"/>
                <html:hidden property="document_type" name="AcqBiblioActionForm" value="<%=doc_type%>"/>
                <html:hidden property="title" name="AcqBiblioActionForm" value="<%=title%>"/>


                     <tr><td height="30px;"></td></tr>
                    

</html:form>

</div>
</table> </body>
</html>
