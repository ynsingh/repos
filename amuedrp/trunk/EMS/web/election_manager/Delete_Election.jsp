<%-- 
    Document   : Election_List
    Created on : Jun 20, 2011, 3:53:40 PM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<jsp:include page="/election_manager/login.jsp"/>
<%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*,com.myapp.struts.hbm.Election,com.myapp.struts.utility.*"%>

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
         
        <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";
    boolean page=true;
    String align="left";
%>

<%
try
{
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
    <body dir="<%=rtl%>" style=" background-image: url('/EMS/images/paperbg.gif'); margin-top:0; margin-bottom:0;">


       

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

                    
 List rs =(List) session.getAttribute("resultset1");

System.out.println("Result size is "+rs.size());
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

   while (it.hasNext()) {

	election=(Election)rs.get(tcount);
      
       // staffdetail = (StaffDetail)rs.get(tcount).getStaffDetail();

        Ob = new StaffDoc ();
        //ems=new Election_Manager_StaffDetail();

       // Ob.setmanager_id(electionmanager.getId().getManagerId());
        //Ob.setinstitute_id(electionmanager.getId().getInstituteId());
        //Ob.setfirst_ame(staffdetail.getFirstName());
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
        if(Ob.getStatus().equalsIgnoreCase("under-process"))
            pageContext.setAttribute("votingStatus", "Not-Started");
        else if(Ob.getStatus().equalsIgnoreCase("started"))
            pageContext.setAttribute("votingStatus", "Started");
        else
            pageContext.setAttribute("votingStatus", "Closed");


        String institute_id=(String)session.getAttribute("institute_id");

            ElectionManagerDAO dao=new ElectionManagerDAO();


         List     list=dao.VotedVoterList(institute_id,election.getId().getElectionId());
if(list!=null)
         Ob.setTotalvoted(String.valueOf(list.size()));
         else
Ob.setTotalvoted("0");






   requestList.add(Ob);
   tcount++;
it.next();
   
		     }



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



 <font dir="<%=rtl%>">
  <%=resource.getString("voterreq")%>(<%=count%>) &nbsp;<a href="<%=contextPath%>/election_manager/pending_voter.jsp">Click here </a>
            </font>
<br>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("no_record_found")%></p>
<%}
else
{%>
<table align="center" dir="<%=rtl%>" width="95%" >
    <tr dir="<%=rtl%>"><td dir="<%=rtl%>">
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">

  <columns>

    <column width="5%">
      <header value="${Election_Id}" hAlign="center" styleClass="header"/>
      <item   value="${doc.election_id}"   hAlign="center"    styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${Election_Name}" hAlign="left" styleClass="header"/>
      <item   value="${doc.election_name}"  styleClass="item"/>
    </column>





 <column width="5%">
      <header value="Voting Status" hAlign="left" styleClass="header"/>
      <item   value="${votingStatus}"   hAlign="left" styleClass="item"/>
    </column>


      <column width="5%">
      <header value="${Status}" hAlign="left" styleClass="header"/>
      <item   value="${doc.status}"   hAlign="left" styleClass="item"/>
    </column>
<column width="3%">
      <header value="Action" hAlign="left" styleClass="header"/>
      <item   value="Delete" hyperLink="${path}/electionview1.do?id=${doc.election_id}&amp;bt='del'"  hAlign="left" styleClass="item"/>
    </column>

<column width="3%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="View" hyperLink="${path}/electionview.do?id=${doc.election_id}&amp;st='y'"  hAlign="left" styleClass="item"/>
    </column>
    <column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="Results" hyperLink="${path}/Voter/finalresult.jsp?election=${doc.election_id}&amp;"  hAlign="left" styleClass="item"/>
    </column> 
    
    
<column width="5%">
<header value="" hAlign="left" styleClass="header"/>

   
      <item   value="Cast Vote" hyperLink="${path}/voting.do?election=${doc.election_id}"  hAlign="left" styleClass="item"/>


  </column>
      <column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="PreferencialResults" hyperLink="${path}/Voter/Preferencialfinalresult.jsp?election=${doc.election_id}&amp;"  hAlign="left" styleClass="item"/>
    </column>

      <column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="Preview Ballot" hyperLink="${path}/electionview.do?id=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>

       <column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="Candidate List" hyperLink="${path}/AllCandiList.do?election=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>

      <column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="Voter List" hyperLink="${path}/voterlist.do?election=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>

 <column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="Publish Election" hyperLink="${path}/electionview1.do?id=${doc.election_id}&amp;publish='y'"  hAlign="left" styleClass="item"/>
    </column>
        <column width="5%">
      <header value="Vote Cast Till Date" hAlign="left" styleClass="header"/>
      <item   value="${doc.totalvoted}" hyperLink="${path}/votedvoterlist.do?election=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>
      <column width="3%">
      <header value="View All Voted Voter" hAlign="left" styleClass="header"/>
      <item   value="PDF" hyperLink="${path}/votedvoterlist.do?election=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>
       <column width="3%" >
      <header value=""   hAlign="left" styleClass="header"   />
   <item>
      <![CDATA[<a href="/EMS/xmlexport.do?election=${doc.election_id}">XML</a>]]>
  </item>

    </column>
   <column width="3%" >
      <header value=""   hAlign="left" styleClass="header"   />
   <item>
      <![CDATA[<a href="/EMS/xlsexport.do?election=${doc.election_id}">XLS</a>]]>
  </item>

    </column>
   
       <column width="5%" >
      <header value=""   hAlign="left" styleClass="header"   />
   <item>
      <![CDATA[<a href="/EMS/csvexport.do?election=${doc.election_id}&amp;export='csv'">CSV</a>]]>
  </item>

    </column>


 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="10" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

  <table width="95%" style="font-family: arial; font-size: 10pt" border=0>
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
  if(msg!=null){
  //    out.println(msg);
  }

  
String msg2=(String)session.getAttribute("exportmsg");
if(msg2!=null)
    {
  out.println(msg2);
  %>
   <%-- <p class="err" style="font-size:12px"><%=msg%></p>--%>
 <% String requestpage=(String)request.getAttribute("exportpath");%>
    <a href="<%=request.getContextPath()%>/Export/<%=requestpage%>" target="_blank">Download It</a>

<%}
 session.removeAttribute("exportmsg");
%>

<%
     String msgxls=(String)request.getAttribute("msgxls");
     if(msgxls!=null){out.println(msgxls);
%>
<%
  String filename=(String)session.getAttribute("filename");%>
   <a href="<%=request.getContextPath()%>/Export/<%=filename%>" target="_blank">Download It</a>
<%  }
%>

  <%

msg=(String)request.getAttribute("msgerr");
if(msg!=null)
    {%>
    <p class="err" style="font-size:12px"><%=msg%></p>


<%}%>

  <%}%></td>

  <td align="right">
    
</td>

  </tr>
</table>
    </body>

</html>


<column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="PreferencialResults" hyperLink="${path}/Voter/Preferencialfinalresult.jsp?election=${doc.election_id}&amp;"  hAlign="left" styleClass="item"/>
    </column>
<%-- 
    Document   : Election_List
    Created on : Jun 20, 2011, 3:53:40 PM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<jsp:include page="/election_manager/login.jsp"/>
<%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*,com.myapp.struts.hbm.Election,com.myapp.struts.utility.*"%>

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
         
        <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";
    boolean page=true;
    String align="left";
%>

<%
try
{
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
    <body dir="<%=rtl%>" style=" background-image: url('/EMS/images/paperbg.gif'); margin-top:0; margin-bottom:0;">


       

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

                    
 List rs =(List) session.getAttribute("resultset1");

System.out.println("Result size is "+rs.size());
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

   while (it.hasNext()) {

	election=(Election)rs.get(tcount);
      
       // staffdetail = (StaffDetail)rs.get(tcount).getStaffDetail();

        Ob = new StaffDoc ();
        //ems=new Election_Manager_StaffDetail();

       // Ob.setmanager_id(electionmanager.getId().getManagerId());
        //Ob.setinstitute_id(electionmanager.getId().getInstituteId());
        //Ob.setfirst_ame(staffdetail.getFirstName());
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
        if(Ob.getStatus().equalsIgnoreCase("under-process"))
            pageContext.setAttribute("votingStatus", "Not-Started");
        else if(Ob.getStatus().equalsIgnoreCase("started"))
            pageContext.setAttribute("votingStatus", "Started");
        else
            pageContext.setAttribute("votingStatus", "Closed");


        String institute_id=(String)session.getAttribute("institute_id");

            ElectionManagerDAO dao=new ElectionManagerDAO();


         List     list=dao.VotedVoterList(institute_id,election.getId().getElectionId());
if(list!=null)
         Ob.setTotalvoted(String.valueOf(list.size()));
         else
Ob.setTotalvoted("0");






   requestList.add(Ob);
   tcount++;
it.next();
   
		     }



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



 <font dir="<%=rtl%>">
  <%=resource.getString("voterreq")%>(<%=count%>) &nbsp;<a href="<%=contextPath%>/election_manager/pending_voter.jsp">Click here </a>
            </font>
<br>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("no_record_found")%></p>
<%}
else
{%>
<table align="center" dir="<%=rtl%>" width="95%" >
    <tr dir="<%=rtl%>"><td dir="<%=rtl%>">
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">

  <columns>

    <column width="5%">
      <header value="${Election_Id}" hAlign="center" styleClass="header"/>
      <item   value="${doc.election_id}"   hAlign="center"    styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${Election_Name}" hAlign="left" styleClass="header"/>
      <item   value="${doc.election_name}"  styleClass="item"/>
    </column>





 <column width="5%">
      <header value="Voting Status" hAlign="left" styleClass="header"/>
      <item   value="${votingStatus}"   hAlign="left" styleClass="item"/>
    </column>


      <column width="5%">
      <header value="${Status}" hAlign="left" styleClass="header"/>
      <item   value="${doc.status}"   hAlign="left" styleClass="item"/>
    </column>
<column width="3%">
      <header value="Action" hAlign="left" styleClass="header"/>
      <item   value="Delete" hyperLink="${path}/electionview1.do?id=${doc.election_id}&amp;bt='del'"  hAlign="left" styleClass="item"/>
    </column>

<column width="3%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="View" hyperLink="${path}/electionview.do?id=${doc.election_id}&amp;st='y'"  hAlign="left" styleClass="item"/>
    </column>
    <column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="Results" hyperLink="${path}/Voter/finalresult.jsp?election=${doc.election_id}&amp;"  hAlign="left" styleClass="item"/>
    </column> 
    
    
<column width="5%">
<header value="" hAlign="left" styleClass="header"/>

   
      <item   value="Cast Vote" hyperLink="${path}/voting.do?election=${doc.election_id}"  hAlign="left" styleClass="item"/>


  </column>
      <column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="PreferencialResults" hyperLink="${path}/Voter/Preferencialfinalresult.jsp?election=${doc.election_id}&amp;"  hAlign="left" styleClass="item"/>
    </column>

      <column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="Preview Ballot" hyperLink="${path}/electionview.do?id=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>

       <column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="Candidate List" hyperLink="${path}/AllCandiList.do?election=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>

      <column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="Voter List" hyperLink="${path}/voterlist.do?election=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>

 <column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="Publish Election" hyperLink="${path}/electionview1.do?id=${doc.election_id}&amp;publish='y'"  hAlign="left" styleClass="item"/>
    </column>
        <column width="5%">
      <header value="Vote Cast Till Date" hAlign="left" styleClass="header"/>
      <item   value="${doc.totalvoted}" hyperLink="${path}/votedvoterlist.do?election=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>
      <column width="3%">
      <header value="View All Voted Voter" hAlign="left" styleClass="header"/>
      <item   value="PDF" hyperLink="${path}/votedvoterlist.do?election=${doc.election_id}"  hAlign="left" styleClass="item"/>
    </column>
       <column width="3%" >
      <header value=""   hAlign="left" styleClass="header"   />
   <item>
      <![CDATA[<a href="/EMS/xmlexport.do?election=${doc.election_id}">XML</a>]]>
  </item>

    </column>
   <column width="3%" >
      <header value=""   hAlign="left" styleClass="header"   />
   <item>
      <![CDATA[<a href="/EMS/xlsexport.do?election=${doc.election_id}">XLS</a>]]>
  </item>

    </column>
   
       <column width="5%" >
      <header value=""   hAlign="left" styleClass="header"   />
   <item>
      <![CDATA[<a href="/EMS/csvexport.do?election=${doc.election_id}&amp;export='csv'">CSV</a>]]>
  </item>

    </column>


 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="10" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

  <table width="95%" style="font-family: arial; font-size: 10pt" border=0>
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
  if(msg!=null){
  //    out.println(msg);
  }

  
String msg2=(String)session.getAttribute("exportmsg");
if(msg2!=null)
    {
  out.println(msg2);
  %>
   <%-- <p class="err" style="font-size:12px"><%=msg%></p>--%>
 <% String requestpage=(String)request.getAttribute("exportpath");%>
    <a href="<%=request.getContextPath()%>/Export/<%=requestpage%>" target="_blank">Download It</a>

<%}
 session.removeAttribute("exportmsg");
%>

<%
     String msgxls=(String)request.getAttribute("msgxls");
     if(msgxls!=null){out.println(msgxls);
%>
<%
  String filename=(String)session.getAttribute("filename");%>
   <a href="<%=request.getContextPath()%>/Export/<%=filename%>" target="_blank">Download It</a>
<%  }
%>

  <%

msg=(String)request.getAttribute("msgerr");
if(msg!=null)
    {%>
    <p class="err" style="font-size:12px"><%=msg%></p>


<%}%>

  <%}%></td>

  <td align="right">
    
</td>

  </tr>
</table>
    </body>

</html>


<column width="5%">
      <header value="" hAlign="left" styleClass="header"/>
      <item   value="PreferencialResults" hyperLink="${path}/Voter/Preferencialfinalresult.jsp?election=${doc.election_id}&amp;"  hAlign="left" styleClass="item"/>
    </column>