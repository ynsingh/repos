
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,com.myapp.struts.hbm.*,com.myapp.struts.AdminDAO.*"%>
 
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>Member LogIn Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
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
alert("HTTP error "+req.status+": "+req.statusText);
}
}
}
}
function search() {

    var keyValue = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;

if (keyValue=="Select")
    {


               document.getElementById('CMBLib').focus();
               document.getElementById('SubLibary').options.length = 0;
                newOpt = document.getElementById('SubLibary').appendChild(document.createElement('option'));
                newOpt.value ="Select";
                newOpt.text = "Select";


		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update);

req.open("POST","<%=request.getContextPath()%>/sublibrary.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getSubLibrary_Id="+keyValue);


}
return true;
}
}

function update(cartXML)
{
var depts = cartXML.getElementsByTagName("sublibrary_ids")[0];
var em = depts.getElementsByTagName("sublibrary_id");
var em1 = depts.getElementsByTagName("sublibrary_name");

        var newOpt =document.getElementById('SubLibary').appendChild(document.createElement('option'));
        document.getElementById('SubLibary').options.length = 0;

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('SubLibary').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}

}

function quit()
{
    location.href="<%=request.getContextPath()%>/OPAC/OPACmain.jsp";
}

function loadHelp()
{
    window.status="Press F1 for Help";
}

</script>
<style type="text/css">

</style>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<%

try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
  <%
  

        String lib_id = (String)session.getAttribute("library_id");
         String sublib_id = (String)session.getAttribute("memsublib");
        if(sublib_id==null)sublib_id= (String)session.getAttribute("sublibrary_id");
        
       // System.out.println(lib_id);
        
%>

    </head><body onload="loadHelp()" style="background-color:#e0e8f5;margin: 0px 0px 0px 0px" >
    <%! String message;%>
    
   

    <%--<script language="javascript" type="text/javascript">
        if(top.location != this.location)
            top.location = this.location;
    </script>--%>
<html:form method="post" action="/OPAC/MemberDetails">

 

   <table align="center" dir="<%=rtl%>" width="40%" class="datagrid" height="400px"  style="border:solid 1px black;" >


  <tr class="header"><td  width="40%" dir="<%=rtl%>"  height="28px" align="center" colspan="2">


		<%=resource.getString("opac.myaccount.memberlogin")%>




        </td></tr>
           
            <tr><td dir="<%=rtl%>" width="100px"  align="<%=align%>"><%=resource.getString("opac.myaccount.memberid")%></td><td dir="<%=rtl%>"><input id="TXTMEMID"  name="TXTMEMID" type="text" onfocus="statwords('Enter Your User Name which you have recieved through email')" onblur="loadHelp()" ></td></tr>
        <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opac.myaccount.password")%></td><td dir="<%=rtl%>"><input id="TXTPASS" name="TXTPASS" type="password" onfocus="statwords('Enter Password which you have recieved through email')" onblur="loadHelp()" >
               


            </td></tr>
        <tr><td  dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opac.simplesearch.library")%></td><td dir="<%=rtl%>" width="200px">
               
<html:select property="CMBLib" dir="<%=rtl%>"  tabindex="3" styleClass="selecthome" styleId="CMBLib" value="<%=lib_id%>" onchange="search()">
   <html:option value="Select">Select</html:option>
    <html:options name="Library" collection="libRs" property="libraryId" labelProperty="libraryName"/>
 </html:select>
  
 <br>
                 <%
     message=request.getParameter("msg");
     if (message==null) message =(String) request.getAttribute("msg");
    if(message!=null) {%>
            <font size="3px" align=center color=red><b><%=message%></b></font>
   <% }else
        message="";
    %>



            </td></tr>
        <tr><td  dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opac.myaccount.deptname")%></td><td dir="<%=rtl%>" width="200px">
                <html:select property="cmdSubLibary" styleClass="selecthome"  styleId="SubLibary" value="<%=sublib_id%>">
                            <html:option value="select">Select</html:option>
                           <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />
                       </html:select>

</tr>
<tr><td dir="<%=rtl%>"></td></tr>


        <tr><td dir="<%=rtl%>"></td><td width="200px" dir="<%=rtl%>"><input id="Button1" class="buttonhome" value="<%=resource.getString("opac.myaccount.submit")%>"  type="submit">
           <%-- <input type="button" class="buttonhome" value="<%=resource.getString("opac.myaccount.back")%>" onclick="quit();">--%>

            </td></tr>
       
    </table>
</html:form>

   
    


</body></html>