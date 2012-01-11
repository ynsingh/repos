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
//session.setAttribute("webadmin",webadmin);
//session.setAttribute("webpass",webpass);
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<noscript>
<table cellpadding="0" cellspacing="0" border="0" width="550">
<tr><td width="100%" valign="top" class="PPDesTxt"><b>Have you disabled JavaScript?</b></td></tr>

<tr><td width="100%" valign="top" class="PPDesTxt">If you have disabled JavaScript, you must re-enable JavaScript to use this page.<a href="<%=request.getContextPath()%>/javascript.html">Click Here</a></td></tr>

</table>

</noscript>
<%--<a href="#" onclick="changeIt()"><img src='<%=request.getContextPath()%>/images/verifyemail.png' name='example' border='0' /></a>--%>
        <script type="text/javascript" language="javascript">
            function changeIt()
{
var theImg = document.getElementsByTagName('img')[0].src;

var x = theImg.split("/");
var t = x.length-1;
var y = x[t];

if(y=='verifyemail.png')
{
document.images.example.src='<%=request.getContextPath()%>/images/busy1.gif'
}
<%--if(y=='busy1.gif')
{
document.images.example.src='<%=request.getContextPath()%>/images/busy.gif'
}--%>
}
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
    <body  style="margin:0px 0px 0px 0px; table-layout: auto;background-color: white; " onload="validateSession()" >
    



    <form method="post" action="login.do" name="form1" onsubmit="return search2();" >
        <table align="center"  width="100%"  style="background-color: white;border:solid 1px black"   dir="<%=rtl%>" >

            <tr>
                <td valign="middle" align="center" >
                    <table width="100%"  >
                        <tr>
                            <td height="2px" colspan="2px">

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

            
        
                            </td></tr>

                        <tr  ><td  valign="bottom" height="10%"   >





                                <img src="<%=request.getContextPath()%>/images/bp.PNG" alt="banner space"  border="0" align="<%=align%>" dir="<%=rtl%>" id="Image1" style="height:50px;width:200px;">
                                <br>
                            

                            </td>
                            <td align="right" >
                                <img src="<%=request.getContextPath()%>/images/logo.png" alt=""  border="0" align="top" id="Image1" style="">
                </td>
            
            </tr>
                    </table>
                </td></tr></table>
                <table  style="background-color: #BFDBFF; background-image: url('<%=request.getContextPath()%>/images/body-bg.png'); border:  solid 1px black;margin: 0px 0px 0px 0px;line-height:20px;" width="100%" height="100%">
                        <tr class="search"><td class="homepage"  valign="middle" align="left">
           &nbsp;<%=resource.getString("login.message.logo.under")%></td>

             <td align="center" class="homepage" ><%=resource.getString("login.message.selectlanguage")%><select name="locale" class="selecthome" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select></td>
            </tr>
                </table>
   <table align="center"  width="100%"  style="background-color: white;border:solid 1px black"   dir="<%=rtl%>" >

            <tr>
                <td valign="top" align="center" >
                    <table width="100%"  border="0px">
       

            <tr><td  colspan="2" valign="top" align="center" dir="<%=rtl%>" width="75%">
                    <br/>
                               
                            <span  dir="<%=rtl%>">
                                <table style="height: 250px; width: 100%;" dir="<%=rtl%>">
                                    <tr><td colspan="2" width="20%" style="height: 20px">
                                           <span class="txtStyle" dir="<%=rtl%>"><%=resource.getString("login.message.logo.center")%></span>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td width="70%">


                                            <table cellpadding="5px" class="logintxtStyle" >
                        
                                                <tr><td width="15%" valign="top" ><b><img src="./images/images.jpeg"  width="20px" alt=""><a href="OPAC/OPACmain1.jsp"   target="_top"><%=resource.getString("login.image.opac")%></a></b></td><td ><%=resource.getString("login.message.image.OPAC")%></td></tr>

                        <%--<tr><td height="5px"></td></tr>--%>
                           <tr><td  valign="top"> <b><img src="./images/images.jpeg" height="22px" width="20px" alt=""><%=resource.getString("login.image.acquisition")%></b></td><td><%=resource.getString("login.message.image.acquisition")%></td></tr>
                       <%-- <tr><td height="5px"></td></tr>--%>
                        <tr><td  valign="top"><b><img src="./images/images.jpeg" height="22px" width="20px" alt=""><%=resource.getString("login.image.cataloging")%></b></td><td ><%=resource.getString("login.message.image.cataloging")%></td></tr>
                        <%--<tr><td height="5px"></td></tr>--%>
                        <tr><td valign="top"><b><img src="./images/images.jpeg" height="22px" width="20px" alt=""><%=resource.getString("login.image.circulation")%></b></td><td><%=resource.getString("login.message.image.circulation")%></td></tr>
                        <%--<tr><td height="5px"></td></tr>--%>
                        <tr><td valign="top"><b><img src="./images/images.jpeg" height="22px" width="20px" alt=""><%=resource.getString("login.image.serial")%></b></td><td><%=resource.getString("login.message.image.serial")%></td></tr>
                        



                      </table>







                                        </td>
                                    </tr>
                                </table>





                        </span>

                </td>
                <td align="center"  valign="top" width="100%" style="background-color: #DDDDDD;">
                 
                   
                    <table  cellpadding="0" width="100%" height="100%" style="background-color: #DDDDDD;height: 150px" dir="<%=rtl%>">
                        <tr><td colspan="2" height="25px" style="background-color: #DDDDDD; vertical-align: middle" align="left" class="emailheadhome" >&nbsp;&nbsp;<font color="black"><%=resource.getString("login.message.signin.top")%></font>

                              <%--  <img src="<%=request.getContextPath()%>/images/LibAccount.PNG" style="height:75px;" alt="" style=""  border="0" align="top" id="Image1" style="">--%>&nbsp;</td>


                    </tr>
                    <tr>
                        <td  align="center" width="100%" style="background-color: white;height: 150px">
                            <table dir="<%=rtl%>" cellpadding="0"  class="emailheadhome1" align="center" >
                            <tr> <td width=30%"><%=resource.getString("login.message.signin.username")%></td>
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
                           


                    </td>
                    </tr>
                    <tr>
                        <td></td> <td valign="bottom"  >
                        <input type="submit" name="button" class="buttonhome"  value="<%=resource.getString("login.button.sigin.login")%>" dir="<%=rtl%>"  onclick="return submitLogin();" />&nbsp;<input type="submit"  class="buttonhome" name="button" value="<%=resource.getString("login.button.sigin.forgetpassword")%>" onclick="return submitForget();" />
                        </td>
                    </tr>
                  
                        </table>
<input type="hidden" id="button1" name="button1" value=""/>
                    </td></tr>
                    <tr><td colspan="2" height="15px" class="emailheadhome1" align="center" ><br/>
 <b> How to Use LibMS?
 <a style="text-decoration: none;color:blue;"  href="<%=request.getContextPath()%>/help/help2.jsp"><i>Click here</i></a></b>
 <br/>
                  
     </td>


                    </tr>
                    <tr><td colspan="2"  class="emailheadhome1" align="center" valign="bottom" ><hr>
                           
 <b>
  <a  href="<%=request.getContextPath()%>/admin/admin_registration.jsp" style="text-decoration: none;color:black" ><%= resource.getString("login.href.institute.registration") %></a></b>
 

     </td>


                    </tr>
                  


                    </table>
                    

                   
         



                </td></tr>



<%--<tr ><td class="homepage" colspan="3"><hr style="border:solid 1px #BFDBFF;"/></td></tr>--%>
        </table></td></tr>
         
        </table>
    
                    <table  style="background-color: #BFDBFF;  background-image: url('<%=request.getContextPath()%>/images/body-bg.png'); border:  solid 1px black;margin: 0px 0px 0px 0px" width="100%" height="100%">
                        <tr class="search"><td class="homepage"  valign="middle" align="right"><span style="color:white;font-size: 20px;font-family: arial;font-weight: bold">Lib</span><span style="color:pink;font-size: 20px;font-family: arial;font-weight: bold">MS</span>
        <%--Powered By<br>
        <img src="<%=request.getContextPath()%>/images/apache-struts-logo.jpg" height="60px" width="100px"/>
--%>

                </td><td  valign="middle" align="left" > &nbsp;About Us&nbsp;|&nbsp;Advertising&nbsp;|&nbsp;User Agreement&nbsp;|&nbsp;Copyright Policy&nbsp;|&nbsp;<a href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx">NME-ICT ERP Mission</a>

                    <%--<br/> </td><%--<td class="homepage" align="right" valign="top">Based on Open Source LibMS at<br>
         <a href="http://sourceforge.net/projects/libms/">   <img src="/LibMS/images/sflogo.png"/></a>
     </td>--%></tr>
                        
                       
                    </table>
                    <table>
                        <tr><td align="left" valign="top" style="margin: 0px 0px 0px 0px;">
                            <div style="height: 60px;" class="homepage">
        <div id="divTestBox2" style="position: absolute;margin: 0px 0px 0px 0px;">
    <table><tr><td>
      <img src="<%=request.getContextPath()%>/images/LOGO979.png" height="80px" width="80px"/></td><td>&nbsp;&nbsp;   <%=resource.getString("developedby")%> &nbsp;<br/> &nbsp;&nbsp;  &copy; <%=resource.getString("login.message.footer")%>
        <br> &nbsp;&nbsp; follow us : <img src="<%=request.getContextPath()%>/images/blog.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/facebook.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/twitter.jpeg" height="16px" width="20px"/>
      <a href="http://www.youtube.com/user/DrAasimZafar?blend=15&ob=5#p/u/0/COwssqRU9Ao"><img src="<%=request.getContextPath()%>/images/youtube.jpeg" height="16px" width="40px"/></a>
            </td></tr></table>



</div>
</div>
<script type="text/javascript">
$(function()
{
       <%-- $("#divTestBox2").animate(
                {
                        "left" : "50px"
                },
                1000
        );--%>
});
</script>


                           </td></tr>
                    </table>

</form>

</body>
      <script>function validateSession()
{
var msg;
<% msg1="";
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

