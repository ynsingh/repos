<%-- 
    Document   : reminder_member
    Created on : May 5, 2012, 12:18:42 PM
    Author     : edrp-03
--%>


<%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<jsp:include page="/admin/header.jsp"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script language="javascript">
function b1click()
{
location.href="<%=request.getContextPath()%>/circulation/remindall.do";
f.method="post";

f.submit();
}
function b2click()
{
location.href="<%=request.getContextPath()%>/admin/main.jsp";
f.method="post";
f.target="_self";
f.submit();
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

   ResultSet rs=null;
   ArrayList opacList;
   int fromIndex, toIndex;
%>

<%
String path= request.getContextPath();
 pageContext.setAttribute("path", path);

   opacList = new ArrayList ();
   opacList=(ArrayList)session.getAttribute("reminderlist");
   int tcount =0;
   int perpage=4;
   int tpage=0;

   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
  // out.println(opacList.size());
   tcount=opacList.size();
   if ((toIndex = fromIndex+4) >= opacList.size ())
   toIndex = opacList.size();
   request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);



%>

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
             <%
String AccessionNo=resource.getString("circulation.showcirreqopac.accessno");
pageContext.setAttribute("AccessionNo", AccessionNo);
String MemberId=resource.getString("circulation.cir_newmember.memberid");
pageContext.setAttribute("MemberId", MemberId);
String FirstName=resource.getString("circulation.cir_newmember.fname");
pageContext.setAttribute("FirstName",FirstName);
String LastName=resource.getString("circulation.cir_newmember.lname");
pageContext.setAttribute("LastName",LastName);
String EmailId=resource.getString("circulation.cir_newmember.email");
pageContext.setAttribute("EmailId",EmailId);
String MainEntry=resource.getString("opac.simplesearch.mainentry");
pageContext.setAttribute("MainEntry",MainEntry);
String Title=resource.getString("opac.simplesearch.title");
pageContext.setAttribute("Title",Title);
String DueDate=resource.getString("circulation.cir_view_book.duedate");
pageContext.setAttribute("DueDate",DueDate);
String Action=resource.getString("admin.viewpending.action");
pageContext.setAttribute("Action",Action);
%>
<div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table class="datagrid" width="80%" align="center">
        <tr><td class="headerStyle" align="center">Overdue Reminder</td></tr>
        <tr><td align="center">


<%

if(tcount==0)
{
%>
<p class="err"><%=resource.getString("circulation.cirviewall.norectodis")%></p>
<%}
else
{%>


     <form name="f"  method="post" >


<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0"
    cellSpacing="0" styleClass="datagrid"  >

  <columns>



   

    <column width="100">
      <header value="${MemberId}" hAlign="left" styleClass="headerStyle"/>
      <item   value="${doc.memid}"   hAlign="left"  styleClass="item" />
    </column>
     <column width="100">
      <header value="${FirstName}" hAlign="left" styleClass="headerStyle"/>
      <item   value="${doc.fname}"  hAlign="left"  styleClass="item"  />
    </column>
      <column width="100">
      <header value="${LastName}" hAlign="left" styleClass="headerStyle"/>
      <item   value="${doc.lname}"  hAlign="left"  styleClass="item"  />
    </column>
     <column width="100">
      <header value="${EmailId}" hAlign="left" styleClass="headerStyle"/>
      <item   value="${doc.email}"  hAlign="left"  styleClass="item" />
    </column>
     <column width="100">
      <header value="${MainEntry}" hAlign="left" styleClass="headerStyle"/>
      <item   value="${doc.main_entry}"  hAlign="left"  styleClass="item" />
    </column>
     <column width="100">
      <header value="${Title}" hAlign="left" styleClass="headerStyle"/>
      <item   value="${doc.title}"  hAlign="left"  styleClass="item"  />
    </column>
       <column width="100">
      <header value="${AccessionNo}" hAlign="left" styleClass="headerStyle"  />
      <item   value="${doc.accession_no}"     hAlign="left"   styleClass="item" />

    </column>
      <column width="100">
      <header value="${DueDate}" hAlign="left" styleClass="headerStyle"/>
      <item   value="${doc.due_date}"  hAlign="left"  styleClass="item" />
    </column>

      <column width="100">
      <header value="${Action}" hAlign="left" styleClass="headerStyle"/>
      <item   value="Send Reminder"  hAlign="left"  styleClass="item"  hyperLink="${path}/circulation/remindermail.do?memid=${doc.memid}"/>
    </column>

 </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

<table width="100%" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="center">
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
     </form>
  <%}%>
            </td></tr>
        <tr><td align="center">
 <%
String msg=(String)request.getAttribute("msg");
if (msg!=null)
{
out.println(msg);
}
%>
<br/>
          

    <input type="button" name="button" value="Remind All" onclick="return b1click();">
    <input type="button" name="button" value="Back" onclick="return b2click(); ">
    </td></tr>
    </table>
</div>
    </body>
</html>