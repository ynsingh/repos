
  
<%--
    Document   : isbn
    Created on : Jun 22, 2010, 1:15:37 PM
    Author     : Mayank Saxena
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.myapp.struts.opac.OpacDoc"%>
<%@ page import="java.util.*"%>
 <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
 <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %> 
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html" %>
<%@ page import="java.util.*" %>
<html>
<head>
<title>Search by ISBN..</title>
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
<script language="javascript">
function b1click()
{
location.href="isbn.jsp";
}
function b2click()
{
f.action="home.htm";
f.method="post";
f.target="_top";
f.submit();
}

</script>
   <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
</head>
<body bgcolor="#FFFFFF">
<%! /*Retrieving the values from HTML page..*/
    String isbn; 
    String title;
    String author; String accno;
    OpacDoc Ob;
   ArrayList opacList;
   int fromIndex, toIndex;
   ResultSet rs=null;
  %>
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
 <table width="600" style="font-family: arial; font-size: 10pt" border=0>
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
<td align="left" width="10%">&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>">Next</a>
</c:if>
</td>
</tr>
</table>
  <%}%><%}%>
<br>
<br>
<br>
<br>
<br>
<br>

<%--<form name="f">
  <div align="left">
    <input type="button" name="b1" value="Back..." onclick="b1click()">
    <input type="button" name="b2" value="Home..." onclick="b2click()">
  </div>
</form>--%>
</body>
<IFRAME  name="fr2" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:25px;left:420px;height:370px;width:450px;visibility:true;" id="fr2"></IFRAME>
</html>