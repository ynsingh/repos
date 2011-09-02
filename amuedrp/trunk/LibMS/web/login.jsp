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
%>
<script type="text/javascript" language="javascript">
    this.opener.close();
</script>
<link rel="stylesheet" href="/EMS-Struts/css/page.css"/>
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

</script>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
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
<body style="margin:0px 0px 0px 0px; table-layout: auto; " onload="validateSession()" >
    



    <form method="post" action="login.do" name="form1" onsubmit="return search2();" >
        <table align="center" width="90%"  dir="<%=rtl%>" >

            <tr>
                <td valign="top" align="center" >
                    <table width="90%" >
                        <tr>
                            <td height="2px" colspan="2">

              <%
String col="blue";
String str1=(String)request.getAttribute("msg");
if(str1==null){col="red";str1=(String)request.getAttribute("msg1");}
if(str1!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:15px;font-weight:bold;color:<%=col%>;" ><%=str1%></span>
<%}%>

            <%

String str=(String)request.getAttribute("registration_msg");
if(str!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:15px;font-weight:bold;color:blue; direction: <%=rtl%>" ><%=str%></span>
<%}%>

            
        


                        <tr ><td  valign="bottom" height="10%"   colspan="2" >





                                <img src="<%=request.getContextPath()%>/images/bp.PNG" alt="banner space"  border="0" align="<%=align%>" dir="<%=rtl%>" id="Image1" style="height:50px;width:200px;">
                                <br>
                            

                            </td>
                            <td align="left" >
                                <img src="<%=request.getContextPath()%>/images/logo.png" alt=""  border="0" align="top" id="Image1" style="">
                </td>
            
            </tr>
            <tr><td colspan="3" dir="<%=rtl%>" align="<%=align%>" ><span class="txtStyle"  dir="<%=rtl%>"><%=resource.getString("login.message.logo.under")%></span><br></td></tr>

            <tr><td  colspan="2" valign="top" align="center" dir="<%=rtl%>" >

                               
                            <span  dir="<%=rtl%>"><br>
                                <table style="height: 250px; width: 100%;" dir="<%=rtl%>">
                                    <tr><td colspan="2" width="20%" style="height: 20px">
                                           <span class="txtStyle" dir="<%=rtl%>"><%=resource.getString("login.message.logo.center")%></span>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td width="80%">


                                            <table cellpadding="5px" class="logintxtStyle" >
                        
                        <tr><td width="150px"><b><img src="./images/images.jpeg" height="22px" width="25px" alt=""><a href="OPAC/OPACmain1.jsp"><%=resource.getString("login.image.opac")%></a></b></td><td ><%=resource.getString("login.message.image.OPAC")%></td></tr>

                        <tr><td height="5px"></td></tr>
                           <tr><td width="150px"> <b><img src="./images/images.jpeg" height="22px" width="25px" alt=""><%=resource.getString("login.image.acquisition")%></b></td><td><%=resource.getString("login.message.image.acquisition")%></td></tr>
                        <tr><td height="5px"></td></tr>
                        <tr><td valign="top"><b><img src="./images/images.jpeg" height="22px" width="25px" alt=""><%=resource.getString("login.image.cataloging")%></b></td><td ><%=resource.getString("login.message.image.cataloging")%></td></tr>
                        <tr><td height="5px"></td></tr>
                        <tr><td valign="top"><b><img src="./images/images.jpeg" height="22px" width="25px" alt=""><%=resource.getString("login.image.circulation")%></b></td><td><%=resource.getString("login.message.image.circulation")%></td></tr>
                        <tr><td height="5px"></td></tr>
                        <tr><td valign="top"><b><img src="./images/images.jpeg" height="22px" width="25px" alt=""><%=resource.getString("login.image.serial")%></b></td><td><%=resource.getString("login.message.image.serial")%></td></tr>
                        <tr><td height="5px"></td></tr>



                      </table>







                                        </td>
                                    </tr>
                                </table>





                        </span>

                </td>
                <td align="center" height="100%">
                    <br>
                    <table dir="<%=rtl%>" width="90%" align="left">
                    <tr><td align="center" style=" font-family:Tahoma;font-size:15px;"><%=resource.getString("login.message.selectlanguage")%><select name="locale" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select></td></tr></table>
                    <table  cellpadding="0" class="loginemailbox" dir="<%=rtl%>">
                    <tr><td colspan="2" height="2px" style="color:#006BF5; vertical-align: top" align="center" ><br><%=resource.getString("login.message.signin.top")%><br>

                                <img src="<%=request.getContextPath()%>/images/LibAccount.PNG" style="height:75px;" alt="" style=""  border="0" align="top" id="Image1" style="">&nbsp;</td>


                    </tr>
                    <tr>
                    <td  align="center">
                        <table dir="<%=rtl%>" cellpadding="0" Class="">
                            <tr> <td><%=resource.getString("login.message.signin.username")%></td>
                                <td align="left"><input name="username"  type="text" id="username" onfocus="statwords('Please Enter User ID');"  onblur="return search();" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;"/>
                    <br/> <div align="left" id="searchResult" class="err" style="border:#000000; "></div></td>
                    </tr>
                           <tr>
                    <td  height="20px"><%=resource.getString("login.message.signin.password")%></td>
                    <td align="left"><input name="password" class="err" type="password" id="password" value="" onfocus="statwords('Please Enter Password');" onblur="return search1();" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;">
                     <div align="left" id="searchResult1" class="err" style="border:#000000; "></div>
                    </td>
                    </tr>
                    <tr>
                        <td></td><td height="20px" align="<%=align%>"><input id="rememberme" type="checkbox" name="rememberme"><%=resource.getString("login.message.signin.remember")%>
                            <br/><br/>


                    </td>
                    </tr>
                    <tr>
                        <td></td>
                    <td align="left" valign="bottom">
                        <input type="submit" name="button"  value="<%=resource.getString("login.button.sigin.login")%>" dir="<%=rtl%>"  onclick="return submitLogin();" /><input type="submit" name="button" value="<%=resource.getString("login.button.sigin.forgetpassword")%>" onclick="return submitForget();" />
                        <br><br>  </td>
                    </tr>
                        </table>
<input type="hidden" id="button1" name="button1" value=""/>
                    </td></tr>



                    </table>
                    <br>

                    <span style="font-family: Tahoma;font-size:14px"><b><a style="text-decoration: none" class="txt2" href="mailto:nesar.ahmad@gmail.com"><%=resource.getString("login.message.newlib")%></a></b></span><br><br>
                  <b><a href="<%=request.getContextPath()%>/admin/admin_registration.jsp" style="text-decoration: none;color:brown;font-family: Tahoma;font-size:13px;font-weight: bold;"><%= resource.getString("login.href.institute.registration") %></a></b>



                </td></tr>



 <tr><td colspan="2" align="center" class="loginreg" valign="top">
         <%=resource.getString("developedby")%> &nbsp;<br/>
                    &copy; <%=resource.getString("login.message.footer")%>
                </td></tr>
        </table></td></tr>
        </table>
</form>

</body>
      <script>function validateSession()
{
var msg;
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


        msg=<%=msg1%>;
        if (msg!=null  ){
            alert(msg);
        }
}</script>
</html>

