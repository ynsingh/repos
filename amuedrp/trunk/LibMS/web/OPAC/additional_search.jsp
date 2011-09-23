<%--
    Document   : Additional.jsp
    Created on : Jun 18, 2010, 7:46:24 AM
    Author     : Mayank Saxena
--%>
    <%@ page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"%>
    <%@ page import="java.util.ResourceBundle"%>
    <%@ page import="java.util.Locale"%>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script language="javascript" >
function b1click()
{
location.href="<%=request.getContextPath()%>/OPAC/additional.jsp";
}
function b2click()
{
f.action="<%=request.getContextPath()%>/OPAC/home.html";
f.method="post";
f.target="_self";
f.submit();
}
</script>
 <style>

    th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold;border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-weight: bold;text-decoration: none;padding-left: 10px; }

     .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}

</style>
</head>
<body bgcolor="#FFFFFF" >
<%!
   ArrayList opacList;
   int fromIndex=0, toIndex;
   int tcount =0;
   int perpage=10;
   int tpage=0;
%>
<%
if(session.getAttribute("additional_search_list")!=null){


opacList = new ArrayList ();
  opacList=(ArrayList) session.getAttribute("additional_search_list");
   int tcount =0;
   if(opacList!=null)tcount=opacList.size();
   int perpage=4;
   int tpage=0;
 Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= tcount)
   toIndex = tcount;
   if(opacList!=null)request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
%>
<%
  String Title=resource.getString("opac.simplesearch.title");
  pageContext.setAttribute("Title", Title);
  String MainEntry=resource.getString("opac.simplesearch.mainentry");
  pageContext.setAttribute("MainEntry", MainEntry);
  String LibraryID=resource.getString("opac.browse.table.Libraryid");
  pageContext.setAttribute("LibraryID",LibraryID);
  String CallNo=resource.getString("opac.simplesearch.callno.");
  pageContext.setAttribute("CallNo",CallNo);
String path= request.getContextPath();
pageContext.setAttribute("path", path);
  %>
 <table align="<%=align%>" dir="<%=rtl%>" width="100%" height="400px" class="datagrid" style="border:solid 1px #e0e8f5;">
  <tr style="background-color:#e0e8f5;" dir="<%=rtl%>"><td   dir="<%=rtl%>" height="28px" align="center" rowspan="2" colspan="2">
		<%=resource.getString("opac.additional.advancesearch")%>
      </td><td valign="top" align="center" dir="<%=rtl%>">
    <%=resource.getString("opac.browse.bibliodetail")%>
  </td></tr>
  <tr style="background-color:#e0e8f5;" height="10px" dir="<%=rtl%>">
  <td valign="top" rowspan="2" dir="<%=rtl%>">
  <IFRAME  name="fr2" src="#" frameborder=0 scrolling="NO" height="400px"  id="fr2" ></IFRAME>
  </td>
     </tr>
     <tr style="background-color:#e0e8f5;" dir="<%=rtl%>">
         <td colspan="2" align="center" valign="top" dir="<%=rtl%>" height="300px">
<%
if(tcount==0)
{
%>
<p class="err"><%=resource.getString("global.norecordfound")%></p>
<%}
else
{%>
<table height="300px" dir="<%=rtl%>"><tr><td valign="top" dir="<%=rtl%>">
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid" >
  <columns>
    <column width="250">
      <header value="${Title}" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.title}" hyperLink="./viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2"  hAlign="left" />
    </column>

      <column width="250">
      <header value="${MainEntry}" hAlign="left" styleClass="header"/>
      <item   value="${doc.mainEntry}"  hAlign="left" hyperLink="./viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2"
	      styleClass="item"/>
    </column>
            <column width="150">
      <header value="Publishing Name" hAlign="left" styleClass="header"/>
      <item   value="${doc.publisherName}"  hAlign="left" hyperLink="./viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2"
	      styleClass="item"/>
    </column>
    <column width="150">
      <header value="Publishing Year" hAlign="left" styleClass="header"/>
      <item   value="${doc.publishingYear}"  hAlign="left" hyperLink="./viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2"
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
<tr><td height="5px" style="margin:0px 0px 0px 0px;" >
        <table width="900"  border=0 class="header">
    <tr >
<td align="left" width="10%" class="datagrid">
<c:if test="${previous != null}">
    <a style="color:white;" href="<c:out value="${previous}"/>"><%=resource.getString("global.previous")%></a>
</c:if>&nbsp;
</td>

<td align="center" width="10%" class="datagrid">
<c:forEach items="${pages}" var="page">
<c:choose>
  <c:when test="${page.current}">
      <b><a style="color:white" href="<c:out value="${page.url}" />"><c:out value="${page.index}"/></a></b>
  </c:when>
  <c:otherwise>
    <a style="color:white" href="<c:out value="${page.url}" />"><c:out value="${page.index}"/></a>
  </c:otherwise>
</c:choose>
</c:forEach>
</td>
<td align="right" width="10%" class="datagrid">&nbsp;
<c:if test="${next != null}">
<a style="color:white;" href="<c:out value="${next}"/>"><%=resource.getString("global.next")%></a>
</c:if>
</td>
</tr>
    </table></td></tr>
<tr><td height="10px">

    </td></tr></table>
  <%}%>
  </td></tr></table>
  <%}else if(session.getAttribute("additional_search_list1")!=null){

opacList = new ArrayList ();
  opacList=(ArrayList) session.getAttribute("additional_search_list1");
   int tcount =0;
   if(opacList!=null)tcount=opacList.size();
   int perpage=4;
   int tpage=0;
 Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= tcount)
   toIndex = tcount;
   if(opacList!=null)request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
%>
<%
  String Title=resource.getString("opac.simplesearch.title");
  pageContext.setAttribute("Title", Title);
  String MainEntry=resource.getString("opac.simplesearch.mainentry");
  pageContext.setAttribute("MainEntry", MainEntry);
  String LibraryID=resource.getString("opac.browse.table.Libraryid");
  pageContext.setAttribute("LibraryID",LibraryID);
  String CallNo=resource.getString("opac.simplesearch.callno.");
  pageContext.setAttribute("CallNo",CallNo);
String path= request.getContextPath();
pageContext.setAttribute("path", path);
  %>
 <table align="<%=align%>" dir="<%=rtl%>" width="100%" height="400px" class="datagrid" style="border:solid 1px #e0e8f5;">
  <tr style="background-color:#e0e8f5;" dir="<%=rtl%>"><td   dir="<%=rtl%>" height="28px" align="center" rowspan="2" colspan="2">
		<%=resource.getString("opac.additional.advancesearch")%>
      </td><td valign="top" align="center" dir="<%=rtl%>">
    <%=resource.getString("opac.browse.bibliodetail")%>
  </td></tr>
  <tr style="background-color:#e0e8f5;" height="10px" dir="<%=rtl%>">
  <td valign="top" rowspan="2" dir="<%=rtl%>">
  <IFRAME  name="fr2" src="#" frameborder=0 scrolling="NO" height="400px"  id="fr2" ></IFRAME>
  </td>
     </tr>
     <tr style="background-color:#e0e8f5;" dir="<%=rtl%>">
         <td colspan="2" align="center" valign="top" dir="<%=rtl%>" height="300px">
<%
if(tcount==0)
{
%>
<p class="err"><%=resource.getString("global.norecordfound")%></p>
<%}
else
{%>
<table height="300px" dir="<%=rtl%>"><tr><td valign="top" dir="<%=rtl%>">
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid" >
  <columns>
    <column width="250">
      <header value="${Title}" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.title}" hyperLink="./viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2"  hAlign="left" />
    </column>

      <column width="250">
      <header value="${MainEntry}" hAlign="left" styleClass="header"/>
      <item   value="${doc.mainEntry}"  hAlign="left" hyperLink="./viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2"
	      styleClass="item"/>
    </column>
            <column width="150">
      <header value="Publishing Name" hAlign="left" styleClass="header"/>
      <item   value="${doc.publisherName}"  hAlign="left" hyperLink="./viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2"
	      styleClass="item"/>
    </column>
    <column width="150">
      <header value="Publishing Year" hAlign="left" styleClass="header"/>
      <item   value="${doc.publishingYear}"  hAlign="left" hyperLink="./viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2"
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
<tr><td height="5px" style="margin:0px 0px 0px 0px;" >
        <table width="900"  border=0 class="header">
    <tr >
<td align="left" width="10%" class="datagrid">
<c:if test="${previous != null}">
    <a style="color:white;" href="<c:out value="${previous}"/>"><%=resource.getString("global.previous")%></a>
</c:if>&nbsp;
</td>

<td align="center" width="10%" class="datagrid">
<c:forEach items="${pages}" var="page">
<c:choose>
  <c:when test="${page.current}">
      <b><a style="color:white" href="<c:out value="${page.url}" />"><c:out value="${page.index}"/></a></b>
  </c:when>
  <c:otherwise>
    <a style="color:white" href="<c:out value="${page.url}" />"><c:out value="${page.index}"/></a>
  </c:otherwise>
</c:choose>
</c:forEach>
</td>
<td align="right" width="10%" class="datagrid">&nbsp;
<c:if test="${next != null}">
<a style="color:white;" href="<c:out value="${next}"/>"><%=resource.getString("global.next")%></a>
</c:if>
</td>
</tr>
    </table></td></tr>
<tr><td height="10px">

    </td></tr></table>
  <%}%>
  </td></tr></table>
<%}%>
    </body>
</html>

