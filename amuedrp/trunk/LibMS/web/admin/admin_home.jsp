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





<script>
    
    var loc2 = "http://<%=host%>"+"/LibMS/superadmin.do";
    if(window.location=="http://<%=host%>/LibMS/login.do")
        window.location=loc2;
  function quit()
{
    parent.location="<%=request.getContextPath()%>"+"/superadmin.do";
}
  
</script>

<%
if(session.getAttribute("username")==null)
   { %><script>parent.location="<%=request.getContextPath()%>/login.jsp"</script>
<%}%>
 <jsp:include page="adminheader.jsp" flush="true" />

</head>
<link rel="stylesheet" type="text/css" href="chrometheme/chromestyle.css" />

<script type="text/javascript" src="chromejs/chrome.js"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/page.css">

<div
   style="top:60px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    
    <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" dir="<%=rtl%>">
 



<table border=0 cellpadding=0 cellspacing=0 width="100%" dir="<%=rtl%>">
    <tr><td height="25px" width="600px" bgcolor="#7697BC" dir="<%=rtl%>">
            <a href="#" onclick="quit();"    dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"> &nbsp;&nbsp;<%=resource.getString("login.home")%>&nbsp;|&nbsp;</b></a></td><td>
 <a href="<%=contextPath%>/admin/view_pending.jsp" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>" >
      <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.viewpendinglist")%>
          <script>document.write('<span ');
document.write('height:10px;border:0px solid black;font:bold 10pt Verdana;');
document.write(' onClick="toggle_menu(1);');
document.write('event.cancelBubble=1" ><span style="cursor:hand;">');
document.write(' <img width=10 height=10 src="<%=request.getContextPath()%>/images/down.gif"></span>')
document.write('<div id="ddmenu" style="');
document.write('height:45px;width:150px;border:0px solid black;background-color:white;text-align: left;padding-left:50px');
document.write('overflow-y:scroll;visibility:hidden;">')
  additem("Pending List","<%=request.getContextPath()%>/admin/staffupdate.do?id=admin");
additem("Approved List","<%=request.getContextPath()%>/admin/changeuserpassword.do");
additem("Rejected List","<%=request.getContextPath()%>/admin/changeuserpassword.do");
additem("View All","<%=request.getContextPath()%>/admin/changeuserpassword.do");



document.onclick= function() {toggle_menu(0); }
document.write('</div></span>');


                        </script></b></a></td>
  </tr>
  <tr><td dir="<%=rtl%>" class="headerStyle">
            
<%=resource.getString("login.pendingrequestforinstitueregistration")%> (<%=count%>)<a href="<%=contextPath%>/admin/view_pending.jsp" target="f3" dir="<%=rtl%>"> <u><%=resource.getString("viewpending")%></u></a>




      </td></tr>
  <tr dir="<%=rtl%>"><td align="left" style="padding-left: 200px;" dir="<%=rtl%>">
            <IFRAME  name="f3" src="#" frameborder=0 scrolling="no" width="100%" style="color:deepskyblue;height:650px;padding-left:100px;visibility:true;" id="f3" dir="<%=rtl%>"></IFRAME>


      </td></tr>




</table>

<div class="chromestyle" id="chromemenu"  style="top:70px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
<ul class="headerStyle">
<li><a href="<%=contextPath%>/superadmin.do"><%=resource.getString("login.home")%></a></li>
<li><a href="#" rel="dropmenu1">Library List</a></li>
<li><a href="<%=contextPath%>/admin/update_admin.jsp" target="f3">Modify Record</a></li>
<li><a href="<%=contextPath%>/admin/search_admin.jsp"  target="f3"><%=resource.getString("login.searchinstitute")%></a></li>
<li><a href="<%=contextPath%>/manage_superadmin.jsp"  target="f3">Change Password</a></li>
<li><a href="<%=contextPath%>/admin/block_admin.jsp"  target="f3"><%=resource.getString("login.delinquentinstitutelist")%></a></li>
<li><a href="<%=contextPath%>/admin/view_blocked_institute.jsp"  target="f3"><%=resource.getString("login.changeworkingstatus")%></a></li>
</ul>
</div>

<!--1st drop down menu -->
<div id="dropmenu1" class="dropmenudiv"  style="top:70px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;background-color: " >
<a  href="<%=contextPath%>/admin/view_pending.jsp" target="f3">Pending List</a>
<a href="<%=contextPath%>/admin/view_approved.jsp" target="f3">Approved List</a>
<a href="<%=contextPath%>/admin/rejectlibrary.jsp"  target="f3">Rejected List</a>
<a href="<%=contextPath%>/admin/view_all.jsp" target="f3" >View All</a>

</div>
 
    </div>



        </body>
   </html>
   <%}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?user=<%=user_id%>";</script><%
}%>
       


<script type="text/javascript">

cssdropdown.startchrome("chromemenu")

</script>
  