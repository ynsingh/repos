<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
      This jsp page is used for Displaying Grid when View All is Clicked From MENU
      This Jsp page Recieves POJO in form of LIST Which Further used for Populating.
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

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

   opacList = new ArrayList ();
   opacList=(ArrayList)session.getAttribute("cirrequestfromopac");
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

<div
   style="  top:200px;
   left:200px;
   right:5px;
      position: absolute;

      visibility: show;">



<%

if(tcount==0)
{
%>
<p class="err">No Record To Display</p>
<%}
else
{%>


     <form name="f"  method="post" >


<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0"
    cellSpacing="0" styleClass="datagrid"  >

  <columns>



    <column width="100">
      <header value="LibraryId" hAlign="left" styleClass="header"  />
      <item   value="${doc.libraryId}"     hAlign="left"   styleClass="item"  hyperLink="./circulation/cir_viewallaftergrid.jsp?memid=${doc.memId}"/>

    </column>

    <column width="100">
      <header value="MemberId" hAlign="left" styleClass="header"/>
      <item   value="${doc.memId}"   hAlign="left"  styleClass="item"  hyperLink="./circulation/cir_viewallaftergrid.jsp?memid=${doc.memId}"/>
    </column>
     <column width="100">
      <header value="FirstName" hAlign="left" styleClass="header"/>
      <item   value="${doc.fname}"  hAlign="left"  styleClass="item"  hyperLink="./circulation/cir_viewallaftergrid.jsp?memid=${doc.memId}"/>
    </column>
      <column width="100">
      <header value="LastName" hAlign="left" styleClass="header"/>
      <item   value="${doc.lname}"  hAlign="left"  styleClass="item"  hyperLink="./circulation/cir_viewallaftergrid.jsp?memid=${doc.memId}"/>
    </column>
     <column width="100">
      <header value="EmailId" hAlign="left" styleClass="header"/>
      <item   value="${doc.email}"  hAlign="left"  styleClass="item"  hyperLink="./circulation/cir_viewallaftergrid.jsp?memid=${doc.memId}"/>
    </column>
     <column width="100">
      <header value="Address" hAlign="left" styleClass="header"/>
      <item   value="${doc.address1}"  hAlign="left"  styleClass="item"  hyperLink="./circulation/cir_viewallaftergrid.jsp?memid=${doc.memId}"/>
    </column>
     <column width="100">
      <header value="Status" hAlign="left" styleClass="header"/>
      <item   value="${doc.status}"  hAlign="left"  styleClass="item"  hyperLink="./circulation/cir_viewallaftergrid.jsp?memid=${doc.memId}"/>
    </column>
      <column width="100">
      <header value="Request Date" hAlign="left" styleClass="header"/>
      <item   value="${doc.requestdate}"  hAlign="left"  styleClass="item"  hyperLink="./circulation/cir_viewallaftergrid.jsp?memid=${doc.memId}"/>
    </column>

 </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

<table width="750" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>">Next</a>
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
  <script language="javascript">
      var start=0;
       var myArray=new Array();
      function checkedItemCount(myCheckbox )
      {

        alert(myCheckbox);
        if (myCheckbox.checked)
          {
              alert(myCheckbox+"....");
              myArray[start]=myCheckbox;
        start++;

        }
      }

      function get_check_value()
{
   // alert("Hello");
var c_value  ;

for (var i=0; i < document.f.check.length; i++)
   {
       if (document.f.check[i].checked)
      {

           if(c_value==null)
            c_value =document.f.check[i].value;
           else
           {
              c_value = c_value +";"+ document.f.check[i].value;
           }
        }
      }



   alert(c_value);
   document.f.list.value=c_value;
}

function passvalue(memid)
{
   alert("memid="+memid);
   document.getElementById("memid").value = memid;
    if (memid!=null)
        return true;
    else
        return false;
}


  </script>
  <tr><td align="center" width="400px">

          <div align="left">
      <%-- <input type="hidden" name="list" value="" >
    <input type="submit" name="button" value="Approved" >
    <input type="submit" name="button" value="Cancel Approval" >--%>
    <input type="button" name="button" value="Back" onclick="return b2click(); ">
  </div>
</form>
  </div>

    </body>
 <%
        //message from ciropacApprovedAction
String msg=(String)request.getAttribute("msg");


//String title=(String)request.getAttribute("title");
           if (msg!=null){
%>
 <script language="javascript">
 window.location="<%=request.getContextPath()%>/circulation/cir_requestfrom_opacgrid.jsp";
 alert("<%=msg%>");
 </script>
 <%
}

%>

<%
        //message from ciropacApprovedAction

String msg1=(String)request.getAttribute("msg1");

//String title=(String)request.getAttribute("title");
           if (msg1!=null){
%>
 <script language="javascript">
 window.location="<%=request.getContextPath()%>/circulation/cir_requestfrom_opacgrid.jsp";
 alert("<%=msg1%>");
 </script>
 <%
}

%>


</html>





