

<%@ page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
List lib =(List)session.getAttribute("lib");
List sublib = (List)session.getAttribute("sublib");
String library_id=(String)session.getAttribute("library_id");
String name="", email="", comments="",cardno="", date="" ;

  Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    date=day+"/"+ (month+1) +"/"+year;
String sublib_id = (String)session.getAttribute("memsublib");
        if(sublib_id==null)sublib_id= (String)session.getAttribute("sublibrary_id");
%>
<!--jsp:include page="feedback.htm"/-->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>FEEDBACK...</title>
<!?Applying styles for the page. -->
<!--Applying client side validations using JavaScript. -->

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
    window.status="Press F1 for Help";

    var keyValue = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;

if (keyValue=="Select")
    {


               document.getElementById('CMBLib').focus();
               document.getElementById('SubLibary').options.length = 0;
                newOpt = document.getElementById('SubLibary').appendChild(document.createElement('option'));
            newOpt.value = "Select";
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
if(ndValue=="<%=sublib_id%>")
{
    newOpt.selected = true;
}

}

}
function loadHelp()
{
    window.status="Press F1 for Help";
}
</script>


<script language="javascript" type="text/javascript">
function isEmpty(str)
{
if(str=="")
{
return true;
}
else return false;
}



function validateAll()
{

var LibraryId=document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;
var SubLibraryId=document.getElementById('SubLibary').options[document.getElementById('SubLibary').selectedIndex].value;

var name = document.getElementById('name').value;
var email =document.getElementById('email').value;
var comm=document.getElementById('comments').value;


var reg = new RegExp("^[A-Za-z0-9_]{1,}[.]?[A-Za-z0-9_]{1,}@{1}([A-Za-z0-9_]+[.]{1})+[A-Za-z0-9_]{1,}$");

if(LibraryId=="Select")
 {
alert("Please Select Library ID.");
document.getElementById('CMBLib').focus();
return false;
    }

if(SubLibraryId=="Select")
 {
alert("Please Select SubLibrary ID.");
document.getElementById('CMBSUBLib').focus();
return false;
    }


if(isEmpty(name))
{
alert("Please type your name.");
document.getElementById('name').focus();
return false;
}

else if(isEmpty(email))
{
alert("Please type your e-mail address.");
document.getElementById('email').focus();
return false;
}
//else if(!isEmpty(email))
//{
//if(!reg.test(email))
//{
//alert("Invalid E-mail address. Please re-enter.");
//document.forms[0].email.focus();
//return false;
//}
//}
if(isEmpty(comm))
{
alert("Please type your Comment.");
document.getElementById('comments').focus();
return false;
}
}
<!--The func_subm() function submits the text field values to the server after validating them. -->
function func_subm()
{
if(validateAll())
{
frmRegister.action="feedback.jsp";
frmRegister.method="post";
frmRegister.submit();
}
}

</script>
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



</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="search();" style="background-color:#e0e8f5;" >
   

    <html:form  action="/Feedback" method="post" onsubmit="return validateAll()">
        <table  align="center" dir="<%=rtl%>" width="50%" class="datagrid" style="border:solid 1px black;">

            <tr dir="<%=rtl%>"><td  dir="<%=rtl%>"  class="header" align="center">

		    <%=resource.getString("opac.feedback.yourfeedback")%>



          
                  </td></tr>


  <tr><td dir="<%=rtl%>">
          <br>

          <table align="center" class="datagrid" width="100%" dir="<%=rtl%>">
              <tr><td  align="right" dir="<%=rtl%>" colspan="2">  <font dir="<%=rtl%>">  <%=resource.getString("opac.feedback.note")%></font></td></tr>
 <tr><td  align="<%=align%>" dir="<%=rtl%>">Library ID</td><td  dir="<%=rtl%>">
         <html:select property="CMBLib"  tabindex="3" dir="<%=rtl%>" styleClass="selecthome" value="<%=library_id%>"  styleId="CMBLib" onchange="search()">
             <html:option value="Select">Select</html:option>
             <html:options collection="lib" property="libraryId" labelProperty="libraryName"/>
 </html:select></td></tr>

  <tr><td dir="<%=rtl%>" align="<%=align%>">SubLibrary ID</td><td >
          <html:select dir="<%=rtl%>" property="CMBSUBLib"  styleClass="selecthome" styleId="SubLibary" >
                           <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />
                       </html:select></td></tr>
              <tr><td  dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opac.feedback.date")%>:</td><td>
              <input type="text" name="date1" dir="<%=rtl%>"  font="Arial" color="BLACK" disabled="disabled"  value=<%=date%>>
             </td></tr>

              
              <tr><td style="width:130px" align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.feedback.name")%>*:</td><td>
                 <input type="text" id="name" name="name" dir="<%=rtl%>" onfocus="statwords('Enter Your Name')" onblur="loadHelp()"  >
             </td></tr>
               <tr><td style="width:130px" align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.feedback.email")%>*:</td><td>
                 <input type="text" id="email" name="email" dir="<%=rtl%>" onfocus="statwords('Enter Your Email Id')" onblur="loadHelp()" >
             </td></tr>
               <tr><td style="width:130px" align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.feedback.comments")%>*:</td><td>
                 <input type="text" id="comments" name="comments" dir="<%=rtl%>" onfocus="statwords('Write Your Comments or Suggestions. Thanks')" onblur="loadHelp()"  />
             </td></tr>
               <tr><td></td><td>
              <input class="buttonhome" type="submit" name="Submit" dir="<%=rtl%>" value="<%=resource.getString("opac.feedback.submit")%>" align="right">   <input class="buttonhome" type="reset" value="<%=resource.getString("opac.feedback.clear")%>" align="left">
              <input class="buttonhome" type="button" name="Cancel" dir="<%=rtl%>" value="<%=resource.getString("opac.feedback.cancel")%>" align="left" onclick="quit();">
                   </td></tr>

            </table>
          <br>
      </td></tr></table>




        

</html:form>
         <%--   <%}else{%>
            

<FORM  name="form1"action="Feedback.do" method="post" onsubmit="return validateAll();">
<table  align="left" width="600px"  style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">



  <tr><td  width="600px"  style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="left">
          <table>
              <tr><td width="600px" style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="center"><b>

		    <%=resource.getString("opac.feedback.yourfeedback")%>



          </b>
                  </td></tr>
              </table>
        </td></tr>

  <tr><td class="btn1" width="600px" >
          <br>

          <table align="center" width="600px" >
              <tr><td  align="left" colspan="2" class="mess" width="600px" >  <font color="blue">  <b><%=resource.getString("opac.feedback.note")%></b><br><br></font> </td></tr>

              <tr><td style="width:130px" align="right">
              <input type="text" name="date1"  font="Arial" color="BLACK" disabled="disabled"  value=<%=date%>>
             </td><td ><%=resource.getString("opac.feedback.date")%>:</td></tr>

              <tr><td style="width:130px" align="right">
                <input type="text" name="cardno">
             </td><td style="width:130px" align="left">:*<%=resource.getString("opac.feedback.cardno")%></td></tr>
              <tr><td style="width:130px" align="right">
                 <input type="text" name="name">
             </td><td style="width:130px" align="left">:*<%=resource.getString("opac.feedback.name")%></td></tr>
               <tr><td style="width:130px" align="right">
                 <input type="text" name="email">
             </td><td style="width:130px" align="left">:*<%=resource.getString("opac.feedback.email")%></td></tr>
               <tr><td style="width:130px" align="right">
                 <input type="text" name="comments" />
             </td><td style="width:130px" align="left">:*<%=resource.getString("opac.feedback.comments")%></td></tr>
               <tr><td style="width:130px" align="right">
              <input class=btn type="button" name="Cancel" value="<%=resource.getString("opac.feedback.cancel")%>" align="left" onclick="quit();">
                       <input class=btn type="reset" value="<%=resource.getString("opac.feedback.clear")%>" align="left">
               <input class="btn" type="submit" name="Submit" value="<%=resource.getString("opac.feedback.submit")%>" align="right">
              
                   </td><td></td></tr>

            </table>
          <br>
      </td></tr></table>






</FORM>
            
            <%}%>--%>

</body>
</html>
