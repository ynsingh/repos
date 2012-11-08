<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.sql.*,java.util.List"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

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

</head>
<link rel="stylesheet" type="text/css" href="chrometheme/chromestyle.css" />

<script type="text/javascript" src="chromejs/chrome.js"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/page.css">
<tiles:insert definition="superadmin-template" >
	<tiles:put name="body" value="/admin/admin_form.jsp" />
</tiles:insert>
   </html>
   <%}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?user=<%=user_id%>";</script><%
}%>
       

<%--
<script type="text/javascript">

cssdropdown.startchrome("chromemenu")

</script>
  --%>