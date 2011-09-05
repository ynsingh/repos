<%-- 
    Document   : voter_home
    Created on : Jun 16, 2011, 1:27:11 AM
    Author     : faraz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List,com.myapp.struts.hbm.Election" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="../Voter/login.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
<%--
.dd-menu {
    position: relative;
    top: 0px;
    width: 70%;
    margin-left: 0px;
    background-attachment: scroll;
    background-clip: border-box;
    background-color: transparent;
    
}
.dd-menu li{
   display: inline-block;
   position: relative;
   list-style: none;
   margin-left: 25px;
   width: 100px;



}

.dd-menu li a {
    color: white;
    outline: none;
    position: relative;
    width: 100%;
    height: 100%;
    margin: 0 0;

}
.dd-menu li a:hover, .dd-menu li a.dd-path {
    background-attachment: scroll;
    background-clip: border-box;
    width: 100%;
    background-color: gray;
    opacity: 0.7;
    background-origin: padding-box;
    overflow: visible;
    color:  rgb(241,241,241);
}
a:hover, a.dd-path {
    background-attachment: scroll;
    
    
    background-color: gray;
    
    background-origin: padding-box;
    overflow: visible;
    color:  rgb(241,241,241);
}
.dd-menu li ul li a {
    
    width: 133px;
    background-color: black;
    position: relative;
    height: 15px;
    border: 2px solid black;
    
    left: inherit;
    top: -34px;
    z-index: 1000;
    overflow: visible;

}

.dd-menu li ul li a:hover, .dd-menu li ul li a.dd-path {
    background-color: gray;
    
    z-index: 1000;
    
    color:  rgb(0,0,0);
}
.dd-menu, .dd-menu ul {
padding: 0px;
margin: 0px 0px;
position: relative;
list-style-type: none;
list-style-position: inside;
}
.dd-menu li { position: relative; float: left; }
.dd-menu li a { position: relative; display: inline; float: left; text-decoration: none; }
.dd-menu li ul { width: 133px; position: relative; display: none; z-index: 1000; }
.dd-menu li ul a { }
/* Links level 1 */
.dd-menu li { margin-left: 20px; }
.dd-menu li:first-child { margin-left: 20px;width: 60px; }
.dd-menu li ul li:first-child {top:0px;left: inherit}
.dd-menu li a { width: 100%; height: 20px; padding: 0px 5px; line-height:20px; }
/* Links level 2,3,4 */
.dd-menu ul li { margin-left: 0px; padding: 0px 0px; top:0px; }
.dd-menu ul a { height: 18px; width: 100%;  padding: 9px 10px; line-height: 18px; }
/* Submenu level 1 */
.dd-menu ul { left: -10px; top: 34px; }
/* Submenu level 2,3,4 */
.dd-menu ul ul { left: 15px; top: 0; }
/* Dropdown mechanism */
.dd-menu li:hover ul ul,
.dd-menu li:hover ul ul ul,
.dd-menu li:hover ul ul ul ul { display:none; }
.dd-menu li:hover ul,
.dd-menu li li:hover ul,
.dd-menu li li li:hover ul,
.dd-menu li li li li:hover ul { display:block; }
/* Arrows level 1 */
.dd-menu a.dd-submenu-title { padding-right: 15px; }
.dd-menu a span.dd-arrow {
height: 4px;
width: 7px;
position: absolute;
right: 10px;
top: 15px;

background-position: 0 60px;
background-repeat: no-repeat;
}
.dd-menu a:hover span.dd-arrow, .dd-menu a.dd-path span.dd-arrow {

}
/* Arrows level 2,3,4 */
.dd-menu ul a span.dd-arrow {
height: 7px;
width: 4px;
position: absolute;
right: 0px;
top: 14px;

background-position: -20px 0;
background-repeat: no-repeat;
}
.dd-menu ul a.dd-path span.dd-arrow, .dd-menu ul a:hover span.dd-arrow {
background-position: 0 -60px;
}
/* Rounded corners */
.dd-menu ul {  }
.dd-menu > li.current-menu-item > a, .dd-menu > li > a:hover, .dd-menu > li > a.dd-path {

}

a{
    text-decoration: none;
}
.dd-menu li ul li a:hover, .dd-menu li ul li a.dd-path {
    width: 133px;
    color:rgb(241,241,241);
    background-color: gray;
    
}
.dd-menu a span.dd-arrow {
    background-image: url("images/arrow-down.png");
    background-position: -10px -30px;
}
.dd-menu ul a span.dd-arrow {
    background-image: url("images/arrow-right.png");
}
.dd-menu ul a.dd-path span.dd-arrow, .dd-menu ul a:hover span.dd-arrow {
    background-position: -10px -30px;
}--%>
        </style>
        

        
        <script type="text/javascript" language="javascript">

var olddoc;
var olddoc1;
var loadcount=0;
function checkElection()
{
<%
List<Election> lstelec = (List<Election>)session.getAttribute("currentelectionList");
if(lstelec!=null && !lstelec.isEmpty()){
%>
        if(loadcount==0)
        {var choice = confirm("Voting for election '<%=lstelec.get(0).getElectionName()%>' is going on. Do you want to vote now?");
        if(choice==true)
            {
                loadvoting();loadcount++;
            }}<%}%>
}
            function elections()
            {
                var divtag = document.createElement("div");
                divtag.id = "overbody";
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserWrite");
                window.scrollbars.visible = false;
                    divtag.style.width = "100%";
                divtag.style.height = "100%";
                divtag.style.top = "0px";
                divtag.style.position = "absolute";
                divtag.style.overflow = "hidden";
                divtag.style.backgroundColor = "gray";
                divtag.style.opacity = 0.97;
                var doc = document.getElementById("electionDetails1");
                if(doc.innerHTML=="") doc.innerHTML = olddoc;
                var divtag1 = document.createElement("div");
                divtag1.id = "electionDetails";
                divtag1.style.display = "block";
                divtag1.style.border = "2px solid teal";
                divtag1.style.backgroundColor = "white";
                divtag1.style.height = "100px";
                divtag1.style.width = "450px";
                divtag1.style.marginLeft = "450px";
                divtag1.style.position = "absolute";
                divtag1.style.top = "40%";
                divtag1.innerHTML = doc.innerHTML;
                divtag.appendChild(divtag1);
                document.getElementById("bod").appendChild(divtag);
                olddoc = doc.innerHTML;
                doc.innerHTML = "";
                
            }
            function deleteBod()
            {
                var child = document.getElementById("overbody");
               if(child!=null)
               document.getElementById("bod").removeChild(child);

            }
            function electionsResults()
            {
                
                var divtag = document.createElement("div");
                divtag.id = "overbody";
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserWrite");
                window.scrollbars.visible = false;
                    divtag.style.width = "100%";
                divtag.style.height = "100%";
                divtag.style.top = "0px";
                divtag.style.position = "absolute";
                divtag.style.overflow = "hidden";
                divtag.style.backgroundColor = "gray";
                divtag.style.opacity = 0.97;
                var doc = document.getElementById("electionResults2");
                if(doc.innerHTML=="") doc.innerHTML = olddoc1;
                var divtag1 = document.createElement("div");
                divtag1.id = "electionResults";
                divtag1.style.display = "block";
                divtag1.style.border = "2px solid teal";
                divtag1.style.backgroundColor = "white";
                divtag1.style.height = "100px";
                divtag1.style.width = "450px";
                divtag1.style.marginLeft = "450px";
                divtag1.style.position = "absolute";
                divtag1.style.top = "40%";
                divtag1.innerHTML = doc.innerHTML;
                divtag.appendChild(divtag1);
                document.getElementById("bod").appendChild(divtag);
                olddoc1 = doc.innerHTML;
                doc.innerHTML = "";
                
            }
            function currentElections()
            {
               
               loadElections();
            }
            function fun(index,node){
                var id = "block"+index;
                
                if(node.src.toString()=="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/images/plus.jpg")
                {
                    node.src = "<%=request.getContextPath()%>/images/minus.jpg";
                    document.getElementById(id).style.display="block";
                }
            else
                {
                    node.src = "<%=request.getContextPath()%>/images/plus.jpg";
                    document.getElementById(id).style.display="none";
                }
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

function loadvoting()
{
   <%-- alert("fff");
    alert(document.getElementById('election2').value);--%>
            if(document.getElementById('election2').selectedIndex<0){return false;}
    var electVal = document.getElementById('election2').options[document.getElementById('election2').selectedIndex].value;
   //alert(electVal);
    if(electVal==undefined) return false;
    deleteBod();
    var divtag = document.createElement("div");
                divtag.id = "overbody";
                divtag.style.width = "100%";
                divtag.style.height = "100%";
                window.scrollBy(0, 0);
                divtag.style.top = "0px";
                divtag.style.position = "absolute";
                divtag.style.overflow = "visible";
                divtag.style.backgroundColor = "gray";
                divtag.style.opacity = 0.97;
                var h1 = "495";//alert(h1);
                var htm = '<div id="electionResult1" style="display: block;opacity:1;background-color: white;border: 2px solid teal;height: 90%; width: 669px;margin-left: 25%; position: absolute;top:100px;">';
                htm+= '<div style="background-color: teal;width: 100%;position: relative">&nbsp;<span style="float: right;"><a href="#" title="Close this window" onclick="deleteBod();">[X]</a></span></div>';
                htm+='<div style="position: relative">';
                htm+='<iframe name="f1" id="f1"  src="<%=request.getContextPath()%>/election.do?election='+ electVal +'" width="665px" style="height: '+ h1 +'px" />';
                    htm+='</div></div>';
                divtag.innerHTML = htm;
                document.getElementById("bod").appendChild(divtag);
               
         
}
function loadresult()
{
   
    var electVal = document.getElementById('election3').options[document.getElementById('election3').selectedIndex].value;
   
    deleteBod();
    var divtag = document.createElement("div");
                divtag.id = "overbody";
                divtag.style.width = "100%";
                divtag.style.height = "100%";
                window.scrollBy(0, 0);
                divtag.style.top = "0px";
                divtag.style.position = "absolute";
                divtag.style.overflow = "visible";
                divtag.style.backgroundColor = "gray";
                divtag.style.opacity = 0.97;
                var h1 = "495";//alert(h1);
                var htm = '<div id="electionResult1" style="display: block;opacity:1;background-color: white;border: 2px solid teal;height: 90%; width: 669px;margin-left: 25%; position: absolute;top:100px;">';
                htm+= '<div style="background-color: teal;width: 100%;position: relative">&nbsp;<span style="float: right;"><a href="#" title="Close this window" onclick="deleteBod();">[X]</a></span></div>';
                htm+='<div style="position: relative">';
                htm+='<iframe name="f1" id="f1"  src="<%=request.getContextPath()%>/Voter/result.jsp?election='+ electVal +'" width="665px" style="height: '+ h1 +'px" />';
                    htm+='</div></div>';
                divtag.innerHTML = htm;
                document.getElementById("bod").appendChild(divtag);
                
         
}
function pausecomp(millis)
{
var date = new Date();
var curDate = null;

do { curDate = new Date(); }
while(curDate-date < millis);
}
var heigh;
function getIFRAMEdocheight() {
var def_height=404;
var i=0;


//alert(theiframe);
var theiframe = "f1";
var IFRAMEref = frames[theiframe];
//pausecomp(3000);
if (IFRAMEref)
    {//alert(IFRAMEref.document);
   //i=0; while(i<558240000){i++;}
    
    if(IFRAMEref.document!=null)
    heigh = (typeof IFRAMEref.document.body != 'undefined' &&
typeof IFRAMEref.document.body.scrollHeight != 'undefined') ?
IFRAMEref.document.body.scrollHeight :
(typeof IFRAMEref.document.height != 'undefined') ?
IFRAMEref.document.height :
def_height;alert("i="+i+" heigh="+heigh);
//var d = IFRAMEref.document.getElementById("");
}
//heigh = heigh<495?495:heigh;
var he,hei;
if(heigh>=def_height){
he = heigh + 200;
hei = heigh + 20;}
else{
    he=parseInt(heigh + 0.27*heigh,10);
    hei = parseInt(heigh + 0.07* heigh,10);}
var overheigh = document.getElementById("overbody").style.height;
<%--alert(overheigh);--%>
overheigh = overheigh.substr(0, overheigh.indexOf("px"));
overheigh = overheigh.substr(0, overheigh.indexOf("%"));
alert("Overheigh="+overheigh+" he= "+he+"window.height="+screen.height);
if(he>screen.height)
    document.getElementById("overbody").style.height = he+'px';
else
    document.getElementById("overbody").style.height = screen.height+'px';
var f1heigh = document.getElementById("f1").style.height;
//alert("f1="+f1heigh);
f1heigh = f1heigh.substr(0, f1heigh.indexOf("px"));
f1heigh = f1heigh.substr(0, f1heigh.indexOf("%"));
if(f1heigh<hei)
    document.getElementById("f1").style.height = hei+'px';
document.getElementById("electionResult1").style.height = hei+10+'px';
IFRAMEref = null;
return heigh;
}
function candiReq(pos,election) {
    //alert("index="+index+" current="+current);
   // alert(document.getElementById("position").style.display);

    var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, responseRequest);

req.open("POST","<%=request.getContextPath()%>/applyCandidature.do?position="+pos+"&election="+election, true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send();
return true;
}
function responseRequest(cartXML)
{
    var em = cartXML.getElementsByTagName("messages")[0];
var em1 = em.getElementsByTagName("message");
alert(em1[0].firstChild.nodeValue);
}
function loadElections() {
    //alert("index="+index+" current="+current);
   // alert(document.getElementById("position").style.display);

    var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, loadElection);

req.open("POST","<%=request.getContextPath()%>/getElection.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send();
return true;
}
  function loadElection(cartXML)
{
var htm="";
var curElec = document.getElementById("currentElections");
curElec.innerHTML="";

var em = cartXML.getElementsByTagName("elections")[0];
var em1 = em.getElementsByTagName("election");
//var em = cartXML.firstChild.value;
for(iii=0;iii<em1.length;iii++)
    {
        var electionId1 = em1[iii].getElementsByTagName("election_id");
        var electionName1 = em1[iii].getElementsByTagName("electionname");
        var description1 = em1[iii].getElementsByTagName("description");
        var nstart1 = em1[iii].getElementsByTagName("Nstart");
        var nend1 = em1[iii].getElementsByTagName("Nend");
        var Sstart1 = em1[iii].getElementsByTagName("Sstart");
        var Send1 = em1[iii].getElementsByTagName("Send");
        var Wstart1 = em1[iii].getElementsByTagName("Wstart");
        var Wend1 = em1[iii].getElementsByTagName("Wend");
        var Estart1 = em1[iii].getElementsByTagName("Estart");
        var Eend1 = em1[iii].getElementsByTagName("Eend");
        var posts = em1[iii].getElementsByTagName("Posts")[0];
        var post = posts.getElementsByTagName("post");
        var electionId = electionId1[0].firstChild.nodeValue;
        var electionName = electionName1[0].firstChild.nodeValue;
        var description = description1[0].firstChild.nodeValue;
        var nstart = nstart1[0].firstChild.nodeValue;
        var nend = nend1[0].firstChild.nodeValue;
        var sstart = Sstart1[0].firstChild.nodeValue;
        var send = Send1[0].firstChild.nodeValue;
        var wstart = Wstart1[0].firstChild.nodeValue;
        var wend = Wend1[0].firstChild.nodeValue;
        var estart = Estart1[0].firstChild.nodeValue;
        var eend = Eend1[0].firstChild.nodeValue;

       var doc = document.createElement("div");
       var id = "election"+iii;
       doc.id = id;
       doc.style.border = "2px solid teal";
       
       var doc1 = document.createElement("div");
       var elecId = "elecHeader"+iii;
       doc1.id = elecId;
       doc1.style.backgroundColor = "teal";
       doc1.style.color = "white";
       
       doc1.innerHTML = '<img src="<%=request.getContextPath()%>/images/plus.jpg" alt="" style="width: 13px;height: 13px;" onclick="fun('+ iii +',this)"/> '+ electionName +'<span style="margin-left: 57%;color: white">Download As:</span><span style="margin-left: 10px;color: white"><a href="#" style="color: white">pdf</a></span><span style="margin-left: 10px;color: white"><a href="#" style="color: white">xml</a></span>';
       doc.appendChild(doc1);
       var block1 = document.createElement("div");
       var blockId = "block"+iii;
       block1.id = blockId;
       block1.style.display = "none";
       var innerhtm = '<table>';
       innerhtm = innerhtm + '<tr><td>Election Name:</td><td>'+ electionName +'</td></tr>';
       innerhtm = innerhtm + '<tr><td>Description:</td><td>'+ description +'</td></tr>';
       innerhtm = innerhtm + '<tr><td>Nomination Date:</td><td>'+ nstart +'&nbsp;&nbsp;&nbsp;&nbsp; to&nbsp;&nbsp;&nbsp;&nbsp; '+nend+'</td></tr>';
       //innerhtm = innerhtm + '<tr><td>Nomination End Date:</td><td>'+ nend +'</td></tr>';
       innerhtm = innerhtm + '<tr><td>Scrutiny Date:</td><td>'+ sstart +'&nbsp;&nbsp;&nbsp;&nbsp; to&nbsp;&nbsp;&nbsp;&nbsp; '+send+'</td></tr>';
       //innerhtm = innerhtm + '<tr><td>Scrutiny End Date:</td><td>'+ send +'</td></tr>';
       innerhtm = innerhtm + '<tr><td>Nomination Withdrawal Date:</td><td>'+ wstart +'&nbsp;&nbsp;&nbsp;&nbsp; to&nbsp;&nbsp;&nbsp;&nbsp; '+wend+'</td></tr>';
       //innerhtm = innerhtm + '<tr><td>Nomination Withdrawal End Date:</td><td>'+ wend +'</td></tr>';
       innerhtm = innerhtm + '<tr><td>Election Date:</td><td>'+ estart +'&nbsp;&nbsp;&nbsp;&nbsp; to&nbsp;&nbsp;&nbsp;&nbsp; '+ eend +'</td></tr>';
       //innerhtm = innerhtm + '<tr><td>Election End Date:</td><td>'+ eend +'</td></tr>';
       innerhtm += '<tr><td colspan=2>For the Post of:</td></tr>';
       for(jj=0;jj<post.length;jj++)
           {
               var positionId1 = post[jj].getElementsByTagName("positionId");
               var positionId = positionId1[0].firstChild.nodeValue;
               var position1 = post[jj].getElementsByTagName("position");
               var position = position1[0].firstChild.nodeValue;
               var candidature1 = post[jj].getElementsByTagName("candidature");
               var candidature = candidature1[0].firstChild.nodeValue;
               innerhtm +='<tr><td>'+position+' ('+candidature+')</td><td><input type="button" value="Send Request For Candidature" onclick="candiReq('+positionId+','+electionId+');"/></td></tr>';
           }
       innerhtm += '</table>';
       block1.innerHTML = innerhtm;
       doc.appendChild(block1);
       curElec.appendChild(doc);
    }
var doc2 = document.getElementById("currentElections");
doc2.style.display = "block";

}
  function previewBallot() {
       if(document.getElementById("electionDetails").style.display=="block")
        {
            document.getElementById("ballot").style.display="block";
            document.getElementById("electionDetails").style.display="none";
        }
    var req = newXMLHttpRequest();
req.onreadystatechange = getReadyStateHandler(req, designBallot);
req.open("POST","<%=request.getContextPath()%>/getPosition.do", true);
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
//var em = cartXML.firstChild.value;
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
                divtag.style.height = document.height;
                //divtag.innerHTML ='Position Name *<br><input type="text" Id="position_name0'+iii +'" size="40px"/><br>Number of choice *<br><input type="text" Id="numberofchoice0'+ iii +'" size="40px"/><br><br><div id="candidate0'+iii+'" style="position: relative;background-color: #D8CEF6; border: 3px solid #F2F5A9;display: block;width: 595px"></div><input type="button" name="add candidate" style="" id="add0'+iii+'" onclick="create(0,'+iii+',this);" value="Add Candidate" size="50px"><br>';
                //document.getElementById("position").appendChild(divtag);
        //end of block
var positionname1 = em1[iii].getElementsByTagName("positionname");
var positionname = positionname1[0].firstChild.nodeValue;
var noofchoice1 = em1[iii].getElementsByTagName("noofchoice");
var noofchoice = noofchoice1[0].firstChild.nodeValue;
htm = '<div class="building_block" >Position: <strong>'+positionname+'</strong><br><strong>You may select up to '+noofchoice+'';
if(noofchoice>1) htm=htm + ' candidates';
else htm=htm + ' candidate';
htm = htm +' for this position</strong><table class="ballot"><tbody><tr><th style="text-align: left;">Candidate Name</th><th>Selection</th></tr>';

var ca = em1[iii].getElementsByTagName("candidate");
for(jj=0;jj<ca.length;jj++)
    {
        var candidatename1 = ca[jj].getElementsByTagName("candidatename");
        var candidatename;
            if(candidatename1[0].firstChild!=null) candidatename = candidatename1[0].firstChild.nodeValue;
            else candidatename = "";
       htm = htm +'<tr><td style="text-align: left;"><label for="entry'+iii+'">'+candidatename+'</label></td>';
if(noofchoice>1)
       {
               htm = htm +'<td><input type="checkbox" value="'+jj+'" onclick="checkCandidateLimit('+iii+')" name="entry'+iii+'" id="entry'+iii+'" ></td></tr>';
       }
       else
           {
               htm = htm +'<td><input type="radio" value="'+jj+'"  name="entry'+iii+'" id="entry'+iii+'" ></td></tr>';
           }

    }
  htm = htm + '</tbody></table></div>';
//alert("create("+jj+","+iii+",this);");
//alert(document.getElementById(idadd).attributes.onclick.value);
divtag.innerHTML =htm;
document.getElementById("ballot").appendChild(divtag);
document.getElementById("position").style.display = "none";
document.getElementById("add Position").style.display = "none";
document.getElementById("ballot").style.display="inline";
document.getElementById("previewtext").textContent = "Close Preview";
}


}
var obj;
function createul(current)
{
    if(obj!=undefined)
        {
            var par = current.parentNode;
    
    par.removeChild(obj);
        }
    var dul = document.createElement("ul");
    var htm = '<li><a href="#" style="font-size: 13px;font-weight: bold"  onclick="currentElections()">Current&nbsp;Elections</a></li>';
    
    dul.innerHTML = htm;
    var par = current.parentNode;
    par.appendChild(dul);
    obj = dul;
}

        </script>
    </head>
    <body style="margin:  0px;height: 90%; " id="bod" onload="checkElection();">
        <div id="main" style="width: 100%;height: 100%;margin: 0px;">
            <div id="topstrip" style="margin: 0px; width: 100%;height: 20px;background-color: black;">
                <div style="float: right;color: white;width: 50px;"><a href="<%=request.getContextPath()%>/logout.do" style="color: white">Logout</a></div>
                    <div style="float: right;color: white;width: 150px;">Welcome <%=session.getAttribute("username")%></div>
                    <div style="">
                    <ul class="dd-menu" >
                        <li class="current-menu-item">
                            <a href="#" style="font-size: 13px;font-weight: bold">Home</a>
                        </li>
                        <%--<li><a href="#"  onmouseover="createul(this);">Current&nbsp;Elections</a>
                            
                        </li>--%>
                        <li><a href="#" style="font-size: 13px;font-weight: bold"  onclick="currentElections()">Current&nbsp;Elections</a></li>
                        <li ><a href="#" style="font-size: 13px;font-weight: bold" onclick="elections();">Voting&nbsp;Process</a></li>
                        <li><a href="#" style="font-size: 13px;font-weight: bold" onclick="electionsResults()">Election&nbsp;Results</a>
                           <%-- <ul>
                                <li><a href="#" style="font-size: 13px;font-weight: bold;z-index:1000" onclick="electionsResults()">Election&nbsp;Results</a></li>
                                <li><a href="#" style="font-size: 13px;font-weight: bold;z-index:1000" onclick="electionsResults()">Election&nbsp;Results</a></li>
                            </ul>--%>
                        </li>
                    </ul>

                </div>
            </div>
            <div id="header" style="width: 100%;height: 5%;margin: 0px;">
                
                
            </div>
            <div id="middle" style="width: 100%;height: 70%; position: relative;margin-top: 30px;">
                <div id="ballot"></div>
                
                <div id="electionDetails1" style="border: 2px solid teal;background-color: white;height: 100px; width: 450px;margin-left: 450px; position: absolute;top: 40%;display: none">
                <div style="background-color: teal;width: 100%;position: relative">&nbsp;<span style="float: right;"><a href="#" title="Close this window" onclick="deleteBod();">[X]</a></span></div>
                <div style="position: relative;">
                   <html:form action="/election" styleId="election1" target="f1"><table style="width:100%"><tr><td style="text-align:center">Select an Election:
                                   <html:select property="election" styleId="election2"  style="width: 150px">
                                        <html:options collection="currentelectionList" property="id.electionId" labelProperty="electionName" />
                       </html:select></td></tr><tr><td style="text-align:center">
                       <input value="Select" type="button" onclick="loadvoting();" id="electionsubmit"/>

                                           </td></tr></table>
                   </html:form></div></div>



                <div id="electionResults2" style="display: none">
                    <div style="background-color: teal;width: 100%;position: relative">&nbsp;<span style="float: right;"><a href="#" title="Close this window" onclick="deleteBod();">[X]</a></span></div>
                <div style="position: relative;">
                    <html:form action="/electionResult" styleId="f2" target="f1">
                        Select an Election: <html:select property="election" styleId="election3" >
              <html:options collection="ClosedelectionList" property="id.electionId" labelProperty="electionName" />
                        </html:select><input type="button" value="Election Result" onclick="loadresult();"  id="electionsubmit"/>
                    </html:form>
                </div></div>
                <%--<div id="middleleft" style="display: none;position: relative; width: 30%;float: left" >&nbsp;</div>
                <div id="middleright" style="display: block;position: relative; width: 30%;float: right" >&nbsp;</div>--%>
                <div id="currentElections" style="display: none;position: absolute;margin-left: 250px; width: 40%;float: left;">

                    
                </div>
            </div>
            <div id="footer" style="width: 100%;height: 15%; position: relative;"></div>
        </div>
    </body>
</html>
