<%--
    Document   : Additional.jsp
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
   String p,callno,publ,loc,place,cf,cmbyr,db,title,author,accno;
   String cnf,op1,op2,yr1,yr2,sort,query="";
   OpacDoc Ob;
   ArrayList opacList;
   
   int fromIndex=0, toIndex;

   int tcount =0;
   int perpage=10;
   int tpage=0;
%>

<%
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
 <%-- <%if(page.equals(true)){%>--%>

 <table align="<%=align%>" dir="<%=rtl%>" width="1200x" height="400px" class="datagrid" style="border:solid 1px #e0e8f5;">


  <tr style="background-color:#e0e8f5;" dir="<%=rtl%>"><td  width="800px" dir="<%=rtl%>" height="28px" align="center" rowspan="2" colspan="2">


		<%=resource.getString("opac.additional.advancesearch")%>




        </td><td valign="top" align="center" dir="<%=rtl%>">
    <%=resource.getString("opac.browse.bibliodetail")%>
  </td></tr>
  <tr style="background-color:#e0e8f5;" height="10px" dir="<%=rtl%>">
  <td valign="top" rowspan="2" dir="<%=rtl%>">
  <IFRAME  name="fr2" src="#" frameborder=0 scrolling="NO" height="400px"  id="fr2"></IFRAME>
  </td>
     </tr>
     <tr style="background-color:#e0e8f5;" dir="<%=rtl%>">
         <td colspan="2" align="center" valign="top" dir="<%=rtl%>" height="300px">





<%




if(tcount==0)
{
%>
<p class="err">No record Found</p>
<%}
else
{%>
<table height="300px" dir="<%=rtl%>"><tr><td valign="top" dir="<%=rtl%>">
<ui:dataGrid items="${opacList}"  var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">

  <columns>



    <column width="450">
      <header value="Title" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.title}" hyperLink="./viewDetails.do?doc_id=${doc.id.documentId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2" hAlign="left" />
    </column>

    <column width="200">
      <header value="Main Entry" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.mainEntry}" hAlign="left" hyperLink="./viewDetails.do?doc_id=${doc.id.documentId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2"  />
    </column>
      <column width="150">
      <header value="Library ID" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.id.libraryId}" hyperLink="./viewDetails.do?doc_id=${doc.id.documentId}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}" hyperLinkTarget="fr2" hAlign="left" />
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
<td align="<%=align%>" dir="<%=rtl%>" width="10%" class="datagrid">
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
<form name="f">
  <div align="<%=align%>" dir="<%=rtl%>">
    <input type="button" name="b1" value="Back..." onclick="b1click()">
  
  </div>
</form>
    </td></tr></table>
  <%}%>


  </td></tr></table>
 
    </body>

</html>

