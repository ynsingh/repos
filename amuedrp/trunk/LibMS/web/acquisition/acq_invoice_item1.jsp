<%--
    Document   : cat_viewAll_biblio
    Created on : Mar 15, 2011, 12:05:56 PM
    Author     : EdRP-04
--%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Aqeel Ahmad Khan" content="MCA,AMU">
      <title></title>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript">
function send()
{
    top.location="<%=request.getContextPath()%>/acquisition/acq_invoice_item_main.jsp";
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
<%!  ArrayList opacList;
   int fromIndex, toIndex;
%>
<%
opacList = new ArrayList();
int tcount =0;
   int perpage=9;
   int tpage=0;

 opacList=(ArrayList)session.getAttribute("listforinvoice");
System.out.println("opacList="+opacList.size());

session.removeAttribute("discnt");
 session.removeAttribute("ntotal");
 
tcount = opacList.size();
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+9) >= opacList.size())
   toIndex = opacList.size();
   request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
<table style="position:absolute; left: 10%; top: 10%;">
<div>
<%
if(tcount==0)
{
%>
<p class="err">No record Found</p>
<%}
else
{%>
<%
String path= request.getContextPath();
pageContext.setAttribute("path", path);
%>
<form id="f" name="f">
<tr><td>
        <input type="hidden" name="list" id="list"/>
        <input type="hidden" name="list2" id="list2"/>
        <input type="hidden" name="disc" id="disc">
        <input type="hidden" name="i" id="i">
        <%--<input type="text" name="disc" id="disc">--%>
        <%--<input type="text" name="i" id="i">--%>
        <input type="hidden" name="sizearray" id="sizearray">
        <input type="hidden" name="sizearrayindex" id="sizearrayindex">
        <%--<input type="text" name="sizearrayindex" id="sizearrayindex">--%>
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid" >
  <columns>
    <column width="60">
        <header value="Control No" hAlign="left" styleClass="header"/>
        <item   value="${doc.control_no}"  hAlign="left"   styleClass="item"/>
    </column>

    <column width="70">
        <header value="Order No" hAlign="left" styleClass="header"/>
        <item   value="${doc.order_no}"  hAlign="left"  styleClass="item"/>
    </column>
    <column width="60">
        <header value="Copy Received" hAlign="left" styleClass="header"/>
        <item   value="${doc.recieved_copies}"  hAlign="left"  styleClass="item"/>
    </column>
    <column width="70">
        <header value="Unit Price" hAlign="left" styleClass="header"/>
        <item   value="${doc.unit_price}"  hAlign="left"  styleClass="item"/>
    </column>
    <column width="100">
        <header value="Receiving No" hAlign="left" styleClass="header"/>
        <item   value="${doc.recieving_no}"  hAlign="left"  styleClass="item"/>
    </column>
    <column width="120">
        <header value="Vendor Id" hAlign="left" styleClass="header"/>
        <item   value="${doc.vendor_id}"  hAlign="left"  styleClass="item"/>
    </column>
    <column width="120">
        <header value="Received By" hAlign="left" styleClass="header"/>
        <item   value="${doc.recieved_by}"  hAlign="left"  styleClass="item"/>
    </column>
    <columns>
    <column width="50">
        <header value="select" halign="left" styleClass="header"/>
        <item>
            <![CDATA[
            <input type="checkbox" name="check" align="right" id="check" value="${doc.control_no},${doc.order_no},${doc.recieved_copies},${doc.unit_price},${doc.title},${doc.recieving_no},${doc.vendor_id},${doc.recieved_by},${doc.pending_copies}" onClick="javascript:get_check_value();">
            ]]>
          </item>
    </column>
   </columns>



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
</table>

<tr><td height="20px;"></td></tr>
<%--<tr>
           <td align="left" width="1200px" height="15px" >
             <table class="datagrid" >
               <tr>
                   <td>Title</td>
                   <td width="200px">
                       <input type="text" name="title"  styleClass="selecthome"  id="title" />
                   </td>
                   <td>Ordered Copies</td>
                   <td>
                       <input type="text" name="no_of_copies" styleClass="selecthome"  id="no_of_copies" />
                   </td>
                   <td>Prev Received</td>
                   <td>
                       <input type="text" name="recieved_copies" styleClass="selecthome"  id="recieved_copies" />
                   </td>
                   <td>Pending</td>
                   <td>
                       <input type="text" name="pending_copies" styleClass="selecthome"  id="pending_copies" />
                   </td>

             </tr>
             </table>


           </td>
        </tr>
--%>
<tr> <td align="center">
<input type="button" onclick="return send()" name="button" value="Back" Class="txt1"/>
                 </td></tr>

</form>
</div>
</table

</body>



 <script language="javascript">



 function get_check_value()
 {
   var c_value  ;

   for (var i=0; i < document.f.check.length; i++)
   {   
       if (document.f.check[i].checked)
      {    var va=document.f.check[i].value;
           document.f.list2.value=va;
           var j,k;
      //     alert(va);
           if(c_value==null)
          {
           j=i;
           k=i;
            alert("value of i="+i);
            c_value =document.f.check[i].value;
          }
          else
           {
              j=j+","+i;
              k=i;
              c_value = c_value +";"+ document.f.check[i].value;
               alert("value of i="+i);
           }
          document.f.i.value=j;
          document.f.sizearrayindex.value=k;
         // alert("testaqeel"+document.f.i.value);
       }
    
       
   }

  var sizearray=document.f.check.length;
   document.f.sizearray.value=sizearray;
   
  // alert(c_value);
 <%--  var d=document.f.disc.value;
   alert("test"+d);--%>
   document.f.list.value=c_value;
   document.f.action="<%=request.getContextPath()%>/invoice_item2.do";
   document.f.method="post";
   document.f.target="f2";
   document.f.submit();
}
</script>


</html>