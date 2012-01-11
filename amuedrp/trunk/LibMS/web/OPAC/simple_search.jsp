
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


    <link rel="stylesheet" href="/LibMS/css/page.css"/>

<script language="javascript" >


function showcount()
{
    var page;
    page = document.getElementById("pagesize").value;
    if (page>0){
    location.href="/LibMS/OPAC/simple_search.jsp?pagesize="+page;}
}
</script>
</head>
<body style="background-color:#e0e8f5;margin: 2px 2px 2px 2px ">
<%!

   
   OpacDoc Ob;
   ArrayList opacList;
   int fromIndex, toIndex;
   static Integer count=0;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    boolean page=true;
%>
<%
if(session.getAttribute("simple_search_list")!=null){

String path= request.getContextPath();
pageContext.setAttribute("path", path);


 opacList = new ArrayList ();
 opacList = (ArrayList)session.getAttribute("simple_search_list");
 int tcount=0;
  if(opacList!=null)tcount =opacList.size();
   
   
   int pagesize = 10;
  //   String page1 = (String)request.getParameter("pagesize");
     //System.out.println("page1="+page1);
  // if (page1!=null) pagesize = Integer.parseInt(page1);
   fromIndex = (int)DataGridParameters.getDataGridPageIndex(request, "datagrid1");

   if ((toIndex = fromIndex+(int)pagesize) >= tcount)
   toIndex = tcount;
   //System.out.println("opacList="+opacList.size()+" tcount="+tcount);
   if(opacList!=null)request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   pageContext.setAttribute("pagesize", pagesize);
   pageContext.setAttribute("fromIndex", fromIndex);
   pageContext.setAttribute("fromIndex1", fromIndex+1);
pageContext.setAttribute("toIndex1", toIndex);
   
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

    %>

 <%
  String Title=resource.getString("opac.simplesearch.title");
  pageContext.setAttribute("Title", Title);
  String MainEntry=resource.getString("opac.simplesearch.mainentry");
  pageContext.setAttribute("MainEntry", MainEntry);
  String CallNo=resource.getString("opac.simplesearch.callno.");
  pageContext.setAttribute("CallNo",CallNo);
  String LibraryID=resource.getString("opac.browse.table.Libraryid");
  pageContext.setAttribute("LibraryID",LibraryID);
 pageContext.setAttribute("project",request.getContextPath());
%>
<table align="center" dir="<%=rtl%>" width="70%" height="400px" class="datagrid"  >
  
     <tr  dir="<%=rtl%>">
         <td  align="center" width="80%"  valign="top" dir="<%=rtl%>" height="300px">

        <!--     <input type="text" id="pagesize" onblur="showcount()"/>-->


<%
if(tcount==0)
{
%>
<%--<i>    <%=resource.getString("opac.simplesearch.smpsearch")%> Results>></i><br/><p class="err"> <%=resource.getString("global.norecordfound")%></p>--%>
<i>    <%=resource.getString("opac.simplesearch.smpsearch")%> Results>><span class="err"> <%=tcount%> Record Found</span></i>
<%}
else
{%>
<table height="300px" width="100%" ><tr><td valign="top" align="center">
           <i>    <%=resource.getString("opac.simplesearch.smpsearch")%> Results>> <%=tcount%> Record Found</i>
<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">
  <columns>
     

    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left"/>
    
    </column>

    <column width="10%">
      <header value="${MainEntry}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.mainEntry}" hAlign="left" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  />
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
 <footer        styleClass="footer" show="true"/>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows" />

  <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>
</td></tr>
<tr><td height="5px" style="margin:0px 0px 0px 0px;" >
        <table width="100%"  border=0 class="header">
    <tr >
<td align="left" width="10%" class="datagrid">

</td>

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
 <%--<c:if test="${next != null}">
<a style="color:white;" href="<c:out value="${next}" />">  <%=pageContext.getAttribute("toIndex1")%></a>
</c:if>
--%>

</c:forEach>
    <span style="color:white;"> to <%=pageContext.getAttribute("toIndex1")%></span>&nbsp;&nbsp;
<c:if test="${next != null}">
<a style="color:white;" href="<c:out value="${next}"/>"> <b><i> <%=resource.getString("global.next")%>>></i></b></a>
</c:if>

   
</td>
<td align="right" width="10%" class="datagrid">&nbsp;
</td>
</tr>
    </table></td></tr>
<tr><td height="10px">

    </td></tr></table>
  <%}%>
  </td><%--<td valign="top"  dir="<%=rtl%>">&nbsp;&nbsp;
      <%
if(tcount!=0)
{
%>

      <i> <%=resource.getString("opac.browse.bibliodetail")%>>></i><br/>
      <%}%>
      <IFRAME  name="fr2" src="#" frameborder=0 scrolling="NO" height="400px" width="100%"  id="fr2"></IFRAME>
  </td>--%></tr></table>
  <%}else if(session.getAttribute("simple_search_list1")!=null){

String path= request.getContextPath();
pageContext.setAttribute("path", path);


 opacList = new ArrayList ();
 opacList = (ArrayList)session.getAttribute("simple_search_list1");
 int tcount=0;
if(opacList!=null)tcount =opacList.size();


   int pagesize = 10;
  //   String page1 = (String)request.getParameter("pagesize");
     //System.out.println("page1="+page1);
  // if (page1!=null) pagesize = Integer.parseInt(page1);
   fromIndex = (int)DataGridParameters.getDataGridPageIndex(request, "datagrid1");

   if ((toIndex = fromIndex+(int)pagesize) >= tcount)
   toIndex = tcount;
   //System.out.println("opacList="+opacList.size()+" tcount="+tcount);
   if(opacList!=null)request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   pageContext.setAttribute("pagesize", pagesize);
   pageContext.setAttribute("fromIndex", fromIndex);
   pageContext.setAttribute("fromIndex1", fromIndex+1);
 
   pageContext.setAttribute("toIndex1", toIndex);
 
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

    %>

 <%
  String Title=resource.getString("opac.simplesearch.title");
  pageContext.setAttribute("Title", Title);
  String MainEntry=resource.getString("opac.simplesearch.mainentry");
  pageContext.setAttribute("MainEntry", MainEntry);
  String CallNo=resource.getString("opac.simplesearch.callno.");
  pageContext.setAttribute("CallNo",CallNo);
  String LibraryID=resource.getString("opac.browse.table.Libraryid");
  pageContext.setAttribute("LibraryID",LibraryID);
  pageContext.setAttribute("project",request.getContextPath());
%>
 <table align="center" dir="<%=rtl%>" width="70%" height="400px" class="datagrid"  >

     <tr  dir="<%=rtl%>">
         <td  align="center" width="80%"  valign="top" dir="<%=rtl%>" height="300px">

        <!--     <input type="text" id="pagesize" onblur="showcount()"/>-->


<%
if(tcount==0)
{
%>
<%--<i>    <%=resource.getString("opac.simplesearch.smpsearch")%> Results>></i><br/><p class="err"> <%=resource.getString("global.norecordfound")%></p>--%>
<i>    <%=resource.getString("opac.simplesearch.smpsearch")%> Results>><span class="err"> <%=tcount%> Record Found</span></i>
<%}
else
{%>
<table height="300px" width="100%" ><tr><td valign="top" align="center">
           <i>    <%=resource.getString("opac.simplesearch.smpsearch")%> Results>> <%=tcount%> Record Found</i>
<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">
  <columns>


    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left"/>

    </column>

    <column width="10%">
      <header value="${MainEntry}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.mainEntry}" hAlign="left" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  />
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
 <footer        styleClass="footer" show="true"/>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows" />

  <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>
</td></tr>
<tr><td height="5px" style="margin:0px 0px 0px 0px;" >
        <table width="100%"  border=0 class="header">
    <tr >
<td align="left" width="10%" class="datagrid">

</td>

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
 <%--<c:if test="${next != null}">
<a style="color:white;" href="<c:out value="${next}" />">  <%=pageContext.getAttribute("toIndex1")%></a>
</c:if>
--%>

</c:forEach>
    <span style="color:white;"> to <%=pageContext.getAttribute("toIndex1")%></span>&nbsp;&nbsp;
<c:if test="${next != null}">
<a style="color:white;" href="<c:out value="${next}"/>"> <b><i> <%=resource.getString("global.next")%>>></i></b></a>
</c:if>


</td>
<td align="right" width="10%" class="datagrid">&nbsp;
</td>
</tr>
    </table></td></tr>
<tr><td height="10px">

    </td></tr></table>
  <%}%>
  </td><%--<td valign="top"  dir="<%=rtl%>">&nbsp;&nbsp;
      <%
if(tcount!=0)
{
%>

      <i> <%=resource.getString("opac.browse.bibliodetail")%>>></i><br/>
      <%}%>
      <IFRAME  name="fr2" src="#" frameborder=0 scrolling="NO" height="400px" width="100%"  id="fr2"></IFRAME>
  </td>--%></tr></table>

  <%}%>
    </body>
</html>


