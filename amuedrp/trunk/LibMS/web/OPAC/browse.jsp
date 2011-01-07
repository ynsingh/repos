
    
<%@page import="com.myapp.struts.opac.OpacDoc"%>
<%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
     <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
<title>Browsing.....</title>
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
  </head>
  <body>
         <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
  <span align="center" style="padding-left:250px;font-size:20px;" dir="rtl"></span>
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
   int tcount =0;
   int perpage=4;
   int tpage=0;

      rs= (ResultSet)session.getAttribute("Result");
    System.out.println(rs);
   if (rs!=null)
{
       rs.beforeFirst();

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


   fromIndex = (int)DataGridParameters.getDataGridPageIndex(request, "datagrid1");
   if ((toIndex = fromIndex+4) >= opacList.size ())
   toIndex = opacList.size();
   System.out.println("opacList="+opacList.size()+" tcount="+tcount);
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
    <%--<%=resource.getString("opac.browse.table.Title")%>--%>

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

    <column width="50">
      <header value="" hAlign="left" styleClass="header"/>
    </column>

    <column width="250">
      <header value="Title" hAlign="left" styleClass="header"/>
      <item   value="${doc.title}" hyperLink="MyResultSet.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="f2" hAlign="left"
	      styleClass="item"/>
    </column>

    <column width="200">
      <header value="Author" hAlign="left" styleClass="header"/>
      <item   value="${doc.author}" hAlign="left" hyperLink="MyResultSet.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="f2"  styleClass="item"/>
    </column>

    <column width="100">
      <header value="Call No." hAlign="left" styleClass="header"/>
      <item   value="${doc.callno}" hyperLink="MyResultSet.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="f2" hAlign="left" styleClass="item"/>
    </column>
     <column width="150">
      <header value="Library ID" hAlign="left" styleClass="header"/>
      <item   value="${doc.library_id}" hyperLink="MyResultSet.do?id=select * from document where accessionno='${doc.accessionno}' and library_id='${doc.library_id}'" hyperLinkTarget="f2" hAlign="left" styleClass="item"/>
    </column>
      
 </columns>
  <rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
<table width="750" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="33%">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
</c:if>&nbsp;
</td>
<td align="center" width="33%">
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
<td align="right" width="33%">&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>">Next</a>
</c:if>
</td>
</tr>
</table>
<%}%><%}%>
</body>
<IFRAME  name="fr2" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:25px;left:720px;height:370px;width:450px;visibility:true;" id="fr2"></IFRAME>
</html>

