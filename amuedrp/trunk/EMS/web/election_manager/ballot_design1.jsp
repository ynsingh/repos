<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.util.*,java.io.*,java.net.*" %>
<link type="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
List ls =(List)session.getAttribute("CandidateList");

String role=(String)session.getAttribute("login_role");
if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
%>

<%}else if(role.equalsIgnoreCase("Election Manager")|| role.equalsIgnoreCase("Election Manager,voter"))
   {
%>
<jsp:include page="/election_manager/login.jsp"/>
<%}%>
<html>
    <head>
        <meta http-equiv="Content-Type"  content="text/html; charset=UTF-8">
        <title>Ballot Design</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        
        <script type="text/javascript" language="javascript">

function send()
{
  <%  if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
%>
top.location.href="<%=request.getContextPath()%>/institute_admin/search_election_details.jsp";
<%}else if(role.equalsIgnoreCase("Election Manager")|| role.equalsIgnoreCase("Election Manager,voter"))
   {
%>
window.location="<%=request.getContextPath()%>/electionmanager.do";
<%}else{%>
    top.location.href="<%=request.getContextPath()%>/Voter/voter_home.jsp";
    <%}%>
    return false;
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






function previewBallot() {
   
  if(document.getElementById("ballot").style.display=="inline")
        {
            <%--document.getElementById("ballot").style.display="none";
            //document.getElementById("position").style.display="block";
           // document.getElementById("add Position").style.display = "block";
            document.getElementById("previewtext").textContent = "Preview Ballot";
            return false;--%>
                        window.location = "http://<%=request.getHeader("host")%><%=request.getContextPath()%>/manageElection.do";
        }
    var req = newXMLHttpRequest();
document.getElementById("ballot").style.display="block";
req.onreadystatechange = getReadyStateHandler(req, designBallot);
<% String x=(String)request.getParameter("electionId");
if(x==null)
    x=(String)request.getParameter("id");
%>
       
 req.open("POST","<%=request.getContextPath()%>/getPosition.do?getElectionId=<%=x%>", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send();
return true;
}
function designBallot(cartXML)
{


var htm="";
document.getElementById("ballot").innerHTML="";
var em = cartXML.getElementsByTagName("positions")[0];
var em1 = em.getElementsByTagName("position");

 <%if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
%>
if(em1.length==0){
    alert("No Poisition Defined in the Selected Election");
    top.window.location="/EMS/institute_admin/search_election_details.jsp";
<%}else{%>
if(em1.length==0){
    alert("No Poisition Defined in the Selected Election");
    top.window.location="/EMS/electionmanager.do";
<%}%>
    return true;
}
for(iii=0;iii<em1.length;iii++)
    {
        //position block creation
                var divtag = document.createElement("div");
                divtag.id = "position"+iii;
                divtag.style.backgroundColor = "#D8CEF6";
                divtag.style.border = "solid 5px #F2F5A9";
                divtag.style.borderTopLeftRadius = "15px";
                divtag.style.borderTopRightRadius = "15px";
                divtag.style.borderBottomLeftRadius = "15px";
                divtag.style.borderBottomRightRadius = "15px";
                divtag.style.width = "590px";
                divtag.style.align = "center";
                divtag.style.marginTop = "5px";
               // divtag.style.height = document.height;
                //divtag.innerHTML ='Position Name *<br><input type="text" Id="position_name0'+iii +'" size="40px"/><br>Number of choice *<br><input type="text" Id="numberofchoice0'+ iii +'" size="40px"/><br><br><div id="candidate0'+iii+'" style="position: relative;background-color: #D8CEF6; border: 3px solid #F2F5A9;display: block;width: 595px"></div><input type="button" name="add candidate" style="" id="add0'+iii+'" onclick="create(0,'+iii+',this);" value="Add Candidate" size="50px"><br>';
                //document.getElementById("position").appendChild(divtag);
        //end of block
var positionname1 = em1[iii].getElementsByTagName("positionname");
var positionname = positionname1[0].firstChild.nodeValue;
var positionid1 = em1[iii].getElementsByTagName("positionId");
var positionid = positionid1[0].firstChild.nodeValue;
var noofchoice1 = em1[iii].getElementsByTagName("noofchoice");
var noofchoice = noofchoice1[0].firstChild.nodeValue;
var instruct = em1[iii].getElementsByTagName("instruction");
var instrucT = instruct[0].firstChild.nodeValue;
var instruct1;
var instruct2;
<%--if(instrucT!=undefined)
    if(instrucT.lastIndexOf(noofchoice)!=-1)
        {instruct1 = instrucT.substr(0, instrucT.lastIndexOf(noofchoice)-1);
        instruct2 = instrucT.substr(instrucT.lastIndexOf(noofchoice)+2,instrucT.length);}--%>
htm = ' <div class="datagrid" align="center" >Position: <strong>'+positionname+'</strong><br><strong >'+ instrucT +' <span style="color: red"></span></strong>';
<%--if(noofchoice>1) htm=htm + ' candidates';
else htm=htm + ' candidate';--%>
htm = htm +'<table class="datagrid" width="100%"><tbody><tr><th style="text-align: left;">Candidate ID</th><th style="text-align: left;">Candidate Name</th><th>Selection</th></tr>';

var ca = em1[iii].getElementsByTagName("candidate");

for(jj=0;jj<ca.length;jj++)
    {
           var e1 = ca[jj].getElementsByTagName("candidateenroll");
        var e;
            if(e1[0].firstChild!=null) e = e1[0].firstChild.nodeValue;
            else e = "";


       // if candidate menifesto not uploaded
var mn1 = ca[jj].getElementsByTagName("candidatemn");
 var mn;
            if(mn1[0].firstChild!=null) mn = mn1[0].firstChild.nodeValue;
            else mn = "";


        var candidatename1 = ca[jj].getElementsByTagName("candidatename");
        var candidatename;
            if(candidatename1[0].firstChild!=null) candidatename = candidatename1[0].firstChild.nodeValue;
            else candidatename = "";
       htm = htm +'<tr><td style="text-align: left;"><label for="entry'+iii+'">'+e+'</label><td style="text-align: left;"><label for="entry'+iii+'">'+candidatename+'</label></td>';

if(mn=="1"){
if(noofchoice>1)
       {
               htm = htm +'<td><input type="checkbox" value="'+jj+'" onclick="checkCandidateLimit('+iii+')" name="entry'+iii+'" id="entry'+iii+'" ><a href="/Candidate/viewmenifesto.jsp?id=<%=x%>&amp;pos_id='+positionid+'&amp;candi='+e+'">view ballot</a></td></tr>';
       }
       else
           {
               htm = htm +'<td class="login" align="center"><a href="/EMS/Candidate/viewmenifesto.jsp?id=<%=x%>&amp;pos_id='+positionid+'&amp;candi='+e+'">View Menifesto</a></td></tr>';
           }

        }else{
               htm = htm +'<td align="center">Menifesto Not Uploaded</td></tr>';
        
        }


    }
  htm = htm + ' </tbody></table></div>';

divtag.innerHTML =htm;

document.getElementById("ballot").appendChild(divtag);
document.getElementById("ballot").style.display="inline";

}


}


            </script>
    </head>
    <body onload="previewBallot();">

      <div id="ballot">
    
</div>
<input type="button" value="Back" onclick="send()"/>
            
       

    </body>
</html>
