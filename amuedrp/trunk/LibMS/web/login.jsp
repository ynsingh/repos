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
<style>
    html
{
   overflow: hidden;
}


</style>

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
            <link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>
<style>
  html, body {
    height: 100%;
  }
  #tableContainer-1 {
    height: 100%;
    width: 100%;
    display: table;
  }
  #tableContainer-2 {
    vertical-align: middle;
    display: table-cell;
    height: 100%;
  }
  #myTable {
    margin: 0 auto;
  }
</style>

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
    <body  style="margin:0px 0px 0px 0px;background-repeat: y-repeat;"   background="<%=request.getContextPath()%>/images/bodyBackground.png"  onload="validateSession()">
        



    <form method="post" action="login.do" name="form1" onsubmit="return search2();" >
        <table align="center"  width="100%" id="tableContainer" background="<%=request.getContextPath()%>/images/bodyBackground.png"   dir="<%=rtl%>" >
            
            <tr><td><table align="center"  width="85%"  style="background-color: white;"   dir="<%=rtl%>" >
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
                                 <table width="100%">
                                     <tr><td>



                                <img src="<%=request.getContextPath()%>/images/bp.PNG" alt="banner space"  border="0" align="<%=align%>" dir="<%=rtl%>" id="Image1" style="height:40px;width:150px;">
                                <br>


                            </td>
                            <td align="right" >
                                <img src="<%=request.getContextPath()%>/images/logo.png" alt=""  border="0" align="top" id="Image1" style="height:70px;width:160px;">
                            </td></tr></table>
                            </td>

            </tr>
 <tr><td class="datagrid" valign="middle" align="left" > 
         <table width="100%" >
                                     <tr><td>


         
         <a href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx">NME-ICT ERP Mission</a>&nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/contactus.jsp">Contact Us</a>&nbsp;|&nbsp; <a  href="<%=request.getContextPath()%>/admin/admin_registration.jsp"><%= resource.getString("login.href.institute.registration") %></a>
                                         </td>
<td align="right" class="homepage" ><%=resource.getString("login.message.selectlanguage")%><select name="locale" class="selecthome" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select>
</td>
                                     </tr></table>
                                     </td> </tr>

                        <tr   style="background-color: #BFDBFF;height: 50px;  background-image: url('<%=request.getContextPath()%>/images/banner_bg.jpg'); margin: 0px 0px 0px 0px"><td colspan="2" style="font-style: italic;font-size: 20px;color:white;" valign="middle" align="center">
           "<%=resource.getString("login.message.logo.under")%>"</td>


            </tr>
         
            <tr>
                <td valign="top" align="center" >
                    <table width="100%"  border="0px">
       

            <tr><td  colspan="2" valign="top" align="center" dir="<%=rtl%>" width="75%">
                    
                               
                            <span  dir="<%=rtl%>">
                                <table style="height: 250px; width: 100%;" dir="<%=rtl%>">
                                    <tr><td colspan="2" width="20%" style="height: 20px">
                                           <span dir="<%=rtl%>" class="txtStyle"><%=resource.getString("login.message.logo.center")%></span>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td width="70%">


                                            <table cellpadding="5px" class="logintxtStyle" >
                        
                                                <tr><td width="20%" valign="top" align="center"><a style="font-family: PakType Tehreer;font-size: 25px;font-weight: bold;color:white" href="OPAC/OPACmain1.jsp"   target="_top"><img src="<%=request.getContextPath()%>/images/opac.JPG" height="35px" width="100px" ><%--<%=resource.getString("login.image.opac")%>--%></a></td><td style="border-bottom:  dotted 1px aqua" valign="top" ><%=resource.getString("login.message.image.OPAC")%>
                                                   
                                                    </td></tr>

                        
                                                <tr><td valign="top"  align="center" ><span style="font-family:PakType Tehreer;font-size: 25px;font-weight: bold;color:white"><%--<%=resource.getString("login.image.acquisition")%>--%><img src="<%=request.getContextPath()%>/images/acq.JPG" height="35px" width="150px" ></span></td><td  valign="top" style="border-bottom:  dotted 1px aqua"> <%=resource.getString("login.message.image.acquisition")%>
                                   <%--Record purchases made  in  database, store item descriptions in catalogue and enter invoices and credit notes using common procedures for serials and non-serials.

Manage vendors details, discounts and delivery options, from a central location/Library of Institute.--%></td></tr>
                       
                        <tr><td valign="top" align="center"><span style="font-family:PakType Tehreer;font-size: 25px;font-weight: bold;color:white"><img src="<%=request.getContextPath()%>/images/catalog.jpg" height="35px" width="130px"><%--<%=resource.getString("login.image.cataloging")%>--%></span></td><td valign="top" style="border-bottom:  dotted 1px aqua"><%=resource.getString("login.message.image.cataloging")%>
                                <%--Having highly flexible database to manage a wide variety of physical and electronic materials.

It also support the facility includes Import/Export Data in XLS or FLAT file.--%></td></tr>
                       
                        <tr><td valign="top" align="center"><span style="font-family:arial;font-size: 30px;font-weight: bold;color:black"><b><%=resource.getString("login.image.circulation")%></b><br/></span></td><td valign="top"  style="border-bottom:  dotted 1px aqua"><%=resource.getString("login.message.image.circulation")%>
                            <%--Allow registered users of library system to issue, self-renew and return items.  It supports bar code management  for  check-in/out of materials.--%>
                            </td></tr>
                       
                        <tr><td valign="top" align="center" ><span style="font-family:PakType Tehreer;font-size: 25px;font-weight: bold;color:white"><img src="<%=request.getContextPath()%>/images/serial.GIF" height="35px" ><%--<%=resource.getString("login.image.serial")%>--%></span></td><td valign="top" style="border-bottom:  dotted 1px aqua"><%=resource.getString("login.message.image.serial")%>
                                <%--this Module simplifies ordering, accounting and receipting of serials, which appear as a separate content type in Institute Library catalogue.--%>

 </td></tr>
                        



                      </table>







                                        </td>
                                    </tr>
                                </table>





                        </span>

                </td>
                <td align="center" style="border-left: dashed 1px aqua"  valign="top" width="100%" >
                    <table dir="<%=rtl%>" cellpadding="0"  style="border-style:  ridge 1px cyan;" class="emailheadhome1" align="center" >
                       
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
                    <tr><td colspan="2" height="100px">
                            <input type="hidden" id="button1" name="button1" value=""/>

<p class="emailheadhome1" align="left">
                        <%--<form  action="./admin/remote" method="post">--%>
       Login with Open ID<br/>
Please click your open Id provider:<input type="textbox" style="width:300px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;" name="email" value="http://202.141.40.216:8081/openid/username" id="email2"/>
<br>     <input class="buttonhome" type="button" value="Log In" onclick="send()"/><br>Please replace username with your actual username.
        <%--</form>--%>
</p>
                   

                        </td></tr>
                    <tr>
                        <td colspan="2" height="100px">
        <div align="justify" style="border : solid 1px red;">
    <marquee direction="up" scrollamount="2" scrolldelay="1" onmouseover="this.stop();" onmouseout="this.start();" style="padding:0px 5px; margin:0px; color:#6699FF;">

            Workshop on ERP mission in IITK from 28-01 July 2012.
           


    </marquee>
</div>
 <a href="<%=request.getContextPath()%>/admin/view_instiapproved.jsp">View All Registered Institutes</a>
        

                        </td>
                    </tr>
                    <tr>
    <td colspan="2">            <a href="relnotes.jsp">   Release Notes:LibMS</a>

                        </td>
                    </tr>

                     <tr>
                         <td colspan="2"> <a href="instantUserManual_LibMS-2012.pdf">LibMS Instant UserManual</a>

                        </td>
                    </tr>

                        </table>
<input type="hidden" id="button1" name="button1" value=""/>
                   
                   
                    

                   
         



                </td></tr>




        </table></td></tr>
            <tr><td colspan="2">
                    <table  style="background-color: #BFDBFF;  background-image: url('<%=request.getContextPath()%>/images/body-bg.png'); border:  solid 1px black;margin: 0px 0px 0px 0px" width="100%" align="left" height="100%">
                        


                    </table></td></tr>
            <tr><td align="left" class="datagrid" valign="top">
                          <%=resource.getString("developedby")%>  &copy; <%=resource.getString("login.message.footer")%>
         &nbsp; follow us : <img src="<%=request.getContextPath()%>/images/blog.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/facebook.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/twitter.jpeg" height="16px" width="20px"/>
      <a href="http://www.youtube.com/user/DrAasimZafar?blend=15&ob=5#p/u/0/COwssqRU9Ao"><img src="<%=request.getContextPath()%>/images/youtube.jpeg" height="16px" width="40px"/></a>
      
       Best Viewed with 1024 X 768 on Mozilla FireFox Browser </td></tr>
            <tr><td class="datagrid" >For reporting bugs, suggestion, feature request, and maintainence support
send Email to amuedrp@gmail.com<br><br><a href="<%=request.getContextPath()%>/mem.jsp">View Server Memory Status</a>&nbsp;|&nbsp;You are the Visitor, Number >>
                    <%
    Integer hitsCount =
      (Integer)application.getAttribute("hitCounter");
    if( hitsCount ==null || hitsCount == 0 ){
       /* First visit */

       hitsCount = 1;
    }else{
       /* return visit */

       hitsCount += 1;
    }
    application.setAttribute("hitCounter", hitsCount);
%>
<%= hitsCount%>

                </td></tr>
           
         
        </table>
                </td></tr></table>
                    
                    

</form>

  

</body>
      <script>function validateSession()
{

    var height = 0;
    var body = window.document.body;
    if (window.innerHeight) {
      height = window.innerHeight;
    } else if (body.parentElement.clientHeight) {
      height = body.parentElement.clientHeight;
    } else if (body && body.clientHeight) {
      height = body.clientHeight;
      }
      
      document.getElementById("tableContainer").style.height = height+"px";




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

