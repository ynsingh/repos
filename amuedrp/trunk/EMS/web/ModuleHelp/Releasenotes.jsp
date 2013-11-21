<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*,com.myapp.struts.utility.AppPath"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html>
<head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";
    boolean page=true;
    String align="left";
%>
<%
//out.println(AppPath.getProjectPath());
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script language="javascript" type="text/javascript">

    function send()
        {

        location.href="<%=request.getContextPath()%>/admin/remote?email="+document.getElementById('email2').value+"&context="+"<%=request.getScheme()%>"+"://"+"<%=request.getServerName()%>"+":"+"<%=request.getServerPort()%>"+"<%=request.getContextPath()%>";

        }


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
//alert("HTTP error "+req.status+": "+req.statusText);
}
}
}
}

function search() {

    window.status=' Press F1 for help';
 availableSelectList = document.getElementById("searchResult");
  availableSelectList.innerHTML = "";
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

window.status=' Press F1 for help ';
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
   window.onload = function () { Clear(); }
        function Clear() {
            var Backlen=history.length;
            if (Backlen > 0) history.go(-Backlen);
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


</script>

<script type='text/javascript'>
//<![CDATA[
msg = "Election Managment System";
msg = "............................................" + msg;pos = 0;
function scrollMSG() {
document.title = msg.substring(pos, msg.length) + msg.substring(0, pos);

pos++;

if (pos > msg.length) pos = 0
window.setTimeout("scrollMSG()",200);
}
scrollMSG();
//]]>
</script> 

</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

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
   if(userid.innerHTML=="")
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
            <style>a:link{color:white;}</style>
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
<body  style=" background-image: url('/EMS/images/paperbg.gif'); margin-top:0; margin-bottom:0;">
    



    <form method="post" action="/EMS/login.do" name="form1" onsubmit="return search2();" >
        <table align="center" style="padding: 0px 0px 0px 0px;width: 80%;height:100%;border-right:  solid #ECF1EF 3px;border-left:  solid #ECF1EF 3px;" dir="<%=rtl%>" >
             
            
           
            <tr style=" background-image: url('/EMS/images/header.jpg'); height: 100px">
    <td  valign="top" colspan="2" width="100%" align="center">
        <table  align="center" width="100%"  dir="<%=rtl%>">
            <tr style="background-color: #425C83;color:white"><td align="center">
                    
                         
<%String msg1="";
if (request.getAttribute("msg")==null){
String msgsession=(String)request.getParameter("session");
if(msgsession!=null){
    msg1= "Your Session "+msgsession;
}else
    {
    msg1=null;
    }
}
else{
    msg1= (String)request.getAttribute("msg");
}%>

<%
if(msg1!=null)
      { 
   // out.println(msg1);
}
 String msg11 =(String) request.getAttribute("msg1");
 //request.removeAttribute(msg11);
if (msg11!=null){
      out.println(msg11);       
    
       }

%>
 
                    
                    <%
String m=(String)request.getParameter("m");
if(m!=null)
    out.println(m);
%>

                     <%
String col="blue";
String str1=(String)session.getAttribute("msg5");
if(str1==null){
    col="red";
    str1=(String)session.getAttribute("msg5");
}
if(str1!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:12px;font-weight:bold;color:white;" ><%=str1%></span>
<%}%>


            <%

String str=(String)request.getAttribute("msg");
if(str!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:12px;font-weight:bold;color:white;" ><%=str%></span>
<%}%> 

   

                </td><td align="right">         <%=resource.getString("login.message.selectlanguage")%><select name="locale" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select></td>
            </tr>
            <tr><td width="70%"  valign="bottom"  align="<%=align%>">
                            &nbsp;&nbsp;    <span style="font-weight: bold;font-size: 35px;font-family:Gill, Helvetica, sans-serif;color:white" >Election</span><span style="color:white;font-weight: bold;font-size: 35px;font-family:Gill, Helvetica, sans-serif;" >MS</span>

                          
               
                </td><td align="right" > <img src="<%=request.getContextPath()%>/images/logo.png" alt="No Image"  border="0" align="center" id="Image1" style="" height="80px" width="150"><br/>
                                
                            </td></tr>
             <tr><td>
                    <div style="background-color: white;color:blue;font-size: 14px;border:double 1px black;font-family:Gill, Helvetica, sans-serif" >
&nbsp;<%=resource.getString("login.message.logo.under")%>&nbsp;

</div>
                </td>
                <td >
                    <div style="background-color: white;color:blue;font-size: 14px;border:double 1px black;font-family:Gill, Helvetica, sans-serif" >
&nbsp;<%=resource.getString("userlogin")%>&nbsp;

</div>
                </td></tr>
            </table></td>
            </tr>
           
         <%--   <tr  height="5px" style="font-family: arial;color:#6495ED;font-weight: bold;font-size: 12px"><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=resource.getString("login.message.logo.under")%></td></tr>
         --%>   <tr><td width="90%" colspan="2" valign="top" align="center" dir="<%=rtl%>" >

                    <table align="center" ><tr><td align="center" valign="top">
                                <table width="100%" dir="<%=rtl%>" class="datagrid" style="line-height: 17px;color:#00008B;text-align: justify;padding: 5px 5px 5px 5px;font-size: 12px;">
                                    <tr ><td dir="<%=rtl%>" valign="top">
<p align="center"> <b><h2>Release Notes</h2></b></p>

                                          <%=resource.getString("intro")%>
                                          <br><br>
                                          <%=resource.getString("functionmoduleinelection")%><br>
                                          <ol style="line-height: 17px">
                                                   
                                                  <li><b> <%=resource.getString("webadmin")%></b><hr>
                                                      
<ul type="disc">Once Any User Download the System & have a local installation they need to login into the system & Use this module. It has following features
    <li>Approval Process of Institute Request.</li>
    <li>SMTP Mail Setting.</li>
    <li>Block Institute.</li>
    <li>Change Any User Password.</li>
</ul>  



                                                  </li>
                                                  <li><b> <%=resource.getString("chiefelectionofficer")%>
                                                      </b><hr> 
<ul type="disc">When user login into the system as a Chief Election Manager/Officer.  It has following features
    <li>Create Election Manager.</li>
    <li>View Elections Details.</li>
    <li>View All Voters.</li>
    <li>View All Candidates.</li>
     <li>Block Voter.</li>
     <li>Migrate Election Manager.</li> 
    <li>Change Any User Password.</li>
    <li>Cast Vote</li>
    <li>View Election Result</li>
</ul>  




</li>

                                                  <li><b> <%=resource.getString("electionmanager")%></b><hr>
<ul type="disc">When user login into the system as a  Election Manager/Officer.  It has following features
    <li>Manage Election</li>
    <li>Manage Voter
      <ul>
           <li>Add Voter.</li>
           <li>View Voter.</li>
           <li>Block Voter.</li>
           <li>Set Voter List.</li>
           <li>Set Login Password & Key.</li>
           <li>Set Voter Alternate Key.</li>
            <li>Set Voter & Send Link.</li>
            <li>Import Voters.</li>
     </ul>
    </li>
      <li>Cast Vote</li>
      <li>View Election Result</li>
    <li>Manage Candidate Request</li>
    <li>Manage Candidate

      <ul>
       <li>Import Candidates</li>
      
     </ul>
</li>
     <li>Set Mail Body</li>
     <li>Reset Password</li> 
    <li>ResultComputation.
      <ul>
       <li>AGM</li>
       <li>Postal</li>
</ul>

</li>
</ul>  


</li>
                                                  <li><b> <%=resource.getString("voter")%>/<%=resource.getString("candidate")%></b><hr>

<ul type="disc">When user login into the system as a Voter/Candidate.  It has following features
    <li>View Ongoing Elections.</li>
    <li>View Nomination List.</li>
    <li>Upload Manifesto.</li>
    <li>Cast Vote.</li>
     <li>Chatting.</li>
     <li>View Election Result.</li> 
    <li>Send Candidature Request.</li>
</ul>  



</li>
                                                  
                                              </ol>
                                        
                                               
                                         
                                        
                                        


                                        </td></tr>

                                </table>
                               
                            </td><td valign="top" style="background-color:#657AF0 ;font-family:arial;font-size:12px;margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px">

                                <table  width="250px" dir="<%=rtl%>" style="color:white ">
                        <tr><td style="background-color: #425C83;color:white;font-size: 15px"><%=resource.getString("pleaselogin")%></td></tr>
                    <tr>
                    <td  align="center" width="250px">
                        <table dir="<%=rtl%>" width="250px">
                            <tr> <td width="250px"><%=resource.getString("login.message.signin.username")%></td>
                                <td align="left"><input name="username" type="text" id="username" onfocus="return statwords('Please enter your user name for login')" onblur="return search();" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;"/>
                    <br/> <div align="left" id="searchResult" class="err" style="border:#000000; "></div></td>
                    </tr>
                           <tr>
                    <td  ><%=resource.getString("login.message.signin.password")%></td>
                    <td align="left"><input name="password" class="err" type="password" id="password" value="" onfocus="return statwords('Please enter your password')" onblur="return search1();" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;">
                     <div align="left" id="searchResult1" class="err" style="border:#000000; "></div>
                    </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td><td  align="left"><input id="rememberme" type="checkbox" name="rememberme"><%=resource.getString("login.message.signin.remember")%>
                        <br>


                    </td>
                    </tr>
                    <tr>
                    <td align="center" valign="bottom">
                        <input type="submit" name="button" style="font-family: arial;font-size: 12px"  value="<%=resource.getString("login.button.sigin.login")%>" dir="<%=rtl%>"  onclick="return submitLogin();" /></td><td><input type="submit" name="button" style="font-family: arial;font-size: 12px" value="<%=resource.getString("login.button.sigin.forgetpassword")%>" onclick="return submitForget();" />
                    </td>
                    </tr>
                    <tr><td width="250px" colspan="2">

                            <p class="emailheadhome1" align="left">
                        <%--<form  action="./admin/remote" method="post">--%>
      <%=resource.getString("loginwithopen")%><br/>
<%=resource.getString("pleaseclickyour")%><input type="textbox" style="width:300px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;" name="email" value="http://202.141.40.216:8081/openid/username" id="email2"/>
<br>     <input class="buttonhome" type="button" value="<%=resource.getString("login.button.sigin.login")%>" onclick="send()"/><br>Please replace username with your actual username.
        <%--</form>--%>
</p>

                        </td></tr>
                    <tr><td width="250px" colspan="2"  class="homepage" style="color:white">

                             <b><%=resource.getString("importantlink")%></b>

                        <br/> <img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;     <a href="<%=request.getContextPath()%>/admin/admin_registration.jsp"> <%=resource.getString("join")%></a>
                        <br/> <img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;    <a href="<%=request.getContextPath()%>/newenrollment.do">  <%=resource.getString("Voter_Registration")%></a>
                        <br/> <img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;    <a href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx" style="color:white">  <%=resource.getString("login.ems.nmeict")%> <%=resource.getString("homepage")%></a>
                       <br/> <img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;    <a href="#" style="color:white">  <%=resource.getString("sitemap")%></a>
                       <br/> <img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;    <a href="<%=request.getContextPath()%>/login.jsp" style="color:white"> <%=resource.getString("releasenotes.home.page")%> </a>
                       <br/> <img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;    <a href="<%=request.getContextPath()%>/ModuleHelp/CompleteUserManual.pdf" style="color:white"> <%=resource.getString("usermanual")%></a>
                       <br/> <img src="<%=request.getContextPath()%>/images/bullet.jpg">&nbsp;    <a  style="color:white" href="/EMS/ModuleHelp/index.html"><%=resource.getString("login.ems.html.help")%></a></a>

                         </td></tr>


                        </table>



                       

                </td><td  width="80%" colspan="2" valign="top" align="center">
                 
<input type="hidden" id="button1" name="button1" value=""/>
                    </td>
                    

                    </tr>


                    </table>
                            </td></tr></table>
                

                    
                 



                </td></tr>



 <tr><td colspan="3" align="center"  style="font-family: arial;color:white;font-size: 12px;background-color: #425C83;height: 25px" valign="middle">
         <%=resource.getString("developedby")%> &nbsp;
                    &copy; <%=resource.getString("login.message.footer")%>
                </td></tr>
        </table>
  
</form>

</body>
     


</html>

