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



<%!


   RequestDoc Ob;
   AdminRegistration adminReg;
   ArrayList requestList;
   int fromIndex=0, toIndex;
%>
 <%
 int perpage=4;
 List rs = (List)(session.getAttribute("resultset2"));

  if(request.getParameter("pageSize")!=null && request.getParameter("pageSize")!="")
    perpage = Integer.parseInt((String)request.getParameter("pageSize"));


   requestList = new ArrayList();
   int tcount =0;

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
           Ob.setStatus(adminReg.getStatus());
            Ob.setAddress(adminReg.getInstituteAddress());
        Ob.setUser_name(adminReg.getAdminFname()+" "+adminReg.getAdminLname());
        adminReg = null;
        requestList.add(Ob);
        tcount++;
        it.next();

	}

System.out.println("tcount="+tcount);

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

<%
  String Registration_ID=resource.getString("admin.viewpending.registrationid");
  pageContext.setAttribute("Registration_ID", Registration_ID);
  String InstituteName=resource.getString("admin.viewpending.institutename");
  pageContext.setAttribute("InstituteName", InstituteName);
  String UserId=resource.getString("admin.viewpending.userid");
  pageContext.setAttribute("UserId", UserId);
  String Admin_Email=resource.getString("admin.viewpending.adminemail");
  pageContext.setAttribute("Admin_Email",Admin_Email);
  String Status=resource.getString("admin.viewall.status");
  pageContext.setAttribute("Status",Status);

%>
<%
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+perpage) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
    pageContext.setAttribute("rec",perpage);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>View Request</title>


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>
<script>
    function changerec(){
        var x=document.getElementById('rec').value;
    var loc = window.location;
    loc = "http://<%=request.getHeader("host")%><%=request.getContextPath()%>/admin/view_pending.jsp";


        loc = loc + "?pageSize="+x;
         // alert(loc);
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
    </script>
<body>

<table border="1" style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;border-collapse: collapse;  border-spacing: 0;" align="center"  width="80%" >
        <tr><td class="headerStyle" align="center">View All  Institute
            </td></tr>
        <tr><td align="center">
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Pending Request Found</p>
<%}
else
{%>

 <p align="right" class="txtStyle">View Next<input type="textbox" id="rec" onkeypress="return isNumberKey(event)" onblur="changerec()" style="width:50px"/></p>

<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">

  <columns>

    <column width="100">
      <header value="${Registration_ID}" hAlign="left" styleClass="header"/>
      <item   value="${doc.registration_id}" hyperLink="index3.jsp?id=${doc.registration_id}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="250">
      <header value="${InstituteName}" hAlign="left" styleClass="header"/>
      <item   value="${doc.institute_name}" hAlign="left" hyperLink="index3.jsp?id=${doc.registration_id}"  styleClass="item"/>
    </column>
    <column width="100">
      <header value="${UserId}" hAlign="left" styleClass="header"/>
      <item   value="${doc.userId}" hAlign="left" hyperLink="index3.jsp?id=${doc.registration_id}"  styleClass="item"/>
    </column>

   <column width="200">
      <header value="Institute Address" hAlign="left" styleClass="header"/>
      <item   value="${doc.address}" hyperLink="index3.jsp?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
<column width="200">
      <header value="Institute AdminName" hAlign="left" styleClass="header"/>
      <item   value="${doc.user_name}" hyperLink="index3.jsp?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>

      <column width="150">
      <header value="${Admin_Email}" hAlign="left" styleClass="header"/>
      <item   value="${doc.admin_email}" hyperLink="index3.jsp?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
       <column width="100">
      <header value="${Status}" hAlign="left" styleClass="header"/>
      <item   value="${doc.status}" hyperLink="index3.jsp?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="${rec}" count="${tCount}" custom="true" nextUrlVar="next"
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
     Export :<img src="<%=request.getContextPath()%>/images/excel.jpeg" border="1" height="25" width="25">
   <img src="<%=request.getContextPath()%>/images/xml.jpeg" height="45" border="1" width="45">
    <img src="<%=request.getContextPath()%>/images/pdf.jpeg" height="25"border="1" width="25">
</td>

</tr>
  </table>
<%}}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
}%>
  </td></tr></table>
    </body>

</html>


