<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled Page</title>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
%>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
<script language="javascript">
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
alert("HTTP error "+req.status+": "+req.statusText);
}
}
}
}

function search() {
 availableSelectList = document.getElementById("searchResult");
  availableSelectList.innerHTML = "";
    var keyValue = document.getElementById("username").value;

    if (echeck(keyValue)==false)
    {
		username.value="";
		username.focus();
		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{
availableSelectList = document.getElementById("searchResult");
var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update);

req.open("POST","<%=request.getContextPath()%>/adminemail.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getEmail_Id="+keyValue);


}
return true;
}
}

function search1() {

 availableSelectList = document.getElementById("searchResult1");
  availableSelectList.innerHTML = "";
    var keyValue = document.getElementById("password").value;

var keyValue1 = document.getElementById("username").value;

    if (pcheck(keyValue)==false)
    {
		password.value="";
		password.focus();
		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{
availableSelectList = document.getElementById("searchResult1");
var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update);

req.open("POST","<%=request.getContextPath()%>/adminpass.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

req.send("getEmail_Id="+keyValue1+"&getPassword="+keyValue);



}
return true;
}
}
function pcheck(str) {

<%--var s_len=str.length ;
  var s_charcode = 0;
 availableSelectList = document.getElementById("searchResult1");
    for (var s_i=0;s_i<s_len;s_i++)
    {
       
     s_charcode = str.charCodeAt(s_i);
     if(!((s_charcode>=48 && s_charcode<=57) || (s_charcode>=65 && s_charcode<=90) ||(s_charcode>=97 && s_charcode<=122)  ))
      {
       availableSelectList.innerHTML = "Special Character not Allowed";
          str.value='';
         str.focus();
        return false;
      }
    }--%>
    
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
function echeck(str) {


availableSelectList = document.getElementById("searchResult");
		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		 availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		  availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		    availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		    availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		    availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		    return false
		 }

		 if (str.indexOf(" ")!=-1){
		    availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		    return false
		 }

 		 return true
	}

</script>



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
    <script language="javascript">
        function fun()
        {
           
            document.form1.action="/LibMS-Struts/admin/language.jsp";
            document.form1.submit();
        }
    </script>


</head>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
<body style="margin:0px 0px 0px 0px" >
    

    <br><br>

    <form method="post" action="/LibMS-Struts/login.do" name="form1">
        <table align="center" style="border:solid 1px white;height:500px">
            <%
String str=(String)request.getAttribute("registration_msg");
if(str!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:18px;font-weight:bold;color:blue;" ><%=str%></span>
<%}%>

            <%if(page.equals(true)){%>
            <tr>
                <td colspan="2" style="" valign="top">
                    <table>
                        <tr><td width="800px" valign="bottom" height="50px">
                   <img src="images/lib.PNG" alt="banner space"  border="0" align="top" id="Image1" style="height:50px;width:200px;">
                            <br>
                            <span style="font-family: Tahoma" dir="<%=rtl%>"><%=resource.getString("login.message.logo.under")%></span><br>
                         
                            </td><td align="right"> <img src="images/logo.png" alt=""  border="0" align="top" id="Image1" style="">
                </td>
            </table></td>
            </tr>
            
            <tr><td width="800px" valign="top" align="left" >
                    
                               <br>
                            <span style="font-family: Arial;font-size:15px;" dir="<%=rtl%>"><br>
                                    <%=resource.getString("login.message.logo.center")%>
                    <table cellpadding="5px">
                    <tr><td height="10px"></td><td></td></tr>
                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="top" > <span style="font-family: Tahoma;font-size:14px; color: #151B54;" ><b><%=resource.getString("login.image.acquisition")%></b></span></td><td width="800px" ><span style="font-family: Tahoma;font-size:14px; color: #006BF5;align:justify;" ><%=resource.getString("login.message.image.acquisition")%></span></td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="left"><span style="font-family: Tahoma;font-size:14px; color: #151B54;" ><b><%=resource.getString("login.image.cataloging")%></b></span></td><td width="800px" ><span style="font-family: Tahoma;font-size:14px; color: #006BF5;align:justify;" ><%=resource.getString("login.message.image.cataloging")%></span></td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="left"><span style="font-family: Tahoma;font-size:14px; color: #151B54;" ><b><%=resource.getString("login.image.circulation")%></b></span></td><td width="800px" ><span style="font-family: Tahoma;font-size:14px; color: #006BF5;align:justify;" ><%=resource.getString("login.message.image.circulation")%></span></td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="left"><span style="font-family: Tahoma;font-size:14px; color: #151B54;" ><b><%=resource.getString("login.image.serial")%></b></span></td><td width="800px" ><span style="font-family: Tahoma;font-size:14px; color: #006BF5;align:justify;" ><%=resource.getString("login.message.image.serial")%></span></td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="left"><span style="font-family: Tahoma;font-size:14px; color: #151B54;" ><b><%=resource.getString("login.image.opac")%></b></span></td><td width="800px" ><span style="font-family: Tahoma;font-size:14px; color: #006BF5;align:justify;" ><%=resource.getString("login.message.image.OPAC")%></span></td></tr>
                        <tr><td height="10px"></td><td></td></tr>


                      </table>

                        
                        </span>
                   
                </td><td align="center">
                    <br>
                    <table  cellpadding="0" style="border:solid 1px #BFDBFF;color:#006BF5;font-family:Tahoma;font-size:13px;font-weight: bold;height:150px;width:300px">
                        <tr colspan="2" align="left" style="top:2%; font-family:Tahoma;font-size:15px;"><%=resource.getString("login.message.selectlanguage")%><select name="locale" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select></tr>
                        <tr><td colspan="2" height="17px" style="color:#006BF5;" align="center"><br><br><%=resource.getString("login.message.signin.top")%><br><br>

                         <img src="images/lib1.PNG" style="height:50px;width:250px;" alt="" style=""  border="0" align="top" id="Image1" style="">

                        </td>
                    </tr>
                    <tr>
                    <td  align="center">
                        <table>
                            <tr> <td><%=resource.getString("login.message.signin.username")%></td>
                                <td align="left"><input name="username" type="text" id="username" onblur="return search();" value="" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;"/>
                    <br/> <div align="left" id="searchResult" class="err" style="border:#000000; "></div></td>
                    </tr>
                           <tr>
                    <td  height="20px"><%=resource.getString("login.message.signin.password")%></td>
                    <td align="left"><input name="password" class="err" type="password" id="password" value="" onblur="return search1();" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;">
                     <div align="left" id="searchResult1" class="err" style="border:#000000; "></div>
                    </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td><td height="20px" align="left"><input id="rememberme" type="checkbox" name="rememberme"><%=resource.getString("login.message.signin.remember")%>
                        <br><br>
                    
                    
                    </td>
                    </tr>
                    <tr>
                    <td>&nbsp;</td><td align="left" valign="bottom">
                        <br>
                        <input type="submit" name="button" value="<%=resource.getString("login.button.sigin.login")%>" dir="rtl" /><input type="submit" name="button" value="<%=resource.getString("login.button.sigin.forgetpassword")%>"/>
                    </td>
                    </tr>
                        </table>

                    </td></tr>

                    

                    </table>
                    <br>
               
                    <span style="font-family: Tahoma;font-size:14px"><b><%=resource.getString("login.message.newlib")%></b></span><br><br>
                   &nbsp;&nbsp;&nbsp;&nbsp; <img src="images/B.jpg" alt="" style="height:20px;width:20px;"  border="0" align="top" id="Image1" style="">&nbsp;&nbsp;<b><a href="/LibMS-Struts/admin/admin_registration.jsp" style="text-decoration: none;color:brown;font-family: Tahoma;font-size:13px;font-weight: bold;"><%= resource.getString("login.href.institute.registration") %></a></b>
                    <br><br>
                    
                    <br>



                </td></tr>
            <tr><td colspan="2" align="center" height="50px" valign="top">

                    <b><span style="font-family: Tahoma;font-size:14px">&copy; <%=resource.getString("login.message.footer")%></span></b>
                </td></tr>

        
        <%}else{%>
        <tr>
    <td height="116"><img src="images/logo.png" width="252" height="112"></td>
  </tr>
  
  <tr>
    <td height="429"><table  >
                                       
      <tr>
          <td height="216" align="right">
		  <table width="984"  cellpadding="0" style="border:solid 1px #BFDBFF;color:#006BF5;font-size:13px;font-weight: bold;height:300px;width:400px">
                      <tr colspan="2" align="left" style="top:2%; font-size:14px;"><select name="locale" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select><span style="top:2%; font-family:Tahoma;font-size:14px;"><%=resource.getString("login.message.selectlanguage")%></span></tr>
                      <tr><td width="978" height="17px" colspan="2" align="center" style="color:#006BF5;"><br><br><%=resource.getString("login.message.signin.top")%><br><br>

                         <img src="images/lib1.PNG" style="height:50px;width:250px;" alt="" style=""  border="0" align="top" id="Image1" style="">

                        </td>
                    </tr>
                    <tr>
                    <td  align="center">
                        <table>

                            <tr><td align="right"><input name="username" type="text" id="username" value="" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-size:11px;" dir="rtl"></td> <td><%=resource.getString("login.message.signin.username")%></td>

                    </tr>
                           <tr>
                               <td align="right"><input name="password" type="password" id="password" value="" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-size:11px;" dir="rtl"></td><td  height="20px"><%=resource.getString("login.message.signin.password")%></td>

                    </tr>
                    <tr>
                    <td height="20px" align="right"><%=resource.getString("login.message.signin.remember")%><input id="rememberme" type="checkbox" name="rememberme">
                        <br><br>


                    </td><td>&nbsp;</td>
                    </tr>
                    <tr>
                   <td align="right" valign="bottom">
                        <br>
                        <input type="submit" name="button" value="<%=resource.getString("login.button.sigin.forgetpassword")%>" dir="rtl"/><input type="submit" name="button" value="<%=resource.getString("login.button.sigin.login")%>" dir="rtl" />
                    </td> <td>&nbsp;</td>
                    </tr>
                        </table>

                        <blockquote>&nbsp;</blockquote></td>
              </tr>




            </table>

		</td>
        <td align="right">
		 <img src="images/lib.PNG" alt="banner space"  border="0" align="top" id="Image1" style="height:50px;width:200px;">
                    <br><br>
                    <span style="font-family: Times New Roman;"><%=resource.getString("login.message.logo.under")%></span><br>
                    <br>
                    <span style="font-family: Arial;font-size:13px;" dir="RTL"><b>
                    <%=resource.getString("login.message.logo.center")%>
                    <br>
                    <table cellpadding="5px">

                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="right"><span style="font-size:14px; color: #151B54;" ><b><%=resource.getString("login.image.acquisition")%></b></span></td><td><span style="font-size:14px; color: #006BF5;align:justify;" ><%=resource.getString("login.message.image.acquisition")%></span></td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="right"><span style="font-size:14px; color: #151B54;" ><b><%=resource.getString("login.image.cataloging")%></b></span></td><td><span style="font-size:14px; color: #006BF5;align:justify;" ><%=resource.getString("login.message.image.cataloging")%></span></td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="right"><span style="font-size:14px; color: #151B54;" ><b><%=resource.getString("login.image.circulation")%></b></span></td><td><span style="font-size:14px; color: #006BF5;align:justify;" ><%=resource.getString("login.message.image.circulation")%></span></td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="right"><span style="font-size:14px; color: #151B54;" ><b><%=resource.getString("login.image.serial")%></b></span></td><td><span style="font-size:14px; color: #006BF5;align:justify;" ><%=resource.getString("login.message.image.serial")%></span></td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="right"><span style="font-size:14px; color: #151B54;" ><b><%=resource.getString("login.image.opac")%></b></span></td><td><span style="font-size:14px; color: #006BF5;align:justify;" ><%=resource.getString("login.message.image.OPAC")%></span></td></tr>
                        <tr><td height="10px"></td><td></td></tr>


                      </table>
                        </b>
                </span>


		</td>
      </tr>
      <tr>
                      <td  align="right">
					   <span style="font-size:14px"><b><%=resource.getString("login.message.newlib")%></b></span><br><br>
                                           &nbsp;&nbsp;&nbsp;&nbsp; <b><a href="/LibMS-Struts/admin/admin_registration.jsp" style="text-decoration: none;color:brown;font-size:13px;font-weight: bold;"><%= resource.getString("login.href.institute.registration") %></a><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style="">&nbsp;&nbsp;</b>

					  </td>
                    </tr>
    </table></td>
  </tr>
 <tr><td colspan="2" align="center" height="50px" valign="top">

                    <b><span style="font-family: Tahoma;font-size:14px">&copy; <%=resource.getString("login.message.footer")%></span></b>
                </td></tr>
  <%}%>
  </table>
</form>

</body>
</html>
