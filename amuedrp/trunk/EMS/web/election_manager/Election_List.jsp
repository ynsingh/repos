<%-- 
    Document   : Election_List
    Created on : Jun 20, 2011, 3:53:40 PM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/election_manager/login.jsp"/>
<%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*,com.myapp.struts.hbm.Election"%>

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
List rst =(List)session.getAttribute("resultset");
int count=(Integer)session.getAttribute("count");

 int i=1;
 
%>

<%
String Election_Id=resource.getString("electionid");
pageContext.setAttribute("Election_Id",Election_Id );
String Election_Name=resource.getString("electionname");
pageContext.setAttribute("Election_Name", Election_Name);
String Status=resource.getString("workingstatus");
pageContext.setAttribute("Status", Status);

String Action=resource.getString("login.ems.action");
pageContext.setAttribute("Action",Action);
String Edit=resource.getString("edit");
pageContext.setAttribute("Edit",Edit);

%>
    </head>
    <body dir="<%=rtl%>" >


       

       <%!


   StaffDoc Ob;
 //  VoterRegistration voter;
   Election election;
  // Election_Manager_StaffDetail ems;
  // AdminRegistration adminReg;
   //ElectionManager electionmanager;
   //StaffDetail staffdetail;

   ArrayList requestList;
   int fromIndex=0, toIndex;

%>



 <%

 List<ElectionRuleEligiblity1> rs = (List<ElectionRuleEligiblity1>)session.getAttribute("resultset1");


   requestList = new ArrayList();
//requestList = (ArrayList)session.getAttribute("resultset");
   int tcount =0;
   int perpage=10;
   int tpage=0;
 /*Create a connection by using getConnection() method
   that takes parameters of string type connection url,
   user name and password to connect to database.*/
if(rs!=null){
  Iterator it = rs.iterator();
System.out.println("it="+(tcount)+rs.size());
//requestList = (Login)rs.get(0);

   while (it.hasNext()) {

	System.out.println("it="+(tcount));
        election = (Election)rs.get(tcount).getElection();
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
       // Ob.setEnrollment(voter.getId().getEnrollment());
        //Ob.setVoter_name(voter.getVoterName());
        //Ob.setDepartment(voter.getDepartment());
        //Ob.setCourse(voter.getCourse());
        //Ob.setYear(voter.getYear());
        //Ob.setStatus(voter.getStatus());
        Ob.setElection_id(election.getId().getElectionId());
        Ob.setElection_name(election.getElectionName());
        Ob.setStatus(election.getStatus());
        //ems.getElectionManager().setStatus(ems.getElectionManager().getStatus());









   requestList.add(Ob);
   tcount++;
it.next();
   //System.out.println("tcount="+tcount);
		     }

System.out.println("tcount="+tcount);

   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+10) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   }

String path=request.getContextPath();
pageContext.setAttribute("path", path);
String msg1=(String)request.getAttribute("msg1");

  %>



 <font color="blue" size="-1" dir="<%=rtl%>">
               <%=resource.getString("voterreq")%>(<%=count%>) &nbsp;<a href="<%=contextPath%>/election_manager/pending_voter.jsp"><%=resource.getString("voter")%> </a>
            </font>
<br>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("no_record_found")%></p>
<%}
else
{%>
<table align="<%=align%>" dir="<%=rtl%>" width="80%" style="top:150px;position: absolute;z-index: 30 ">
    <tr dir="<%=rtl%>"><td dir="<%=rtl%>">
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">

  <columns>

    <column width="10%">
      <header value="${Election_Id}" hAlign="left" styleClass="header"/>
      <item   value="${doc.election_id}"   hAlign="left"    styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${Election_Name}" hAlign="left" styleClass="header"/>
      <item   value="${doc.election_name}"  styleClass="item"/>
    </column>








      <column width="10%">
      <header value="${Status}" hAlign="left" styleClass="header"/>
      <item   value="${doc.status}"   hAlign="left" styleClass="item"/>
    </column>
<column width="10%">
      <header value="Action" hAlign="left" styleClass="header"/>
      <item   value="Update" hyperLink="${path}/electionview1.do?id=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>

<column width="10%">
      <header value="Action" hAlign="left" styleClass="header"/>
      <item   value="View" hyperLink="${path}/electionview.do?id=${doc.election_id}&amp;st='y'"  hAlign="left" styleClass="item"/>
    </column>
    <column width="10%">
      <header value="Action" hAlign="left" styleClass="header"/>
      <item   value="Results" hyperLink="${path}/Voter/result.jsp?election=${doc.election_id}&amp;"  hAlign="left" styleClass="item"/>
    </column>
<column width="10%">
<header value="Action" hAlign="left" styleClass="header"/>

   
      <item   value="Cast Vote" hyperLink="${path}/voting.do?election=${doc.election_id}"  hAlign="left" styleClass="item"/>


  </column>
      

      <column width="10%">
      <header value="Action" hAlign="left" styleClass="header"/>
      <item   value="Preview Ballot" hyperLink="${path}/electionview.do?id=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>

       <column width="10%">
      <header value="Action" hAlign="left" styleClass="header"/>
      <item   value="Candidate List" hyperLink="${path}/AllCandiList.do?election=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>

      <column width="10%">
      <header value="Action" hAlign="left" styleClass="header"/>
      <item   value="Voter List" hyperLink="${path}/voterlist.do?election=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>

 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="10" count="${tCount}" custom="true" nextUrlVar="next"
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

      
</table>
  <%

String msg=(String)request.getAttribute("msg");
if(msg!=null)
    {%>
    <p class="err" style="font-size:12px"><%=msg%></p>


<%}%>
  <%

msg=(String)request.getAttribute("msgerr");
if(msg!=null)
    {%>
    <p class="err" style="font-size:12px"><%=msg%></p>


<%}%>

  <%}%></td></tr>
</table>
    </body>

</html>
