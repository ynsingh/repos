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
<body style="margin:0px 0px 0px 0px"  background="<%=request.getContextPath()%>/images/spaces_background-2560x1600.png" >
    



    <form method="post" action="login.do" name="form1" onsubmit="return search2();" >
        <table align="center" style="width: 70%;background-color: white;border: solid #ECF1EF 10px;margin-top: 50px" dir="<%=rtl%>" >
             
            <tr><td>
                     <%
String col="blue";
String str1=(String)request.getAttribute("msg");
if(str1==null){col="red";str1=(String)request.getAttribute("msg1");}
if(str1!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:12px;font-weight:bold;color:<%=col%>;" ><%=str1%></span>
<%}%>

            <%

String str=(String)request.getAttribute("registration_msg");
if(str!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:12px;font-weight:bold;color:blue; direction: <%=rtl%>" ><%=str%></span>
<%}%>

                </td> </tr>
           
        <tr>
    <td  valign="top" colspan="2" width="100%" align="center">
        <table  align="center"  dir="<%=rtl%>">
            <tr><td  width="73%" valign="bottom"  align="<%=align%>">
                                <img src="<%=request.getContextPath()%>/images/logo.bmp" alt="banner space"  border="0" align="top" id="Image1">
                          
               
                </td><td> <img src="<%=request.getContextPath()%>/images/logo.png" alt="No Image"  border="0" align="top" id="Image1" style="" height="100px" width="100%"><br/>
                                
                            </td></tr>
            </table></td>
            </tr>
            <tr  height="5px" style="font-family: arial;color:#6495ED;font-weight: bold;font-size: 12px"><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=resource.getString("login.message.logo.under")%></td><td align="center"><%=resource.getString("login.message.selectlanguage")%><select name="locale" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select></td></tr>
            <tr><td width="90%" colspan="2" valign="top" align="center" dir="<%=rtl%>" >

                    <table align="center" ><tr><td align="center" >
                                <table width="90%" dir="<%=rtl%>" style="color:#00008B;font-family: arial;font-size: 16px;text-align: justify;line-height: 30px">
                                    <tr><td dir="<%=rtl%>">

                                          <%=resource.getString("intro")%> 

                                        </td></tr>

                                </table>
                               
                            </td><td style="background-color:#ECF1EF ;font-family:arial;font-size:12px;">

                    <table  width="250px" dir="<%=rtl%>">
                        <tr><td style="background-color: #458B74;color:white;font-size: 15px"><%=resource.getString("pleaselogin")%></td></tr>
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
                            <a href="<%=request.getContextPath()%>/admin/admin_registration.jsp"> <%=resource.getString("join")%></a>
                        </td></tr>
                        </table>



                       

                </td><td  width="80%" colspan="2" valign="top" align="center">
                 
<input type="hidden" id="button1" name="button1" value=""/>
                    </td>
                    

                    </tr><tr><td width="250px" colspan="2"> <a href="<%=request.getContextPath()%>/newenrollment.do">  <%=resource.getString("Voter_Registration")%></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td></tr>


                    </table>
                            </td></tr></table>
                

                    
                 



                </td></tr>



 <tr><td colspan="3" align="center" style="font-family: arial;font-size: 12px;color:#6495ED;" valign="top"><br/><br/><br/>
         <%=resource.getString("developedby")%> &nbsp;<br/>
                    &copy; <%=resource.getString("login.message.footer")%>
                </td></tr>
        </table>
  
</form>

</body>
      
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
   // out.println(msg1);
%>
     


</html>

