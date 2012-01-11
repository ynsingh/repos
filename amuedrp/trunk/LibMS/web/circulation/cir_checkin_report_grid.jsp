 <%@page import="com.myapp.struts.circulation.CheckInDocumentDetails,com.myapp.struts.systemsetupDAO.BookCategoryDAO,com.myapp.struts.utility.DateCalculation,com.myapp.struts.hbm.*,com.myapp.struts.opacDAO.*"%>
  
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

    <title>LibMS : Manage CheckInReport</title>
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
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>


<body style="margin:0px 0px 0px 0px">
    <table  dir="<%=rtl%>" width="100%" align="center">



       <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("circulation.cir_chekin_report_grid.viewallchekinreport")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>


  <%
String LibraryId=resource.getString("opac.browse.table.Libraryid");
pageContext.setAttribute("LibraryId", LibraryId);
String MemberId=resource.getString("circulation.cir_newmember.memberid");
pageContext.setAttribute("MemberId", MemberId);
String SubLibraryId=resource.getString("circulation.cir_chekin_report_grid.sublib");
pageContext.setAttribute("SubLibraryId",SubLibraryId);
String ReturningDate=resource.getString("circulation.cir_checkinbookdetail.returndate");
pageContext.setAttribute("ReturningDate",ReturningDate);


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
 List<CheckInDocumentDetails>  requestList=(List<CheckInDocumentDetails>)session.getAttribute("circheckInlist1");
  

   int tcount =requestList.size();

String Title="Title";
pageContext.setAttribute("Title", Title);

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


   

  fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
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


<ui:dataGrid items="${requestList}" var="doc"  name="datagrid1" cellPadding="0"
    cellSpacing="0" styleClass="datagrid"  >

 


<columns>
      <column width="15%">
      <header value="MemberID" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cirCheckin.memberId}"  hAlign="left"    styleClass="item"/>
    </column>


    <column width="15%">
      <header value="${Title}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.title}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${Author}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.mainEntry}" hAlign="left"   styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${CallNo}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.callNo}"   hAlign="left" styleClass="item"/>
    </column>
    <column width="15%">
      <header value="AccessionNo" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.accessionNo}"   hAlign="left" styleClass="item"/>
    </column>
<column width="10%">
      <header value="IssueDate" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cirTransactionHistory.issueDate}" hAlign="left" styleClass="item"/>
    </column>

    <column width="10%">
      <header value="Returningdate" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cirCheckin.returningDate}" hAlign="left" styleClass="item"/>
    </column>
       <column width="15%">
      <header value="${Edition}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.edition}"   hAlign="left" styleClass="item"/>
    </column>
      <column width="15%">
      <header value="Fine" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cirTransactionHistory.fineAmt}"   hAlign="left" styleClass="item"/>
    </column>
 </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

  <%}%>
  <br><br><br>
  </td></tr>
  <tr><td>

<table width="600" style="font-family: arial; font-size: 10pt" border=0>
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

      </td></tr>
 </table>


    </body>

</html>

