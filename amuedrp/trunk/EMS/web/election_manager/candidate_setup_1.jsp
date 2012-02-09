<%-- 
    Document   : candidate_setup
    Created on : Jun 27, 2011, 4:29:08 PM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*,com.myapp.struts.hbm.VoterRegistration,com.myapp.struts.hbm.CandidateRegistration"%>

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
        <title>Candidate Setup</title>
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
    <body onload="funload()">

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
String Candidate_Name=resource.getString("candidatename");
pageContext.setAttribute("Candidate_Name", Candidate_Name);
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
   CandidateRegistration candiadte;
   VoterCandidate voter1;


   ArrayList requestList;
   int fromIndex=0, toIndex;

%>



 <%

 
 List<VoterCandidate> rs = (List<VoterCandidate>)session.getAttribute("resultset1");


   requestList = new ArrayList();

   int tcount =0;
   int perpage=10;
   int tpage=0;
   tcount=0;


   if(request.getParameter("pageSize")!=null && request.getParameter("pageSize")!="")
    perpage = Integer.parseInt((String)request.getParameter("pageSize"));
 
if(rs!=null){
  Iterator it = rs.iterator();
System.out.println("it="+(tcount));

String status = "&status="+request.getParameter("status");
   while (it.hasNext()) {

	System.out.println("it="+(tcount));
        voter1 = (VoterCandidate)rs.get(tcount);
      
       candiadte = (CandidateRegistration)voter1.getCandidateRegistration();
        Ob = new StaffDoc ();
       
       System.out.println(candiadte.getId().getPosition());
        Ob.setPosition_id(candiadte.getId().getPosition());
        Ob.setEnrollment(voter1.getVoterRegistration().getId().getEnrollment());
        Ob.setVoter_name(voter1.getVoterRegistration().getVoterName());
        Ob.setDepartment(voter1.getVoterRegistration().getDepartment());
        Ob.setCourse(voter1.getVoterRegistration().getCourse());
        Ob.setYear(voter1.getVoterRegistration().getYear());
        Ob.setStatus(voter1.getCandidateRegistration().getStatus());
      









   requestList.add(Ob);
   tcount++;
it.next();
 
		     }

System.out.println("tcount="+tcount);

   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+perpage) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   pageContext.setAttribute("rec",perpage);
   pageContext.setAttribute("stat",status);
   pageContext.setAttribute("amp","&");
   }

String path=request.getContextPath();
pageContext.setAttribute("path", path);
  %>

<script type="text/javascript" language="javascript">
        function changerec(){
        var x=document.getElementById('rec').value;
    var loc = window.location;
    loc = "http://<%=request.getHeader("host")%><%=request.getContextPath()%>/election_manager/candidate_setup_1.jsp";


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
         var IFRAMERef = document.getElementById('grid');
    var parheight= <%=perpage%>;
    var tc = <%=tcount%>;
    parheight = parheight>tc?tc:parheight;
    var heigh = parheight*10 + 300;
    //alert(heigh);
    <%--if(heigh>parheight) parheight=heigh;
    alert(parheight);--%>
    if(heigh!=undefined)
        IFRAMERef.height = heigh;
    else
        IFRAMERef.height = 500;
    parent.document.getElementById("form1").style.height = heigh;
      },200);}
    </script>
<br><br>

<%if(tcount==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("no_record_found")%></p>
<%}
else
{%>
<table id="grid" dir="" width="100%" style="padding-left: 5%;padding-right: 5%">
   <tr><td>Candidate Approval Page</td><td align="right">View Next&nbsp;
           <%--<input type="textbox" id="rec" onkeypress="return isNumberKey(event)" onblur="changerec()" style="width:50px"/>--%>
       <select id="rec" onchange="changerec()" style="width:50px">
           <option value="10">10</option>
            <option value="20">20</option>
             <option value="30">30</option>
       </select>
       </td></tr>
    <tr dir=""><td dir="">
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">

  <columns>

    <column width="10%">
      <header value="${Enrollment_No}" hAlign="left" styleClass="header"/>
      <item   value="${doc.enrollment}" hyperLink="${path}/candidate1.do?id=${doc.enrollment}${amp}pos=${doc.position_id}${stat}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${Candidate_Name}" hAlign="left" styleClass="header"/>
      <item   value="${doc.voter_name}" hAlign="left" hyperLink="${path}/candidate1.do?id=${doc.enrollment}${amp}pos=${doc.position_id}${stat}"  styleClass="item"/>
    </column>
    <column width="10%">
      <header value="${Department}" hAlign="left" styleClass="header"/>
      <item   value="${doc.department}" hAlign="left" hyperLink="${path}/candidate1.do?id=${doc.enrollment}${amp}pos=${doc.position_id}${stat}"  styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${Course}" hAlign="left" styleClass="header"/>
      <item   value="${doc.course}" hyperLink="${path}/candidate1.do?id=${doc.enrollment}${amp}pos=${doc.position_id}${stat}"  hAlign="left" styleClass="item"/>
    </column>


       <column width="10%">
      <header value="${Year}" hAlign="left" styleClass="header"/>
      <item   value="${doc.year}" hyperLink="${path}/candidate1.do?id=${doc.enrollment}${amp}pos=${doc.position_id}${stat}"  hAlign="left" styleClass="item"/>
    </column>


      <column width="10%">
      <header value="${Status}" hAlign="left" styleClass="header"/>
      <item   value="${doc.status}" hyperLink="${path}/candidate1.do?id=${doc.enrollment}${amp}pos=${doc.position_id}${stat}"  hAlign="left" styleClass="item"/>
    </column>



 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="${rec}" count="${tCount}" custom="true" nextUrlVar="next"
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

  <%}%>


<%
String msg=(String)session.getAttribute("msg");

   if(msg!=null) out.println(msg);
    session.removeAttribute("msg");

%>
   
  </td></tr>
</table>
    </body>

</html>