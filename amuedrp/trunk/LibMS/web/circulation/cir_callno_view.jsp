<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Library Details
-->
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%-- <jsp:include page="/admin/header.jsp" flush="true" />--%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>LibMS :Call No </title>
<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.js"></script>
</head>
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

<body>

   
     <html:form action="/cir_callno" method="post">
       

<table dir="<%=rtl%>" align="center">
    <tr>

      <%--<td dir="<%=rtl%>"  align="<%=align%>"><p class="txt2"><%=resource.getString("circulation.showcirreqopac.callno")%><a class="star"></a> :</p></td>--%>
      <%--<td dir="<%=rtl%>">--%><%--<html:hidden property="call_no" styleId="call_no"  value="" styleClass="textBoxWidth"/>--%>

<%--    </td>--%>
  </tr>
  <tr>

      <td dir="<%=rtl%>"  align="<%=align%>"><p class="txt2"> Accession No<a class="star"></a> :</p></td>
    <td dir="<%=rtl%>"><html:text property="accessionno" styleId="accessionno"  value="" styleClass="textBoxWidth"/>

    </td>
  </tr>
 
  <tr>
    <td dir="<%=rtl%>" colspan="2" align="center"><input type="button"  value="<%=resource.getString("circulation.cir_newmember.submit")%>"  onClick="return validation();"/>

    
 </td>
</tr>
  
  <tr><td dir="<%=rtl%>" colspan="2">
    <%
 String msg=(String)request.getAttribute("msg");
 String msg1=(String)request.getAttribute("msg1");
           if (msg!=null){
%>
  <br/>   <span style="font-size:12px;font-weight:bold;color:blue;" ><%=msg%></span>
 <%
}else if(msg1!=null){%>
  <br/> <span style="font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>
  <%}
%>

        </td></tr>
                </table>

               
</html:form>


</body>

 <script language="javascript" type="text/javascript">
  function quit()
  {

      window.location="<%=request.getContextPath()%>/circulation/view_for_member.jsp";
      return false;
   }
   function validation()
    {





 //   var sublib_name=document.getElementById('call_no');
  var keyValue1 = document.getElementById('accessionno');









var str="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";



    if( keyValue1.value=="")
        {str+="\n Please Enter AccessionNo ";
             alert(str);
             document.getElementById('accessionno').focus();
            return false;

        }
if(keyValue1.value!="")
    {
        
var keyValue = document.getElementById('accessionno').value;
var framevalue = "<iframe align=center name=section1 id=section1 height=300px width=500px scrolling=no src=<%=request.getContextPath()%>/showbook.do?id="+ keyValue+"&b="+"back"+" frameborder=0 />";
top.document.getElementById("section1").innerHTML = framevalue;
    }

  //  search();


//var keyValue = document.getElementById('call_no').value;
//var framevalue = "<iframe align=center name=section1 id=section1 height=300px width=500px scrolling=no src=<%=request.getContextPath()%>/cir_callno.do?callno="+ keyValue +" frameborder=0 />";
//top.document.getElementById("section1").innerHTML = framevalue;







    }
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

    var keyValue = document.getElementById('call_no').value;

    

//alert("caa");



if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update1);

req.open("POST","<%=request.getContextPath()%>/searchcallno.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getCallNo="+keyValue);


}

return true;

}
function update1(cartXML)
{

var depts = cartXML.getElementsByTagName("course_ids")[0];
//alert(depts);


if(depts==undefined)
{
    document.getElementById('err').innerHTML="No Books are available for Checkout";
    
    return  false;

}
else{
var em = depts.getElementsByTagName("course_id");
document.getElementById('err').innerHTML="";
var keyValue = document.getElementById('call_no').value;
var framevalue = "<iframe align=center name=section1 id=section1 height=300px width=500px scrolling=no src=<%=request.getContextPath()%>/cir_callno.do?callno="+ keyValue +" frameborder=0 />";
top.document.getElementById("section1").innerHTML = framevalue;

}
}
 </script>


</html>


