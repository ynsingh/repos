
    <%@page import="com.myapp.struts.opac.OpacDoc"%>
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


    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

<script language="javascript" >

function b1click()
{

location.href="advance.jsp";
}

function b2click()
{
f.action="<%=request.getContextPath()%>/OPAC/home.html";
f.method="post";
f.target="_self";
f.submit();
}

</script>

</head>
<body style="background-color:#e0e8f5;">
<%!

  
Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
   OpacDoc Ob;
   ArrayList opacList;
   int fromIndex, toIndex;
%>
<%
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";page=true;}
    else{ rtl="RTL";align="right";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
if(session.getAttribute("ResultSet")!=null){
   opacList = new ArrayList ();
   int tcount =0;
   int pagesize=10;
   int tpage=0;

opacList=(ArrayList)session.getAttribute("ResultSet");
  
  if(opacList!=null)tcount =opacList.size();


  
  //   String page1 = (String)request.getParameter("pagesize");
     //System.out.println("page1="+page1);
  // if (page1!=null) pagesize = Integer.parseInt(page1);
   fromIndex = (int)DataGridParameters.getDataGridPageIndex(request, "datagrid1");

   if ((toIndex = fromIndex+(int)pagesize) >= tcount)
   toIndex = tcount;
   if(opacList!=null)request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   pageContext.setAttribute("pagesize", pagesize);
   pageContext.setAttribute("fromIndex", fromIndex);
   pageContext.setAttribute("fromIndex1", fromIndex+1);
pageContext.setAttribute("toIndex1", toIndex);
pageContext.setAttribute("project", request.getContextPath());


  %>
 <table align="center" dir="<%=rtl%>" width="80%" height="400px" class="datagrid" style="border:solid 1px #e0e8f5;">

     <tr  dir="<%=rtl%>">
         <td   align="<%=align%>" valign="top" dir="<%=rtl%>" height="300px">
<%
if(tcount==0)
{
%>
<i>    Advanced Search Results>> <%=tcount%> Record Found</i>
<%}
else
{%>
<table height="300px" width="100%" dir="<%=rtl%>"><tr><td valign="top" dir="<%=rtl%>">
            <%--<%=resource.getString("opac.additional.advancesearch")%>--%>
            <i>Advanced Search Result>> <%=tcount%> Record Found</i>
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid" >
   <columns>
    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left"/>
    </column>

    <column width="10%">
      <header value="${MainEntry}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.mainEntry}" hAlign="left" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"   />
    </column>
 <column width="10%">
      <header value="PublisherName" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.publisherName}" hAlign="left" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  />
    </column>
 <column width="10%">
      <header value="PublicationPlace" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.publicationPlace}" hAlign="left" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"   />
    </column>


      <column width="10%">
      <header value="${CallNo}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.callNo}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left" />
    </column>


      <column width="10%">
      <header value="${LibraryID}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.id.libraryId}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left" />
    </column>
       <column width="10%">
      <header value="SubLibrary" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.id.sublibraryId}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left" />
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
<alternateRows styleClass="alternaterows"/>
 <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
</td></tr>

<tr class="header">

<td align="center" class="datagrid">
 <c:if test="${previous != null}">
   <a style="color:white;" href="<c:out value="${previous}"/>">
      <b><i><< <%=resource.getString("global.previous")%></i></b>
    </a>
</c:if>&nbsp;


   <c:forEach items="${pages}"  var="page"  >





<c:choose>
  <c:when test="${page.current}">

 <span style="color:white;font-weight:italic" href="<c:out value="${page.url}" />">Record No : <%=pageContext.getAttribute("fromIndex1")%></span>
  </c:when>

</c:choose>
 </c:forEach>
    <span style="color:white;"> to <%=pageContext.getAttribute("toIndex1")%></span>&nbsp;&nbsp;
<c:if test="${next != null}">
<a style="color:white;" href="<c:out value="${next}"/>"> <b><i> <%=resource.getString("global.next")%>>></i></b></a>
</c:if>


</td>
</tr>
</table>
  <%}%>
  </td>
  </tr></table>

    <%}else if(session.getAttribute("ResultSet1")!=null){
   opacList = new ArrayList ();
   int tcount =0;
  

opacList=(ArrayList)session.getAttribute("ResultSet1");
 
  if(opacList!=null)tcount =opacList.size();


   int pagesize = 10;
  //   String page1 = (String)request.getParameter("pagesize");
     //System.out.println("page1="+page1);
  // if (page1!=null) pagesize = Integer.parseInt(page1);
   fromIndex = (int)DataGridParameters.getDataGridPageIndex(request, "datagrid1");

   if ((toIndex = fromIndex+(int)pagesize) >= tcount)
   toIndex = tcount;
   if(opacList!=null)request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   pageContext.setAttribute("pagesize", pagesize);
   pageContext.setAttribute("fromIndex", fromIndex);
   pageContext.setAttribute("fromIndex1", fromIndex+1);
pageContext.setAttribute("toIndex1", toIndex);
pageContext.setAttribute("project", request.getContextPath());

  %>
 <table align="center" dir="<%=rtl%>" width="80%" height="400px" class="datagrid" style="border:solid 1px #e0e8f5;">

     <tr  dir="<%=rtl%>">
         <td   align="<%=align%>" valign="top" dir="<%=rtl%>" height="300px">
<%
if(tcount==0)
{
%>
<i>    Advanced Search Results>> <%=tcount%> Record Found</i>
<%}
else
{%>
<table height="300px" width="80%" dir="<%=rtl%>"><tr><td valign="top" dir="<%=rtl%>">
            <%--<%=resource.getString("opac.additional.advancesearch")%>--%>
            <i>Advanced Search Result>> <%=tcount%> Record Found</i>
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid" >
   <columns>
    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left"/>
    </column>

    <column width="10%">
      <header value="${MainEntry}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.mainEntry}" hAlign="left" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"   />
    </column>
 <column width="10%">
      <header value="PublisherName" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.publisherName}" hAlign="left" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  />
    </column>
 <column width="10%">
      <header value="PublicationPlace" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.publicationPlace}" hAlign="left" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"   />
    </column>


      <column width="10%">
      <header value="${CallNo}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.callNo}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left" />
    </column>


      <column width="10%">
      <header value="${LibraryID}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.id.libraryId}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left" />
    </column>
       <column width="10%">
      <header value="SubLibrary" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.id.sublibraryId}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left" />
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
<alternateRows styleClass="alternaterows"/>
 <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
</td></tr>

<tr class="header">

<td align="center" class="datagrid">
 <c:if test="${previous != null}">
   <a style="color:white;" href="<c:out value="${previous}"/>">
      <b><i><< <%=resource.getString("global.previous")%></i></b>
    </a>
</c:if>&nbsp;


   <c:forEach items="${pages}"  var="page"  >





<c:choose>
  <c:when test="${page.current}">

 <span style="color:white;font-weight:italic" href="<c:out value="${page.url}" />">Record No : <%=pageContext.getAttribute("fromIndex1")%></span>
  </c:when>

</c:choose>
 </c:forEach>
    <span style="color:white;"> to <%=pageContext.getAttribute("toIndex1")%></span>&nbsp;&nbsp;
<c:if test="${next != null}">
<a style="color:white;" href="<c:out value="${next}"/>"> <b><i> <%=resource.getString("global.next")%>>></i></b></a>
</c:if>


</td>
</tr>
</table>
  <%}%>
  </td>
  </tr></table>

    <%}%>
    </body>
</html>


