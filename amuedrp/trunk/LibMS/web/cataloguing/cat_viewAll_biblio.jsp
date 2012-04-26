<!-- VIEW ALL CATALOG OLD TITLE ENTRY RECORD GRID-->
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

    <%!
        Locale locale=null;
        String locale1="en";
        String rtl="ltr";
        String align="left";
        List opacList;
        int fromIndex, toIndex;
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
    }
    catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";page=true;}
    else{ rtl="RTL";align="right";page=false;}
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

  
    int tcount =0;
    opacList=(List)session.getAttribute("opacList");
    tcount = opacList.size();
    fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
    if ((toIndex = fromIndex+100) >= opacList.size())
    toIndex = opacList.size();
    request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
    pageContext.setAttribute("tCount", tcount);
%>

<html>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
function next(){
         <%
     int pageNumber=0;
     if(request.getParameter("page") != null) {
       session.setAttribute("page", request.getParameter("page"));
       pageNumber = Integer.parseInt(request.getParameter("page"));
     } else {
       session.setAttribute("page", "1");
     }
     String nextPage = (pageNumber +1) + "";%>
     var loc="<%=request.getContextPath()%>/cataloguing/viewAllBiblio.do?page="+<%=nextPage%>;
     location.href= loc;
}

function previous()
{
     <%
     String previousPage ="";
     if(pageNumber>=1)
        previousPage = (pageNumber -1) + "";
    else
        previousPage = 0 + "";
    %>
    var loc="<%=request.getContextPath()%>/cataloguing/viewAllBiblio.do?page="+<%=previousPage%>;
    location.href= loc;
}
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
</head>
<body>
<table  width="100%"  dir="<%=rtl %>">
<%
if(tcount==0)
{
%>
<p class="err"><%=resource.getString("global.norecordfound")%></p>
<%}
else
{%>
<tr><td colspan="2" align="center">
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid" >

  <columns>
         <column width="5%">
      <header value="${biblio_id}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.id.biblioId}"  hAlign="left"
	      styleClass="item"/>
       </column>
       
       <column width="15%">
      <header value="Call No" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.callNo}"  hAlign="left"
	      styleClass="item"/>
       </column>
    <column width="10%">
      <header value="${document_type}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentType}"  hAlign="left"
	      styleClass="item"/>
    </column>
      <column width="25%">
      <header value="${title1}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.title}"  hAlign="left"
	      styleClass="item"/>
    </column>
    <column width="20%">
      <header value="${main_entry}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.mainEntry}"  hAlign="left"
	      styleClass="item"/>
    </column>
    <column width="10%">
      <header value="${action}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${view}"  hAlign="left" hyperLink="${path}/cataloguing/titleShow.do?id=${doc.id.biblioId}"
	      styleClass="item"/>
    </column>
  </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
<alternateRows styleClass="alternaterows"/>
<paging size="100" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
</td></tr>
<tr><td>
        <input type="button" onclick="next()" value="next" class="datagrid"/>
  <input type="button" onclick="previous()" value="previous" class="datagrid"/>
<%--
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

</td>
</tr>
</table>--%>
<%}%>

</table>
</body>
</html>
