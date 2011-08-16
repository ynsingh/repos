
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%@page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<script>
    function quit()
    {
        location.href="<%=request.getContextPath()%>/OPAC/accountdetails.jsp";
    }
</script>
<style type="text/css">
a:active
{
   color: #0000FF;
}
</style>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="";
%>
<%

try{
if(session.getAttribute("mem_id")!=null){
System.out.println("library_id"+session.getAttribute("mem_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/OPAC/member.jsp?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    }

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
    var MemIdValue=document.getElementById('memId').value;

if (keyValue=="Select")
    {


               document.getElementById('CMBLib').focus();
               document.getElementById('SubLibrary').options.length = 0;
            

		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");

if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update);

req.open("POST","<%=request.getContextPath()%>/loadsublibrary.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getSubLibrary_Id="+keyValue+"&getMember_Id="+MemIdValue);




}
return true;
}
}

function update(cartXML)
{
var depts = cartXML.getElementsByTagName("sublibrary_ids")[0];
var em = depts.getElementsByTagName("sublibrary_id");
var em1 = depts.getElementsByTagName("sublibrary_name");

        var newOpt =document.getElementById('SubLibrary').appendChild(document.createElement('option'));
        document.getElementById('SubLibrary').options.length = 0;
        


for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('SubLibrary').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}

}
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<body onload="return search();">
<%
String card_id,mem_id;



  String lib_id = (String)session.getAttribute("memlib");
  String mem_type=(String)session.getAttribute("mem_type");
//Retrieving the values of NewMember form in variables.
card_id=(String)session.getAttribute("card_id");
mem_id=(String)session.getAttribute("id");
String sublibrary_id=(String)session.getAttribute("sublibrary_id");
        List libRs = (List)session.getAttribute("libRs");
        List sublib = (List)session.getAttribute("sublib");
       

 String name; 
  name=(String)session.getAttribute("mem_name");
%>



 
<html:form method="post" action="/OPAC/NewDemand">
 <table  align="<%=align%>" width="700px"  style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">

     <input type="hidden" value="<%=mem_id%>" id="memId"/>

  <tr><td  width="700px" dir="<%=rtl%>" style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="<%=align%>">
          <table>
              <tr><td width="550px" dir="<%=rtl%>" style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="<%=align%>"><b>


	&nbsp;&nbsp;
                <a href="accountdetails.jsp"  style="text-decoration: none;color:white"><%=resource.getString("opac.accountdetails.home")%></a>&nbsp;|&nbsp;            <a href="newdemand2.jsp"  style="text-decoration: none;color:white"> <%=resource.getString("opac.accountdetails.newdemand")%></a>&nbsp;
   <%-- |&nbsp;<a href="reservationrequest1.jsp"  style="text-decoration: none;color:white"> <%=resource.getString("opac.accountdetails.reservationrequest")%></a>--%>




          </b>
                  </td><td align="<%=align%>" dir="<%=rtl%>" style="color:white;font-family:Tahoma;font-size:12px"><%=resource.getString("opac.accountdetails.hi")%>&nbsp;<%=name%>&nbsp;<b>|</b>&nbsp;<a href="/member.jsp" style="text-decoration: none;color:white"><%=resource.getString("opac.accountdetails.logout")%></a></td></tr></table>
        </td></tr>

    
     <tr><td colspan="3"  dir="<%=rtl%>" valign="top" class="btn1" align="center">
             <table align="center" width="700px">
 <tr><td dir="<%=rtl%>" align="center" style="color:Brown" colspan="3">
         <table align="center" width="700px">
             <tr><td dir="<%=rtl%>" width="350px" align="center">
       <b><%=resource.getString("opac.myaccount.newdemand.newdemandtext")%></b></td><td dir="<%=rtl%>"><font color="blue" align="<%=align%>"> <b><%=resource.getString("opac.myaccount.newdemand.note")%></b><br><br></font>
                 </td></table>
         </td></tr>
     
<tr><td dir="<%=rtl%>" align="<%=align%>" width="150px"><%=resource.getString("opac.myaccount.newdemand.category")%>*</td><td align="<%=align%>" dir="<%=rtl%>" width="200px">
        <html:select property="CMBCAT" size="1" >
            <html:option value="">Select Any</html:option>
            <html:option value="books">Books</html:option>

<html:option value="others">OTHERS</html:option>
</html:select>


    </td>
   <td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name"  property="CMBCAT">
            <bean:write name="err_name" />
             </html:messages>
         </td>
</tr>
<tr><td  dir="<%=rtl%>" align="<%=align%>">Library Name</td><td width="200px" align="<%=align%>" dir="<%=rtl%>">
        <html:hidden property="CMBLib" value="<%=lib_id%>"/>
        <html:select property="CMBLib" disabled="true" tabindex="3"  styleId="CMBLib" value="<%=lib_id%>"  onchange="return search();" >
     <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
 </html:select></td>

</tr>
<tr><td  dir="<%=rtl%>" align="<%=align%>">SubLibrary Name*</td><td width="200px"  align="<%=align%>" dir="<%=rtl%>">
                <html:select property="cmdSubLibary"  styleId="SubLibrary" >
                  
                           <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />
                       </html:select>
    </td>  

</tr>

<tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opac.myaccount.newdemand.title")%>*</td><td dir="<%=rtl%>" align="<%=align%>"><html:text property="TXTTITLE" /></td>
 <td  dir="<%=rtl%>" class="err" align="<%=align%>">   <html:messages id="err_name"  property="TXTTITLE">
            <bean:write name="err_name" />
             </html:messages>
         </td>
</tr>
<tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opac.myaccount.newdemand.author")%>*</td><td dir="<%=rtl%>" align="<%=align%>"><html:text property="TXTAUTHOR"   /></td>
 <td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name"  property="TXTAUTHOR">
            <bean:write name="err_name" />
             </html:messages>
         </td>
</tr>
<tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opac.myaccount.newdemand.isbn")%></td><td dir="<%=rtl%>" align="<%=align%>"><html:text property="TXTISBN"/></td>
 <td  class="err" dir="<%=rtl%>">
         </td>
</tr>
<tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opac.myaccount.newdemand.issn")%></td><td dir="<%=rtl%>" align="<%=align%>"><html:text property="issn"  /></td>
 <td  class="err" dir="<%=rtl%>">
         </td>
</tr>

<tr><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.myaccount.newdemand.publisher")%></td><td dir="<%=rtl%>" align="<%=align%>"><html:text property="TXTPUB"  /></td>
 <td  class="err" dir="<%=rtl%>">
         </td>
</tr>
<tr><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.myaccount.newdemand.publishingyear")%></td><td dir="<%=rtl%>" align="<%=align%>"><html:text property="TXTPUBYR" /></td>
 <td  class="err" dir="<%=rtl%>">
         </td>
</tr>
<tr><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.myaccount.newdemand.remark")%></td><td dir="<%=rtl%>" align="<%=align%>"><html:textarea property="TXTREMARK"   rows="2" cols="24"/></td>
 <td  class="err" dir="<%=rtl%>">
         </td>
</tr>
<tr><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.myaccount.newdemand.language")%></td><td dir="<%=rtl%>" align="<%=align%>"><html:text property="lang" /></td>
 <td  class="err" dir="<%=rtl%>">
         </td>
</tr>
<tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opac.myaccount.newdemand.edition")%></td><td  dir="<%=rtl%>" align="<%=align%>"><html:text property="TXTEDITION" /></td>
 <td  class="err" align="<%=align%>">
         </td>
</tr>
<tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opac.myaccount.newdemand.volume")%></td><td dir="<%=rtl%>" align="<%=align%>"><html:text property="TXTVOL" /></td>
 <td  class="err" align="<%=align%>">
         </td>
</tr>

<tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opac.myaccount.newdemand.noofcopies")%>*</td><td dir="<%=rtl%>" align="<%=align%>">
        <% if(mem_type.equals("st")){%>
        
        <html:text property="TXTCOPY"  readonly="true" value="1" />

    <%}else{%>
    <html:text property="TXTCOPY"  />
    <%}%>
    </td>
 <td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name"  property="TXTCOPY">
            <bean:write name="err_name" />
             </html:messages>
         </td>
</tr>

<tr><td dir="<%=rtl%>" ></td><td dir="<%=rtl%>" align="<%=align%>"><html:submit styleClass="btn"><%=resource.getString("opac.myaccount.newdemand.send")%></html:submit>&nbsp;&nbsp;<input type="button"   name="cancel" value="<%=resource.getString("opac.myaccount.newdemand.cancel")%>" class="txt2" onclick="quit()"><br></td></tr>
 </table>


         </td></tr></table>
</html:form>
</body>
</html>
