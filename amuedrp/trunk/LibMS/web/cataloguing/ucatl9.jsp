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

ddtabmenu.definemenu("ddtabs3", 9) //initialize Tab Menu #3 with 2nd tab selected
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
    <body><div
   style="  top:15%;
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
                                          <h2 align="center"  class="headerStyle" style="height: 25px;">UPDATE MARC Based Bibliographic Cataloging</h2>

<div id="ddtabs3" class="header1" style="background-color: cyan;line-height: 26px;font-size: 13px;vertical-align: bottom" >
<a style="text-decoration:none" href="<%=request.getContextPath()%>/cataloguing/updatecatlcontrol.jsp" onclick="func1(10)"  rel="sb10">Control Fields</a><font color="blue">&nbsp;|&nbsp;</font>
<a style="text-decoration:none" href="<%=request.getContextPath()%>/cataloguing/ucatl0.jsp" onclick="func1(0)"  rel="sb0">0 (01X-09X)</a><font color="blue">&nbsp;|&nbsp;</font>
<a style="text-decoration:none" href="<%=request.getContextPath()%>/cataloguing/ucatl1.jsp" onclick="func1(1)" rel="sb1">1 (1XX)</a><font color="blue">&nbsp;|&nbsp;</font>
<a style="text-decoration:none" href="<%=request.getContextPath()%>/cataloguing/ucatl2.jsp" onclick="func1(2)" rel="sb2">2 (20X-28X)</a><font color="blue">&nbsp;|&nbsp;</font>
<a style="text-decoration:none" href="<%=request.getContextPath()%>/cataloguing/ucatl3.jsp" onclick="func1(3)" rel="sb3">3 (3XX)</a><font color="blue">&nbsp;|&nbsp;</font>
<a style="text-decoration:none" href="<%=request.getContextPath()%>/cataloguing/ucatl4.jsp" onclick="func1(4)" rel="sb4">4 (4XX)</a><font color="blue">&nbsp;|&nbsp;</font>
<a style="text-decoration:none" href="<%=request.getContextPath()%>/cataloguing/ucatl5.jsp" onclick="func1(5)" rel="sb5">5 (5XX)</a><font color="blue">&nbsp;|&nbsp;</font>
<a style="text-decoration:none" href="<%=request.getContextPath()%>/cataloguing/ucatl6.jsp" onclick="func1(6)" rel="sb6">6 (6XX)</a><font color="blue">&nbsp;|&nbsp;</font>
<a style="text-decoration:none" href="<%=request.getContextPath()%>/cataloguing/ucatl7.jsp" onclick="func1(7)" rel="sb7">7 (70X-78X)</a><font color="blue">&nbsp;|&nbsp;</font>
<a style="text-decoration:none" href="<%=request.getContextPath()%>/cataloguing/ucatl8.jsp" onclick="func1(8)" rel="sb8">8 (80X-88X)</a><font color="blue">&nbsp;|&nbsp;</font>
<a style="text-decoration:none" href="<%=request.getContextPath()%>/cataloguing/ucatl9.jsp" onclick="func1(9)" rel="sb9">9</a><font color="blue">&nbsp;|&nbsp;</font>
<a style="text-decoration:none" href="<%=request.getContextPath()%>/cataloguing/cat_new_MARC.jsp"  rel="home">Cancel</a>

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
</div></FONT>
</DIV>
                 <div style="width:100%"><%

%>
                      <table width="100%">
                                             <tr><td></td><td align="center">
    
                                                     <a href="<%=request.getContextPath() %>/marccommit.do" style="text-decoration: none;"><input type="submit" value="Commit Entered MARC Data" /></a>
                                                 
                                                 </td></tr>
                                     </table>
                                     </div>

    <table border="0" >
        <tr><td align="center"><img src="<%=request.getContextPath()%>/images/cont.jpg" alt="under construction"  /></td><td align="center"><font face="areal" size="6" color="blue">This page Is Under Construction !</font></td></tr>
        <br> <tr><td></td><td align="center"><font face="areal" size="3" color="navy">* Tags on this page will be local to library.</font></td></tr>
    </table>
</div></body>
    
</html>
