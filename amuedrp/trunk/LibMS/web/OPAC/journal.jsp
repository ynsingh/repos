<%--
 Kindly Paste all jar files given in lib directory in Apache\lib directory..
    Document   : Browse.jsp
    Created on : Aug 5, 2010, 12:46:07 PM
    Author     : Mayank Saxena
--%>
    <%@page import="com.myapp.struts.opac.OpacDoc"%>
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
<meta name="Mayank Saxena" content="MCA,AMU">
<title></title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
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
   <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
  </head>
  <body>
  <span align="center" style="padding-left:250px;font-size:20px;"></span>
<%!
   
   String isbn;
    String title;
    String author; String accno;
    OpacDoc Ob;
   ArrayList opacList;
   int fromIndex, toIndex;
   ResultSet rs=null;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
%>
<%
  

 opacList = new ArrayList ();
   int tcount =0;
   int perpage=10;
   int tpage=0;
 /*Create string of connection url within specified
    format with machine name, port number and database name.
    Here machine name id localhost and
    database name is library.*/

 rs= (ResultSet)session.getAttribute("journalRs");
 rs.beforeFirst();
 while (rs.next()) {
	tcount++;
	Ob = new OpacDoc ();
	Ob.setTitle(rs.getString("title"));
	Ob.setCallno(rs.getString("callno"));
	Ob.setAccessionno(rs.getString("accessionno"));
        Ob.setLocation(rs.getString("location"));
	Ob.setPublisher(rs.getString("publisher"));
        Ob.setPubplace(rs.getString("pubplace"));
        Ob.setLibrary_id(rs.getString("library_id"));
   opacList.add(Ob);
		     }
 rs=null;
session.setAttribute("page_name", "newarrivals");

   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+10) >= opacList.size ())
   toIndex = opacList.size();
   request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
  <%if(page.equals(true)){%>
<table align="left" width="1200x" height="400px" class="datagrid" style="border:solid 1px #e0e8f5;">


    <tr style="background-color:#e0e8f5;"><td  width="800px" rowspan="2"  height="18px" align="center" colspan="2">


		Search Result





        </td><td valign="top" align="center">
    Biblograhic Details
  </td></tr>
  <tr style="background-color:#e0e8f5;" height="10px">
  <td valign="top" rowspan="2">
  <IFRAME  name="f2" src="#" style="background-color:#e0e8f5;" frameborder=0 scrolling="NO" height="400px"  id="f2"></IFRAME>
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
      <item  styleClass="item"  value="${doc.title}" hyperLink="/LibMS-Struts/OPAC/MyResultSet1.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="f2" hAlign="left"/>
    </column>

    <column width="200">
      <header value="Author" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.author}" hAlign="left" hyperLink="/LibMS-Struts/OPAC/MyResultSet1.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'"  hyperLinkTarget="f2"  />
    </column>

    <column width="100">
      <header value="Call No." hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.callno}" hyperLink="/LibMS-Struts/OPAC/MyResultSet1.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'"  hyperLinkTarget="f2" hAlign="left" />
    </column>

      <column width="150">
      <header value="Library ID" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.library_id}" hyperLink="/LibMS-Struts/OPAC/MyResultSet1.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'"  hyperLinkTarget="f2" hAlign="left" />
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
<%}else{%>
<table align="left" width="1200x" height="400px" class="datagrid" style="border:solid 1px #e0e8f5;">


    <tr style="background-color:#e0e8f5;"><td valign="top" align="center">
    Biblograhic Details
  </td><td  width="800px" rowspan="2"  height="18px" align="center" colspan="2">


		Search Result





        </td></tr>
  <tr style="background-color:#e0e8f5;" height="10px">
  <td valign="top" rowspan="2">
  <IFRAME  name="f2" src="#" style="background-color:#e0e8f5;" frameborder=0 scrolling="NO" height="400px"  id="f2"></IFRAME>
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
      <item  styleClass="item"  value="${doc.title}" hyperLink="/LibMS-Struts/OPAC/MyResultSet1.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="f2" hAlign="left"/>
    </column>

    <column width="200">
      <header value="Author" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.author}" hAlign="left" hyperLink="/LibMS-Struts/OPAC/MyResultSet1.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'"  hyperLinkTarget="f2"  />
    </column>

    <column width="100">
      <header value="Call No." hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.callno}" hyperLink="/LibMS-Struts/OPAC/MyResultSet1.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'"  hyperLinkTarget="f2" hAlign="left" />
    </column>

      <column width="150">
      <header value="Library ID" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.library_id}" hyperLink="/LibMS-Struts/OPAC/MyResultSet1.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'"  hyperLinkTarget="f2" hAlign="left" />
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
<%}%>

</html>


