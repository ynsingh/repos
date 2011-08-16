<%--
    Document   : serial_approval
    Created on : Jan 20, 2011, 4:49:12 PM
    Author     : Client
--%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script language="javascript">
function fun()
{

      //  document.f.target = "f1";
//document.f.target="f1";

document.f.submit();
}
function b1click()
{
location.href="<%=request.getContextPath()%>/OPAC/simple.jsp";
}
function b2click()
{
location.href="<%=request.getContextPath()%>/OPAC/home.html";
f.method="post";
f.target="_self";
f.submit();
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
    <body>
<%!

   ResultSet rs=null;
   ArrayList opacList;
   int fromIndex, toIndex;
   static int i=0;
%>
<%
//i++;
   opacList = new ArrayList ();
   opacList=(ArrayList)session.getAttribute("opacList");
   int tcount =0;
   int perpage=4;
   int tpage=0;
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");

   tcount=opacList.size();
   if ((toIndex = fromIndex+4) >= opacList.size ())
   toIndex = opacList.size();
   request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
<br><br>
<%
if(tcount==0)
{
%>
<p class="err">No record Found</p>
<%}
else
{%>
<form id="f" name="f" action="serialinitiate.do">
    <input type="text" name="list" id="list"/>
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0"
    cellSpacing="0" styleClass="datagrid"  >

  <columns>
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
      <header value="Control No." hAlign="left" styleClass="header"/>
      <item   value="${doc.id.controlNo}"  hAlign="left"  styleClass="item"/>
    </column>
      <column width="100">
      <header value="No of Copies" hAlign="left" styleClass="header"/>
      <item   value="${doc.noOfCopies}"  hAlign="left"  styleClass="item"/>
    </column>
  <columns>
      <column width="50">
        <header value="select" halign="left" styleClass="header"/>
        <item>
            <![CDATA[
            <input type="checkbox" name="check" align="right" id="check"  value="${doc.acqBibliography.title},${doc.id.controlNo},${doc.noOfCopies}" onClick="javascript:get_check_value();">
            ]]>
          </item>
    </column>
      <column width="50">
        <header value="select" halign="left" styleClass="header"/>
        <item>
            <![CDATA[
            <div id="c" onclick="javascript:popalert();"><input type="checkbox" name="check" align="right" id="check"  value="${doc.acqBibliography.title},${doc.id.controlNo},${doc.noOfCopies}" onClick="javascript:get_check_value();"></div>
            ]]>
          </item>
    </column>
  </columns>
</columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

<table width="750" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>">Next</a>
</c:if>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

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

</tr>
</table>
  <%}%>
  <script language="javascript">


function get_check_value()
{
var c_value  ;

for (var i=0; i < document.f.check.length; i++)
   {
       if (document.f.check[i].checked)
      {

           if(c_value==null)
            c_value =document.f.check[i].value;
           else
           {
              c_value = c_value +";"+ document.f.check[i].value;
           }
       }
   }



 //  alert(c_value);
   document.f.list.value=c_value;

}


var win = null;
function popWin(loc) {
win =
window.open('','WIN','width=300,height=100,scrollb ars,location=middle,menubar=no,toolbar=no');
if (win != null) {
if (win.opener == null) {
win.opener = self;
}
win.location.href = loc;
}
}
function popalert(){
    document.getElementById('c').innerHTML =
        "<input type='text' />";
}

  </script>



 </form>
    </body>

</html>
