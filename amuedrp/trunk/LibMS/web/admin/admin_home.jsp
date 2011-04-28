<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.sql.*,java.util.List"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%
String host = (String)request.getHeader("host");

if(session.isNew()){System.out.println("session new");}

String user_id = (String)session.getAttribute("staff_id");
 

if (session.getAttribute("staff_id")!=null)
    {
List rst =(List)session.getAttribute("resultset");
int count=(Integer)session.getAttribute("count");
 int i=1;

String contextPath = request.getContextPath();

%>
<%@page import="java.util.*,java.io.*,java.net.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
<html>
<head>

  

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>


<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>


<script>
    
    var loc2 = "http://<%=host%>"+"/LibMS-Struts/superadmin.do";
    if(window.location=="http://<%=host%>/LibMS-Struts/login.do")
        window.location=loc2;
  
</script>

<%
if(session.getAttribute("username")==null)
   { %><script>parent.location="<%=request.getContextPath()%>/login.jsp"</script>
<%}%>
 <jsp:include page="adminheader.jsp" flush="true" />
</head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/page.css">
<div
   style="  top:60px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    
    <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" dir="<%=rtl%>">
 
<table border=0 cellpadding=0 cellspacing=0 width="100%" dir="<%=rtl%>">
    <tr><td height="25px" width="600px" bgcolor="#7697BC" dir="<%=rtl%>">
  <a href="<%=contextPath%>/admin/admin_home.jsp"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"> &nbsp;&nbsp;<%=resource.getString("login.home")%>&nbsp;|&nbsp;</b></a>
 <a href="<%=contextPath%>/admin/view_pending.jsp" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>" >
      <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.viewpendinglist")%>&nbsp;|&nbsp;</b></a>
 <a href="<%=contextPath%>/admin/view_approved.jsp" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>" >
      <b style="color:white" dir="<%=rtl%>"> <%=resource.getString("login.viewapprovedlist")%>&nbsp;|&nbsp;</b></a>
<a href="<%=contextPath%>/admin/view_all.jsp" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>" >
      <b style="color:white" dir="<%=rtl%>"> <%=resource.getString("login.viewall")%> &nbsp;|&nbsp;</b></a>
          <a href="<%=contextPath%>/admin/update_admin.jsp" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"> <%=resource.getString("login.modifyinstituterecord")%>&nbsp;|&nbsp;</b></a>
<a href="<%=contextPath%>/admin/search_admin.jsp"  target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.searchinstitute")%>  &nbsp;|&nbsp;</b></a>
<a href="<%=contextPath%>/manage_superadmin.jsp"  target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.managesuperadminaccount")%>&nbsp;|</b>&nbsp;</a>
<a href="<%=contextPath%>/admin/block_admin.jsp"  target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.delinquentinstitutelist")%> |</b>&nbsp;</a>
<a href="<%=contextPath%>/admin/view_blocked_institute.jsp"  target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>">
    <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.changeworkingstatus")%></b></a>
 <%-- <a href="<%=contextPath%>/admin/view_rejected_institute.jsp"  target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>">
    <b style="color:white" dir="<%=rtl%>">Reject Library&nbsp;</b></a>--%>
  



      </td>
  </tr>
  <tr><td dir="<%=rtl%>" class="mess">
            <b><br>
<%=resource.getString("login.pendingrequestforinstitueregistration")%> (<%=count%>)<a href="<%=contextPath%>/admin/view_pending.jsp" target="f3" dir="<%=rtl%>"> <u><%=resource.getString("viewpending")%></u></a>

    </b>


      </td></tr>
  <tr dir="<%=rtl%>"><td align="left" style=" padding-left: 200px;" dir="<%=rtl%>">
            <IFRAME  name="f3" src="#" frameborder=0 scrolling="no" width="100%" style="color:deepskyblue;height:650px;left:5px;visibility:true;" id="f3" dir="<%=rtl%>"></IFRAME>


      </td></tr>
  
 
       
      
</table>

    </div>
        </body>
   </html>
   <%}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?user=<%=user_id%>";</script><%
}%>
       



  