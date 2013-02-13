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
%>


<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>

<%
String user_id = (String)session.getAttribute("staff_id");

//loginTempDAO logintempdao = new loginTempDAO();
/*List logincheck = logintempdao.getLoginDetails(user_id);
if(!logincheck.isEmpty())
{
    LoginTemp login = (LoginTemp)logincheck.get(0);
    
}
else
    {
    %><!--<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>-->
<%
    }
*/
//pending
if (session.getAttribute("staff_id")!=null)
    {
List rst =(List)session.getAttribute("resultset");
int count=(Integer)session.getAttribute("count");
 int i=1;
// session.setAttribute("resultset", rst);
 //session.setAttribute("count",count);

//approved
//ResultSet rst1 =(ResultSet)session.getAttribute("resultset1");
//int count1=(Integer)request.getAttribute("count1");
 //int j=1;
 //session.setAttribute("resultset1", rst1);
 //session.setAttribute("count1",count1);

//view all
//ResultSet rst2 =(ResultSet)request.getAttribute("resultset2");
//int count2=(Integer)request.getAttribute("count2");
// int k=1;
//session.setAttribute("resultset2", rst2);
//session.setAttribute("count2",count2);

String contextPath = request.getContextPath();

%>
<%@page import="java.util.*,java.io.*,java.net.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";String align="left";
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
   String user=(String)session.getAttribute("username");
String pass=(String)session.getAttribute("pass");
 session.setAttribute("pass","t");
  user_id=   (String)session.getAttribute("user_id");
String user_name=   (String) session.getAttribute("username");
  String question=  (String)request.getAttribute("question");
   String staff_id=  (String) request.getAttribute("staff_id");
    contextPath = request.getContextPath();

    %>
<html>
<head>

    
<!--	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
-->
<link rel="stylesheet" href="<%=contextPath%>/css/page.css"/>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/chrometheme/chromestyle.css" />

<script type="text/javascript" src="<%=contextPath%>/chromejs/chrome.js"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
<script>var loc2 = "http://<%=host%>"+"<%=contextPath%>/superadmin.do";
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
            frmheight = 550+"px";
    //alert("frmheight="+frmheight);
    if(winwidth!=undefined && frmwidth!=undefined)
        windiff= winwidth - frmwidth;
   // document.getElementById("ifr3").style.paddingLeft = windiff*0.5+"px";
    document.getElementById("ifr3").style.height = frmheight;
}

function divload(current,submenu)
{
    
    <%--document.getElementById(submenu).style.left = current.left;--%>
    document.getElementById(submenu).style.visibility="visible";
<%--alert(current.style.left);--%>
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
 
</head>

<body onload="window.setTimeout('winresize()', 1000);"  style=" background-image: url('/EMS/images/paperbg.gif'); margin-top:0; margin-bottom:0;">




        <table align="center" style="padding: 0px 0px 0px 0px;width: 80%;height:100%;border-right:  solid #ECF1EF 10px;border-left:  solid #ECF1EF 10px;" dir="<%=rtl%>" >
            <tr style="background-color: #425C83;color:white"><td><b>&nbsp;Web Admin Module</b></td><td align="right">
  <span style="font-family:arial;color:white;font-size:12px;" dir="<%=rtl%>"><b dir="<%=rtl%>"><%=resource.getString("login.hello")%> [<%=user%>]&nbsp;|<a href="<%=contextPath%>/logout.do" style="text-decoration: none;color:white" dir="<%=rtl%>">&nbsp;<%=resource.getString("login.signout")%></a></b></span>&nbsp;&nbsp;
     </td></tr>

            <tr style="background-image: url('/EMS/images/header.jpg');;height: 100px">
    <td  valign="top" colspan="2" width="100%" align="center">
        <table  align="center" width="100%"  dir="<%=rtl%>">
            <tr><td width="70%"  valign="bottom"  align="<%=align%>">
                            &nbsp;&nbsp;    <span style="font-weight: bold;color:white;font-size: 35px;font-family:Gill, Helvetica, sans-serif;" >Election</span><span style="color:white;font-weight: bold;font-size: 35px;font-family:Gill, Helvetica, sans-serif;" >MS</span>



                </td><td align="center" > <img src="<%=request.getContextPath()%>/images/logo.png" alt="No Image"  border="0" align="center" id="Image1" style="" height="80px" width="120"><br/>

                            </td></tr>
             <tr><td>
                    <div style="background-color: white;color:blue;font-size: 14px;border:double 1px black;font-family:Gill, Helvetica, sans-serif" >
&nbsp;<%=resource.getString("login.message.logo.under")%>&nbsp;

</div>
                </td>
                <td >
                    <div style="background-color: white;color:blue;font-size: 14px;border:double 1px white;font-family:Gill, Helvetica, sans-serif" >


</div>
                </td></tr>
            </table></td>
            </tr>
    
    <%--<body leftmargin="0" topmargin="0" marginwidth="0" onload="fn();" marginheight="0" dir="<%=rtl%>">--%>
    <tr><td valign="top" width="20%">

<table border=0 cellpadding=0 cellspacing=0 style="padding: 0px 0px 0px 0px" width="100%" dir="<%=rtl%>">
    <tr><td height="25px"  align="center" bgcolor="#7697BC" dir="<%=rtl%>"><hr color="white">
          <%-- <a href="<%=contextPath%>/admin/admin_home.jsp"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.home")%>&nbsp;</b></a>
      <hr color="white">--%>
      <a href="<%=contextPath%>/admin/view_pending.jsp" target="f3" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>" >
      <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.viewpendinglist")%>&nbsp;</b></a>
   <hr color="white"><a href="<%=contextPath%>/admin/view_approved.jsp" target="f3" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>" >
      <b style="color:white" dir="<%=rtl%>"> <%=resource.getString("login.viewapprovedlist")%>&nbsp;</b></a>
  <hr color="white"><a href="<%=contextPath%>/admin/view_all.jsp" target="f3" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>" >
      <b style="color:white" dir="<%=rtl%>"> <%=resource.getString("login.viewall")%> &nbsp;</b></a>
  <hr color="white">     <a href="<%=contextPath%>/admin/update_admin.jsp" onclick="window.setTimeout('winresize()', 1000);" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"> <%=resource.getString("login.modifyinstituterecord")%>&nbsp;</b></a>
<hr color="white"><a href="<%=contextPath%>/admin/search_admin.jsp"  target="f3" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.searchinstitute")%>  &nbsp;</b></a>
<hr color="white"><a href="<%=contextPath%>/manage_superadmin.jsp"  target="f3" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.managesuperadminaccount")%>&nbsp;</b></a>
<hr color="white"><a href="<%=contextPath%>/admin/block_admin.jsp"  target="f3" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>">
      <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.delinquentinstitutelist")%> </b>&nbsp;</a>
<hr color="white"><a href="<%=contextPath%>/admin/view_blocked_institute.jsp" onclick="window.setTimeout('winresize()', 1000);"  target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>">
    <b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.changeworkingstatus")%> </b></a>
<hr color="white"><a href="<%=contextPath%>/admin/mailsetting.jsp"  target="f3" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>">
    <b style="color:white" dir="<%=rtl%>">SMTP Mail Setting </b></a>&nbsp;
    <hr color="white">
<a href="<%=contextPath%>/reset_password.jsp"  target="f3" onclick="window.setTimeout('winresize()', 1000);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" dir="<%=rtl%>">
    <b style="color:white" dir="<%=rtl%>">Reset Login Password</b></a>&nbsp;
    <hr color="white">

      </td>
 </tr>
  
 
       
      
</table>
         
        </td><td id="ifr3" valign="top" align="left" rowspan="2" dir="<%=rtl%>">
     <%--   <font color="blue" size="-1" dir="<%=rtl%>"><b><br>
<%=resource.getString("login.pendingrequestforinstitueregistration")%> (<%=count%>)&nbsp;<a href="<%=contextPath%>/admin/view_pending.jsp" target="f3" dir="<%=rtl%>"> <%=resource.getString("viewpending")%></a>

    </b>
</font>--%>
          <IFRAME  name="f3" src="/EMS/admin/view_pending.jsp" frameborder=0 scrolling="no" width="100%" style="color:deepskyblue;height:100%;visibility:true;" id="f3" dir="<%=rtl%>"></IFRAME>

          <br><br><br>
      </td></tr>
        <tr><td colspan="2" align="center"  style="font-family: arial;color:white;font-size: 12px;background-color: #425C83;height: 25px" valign="middle">
         <%=resource.getString("developedby")%> &nbsp;
                    &copy; <%=resource.getString("login.message.footer")%>
                </td></tr>
        </table>
        </body>
   </html>
   <%}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?user=<%=user_id%>";</script><%
}%>
       



  