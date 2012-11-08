    <%@page import="com.myapp.struts.opac.ReservationDoc,com.myapp.struts.hbm.*,com.myapp.struts.opacDAO.*"%>
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
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
    <title>View Checkout Detail</title>

</head>
<jsp:include page="opacheader.jsp"></jsp:include>
<body>
     <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="true";
    boolean page=true;
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
<%!
   ReservationDoc Ob;
   ArrayList requestList;
   int fromIndex=0, toIndex;
%>
 <%
 List<CheckoutDeocumentDetails> requestList = (List<CheckoutDeocumentDetails>)session.getAttribute("membercheckoutDetail1");
 String name=(String)session.getAttribute("mem_name");
 String requestpath = request.getContextPath()+"/OPAC";
   int tcount =requestList.size();
%>
       
<%
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
<br>
 <%if(page.equals(true))
    {

%>


<table  align="center" dir="<%=rtl%>" width="80%" class="datagrid" style="border: dashed 1px cyan;">




   <tr><td   dir="<%=rtl%>" style="font-family:Tahoma;font-size:12px" height="28px" align="<%=align%>">
          <table width="100%">
              <tr><td   dir="<%=rtl%>" colspan="2" style="font-family:Arial;font-size:12px;border-bottom: dashed 1px cyan" height="28px" align="center" ><b>Circulation Member : My Account Section OPAC</b></td></tr>
              <tr><td  dir="<%=rtl%>" style="font-family:Tahoma;font-size:12px;border-bottom: dashed 1px cyan" height="28px" align="<%=align%>"><b>


	&nbsp;&nbsp;
                <a href="<%=request.getContextPath()%>/OPAC/accountdetails.jsp"  style="text-decoration: none;"><%=resource.getString("opac.accountdetails.home")%></a>&nbsp;|&nbsp;
            <a href="<%=request.getContextPath()%>/OpacLib.do?name=newdemand"  style="text-decoration: none;"> <%=resource.getString("opac.accountdetails.newdemand")%></a>&nbsp;
    |&nbsp;<a href="#"  style="text-decoration: none;"> <%=resource.getString("opac.accountdetails.reservationrequest")%></a>

 &nbsp;
    |&nbsp;<a href="<%=request.getContextPath()%>/OPAC/OpacLib.do?name=changepassword"  style="text-decoration: none;"> Change Password</a>
|&nbsp;<a href="<%=request.getContextPath()%>/OPAC/uploadExcel.do"  style="text-decoration: none;"> Bulk Import</a>
  


          </b>
                  </td><td align="right" dir="<%=rtl%>" style="font-family:Tahoma;font-size:12px;border-bottom: dashed 1px cyan;"><%=resource.getString("opac.accountdetails.hi")%>&nbsp;<%=name%>&nbsp;<b>|</b>&nbsp;<a href="home.do"  style="text-decoration: none;"><%=resource.getString("opac.accountdetails.logout")%></a></td></tr>
          </table>
        </td></tr>




  <tr><td  valign="top" class="btn1">

          <br><br>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Record Found</p>
<%}
else
{%>
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="2" cellSpacing="0" styleClass="datagrid">
    
  <columns>
      
    <column width="10">
      <header value="" hAlign="left" styleClass="header"/>
    </column>

    <column width="100">
      <header value="Document Id" hAlign="left" styleClass="header"/>
      <item   value="${doc.documentDetails.id.documentId}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="200">
      <header value="Author" hAlign="left" styleClass="header"/>
      <item   value="${doc.documentDetails.mainEntry}" hAlign="left"   styleClass="item"/>
    </column>

    <column width="200">
      <header value="CallNo" hAlign="left" styleClass="header"/>
      <item   value="${doc.documentDetails.callNo}"   hAlign="left" styleClass="item"/>
    </column>
   
      <column width="100">
      <header value="Issue Date" hAlign="left" styleClass="header"/>
      <item   value="${doc.cirCheckout.issueDate}" hAlign="left" styleClass="item"/>
    </column>

    <column width="100">
      <header value="Due Date" hAlign="left" styleClass="header"/>
      <item   value="${doc.cirCheckout.dueDate}" hAlign="left" styleClass="item"/>
    </column>
       <column width="100">
      <header value="Edition" hAlign="left" styleClass="header"/>
      <item   value="${doc.documentDetails.edition}"   hAlign="left" styleClass="item"/>
    </column>
        <column width="100">
      <header value="Status" hAlign="left" styleClass="header"/>
      <item   value="${doc.cirCheckout.status}"   hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
<table width="600px" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="150px">
     <a href="<%=requestpath%>/accountdetails.jsp" > <%=resource.getString("opac.accountdetails.back")%></a>&nbsp;&nbsp;
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>"><%=resource.getString("opac.accountdetails.previous")%></a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>"><%=resource.getString("opac.accountdetails.next")%></a>
</c:if>
</td><td align="center" >


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
</td></tr></table>

<% }else
    {%>



<table  align="center" dir="<%=rtl%>" width="80%" class="datagrid" style="border: dashed 1px cyan;">




   <tr><td   dir="<%=rtl%>" style="font-family:Tahoma;font-size:12px" height="28px" align="<%=align%>">
          <table width="100%">
              <tr><td   dir="<%=rtl%>" colspan="2" style="font-family:Arial;font-size:12px;border-bottom: dashed 1px cyan" height="28px" align="center" ><b>Circulation Member : My Account Section OPAC</b></td></tr>
              <tr><td  dir="<%=rtl%>" style="font-family:Tahoma;font-size:12px;border-bottom: dashed 1px cyan" height="28px" align="<%=align%>"><b>


	&nbsp;&nbsp;
                <a href="<%=request.getContextPath()%>/OPAC/accountdetails.jsp"  style="text-decoration: none;"><%=resource.getString("opac.accountdetails.home")%></a>&nbsp;|&nbsp;
            <a href="<%=request.getContextPath()%>/OPAC/OpacLib.do?name=newdemand"  style="text-decoration: none;"> <%=resource.getString("opac.accountdetails.newdemand")%></a>&nbsp;
    |&nbsp;<a href="#"  style="text-decoration: none;"> <%=resource.getString("opac.accountdetails.reservationrequest")%></a>

 &nbsp;
    |&nbsp;<a href="<%=request.getContextPath()%>/OPAC/OpacLib.do?name=changepassword"  style="text-decoration: none;"> Change Password</a>
|&nbsp;<a href="<%=request.getContextPath()%>/OPAC/uploadExcel.do"  style="text-decoration: none;"> Bulk Import</a>




          </b>
                  </td><td align="right" dir="<%=rtl%>" style="font-family:Tahoma;font-size:12px;border-bottom: dashed 1px cyan;"><%=resource.getString("opac.accountdetails.hi")%>&nbsp;<%=name%>&nbsp;<b>|</b>&nbsp;<a href="home.do"  style="text-decoration: none;"><%=resource.getString("opac.accountdetails.logout")%></a></td></tr>
          </table>
        </td></tr>



     <tr><td  valign="top" >

          
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Record Found</p>
<%}
else
{%>
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="2" cellSpacing="0" styleClass="datagrid">

<columns>

    <column width="10">
      <header value="" hAlign="left" styleClass="header"/>
    </column>

    <column width="100">
      <header value="Document Id" hAlign="left" styleClass="header"/>
      <item   value="${doc.documentDetails.id.documentId}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="200">
      <header value="Author" hAlign="left" styleClass="header"/>
      <item   value="${doc.documentDetails.mainEntry}" hAlign="left"   styleClass="item"/>
    </column>

    <column width="200">
      <header value="CallNo" hAlign="left" styleClass="header"/>
      <item   value="${doc.documentDetails.callNo}"   hAlign="left" styleClass="item"/>
    </column>

      <column width="100">
      <header value="Issue Date" hAlign="left" styleClass="header"/>
      <item   value="${doc.cirCheckout.issueDate}" hAlign="left" styleClass="item"/>
    </column>

    <column width="100">
      <header value="Due Date" hAlign="left" styleClass="header"/>
      <item   value="${doc.cirCheckout.dueDate}" hAlign="left" styleClass="item"/>
    </column>
       <column width="100">
      <header value="Edition" hAlign="left" styleClass="header"/>
      <item   value="${doc.documentDetails.edition}"   hAlign="left" styleClass="item"/>
    </column>
        <column width="100">
      <header value="Status" hAlign="left" styleClass="header"/>
      <item   value="${doc.cirCheckout.status}"   hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
<table width="600px" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="150px">
     <a href="accountdetails.jsp" > <%=resource.getString("opac.accountdetails.back")%></a>&nbsp;&nbsp;
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>"><%=resource.getString("opac.accountdetails.previous")%></a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>"><%=resource.getString("opac.accountdetails.next")%></a>
</c:if>
</td><td align="center" >


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
</td></tr></table>



    <%}%>

    </body>
    <jsp:include page="opacfooter.jsp"></jsp:include>
</html>


