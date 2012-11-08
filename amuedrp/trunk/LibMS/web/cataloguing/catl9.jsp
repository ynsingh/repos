<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
       <title> Bibliographic Cataloguing According to MARC21 -- 9XX</title>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ddtabmenufiles/ddtabmenu.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">


<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/animatedcollapse.js">

/***********************************************
* Animated Collapsible DIV- (c) muhammad zeeshan
***********************************************/

</script>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ddtabmenufiles/solidblocksmenu.css" />
<script type="text/javascript">

ddtabmenu.definemenu("ddtabs3", 10) //initialize Tab Menu #3 with 2nd tab selected
</script>


<script type="text/javascript">
animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}


animatedcollapse.init()

</script>
<script type="text/javascript">
var description=new Array()
description[0]='This is tool-tip description 1'
description[1]='<b>This is tool-tip descrition 2'
description[2]='<i>This is tool-tip description 3'

</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/tooltip.js">

/***********************************************
* ToolTip layer - (c) muhammad zeeshan
***********************************************/

</script>

    </head>

     <jsp:include page="/admin/header.jsp"></jsp:include>
<body>
    <div
   style="  top:20%;
   left:10%;
   right:10%;border: solid 1px black;
      position: absolute;

      visibility: show;">
    
       <layer name="nsviewer" bgcolor="#FFFFCC" style="border-width:thin;z-index:1"></layer>
<script type="text/javascript">
if (iens6){
document.write("<div id='viewer' style='background-color:#FFFFCC;visibility:hidden;position:absolute;left:0;width:0;height:0;z-index:1;overflow:hidden;border:0px ridge white'></div>")
}
if (ns4){
	hideobj = eval("document.nsviewer")
	hideobj.visibility="hidden"
}
</script>
                                              <h2 align="center"  class="headerStyle" style="height: 25px;">MARC Based Bibliographic Cataloging</h2>

<div id="ddtabs3" class="header1" style="background-color: cyan;line-height: 26px;font-size: 13px;vertical-align: bottom" >

    <a href="<%=request.getContextPath()%>/cataloguing/catlcontrol.jsp" style="text-decoration:none" onclick="func1(10)"  rel="sb10">Control Fields</a><font color="blue">&nbsp;|&nbsp;</font>
<a href="<%=request.getContextPath()%>/cataloguing/catl0.jsp" style="text-decoration:none" onclick="func1(0)"  rel="sb0">0 (01X-09X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl1.jsp" style="text-decoration:none" onclick="func1(1)" rel="sb1">1 (1XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl2.jsp"  style="text-decoration:none" onclick="func1(2)" rel="sb2">2 (20X-28X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl3.jsp"  style="text-decoration:none" onclick="func1(3)" rel="sb3">3 (3XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl4.jsp" style="text-decoration:none" onclick="func1(4)" rel="sb4">4 (4XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl5.jsp" style="text-decoration:none" onclick="func1(5)" rel="sb5">5 (5XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl6.jsp" style="text-decoration:none" onclick="func1(6)" rel="sb6">6 (6XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl7.jsp" style="text-decoration:none" onclick="func1(7)" rel="sb7">7 (70X-78X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl8.jsp" style="text-decoration:none" onclick="func1(8)" rel="sb8">8 (80X-88X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/catl9.jsp" style="text-decoration:none" onclick="func1(9)" rel="sb9">9</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/marchome.do" style="text-decoration:none" rel="home">Cancel</a>&nbsp;|&nbsp;

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
You are on MARC Page : 9 Tag Page
<div style="width:100%"><%
String tag0=(String)session.getAttribute("tag0");
String tag1=(String)session.getAttribute("tag1");
String tag2=(String)session.getAttribute("tag2");

%>
                      <table width="100%">
                                             <tr><td></td><td align="center">
                                                     <%if(tag0!=null && tag1!=null && tag2!=null){%>
                                                     <a href="<%=request.getContextPath() %>/marccommit1.do" style="text-decoration: none;"><input type="submit" value="Commit Entered MARC Data" /></a>
                                                 <%}else{%>Please Fill data in following tag Pages before commit.
                                                 <br/> <%=(tag0==null)?"Tag 0 Field data":""%>
                                                  <br/> <%=(tag1==null)?"Tag 1 Field data":""%>
                                                   <br/> <%=(tag2==null)?"Tag 2 Field data":""%>
                                                 <%}%>
                                                 </td></tr>
                                     </table>
                                     </div>
<div>
    <table border="0" >
        <tr><td align="center"><img src="<%=request.getContextPath()%>/images/cont.jpg" alt="under construction"  /></td><td align="center"><font face="areal" size="6" color="blue">This page Is Under Construction !</font></td></tr>
        <br> <tr><td></td><td align="center"><font face="areal" size="3" color="navy">* Tags on this page will be local to library.</font></td></tr>
    </table>
</div></div></body>
    </body>
</html>
