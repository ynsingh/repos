<%-- 
    Document   : manage_superadmin
    Created on : Jan 12, 2011, 7:34:19 PM
    Author     : System Administrator
--%>
<%@page import="java.util.*,java.io.*,java.net.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link rel="Stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
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

    %>
        <%
try{
if(session.getAttribute("library_id")!=null){
System.out.println("library_id"+session.getAttribute("library_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }
String contextPath = request.getContextPath();
String user=(String)session.getAttribute("user_id");
System.out.println(user+"....................");
%>
        <script language="javascript" type="text/javascript">
               function check()
    {

               


        var x=document.getElementById('password1');
        if(x.value=="")
            {
                alert("Existing Password should not be blank");
                 document.getElementById('password1').focus();
                return false;
            }
     if(document.getElementById('password2').value=="")
    {
        alert("Enter New password...");

        document.getElementById('password2').focus();

        return false;
    }
     if(document.getElementById('password3').value=="")
    {
        alert("Enter Re-enter password...");

        document.getElementById('password3').focus();

        return false;
    }
      var x1=document.getElementById('password2');
        var x2=document.getElementById('password3');
        if(x1.value!=x2.value)
            {
                document.getElementById('password2').value="";
                document.getElementById('password3').value="";
                availableSelectList3= document.getElementById("searchResult3");
                availableSelectList3.innerHTML="Password Mismatch";
                document.getElementById('password3').focus();
                return false;
            }
             else{

             availableSelectList3 = document.getElementById("searchResult3");
                availableSelectList3.innerHTML="";

         }
            
                return true;




               


    }
 function checkPass()
    {




        var x=document.getElementById('password1');
        if(x.value=="")
            {
                alert("Existing Password should not be blank");
                 document.getElementById('password1').focus();
                return false;
            }
     if(document.getElementById('password2').value=="")
    {
        alert("Enter New password...");

        document.getElementById('password2').focus();

        return false;
    }

      var x1=document.getElementById('password1');
        var x2=document.getElementById('password2');
        if(x1.value==x2.value)
            {
                document.getElementById('password2').focus();
                availableSelectList1 = document.getElementById("searchResult2");
                availableSelectList1.innerHTML="old and new password cannot be same";
               
                document.getElementById('password2').value="";
                
                return false;
            }
         else{

             availableSelectList1 = document.getElementById("searchResult2");
                availableSelectList1.innerHTML="";

         }
                return true;




               


    }
function clearme()
{
   
   
   
    document.getElementById("password1").value = "";
    document.getElementById("password2").value = "";
    document.getElementById("password3").value = "";
    
    return true;
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
alert("HTTP error "+req.status+": "+req.statusText);
}
}
}
}


function search1() {

 availableSelectList = document.getElementById("searchResult1");
  //availableSelectList.innerHTML = "";
    var keyValue = document.getElementById("password1").value;

var keyValue1 = document.getElementById("user_id1").value;


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

            </script>
    </head>
    <body>
        <html:form styleId="form1"  action="/manage_superadmin.do" method="post">
            <table  align="<%=align%>" dir="<%=rtl%>" width="50%" height="150px" style="background-color: white;border:#C7C5B2 1px solid;margin:0px 0px 0px 0px;">
                <tr><td dir="<%=rtl%>" style="background-color: #7697BC;color:white;" colspan="2" class="btn1" height="30px"><b><%=resource.getString("login.managesuperadminaccount.superadminaccount")%></b> </td></tr>
                <tr><td width="30%" class="btn3" dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("login.managesuperadminaccount.oldloginid")%></td><td>
                        
                        <html:text styleId="user_id1" property="user_id1" value="<%=user%>" readonly="true"/>
                    <html:hidden styleId="user_id1" property="user_id1" value="<%=user%>"/>
                    </td></tr>
           
                <tr><td class="btn3" dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("login.managesuperadminaccount.oldpassword")%></td><td><html:password styleId="password1" onblur="return search1()" property="password1"/><div align="left" id="searchResult1" class="err" style="border:#000000; "></div>
                <tr><td class="btn3" dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("login.managesuperadminaccount.newpassword")%></td><td><html:password styleId="password2" property="password2" onblur="checkPass()"/><div align="left" id="searchResult2" class="err" style="border:#000000; "></div>
                          </td></tr>
           <tr><td class="btn3" dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("login.managesuperadminaccount.repassword")%></td><td><input type="password" id="password3" onblur="return check();"/><div align="left" id="searchResult3" class="err" style="border:#000000; "></div>
                <br>
   <%
   String   message="";
     message =(String) request.getAttribute("msg");
    if(message!=null) {%>
            <font size="2px" dir="<%=rtl%>" align="<%=align%>" color=red><b><%=message%></b></font>
            <script type="text/javascript" language="javascript">
               
                <%if(message.equalsIgnoreCase("Record Successfully Updated")==true){%>
                clearme();
                alert("Please Re-Login to Continue with New Login ID");
                top.location="<%=contextPath%>/logout.do";<%}else{%>
                alert("<%=message%>");
                //document.getElementById("form1").reset();
              //  parent.target = "_blank";
            
            
          
          
          //parent.location.href="<%=contextPath%>/logout.do";
        <%}%>
                </script>
                
   <% }else
        message="";
     String clear="clear";
    %>




            </td></tr>
           <tr><td></td><td dir="<%=rtl%>" width="300px"><br><html:submit styleClass="btn2"  onclick="return check();"> <%=resource.getString("login.managesuperadminaccount.changepassword")%></html:submit>
                   <input type="button" onclick="return clearme();"   class="btn2" dir="<%=rtl%>" value="<%=resource.getString(clear)%>" />
            </td></tr>
        <tr><td></td><td  align="<%=align%>" width="200px">
                  
                <br><br></td></tr>
    </table>
        </html:form>
    </body>
</html>
