<%-- 
    Document   : block_managergrid
    Created on : Apr 11, 2011, 4:52:30 PM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*,com.myapp.struts.hbm.ElectionManager"%>
<%--<jsp:include page="/Voter/voter_home.jsp"/>--%>
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


<%
try{
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));
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


   StaffDoc Ob;
   CandidateRegLoginDetails ems;
  // AdminRegistration adminReg;
   VoterRegistration voter;
   StaffDetail staffdetail;
   Election elec;
   Position1 pos;
   ArrayList requestList;
   int fromIndex=0, toIndex;

%>

        <%
String Manager_ID=resource.getString("managerid");
pageContext.setAttribute("ElectionName","Election Name" );
String First_Name=resource.getString("firstname");
pageContext.setAttribute("First_Name", First_Name);
String Last_Name=resource.getString("lastname");
pageContext.setAttribute("Last_Name", Last_Name);
String Staff_id=resource.getString("staffid");
pageContext.setAttribute("Staff_id", Staff_id);
String Working_status=resource.getString("workingstatus");
pageContext.setAttribute("Working_status",Working_status);
String Action=resource.getString("login.ems.action");
pageContext.setAttribute("Action",Action);
String Change_Status=resource.getString("changestatus");
pageContext.setAttribute("Change_Status",Change_Status);

%>


 <%

 List<CandidateRegLoginDetails> rs = (List<CandidateRegLoginDetails>)session.getAttribute("nominationList");


   requestList = new ArrayList();
//requestList = (ArrayList)session.getAttribute("resultset");
   int tcount =0;
   int perpage=4;
   int tpage=0;
tcount=0;
   if(request.getParameter("pageSize")!=null && request.getParameter("pageSize")!="")
    perpage = Integer.parseInt((String)request.getParameter("pageSize"));
 /*Create a connection by using getConnection() method
   that takes parameters of string type connection url,
   user name and password to connect to database.*/
if(rs!=null){
  Iterator it = rs.iterator();
System.out.println("it="+(tcount));
//requestList = (Login)rs.get(0);

   while (it.hasNext()) {

	System.out.println("it="+(tcount));
        voter = (VoterRegistration)rs.get(tcount).getVoterRegistration();
        CandidateRegistration cr = (CandidateRegistration)rs.get(tcount).getCandidateRegistration();
        elec = (Election)rs.get(tcount).getElection();
        pos = (Position1)rs.get(tcount).getPosition1();
        Ob = new StaffDoc ();
        //ems=new Election_Manager_StaffDetail();
        Ob.setIndex(String.valueOf(tcount));
        Ob.setElection_id(elec.getId().getElectionId());
        Ob.setinstitute_id(voter.getId().getInstituteId());
        Ob.setPosition_id(cr.getPosition());
        Ob.setElection_name(elec.getElectionName());
        Ob.setVoter_name(voter.getVoterName());
        Ob.setPositionName(pos.getPositionName());
        Ob.setCandidateName(voter.getVoterName());
        //ems.getElectionManager().setStatus(ems.getElectionManager().getStatus());









   requestList.add(Ob);
   tcount++;
it.next();
   //System.out.println("tcount="+tcount);
		     }

System.out.println("tcount="+tcount);

   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+perpage) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   }

String path=request.getContextPath();
pageContext.setAttribute("path", path);
pageContext.setAttribute("rec",perpage);
  %>
<script type="text/javascript" language="javascript">
            function changerec(){
        var x=document.getElementById('rec').value;
    var loc = window.location;
    loc = "http://<%=request.getHeader("host")%><%=request.getContextPath()%>/Candidate/nominationList.jsp";


            loc = loc + "?pageSize="+x;
    window.location = loc;


    }

   document.onkeyup = keyHit
function keyHit(event) {

  if (event.keyCode == 13) {
  changerec();

    event.stopPropagation()
    event.preventDefault()
  }
}

function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }
      function funload()
      {
          window.setTimeout(function(){
         var IFRAMERef = window.top.document.getElementById('f3');
    var parheight= <%=perpage%>;
    var tc = <%=tcount%>;
    parheight = parheight>tc?tc:parheight;
    var heigh = parheight*10 + 700;
    //alert(heigh);
    <%--if(heigh>parheight) parheight=heigh;
    alert(parheight);--%>
    if(IFRAMERef!=undefined){
    if(heigh!=undefined)
        IFRAMERef.height = heigh;
    else
        IFRAMERef.height = 500;}
    parent.document.getElementById("ifr3").style.height = heigh;
    parent.document.getElementById("f3").style.height = heigh;
      },200);}
        </script>
    </head>
    <body onload="funload()">

        


<br><br> 

<%if(tcount==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("no_record_found")%></p>
<%}
else
{%>
<table align="<%=align%>" dir="<%=rtl%>" width="50%">
    <tr><td>Election Details</td><td align="right">View Next&nbsp;<input type="textbox" id="rec" onkeypress="return isNumberKey(event)" onblur="changerec()" style="width:50px"/></td></tr>
    <tr dir="<%=rtl%>"><td dir="<%=rtl%>" colspan="2">
            <table width="100%"  align="center"><tr><td colspan="2" align="center">
            <ui:dataGrid items="${requestList}"  var="doc" name="datagrid1"  cellPadding="0" cellSpacing="0" styleClass="datagrid">

  <columns>

    <column width="20%">
      <header value="Election_Id" hAlign="left" styleClass="header"/>
      <item   value="${doc.election_id}" hyperLink="${path}/Candidate/candidate_details.jsp?id=${doc.index}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="20%">
      <header value="Institute_Id" hAlign="left" styleClass="header"/>
      <item   value="${doc.institute_id}" hAlign="left" hyperLink="${path}/Candidate/candidate_details.jsp?id=${doc.index}"  styleClass="item"/>
    </column>
    <column width="20%">
      <header value="Election Name" hAlign="left" styleClass="header"/>
      <item   value="${doc.election_name}" hAlign="left" hyperLink="${path}/Candidate/candidate_details.jsp?id=${doc.index}"  styleClass="item"/>
    </column>

    <column width="20%">
      <header value="Position Name" hAlign="left" styleClass="header"/>
      <item   value="${doc.positionName}" hyperLink="${path}/Candidate/candidate_details.jsp?id=${doc.index}"  hAlign="left" styleClass="item"/>
    </column>

      <column width="20%">
      <header value="Candidate Name" hAlign="left" styleClass="header"/>
      <item   value="${doc.candidateName}" hyperLink="${path}/Candidate/candidate_details.jsp?id=${doc.index}"  hAlign="left" styleClass="item"/>
    </column>

       


      

 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="${rec}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
</td></tr>
<tr>
<td align="center" colspan="2">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>"><%=resource.getString("previous")%></a>
</c:if>&nbsp;
<c:if test="${next != null}">
    <a href="<c:out value="${next}"/>"><%=resource.getString("next")%></a>
</c:if>


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

 </td></tr>
</table>
 <%}%>
    </body>

</html>
