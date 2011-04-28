<%--
    Document   : Member.jsp
    Created on : Jun 10, 2010, 1:15:40 PM
    Author     : Mayank Saxena
--%>
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


</script>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
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
        
        System.out.println(lib_id);
        
%>

    </head><body>
    <%! String message;%>
    
   

    <script language="javascript" type="text/javascript">
        if(top.location != this.location)
            top.location = this.location;
    </script>
<html:form method="post" action="/OPAC/MemberDetails">

 <%--    <%if(page.equals(true)){%>--%>

 <table  align="<%=align%>" dir="<%=rtl%>" width="300px" height="200px" style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">
           <tr><td dir="<%=rtl%>" style="background-color: #c0003b;color:white;" colspan="2" class="btn1" height="30px"><b><%=resource.getString("opac.myaccount.memberlogin")%></b> </td></tr>
            <tr><td dir="<%=rtl%>" width="100px" class="btn1" align="<%=align%>"><%=resource.getString("opac.myaccount.memberid")%></td><td dir="<%=rtl%>"><input id="TXTMEMID"  name="TXTMEMID" type="text"></td></tr>
        <tr><td dir="<%=rtl%>" class="btn1" align="<%=align%>"><%=resource.getString("opac.myaccount.password")%></td><td dir="<%=rtl%>"><input id="TXTPASS" name="TXTPASS" type="password">
               


            </td></tr>
        <tr><td class="btn1" dir="<%=rtl%>" align="<%=align%>">Library Name</td><td dir="<%=rtl%>" width="200px">
               
<html:select property="CMBLib" dir="<%=rtl%>"  tabindex="3"  styleId="CMBLib" value="<%=lib_id%>" onchange="search()">
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
        <tr><td class="btn1" dir="<%=rtl%>" align="<%=align%>">Departmental Library Name</td><td dir="<%=rtl%>" width="200px">
                <html:select property="cmdSubLibary"  styleId="SubLibary" value="<%=sublib_id%>">
                            <html:option value="select">Select</html:option>
                           <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />
                       </html:select>

</tr>
<tr><td dir="<%=rtl%>"></td></tr>


        <tr><td dir="<%=rtl%>"></td><td width="200px" dir="<%=rtl%>"><input id="Button1" class="btn2" value="<%=resource.getString("opac.myaccount.submit")%>"  type="submit">
            <input type="button" class="btn2" value="Back" onclick="quit();">

            </td></tr>
        <tr><td dir="<%=rtl%>"></td><td dir="<%=rtl%>" align="<%=align%>" width="200px">
                  
                <br><br></td></tr>
    </table>

<%--
<%}
    else
{%>

<table  align="right" width="300px" height="200px" style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">

     <tr><td style="background-color: #c0003b;color:white;" colspan="2" class="btn1" height="30px"><b><%=resource.getString("opac.myaccount.memberlogin")%></b> </td></tr>
     <tr><td width="150px" align="right"><input id="TXTMEMID"  name="TXTMEMID" type="text" align="right"></td><td  class="btn1" align="left"><%=resource.getString("opac.myaccount.memberid")%></td></tr>
     <tr><td align="right"><input id="TXTPASS" name="TXTPASS" type="password" align="right">
                

            </td><td class="btn1" align="left"><%=resource.getString("opac.myaccount.password")%></td></tr>
     <tr><td width="150px" align="right">
           
             <html:select property="CMBLib" dir="<%=rtl%>"  tabindex="3"  styleId="CMBLib" value="<%=lib_id%>" onchange="search()">
     <html:options name="Library"  collection="libRs" property="libraryId" labelProperty="libraryName"/>
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

<tr><td></td></tr>

             <td  class="btn1" align="left">Library Name</td>
            <tr><td class="btn1" align="left">Departmental Library Name</td><td width="200px">
                  <html:select property="cmdSubLibary"  styleId="SubLibary" >
                           <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />
                       </html:select>

</tr>



        <tr><td align="right">  <input type="button" class="btn2" value="Back" onclick="quit();">&nbsp;<input id="Button1" class="btn2" value="<%=resource.getString("opac.myaccount.submit")%>"  type="submit"></td><td></td></tr>
        <tr><td  align="right" width="200px">
                  <form method="post" action="">


                

                  </form>
                <br><br></td><td></td></tr>
</table>

<%}%>
--%>

</html:form>

   
    


</body></html>