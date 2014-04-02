<%-- 
    Document   : result
    Created on : Aug 10, 2011, 2:40:35 AM
    Author     : faraz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String role=(String)session.getAttribute("login_role");
System.out.println("role issssssssss  "+role);
if(role!=null)
{
if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}else if(role.equalsIgnoreCase("Election Manager")|| role.equalsIgnoreCase("Election Manager,voter"))
   {
    String c=(String)request.getParameter("compute");
    System.out.println(c+"...................");
    if(c==null){
%>

<jsp:include page="/election_manager/login.jsp"/>
<%}}
    else{
    %>
    <%--<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>--%>
    <%
    }}
else{
%>
    <script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
    <%

}
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Election Result</title>
        <link rel="stylesheet" href="/EMS/css/page.css"/>
        <script type="text/javascript">
   //var heigh;
   var q;
   function fn() {

var def_height=undefined;
var i=0;


//alert(theiframe);
var theiframe = "f1";
var IFRAMEref = window.parent.frames[theiframe];
//alert(IFRAMEref);
if (IFRAMEref)
    {//alert(IFRAMEref.document);
   //i=0; while(i<558240000){i++;}
var heigh;
    if(IFRAMEref.document!=null)
    heigh = (typeof IFRAMEref.document.body != 'undefined' &&
typeof IFRAMEref.document.body.scrollHeight != 'undefined') ?
IFRAMEref.document.body.scrollHeight :
(typeof IFRAMEref.document.height != 'undefined') ?
IFRAMEref.document.height :
def_height;

if(heigh==undefined) heigh = document.height;
//alert("i="+i+" heigh="+heigh);
//var d = IFRAMEref.document.getElementById("");
}
heigh = heigh<200?495:heigh;
var he,hei;
//if(heigh>=def_height){
he = heigh + 200;
hei = heigh + 0;//}
<%--else{
    he=parseInt(heigh + 0.27*heigh,10);
    hei = parseInt(heigh + 0.07* heigh,10);}--%>
var overheigh = parent.document.getElementById("overbody").style.height;
<%--alert(overheigh);--%>
overheigh = overheigh.substr(0, overheigh.indexOf("px"));
overheigh = overheigh.substr(0, overheigh.indexOf("%"));

if(he>screen.height)
    parent.document.getElementById("overbody").style.height = he+'px';
else
    parent.document.getElementById("overbody").style.height = screen.height+'px';
var f1heigh = parent.document.getElementById("f1").style.height;
//alert("f1="+f1heigh);
f1heigh = f1heigh.substr(0, f1heigh.indexOf("px"));
f1heigh = f1heigh.substr(0, f1heigh.indexOf("%"));
//if(f1heigh<hei)
    parent.document.getElementById("f1").style.height = hei+'px';

var h = hei + 25;
parent.document.getElementById("electionResult1").style.height = h+'px';

IFRAMEref = null;
return heigh;
}
            var posXml=new Array;
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
 
function send()
{


  <%
  if(role==null){}else{
  if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
%>
top.location.href="<%=request.getContextPath()%>/institute_admin/search_election_details.jsp";
<%}else if(role.equalsIgnoreCase("Election Manager")|| role.equalsIgnoreCase("Election Manager,voter"))
   {%>
 window.location="<%=request.getContextPath()%>/electionmanager.do";
<%}else{%>

 top.location.href="<%=request.getContextPath()%>/Voter/voter_home.jsp";
    <%}}%>

    return false;
    
}

            function previewBallot(p) {
    //alert("index="+index+" current="+current);
   // alert(document.getElementById("position").style.display);
q=p;
    var req = newXMLHttpRequest();
    
    <%String election = request.getParameter("election");%>
req.onreadystatechange = getReadyStateHandler(req, designBallot);

req.open("POST","<%=request.getContextPath()%>/electionResult.do?election=<%=election%>", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send();
return true;
}
var choice= new Array;
function designBallot(cartXML)
{

var htm="";
var ival = 0;
document.getElementById("ballot").innerHTML="";
var em = cartXML.getElementsByTagName("positions")[0];
//alert(em);
if(em!=undefined)
{
    var el = em.getElementsByTagName("election");
    var em1 = em.getElementsByTagName("position");
    var elec = el[0].firstChild.nodeValue;
    var divtag1 = document.createElement("div");
             divtag1.id = "ElectionName";
             divtag1.style.backgroundColor = "#D8CEF6";
             divtag1.style.border = "solid 5px #F2F5A9";
            var htm1 = '<span style="text-align:center">'+ elec +'</span>';
            divtag1.innerHTML = htm1;
            document.getElementById("ballot").appendChild(divtag1);
  //alert(em1.length);
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
                divtag.style.marginLeft = "30px";
                divtag.style.width = "590px";
                divtag.style.align = "center";
                divtag.style.marginTop = "5px";
              
 //end of block
var positionname1 = em1[iii].getElementsByTagName("positionname");
var positionname = positionname1[0].firstChild.nodeValue;
var noofchoice1 = em1[iii].getElementsByTagName("noofchoice");
var noofchoice = noofchoice1[0].firstChild.nodeValue;
posXml[iii]=positionname;
htm = '<div class="building_block" style="margin-left: 15px">Position: <strong>'+positionname+'</strong><div style="float:right;margin-right: 250px">No of Choice: '+noofchoice+'</div>';
htm = htm +'<table class="ballot"><tbody><tr><th style="text-align: left;">Candidate ID/EnrollmentNo</th><th style="text-align: left;width:250px">Candidate Name</th><th>Number of PostalVotes</th><th>Number of AGMVotes</th><th>Number of OnineVotes</th><th>Total Votes</th></tr>';

var ca = em1[iii].getElementsByTagName("candidate");

choice[iii]=noofchoice;
var ch =0;
var max=0;
var n="";
//if(ca.length>1){
var tie=0;
for(jj=0;jj<ca.length;jj++)
    {
         var e1 = ca[jj].getElementsByTagName("candidateenroll");
        var e;
            if(e1[0].firstChild!=null) e = e1[0].firstChild.nodeValue;
            else e = "";




        var candidatename1 = ca[jj].getElementsByTagName("candidatename");
        var postalvotes = ca[jj].getElementsByTagName("postalvotes");
        var postal=0;
        var agmvotes = ca[jj].getElementsByTagName("agmvotes");
        var agm=0;

        var votes1 = ca[jj].getElementsByTagName("votes");

        var candidatename;
        var votes;
            if(candidatename1[0].firstChild!=null) candidatename = candidatename1[0].firstChild.nodeValue;
            else candidatename = "";
            
            
            
            
            
            
            if(postalvotes[0].firstChild!=null) postal = postalvotes[0].firstChild.nodeValue;
            else postal = "0";


             if(agmvotes[0].firstChild!=null) agm = agmvotes[0].firstChild.nodeValue;
            else agm = "0";
            


             if(votes1[0].firstChild!=null) votes = votes1[0].firstChild.nodeValue;
            else votes = "0";
            var v=votes;



v=parseInt(postal)+parseInt(agm)+parseInt(votes);


            if(max<v)
          { max=v;n=e;
          }
else if(max==v && max!=0)
         tie="1";

           
       htm = htm +'<tr><td style="text-align: left;width:250px"><label for="entry'+iii+'">'+e+'</label></td><td style="text-align: left;width:250px"><label for="entry'+iii+'">'+candidatename+'</label></td>';
       htm = htm +'<td style="text-align: center;"><label for="entry'+iii+'">'+postal+'</label></td><td style="text-align: center;"><label for="entry'+iii+'">'+agm+'</label></td><td style="text-align: center;"><label for="entry'+iii+'">'+votes+'</label></td><td style="text-align: center;"><label for="entry'+iii+'">'+v+'</label></td></tr>';
       
ival = iii;

    }

   
  
    <%--
 htm = htm + '<tr><td colspan=6 ><hr color=black></td></tr>';
if(ca.length==1){
         htm = htm + '<tr><td colspan=6 style="text-align:center">1 candidate is there in Ballot, Contact EM</td></tr></tbody></table></div>';

    }
    else{
    if(max==0)
        htm = htm + '<tr><td colspan=6 style="text-align:center">Voting Not Made</td></tr>';
        
    if(tie==0)
        htm = htm + '<tr><td colspan=6 style="text-align:center"><b>Candidate Enrollment '+n +' is elected'+' with '+max+' Number of Votes</b></td></tr>';
    else
        htm = htm + '<tr><td colspan=6 style="text-align:center"><b>Voting Tie</b></td></tr>';
   
  }
    htm = htm + '<tr><td colspan=6 ><hr color=black></td></tr>';
    --%>
  
  htm=htm+'</tbody></table></div>';
divtag.innerHTML =htm;
document.getElementById("ballot").appendChild(divtag);


document.getElementById("ballot").style.display="block";

}

//var s = 'fn()';
//window.setTimeout('fn()', 400);

}
else
    {

   
     <%
role=(String)session.getAttribute("login_role");
if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
%>
             alert("Sorry Election Result is not Declared");
top.location.href="/EMS/institute_admin/search_election_details.jsp";
<%}else if(role.equalsIgnoreCase("Election Manager")|| role.equalsIgnoreCase("Election Manager,voter"))
   {%>
  alert("Sorry Election Result is not Declared");
  location.href="/EMS/electionmanager.do";
<%}else{%>

     
   alert("Sorry Election Result is not Declared");
 top.location.href="/EMS/Voter/voter_home.jsp";
 
    <%}%>
   
 
 
    }

}
        </script>
    </head>

    <body onload="previewBallot(this)" style="margin: 0px;">
<table style="background-color: blue;color:white;font-weight: bold;font-size: 12px;" align="center" width="660px">
            <tr><td>Election Voting Result</td><td align="right">

<input type="button" value="Close" onclick="send()"/>
                </td></tr>
            <tr><td>
                    <a style="color:white" href="<%=request.getContextPath()%>/electionResult.do?election=<%=election%>&amp;report=true">Show PDF Report</a><br>

                </td>
                <td align="right"><a style="color:white" href="<%=request.getContextPath()%>/printlog.do?election=<%=election%>">Show PDF Report In Graphical Format</a>
</td></tr>
        </table>
 
        <div id="main" align="center" style="width: 100%; height: 100%;font-family: Gill, Helvetica, sans-serif ;font-size: 12px" >
            <div id="middle" style="width: 100%;margin-left: 0%; position: relative;">
                <form id="form1">
                <div id="ballot" style="width: 660px; position: relative;margin-left: 0px; margin-top: 0px;background-color: #D8CEF6;overflow: auto">

                </div>
                </form>
            </div>

        </div>
    </body>


</html>

