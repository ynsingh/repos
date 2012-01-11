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

    <title>LibMS : Manage Staff Details</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script language="javascript" >
function b1click()
{
location.href="<%=request.getContextPath()%>/circulation/cir_callno_view.jsp";
}
function b2click()
{
f.action="<%=request.getContextPath()%>/admin/main.jsp";
f.method="post";
f.target="_self";
f.submit();
}

 function quit()
  {


      window.location="<%=request.getContextPath()%>/circulation/cir_callno_view.jsp";
}

</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>
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
    %>



<body>
    
    <table dir="<%=rtl%>" class="table" width="100%"  align="center" >



       <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("circulation.cir_viewallcallno.viewallavailable")%></td></tr>
                <tr><td valign="top" align="center"> <br/>



      <%
String Title=resource.getString("opac.myaccount.reservationrequest.title");
pageContext.setAttribute("Title", Title);
String CallNo=resource.getString("circulation.showcirreqopac.callno");
pageContext.setAttribute("CallNo", CallNo);
String AccessionNo=resource.getString("circulation.showcirreqopac.accessno");
pageContext.setAttribute("AccessionNo",AccessionNo);
String Status=resource.getString("circulation.memberaccviewall.Status");
pageContext.setAttribute("Status",Status);

%>

<%
String path= request.getContextPath();
 pageContext.setAttribute("path", path);

%>

<%!

   ResultSet rs=null;
   ArrayList opacList;
   int fromIndex, toIndex;
%>

<%

   opacList = new ArrayList ();
   opacList=(ArrayList)session.getAttribute("list");
   int tcount =0;
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");

   tcount=opacList.size();
   if ((toIndex = fromIndex+4) >= opacList.size ())
   toIndex = opacList.size();
   request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
<%

%>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("circulation.cir_viewall_mem_detail.norecfond")%></p>
<%}
else
{%>

<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0"
    cellSpacing="0" styleClass="datagrid"  >

  <columns>



    <column width="200">
      <header value="${Title}" hAlign="left" styleClass="admingridheader"  />
      <item   value="${doc.title}" hyperLink="${path}/showbook.do?id1=${doc.accessionNo}"  hAlign="left" hyperLinkTarget="section1"   styleClass="item"/>

    </column>
 <column width="200">
      <header value="Author/Main Entry" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.mainEntry}"  hyperLink="${path}/showbook.do?id1=${doc.accessionNo}"  hAlign="left"  hyperLinkTarget="section1" styleClass="item"/>
    </column>
    <column width="100">
      <header value="${CallNo}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.callNo}"  hyperLink="${path}/showbook.do?id1=${doc.accessionNo}"  hAlign="left"  hyperLinkTarget="section1" styleClass="item"/>
    </column>

    <column width="100">
      <header value="${AccessionNo}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.accessionNo}" hyperLink="${path}/showbook.do?id1=${doc.accessionNo}"  hAlign="left" hyperLinkTarget="section1" styleClass="item"/>
    </column>

    <column width="100">
      <header value="${Status}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.status}" hyperLink="${path}/showbook.do?id1=${doc.accessionNo}"  hAlign="left" hyperLinkTarget="section1" styleClass="item"/>
    </column>


 </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

<table  style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>"><%=resource.getString("circulation.cir_viewall_mem_detail.previos")%></a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>"><%=resource.getString("circulation.cir_viewall_mem_detail.next")%></a>
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
  <tr><td align="center" width="400px">
<form name="f">

   
   
</form>

      </td></tr></table>

 <input type="button" onclick="return quit();" class="btn" style="left:150px" value="<%=resource.getString("circulation.cir_newmember.back")%>"/>
    </body>

   

</html>

