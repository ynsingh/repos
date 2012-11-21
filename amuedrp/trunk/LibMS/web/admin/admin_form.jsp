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
<style>
    a{
        color:white;
}
</style>
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
 <%--<jsp:include page="superadminheader.jsp" flush="true" />--%>

</head>
<link rel="stylesheet" type="text/css" href="chrometheme/chromestyle.css" />

<script type="text/javascript" src="chromejs/chrome.js"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/page.css"/>




<body style="margin: 0px 0px 0px 0px;" bgcolor="blue" dir="<%=rtl%>">

    
          <script>

                        </script>


    <table style="margin: 0px 0px 0px 0px;" width="100%" class="homepage">
        <tr ><td>    <img src="<%=request.getContextPath()%>/images/logo.png" style="height:70px;width:140px;" alt=""  border="0" align="top" id="Image1" >&nbsp;&nbsp;</td><td width="60%" style="font: arial;font-size: 20px;" valign="bottom">WebAdmin Control Panel</td><td  align="center"><a href="<%=contextPath%>/superadmin.do"><img src="<%=request.getContextPath()%>/images/home.png" style="height:60px;width:60px;" alt=""  border="0" align="top" id="Image1" ><br>Home&nbsp;&nbsp;</a></td><td align="center"><a href="<%=contextPath%>/manage_superadmin.jsp"  target="f3"><img src="<%=request.getContextPath()%>/images/setting.jpg" style="height:60px;width:60px;" alt=""  border="0" align="top" id="Image1" ><br>Setting&nbsp;&nbsp;</a></td><td align="center"><a href="<%=contextPath%>/admin/search_admin.jsp"  target="f3"><img src="<%=request.getContextPath()%>/images/search.jpg" style="height:60px;width:60px;" alt=""  border="0" align="top" id="Image1" ><br>Search Institute&nbsp;&nbsp;</a></td><td align="center"><a href="<%=contextPath%>/admin/update_admin.jsp" target="f3"><img src="<%=request.getContextPath()%>/images/update.jpg" style="height:60px;width:60px;" alt=""  border="0" align="top" id="Image1" ><br>Update Record&nbsp;&nbsp;</a></td></tr>
        <tr><td colspan="6"><hr></td></tr>
        <tr ><td valign="top"  width="15%">

                <table valign="top" style="margin: 0px 0px 0px 0px;padding: 0px">
                    <tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;<a href="<%=contextPath%>/printlog.do"  target="_blank">User Log</a></td></tr>
                   
                    <tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;<a href="<%=contextPath%>/admin/update_admin.jsp" target="f3">Modify Record</a></td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;<a href="<%=contextPath%>/admin/search_admin.jsp"  target="f3"><%=resource.getString("login.searchinstitute")%></a></td></tr>

<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;<a href="<%=contextPath%>/admin/block_admin.jsp"  target="f3"><%=resource.getString("login.delinquentinstitutelist")%></a></td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;<a href="<%=contextPath%>/admin/view_blocked_institute.jsp"  target="f3"><%=resource.getString("login.changeworkingstatus")%></a></td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;<a  href="<%=contextPath%>/admin/view_pending.jsp" target="f3">Pending List</a></td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;<a href="<%=contextPath%>/admin/view_approved.jsp" target="f3">Approved List</a></td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;<a href="<%=contextPath%>/admin/rejectlibrary.jsp"  target="f3">Rejected List</a></td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;<a href="<%=contextPath%>/admin/view_all.jsp" target="f3" >View All</a></td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;Delete Institute Request</td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;Clear Server Log</td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;Change User Login</td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;<a href="#" >Update Release Notes</a></td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;<a href="<%=contextPath%>/mailsetting1.do"  target="f3">Mail Setting</a></td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;<a href="<%=contextPath%>/manage_superadmin.jsp"  target="f3">Change Superadmin Password</a></td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;<a href="<%=contextPath%>/admin/change_pass.jsp"  target="f3">Change Login Password</a></td></tr>
<tr><td><img src="<%=contextPath%>/images/bullet.jpg">&nbsp;Upload UserManual</td></tr>

</table>





            </td><td colspan="6">
<p class="txtStyle">    <%=resource.getString("login.pendingrequestforinstitueregistration")%> (<%=count%>)<a href="<%=contextPath%>/admin/view_pending.jsp" target="f3" dir="<%=rtl%>"> <u><%=resource.getString("viewpending")%></u></a></p>
            <IFRAME  name="f3" src="<%=contextPath%>/admin/view_pending.jsp" frameborder=0 scrolling="yes" width="100%" style="color:deepskyblue;height:400px;visibility:true;"  id="f3" dir="<%=rtl%>"></IFRAME>


</td></tr>
    </table>





 
  



        </body><%-- </div>--%>
   </html>
   <%}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?user=<%=user_id%>";</script><%
}%>
       


<script type="text/javascript">

cssdropdown.startchrome("chromemenu")

</script>
  
