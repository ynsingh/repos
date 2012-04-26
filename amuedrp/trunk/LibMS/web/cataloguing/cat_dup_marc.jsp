<!-- DISPLAY THE DUPLICATE TITLE EXIST IN LIBRARY-->
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
    <body>
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
    ArrayList opacList;
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
    opacList = new ArrayList();
    int tcount =0;
    int perpage=4;
    int tpage=0;
    opacList=(ArrayList)session.getAttribute("opacList");
    tcount = opacList.size();
    fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
    if ((toIndex = fromIndex+4) >= opacList.size())
    toIndex = opacList.size();
    request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
    pageContext.setAttribute("tCount", tcount);
   if(session.getAttribute("marcbutton").equals("Delete"))
       pageContext.setAttribute("delete", "delete");
    %>

<table  border="0"  dir="<%=rtl %>" align="left">
<div>
<%
if(tcount==0)
{
%>
<p class="err"><%=resource.getString("global.norecordfound")%></p>
<%}
else
{%>
<tr><td class="headerStyle" align="center" colspan="2">TITLE EXIST IN LIBRARY</td></tr>
<tr><td colspan="2">
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
      <column width="150">
      <header value="CallNo" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.callNo}"  hAlign="left"
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
<table width="100%" style="font-family: arial; font-size: 10pt" border=0>
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
    </td></tr>
</div>
<tr>
    
    <td align="left" width="100px">
        <html:form action="/cataloguing/duplicatemarc" method="post">
            <input type="submit"  name="button" value="New Entry of Title" />
                 </html:form> </td><td align="left">
        <input type="button" onclick="return send()" name="button" value="<%=resource.getString("cataloguing.catoldtitle.back")%>" />
    </td></tr>
</table>
  <%--<column width="100">
      <header value="${action}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${view}"  hAlign="left" hyperLink="${path}/cataloguing/showMarcDetails1.do?id=${doc.id.biblioId}"  hyperLinkTarget="fr"
	      styleClass="item"/>
    </column>
<div  style="position:absolute; left: 60%; top: 10%; font-size: 12px;" dir="<%=rtl %>">
    <iframe id="fr" name="fr" src="#" width="100%" height="500px" scrolling="false" frameborder="0" />
</div>--%>
</div>
</body>
</html>
