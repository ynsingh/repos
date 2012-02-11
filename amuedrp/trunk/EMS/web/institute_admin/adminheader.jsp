<%-- 

    Document   : institute_admin_home
    Created on : Mar 11, 2011, 7:22:26 PM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page import="java.sql.*,java.util.List"%>
<%@page import="java.util.*,java.io.*,com.myapp.struts.hbm.Election"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>


 <!-- code for multilingual view -->

 <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);


    %>


     <%
try{
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>
    <script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }
String user=(String)session.getAttribute("username");
 String contextPath = request.getContextPath();
%>



<html>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Election Management System : Institute_Admin</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/chrometheme/chromestyle.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/chromejs/chrome.js"/>
<script type="text/javascript">



        //Drop Down Menu Code

<%--var urls1 = new buildArray(
"<%=request.getContextPath()%>/admin/staffupdate.do",
"<%=request.getContextPath()%>/admin/changeuserpassword");


function buildArray()
{
  var a = buildArray.arguments;

  for( var i=0; i<a.length; i++ )
  {
    this[i] = a[i];
  }
  this.length = a.length;
}


function go ( which, num, win )
{
  var n = which.selectedIndex;

  if ( n != 0 )
  {
    var url = eval ( "urls" + num + "[n]" );
    if ( win )
    {
      openWindow ( url );
    }
    else
    {
      location.href = url;


    }
  }
}--%>

function openWindow(url)
{
  popupWin = window.open(url,"remote","width=100;height=350;dependent=true;");
}



</script>
<script type="text/javascript" language="javascript">
function pageload(loc)
            {
              // alert("hey");

               document.getElementById("page").innerHTML ="";
               var loc1="";
               if(loc==1) loc1="<%=request.getContextPath()%>/create_manager.do";
               if(loc==2) loc1="<%=request.getContextPath()%>/institute_admin/search_block_election_manager.jsp";
               if(loc==3) loc1="<%=request.getContextPath()%>/institute_admin/search_update_election_manager.jsp";
               if(loc==4) loc1="<%=request.getContextPath()%>/view_managers.do";
               if(loc==5) loc1="<%=request.getContextPath()%>admin_account.do";
               if(loc==1)
               document.getElementById("page").innerHTML = "<iframe name=\"page\" id=\"pagetab\" height=\"590px\" width=\"100%\" src=\"/"+loc1+"\"/>";
              else
                  document.getElementById("page").innerHTML = "<iframe name=\"page\" id=\"pagetab\" height=\"230px\" width=\"100%\" src=\"/"+loc1+"\"/>";
               return true;
            }
function change(){

if(top.location=="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/login.do")
    {
        //alert(top.location);
        top.location="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/instituteadmin.do";
    }
    }
</script>
<style type="text/Stylesheet">
    #ddmenu a{ text-decoration:none; }
#ddmenu a:hover{ background-color:#FFFF95;

    </style>


<script type="text/javascript" language="javascript">
var olddoc="";
var olddoc1="";
var loadcount;
var election_id;

function castVote(id) {
    election_id=id;

   // alert(document.getElementById("position").style.display);

    var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, updateCast);

req.open("POST","<%=request.getContextPath()%>/VoteCast.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("id="+election_id);
return true;
}
function updateCast(cartXML)
{
   var em = cartXML.getElementsByTagName("cast")[0];
var em1 = em.getElementsByTagName("message");
var text=em1[0].firstChild.nodeValue;
if(text=="Voter Already voted for this election!"){
    alert(text);
}
    else
{windload();}
}







function checkElection()
{


<%
List<Election> lstelec = (List<Election>)session.getAttribute("electionList");
if(lstelec!=null && !lstelec.isEmpty()){
%>
     <%--   if(loadcount==0 || loadcount==null)
        {var choice = confirm("Voting for election '<%=lstelec.get(0).getElectionName()%>' is going on. Do you want to vote now?");
        if(choice==true)
            {
                windload();
            }}--%>
  var divtag = document.createElement("div");
                divtag.id = "overbody";
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserWrite");
                window.scrollbars.visible = false;
                    divtag.style.width = "100%";
                divtag.style.height = "100%";
                divtag.style.top = "0px";
                divtag.style.zIndex = "1500";
                divtag.style.position = "absolute";
                divtag.style.overflow = "hidden";
                divtag.style.backgroundColor = "gray";
                divtag.style.opacity = 0.97;
                var doc = document.getElementById("electionResults3");
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


<%}%>

}



function windload()
{
     election_id=document.getElementById("election4").value;


    deleteBod();
    var divtag = document.createElement("div");
                divtag.id = "electionPass";
                window.scrollbars.visible = false;
                divtag.style.width = "100%";
                divtag.style.height = "100%";
                divtag.style.top = "0px";
                divtag.style.position = "absolute";
                divtag.style.overflow = "hidden";
                divtag.style.backgroundColor = "gray";
                divtag.style.opacity = 0.97;
                var divtag1 = document.createElement("div");
                divtag1.id = "block2";
                divtag1.style.display = "block";
                divtag1.style.border = "2px solid teal";
                divtag1.style.backgroundColor = "white";
                divtag1.style.height = "100px";
                divtag1.style.width = "450px";
                divtag1.style.marginLeft = "450px";
                divtag1.style.position = "absolute";
                divtag1.style.top = "40%";
                divtag1.innerHTML = '<table><tr><td colspan="2">Enter Valid Password</td></tr><tr><td><input type="password" id="pass" onblur="getPass();"/></td></tr></table> ';
                divtag.appendChild(divtag1);
                document.getElementById("bod").appendChild(divtag);
}
function getPass()
{
    var val = document.getElementById("pass").value;
    if(val!=undefined && val!="")
        checkPassword(val,election_id);
    var child = document.getElementById("electionPass");
    document.getElementById("bod").removeChild(child);
}
<%
List<Election> lstcurelection = (List<Election>)session.getAttribute("currentelectionList");
                        List<Election> lstelection = (List<Election>)session.getAttribute("electionList");
                        List<Election> lstclosedelection = (List<Election>)session.getAttribute("ClosedelectionList");

%>

<%--function viewelections()
            {

                 <%if(lstelection.isEmpty()==true && lstelection==null){%>
                         alert("No Election is in Process");
                         return true;
                         <%}%>

                var divtag = document.createElement("div");
                divtag.id = "overbody";
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserWrite");
                windlow.scrollbars.visible = false;
                divtag.style.width = "100%";
                divtag.style.height = "100%";
                divtag.style.top = "0px";
                divtag.style.zIndex = "1500";
                divtag.style.position = "absolute";
                divtag.style.overflow = "hidden";
                divtag.style.backgroundColor = "gray";
                divtag.style.opacity = 0.97;

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
                var doc = document.getElementById("electionView1");
                if(doc.innerHTML=="") doc.innerHTML = olddoc;
                var divtag1 = document.createElement("div");
                var htm = '';

                divtag1.innerHTML = doc.innerHTML;
                divtag.appendChild(divtag1);
                document.getElementById("bod").appendChild(divtag);
                olddoc = doc.innerHTML;
                doc.innerHTML = "";

            }--%>
            function elections()
            {
                var divtag = document.createElement("div");
                divtag.id = "overbody";
                //netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserWrite");
                window.scrollbars.visible = false;
                    divtag.style.width = "100%";
                divtag.style.height = "100%";
                divtag.style.zIndex = "1500";
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
                divtag.style.zIndex = "1500";
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
function loadelectionview()
{

            if(document.getElementById('electionv2').selectedIndex<0){return false;}
    var electVal = document.getElementById('electionv2').options[document.getElementById('electionv2').selectedIndex].value;
   //alert(electVal);
    if(electVal==undefined) return false;
    deleteBod();
    var divtag = document.createElement("div");
                divtag.id = "overbody";
                divtag.style.width = "100%";
                divtag.style.height = "100%";
                window.scrollBy(0, 0);
                divtag.style.zIndex="1500";
                divtag.style.top = "0px";
                divtag.style.position = "absolute";
                divtag.style.overflow = "visible";
                divtag.style.backgroundColor = "gray";
                divtag.style.opacity = 0.97;
                var h1 = "495";//alert(h1);
                var htm = '<div id="electionResult1" style="display: block;opacity:1;background-color: white;border: 2px solid teal;height: 90%; width: 669px;margin-left: 25%; position: absolute;top:100px;">';
                htm+= '<div style="background-color: teal;width: 100%;position: relative">&nbsp;<span style="float: right;"><a href="#" title="Close this window" onclick="deleteBod();">[X]</a></span></div>';
                htm+='<div style="position: relative">';
                htm+='<iframe name="f1" id="f1"  src="<%=request.getContextPath()%>/viewelection.do?election='+ electVal +'" width="665px" style="height: '+ h1 +'px" />';
                    htm+='</div></div>';
                divtag.innerHTML = htm;
                document.getElementById("bod").appendChild(divtag);


}
function loadvoting()
{

alert("sdsdg");
           // if(document.getElementById('election3').selectedIndex<0){return false;}
    var electVal = election_id;//document.getElementById('election4').options[document.getElementById('election4').selectedIndex].value;

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
var p,e;
function candiReq(pos,election) {
    //alert("index="+index+" current="+current);
   // alert(document.getElementById("position").style.display);
p=pos;
e=election;
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
var t=em1[0].firstChild.nodeValue;


location.href="<%=request.getContextPath()%>/applyCandidature.do?position="+p+"&election="+e+"&report=true";
alert(t);
}
function checkPassword(pass,election) {
    //alert("index="+index+" current="+current);
   // alert(document.getElementById("position").style.display);

    var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, confirmPass);

req.open("POST","<%=request.getContextPath()%>/checkPass.do?election="+election+"&pass="+pass, true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send();
return true;
}
  function confirmPass(cartXML)
{
var htm="";
//var curElec = document.getElementById("currentElections");
//curElec.innerHTML="";

var em = cartXML.getElementsByTagName("messages")[0];
var em1 = em.getElementsByTagName("message");
var em2 = em1[0].firstChild.nodeValue;

if(em2=="pass")
 {
     loadvoting();
     loadcount=1;
}
else
    alert(em2);
}

function loadElections() {

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

       doc1.innerHTML = '<img src="<%=request.getContextPath()%>/images/plus.jpg" alt="" style="width: 13px;height: 13px;" onclick="fun('+ iii +',this)"/> '+ electionName +'<span style="margin-left: 57%;color: white">Download As:</span><span style="margin-left: 10px;color: white"><a href="<%=request.getContextPath()%>/detailreport.do?getElection='+electionId+'" style="color: white">pdf</a></span><span style="margin-left: 10px;color: white"><a href="#" style="color: white">xml</a></span>';
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
              <%-- <%
               Calendar cal = Calendar.getInstance();
               Date d = cal.getTime();
               %>--%>
                          innerhtm +='<tr><td>'+position+' ('+candidature+')</td>';
                         <%-- if(Date.parse(nstart)<=Date.parse(<%=d%>) && Date.parse(nend)>Date.parse(<%=d%>))--%>
                      // {

                                  innerhtm +='<td><input type="button" value="Send Request For Candidature" onclick="candiReq('+positionId+','+electionId.toString()+');"/></td>';

                      //}
                       innerhtm +='</tr>';
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
var instruct = em1[iii].getElementsByTagName("instruction");
var instrucT = instruct[0].firstChild.nodeValue;
var instruct1;
var instruct2;
if(instrucT!=undefined)
    if(instrucT.lastIndexOf(noofchoice)!=-1)
        {instruct1 = instrucT.substr(0, instrucT.lastIndexOf(noofchoice)-1);
        instruct2 = instrucT.substr(instrucT.lastIndexOf(noofchoice)+1,instrucT.length);}
htm = '<div class="building_block" ><%=resource.getString("positionname")%>: <strong>'+positionname+'</strong><br><strong >'+ instruct1 +' <span style="color: red">'+noofchoice+'</span>'+ instruct2 +'</strong>';
//htm = '<div class="building_block" >Position: <strong>'+positionname+'</strong><br><strong>You may select up to '+noofchoice+'';
<%--if(noofchoice>1) htm=htm + ' candidates';
else htm=htm + ' candidate';--%>
htm = htm +'<table class="ballot"><tbody><tr><th style="text-align: left;"><%=resource.getString("candidatename")%></th><th>Selection</th></tr>';

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
<%
String instituteName=(String)session.getAttribute("institute_name");

 String role=(String)session.getAttribute("login_role");

%>
</script>



    </head>



    <body onload="change()"  id="bod" >
            <div>   <table border="0" width="100%" dir="<%=rtl%>" >
                              <tr>

                                  <td width="20%" valign="bottom" height="50px" dir="<%=rtl%>">



                                  <img src="<%=request.getContextPath()%>/images/logo.bmp" alt="banner space"  border="0" align="top" id="Image1">

                                  </td>
                                  <td align="center"><span  dir="<%=rtl%>"><br>
                                          <%=session.getAttribute("institute_name")%><%=session.getAttribute("login_role")%><br/>
                                          Cheif Election Officer</span></td>
                                  <td width="40%"  valign="top" dir="<%=rtl%>" style="font:8pt Verdana;text-decoration:none;">
<table width="100%" border="0px"><tr>
<td align="right">
<html:img src="/EMS/images/logo.png" width="150px" height="60px"  />
</td>
<td align="right"  valign="top"><%=resource.getString("login.hello")%>,&nbsp;

<script type="text/javascript" language="javascript">
document.write("<span " );
document.write('style="height:10px;border:0px solid black;font:bold 11px Verdana;"');
document.write(' onclick="toggle_menu(1);');
document.write('event.cancelBubble=1" ><span style="cursor:hand;">');
document.write('<%=user%> <img width=10 height=10 src="<%=request.getContextPath()%>/images/down.gif"></span>');
document.write('<div id="ddmenu" style="');
document.write('height:45px;border:0px solid black;background-color:white;color:black;text-decoration:none;text-align: right;padding-right:2px');
document.write('overflow-y:scroll;visibility:hidden;">');
add_item("<%=resource.getString("view_profile")%>","<%=request.getContextPath()%>/admin_account.do");
add_item("<%=resource.getString("login.managesuperadminaccount.changepassword")%>","<%=request.getContextPath()%>/admin_password.do");
function add_item(linkname,dest){
  document.write('<a  href="'+dest+'"  onclick="return pageload(3);">'+linkname+'</a><br>');
}

function toggle_menu(state){
var theMenu=document.getElementById("ddmenu").style;
if (state==0) {
  theMenu.visibility="hidden"; }
else {
  theMenu.visibility = (theMenu.visibility=="hidden") ? "visible" : "hidden";
}
}


document.onclick= function() {toggle_menu(0); }
document.write('</div></span>');


</script>



</td><td align="left" valign="top" width="20%">
| &nbsp;<a href="<%=contextPath%>/logout.do" style="text-decoration: none;color:brown" dir="<%=rtl%>">&nbsp;<%=resource.getString("login.signout")%></a>

</td></tr>
</table>

                     </td>
                              </tr>
                      </table>
        </div>


        <div>
        <ul class="dd-menu">
        <li>
            <a href="<%=contextPath%>/institute_admin/institute_admin_home.jsp"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
                <b style="color:white" dir="<%=rtl%>"> &nbsp;&nbsp;<%=resource.getString("login.home")%></b>
            </a>
        </li>
      <li>
          <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
              <b><%=resource.getString("manage_election_manager")%></b>
          </a>
            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/create_manager.do"  onclick="return pageload(1);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("createelectionmanager")%></a>
                </li>
               <%-- <li>
                    <a href="<%=request.getContextPath()%>/institute_admin/search_update_election_manager.jsp"   onclick="return pageload(3);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("updateelectionmanager")%></a>
                </li>--%>
                <%--<li>
                    <a href="<%=request.getContextPath()%>/delete_managers.do" target="page" onclick="return pageload(2);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Delete Election Manager<%=resource.getString("deleteelectionmanager")%></a>
                </li>--%>
               <%-- <li>
                    <a href="<%=request.getContextPath()%>/institute_admin/search_block_election_manager.jsp"  onclick="return pageload(2);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("blockmanager")%></a>
                </li>--%>
            </ul>
        </li>
        <li>
            <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>"><b><%=resource.getString("view_electionmanager")%></b></a>
            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/view_managers.do?status=B"   onclick="return pageload(4);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("view_blocked")%><%--<%=resource.getString("Viewmanagerdetails")%>--%></a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/view_managers.do?status=A"  onclick="return pageload(4);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("view_active")%><%--<%=resource.getString("Viewmanagerdetails")%>--%></a>
                </li>
               <%-- <li>
                    <a href="<%=request.getContextPath()%>/view_managers.do"   onclick="return pageload(4);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("view_all")%><%=resource.getString("Viewmanagerdetails")%></a>
                </li>--%>
            </ul>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/institute_admin/search_election_details.jsp"  onclick="return pageload(4);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><b style="color:white" dir="<%=rtl%>"><%=resource.getString("view_electiondetails")%></b><%--<%=resource.getString("view_electiondetails")%>--%></a>
                </li>
 <li>
                <a href="<%=request.getContextPath()%>/election_manager/search_voter.jsp"  onclick="return pageload(4);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><b style="color:white" dir="<%=rtl%>">View All Voters</b><%--<%=resource.getString("view_electiondetails")%>--%></a>
                </li>
                 <li>
                    <a href="<%=request.getContextPath()%>/election_manager/block_voter.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Block Voter</a>
                </li>
                <li>
                <a href="<%=request.getContextPath()%>/election_manager/search_candidate.jsp"  onclick="return pageload(4);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><b style="color:white" dir="<%=rtl%>">View All Candidate</b><%--<%=resource.getString("view_electiondetails")%>--%></a>
                </li>
                <li>
                <a href="<%=request.getContextPath()%>/institute_admin/search_manager.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><b style="color:white" dir="<%=rtl%>">Migrate EM Role</b></a>
                </li>
                  <li>
                      <a href="<%=request.getContextPath()%>/reset_password.jsp"    onclick="return pageload(4);" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><b style="color:white" dir="<%=rtl%>"> Reset Password</b><%--<%=resource.getString("view_electiondetails")%>--%></a>
                </li>
                <li>

          <a href="<%=request.getContextPath()%>/election_manager/SetMailBody.jsp" style="font-size: 13px;text-decoration: none;" > Set Mail Body</a>

        </li>
                <%--<li>
                 <%if(lstelection!=null && !lstelection.isEmpty()){%>
                        <a href="#" style="font-size: 13px;text-decoration: none;" onclick="elections();" onclick="checkElection();"><%=resource.getString("votingprocess")%></a>
                        <%}
                        if(lstclosedelection!=null && !lstclosedelection.isEmpty()){%>
                        <a href="#" style="font-size: 13px;text-decoration: none;" onclick="electionsResults()"><%=resource.getString("electionresults")%></a>
                            <%}%>
                </li>--%>
</ul>
                 <%--<div id="middle" style="width: 100%;height: 70%;position: relative;margin-top: 30px;">
                <div id="ballot"></div>
     <!--Show Vote -->
                    <%if(lstelec!=null && !lstelec.isEmpty()){%>
                <div id="electionResults3" style="display: none">
                    <div style="background-color: teal;width: 100%;position: relative">&nbsp;<span style="float: right;"><a href="#" title="Close this window" onclick="deleteBod();">[X]</a></span></div>
                <div style="position: relative;">
                    <html:form action="/electionResult" styleId="f2" target="f1">
                        Select an Election: <html:select property="election" styleId="election4" >
              <html:options collection="electionList" property="id.electionId" labelProperty="electionName" />
                        </html:select><input type="button" value="Vote Now" onclick="castVote(document.getElementById('election4').value);"  id="electionsubmit"/>
                    </html:form>
                </div></div> <%}%>


                   <%if(lstclosedelection!=null && !lstclosedelection.isEmpty()){%>
                <div id="electionResults2" style="display: none">
                    <div style="background-color: teal;width: 100%;position: relative">&nbsp;<span style="float: right;"><a href="#" title="Close this window" onclick="deleteBod();">[X]</a></span></div>
                <div style="position: relative;">
                    <html:form action="/electionResult" styleId="f2" target="f1">
                        Select an Election: <html:select property="election" styleId="election3" >
              <html:options collection="ClosedelectionList" property="id.electionId" labelProperty="electionName" />
                        </html:select><input type="button" value="Election Result" onclick="loadresult();"  id="electionsubmit"/>
                    </html:form>
                </div></div> <%}%>
                 </div>--%>
            <%--<table border="0" width="100%" bgcolor="#7697BC" style="font-family: arial;font-weight: bold;font-size:13px" dir="<%=rtl%>">
                 <tr>
                     <td align="left" dir="<%=rtl%>" ><a href="<%=request.getContextPath()%>/create_manager.do" target="page" onclick="return pageload(1);" style="text-decoration:none; color: white;font-size: 13px"><%=resource.getString("createelectionmanager")%></a>
                         &nbsp;<font color="white">|</font>&nbsp;
                  <a href="<%=request.getContextPath()%>/block_managers.do" target="page" onclick="return pageload(2);" style="text-decoration:none; color: white;font-size: 13px"><%=resource.getString("blockmanager")%></a>
                  &nbsp;<font color="white">|</font>&nbsp;
                    <a href="<%=request.getContextPath()%>/update_managers.do"  target="page" onclick="return pageload(3);" style="text-decoration:none; color: white;font-size: 13px"><%=resource.getString("updateelectionmanager")%></a>
                    &nbsp;<font color="white">|</font>&nbsp;
                    <a href="<%=request.getContextPath()%>/view_managers.do"  target="page" onclick="return pageload(4);" style="text-decoration:none; color: white;font-size: 13px"><%=resource.getString("Viewmanagerdetails")%></a>

                     </td>

                </tr>
        </table>--%></div>
            <%String msgmail=(String)request.getAttribute("msgmail");%>
<%if(msgmail!=null){%><%=msgmail %><%}%>
    </body></html>