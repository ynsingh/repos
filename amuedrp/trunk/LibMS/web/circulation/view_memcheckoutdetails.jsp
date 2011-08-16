
 
    <%@page import="com.myapp.struts.opac.ReservationDoc,com.myapp.struts.hbm.*,com.myapp.struts.opacDAO.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <jsp:include page="/admin/header.jsp"/>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
    <title>View Reservation Detail</title>

<script language="javascript" >
function b1click()
{
location.href="<%=request.getContextPath()%>/circulation/view_for_member.jsp";
}

</script>

</head>

<body>
    <div
   style="  top:130px;
   left:350px;
   right:5px;
      position: absolute;

      visibility: show;">

     <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

  <%
String Status=resource.getString("circulation.cirviewall.status");
pageContext.setAttribute("Status", Status);
String DocumentId=resource.getString("circulation.cir_viewmem_chkoutreport.docid");
pageContext.setAttribute("DocumentId", DocumentId);
String Author=resource.getString("opac.simplesearch.auth");
pageContext.setAttribute("Author",Author);
String CallNo=resource.getString("opac.myaccount.reservationrequest.callno");
pageContext.setAttribute("CallNo",CallNo);
String IssueDate=resource.getString("circulation.cir_view_book.issuedate");
pageContext.setAttribute("IssueDate",IssueDate);
String DueDate=resource.getString("circulation.cir_view_book.duedate");
pageContext.setAttribute("DueDate",DueDate);
String Edition=resource.getString("opac.myaccount.newdemand.edition");
pageContext.setAttribute("Edition",Edition);

%>



<%!
   
   
   ReservationDoc Ob;
   
   int fromIndex=0, toIndex;
%>
 <%
 
 String name=(String)session.getAttribute("mem_name");
  List<CheckoutDeocumentDetails>  requestList=(List<CheckoutDeocumentDetails>)session.getAttribute("membercheckoutDetail");

   //requestList = new ArrayList ();
   int tcount =requestList.size();
   int perpage=4;
   int tpage=0;
 



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
<table dir="<%=rtl%>" class="table" width="400px" style="height: 200px">
    <tr><td dir="<%=rtl%>" class="headerStyle" valign="top" align="center" height="25px"><%=resource.getString("circulation.viewformember.memberchkoutdetail")%></td></tr>
  <tr><td dir="<%=rtl%>" valign="top"><br>


<%if(tcount==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("circulation.cir_viewall_mem_detail.norecfond")%></p>
<%}
else
{%>
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="2" cellSpacing="0" styleClass="datagrid">
    
  <columns>
      
    <column width="10">
      <header value="" hAlign="left" styleClass="admingridheader"/>
    </column>

    <column width="100">
      <header value="${DocumentId}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.id.documentId}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="200">
      <header value="${Author}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.mainEntry}" hAlign="left"   styleClass="item"/>
    </column>

    <column width="200">
      <header value="${CallNo}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.callNo}"   hAlign="left" styleClass="item"/>
    </column>
   
      <column width="100">
      <header value="${IssueDate}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cirCheckout.issueDate}" hAlign="left" styleClass="item"/>
    </column>

    <column width="100">
      <header value="${DueDate}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cirCheckout.dueDate}" hAlign="left" styleClass="item"/>
    </column>
       <column width="100">
      <header value="${Edition}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.edition}"   hAlign="left" styleClass="item"/>
    </column>
        <column width="100">
      <header value="${Status}" hAlign="left" styleClass="admingridheader"/>
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
     <a href="<%=request.getContextPath()%>/circulation/view_for_member.jsp" > <%=resource.getString("opac.accountdetails.back")%></a>&nbsp;&nbsp;
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

</td></tr>
  <tr><td align="center">
  <input type="button" onclick="b1click()" value="Back"/></td></tr>
</table>

<%}
requestList=null;
   %>
    </div>
    </body>

</html>


