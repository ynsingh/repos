

    <%@page import="com.myapp.struts.admin.RequestDoc"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.*,com.myapp.struts.hbm.*"%>
       <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>View Request</title>


<link rel="stylesheet" href="/EMS/css/page.css"/>
<script language="javascript" >
function b1click()
{
location.href="login.jsp";
}
function b2click()
{
f.action="login.jsp";
f.method="post";
f.target="_self";
f.submit();
}
function getQuery(id)
{
    var query = "/EMS/admin/index1.jsp?id="+id;
    return query;
}

    function changerec(){
       // var x=document.getElementById('rec').value;


   var combo1 = document.getElementById('rec');
   var x = combo1.options[combo1.selectedIndex].value;
   alert(x);

if(x=="0")
{
    x="0";

}


   //     alert(x);
        //if(x=="")
          //  x='0';
       
    var loc = window.location;
    loc = "http://<%=request.getHeader("host")%><%=request.getContextPath()%>/admin/view_pending.jsp";

   
            loc = loc + "?pageSize="+x;
    window.location = loc;
    <%
   System.out.println((String)request.getParameter("pageSize"));
%>

    }
function send(){
   // alert("ok");
     <%
     int pageNumber=1;
     if(request.getParameter("page") != null) {
       session.setAttribute("page", request.getParameter("page"));
       pageNumber = Integer.parseInt(request.getParameter("page"));
     } else {
       session.setAttribute("page", "1");
     }
     String nextPage = (pageNumber +1) + "";%>
var loc="/EMS/votersetup.do?page="+<%=nextPage%>;
//alert(loc);
location.href= loc;

<%


   //  System.out.println(((java.util.List)session.getAttribute("EmpList")).size());
     String myUrl = "/EMS/votersetup.do?page="+nextPage;
   //  System.out.println(myUrl);

     pageContext.setAttribute("myUrl", myUrl);
     %>

}

function sendprevious(){
  //  alert("ok");
     <%String previousPage ="";
if(pageNumber>=1)
   previousPage = (pageNumber -1) + "";
else
  previousPage = 0 + "";
%>
var loc="/EMS/votersetup.do?page="+<%=previousPage%>;
//alert(loc);
location.href= loc;

<%


   //  System.out.println(((java.util.List)session.getAttribute("EmpList")).size());
   //  String myUrl = "/EMS/votersetup.do?page="+nextPage;
   //  System.out.println(myUrl);

     pageContext.setAttribute("myUrl", myUrl);
     %>

}




</script>
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

<body style="width: 720px" onload="show();">
 <div
   style="  top:0px;
   left:5px;
   right:5px;
      position: absolute;
      width: 710px;
      visibility: show;">
     Pending Institute List
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
   int perpage=100;
   int tpage=0;
 /*Create a connection by using getConnection() method
   that takes parameters of string type connection url,
   user name and password to connect to database.*/
   if(request.getParameter("pageSize")!=null)
    perpage = Integer.parseInt((String)request.getParameter("pageSize"));
System.out.println("perpage="+perpage);
 //  if(perpage==0)
   //    perpage=4;
if(rs!=null){
   Iterator it = rs.iterator();

//requestList = (Login)rs.get(0);

   while (it.hasNext()) {
	
	System.out.println("it="+(tcount));
        adminReg = (AdminRegistration)rs.get(tcount);
        Ob = new RequestDoc ();
	Ob.setRegistration_id(adminReg.getRegistrationId());
	Ob.setInstitute_name(adminReg.getInstituteName());
	Ob.setUserId(adminReg.getUserId());
	Ob.setAdmin_email(adminReg.getAdminEmail());
        adminReg = null;
   requestList.add(Ob);
   tcount++;
it.next();
   //System.out.println("tcount="+tcount);
		     }

System.out.println("tcount="+tcount);

%>
<script>
     function show(){
     document.getElementById('rec').value="<%=perpage%>";

 }
</script>
<%
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+perpage) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>

<%
String Registration_ID=resource.getString("registrationid");
pageContext.setAttribute("Registration_ID", Registration_ID);
String Institute_Name=resource.getString("institutename");
pageContext.setAttribute("Institute_Name", Institute_Name);
String Admin_Email=resource.getString("login.ems.adminemail");
pageContext.setAttribute("Admin_Email", Admin_Email);
String User_Id=resource.getString("userid");
pageContext.setAttribute("User_Id", User_Id);
String Action=resource.getString("login.ems.action");
pageContext.setAttribute("Action", Action);
pageContext.setAttribute("rec",perpage);
%>

<br><br>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("no_record_found")%></p>
<%}
else
{%>

<table align="<%=align%>" dir="<%=rtl%>" width="700px" >
  <%--  <tr><td colspan="2" align="right">View Next&nbsp;
       <input type="textbox" id="rec"   onkeypress="return isNumberKey(event)" onblur="changerec()" style="width:50px"/>
       <select id="rec" onchange="changerec()" style="width:50px" value="<%=perpage%>">
           <option value="0">Select</option>
           <option value="20">20</option>
            <option value="50">50</option>
             <option value="100">100</option>
       </select>

        </td></tr>--%>
    <tr dir="<%=rtl%>"><td dir="<%=rtl%>">

<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">
    
  <columns>
      
    <column width="10%">
      <header value="${Registration_ID}" hAlign="left" styleClass="header"/>
      <item   value="${doc.registration_id}" hyperLink="view1.do?id=${doc.registration_id}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="15%">
      <header value="${Institute_Name}" hAlign="left" styleClass="header"/>
      <item   value="${doc.institute_name}" hAlign="left" hyperLink="view1.do?id=${doc.registration_id}"  styleClass="item"/>
    </column>
    <column width="10%">
      <header value="${User_Id}" hAlign="left" styleClass="header"/>
      <item   value="${doc.userId}" hAlign="left" hyperLink="view1.do?id=${doc.registration_id}"  styleClass="item"/>
    </column>
       
    <column width="10%">
      <header value="${Admin_Email}" hAlign="left" styleClass="header"/>
      <item   value="${doc.admin_email}" hyperLink="view1.do?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
       <column width="5%">
      <header value="${Action}" hAlign="left" styleClass="header"/>
      <item   value="Accept" hyperLink="view1.do?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="${rec}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
<table width="700px" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="100px">
<c:if test="${previous} != null">
<a href="<c:out value="${previous}"/>"><%=resource.getString("previous")%></a>
</c:if>&nbsp;
<c:if test="${next} != null">
<a href="<c:out value="${next}"/>"><%=resource.getString("next")%></a>
</c:if>

</td><td width="50%" align="left">

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
 <input type="button" onclick="send()" value="nextPage"/>
  <input type="button" onclick="sendprevious()" value="previous"/>

<%}}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
}%>

</td></tr>
</table>
 </div>
    </body>

</html>


