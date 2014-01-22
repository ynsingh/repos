<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>
    
    parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*,com.myapp.struts.hbm.Election"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
 <%@ page import="java.util.*,java.lang.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Election Management System</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";
    boolean page=true;
    String align="left";
%>

<%--<%
List rst =(List)session.getAttribute("resultset");
int count=(Integer)session.getAttribute("count");
 int i=1;
%>--%>

<script type="text/javascript" language="javascript">
    if(this.opener!=null)
    this.opener.close();
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>



<script language="javascript" type="text/javascript">

            function check1()
            {
                if(document.getElementById('electionid').value=="")
                {
                    alert("Enter Election Id...");
                    document.getElementById('electionid').focus();
                    return false;
                }
            }

</script>

</head>



<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/chrometheme/chromestyle.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/chromejs/chrome.js"/>

            <script language="javascript" type="text/javascript">
            function pageload(loc)
            {
             // alert("hey");

               document.getElementById("page").innerHTML ="";
               var loc1="";
               if(loc==1) loc1="<%=request.getContextPath()%>/election_manager/enterelectionid.jsp";
               if(loc==2) loc1="<%=request.getContextPath()%>/election_manager/createballot.jsp";
               if(loc==3) loc1="<%=request.getContextPath()%>/update_managers.do";
               if(loc==4) loc1="<%=request.getContextPath()%>/view_managers.do";
               if(loc==5) loc1="<%=request.getContextPath()%>admin_account.do";

               document.getElementById("page").innerHTML = "<iframe name=\"page\" id=\"pagetab\" height=\"800px\" width=\"100%\" src=\"/"+loc1+"\"/>";

               return true;
            }
function change(){

if(top.location=="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/login.do")
    {
        //alert(top.location);
        top.location="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/election_manager/login.jsp";
    }}





        </script>






<%
try{
locale1=(String)session.getAttribute("locale");
sessionId = session.getId().toString();
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
String user=(String)session.getAttribute("username");
String instituteName=(String)session.getAttribute("institute_name");
String insti=(String)session.getAttribute("insti");
 String contextPath = request.getContextPath();
 String role=(String)session.getAttribute("login_role");
    %>
    
 <%String msg=(String)request.getAttribute("msg1"); %>
 <%String msg1=(String)request.getAttribute("msg2"); 
 if(msg1!=null){%>
<script>alert("<%=msg1%>");
 window.location="<%=request.getContextPath()%>/election_manager/Election_List.jsp";
</script>
<%}%>
    <%
  List<Election> lstclosedelection = (List<Election>)session.getAttribute("ClosedelectionList");
%>

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
                        lstclosedelection = (List<Election>)session.getAttribute("ClosedelectionList");

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
instituteName=(String)session.getAttribute("institute_name");

role=(String)session.getAttribute("login_role");

%>
</script>

 <body  style=" background-image: url('/EMS/images/paperbg.gif');margin:0px " id="bod">

    <table width="100%" align="center" style="padding: 0px 0px 0px 0px;height:90px;" dir="<%=rtl%>" >
           <tr style="background-color: #425C83;;color:white;font-size: 12px"><td width="70%"><b>Login Details : Institute Name : &nbsp;<%=insti%>/Election Officer Module</b></td><td align="right">
  <span style="font-family:arial;color:white;font-size:12px;" dir="<%=rtl%>"><b dir="<%=rtl%>">&nbsp;<a href="<%=request.getContextPath()%>/change_password.do" style="text-decoration: none;color:white" dir="<%=rtl%>">&nbsp;Change Password</a> &nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/manager_details.do" style="text-decoration: none;color:white" dir="<%=rtl%>">&nbsp;View Profile</a>&nbsp;|&nbsp;<%=resource.getString("login.hello")%> [<%=user%>]&nbsp;|<a href="<%=contextPath%>/logout.do" style="text-decoration: none;color:white" dir="<%=rtl%>">&nbsp;<%=resource.getString("login.signout")%></a></b></span>&nbsp;&nbsp;
     </td></tr>

            <tr style=" background-image: url('/EMS/images/header.jpg');height: 100px">
    <td  valign="top" colspan="2" width="100%" align="center">
        <table  align="center" width="100%"  dir="<%=rtl%>">
            <tr><td width="70%"  valign="bottom"  align="<%=align%>">
                            &nbsp;&nbsp;    <span style="font-weight: bold;font-size: 35px;font-family:Gill, Helvetica, sans-serif;color:white" >Election</span><span style="color:white;font-weight: bold;font-size: 35px;font-family:Gill, Helvetica, sans-serif;" >MS</span>



                </td><td align="center" > <img src="<%=request.getContextPath()%>/images/logo.png" alt="No Image"  border="0" align="center" id="Image1" style="" height="80px" width="120"><br/>

                            </td></tr>
             <tr><td>
                    <div style="background-color: white;color:blue;font-size: 14px;border:double 1px black;font-family:Gill, Helvetica, sans-serif" >
&nbsp;<%=resource.getString("login.message.logo.under")%>&nbsp;

</div>
                </td>
                <td align="right">
                    <div style="background-color: white;color:blue;font-size: 14px;border:double 1px white;font-family:Gill, Helvetica, sans-serif" >


</div>
                </td></tr>
        </table>
    </td></tr>
            
            <tr>
                <td colspan="2">
                    <div>
        <ul class="dd-menu">
        <li>
            <a href="<%=contextPath%>/electionmanager.do"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
                <b style="color:white" dir="<%=rtl%>"> &nbsp;&nbsp;<%=resource.getString("login.home")%></b>
            </a>
        </li>
      <li>
          <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>">
              <b><%=resource.getString("manage_elction")%></b>
          </a>

            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/election_manager/election_add.jsp" onclick="<%--return switchMain()--%>"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("create_elction")%><%----%></a>
                </li>

                <%-- <li>
                    <a href="<%=request.getContextPath()%>/election_manager/Delete_Election.jsp" onclick="return switchMain()"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Delete Election</a>
                </li>--%>
               <li>
                   
                    <a href="<%=contextPath%>/electionmanager.do?bt=del<%--<%=request.getContextPath()%>/election_manager/Delete_Election.jsp--%>"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Delete Election</a>
              
               </li>
            </ul>
        </li>
        <li>
            <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>"><b><%=resource.getString("manage_voter")%></b></a>
            <ul>
                <li>
                    <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("add")%></a>

                    <ul>
                    <li>
                        <a href="<%=contextPath%>/addVoter.do"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("register_newvoter")%></a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/registerfrmpending.do"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("registerform_pending")%><%----%></a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/Voter/cat_data_import_read.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Import Data</a>
                    </li>
                     
                </ul>
                </li>

                <li>
                    <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("view")%></a>
                    <ul>
                        <li>
                            <a href="<%=contextPath%>/election_manager/search_voter.jsp?status=A"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%--<%=resource.getString("active")%>--%>Registered</a>
                        </li>
                        <li>
                            <a href="<%=contextPath%>/election_manager/search_voter.jsp?status=B"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Block From Election</a>
                        </li>
                         <li>
                            <a href="<%=contextPath%>/election_manager/search_voter.jsp?status=BL"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Block From Login</a>
                        </li>

                        <li>
                            <a href="<%=contextPath%>/election_manager/search_voter.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("viewall")%></a>
                        </li>
                    </ul>
                </li>

                <li>
                        <a href="<%=contextPath%>/election_manager/search_voter.jsp?status=D"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("delete")%></a>
                    </li>
                    
                <li>
                    <a href="<%=request.getContextPath()%>/election_manager/block_voter.jsp?status=A"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Block Voter Frm Election</a>
                </li>
                <li>
                            <a href="<%=contextPath%>/election_manager/block_voter_1.jsp?status=A"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Block From Login</a>
                 </li>
                 <li>
                    <a href="<%=request.getContextPath()%>/election_manager/search_voter1.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Update Voter Details</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/election_manager/voter.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Set Voter List</a>
                </li>
                     <li>
                    <a href="<%=request.getContextPath()%>/election_manager/voter1.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Set Login Pass & Key</a>
                </li>
		<li>
                    <a href="<%=request.getContextPath()%>/election_manager/SetVoterMail.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Set Voter Alternate Mail</a>
                </li>
<li>
                    <a href="<%=request.getContextPath()%>/election_manager/voter2.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Set Voter & Send Link</a>
                </li>
            </ul>
        </li>
            <li>
                <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><b><%=resource.getString("manage_candi_reqst")%></b></a>
                <ul>
                        <li>
                            <a href="<%=request.getContextPath()%>/election_manager/search_candidate.jsp?status=NR"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("accept/reject")%></a>
                        </li>
                    
                </ul>
           </li>
           <li>
            <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>"><b><%=resource.getString("manage_candi")%></b></a>
            <ul>
                <li>
                    <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("view")%></a>
                    <ul>
                        
                        <li>
                            <a href="<%=request.getContextPath()%>/election_manager/search_candidate.jsp?status=A"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("accepted")%></a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/election_manager/search_candidate.jsp?status=R"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("rejected")%></a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/election_manager/search_candidate.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("view_all")%></a>
                        </li>
                    </ul>
                </li>
               <%-- <li>
                    <a href="<%=request.getContextPath()%>/election_manager/search_candidate.jsp?status=U" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("Update")%></a>

                </li>--%>
               <li>
                            <a href="<%=request.getContextPath()%>/election_manager/search_candidate.jsp?status=D"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("delete")%></a>
               </li>
               <li>
                            <a href="<%=request.getContextPath()%>/election_manager/search_candidate.jsp?status=U"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Update</a>
               </li>
                <li>
                    <a href="<%=request.getContextPath()%>/Candidate/listelection.jsp" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>"><%=resource.getString("generate_report")%></a>
                    <ul>
                     <%-- <li>
                            <a href="<%=request.getContextPath()%>/Candidate/listelection.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><%=resource.getString("currentelection")%></a>
                         </li>--%>
                    </ul>
                </li>
                 <li>
                            <a href="<%=request.getContextPath()%>/Candidate/cat_data_import_read.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px">Import Candidate Data</a>
                 </li>
 
            </ul>
        </li>
          <li>

        <a  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  href="<%=request.getContextPath()%>/election_manager/SetMailBody1.jsp" > <b>Set Mail Body</b></a>

        </li>
       

        <li>
             <a href="<%=request.getContextPath()%>/reset_password.jsp"   style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"><b>Reset Password</b></a>
        </li>
         <li>
            <a href="#" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px"  dir="<%=rtl%>"><b>Result Computing</b></a>
            <ul>
                <li>
          <%if(lstclosedelection!=null && !lstclosedelection.isEmpty()){%>
          <a href="<%=request.getContextPath()%>/election_manager/electionclosed.jsp" style="font-size: 13px;text-decoration: none;" >Postal Vote Counting</a>
                            <%}%>
        </li>

                 <li>
          <%if(lstclosedelection!=null && !lstclosedelection.isEmpty()){%>
          <a href="<%=request.getContextPath()%>/election_manager/agmvote.jsp" style="font-size: 13px;text-decoration: none;" >AGM  Vote Counting</a>
                            <%}%>
        </li>



            </ul>
        </li>

</ul>

            </div>
                    
                </td></tr>

            <tr><td colspan="2">
                    <span><%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%></span>
<%String msgmail=(String)request.getAttribute("msgmail");%>
<%if(msgmail!=null){%><%=msgmail %><%}%>
                </td></tr>
        </table>
 

</body>

</html>

<script>
    function getStyle()
   {
      var temp = document.getElementById("main").style.visibility;

      return temp;
   }

 function switchMain()
  {

      var current = getStyle();


      if( current == "hidden" )
       {
         document.getElementById("main").style.visibility = "visible";
         document.getElementById("main").style.top = "250px";
       }
       else
       {

         document.getElementById("main").style.visibility = "hidden";
       }
  }
</script>
