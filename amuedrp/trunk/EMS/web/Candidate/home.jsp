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


//pending
if (session.getAttribute("staff_id")!=null){
List rst =(List)session.getAttribute("resultset");
int count=(Integer)(session.getAttribute("count")==null?0:session.getAttribute("count"));
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

    

<link rel="stylesheet" href="<%=contextPath%>/css/page.css"/>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/chrometheme/chromestyle.css" />

<script type="text/javascript" src="<%=contextPath%>/chromejs/chrome.js"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
<script>var loc2 = "http://<%=host%>"+"<%=contextPath%>/login.do";
    if(window.location=="http://<%=host%><%=contextPath%>login.do")
        window.location=loc2;</script>
<script  type="text/javascript" language="javascript">
 
 function fnload(){window.setInterval('fn()', 100);}
function fn(){
        window.setInterval('winresize()', 100);

    }



 function winresize()
{
    //alert(document.width);
    var winwidth = document.width;
    var IFRAMERef = frames['f3'];
   // alert(IFRAMERef);
    var frmwidth = IFRAMERef.document.width;
    var windiff=200;
    var frmheight;
        if(IFRAMERef.document.getElementById("f1")!=undefined)
        frmheight= IFRAMERef.document.getElementById("f1").height;
        else
            if(IFRAMERef.document.getElementById("form3")!=undefined)
        frmheight= IFRAMERef.document.getElementById("form3").height;
        else
            frmheight = 800+"px";
    //alert("frmheight="+frmheight);
    if(winwidth!=undefined && frmwidth!=undefined)
        windiff= winwidth - frmwidth;
    document.getElementById("ifr3").style.paddingLeft = windiff*0.5+"px";
    document.getElementById("ifr3").style.height = frmheight;
}

function divload(current,submenu)
{
    
    
    document.getElementById(submenu).style.visibility="visible";

}
function divunload(current,submenu)
{

    document.getElementById(submenu).style.visibility="collapse";

}
</script>

<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<style type="text/css">
<!--
.style7 {font-size: 12px}
.style8 {color: #C65A12}
.style9 {color: #990000}
-->
    </style>



<%
if(session.getAttribute("username")==null)
   { %><script>parent.location="<%=request.getContextPath()%>"</script>
<%}%>
<%
if(request.getAttribute("msg")!=null)
   { %><script>alert("<%=request.getAttribute("msg")%>");</script>
<%}%>
 <jsp:include page="candidateheader.jsp" flush="true" />
</head>

<div
   style="  top:55px;
   left:5px;
   right:5px;
      position: absolute;
      height: 600px;
      visibility: show;">
    
    <body leftmargin="0" topmargin="0" marginwidth="0" onload="fn();" marginheight="0" dir="<%=rtl%>">
 
<ul class="dd-menu">
<li><a href="<%=contextPath%>/Candidate/home.jsp"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"> &nbsp;&nbsp;<%=resource.getString("login.home")%></b></a></li>
      <li><a href="<%=contextPath%>/nominationList.do" target="f3" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>"><b>Nomination&nbsp;List</b></a></li>
    <li><a href="<%=contextPath%>/withdrawal.do" target="_self" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>" >
      <b style="color:white" dir="<%=rtl%>">Send Withdrawal Request</b></a></li>
        <li><a href="<%=contextPath%>/finalnominationList.do" target="f3" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>" >
      <b style="color:white" dir="<%=rtl%>"> Final List</b></a></li>
      <li><a href="<%=contextPath%>/Candidate/upload_menifesto.jsp"  target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>">
        <b style="color:white" dir="<%=rtl%>">Upload Menifesto</b> </a></li>
      <%--<li><a href="<%=contextPath%>/admin/view_all.jsp" target="f3" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>" >
      <b style="color:white" dir="<%=rtl%>"> <%=resource.getString("login.viewall")%></b></a></li>--%>
  



<%--<li><a href="<%=contextPath%>/admin/update_admin.jsp" onclick="window.setTimeout('winresize()', 1000);" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"> <%=resource.getString("login.modifyinstituterecord")%></b></a></li>
<li><a href="<%=contextPath%>/admin/search_admin.jsp"  target="f3" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.searchinstitute")%>  </b></a></li>
<li><a href="<%=contextPath%>/manage_superadmin.jsp"  target="f3" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.managesuperadminaccount")%></b></a></li>
<li><a href="<%=contextPath%>/admin/view_blocked_institute.jsp" onclick="window.setTimeout('winresize()', 1000);"  target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>">
    <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.changeworkingstatus")%> </b></a></li>--%>

</ul>



<table border=0 cellpadding=0 cellspacing=0 style="position: absolute;top: 20px" width="100%" dir="<%=rtl%>">
   
  <%--<tr><td dir="<%=rtl%>">
            <font color="blue" size="-1" dir="<%=rtl%>"><b><br>
<%=resource.getString("login.pendingrequestforinstitueregistration")%> (<%=count%>)&nbsp;<a href="<%=contextPath%>/admin/view_pending.jsp" target="f3" dir="<%=rtl%>"> <%=resource.getString("viewpending")%></a>

    </b>
</font>

      </td></tr>--%>
  <tr dir="<%=rtl%>"><td id="ifr3" align="left" rowspan="2" style=" padding-left:200px;" dir="<%=rtl%>">
          <IFRAME  name="f3" src="#" frameborder=0 scrolling="no" width="100%" style="color:deepskyblue;height:100%;visibility:true;" id="f3" dir="<%=rtl%>"></IFRAME>


      </td></tr>
  
 
       
      
</table>

    </div>
        </body>
   </html>
   <%}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?user=<%=user_id%>";</script><%
}%>
       



  