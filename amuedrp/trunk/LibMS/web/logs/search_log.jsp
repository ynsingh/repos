<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.util.*,com.myapp.struts.hbm.Library,com.myapp.struts.AdminDAO.LogsDAO"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%@page import="java.util.*,java.io.*,java.net.*"%>

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



<html><head>

<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>
  <script language="javascript" type="text/javascript">

var availableSelectList1;
var availableSelectList2;

var str1;


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

    var keyValue = document.getElementById('library').options[document.getElementById('library').selectedIndex].value;

if(keyValue=="all"){

           document.getElementById('library').focus();
               document.getElementById('SubLibrary').options.length = 0;
               newOpt = document.getElementById('SubLibrary').appendChild(document.createElement('option'));
                newOpt.value = "all";
                newOpt.text = "All";
                
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



               newOpt = document.getElementById('SubLibrary').appendChild(document.createElement('option'));
                newOpt.value = "all";
                newOpt.text = "All";

        document.getElementById('SubLibrary').value="all";

}



function dcheck() {

availableSelectList1 = document.getElementById("searchResult1");

    if (isValidDate(start_date.value)==false)
    {

        availableSelectList1.innerHTML=str1;
		start_date.value="";

                start_date.focus();
		return false;
	}
        else
            {
              availableSelectList1.innerHTML="";
            }
}
function dcheck1() {

availableSelectList2 = document.getElementById("searchResult2");

    if (isValidDate(end_date.value)==false)
    {
         availableSelectList2.innerHTML=str1;
		end_date.value="";

                end_date.focus();
		return false;
	}
        else
    {
    availableSelectList2.innerHTML="";
    }
}

function isValidDate(dateStr) {
// Checks for the following valid date formats:
// YYYY-mm-dd
// Also separates date into month, day, and year variables

var datePat = /^(\d{4})(\-)(\d{1,2})\2(\d{1,2})$/;

// To require a 4 digit year entry, use this line instead:
// var datePat = /^(\d{4})(\-)(\d{1,2})\2(\d{1,2})$/;

var matchArray = dateStr.match(datePat); // is the format ok?
if (matchArray == null) {
str1="Date is not in a valid format.";
return false;
}
month = matchArray[3]; // parse date into variables
day = matchArray[4];
year = matchArray[1];
if (month < 1 || month > 12) { // check month range
str1="Month must be between 1 and 12.";
return false;
}
if (day < 1 || day > 31) {
str1="Day must be between 1 and 31.";

return false;
}
if ((month==4 || month==6 || month==9 || month==11) && day==31) {
str1="Month "+month+" doesn't have 31 days!";

return false
}
if (month == 2) { // check for february 29th
var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
if (day>29 || (day==29 && !isleap)) {
str1="February " + year + " doesn't have " + day + " days!";

return false;
   }
}
return true;  // date is valid
}
//  End -->






/**
 * DHTML email validation script. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */









</script>
<style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>
<script>



$(document).ready(function()
{
   var jQueryDatePicker1Opts =
   {
      dateFormat: 'yy-mm-dd',
      changeMonth: false,
      changeYear: false,
      showButtonPanel: false,
      showAnim: 'show'
   };
   $("#start_date").datepicker(jQueryDatePicker1Opts);
   $("#end_date").datepicker(jQueryDatePicker1Opts);




});

    </script>

<%
try{
if(session.getAttribute("library_id")!=null){
//System.out.println("library_id"+session.getAttribute("library_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

%>

<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<script language="javascript">


</script>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<body onload="search();" class="datagrid">
   

    <html:form action="/showloggrid">
      <table  align="left" width="100%" class="datagrid"  style="border:solid 1px #e0e8f5;" dir="<%=rtl%>" align="<%=align%>">



          <tr bgcolor="#7697BC" ><td  width="100%"   align="center" colspan="2" dir="<%=rtl%>">


                  <font color="white"> <b>Log Report Search</b></font>




        </td></tr>
  <tr style="background-color:#e0e8f5;"><td width="800px"  >
          <table dir="<%=rtl%>" align="<%=align%>">
              <tr><td dir="<%=rtl%>" align="<%=align%>">Login Id</td><td><html:text property="userid"  styleId="userid"></html:text></td>
              <td>


              <input type="submit" value="Find" onclick="fun();">       <input type="reset" id="Button1" name="clear" value="<%=resource.getString("login.searchinstitute.clear")%>">


      </td></tr>
              

          </table>
      </td>
    </tr>
  <tr bgcolor="#7697BC" ><td dir="<%=rtl%>" align="left" colspan="2"><font color="white"><b>Restricted By</b></font></td></tr>
   <tr style="background-color:#e0e8f5;">
       <td align="left" colspan="2">
           <table>
                           <tr>
                               <td>
                                   <table>
                                      
                                       <tr><td>Library Id
<%
List<String> library=(List)session.getAttribute("logliblist");


%>
<html:select property="library_id" styleId="library" onchange="return search();" >
    <html:option value="all">All</html:option>
    <%
    if(library.size()>0){
for(int i=0;i<library.size();i++){
    Library lib=(Library)LogsDAO.SearchlogLibName(library.get(i));
%>
<html:option value="<%=library.get(i) %>"><%=lib.getLibraryName() %></html:option>

<%}}%>
</html:select>
                                           
                                           </td><td>SubLibrary Id
                                               <html:select property="sublibrary" styleId="SubLibrary" size="1">

</html:select>


                                           </td>
                                           <tr><td>Start Date
                                               
                                                   <html:text property="startdate" styleId="start_date"></html:text>
                                               </td><td>End Date
                                                  <html:text property="enddate" styleId="end_date"></html:text>
                                               </td>
                                        


                                   </table>


                               </td>


                               
                           </tr></table>


      </td>

  </tr>
      <tr class="header"  dir="<%=rtl%>">
                               <td colspan="2" dir="<%=rtl%>">
                            <a name="tips" dir="<%=rtl%>">&nbsp;<%=resource.getString("opac.simplesearch.searchtip")%></a>
                            <table class="datagrid" dir="<%=rtl%>" style="background-color:#e0e8f5;color:black" halign="right" border="0" cellpadding="2" cellspacing="0" width="100%" frame="hspaces" height="38" rules="rows">
    <colgroup width="15%"></colgroup><colgroup width="1%"></colgroup><colgroup width="90%"></colgroup>
    <tbody><tr>
    <th colspan="3" class="tipstext" dir="<%=rtl%>">
     <%=resource.getString("opac.additional.t1")%>
    </th>
    </tr>
    <tr>
        <td class="txt2" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.t2")%>
    </td>
    <td class="tipsheading" dir="<%=rtl%>">:</td>
    <td class="tipstext" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.t3")%>
    </td>
    </tr>
<tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" nowrap1="" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.t10")%>
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    		 <%=resource.getString("opac.simplesearch.t11")%>
    	</td>
    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    	   <%=resource.getString("opac.additional.t3")%>
   	</td>
   	<td class="tipsheading" dir="<%=rtl%>">:</td>
   	<td class="tipstext" dir="<%=rtl%>">
    	  <%=resource.getString("opac.additional.t4")%>
    	</td>
    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    	  <%=resource.getString("opac.additional.t5")%>
   	</td>
   	<td class="tipsheading" dir="<%=rtl%>">:</td>
   	<td class="tipstext" dir="<%=rtl%>">
    	  <%=resource.getString("opac.additional.t6")%>
    	</td>
    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    		<%=resource.getString("opac.additional.t7")%>
   	</td>
   	<td class="tipsheading" dir="<%=rtl%>">:</td>
   	<td class="tipstext" dir="<%=rtl%>">
    	  <%=resource.getString("opac.additional.t8")%>
    	</td>
    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    	   <%=resource.getString("opac.additional.t9")%>
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
           <%=resource.getString("opac.additional.t10")%>
    	</td>
    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    		 <%=resource.getString("opac.simplesearch.t8")%>
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    		 <%=resource.getString("opac.simplesearch.t9")%>
    	</td>
    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" nowrap1="" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.t12")%>
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    		 <%=resource.getString("opac.simplesearch.t13")%>
    	</td>
    </tr>
<tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.t4")%>
   	</td>
   	<td class="tipsheading" dir="<%=rtl%>">:</td>
   	<td class="tipstext" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.t5")%>
    	</td>
    </tr>
   <tr valign="top" dir="<%=rtl%>">
   	<td class="txt2" align="right" dir="<%=rtl%>">
   		<%=resource.getString("opac.simplesearch.t16")%>
    	</td>
    	<td colspan="2" class="txt2" dir="<%=rtl%>">
    		<%=resource.getString("opac.additional.t2")%>
    	</td>
   </tr></tbody></table>
                               </td></tr>
 
     

       </table>



   






 

</html:form>
  
</body>
</html>