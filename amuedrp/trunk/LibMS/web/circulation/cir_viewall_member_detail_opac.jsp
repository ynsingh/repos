<%--
    Document   : cir_viewall_member_detail
    Created on : Apr 8, 2011, 8:05:56 AM
    Author     : edrp02
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>

</html>
 <%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*"%>
    <%--<jsp:include page="/admin/header.jsp" flush="true" />--%>

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
location.href="<%=request.getContextPath()%>/admin/main.jsp";
}
function b2click()
{
f.action="<%=request.getContextPath()%>/admin/main.jsp";
f.method="post";
f.target="_self";
f.submit();
}

</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>
<div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

<body>
    <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    boolean page=true;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";page=true;}
    else{ rtl="RTL";align="right";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
    %>

    <%!

   ResultSet rs=null;
   ArrayList opacList;
   int fromIndex, toIndex;
%>

<%
String path= request.getContextPath();
 pageContext.setAttribute("path", path);
   opacList = new ArrayList ();
   opacList=(ArrayList)session.getAttribute("RequestFromOPAC");
  // if(opacList==null)
    //opacList=(ArrayList)session.getAttribute("cirmemdetaillist");
   int tcount =0;
   int perpage=4;
   int tpage=0;
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

String MemberId=resource.getString("circulation.cir_newmember.memberid");
pageContext.setAttribute("MemberId", MemberId);
String FirstName=resource.getString("circulation.cir_newmember.fname");
pageContext.setAttribute("FirstName",FirstName);
String MiddleName=resource.getString("circulation.cir_newmember.mname");
pageContext.setAttribute("MiddleName", MiddleName);
String LastName=resource.getString("circulation.cir_newmember.lname");
pageContext.setAttribute("LastName",LastName);
String EmailId=resource.getString("circulation.cir_newmember.email");
pageContext.setAttribute("EmailId",EmailId);

%>


<table border="1" class="table" width="700px" align="center" dir="<%=rtl%>">



       <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("circulation.cir_viewall_mem_detail.viewallmemdetail")%></td></tr>
                <tr><td valign="top" align="center"> <br/>


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
      <header value="MemberId" hAlign="left" styleClass="admingridheader"  />
      <item   value="${doc.memId}" hyperLink="${path}/circulation/showMember.do?id=${doc.memId}"  hAlign="left"   styleClass="item"/>

    </column>

    <column width="200">
      <header value="FirstName" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.fname}" hyperLink="${path}/circulation/showMember.do?id=${doc.memId}"   hAlign="left"  styleClass="item"/>
    </column>

    <column width="200">
      <header value="MiddleName" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.mname}" hyperLink="${path}/circulation/showMember.do?id=${doc.memId}"   hAlign="left"  styleClass="item"/>
    </column>
   <column width="200">
      <header value="LastName" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.lname}" hyperLink="${path}/circulation/showMember.do?id=${doc.memId}"  hAlign="left"  styleClass="item"/>
    </column>

    <column width="200">
      <header value="EmailId" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.email}" hyperLink="${path}/circulation/showMember.do?id=${doc.memId}"  hAlign="left"  styleClass="item"/>
    </column>

       <column width="200">
      <header value="RequestDate" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.requestdate}"   hAlign="left"  styleClass="item"/>
    </column>

      
       <column width="200">
      <header value="Status" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.status}"   hAlign="left"  styleClass="item"/>
    </column>

       <column width="200">
      <header value="Faculty" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.faculty_name}"   hAlign="left"  styleClass="item"/>
    </column>

       <column width="200">
      <header value="Department" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.dept_name}"   hAlign="left"  styleClass="item"/>
    </column>

       <column width="200">
      <header value="Course" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.course_name}"   hAlign="left"  styleClass="item"/>
    </column>

       <column width="200">
      <header value="Sublibrary" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.sublib_name}"   hAlign="left"  styleClass="item"/>
    </column>


 </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

<table width="700" style="font-family: arial; font-size: 10pt" border=0>
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


    </body>
</div>

</html>

