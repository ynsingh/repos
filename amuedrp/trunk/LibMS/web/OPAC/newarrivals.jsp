 <%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*"%>
   
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
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<%
String lib_id=(String)session.getAttribute("library_id");
String sublib_id = (String)session.getAttribute("memsublib");
        if(sublib_id==null)sublib_id= (String)session.getAttribute("sublibrary_id");
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
String pub_name=resource.getString("cataloguing.catoldtitleentry1.publishername");
  pageContext.setAttribute("pub_name", pub_name);
%>
</head>


<body style="background-color:#e0e8f5;">
 <table  width="100%" align="center">



     <%--  <tr ><td align="center" class="header" height="25px;" align="<%=align%>" dir="<%=rtl%>"> <%=resource.getString("opac.newarrivals.viewnewarrival")%></td></tr>--%>
                <tr><td valign="top" align="center"> <br/>


<%


%>

<%!

   //ResultSet rs=null;
   ArrayList opacList;
   int fromIndex, toIndex;
%>

<%
   String msg=(String)request.getAttribute("msg");
   opacList = new ArrayList ();
   int tcount =0;
   int perpage=4;
   int tpage=0;
   opacList=(ArrayList)session.getAttribute("newarrival");
   session.removeAttribute("newarrival");
   if(opacList!=null)
   {
   
   
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");

   tcount=opacList.size();
   if ((toIndex = fromIndex+4) >= opacList.size ())
   toIndex = opacList.size();
   request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   }
%>
<%

%>
<%if( tcount==0)
{%>
<p class="err" style="font-size:12px"> <%=resource.getString("global.norecordfound")%></p>
<%}
else
{%>
 <i>NewArrials Search>> <%=tcount%> records found</i>

<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">

  <columns>



    <column width="250">
      <header value="${Title}" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.title}"   hAlign="left"/>
    </column>

    <column width="200">
      <header value="${MainEntry}" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.mainEntry}" hAlign="left"  />
    </column>
          <column width="200">
      <header value="${pub_name}" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.publisherName}" hAlign="left"  />
    </column>
      <column width="150">
      <header value="${LibraryID}" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.id.libraryId}"   hAlign="left" />
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="10" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>
<table width="700" style="font-family: arial; font-size: 10pt" border=0>
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
  <br><br><br>
  </td></tr>
 </table>


    </body>


</html>

