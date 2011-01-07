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
    <title>Simple Search...</title>
    <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>

<script language="javascript" >
function b1click()
{
location.href="/LibMS-Struts/OPAC/simple.jsp";
}
function b2click()
{
f.action="/LibMS-Struts/OPAC/home.html";
f.method="post";
f.target="_self";
f.submit();
}
function getQuery(id)
{
    var query = "MyResultSet.do?id=(select * from document where accessionno='"+id+"')";
    return query;
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

<%!
   String p,callno,publ,loc,place,cf,cmbyr,db,title,author,accno;
   String cnf,op1,op2,yr1,yr2,sort,query="";
   OpacDoc Ob;
   ArrayList opacList;
   int fromIndex=0, toIndex;

   int tcount =0;
   int perpage=4;
   int tpage=0;
%>
 <%
opacList = new ArrayList ();
 ResultSet rs = (ResultSet)(session.getAttribute("simple_resultset"));
    if(rs!=null)
    {
      // System.out.println(rs);
        /* if(cnf.equals("and"))
          {
            if(cmbyr.equals("between")){query=query+" and pub_yr between "+yr1+" and "+yr2;}
            if(cmbyr.equals("upto"))   {query=query+" and pub_yr <="+yr1;}
            if(cmbyr.equals("after"))  {query=query+" and pub_yr >="+yr1;}
          }*/

   
 /*Create a connection by using getConnection() method
   that takes parameters of string type connection url,
   user name and password to connect to database.*/
tcount=0;
rs.beforeFirst();

   while (rs.next()) {
	tcount++;
	Ob = new OpacDoc ();
	Ob.setTitle(rs.getString("title"));
	Ob.setAuthor(rs.getString("author"));
	Ob.setCallno(rs.getString("callno"));
	Ob.setAccessionno(rs.getString("accessionno"));
        Ob.setLocation(rs.getString("location"));
	Ob.setPublisher(rs.getString("publisher"));
        Ob.setPubplace(rs.getString("pubplace"));
        Ob.setLibrary_id(rs.getString("library_id"));
   opacList.add(Ob);
   System.out.println("tcount="+tcount);
		     }


System.out.println("tcount="+tcount);
}
else{
%>

<%}
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
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
<ui:dataGrid items="${opacList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">
    
  <columns>
      
    

    <column width="250">
      <header value="Title" hAlign="left" styleClass="header"/>
      <item   value="${doc.title}" hyperLink="/LibMS-Struts/OPAC/MyResultSet.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="fr2" hAlign="left"
	      styleClass="item"/>
    </column>

    <column width="200">
      <header value="Author" hAlign="left" styleClass="header"/>
      <item   value="${doc.author}" hAlign="left" hyperLink="/LibMS-Struts/OPAC/MyResultSet.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="fr2"  styleClass="item"/>
    </column>

    <column width="100">
      <header value="Call No." hAlign="left" styleClass="header"/>
      <item   value="${doc.callno}" hyperLink="/LibMS-Struts/OPAC/MyResultSet.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="fr2" hAlign="left" styleClass="item"/>
    </column>

      <column width="150">
      <header value="Library ID" hAlign="left" styleClass="header"/>
      <item   value="${doc.library_id}" hyperLink="/LibMS-Struts/OPAC/MyResultSet.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="fr2" hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
<table width="600" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="10%">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
</c:if>&nbsp;
</td>

<td align="center" width="10%">
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
<form name="f">
  <div align="left">
    <input type="button" name="b1" value="Back..." onclick="b1click()">
    <input type="button" name="b2" value="Home..." onclick="b2click()">
  </div>
</form>
  <%}%>
    </body>
<IFRAME  name="fr2" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:25px;left:620px;height:370px;width:450px;visibility:true;" id="fr2"></IFRAME>
</html>
