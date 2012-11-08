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
 List rs = (List)(session.getAttribute("resultset"));

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
        Ob.setAddress(adminReg.getInstituteAddress());
        Ob.setUser_name(adminReg.getAdminFname()+" "+adminReg.getAdminLname());
        adminReg = null;
        requestList.add(Ob);
        tcount++;
        it.next();

	}

System.out.println("tcount="+tcount);

%>

<%
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+perpage) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
    pageContext.setAttribute("rec",perpage);
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
  String Action=resource.getString("admin.viewpending.action");
  pageContext.setAttribute("Action",Action);

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
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
</head>

<body>
    <table  style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;border-collapse: collapse;  border-spacing: 0;" align="center"  width="80%" >
        <tr><td colspan="2" width="80%" style="font: arial;font-size: 15px;" valign="bottom">Pending Institute Request</td>
            <td align="right" style="font: arial;font-size: 15px;">View Next<input class="superadmingridheader" type="textbox" id="rec" onkeypress="return isNumberKey(event)" onblur="changerec()" style="width:50px"/></td>
        </tr>
        <tr><td align="center" colspan="3" style="border:solid 1px cyan">
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Pending Request Found</p>
<%}
else
{%>



<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">
    
  <columns >
      
    <column width="100">
      <header value="${Registration_ID}" hAlign="left"  styleClass="superadmingridheader"  />
      <item   value="${doc.registration_id}" hyperLink="./../view1.do?id=${doc.registration_id}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="250">
      <header value="${InstituteName}" hAlign="left" styleClass="superadmingridheader"/>
      <item   value="${doc.institute_name}" hAlign="left" hyperLink="./../view1.do?id=${doc.registration_id}"  styleClass="item"/>
    </column>
<column width="200">
      <header value="Institute Address" hAlign="left" styleClass="superadmingridheader"/>
      <item   value="${doc.address}" hyperLink="./../view1.do?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
<column width="200">
      <header value="Institute AdminName" hAlign="left" styleClass="superadmingridheader"/>
      <item   value="${doc.user_name}" hyperLink="./../view1.do?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
      <column width="100">
      <header value="${UserId}" hAlign="left" styleClass="superadmingridheader"/>
      <item   value="${doc.userId}" hAlign="left" hyperLink="./../view1.do?id=${doc.registration_id}"  styleClass="item"/>
    </column>
       
    <column width="150">
      <header value="${Admin_Email}" hAlign="left" styleClass="superadmingridheader"/>
      <item   value="${doc.admin_email}" hyperLink="./../view1.do?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
       <column width="50">
      <header value="${Action}" hAlign="left" styleClass="superadmingridheader"/>
      <item   value="Accept" hyperLink="./../view1.do?id=${doc.registration_id}"  hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="${rec}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
 
 
</ui:dataGrid>
</td></tr><tr class="superadmingridfooter"><td  align="left" colspan="2">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>"><img src="<%=request.getContextPath()%>/images/previous.jpg" height="20px" width="20px"/></a>
</c:if>
<c:if test="${previous == null}">
    <img src="<%=request.getContextPath()%>/images/previous.jpg" height="20px" width="20px"/>
</c:if>
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>"><img src="<%=request.getContextPath()%>/images/next.jpg" height="20px" width="20px"/></a>
</c:if>
<c:if test="${next == null}">
 <img src="<%=request.getContextPath()%>/images/next.jpg" height="20px" width="20px"/>
</c:if>
    
    </td><td align="right">
     Import :<img src="<%=request.getContextPath()%>/images/excel.jpeg" border="1" height="25" width="25">
    <img src="<%=request.getContextPath()%>/images/xml.jpeg" height="25" border="1" width="25">
    <img src="<%=request.getContextPath()%>/images/pdf.jpeg" height="25"border="1" width="25">

</td></tr>
<%}}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
}%>
    </table>

    </body>

</html>


