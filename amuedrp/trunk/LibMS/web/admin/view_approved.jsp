 
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
<%!
   
   
   RequestDoc Ob;
   ArrayList requestList;
   AdminRegistration adminReg;
   int fromIndex=0, toIndex;

%>
 <%

 List rs = (List)(session.getAttribute("resultset1"));
if(rs!=null){
 Iterator it = rs.iterator();

   requestList = new ArrayList ();
   int tcount =0;
   int perpage=4;
   int tpage=0;
 
 if(request.getParameter("pageSize")!=null && request.getParameter("pageSize")!="")
    perpage = Integer.parseInt((String)request.getParameter("pageSize"));


  while (it.hasNext()) {
	
	Ob = new RequestDoc();
        adminReg = (AdminRegistration)rs.get(tcount);
	Ob.setRegistration_id(adminReg.getRegistrationId());
	Ob.setInstitute_name(adminReg.getInstituteName());
	
	Ob.setAdmin_email(adminReg.getAdminEmail());
       // Ob.setInstitute_id(adminReg.)
        adminReg=null;
   requestList.add(Ob);
   tcount++;
it.next();
   //System.out.println("tcount="+tcount);
		     }

System.out.println("tcount="+tcount);

%>
<script>
    function changerec(){
        var x=document.getElementById('rec').value;
    var loc = window.location;
    loc = "http://<%=request.getHeader("host")%><%=request.getContextPath()%>/admin/view_approved.jsp";
    
   // alert(loc);
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
    </script>
       
<%
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex + perpage) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
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
  pageContext.setAttribute("AdminEmail", AdminEmail);
pageContext.setAttribute("rec",perpage);


%>
View Next<input type="textbox" id="rec" onkeypress="return isNumberKey(event)" onblur="changerec()" style="width:50px"/><br/>
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">
    
  <columns>
      
   
    <column width="100">
      <header value="${RegistrationID}" hAlign="left" styleClass="header"/>
      <item   value="${doc.registration_id}" hyperLink="index.jsp?id=${doc.registration_id}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="250">
      <header value="${InstituteName}" hAlign="left" styleClass="header"/>
      <item   value="${doc.institute_name}" hAlign="left" hyperLink="index.jsp?id=${doc.registration_id}"  styleClass="item"/>
    </column>

       
    <column width="200">
      <header value="${AdminEmail}" hAlign="left" styleClass="header"/>
      <item   value="${doc.admin_email}" hyperLink="index.jsp?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>

    

 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="${rec}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
 
</ui:dataGrid>
  <table width="581" style="font-family: arial; font-size: 10pt" >
<tr>
<td align="left" width="100px">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>">Next</a>
</c:if>

</td><td width="250px" align="center" id="reccount" class="txtStyle">

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


