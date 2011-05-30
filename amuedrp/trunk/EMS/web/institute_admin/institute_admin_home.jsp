<%-- 
    Document   : institute_admin_home
    Created on : Mar 11, 2011, 7:22:26 PM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page import="java.sql.*,java.util.List"%>
<%@page import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
 

 <!-- code for multilingual view -->

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


     <%
try{
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>
    <script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }
String user=(String)session.getAttribute("username");
 String contextPath = request.getContextPath();
%>



<html>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Institute_Admin</title>
        <script language="javascript">
        //Drop Down Menu Code

var urls1 = new buildArray("",
"<%=request.getContextPath()%>/admin/staffupdate.do",
"<%=request.getContextPath()%>/admin/changeuserpassword"


);


function buildArray()
{
  var a = buildArray.arguments;

  for ( var i=0; i<a.length; i++ )
  {
    this[i] = a[i];
  }

  this.length = a.length;
}


function go ( which, num, win )
{
  var n = which.selectedIndex;

  if ( n != 0 )
  {
    var url = eval ( "urls" + num + "[n]" )
    if ( win )
    {
      openWindow ( url );
    }
    else
    {
      location.href = url;


    }
  }
}

function openWindow ( url )
{
  popupWin = window.open ( url, 'remote', 'width=100,height=350,dependent=true' );
}


function additem(linkname,dest){
  document.write('<a  href="'+dest+'" target="page" onclick="return pageload(3);">'+linkname+'</a><br>')
}

function toggle_menu(state){
var theMenu=document.getElementById("ddmenu").style;
if (state==0) {
  theMenu.visibility="hidden" }
else {
  theMenu.visibility = (theMenu.visibility=="hidden") ? "visible" : "hidden";
}
}






        </script>
<style type="text/Stylesheet">
    #ddmenu a{ text-decoration:none; }
#ddmenu a:hover{ background-color:#FFFF95;

    </style>


        <script language="javascript" type="text/javascript">
            function pageload(loc)
            {
              // alert("hey");
              
               document.getElementById("page").innerHTML ="";
               var loc1="";
               if(loc==1) loc1="<%=request.getContextPath()%>/create_manager.do";
               if(loc==2) loc1="<%=request.getContextPath()%>/institute_admin/block_managers.do";
               if(loc==3) loc1="<%=request.getContextPath()%>/update_managers.do";
               if(loc==4) loc1="<%=request.getContextPath()%>/view_managers.do";
               if(loc==5) loc1="<%=request.getContextPath()%>admin_account.do";
               
               document.getElementById("page").innerHTML = "<iframe name=\"page\" id=\"pagetab\" height=\"800px\" width=\"100%\" src=\"/"+loc1+"\"/>";
              
               return true;
            }
function change(){

if(top.location=="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/login.do")
    {
        //alert(top.location);
        top.location="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/instituteadmin.do";
    }}
        </script>
    </head>



    <body onload="change()"><table dir="<%=rtl%>" width="100%" >
            <tr>  <table border="0" width="100%" dir="<%=rtl%>" >
                              <tr>
                                  <td  height="50px" style="font-family:Arial;color:brown;font-size:12px;" dir="<%=rtl%>"><h2  dir="<%=rtl%>"><%=resource.getString("electionmanagement")%></h2></td>
                                  <td  width="250px" valign="top" dir="<%=rtl%>" style="font-family: arial;font-size: 12px;"><%=resource.getString("login.hello")%>
                                                <script>document.write('<span ');
document.write('height:10px;border:0px solid black;font:bold 10pt Verdana;');
document.write(' onClick="toggle_menu(1);');
document.write('event.cancelBubble=1" ><span style="cursor:hand;">');
document.write('<%=user%> <img width=10 height=10 src="<%=request.getContextPath()%>/images/down.gif"></span>| &nbsp;<a href="<%=contextPath%>/logout.do" style="text-decoration: none;color:brown" dir="<%=rtl%>">&nbsp;<%=resource.getString("login.signout")%></a></b>')
document.write('<div id="ddmenu" style="');
document.write('height:45px;border:0px solid black;background-color:white;text-align: left;');
document.write('overflow-y:scroll;visibility:hidden;">')
  additem("<%=resource.getString("view_profile")%>","<%=request.getContextPath()%>/admin_account.do");
  additem("<%=resource.getString("login.managesuperadminaccount.changepassword")%>","<%=request.getContextPath()%>/admin_password.do");



document.onclick= function() {toggle_menu(0); }
document.write('</div></span>');


                        </script>


                     </td>
                              </tr>
                      </table>
        </tr>


        <tr>
        <table border="0" width="100%" bgcolor="#7697BC" style="font-family: arial;font-weight: bold;font-size:13px" dir="<%=rtl%>">
                 <tr>
                     <td align="left" dir="<%=rtl%>" ><a href="<%=request.getContextPath()%>/create_manager.do" target="page" onclick="return pageload(1);" style="text-decoration:none; color: white;font-size: 13px"><%=resource.getString("createelectionmanager")%></a>
                         &nbsp;<font color="white">|</font>&nbsp;
                  <a href="<%=request.getContextPath()%>/block_managers.do" target="page" onclick="return pageload(2);" style="text-decoration:none; color: white;font-size: 13px"><%=resource.getString("blockmanager")%></a>
                  &nbsp;<font color="white">|</font>&nbsp;
                    <a href="<%=request.getContextPath()%>/update_managers.do"  target="page" onclick="return pageload(3);" style="text-decoration:none; color: white;font-size: 13px"><%=resource.getString("updateelectionmanager")%></a>
                    &nbsp;<font color="white">|</font>&nbsp;
                    <a href="<%=request.getContextPath()%>/view_managers.do"  target="page" onclick="return pageload(4);" style="text-decoration:none; color: white;font-size: 13px"><%=resource.getString("Viewmanagerdetails")%></a>

                     </td>

                </tr>
        </table></tr>

        <tr><td dir="<%=rtl%>">

                <table align="left" border="0" width="80%" dir="<%=rtl%>" ><tr><td dir="<%=rtl%>"><div align="left" class="page" id="page"></div></td></tr></table></td>
            <td dir="<%=rtl%>"> <table align="right" dir="<%=rtl%>" border="1" width="20%" style="border:#C7C5B2 1px solid;" >
                <tr>
                    <td style="background-color: #7697BC; color: white" align="center" dir="<%=rtl%>"> <%=resource.getString("currentelection")%></td>
                </tr>

                <tr>
                    <td height="100px" style="border:#C7C5B2 1px solid; font-size: 13px" dir="<%=rtl%>"><marquee direction="up" onmouseover="this.stop()" onmouseout="this.start()" scrollamount="3"><blink>  </blink> </marquee></td>
                </tr>
</table></td>
</tr>
        </table>
    </body>
</html>
