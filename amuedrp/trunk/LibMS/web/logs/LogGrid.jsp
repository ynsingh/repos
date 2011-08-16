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


    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/newformat.css"/>


    <script language="javascript">

   



function printlog()
{
    window.location="<%=request.getContextPath()%>/jasperlog.do";
    return false;
}



</script>
</head>
<body bgcolor="#FFFFFF">


<%!

   ResultSet rs=null;
   OpacDoc Ob;
   ArrayList opacList;
   int fromIndex, toIndex;
   static Integer count=0;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>

<%



 opacList = new ArrayList ();
   int tcount =0;
   int perpage=10;
   int tpage=0;
opacList=(ArrayList)session.getAttribute("loglist");
if(opacList!=null)
    tcount=opacList.size();

   fromIndex = (int)DataGridParameters.getDataGridPageIndex(request, "datagrid1");
   if ((toIndex = fromIndex+10) >= tcount)
   toIndex = tcount;
   System.out.println("opacList="+tcount+" tcount="+tcount);
   if(opacList!=null)request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
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

%>

<table align="<%=align%>" dir="<%=rtl%>"  class="datagrid" width="100%" style="border:solid 1px #e0e8f5;">
   <td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">View All LOG Reports</td>
     
     <tr style="background-color:#e0e8f5;" dir="<%=rtl%>">
         <td colspan="2" align="center" valign="top" dir="<%=rtl%>" height="300px">




<%




if(tcount==0)
{
%>
<p class="err"><%=resource.getString("global.norecordfound")%></p>
<%}
else
{%>
<table height="300px" width="100%" dir="<%=rtl%>"><tr><td valign="top" dir="<%=rtl%>">
<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">

  <columns>



    <column width="200">
      <header value="Sno" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.libraryId}"  hyperLinkTarget="_parent"   hAlign="left"/>
    </column>

    <column width="200">
      <header value="User Id" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.libraryId}" hAlign="left"  hyperLinkTarget="_parent"  />
    </column>
      <column width="200">
      <header value="Library" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.libraryId}" hyperLinkTarget="_parent" hAlign="left" />
    </column>
      <column width="200">
      <header value="SubLibrary" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.sublibraryId}" hyperLinkTarget="_parent" hAlign="left" />
    </column>
      <column width="200">
      <header value="Date" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.date}" hyperLinkTarget="_parent" hAlign="left" />
    </column>
      <column width="200">
      <header value="Time" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.time}" hyperLinkTarget="_parent" hAlign="left" />
    </column>
      <column width="200">
      <header value="Action Mapping" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.libraryId}" hyperLinkTarget="_parent" hAlign="left" />
    </column>
       <column width="200">
      <header value="Action Performed" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.libraryId}" hyperLinkTarget="_parent" hAlign="left" />
    </column>
      <column width="200">
      <header value="Action Result" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.libraryId}" hyperLinkTarget="_parent" hAlign="left" />
    </column>
      <column width="200">
      <header value="ClassName" hAlign="left" styleClass="header" />
      <item  styleClass="item"  value="${doc.libraryId}" hyperLinkTarget="_parent" hAlign="left" />
    </column>
     
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="10" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>
</td></tr>
<tr><td height="5px" style="margin:0px 0px 0px 0px;" >
        <table width="100%"  border=0 class="header">
    <tr >
<td align="left" width="10%" class="datagrid" dir="<%=rtl%>">
<c:if test="${previous != null}">
    <a style="color:white;" href="<c:out value="${previous}"/>"><%=resource.getString("global.previous")%></a>
</c:if>&nbsp;
</td>

<td align="center" width="10%" class="datagrid" dir="<%=rtl%>">
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
<td align="right"  dir="<%=rtl%>" width="10%" class="datagrid">&nbsp;
<c:if test="${next != null}">
<a style="color:white;" href="<c:out value="${next}"/>"><%=resource.getString("global.next")%></a>
</c:if>
</td>
</tr>
    </table></td></tr>
<tr><td height="10px" dir="<%=rtl%>">

    </td></tr></table>
  <%}%>


  </td></tr>
     <tr><td>  <input type="submit" value="View Report" onclick="return printlog();"/>
       </td></tr></table>

    </body>

</html>
