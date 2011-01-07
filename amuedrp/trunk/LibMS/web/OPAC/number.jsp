
<%@page import="com.myapp.struts.opac.OpacDoc"%>
<%--
    Document   : Number.jsp
    Created on : July 12, 2010, 3:25:37 PM
    Author     : Mayank Saxena
--%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
  
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
<title>Search by Specific Number..</title>
<style type="" >
    th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold }
     .alternaterows { background-color: #efefef }
     .header        { background-color: #c0003b; color: #FFFFFF;font-weight: bold }

     .datagrid      { border: 1px solid #C7C5B2; font-family: arial; font-size: 9pt;
	    font-weight: normal }
</style>
<script type=""  language="javaScript" src="fulldetail.js"></script>
<link rel="stylesheet" href="result.css">
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
</head>
<body bgcolor="#FFFFFF">
<%--<button class='buttonclass' onmousedown="fontResize('-5')">Font Size -&nbsp;-</button>
<button class='buttonclass' onmousedown="fontResize('+5')">Font Size ++</button>--%>
<%!
   
   ResultSet rs=null;
   String phrase;
   String db,sort,field;
   ArrayList opacList;
   OpacDoc Ob;
   int fromIndex, toIndex;
    String no,callno,publ,loc,place,title,author,accno,query="";
   String pubyr,copy,vol,ed,isbn,subtitle,subject,page;
%>

<!--font color="red" face="CALIBRI"><//=query%></font-->
<%
  opacList = new ArrayList ();
   int tcount =0;
   int perpage=4;
   int tpage=0;

      rs= (ResultSet)session.getAttribute("Result");
    System.out.println(rs);
   if (rs!=null)
       {
    while (rs.next()) {
	tcount++;
	Ob = new OpacDoc();
	Ob.setTitle(rs.getString("title"));
	Ob.setAuthor(rs.getString("author"));
	Ob.setCallno(rs.getString("callno"));
	Ob.setAccessionno(rs.getString("accessionno"));
        Ob.setLocation(rs.getString("location"));
	Ob.setPublisher(rs.getString("publisher"));
        Ob.setPubplace(rs.getString("pubplace"));
        Ob.setLibrary_id(rs.getString("library_id"));
   opacList.add(Ob);
   System.out.println(tcount);
		     }
System.out.println("tcount="+tcount);
rs.beforeFirst();
rs.next();

   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= opacList.size ())
   toIndex = opacList.size();
   System.out.println("opacList="+opacList.size()+" tcount="+tcount);
   request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);




%>
<!--jsp:include page="isbn.html"/-->
<br><br>
<br><br>
<%
if(tcount==0)
{
%>
<p class="err">No record Found</p>
<%}
else
{%>
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0"
    cellSpacing="0" styleClass="datagrid" >

  <columns>


    <column width="100">
      <header value="Title" hAlign="left" styleClass="header"/>
      <item   value="${doc.title}" hyperLink="MyResultSet.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="fr2" hAlign="left"
	      styleClass="item"/>
    </column>

    <column width="100">
      <header value="Author" hAlign="left" styleClass="header"/>
      <item   value="${doc.author}" hAlign="left" hyperLink="MyResultSet.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="fr2"  styleClass="item"/>
    </column>

    <column width="100">
      <header value="Call No." hAlign="left" styleClass="header"/>
      <item   value="${doc.callno}" hyperLink="MyResultSet.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="fr2" hAlign="left" styleClass="item"/>
    </column>
     <column width="100">
      <header value="Library ID" hAlign="left" styleClass="header"/>
      <item   value="${doc.library_id}" hyperLink="MyResultSet.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="fr2" hAlign="left" styleClass="item"/>
    </column>

 </columns>
  <rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
<table width="550" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="11%">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
</c:if>&nbsp;
</td>
<td align="left" width="10%">
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
<td align="right" width="10%">&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>">Next</a>
</c:if>
</td>
</tr>
</table>
  <%}%>
<%}%>
</body>
<IFRAME  name="fr2" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:25px;left:400px;height:470px;width:1000px;visibility:true;" id="fr2"></IFRAME>
</html>