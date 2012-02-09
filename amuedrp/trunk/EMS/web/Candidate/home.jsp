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
     String align="left";
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

<%
try{
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }

String user=(String)session.getAttribute("username");
String pass=(String)session.getAttribute("pass");
 session.setAttribute("pass","t");
   user_id=   (String)session.getAttribute("user_id");

   String instituteName=  (String) session.getAttribute("institute_name");
   String role=  (String) session.getAttribute("login_role");

%>

<html>
<head>

    

<link rel="stylesheet" href="<%=contextPath%>/css/page.css"/>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/chrometheme/chromestyle.css" />

<script type="text/javascript" src="<%=contextPath%>/chromejs/chrome.js"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
<%--<script>var loc2 = "http://<%=host%>"+"<%=contextPath%>/login.do";
    if(window.location=="http://<%=host%><%=contextPath%>login.do")
        window.location=loc2;</script>--%>
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
 
</head>


    
    <body leftmargin="0" topmargin="0" marginwidth="0" onload="fn();" marginheight="0" dir="<%=rtl%>">
 <table width="100%"   border="0px"  style="margin:0px 0px 0px 0px;" dir="<%=rtl%>">

    <tr dir="<%=rtl%>" valign="top">
        <td valign="bottom" dir="<%=rtl%>">

                  <%--      <p align="<%=align%>"  style="font-family:Arial;color:brown;font-size:22px; " dir="<%=rtl%>">&nbsp;&nbsp;<%=resource.getString("electionmanagement")%><br/><br>--%>

           <img src="<%=request.getContextPath()%>/images/logo.bmp" alt="banner space"  border="0" align="top" id="Image1">

        </td>
                    <td dir="<%=rtl%>" align="center" valign="middle" width="50%"><span dir="<%=rtl%>"><b><%=instituteName%><br>&nbsp; Role[<%=role%>]</b></span></td>

                    <td align="right"  valign="top" dir="<%=rtl%>" style="font:8pt Verdana;text-decoration:none;">


<table width="100%" border="0px"><tr>
<td>
<html:img src="/EMS/images/logo.png" width="150px" height="60px"  />
</td>
<td align="right"  valign="top"><%=resource.getString("login.hello")%>,&nbsp;




                              
                        
  <script type="text/javascript" language="javascript">
document.write("<span " );
document.write('style="height:10px;border:0px solid black;font:bold 11px Verdana;"');
document.write(' onclick="toggle_menu(1);');
document.write('event.cancelBubble=1" ><span style="cursor:hand;">');
document.write('<%=user%> <img width=10 height=10 src="<%=request.getContextPath()%>/images/down.gif"></span>');
document.write('<div id="ddmenu" style="');
document.write('height:45px;border:0px solid black;background-color:white;text-decoration:none;text-align: right;padding-right:2px');
document.write('visibility:hidden;">');
add_item("<%=resource.getString("view_profile")%>","<%=request.getContextPath()%>/newregistration2.do?id=<%=session.getAttribute("voter_id")%>");
add_item("<%=resource.getString("login.managesuperadminaccount.changepassword")%>","<%=request.getContextPath()%>/change_password.do");
function add_item(linkname,dest){
  document.write('<a target="f3" href="'+dest+'">'+linkname+'</a><br>');
}

 

function toggle_menu(state){
var theMenu=document.getElementById("ddmenu").style;
if (state==0) {
  theMenu.visibility="hidden"; }
else {
  theMenu.visibility = (theMenu.visibility=="hidden") ? "visible" : "hidden";
}
}


document.onclick= function() {toggle_menu(0); }
document.write('</div></span>');


</script>

</td><td align="left" valign="top" width="20%">
|&nbsp;<a href="<%=contextPath%>/logout.do" style="text-decoration: none;color:brown" dir="<%=rtl%>">&nbsp;<%=resource.getString("login.signout")%></a>

</td></tr>
</table>

                          

                     </td>
                </tr>




                <tr><td colspan="3">

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
         <li><a href="<%=contextPath%>/catchroom1.do"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>">
        <b style="color:white" dir="<%=rtl%>">Chat</b> </a>
         </li>
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
                    </td></tr>



   
                <tr dir="<%=rtl%>"><td id="ifr3" align="left" colspan="3" style=" padding-left:200px; height: 450px;" dir="<%=rtl%>">
          <IFRAME  name="f3" src="#" frameborder=0 scrolling="yes" width="100%" height="100%" style="color:deepskyblue;height: 450;visibility:true;" id="f3" dir="<%=rtl%>"></IFRAME>


      </td></tr>
  
 
       
      
</table>

   
        </body>
   </html>
   <%}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?user=<%=user_id%>";</script><%
}%>
       



  