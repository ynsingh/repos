<%--
    Document   : Simple.jsp
    Created on : Jun 18, 2010, 7:46:24 AM
    Author     : Mayank Saxena
--%>

    <%@page import="com.myapp.struts.admin.RequestDoc"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.*,com.myapp.struts.hbm.*"%>
       <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
     <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>

    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>View Request</title>


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>

<body>
 <div
   style="  top:0px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
<%!
   
   
   RequestDoc Ob;
   AdminRegistration adminReg;
   ArrayList requestList;
   int fromIndex=0, toIndex;
%>
 <%

 List rs = (List)(session.getAttribute("resultset"));

       

   requestList = new ArrayList();
   int tcount =0;
   int perpage=4;
   int tpage=0;
 
if(rs!=null)
{
   Iterator it = rs.iterator();



   while (it.hasNext())
        {
	
	System.out.println("it="+(tcount));
        adminReg = (AdminRegistration)rs.get(tcount);
        Ob = new RequestDoc ();
	Ob.setRegistration_id(adminReg.getRegistrationId());
	Ob.setInstitute_name(adminReg.getInstituteName());
	Ob.setUserId(adminReg.getLoginId());
	Ob.setAdmin_email(adminReg.getAdminEmail());
        adminReg = null;
        requestList.add(Ob);
        tcount++;
        it.next();
  
	}

System.out.println("tcount="+tcount);

%>
       
<%
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
<br><br>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Pending Request Found</p>
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
  String Registration_ID=resource.getString("admin.viewpending.registrationid");
  pageContext.setAttribute("Registration_ID", Registration_ID);
  String InstituteName=resource.getString("admin.viewpending.institutename");
  pageContext.setAttribute("InstituteName", InstituteName);
  String UserId=resource.getString("admin.viewpending.userid");
  pageContext.setAttribute("UserId", UserId);
  String Admin_Email=resource.getString("admin.viewpending.adminemail");
  pageContext.setAttribute("Admin_Email",Admin_Email);
  String Action=resource.getString("admin.viewpending.action");
  pageContext.setAttribute("Action",Action);

%>

<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">
    
  <columns>
      
    <column width="100">
      <header value="${Registration_ID}" hAlign="left" styleClass="header"/>
      <item   value="${doc.registration_id}" hyperLink="view1.do?id=${doc.registration_id}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="250">
      <header value="${InstituteName}" hAlign="left" styleClass="header"/>
      <item   value="${doc.institute_name}" hAlign="left" hyperLink="view1.do?id=${doc.registration_id}"  styleClass="item"/>
    </column>
    <column width="100">
      <header value="${UserId}" hAlign="left" styleClass="header"/>
      <item   value="${doc.userId}" hAlign="left" hyperLink="view1.do?id=${doc.registration_id}"  styleClass="item"/>
    </column>
       
    <column width="150">
      <header value="${Admin_Email}" hAlign="left" styleClass="header"/>
      <item   value="${doc.admin_email}" hyperLink="view1.do?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
       <column width="50">
      <header value="${Action}" hAlign="left" styleClass="header"/>
      <item   value="Accept" hyperLink="view1.do?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
 <table width="700" style="font-family: arial; font-size: 10pt" >
<tr>
<td align="left" width="100px">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>">Next</a>
</c:if>

</td><td width="400px" align="center">

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
<%}}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
}%>
 </div>
    </body>

</html>


