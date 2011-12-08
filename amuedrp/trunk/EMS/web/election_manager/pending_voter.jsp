<%-- 
    Document   : pending_voter
    Created on : Jun 18, 2011, 4:48:20 PM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/election_manager/login.jsp"/>
<%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*,com.myapp.struts.hbm.VoterRegistration"%>

    <%@ page import="java.util.*,java.lang.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Block_Managergrid</title>
         <style>
    th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold }
     .alternaterows { background-color: #efefef }
     .header        { background-color: #7697BC; color: #FFFFFF;font-weight: bold }

     .datagrid      { border: 1px solid #C7C5B2; font-family: arial; font-size: 9pt;
	    font-weight: normal }
</style>
    </head>
    <body>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";
    boolean page=true;
    String align="left";
%>

<%
try{
locale1=(String)session.getAttribute("locale");
sessionId = session.getId().toString();
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
String user=(String)session.getAttribute("username");
String instituteName=(String)session.getAttribute("institute_name");
 String contextPath = request.getContextPath();
 String role=(String)session.getAttribute("login_role");
    %>


<%
String Enrollment_No=resource.getString("enrollment");
pageContext.setAttribute("Enrollment_No",Enrollment_No );
String Voter_Name=resource.getString("votername");
pageContext.setAttribute("Voter_Name", Voter_Name);
String Status=resource.getString("workingstatus");
pageContext.setAttribute("Status", Status);
String Department=resource.getString("department");
pageContext.setAttribute("Department",Department);
String Year=resource.getString("year");
pageContext.setAttribute("Year",Year);
String Course=resource.getString("course");
pageContext.setAttribute("Course",Course);
String Edit=resource.getString("edit");
pageContext.setAttribute("Edit",Edit);

%>
        <%!


   StaffDoc Ob;
   VoterRegistration voter;
  // Election_Manager_StaffDetail ems;
  // AdminRegistration adminReg;
   //ElectionManager electionmanager;
   //StaffDetail staffdetail;

   ArrayList requestList;
   int fromIndex=0, toIndex;

%>



 <%

 List rs = (List)session.getAttribute("resultset");


   requestList = new ArrayList();
//requestList = (ArrayList)session.getAttribute("resultset");
   int tcount =0;
   int perpage=5;
   int tpage=0;
 /*Create a connection by using getConnection() method
   that takes parameters of string type connection url,
   user name and password to connect to database.*/
if(rs!=null){
  Iterator it = rs.iterator();
System.out.println("it="+(tcount));
//requestList = (Login)rs.get(0);

   while (it.hasNext()) {

	System.out.println("it="+(tcount));
        voter = (VoterRegistration)rs.get(tcount);
       // staffdetail = (StaffDetail)rs.get(tcount).getStaffDetail();

        Ob = new StaffDoc ();
        //ems=new Election_Manager_StaffDetail();

       // Ob.setmanager_id(electionmanager.getId().getManagerId());
        //Ob.setinstitute_id(electionmanager.getId().getInstituteId());
        //Ob.setfirst_name(staffdetail.getFirstName());
        //Ob.setlast_name(staffdetail.getLastName());
        //Ob.setStaff_id(electionmanager.getStaffId());
        //Ob.setUser_id(electionmanager.getUserId());
        //Ob.setStatus(electionmanager.getStatus());
        Ob.setEnrollment(voter.getId().getEnrollment());
        Ob.setVoter_name(voter.getVoterName());
        Ob.setDepartment(voter.getDepartment());
        Ob.setCourse(voter.getCourse());
        Ob.setYear(voter.getYear());
        Ob.setStatus(voter.getStatus());
        //ems.getElectionManager().setStatus(ems.getElectionManager().getStatus());









   requestList.add(Ob);
   tcount++;
it.next();
   //System.out.println("tcount="+tcount);
		     }

System.out.println("tcount="+tcount);

   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+5) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   }

String path=request.getContextPath();
pageContext.setAttribute("path", path);
  %>


<br><br>

<%if(tcount==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("no_record_found")%></p>
<%}
else
{%>
<table align="" dir="<%=rtl%>" width="80%">
    <tr dir="<%=rtl%>"><td dir="<%=rtl%>">
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">

  <columns>

    <column width="10%">
      <header value="${Enrollment_No}" hAlign="left" styleClass="header"/>
      <item   value="${doc.enrollment}" hyperLink="${path}/newregistration1.do?id=${doc.enrollment}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${Voter_Name}" hAlign="left" styleClass="header"/>
      <item   value="${doc.voter_name}" hAlign="left" hyperLink="${path}/newregistration1.do?id=${doc.enrollment}"  styleClass="item"/>
    </column>
    <column width="10%">
      <header value="${Department}" hAlign="left" styleClass="header"/>
      <item   value="${doc.department}" hAlign="left" hyperLink="${path}/newregistration1.do?id=${doc.enrollment}"  styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${Course}" hAlign="left" styleClass="header"/>
      <item   value="${doc.course}" hyperLink="${path}/newregistration1.do?id=${doc.enrollment}"  hAlign="left" styleClass="item"/>
    </column>


       <column width="10%">
      <header value="${Year}" hAlign="left" styleClass="header"/>
      <item   value="${doc.year}" hyperLink="${path}/newregistration1.do?id=${doc.enrollment}"  hAlign="left" styleClass="item"/>
    </column>


      <column width="10%">
      <header value="${Status}" hAlign="left" styleClass="header"/>
      <item   value="${doc.status}" hyperLink="${path}/newregistration1.do?id=${doc.enrollment}"  hAlign="left" styleClass="item"/>
    </column>



 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="5" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

  <table width="500" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="100px">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>"><%=resource.getString("previous")%></a>
</c:if>&nbsp;
<c:if test="${next != null}">
    <a href="<c:out value="${next}"/>"><%=resource.getString("next")%></a>
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
</td>

</tr>
<%--<tr><td>
        <%

String msg=(String)request.getAttribute("msg");
if(msg!=null)
    {%>
    <p class="err" style="font-size:12px"><%=msg%></p>


<%}%>

</td></tr>--%>
</table>

  <%}%></td></tr>
</table>
    </body>

</html>
