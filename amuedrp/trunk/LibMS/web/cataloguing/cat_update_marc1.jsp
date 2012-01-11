<%--
    Document   : cat_viewAll_biblio
    Created on : Mar 15, 2011, 12:05:56 PM
    Author     : EdRP-04
--%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"%>
    <%@ page import="java.util.ResourceBundle"%>
    <%@ page import="java.util.Locale"%>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
      <title></title>
<jsp:include page="/admin/header.jsp"/>
<script type="text/javascript">
function send()
{
    location.href="<%= request.getContextPath() %>/cataloguing/cat_new_MARC.jsp";
    return false;
}
</script>
            <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
</head>

<body bgcolor="#FFFFFF">
    <div
   style="  top:150px;
   left:100px;
   right:5px;
      position: absolute;

      visibility: show;">
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    
%>
<%
 String lib_id = (String)session.getAttribute("library_id");
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
    String biblio_id=resource.getString("cataloguing.catviewownbibliogrid.biblioid");
pageContext.setAttribute("biblio_id", biblio_id);
String document_type=resource.getString("cataloguing.catoldtitle.documenttype");
pageContext.setAttribute("document_type", document_type);
String title1=resource.getString("cataloguing.catoldtitleentry1.title");
pageContext.setAttribute("title1",title1);
String main_entry=resource.getString("cataloguing.catoldtitleentry1.mainentry");
pageContext.setAttribute("main_entry",main_entry);
String action=resource.getString("cataloguing.catviewownbibliogrid.action");
pageContext.setAttribute("action",action);
String view=resource.getString("cataloguing.catoldtitle.view");
pageContext.setAttribute("view",view);
String path= request.getContextPath();
 pageContext.setAttribute("path", path);
    %>
<%!  ArrayList opacList;
   int fromIndex, toIndex;
%>
<%
opacList = new ArrayList();
int tcount =0;
   int perpage=4;
   int tpage=0;

 opacList=(ArrayList)session.getAttribute("opacList");
System.out.println("opacList="+opacList.size());
tcount = opacList.size();
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= opacList.size())
   toIndex = opacList.size();
   request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   
   if(session.getAttribute("marcbutton").equals("Delete"))
       pageContext.setAttribute("delete", "delete");
   
   System.out.println(session.getAttribute("marcbutton")+(String)pageContext.getAttribute("delete"));
%>

<table  border="0"  dir="<%=rtl %>">
<div>
<%
if(tcount==0)
{
%>
<p class="err"><%=resource.getString("global.norecordfound")%></p>
<%}
else
{%>
<tr><td>
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid" >

  <columns>
         <column width="100">
      <header value="${biblio_id}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.id.biblioId}"  hAlign="left"
	      styleClass="item"/>
       </column>
      <column width="200">
      <header value="${title1}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.title}"  hAlign="left"
	      styleClass="item"/>
    </column>
    <column width="150">
      <header value="${main_entry}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.mainEntry}"  hAlign="left"
	      styleClass="item"/>
    </column>
    <column width="100">
      <header value="${action}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${view}"  hAlign="left" hyperLink="${path}/cataloguing/showMarcDetails1.do?id=${doc.id.biblioId}" hyperLinkTarget="fr"
	      styleClass="item"/>
    </column>
     
      <column width="100">
      <header value="${action}" hAlign="left" styleClass="admingridheader"/>
      <item   value="Update"  hAlign="left" hyperLink="${path}/cataloguing/updateMarc.do?id=${doc.id.biblioId}"
	      styleClass="item"/>
    </column>
  </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
<alternateRows styleClass="alternaterows"/>
<paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
</td></tr>
<tr><td>
<table width="750" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="33%">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>"><%=resource.getString("global.previous")%></a>
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
<a href="<c:out value="${next}"/>"><%=resource.getString("global.next")%></a>
</c:if>
<%}%>
</td>
</tr>
</table>
</div>
<tr><td height="20px;"></td></tr>
<tr> <td align="center">
<input type="button" onclick="return send()" name="button" value="<%=resource.getString("cataloguing.catoldtitle.back")%>" Class="txt1"/>
                 </td></tr>
</table>
<div  style="position:absolute; left: 60%; top: 20%; font-size: 12px;" dir="<%=rtl %>">
    <iframe id="fr" name="fr" src="#" width="500px" height="500px" scrolling="false" frameborder="0" />
</div>
</div>
</body>
</html>
