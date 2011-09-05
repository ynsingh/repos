<%-- 
    Document   : block_managergrid
    Created on : Apr 11, 2011, 4:52:30 PM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*,com.myapp.struts.hbm.ElectionManager"%>

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
   Election_Manager_StaffDetail ems;
  // AdminRegistration adminReg;
   ElectionManager electionmanager;
   StaffDetail staffdetail;

   ArrayList requestList;
   int fromIndex=0, toIndex;

%>

        <%
String Manager_ID=resource.getString("managerid");
pageContext.setAttribute("Manager_ID",Manager_ID );
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

 List<Election_Manager_StaffDetail> rs = (List<Election_Manager_StaffDetail>)session.getAttribute("resultset");


   requestList = new ArrayList();
//requestList = (ArrayList)session.getAttribute("resultset");
   int tcount =0;
   int perpage=4;
   int tpage=0;

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
        electionmanager = (ElectionManager)rs.get(tcount).getElectionManager();
        staffdetail = (StaffDetail)rs.get(tcount).getStaffDetail();

        Ob = new StaffDoc ();
        ems=new Election_Manager_StaffDetail();

        Ob.setmanager_id(electionmanager.getId().getManagerId());
        Ob.setinstitute_id(electionmanager.getId().getInstituteId());
        Ob.setfirst_name(staffdetail.getFirstName());
        Ob.setlast_name(staffdetail.getLastName());
        Ob.setStaff_id(electionmanager.getStaffId());
        Ob.setUser_id(electionmanager.getUserId());
        Ob.setStatus(electionmanager.getStatus());
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
    loc = "http://<%=request.getHeader("host")%><%=request.getContextPath()%>/institute_admin/block_managergrid.jsp";


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
         var IFRAMERef = window.top.document.getElementById('pagetab');
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
<table align="<%=align%>" dir="<%=rtl%>" width="90%">
    <tr><td colspan="2" align="right">View Next&nbsp;<input type="textbox" id="rec" onkeypress="return isNumberKey(event)" onblur="changerec()" style="width:50px"/></td></tr>
    <tr dir="<%=rtl%>"><td dir="<%=rtl%>">
            <ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" style="margin-left: 30px" cellPadding="0" cellSpacing="0" styleClass="datagrid">

  <columns>

    <column width="10%">
      <header value="${Manager_ID}" hAlign="left" styleClass="header"/>
      <item   value="${doc.manager_id}" hyperLink="${path}/status_detail.do?id=${doc.manager_id}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${First_Name}" hAlign="left" styleClass="header"/>
      <item   value="${doc.first_name}" hAlign="left" hyperLink="${path}/status_detail.do?id=${doc.manager_id}"  styleClass="item"/>
    </column>
    <column width="10%">
      <header value="${Last_Name}" hAlign="left" styleClass="header"/>
      <item   value="${doc.last_name}" hAlign="left" hyperLink="${path}/status_detail.do?id=${doc.manager_id}"  styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${Staff_id}" hAlign="left" styleClass="header"/>
      <item   value="${doc.staff_id}" hyperLink="${path}/status_detail.do?id=${doc.manager_id}"  hAlign="left" styleClass="item"/>
    </column>


       <column width="10%">
      <header value="${Working_status}" hAlign="left" styleClass="header"/>
      <item   value="${doc.status}" hyperLink="${path}/status_detail.do?id=${doc.manager_id}"  hAlign="left" styleClass="item"/>
    </column>


      <column width="10%">
      <header value="${Action}" hAlign="left" styleClass="header"/>
      <item   value="${Change_Status}" hyperLink="${path}/status_detail.do?id=${doc.manager_id}"  hAlign="left" styleClass="item"/>
    </column>



 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="${rec}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

  <table width="700" style="font-family: arial; font-size: 10pt;margin-left: 30px" border=0>
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
<%
String msg1=(String)request.getAttribute("msg");
if(msg1==null){msg1=(String)request.getAttribute("msg");}
if(msg1!=null)
    {
    %>
    <script>
        alert("<%=msg1%>"+  " <%=resource.getString("mail_sent_successfully")%>");
    </script>
<%}%>