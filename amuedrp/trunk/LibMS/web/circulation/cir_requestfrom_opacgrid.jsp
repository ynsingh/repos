<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
      This jsp page is used for Displaying Grid when Request From OPAC is Clicked From MENU
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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script language="javascript" type="text/javascript">

function newXMLHttpRequest() {
var xmlreq = false;
// Create XMLHttpRequest object in non-Microsoft browsers
if (window.XMLHttpRequest) {
xmlreq = new XMLHttpRequest();
} else if (window.ActiveXObject) {
try {
// Try to create XMLHttpRequest in later versions
// of Internet Explorer
xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e1) {
// Failed to create required ActiveXObject
try {
// Try version supported by older versions
// of Internet Explorer
xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
} catch (e2) {
// Unable to create an XMLHttpRequest by any means
xmlreq = false;
}
}
}
return xmlreq;
}
/*
* Returns a function that waits for the specified XMLHttpRequest
* to complete, then passes it XML response to the given handler function.
* req - The XMLHttpRequest whose state is changing
* responseXmlHandler - Function to pass the XML response to
*/
function getReadyStateHandler(req, responseXmlHandler) {
// Return an anonymous function that listens to the XMLHttpRequest instance
return function () {
// If the request's status is "complete"
if (req.readyState == 4) {
// Check that we received a successful response from the server
if (req.status == 200) {
// Pass the XML payload of the response to the handler function.
responseXmlHandler(req.responseXML);
} else {
// An HTTP problem has occurred
alert("HTTP error "+req.status+": "+req.statusText);
}
}
}
}
function search() {
  

    var keyValue = document.getElementById('sublibary_id').options[document.getElementById('sublibary_id').selectedIndex].value;

    if (keyValue=="Select")
    {


               document.getElementById('sublibary_id').focus();
             



		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update);

req.open("POST","<%=request.getContextPath()%>/requestfromopac.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getSubLibraryId="+keyValue);


}
return true;
}
}




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
<%
String host=request.getContextPath()+"/circulation/cir_opac_viewmem.jsp";
%>

    </head>
    <body>

<%!

   ResultSet rs=null;
   ArrayList opacList;
   int fromIndex, toIndex;
%>

<%
String path= request.getContextPath();
 pageContext.setAttribute("path", path);

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
    String sublibrary_id=(String)session.getAttribute("sublibrary_id");
String login_role=(String)session.getAttribute("login_role");
%>

 <%
String LibraryId=resource.getString("opac.browse.table.Libraryid");
pageContext.setAttribute("LibraryId", LibraryId);
String MemberId=resource.getString("circulation.cir_newmember.memberid");
pageContext.setAttribute("MemberId", MemberId);
String FirstName=resource.getString("circulation.cir_newmember.fname");
pageContext.setAttribute("FirstName",FirstName);
String LastName=resource.getString("circulation.cir_newmember.lname");
pageContext.setAttribute("LastName",LastName);
String EmailId=resource.getString("circulation.cir_newmember.email");
pageContext.setAttribute("EmailId",EmailId);
String Address=resource.getString("circulation.cir_newmember.permadd");
pageContext.setAttribute("Address",Address);
String Status=resource.getString("circulation.cirviewall.status");
pageContext.setAttribute("Status",Status);
String RequestDate=resource.getString("circulation.cirviewall.reqdate");
pageContext.setAttribute("RequestDate",RequestDate);
%>

<%
System.out.println(login_role);
if(login_role.equalsIgnoreCase("insti-admin")){

%>

 
<table width="750" dir="<%=rtl%>" align="<%=align%>" style="font-family: arial; font-size: 10pt" border=0>
 

    <tr><td dir="<%=rtl %>">


<%}
if(tcount==0)
{
%>
<p class="err"><%=resource.getString("circulation.cir_reqfromopacgrid.noreqfromopac")%></p>
<%}
else
{%>


     <form name="f"  method="post" >

         
<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0"
    cellSpacing="0" styleClass="datagrid"  >

  <columns>



    <column width="1800">
      <header value="${LibraryId}" hAlign="left" styleClass="admingridheader"  />
      <item   value="${doc.libraryId}"     hAlign="left"   styleClass="item"  hyperLinkTarget="_parent" hyperLink="${path}/circulation/cir_opac_viewmem.jsp?memid=${doc.memId}"/>

    </column>

    <column width="600">
      <header value="${MemberId}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.memId}"   hAlign="left"  styleClass="item" hyperLinkTarget="_parent" hyperLink="${path}/circulation/cir_opac_viewmem.jsp?memid=${doc.memId}"/>
    </column>
     <column width="600">
      <header value="${FirstName}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.fname}"  hAlign="left"  styleClass="item" hyperLinkTarget="_parent" hyperLink="${path}/circulation/cir_opac_viewmem.jsp?memid=${doc.memId}"/>
    </column>
      <column width="600">
      <header value="${LastName}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.lname}"  hAlign="left"  styleClass="item" hyperLinkTarget="_parent" hyperLink="${path}/circulation/cir_opac_viewmem.jsp?memid=${doc.memId}"/>
    </column>
     <column width="600">
      <header value="${EmailId}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.email}"  hAlign="left"  styleClass="item" hyperLinkTarget="_parent" hyperLink="${path}/circulation/cir_opac_viewmem.jsp?memid=${doc.memId}"/>
    </column>
     <column width="600">
      <header value="${Address}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.address1}"  hAlign="left"  styleClass="item" hyperLinkTarget="_parent" hyperLink="${path}/circulation/cir_opac_viewmem.jsp?memid=${doc.memId}"/>
    </column>
     <column width="600">
      <header value="${Status}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.status}"  hAlign="left"  styleClass="item" hyperLinkTarget="_parent" hyperLink="${path}/circulation/cir_opac_viewmem.jsp?memid=${doc.memId}"/>
    </column>
      <column width="1800">
      <header value="${RequestDate}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.requestdate}"  hAlign="left"  styleClass="item" hyperLinkTarget="_parent"  hyperLink="${path}/circulation/cir_opac_viewmem.jsp?memid=${doc.memId}"/>
    </column>

 </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

  <%--  <table width="750" dir="<%=rtl%>" style="font-family: arial; font-size: 10pt" border=0> --%>
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

  <%}%>
 </form>
 </td>
         
  </tr>
  
  

</table>


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





