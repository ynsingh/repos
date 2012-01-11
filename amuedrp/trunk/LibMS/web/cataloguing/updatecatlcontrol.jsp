
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.myapp.struts.hbm.Biblio"%>
<%-- <%@page contentType="text/html" import="java.util.Calendar"%>
 <%
Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH) + 1;
       String bb= String.valueOf(now.get(Calendar.YEAR)) + String.valueOf(month) + String.valueOf(now.get(Calendar.DATE)) + String.valueOf(now.get(Calendar.HOUR_OF_DAY)) + String.valueOf(now.get(Calendar.MINUTE)) + String.valueOf(now.get(Calendar.SECOND)) + String.valueOf(now.get(Calendar.MILLISECOND));
%>--%>
<html>
    <head>
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
}
}
}
}
function search() {
// availableSelectList.innerHTML = "";
    var keyValue = document.getElementById("d_t_l_t").value;
    //keyValue = keyValue.replace(/^\s*|\s*$/g,"");
var req = newXMLHttpRequest();
req.onreadystatechange = getReadyStateHandler(req, update);
req.open("POST","<%=request.getContextPath()%>/datetimecal.do", true);
req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getEmail_Id="+keyValue);
return true;
}
function update(cartXML)
{
var emails = cartXML.getElementsByTagName("email_ids")[0];
var em = emails.getElementsByTagName("email_id");
var x="";
for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
x += ndValue+"\n";
}
document.getElementById("d_t_l_t").value=x;
}
</script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ddtabmenufiles/ddtabmenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-1.4.2.min.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">
        <script language="JavaScript">
<!-- Idea by:  Nic Wolfe -->
<!-- This script and many more are available free online at -->
<!-- The JavaScript Source!! http://javascript.internet.com -->
<!-- Begin
function popUp(URL) {
day = new Date();
id = day.getTime();
eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=600,height=550');");
}
// End -->
</script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/interface.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ddtabmenufiles/solidblocksmenu.css" />
<script type="text/javascript">
ddtabmenu.definemenu("ddtabs3", 0) //initialize Tab Menu #3 with 1st tab selected
</script>
<script type="text/javascript">
if (iens6){
document.write("<div id='viewer' style='background-color:#FDF5E6;visibility:hidden;position:absolute;left:0;width:0;height:0;z-index:1;overflow:hidden;border:0px ridge white'></div>")
}
if (ns4){
	hideobj = eval("document.nsviewer")
	hideobj.visibility="hidden"
}
c=0;
function func1(t){
     document.getElementById("zclick").value= t;
     func2(t);
    }
function func2(t){
  //  alert(t);
    if(t.value!=10){
        document.getElementById("ucontrol").submit();
       // alert("submitted! ");
}
}
window.status="F10 for help";
function statwords(message,x,y)
{
document.onkeypress = keyHit
function keyHit(event) {
  if (event.keyCode == event.DOM_VK_F10) {
     setObj(message,'override',x,y);
   //  JavaScript:window.status=message;
    event.stopPropagation()
    event.preventDefault()
  }
}
}
</script>
      <script language="javascript" type="text/javascript">
function leader(){
    var a=document.getElementById("rs").value;
    var b=document.getElementById("tr").value;
    var c=document.getElementById("bl").value;
    var d=document.getElementById("tc").value;
    var e=document.getElementById("ccs").value;
    var f=document.getElementById("el").value;
    var g=document.getElementById("dcf").value;
    var h=document.getElementById("mrrl").value;
    var z="00000"+a+b+c+d+e+"2"+"2"+"00000"+f+g+h+"4500";
    document.getElementById("catch").value=z;
    $("#layer1").hide();
}
function fixedfield(){
    var a=document.getElementById("vo").value;
    var b=document.getElementById("vp").value;
    var c=document.getElementById("cca").value;
    var d=document.getElementById("sb").value;
    var e=document.getElementById("mss").value;
    var f=document.getElementById("mrafa").value;
    var z="00000"+a+b+c+d+e+f;
    document.getElementById("catch1").value=z;
     $("#layer2").hide();
}
function physicalDes(){
var a=document.getElementById("sr").value;
    var b=document.getElementById("d").value;
    var c=document.getElementById("fudr").value;
    var d=document.getElementById("rr").value;
    var e=document.getElementById("mss1").value;
    var f=document.getElementById("tel").value;
    var g=document.getElementById("fel").value;
    var h=document.getElementById("sl").value;
    var i=document.getElementById("park").value;
    var z="e"+a+b+c+d+e+f+g+h+i;
    document.getElementById("catch2").value=z;
     $("#layer3").hide();
}
  </script>
<%! HashMap hm1 = new HashMap();
    Biblio bib1=new Biblio();
    Biblio bib2=new Biblio();
    Biblio bib3=new Biblio();
    Biblio bib4=new Biblio();
    Biblio bib5=new Biblio();
    Biblio bib6=new Biblio();
%>
<%
 hm1 = (HashMap)session.getAttribute("hsmp");
   if(!hm1.isEmpty() && hm1!=null){
  if(hm1.containsKey("Leader")){
       bib1=(Biblio)hm1.get("Leader");
        }
   if(hm1.containsKey("001")){
       bib2=(Biblio)hm1.get("001");
        }
   if(hm1.containsKey("003")){
       bib3=(Biblio)hm1.get("003");
        }
   if(hm1.containsKey("005")){
       bib4=(Biblio)hm1.get("005");
        }
   if(hm1.containsKey("007")){
       bib5=(Biblio)hm1.get("007");
        }
   if(hm1.containsKey("008")){
       bib6=(Biblio)hm1.get("008");
        }
    }else{
     System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL"+request.getAttribute("Leader"));
     if(request.getAttribute("Leader")!=null){
             bib1=(Biblio)request.getAttribute("Leader");}
     if(request.getAttribute("001")!=null){
             bib2=(Biblio)request.getAttribute("001");}
     if(request.getAttribute("003")!=null){
             bib3=(Biblio)request.getAttribute("003");}
     if(request.getAttribute("005")!=null){
             bib4=(Biblio)request.getAttribute("005");}
      if(request.getAttribute("007")!=null){
             bib5=(Biblio)request.getAttribute("007");}
    if(request.getAttribute("008")!=null){
             bib6=(Biblio)request.getAttribute("008");}
    }
 %>

<title>LibMS</title>
        <html:base />
    <style type="text/css">
		body
		{
			font-family:Verdana, Arial, Helvetica, sans-serif;
			font-size:11px
		}
		#layer1
		{
			position: absolute;
		        left:35%;
			top:20%;
			width:400px;
			background-color:#f0f5FF;
			border: 1px solid #000;
			z-index: 50;
		}
		#layer1_handle
		{
			background-color:#5588bb;
			padding:2px;
			text-align:center;
			font-weight:bold;
			color: #FFFFFF;
			vertical-align:middle;
		}
		#layer1_content
		{
			padding:5px;
		}
		#layer2
		{
			position: absolute;
			left:35%;
			top:20%;
			width:500px;
			background-color:#f0f5FF;
			border: 1px solid #000;
			z-index: 50;
		}
		#layer2_handle
		{
			background-color:#5588bb;
			padding:2px;
			text-align:center;
			font-weight:bold;
			color: #FFFFFF;
			vertical-align:middle;
		}
		#layer2_content
		{
			padding:5px;
		}
                #layer3
		{
			position: absolute;
		        left:35%;
			top:20%;
			width:500px;
			background-color:#f0f5FF;
			border: 1px solid #000;
			z-index: 50;
		}
		#layer3_handle
		{
			background-color:#5588bb;
			padding:2px;
			text-align:center;
			font-weight:bold;
			color: #FFFFFF;
			vertical-align:middle;
		}
		#layer3_content
		{
			padding:5px;
		}
                #close
		{
			float:right;
			text-decoration:none;
			color:#FFFFFF;
		}
                #close1
		{
			float:right;
			text-decoration:none;
			color:#FFFFFF;
		}
                #close2
		{
			float:right;
			text-decoration:none;
			color:#FFFFFF;
		}
	</style>
    <script type="text/javascript">
		$(document).ready(function()
		{
			$('#layer1').Draggable(
					{
						zIndex: 	20,
						ghosting:	false,
						opacity: 	0.7,
						handle:	'#layer1_handle'
					}
				);
			$('#layer2').Draggable(
					{
						zIndex: 	20,
						ghosting:	false,
						opacity: 	0.7,
						handle:	'#layer2_handle'
					}
				);
                        $('#layer3').Draggable(
			{
				zIndex: 	20,
				ghosting:	false,
				opacity: 	0.7,
				handle:	'#layer3_handle'
			}
				);
                        $('#layer1_form').ajaxForm({
				target: '#content',
				success: function()
				{
					$("#layer1").hide();
				}
			});
                        $('#layer2_form').ajaxForm({
				target: '#content1',
				success: function()
				{
					$("#layer2").hide();
				}
			});
			$('#layer3_form').ajaxForm({
				target: '#content2',
				success: function()
				{
					$("#layer3").hide();
				}
			});
                        $("#layer1").hide();
                        $("#layer2").hide();
			$("#layer3").hide();
			$('#preferences').click(function()
			{
				$("#layer1").show();
                                $("#layer2").hide();
                                $("#layer3").hide();
			});
			$('#preferences1').click(function()
			{
				$("#layer2").show();
                                 $("#layer1").hide();
                                 $("#layer3").hide();
			});
			$('#preferences2').click(function()
			{
				$("#layer3").show();
                                $("#layer1").hide();
                                $("#layer2").hide();
			});
                        $('#close').click(function()
			{
				$("#layer1").hide();
			});
                        $('#close1').click(function()
			{
				$("#layer2").hide();
			});
                        $('#close2').click(function()
			{
				$("#layer3").hide();
			});
		});
	</script>
    </head>
    <body onload="search()">
    <h2 align="center">Bibliographic Cataloguing</h2>

<div id="ddtabs3" class="solidblockmenu">
<ul>
<li><a href="<%=request.getContextPath()%>/cataloguing/updatecatlcontrol.jsp" onclick="func1(10)"  rel="sb10">Control Fields</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl0.jsp" onclick="func1(0)"  rel="sb0">0 (01X-09X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl1.jsp" onclick="func1(1)" rel="sb1">1 (1XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl2.jsp" onclick="func1(2)" rel="sb2">2 (20X-28X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl3.jsp" onclick="func1(3)" rel="sb3">3 (3XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl4.jsp" onclick="func1(4)" rel="sb4">4 (4XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl5.jsp" onclick="func1(5)" rel="sb5">5 (5XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl6.jsp" onclick="func1(6)" rel="sb6">6 (6XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl7.jsp" onclick="func1(7)" rel="sb7">7 (70X-78X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl8.jsp" onclick="func1(8)" rel="sb8">8 (80X-88X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl9.jsp" onclick="func1(9)" rel="sb9">9</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/cat_new_MARC.jsp"  rel="home">HOME</a></li>
</ul>
</div>
<DIV class="tabcontainer ieclass">
<FONT color="#8B008B">
<div id="sb0" class="tabcontent">
Number and Code Fields.
</div>
<div id="sb1" class="tabcontent">
Main Entry Fields.
</div>
<div id="sb2" class="tabcontent">
Title, Edition, Imprints etc Fields .
</div>
<div id="sb3" class="tabcontent">
Physical Description, etc Fields.
</div>
<div id="sb4" class="tabcontent">
Series Statements Field.
</div>
<div id="sb5" class="tabcontent">
Note Fields.
</div>
<div id="sb6" class="tabcontent">
Subject Access Fields.
</div>
<div id="sb7" class="tabcontent">
Added Entry and Linking Entry Fields.
</div>
<div id="sb8" class="tabcontent">
Series added Entry, Holding, Location, Alternate Graphics Fields.
</div>
<div id="sb9" class="tabcontent">
Local Fields 900 and onwards.
</div>
    <div id="home" class="tabcontent">
Go BACK to Manage MARC Bibliography.
</div>
    <div id="sb10" class="tabcontent">
Control Field Entry
</div>
</FONT>
</DIV>
<br>
<html:form method="post" action="/ucatcontrolaction" styleId="ucontrol">
  <input type="hidden" value="" name="zclick" id="zclick" />
    <div id="content" style="position: absolute; left: 5%;top: 20%">
    <b>    LEADER:</b>
    <br><input type="text"  name="leader" value="<% if(bib1.get$a()!=null){%><%= bib1.get$a() %><%}%>"  id="catch"/>
        <input type="button" id="preferences"  value="Leader Structure"/>
    </div>
    <br>
    <div id="c1" style="position: absolute; left: 5%;top: 30%">
        <b>001 - CONTROL NUMBER:</b>
        <br><input type="text" id="control_no" value="<% if(bib2.get$a()!=null){%><%= bib2.get$a() %><%}%>"  name="control_no"/>
    </div>
    <br>
    <div id="c1" style="position: absolute; left: 5%;top: 40%">
        <b> 003 - CONTROL NUMBER IDENTIFIER:</b>
        <br><input type="text"  id="control_no_id" value="<% if(bib3.get$a()!=null){%><%= bib3.get$a() %><%}%>" name="control_no_id"/>
    </div>
    <br>
    <div id="c1" style="position: absolute; left: 5%;top: 50%">
        <b> 005 - DATE AND TIME OF LATEST TRANSACTION:</b>
        <br><input type="text"  id="d_t_l_t" value="<% if(bib4.get$a()!=null){%><%= bib4.get$a() %><%}%>" name="d_t_l_t" readonly="true"/>
    </div>
    <br>
    <div id="content2" style="position: absolute; left: 5%;top: 60%">
            <b> 007- PHYSICAL DESCRIPTION FIXED FIELD:</b>
            <br><input type="text" name="phy_desc"  id="catch2" value="<% if(bib5.get$a()!=null){%><%= bib5.get$a() %><%}%>" />
        <input type="button" id="preferences2"  value="Physical Desc Structure"/>
    </div>
    <br>
    <div id="content1" style="position: absolute; left: 5%;top: 70%">
        <b>  008-FIXED-LENGTH DATA ELEMENTS:</b>
        <br><input type="text" name="fix_data"  id="catch1" value="<% if(bib6.get$a()!=null){%><%= bib6.get$a() %><%}%>" />
        <input type="button" id="preferences1"  value="Fixed Field Structure"/>
    </div>
    <br>
</html:form>
    
    <div id="layer1">
		<div id="layer1_handle">
			<a href="#" id="close">[ x ]</a>
			Leader
		</div>
		<div id="layer1_content">
                    <div id="layer1_form" >
Leader:
Record length<input type="text" readonly="true" value="00000"/>
<br>
Record Status
<select id="rs">
    <option value="a">Increase in encoding level</option>
    <option value="c">Corrected or revised</option>
    <option value="d">Deleted</option>
    <option value="n">New</option>
    <option value="p">Increase in encoding level from prepublication</option>
</select>
<br>
Type of record
<select id="tr">
    <option value="a">Language material</option>
    <option value="c">Notated music</option>
    <option value="d">Manuscript notated music</option>
    <option value="e">Cartographic material</option>
    <option value="g">Projected medium</option>
    <option value="i">Nonmusical sound recording</option>
    <option value="j">Musical sound recording</option>
    <option value="k">Two-dimensional nonprojectable graphic</option>
    <option value="m">Computer file</option>
    <option value="o">Kit</option>
    <option value="p">Mixed materials</option>
    <option value="r">Three-dimensional artifact or naturally occurring object</option>
    <option value="t">Manuscript language material</option>
</select>
<br>
Bibliographic Level
<select id="bl">
    <option value="a">Monographic component part</option>
    <option value="b">Serial component part</option>
    <option value="c">Collection</option>
    <option value="d">Subunit</option>
    <option value="i">Integrating resource</option>
    <option value="m">Monograph/Item</option>
    <option value="s">Serial</option>
</select>
<br>
Type of control
<select id="tc">
    <option value=" ">No specified type</option>
    <option value="a">Archival</option>
</select>
<br>
Character coding scheme
<select id="ccs">
    <option value=" ">MARC-8</option>
    <option value="a">UCS/Unicode</option>
</select>
<br>
Indicator count<input type="text" readonly="true" value="2"/>
<br>
Subfield code count<input type="text" readonly="true" value="2"/>
<br>
Base address of data<input type="text" readonly="true" value="00000"/>
<br>
Encoding level
<select id="el">
    <option value=" ">Full level</option>
    <option value="1">Full level, material not examined</option>
    <option value="2">Less-than-full level, material not examined</option>
    <option value="3">Abbreviated level</option>
    <option value="4">Core level</option>
    <option value="5">Partial (preliminary) level</option>
    <option value="7">Minimal level</option>
    <option value="8">Prepublication level</option>
    <option value="u">Unknown</option>
    <option value="z">Not applicable</option>
</select>
<br>
Descriptive cataloging form
<select id="dcf">
    <option value=" ">Non-ISBD</option>
    <option value="a">AACR 2</option>
    <option value="c">ISBD punctuation omitted</option>
    <option value="i">ISBD punctuation included</option>
    <option value="u">Unknown</option>
</select>
<br>
Multipart resource record level
<select id="mrrl">
    <option value=" ">Not specified or not applicable</option>
    <option value="a">Set</option>
    <option value="b">Part with independent title</option>
    <option value="c">Part with dependent title</option>
</select>
<br>
Length of the length-of-field portion<input type="text" readonly="true" value="4"/>
<br>
Length of the starting-character-position portion<input type="text" readonly="true" value="5"/>
<br>
Length of the implementation-defined portion<input type="text" readonly="true" value="0"/>
<br>
Undefined<input type="text" readonly="true" value="0"/>
<br>
<a href="#" id="close"><input type="button" onclick="leader()" value="Leader"/></a>
<br>
			</div>
		</div>
	</div>
	<div id="layer2">
		<div id="layer2_handle">
			<a href="#" id="close1">[ x ]</a>
			Fixed Length
		</div>
		<div id="layer2_content">
			<div id="layer2_form">
008-FIXED-LENGTH DATA ELEMENTS:
Date entered on file<input type="text" value="00000"/>
<br>
Volunteer opportunities
<select id="vo">
    <option value="a">No volunteer opportunities</option>
    <option value="b">Volunteer opportunities</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Volunteers provided
<select id="vp">
    <option value="a">No volunteers provided</option>
    <option value="b">Volunteers provided</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Child care arrangements
<select id="cca">
    <option value="a">No child care arrangements</option>
    <option value="b">Child care arrangements</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Speakers bureau
<select id="sb">
    <option value="a">No speakers bureau</option>
    <option value="b">Speakers bureau</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Mutual support groups
<select id="mss">
    <option value="a">No mutual support groups</option>
    <option value="b">Mutual support groups</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Meeting rooms and facilities available
<select id="mrafa">
    <option value="a">No meeting rooms and facilities</option>
    <option value="b">Meeting rooms and facilities</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Language<input type="text" readonly="true" value=""/>
<br>
<a href="#" id="close1"><input type="button" onclick="fixedfield()" value="Fixed Field"/></a>
<br>
			</div>
		</div>
	</div>
<div id="layer3">
		<div id="layer3_handle">
			<a href="#" id="close2">[ x ]</a>
			Physical Description
		</div>
		<div id="layer3_content">
<div id="layer3_form">
007-PHYSICAL DESCRIPTION FIXED FIELD:
Category<input type="text" readonly="true" value="e"/>
<br>
Stairway ramps
<select id="sr">
    <option value="a">No ramps</option>
    <option value="b">Entrance and internal ramps</option>
    <option value="c">Entrance ramp only--multiple floors</option>
    <option value="d">Entrance ramp only--single floor</option>
    <option value="e">Internal ramps only</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Doors
<select id="d">
    <option value="a">No wide or offset-hinge doors</option>
    <option value="b">Wide or offset-hinge doors</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Furniture, equipment, display racks
<select id="fudr">
    <option value="a">No child care arrangements</option>
    <option value="b">Child care arrangements</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Restrooms
<select id="rr">
    <option value="a">No special restroom accommodations or grab bars</option>
    <option value="b">Special restroom accommodations and grab bars</option>
    <option value="c">Special restroom accommodations only</option>
    <option value="d">Grab bars only</option>
    <option value="e">No restrooms</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Elevators
<select id="mss1">
    <option value="a">No mutual support groups</option>
    <option value="b">Mutual support groups</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Telephones
<select id="tel">
    <option value="a">No lowered telephones or handset amplifiers</option>
    <option value="b">Lowered telephones and handset amplifiers</option>
    <option value="c">Lowered telephones only</option>
    <option value="d">Handset amplifiers only</option>
    <option value="e">No telephones</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Flashing emergency lights
<select id="fel">
    <option value="a">No lowered telephones or handset amplifiers</option>
    <option value="b">Lowered telephones and handset amplifiers</option>
    <option value="c">Lowered telephones only</option>
    <option value="d">Handset amplifiers only</option>
    <option value="e">No telephones</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Sign language
<select id="sl">
    <option value="a">No sign language</option>
    <option value="b">Sign language</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Subtitles and/or supertitles
<select id="sas">
    <option value="a">No subtitles or supertitles</option>
    <option value="b">Subtitles and supertitles</option>
    <option value="c">Subtitles only</option>
    <option value="d">Supertitles only</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
Parking
<select id="park">
    <option value="a">No handicapped accessible parking available</option>
    <option value="b">No handicapped accessible parking available</option>
    <option value="c">Handicapped accessible parking available with low clearance only</option>
    <option value="n">Not applicable</option>
    <option value="u">Unknown</option>
</select>
<br>
<a href="#" id="close2"><input type="button" onclick="physicalDes()" value="Physical Description"/></a>
<br>
			</div>
		</div>
	</div>
</body>
    </html>
