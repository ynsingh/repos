<%--
    Document   : cat_viewAll_biblio
    Created on : Mar 15, 2011, 12:05:56 PM
    Author     : EdRP-04
--%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.myapp.struts.Acquisition.approval_1"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>



         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript">


function printapproved()
{
    top.location="<%=request.getContextPath()%>/acq_approvalprint3.do";
    return false;
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
<%!  List<approval_1> opacList1;
approval_1 obj;
ArrayList<approval_1> opacList2;
   int fromIndex, toIndex;
%>
<%

int tcount =0;
   int perpage=4;
   int tpage=0;

 opacList1=(List<approval_1>)session.getAttribute("opacListApproved1");
opacList2=new ArrayList();

System.out.println("opacList="+opacList1.size());
 Iterator it = opacList1.iterator();
 while(it.hasNext())
     {
        obj = new approval_1();

        obj.setControl_no(opacList1.get(tcount).getControl_no());
        obj.setAuthor(opacList1.get(tcount).getAuthor());
        obj.setTitle(opacList1.get(tcount).getTitle());
        obj.setNo_of_copies(opacList1.get(tcount).getNo_of_copies());
        obj.setIsbn(opacList1.get(tcount).getIsbn());
        obj.setVendor_id(opacList1.get(tcount).getVendor_id());
        obj.setAcq_mode(opacList1.get(tcount).getAcq_mode());
     opacList2.add(obj);
     it.next();
     tcount++;
     }



//System.out.println("opacList="+opacList2.size()+opacList2.get(1).getTitle());
tcount = opacList2.size();
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= opacList2.size())
   toIndex = opacList1.size();
   request.setAttribute ("opacList2", opacList2.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
<table  width="750" style="font-family: arial; font-size: 10pt" border=0 class="table">
    <tr class="headerStyle" align="center"><td>Approved List</td></tr>

<%
if(tcount==0)
{
%>
    <tr><td><p class="err">No record Found</p></td></tr>
<%}
else
{%>

<tr><td align="left">
<ui:dataGrid items="${opacList2}" var="doc" name="datagrid1" cellPadding="0"
    cellSpacing="0" styleClass="datagrid"  >

  <columns>
       <column width="100">
      <header value="Control No." hAlign="left" styleClass="header"/>
      <item   value="${doc.control_no}"  hAlign="left"  styleClass="item"/>
    </column>

       <column width="100">
      <header value="Title" hAlign="left" styleClass="header"/>
      <item   value="${doc.title}"  hAlign="left"  styleClass="item"/>
    </column>
       <column width="100">
      <header value="Author" hAlign="left" styleClass="header"/>
      <item   value="${doc.author}"  hAlign="left"  styleClass="item"/>
    </column>
       <column width="100">
      <header value="ISBN" hAlign="left" styleClass="header"/>
      <item   value="${doc.isbn}"  hAlign="left"  styleClass="item"/>
    </column>
 <column width="100">
      <header value="No of Copies" hAlign="left" styleClass="header"/>
      <item   value="${doc.no_of_copies}"  hAlign="left"  styleClass="item"/>
    </column>
       <column width="100">
      <header value="Vendor" hAlign="left" styleClass="header"/>
      <item   value="${doc.vendor_id}"  hAlign="left"  styleClass="item"/>
    </column>

       <column width="200">
      <header value="Acquisition Mode" hAlign="left" styleClass="header"/>
      <item   value="${doc.acq_mode}"  hAlign="left"  styleClass="item"/>
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
<table width="750" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="33%">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
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
<td align="middle" width="33%">&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>">Next</a>
</c:if>
<%}%>
</td>
</tr>


<tr><td height="20px;"></td></tr>
<tr> <td align="left">
<input type="button" onclick="return printapproved()" name="button" value="Print Approved Report" />
                 </td></tr>

</table>
</body>
</html>
