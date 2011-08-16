 <%@page import="com.myapp.struts.circulation.*,com.myapp.struts.hbm.*"%>
    <jsp:include page="/admin/header.jsp" flush="true" />
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

    <title>LibMS : Manage Notices</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

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
String DocumentCategoryName=resource.getString("systemsetup.document_category.doccategorname");
pageContext.setAttribute("DocumentCategoryName", DocumentCategoryName);
String Fine=resource.getString("circulation.cir_checkinbookdetail.fine");
pageContext.setAttribute("Fine", Fine);
String MemberType=resource.getString("systemsetup.submem_view_update.memtypename");
pageContext.setAttribute("MemberType",MemberType);
String SubMemberType=resource.getString("systemsetup.submem_view_update.submemname");
pageContext.setAttribute("SubMemberType",SubMemberType);

%>
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
    <table dir="<%=rtl%>"  class="table" width="700px" align="center">



       <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.view_all_doc_category.viewallfinedetail")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>




<%!

   ResultSet rs=null;
   ArrayList opacList;
   int fromIndex, toIndex;
%>

<%

   opacList = new ArrayList ();
   List<FineDetailGrid> opacList=(List<FineDetailGrid>)session.getAttribute("finelist");
   /*List<DocumentCategory> doc1 = (List<DocumentCategory>)lst.get(0);
   List<BookCategory> bc = (List<BookCategory>)lst.get(1);
   List<EmployeeType> et = (List<EmployeeType>)lst.get(2);
   List<SubEmployeeType> st = (List<SubEmployeeType>)lst.get(3);*/
   int tcount =0;
   int perpage=4;
   int tpage=0;
   //System.out.println(lst);
   

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


<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">

  <columns>

    <column width="200">
      <header value="${DocumentCategoryName}" hAlign="left" styleClass="admingridheader"  />
      <item   value="${doc.documentCategory.documentCategoryName}" hAlign="left"   styleClass="item"/>
    </column>
    <column width="200">
      <header value="${Fine}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.bookCategory.fine}"  hAlign="left"  styleClass="item"/>
    </column>
       <column width="200">
      <header value="${MemberType}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.employeeType.emptypeFullName}"  hAlign="left"  styleClass="item"/>
    </column>
       <column width="200">
      <header value="${SubMemberType}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.subEmployeeType.subEmptypeFullName}"  hAlign="left"  styleClass="item"/>
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


      </td></tr></table>


    </body>
</div>

</html>