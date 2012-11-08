<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LibMS</title>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";
    boolean page=true;
    String align="left";

   //  String webadmin=getServletContext().getInitParameter("webmail");
   //  String webpass=getServletContext().getInitParameter("webpass");
%>
<%
System.gc();
%>
<script type="text/javascript" language="javascript">
    this.opener.close();
</script>
<link rel="stylesheet" href="/LibMS/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
<script language="javascript" type="text/javascript">
/*
* Returns an new XMLHttpRequest object, or false if the browser
* doesn't support it
*/
var availableSelectList;
function newXMLHttpRequest() {
var xmlreq = false;
// Create XMLHttpRequest object in non-Microsoft browsers
if (window.XMLHttpRequest) {
xmlreq = new XMLHttpRequest();
} else if (window.ActiveXObject) {
try {
// Try to create XMLHttpRequest in later versions
// of Internet Explorer
xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e1) {
// Failed to create required ActiveXObject
try {
// Try version supported by older versions
// of Internet Explorer
xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
} catch (e2) {
// Unable to create an XMLHttpRequest by any means
xmlreq = false;
}
}
}
return xmlreq;
}
/*
* Returns a function that waits for the specified XMLHttpRequest
* to complete, then passes it XML response to the given handler function.
* req - The XMLHttpRequest whose state is changing
* responseXmlHandler - Function to pass the XML response to
*/
function getReadyStateHandler(req, responseXmlHandler) {
// Return an anonymous function that listens to the XMLHttpRequest instance
return function () {
// If the request's status is "complete"
if (req.readyState == 4) {
// Check that we received a successful response from the server
if (req.status == 200) {
// Pass the XML payload of the response to the handler function.
responseXmlHandler(req.responseXML);
} else {
// An HTTP problem has occurred

}
}
}
}
function send()
        {

        location.href="<%=request.getContextPath()%>/admin/remote?email="+document.getElementById('email2').value+"&context="+"<%=request.getScheme()%>"+"://"+"<%=request.getServerName()%>"+":"+"<%=request.getServerPort()%>"+"<%=request.getContextPath()%>";;

        }

function search() {
    window.status="Press f1 for help";
 availableSelectList = document.getElementById("searchResult");
 // availableSelectList.innerHTML = "";
    var keyValue = document.getElementById("username").value;

    //keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{
availableSelectList = document.getElementById("searchResult");
var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update);

req.open("POST","<%=request.getContextPath()%>/adminemail.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getEmail_Id="+keyValue);


}
else
    {
        availableSelectList = document.getElementById("searchResult");
        availableSelectList.innerHTML="Please enter userId";
    }
return true;

}

function search1() {
 window.status="Press f1 for help";
 availableSelectList = document.getElementById("searchResult1");
  //availableSelectList.innerHTML = "";
    var keyValue = document.getElementById("password").value;

var keyValue1 = document.getElementById("username").value;

  
//    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{
availableSelectList = document.getElementById("searchResult1");
var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update);

req.open("POST","<%=request.getContextPath()%>/adminpass.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

req.send("getEmail_Id="+keyValue1+"&getPassword="+keyValue);

}
else
    {
        availableSelectList = document.getElementById("searchResult1");
        availableSelectList.innerHTML="Please enter password";
        document.getElementById("password").focus();
    }
return true;
}


function update(cartXML)
{
var emails = cartXML.getElementsByTagName("email_ids")[0];
var em = emails.getElementsByTagName("email_id");
availableSelectList.innerHTML = '';
for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
availableSelectList.innerHTML += ndValue+"\n";
}
}
window.status = "Press f1 for help";
$('#slide_after').cycle({
   fx: 'shuffle',
   timeout: 2000,
   slideExpr: 'img',
   after: function() {
     $('#caption').html(this.alt);
   }
});
</script>
</head>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>


<noscript>
<table cellpadding="0" cellspacing="0" border="0" width="550">
<tr><td width="100%" valign="top" class="PPDesTxt"><b>Have you disabled JavaScript?</b></td></tr>

<tr><td width="100%" valign="top" class="PPDesTxt">If you have disabled JavaScript, you must re-enable JavaScript to use this page.<a href="<%=request.getContextPath()%>/javascript.html">Click Here</a></td></tr>

</table>

</noscript>
<script type="text/javascript" language="javascript">
         
    function submitLogin()
{
    var buttonvalue="Log In";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
function submitForget()
{
    var buttonvalue="Forget Password";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}

function fun()
        {

            document.form1.action="<%=request.getContextPath()%>/admin/language.jsp";
            document.form1.submit();
        }
function search2()
{
   search();
   var userid= document.getElementById("searchResult");
   if(userid=="")
        {
        search1();
        }
    var pass = document.getElementById("searchResult1");
        

    if(pass.innerHTML=="" && userid.innerHTML=="")
        {
            return true;
        }
        else
            {
                return false;
            }
}

    </script>
            <link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>
<%
try{
locale1=(String)session.getAttribute("locale");
sessionId = session.getId().toString();
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
   <body  style=" background-color:#cbcbcb; margin-top:0; margin-bottom:0;">

    <form method="post" action="login.do" name="form1" onsubmit="return search2();" >

        <table  align="center" style="margin-left:  150px;margin-right: 150px;padding: 0px 0px 0px 0px;border-collapse: collapse;  border-spacing: 0;"   width="80%" <%--id="tableContainer"--%>     >
          <tr><td class="homepage" style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;border-collapse: collapse;  border-spacing: 0;background-color: black;color:white;" align="right" colspan="2">



         <a style="color:white" href="">SiteMap</a>&nbsp;|&nbsp;<a style="color:white" href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx">NME-ICT ERP Mission</a>&nbsp;|&nbsp;<a  style="color:white" href="<%=request.getContextPath()%>/contactus.jsp">Contact Us</a>&nbsp;|&nbsp; <a style="color:white" href="<%=request.getContextPath()%>/admin/admin_registration.jsp"><%= resource.getString("login.href.institute.registration") %></a>
         &nbsp;|&nbsp;            <a style="color:white" href="./relnotes.do">   Release Notes</a>&nbsp;|&nbsp;  <a style="color:white" href="./instantUserManual_LibMS-2012.pdf">UserManual</a>&nbsp;|&nbsp; <a style="color:white" href="<%=request.getContextPath()%>/admin/view_instiapproved.jsp">View All Registered Institutes</a>&nbsp;|&nbsp;<%=resource.getString("login.message.selectlanguage")%><select name="locale" class="selecthome" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select>



                                         </td>

                                     </tr>
            <tr><td>

                    <table align="center"  width="100%"  style="background-color: white;"   dir="<%=rtl%>" >

                                     <tr ><td>
                                     <table width="100%" style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;"><tr>
                                         <td align="left"  style="background-color: white;color:blue;height: 50px;  margin: 0px 0px 0px 0px;font-size: 18px;valign:bottom" valign="bottom" align="center">
  &nbsp;        <%=resource.getString("login.message.logo.under")%>




                            </td>
                            <td align="right">
  <img src="<%=request.getContextPath()%>/images/logo.png" alt=""  border="0" align="top" id="Image1" style="height:70px;width:160px;">

                            </td>


                                         </tr></table>
                                          <hr color="cyan">
                                         </td>

           </tr>
   <tr  ><td  valign="bottom" height="10%"   ><%--<marquee>Server Timing:11:00 AM to 4:00 PM Maintances & Updation : 4:00 PM onwards on all working days </marquee>--%>
              <%
String col="blue";
String str1=(String)request.getAttribute("msg");
if(str1==null){col="red";str1=(String)request.getAttribute("msg1");}
if(str1!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:15px;font-weight:bold;color:<%=col%>;" ><%=str1%></span>
<%}%>

            <%

            String msg1=(String)request.getAttribute("errmsg");
if(msg1!=null){
%>
<script>

    alert("<%=msg1%>");
    location.href="<%=request.getContextPath()%>/loginpage";
    </script>
<%
}
String str=(String)request.getAttribute("registration_msg");
if(str!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:15px;font-weight:bold;color:blue; direction: <%=rtl%>" ><%=str%></span>
<%}%>




                            </td>

            </tr>




            <tr>
                <td valign="top" align="center" >
                    <table width="100%"  border="0px">


            <tr><td  colspan="2" valign="top" align="center" dir="<%=rtl%>" width="75%">


                            <span  dir="<%=rtl%>">
                                <table style="height: 250px; width: 100%;" dir="<%=rtl%>">

                                    <tr>
                                        <td width="100%" valign="top" height="50%" >


                                            <table width="100%" >
                                                <tr><td colspan="2"  style="height: 18px;">
                                          <%=resource.getString("login.message.logo.center")%>
                                         </td>

                                    </tr>

                                                <tr><td width="50%"  valign="top" style="line-height: 35px;"  align="left"><img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;<a style="font-family:arial;text-decoration: none;font-size: 14px;" href="OPAC/OPACmain1.jsp"   target="_top"><%=resource.getString("login.image.opac")%></a>
                                                   <br/> <img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;<a style="font-family:arial;text-decoration: none;font-size: 14px;"> <%=resource.getString("login.image.serial")%></a>
                                                   <br/><img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;<a style="font-family:arial;text-decoration: none;font-size: 14px;"><%=resource.getString("login.image.acquisition")%></a>
                                                     <br/>  <img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;<a style="font-family:arial;text-decoration: none;font-size: 14px;">Cataloging</a>
                                                      <br/> <img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;<a style="font-family:arial;text-decoration: none;font-size: 14px;">Administration & Security</a>
                                                      <br/> <img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;<a style="font-family:arial;text-decoration: none;font-size: 14px;">Utility & System SetUp</a>
                                                      <br/>  <img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;<a style="font-family:arial;text-decoration: none;font-size: 14px;">Web Admin</a>

                                                    </td>          <td rowspan="6" align="left" valign="bottom" >
                                                                    <img src="./images/cycle.gif">


                                                                </td>

                                                    </tr>

                      </table>







                                        </td>
                                    </tr>
                                </table>





                        </span>

                </td>
                <td align="center" style="border-left: dashed 1px aqua"  valign="top" width="100%" >
                     <form method="post" action="login.do" name="form1" onsubmit="return search2();" >
                    <table dir="<%=rtl%>" cellpadding="0"  style="border-style:  ridge 1px cyan;" class="emailheadhome1" align="center" >

                                <tr> <td width=30%"><%=resource.getString("login.message.signin.username")%></td>
                                <td align="left"><input name="username"  type="text" id="username" onfocus="statwords('Please Enter User ID');"  onblur="return search();" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;"/>
                    <br/> <div align="left" id="searchResult" class="err" style="border:#000000; "></div></td>
                    </tr>
                           <tr>
                    <td  height="18px"><%=resource.getString("login.message.signin.password")%></td>
                    <td align="left"><input name="password" class="err" type="password" id="password" value="" onfocus="statwords('Please Enter Password');" onblur="return search1();" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;">
                     <div align="left" id="searchResult1" class="err" style="border:#000000; "></div>
                    </td>
                    </tr>
                    <tr>
                        <td></td><td height="18px" align="<%=align%>"><input id="rememberme" type="checkbox" name="rememberme"><%=resource.getString("login.message.signin.remember")%>



                    </td>
                    </tr>
                    <tr>
                        <td></td> <td valign="bottom"  >
                        <input type="submit" name="button" class="buttonhome"  value="<%=resource.getString("login.button.sigin.login")%>" dir="<%=rtl%>"  onclick="return submitLogin();" />&nbsp;<input type="submit"  class="buttonhome" name="button" value="<%=resource.getString("login.button.sigin.forgetpassword")%>" onclick="return submitForget();" />
                        </td>
                    </tr>
                    <tr><td colspan="2" height="100px">
                            <input type="hidden" id="button1" name="button1" value=""/>

<p class="emailheadhome1" align="left">
                        <%--<form  action="./admin/remote" method="post">--%>
       Login with Open ID<br/>
Please click your open Id provider:<input type="textbox" style="width:300px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;" name="email" value="http://202.141.40.216:8081/openid/username" id="email2"/>
<br>     <input class="buttonhome" type="button" value="Log In" onclick="send()"/><br>Please replace username with your actual username.

</p>


                        </td></tr>
                    <tr>
                        <td colspan="2" height="100px" valign="middle"  align="left" >

                            <img src="<%=request.getContextPath()%>/images/review-icon.png" height="40px" width="55px">  <span style="font-weight: bold;font-size: 12px">&nbsp;User Reviews</span>&nbsp;<hr>


                             <table width="100%"><tr><td style="background: white" width="10%" >
                                               <img src="<%=request.getContextPath()%>/images/mem.jpg" style="border : solid 1px cyan;"  width="40px"  >
                                           </td><td style="background: white" valign="top" style="margin-left: 20px;">....<br/>

                                           </td></tr>
                                       <tr><td style="background: white">
                                               <img src="<%=request.getContextPath()%>/images/mem.jpg" style="border : solid 1px cyan;" width="40px" width="40px" style="margin:5px 5px 5px 5px;" >
                                           </td><td valign="top" style="background: white" style="margin-left: 20px;">....<br/>

                                           </td></tr>
                                       <tr><td style="background: white">
                                               <img src="<%=request.getContextPath()%>/images/mem.jpg" style="border : solid 1px cyan;" width="40px" width="40px" style="margin:5px 5px 5px 5px;" >
                                           </td><td style="background: white" valign="top" style="margin-left: 20px;">....<br/>

                                           </td></tr>
                                   </table>
                                         &nbsp;  <i>more...</i>




                        </td>
                    </tr>
                    <tr>
    <td colspan="2">                                 </td>
                    </tr>

                     <tr>
                         <td colspan="2">
                        </td>
                    </tr>

                        </table>
<input type="hidden" id="button1" name="button1" value=""/>

                     </form>







                </td></tr>




        </table></td></tr>
            <tr><td><jsp:include page="OPAC/opacfooter.jsp"/></td></tr>
                    </table>
                </td></tr></table>

                    
                    

</form>

  

</body>
    
</html>

