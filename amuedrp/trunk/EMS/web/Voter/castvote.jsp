<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%-- 
    Document   : voter_home
    Created on : Jun 16, 2011, 1:27:11 AM
   
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<%@page import="java.util.*,com.myapp.struts.hbm.Election,com.myapp.struts.hbm.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
    <%
      response.setHeader("Cache-Control", "no-cache");
      response.setHeader("Pragma", "no-cache");
      response.setDateHeader("max-age", 0);
      response.setDateHeader("Expires", 0);
    %>
<%
	try{
		if(session.getAttribute("institute_id")!=null){
//			System.out.println("institute_id"+session.getAttribute("institute_id"));
		}
		else{
			session.invalidate();
		    request.setAttribute("msg", "Your Session Expired: Please Login Again");
	    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
        	}
	 }catch(Exception e){
         	request.setAttribute("msg", "Your Session Expired: Please Login Again");
                %>session.invalidate();<%
         }
    %> 
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
    String regid="";
    
%>
<%
String contextPath = request.getContextPath();
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
    regid = resource.getString("registrationid");
    System.out.println("Reg id="+regid);
    %>
<html>
    	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Election Management System</title>
	<script type="text/javascript" language="javascript">

       if(window.history.forward(1) != null)
           window.history.forward(1);

		var olddoc="";
		var olddoc1="";
		var loadcount;
		var election_id;

		function castVote(id)
                {
    			election_id=id;
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
		}
	

    election_id="<%=session.getAttribute("election")%>";
function send()
{
    
    top.location.href="<%=request.getContextPath()%>/login.jsp";
    

   }
            function deleteBod()
            {
                var child = document.getElementById("overbody");
               if(child!=null)
               document.getElementById("bod").removeChild(child);

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
                htm+='<iframe name="f1" id="f1"  src="<%=request.getContextPath()%>/election1.do?election='+ electVal +'" width="665px" style="height: '+ h1 +'px" />';
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
        </script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>


   
    </head>
    <body style="margin:  0px;height: 90%; " id="bod" onload="loadvoting();">
   
            

        <div id="main" style="width: 100%;height: 100%;margin: 0px;">
            <div id="topstrip" style="margin: 0px; width: 100%;height: 20px;background-color: black;">
                
                    
                  
<br/><br/>               
            </div>
            <div id="header" style="width: 100%;height: 5%;margin: 0px;">
                
                
            </div>
            <div id="middle" style="width: 100%;height: 70%; position: relative;margin-top: 30px;">
                <div id="ballot"></div>
             
<!--Show Vote -->
<html:hidden property="election" value='<%=(String)session.getAttribute("election")%>' styleId="election4" ></html:hidden>
</div>
            <div id="footer" style="width: 100%;height: 15%; position: relative;"></div>
        </div>
    </body>
</html>
