<%--
    Document   : Simple.jsp
    Created on : Jun 18, 2010, 7:46:24 AM
    Author     : Mayank Saxena
--%>
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
<meta name="Mayank Saxena" content="MCA,AMU">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

<script language="javascript" >
function b1click()
{
location.href="<%=request.getContextPath()%>/OPAC/simple.jsp";
}
function b2click()
{
f.action="<%=request.getContextPath()%>/OPAC/opachome.jsp";
f.method="post";
f.target="_self";
f.submit();
}
function getQuery(id)
{
    var query = "MyResultSet.do?id=(select * from document_details where call_no='"+id+"')";
    return query;
}
function showcount()
{
    var page;
    page = document.getElementById("pagesize").value;
    if (page>0){
    location.href="<%=request.getContextPath()%>/OPAC/simple_search.jsp?pagesize="+page;}
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
<body bgcolor="#FFFFFF">


<%!

   ResultSet rs=null;
   OpacDoc Ob;
   ArrayList opacList;
   int fromIndex, toIndex;
   static Integer count=0;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
%>

<%



 opacList = new ArrayList ();
 opacList = (ArrayList)session.getAttribute("documentdetail");
 int tcount=0;
 if(opacList!=null)
    tcount=opacList.size();
   int perpage=10;
   int tpage=0;
   int pagesize = 10;
     String page1 = (String)request.getParameter("pagesize");
     //System.out.println("page1="+page1);
   if (page1!=null) pagesize = Integer.parseInt(page1);
  
   fromIndex = (int)DataGridParameters.getDataGridPageIndex(request, "datagrid1");
   if ((toIndex = fromIndex+(int)pagesize) >= tcount)
   toIndex = tcount;
   //System.out.println("opacList="+opacList.size()+" tcount="+tcount);
   if(opacList!=null)request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   pageContext.setAttribute("pagesize", pagesize);
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
 <%-- <%if(page.equals(true)){%>--%>
<table align="left" width="1200x" height="400px" class="datagrid" style="border:solid 1px #e0e8f5;">



    <tr style="background-color:#e0e8f5;"><td  width="800px" rowspan="2"  height="18px" align="center" colspan="2">


		Search Result





        </td><td valign="top" align="center">
    Biblograhic Details
  </td></tr>
  <tr style="background-color:#e0e8f5;" height="10px">
  <td valign="top" rowspan="2">
  <IFRAME  name="fr2" src="#" frameborder=0 scrolling="NO" height="400px"  id="fr2"></IFRAME>
  </td>
     </tr>
     <tr style="background-color:#e0e8f5;">
         <td colspan="2" align="center" valign="top" height="300px">

        <!--     <input type="text" id="pagesize" onblur="showcount()"/>-->


<%




if(tcount==0)
{
%>
<p class="err">No record Found</p>
<%}
else
{%>
<table height="300px" ><tr><td valign="top">
<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">

  <columns>



    <column width="450">
      <header value="Title" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="./viewDetails.do?doc_id=${doc.id.documentId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2" hAlign="left"/>
    </column>

    <column width="200">
      <header value="Main Entry" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.mainEntry}" hyperLink="./viewDetails.do?doc_id=${doc.id.documentId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2" hAlign="left"   />
    </column>

    <column width="100">
      <header value="Call No." hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.callNo}" hyperLink="./viewDetails.do?doc_id=${doc.id.documentId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2"  hAlign="left" />
    </column>

      <column width="150">
      <header value="Library ID" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.id.libraryId}" hyperLink="./viewDetails.do?doc_id=${doc.id.documentId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2" hAlign="left" />
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>
</td></tr>
<tr><td height="5px" style="margin:0px 0px 0px 0px;" >
        <table width="900"  border=0 class="header">
    <tr >
<td align="left" width="10%" class="datagrid">
<c:if test="${previous != null}">
    <a style="color:white;" href="<c:out value="${previous}"/>">Previous</a>
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
<a style="color:white;" href="<c:out value="${next}"/>">Next</a>
</c:if>
</td>
</tr>
    </table></td></tr>
<tr><td height="10px">

    </td></tr></table>
  <%}%>


  </td></tr></table>
 <%-- <%}else {%>
  <table align="left" width="1200x" height="400px" class="datagrid" style="border:solid 1px #e0e8f5;">



    <tr style="background-color:#e0e8f5;"><td valign="top" align="center">
    Biblograhic Details
  </td><td  width="800px" rowspan="2"  height="18px" align="center" colspan="2">


		Search Result





        </td></tr>
  <tr style="background-color:#e0e8f5;" height="10px">
  <td valign="top" rowspan="2">
  <IFRAME  name="fr2" src="#" frameborder=0 scrolling="NO" height="400px"  id="fr2"></IFRAME>
  </td>
     </tr>
     <tr style="background-color:#e0e8f5;">
         <td colspan="2" align="center" valign="top" height="300px">




<%




if(tcount==0)
{
%>
<p class="err">No record Found</p>
<%}
else
{%>
<table height="300px" ><tr><td valign="top">
<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">

  <columns>



    <column width="450">
      <header value="Title" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.title}" hAlign="left"/>
    </column>

    <column width="200">
      <header value="Author" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.author}" hAlign="left"  />
    </column>

    <column width="100">
      <header value="Call No." hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.callno}" hAlign="left" />
    </column>

      <column width="150">
      <header value="Library ID" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.library_id}" hAlign="left" />
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="10" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>
</td></tr>
<tr><td height="5px" style="margin:0px 0px 0px 0px;" >
        <table width="900"  border=0 class="header">
    <tr >
<td align="left" width="10%" class="datagrid">
<c:if test="${previous != null}">
    <a style="color:white;" href="<c:out value="${previous}"/>">Previous</a>
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
<a style="color:white;" href="<c:out value="${next}"/>">Next</a>
</c:if>
</td>
</tr>
    </table></td></tr>
<tr><td height="10px">

    </td></tr></table>
  <%}%>


  </td></tr></table>
  <%}%>--%>

    </body>

</html>


