<%--
    Document   : Simple.jsp
    Created on : Jun 18, 2010, 7:46:24 AM
    Author     : Azam/Faraz
<jsp:include page="adminheader.jsp" flush="true" />
--%>
 
    <%@page import="com.myapp.struts.admin.RequestDoc,com.myapp.struts.hbm.*"%>
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

    <title>View approval</title>
    <%
try{
if(session.getAttribute("library_id")!=null){
System.out.println("library_id"+session.getAttribute("library_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

%>
    

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>


</head>

<body>
 <div
   style="  top:0px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
<%
   
   
   RequestDoc Ob;
   List requestList;
   int fromIndex=0, toIndex;
%>
 <%

 List rs = (List)(session.getAttribute("resultset1"));

 if(rs!=null){
 Iterator it = rs.iterator();
       

  // requestList = new List();
   int tcount =0;
   int perpage=4;
   int tpage=0;
 /*Create a connection by using getConnection() method
   that takes parameters of string type connection url,
   user name and password to connect to database.*/



requestList = new ArrayList();
   while (it.hasNext()) {
	AdminRegistration adminReg = (AdminRegistration)rs.get(tcount);
       tcount++;
	Ob = new RequestDoc ();
	Ob.setRegistration_id(adminReg.getRegistrationId());
	Ob.setInstitute_name(adminReg.getInstituteName());
	Ob.setAdmin_email(adminReg.getAdminEmail());
        Ob.setWorking_status(adminReg.getWorkingStatus());

    
   requestList.add(Ob);
   adminReg=null;
it.next();
   //System.out.println("tcount="+tcount);
		     }

System.out.println("tcount="+tcount);

%>
       
<%
   //if (tcount!=0){
    fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   //}String pageurl = request.getContextPath()+"/admin/view_blocked_institute.jsp?p_datagrid_datagrid1_page_index=0&p_datagrid_datagrid1_order_index=0";
    //pageContext.setAttribute("pageurl", pageurl);
%>


<br><br>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Record Found</p>
<%}
else
{%>


<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

<%
  String RegistrationID=resource.getString("admin.viewpending.registrationid");
  pageContext.setAttribute("RegistrationID", RegistrationID);
  String InstituteName=resource.getString("admin.viewpending.institutename");
  pageContext.setAttribute("InstituteName", InstituteName);
  String AdminEmail=resource.getString("admin.viewpending.adminemail");
  pageContext.setAttribute("AdminEmail",AdminEmail);
  String Action=resource.getString("admin.viewpending.action");
  pageContext.setAttribute("Action",Action);
  String WorkingStatus=resource.getString("admin.viewblockinstitute.workingstatus");
  pageContext.setAttribute("WorkingStatus",WorkingStatus);

%>

<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">
    
  <columns>
      
    <column width="50">
      <header value="" hAlign="left" styleClass="header"/>
    </column>

    <column width="100">
      <header value="${RegistrationID}" hAlign="left" styleClass="header"/>
      <item   value="${doc.registration_id}" hyperLink="block_institute_index.jsp?id=${doc.registration_id}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="200">
      <header value="${InstituteName}" hAlign="left" styleClass="header"/>
      <item   value="${doc.institute_name}" hAlign="left" hyperLink="block_institute_index.jsp?id=${doc.registration_id}"  styleClass="item"/>
    </column>

     <column width="200">
      <header value="${AdminEmail}" hAlign="left" styleClass="header"/>
      <item   value="${doc.admin_email}" hyperLink="block_institute_index.jsp?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
 <column width="100">
      <header value="${WorkingStatus}" hAlign="left" styleClass="header"/>
      <item   value="${doc.working_status}" hyperLink="block_institute_index.jsp?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>

      <column width="100">
      <header value="${Action}" hAlign="left" styleClass="header"/>
      <item   value="Change Status" hyperLink="block_institute_index.jsp?id=${doc.registration_id}"  hAlign="left" styleClass="item1"/>
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
<td align="left" width="100px">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>">Next</a>
</c:if>

</td><td align="center" width="400px">

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
</td><td align="center">
     Import :<img src="<%=request.getContextPath()%>/images/excel.jpeg" border="1" height="25" width="25">
    <img src="<%=request.getContextPath()%>/images/xml.jpeg" height="25" border="1" width="25">
    <img src="<%=request.getContextPath()%>/images/pdf.jpeg" height="25"border="1" width="25">
</td>


</tr>
</table>
<%}}
else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
}%>
 </div>
    </body>



</html>


