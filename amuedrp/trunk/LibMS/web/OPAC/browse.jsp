
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>

 
    <script language="javascript" type="text/javascript ">
        function loadHelp()
        {
            window.status="Press F1 for Help";
        }
    </script>
</head>
<body style="margin:0px 0px 0px 0px 0px;background-color:#e0e8f5;" onload="loadHelp()">


<%!

   ResultSet rs=null;
   OpacDoc Ob;
   ArrayList opacList;
   int fromIndex, toIndex;
   static Integer count=0;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    int tcount =0;
%>

<%

if(session.getAttribute("browse_search_list")!=null){

 opacList = new ArrayList ();
   
   int perpage=10;
   int tpage=0;
opacList=(ArrayList)session.getAttribute("browse_search_list");
if(opacList!=null)
    tcount=opacList.size();
 
   fromIndex = (int)DataGridParameters.getDataGridPageIndex(request, "datagrid1");
   if ((toIndex = fromIndex+10) >= tcount)
   toIndex = tcount;
   System.out.println("opacList="+tcount+" tcount="+tcount);
   if(opacList!=null)request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);

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
pageContext.setAttribute("project",request.getContextPath());
  pageContext.setAttribute("fromIndex", fromIndex);
   pageContext.setAttribute("fromIndex1", fromIndex+1);
 
   pageContext.setAttribute("toIndex1", toIndex);

%>

    <table align="<%=align%>" dir="<%=rtl%>"  class="datagrid" >



        <tr ><td   dir="<%=rtl%>"   height="18px" align="<%=align%>" >


		<i><%--<%=resource.getString("opac.browse.browsesearchresult")%>--%> Browse Search Result >> <%=tcount%> Record Found</i>





        </td><%--<td valign="top" align="left" dir="<%=rtl%>">
    <i><%=resource.getString("opac.browse.bibliodetail")%>>></i>
  </td>--%></tr>
  
  
      
  
  
     <tr style="background-color:#e0e8f5;" dir="<%=rtl%>">
         <td  align="center" width="80%" valign="top" dir="<%=rtl%>" height="300px">




<%




if(tcount==0)
{
%>
 <i>    Browse Search Results>><span class="err"> <%=tcount%> Record Found</span></i>
<%}
else
{%>
<table  dir="<%=rtl%>"><tr><td valign="top" dir="<%=rtl%>">
<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">

  <columns>

 <column width="15%">
      <header value="${Title}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left"/>
    </column>

    <column width="15%">
      <header value="${MainEntry}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.mainEntry}" hAlign="left" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"   />
    </column>
 <column width="10%">
      <header value="PublisherName" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.publisherName}" hAlign="left" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  />
    </column>
 <column width="10%">
      <header value="PublicationPlace" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.publicationPlace}" hAlign="left" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  />
    </column>
 

      <column width="10%">
      <header value="CallNo" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.callNo}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hAlign="left" />
    </column>

      <column width="10%">
      <header value="${LibraryID}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.id.libraryId}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hAlign="left" />
    </column>
       <column width="10%">
      <header value="SubLibrary" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.id.sublibraryId}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left" />
    </column>

   
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="10" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

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
</tr>
</table>
  <%}%>


         </td><%--<td valign="top">
             <IFRAME  name="fr2" src="#" width="100%" frameborder=0 scrolling="no" height="400px"  id="fr2"></IFRAME></td>--%></tr></table>

  <%}else if(session.getAttribute("browse_search_list1")!=null){

   opacList = new ArrayList ();

   int perpage=10;
   int tpage=0;
opacList=(ArrayList)session.getAttribute("browse_search_list1");
if(opacList!=null)
    tcount=opacList.size();

   fromIndex = (int)DataGridParameters.getDataGridPageIndex(request, "datagrid1");
   if ((toIndex = fromIndex+10) >= tcount)
   toIndex = tcount;
   System.out.println("opacList="+tcount+" tcount="+tcount);
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

pageContext.setAttribute("project",request.getContextPath());
  pageContext.setAttribute("fromIndex", fromIndex);
   pageContext.setAttribute("fromIndex1", fromIndex+1);
   pageContext.setAttribute("toIndex1", toIndex);
%>

   <table align="<%=align%>" width="100%" dir="<%=rtl%>"  class="datagrid" >



        <tr ><td   dir="<%=rtl%>"   height="18px" align="<%=align%>" >


		<i><%--<%=resource.getString("opac.browse.browsesearchresult")%>--%> Browse Search Result >> <%=tcount%> Record Found</i>





        </td><%--<td valign="top" align="left" dir="<%=rtl%>">
    <i><%=resource.getString("opac.browse.bibliodetail")%>>></i>
  </td>--%></tr>





     <tr style="background-color:#e0e8f5;" dir="<%=rtl%>">
         <td  align="center" width="80%" valign="top" dir="<%=rtl%>" height="300px">




<%




if(tcount==0)
{
%>
 <i>    Browse Search Results>><span class="err"> <%=tcount%> Record Found</span></i>
<%}
else
{%>
<table width="80%" dir="<%=rtl%>">
    <tr><td valign="top" dir="<%=rtl%>">

<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">

  <columns>

 <column width="25%">
      <header value="${Title}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left"/>
    </column>

    <column width="25%">
      <header value="${MainEntry}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.mainEntry}" hAlign="left" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"   />
    </column>
 <column width="10%">
      <header value="PublisherName" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.publisherName}" hAlign="left" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  />
    </column>
 <column width="10%">
      <header value="PublicationPlace" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.publicationPlace}" hAlign="left" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  />
    </column>


      <column width="10%">
      <header value="CallNo" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.callNo}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hAlign="left" />
    </column>

      <column width="10%">
      <header value="${LibraryID}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.id.libraryId}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hAlign="left" />
    </column>
       <column width="10%">
      <header value="SubLibrary" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.id.sublibraryId}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.id.biblioId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left" />
    </column>


 </columns>

 

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="10" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>
</td></tr>


  <%}%>
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

  </td></tr>

   </table>



  <%}%>

    </body>

</html>
