<%-- 
    Document   : catl7
    Created on : Mar 29, 2011, 6:37:30 PM
    Author     : zeeshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
       <title> Bibliographic Cataloguing According to MARC21 -- 7XX</title>

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

ddtabmenu.definemenu("ddtabs3", 8) //initialize Tab Menu #3 with 2nd tab selected
</script>


<script type="text/javascript">
animatedcollapse.addDiv('700', 'fade=1,height=30px')
animatedcollapse.addDiv('700b', 'fade=1,height=30px')
animatedcollapse.addDiv('700c', 'fade=1,height=30px')
animatedcollapse.addDiv('700d', 'fade=1,height=30px')
animatedcollapse.addDiv('700e', 'fade=1,height=30px')
animatedcollapse.addDiv('700f', 'fade=1,height=30px')
animatedcollapse.addDiv('700h', 'fade=1,height=30px')
animatedcollapse.addDiv('700k', 'fade=1,height=30px')
animatedcollapse.addDiv('700l', 'fade=1,height=30px')
animatedcollapse.addDiv('700m', 'fade=1,height=30px')
animatedcollapse.addDiv('700n', 'fade=1,height=30px')
animatedcollapse.addDiv('700p', 'fade=1,height=30px')
animatedcollapse.addDiv('700r', 'fade=1,height=30px')
animatedcollapse.addDiv('700s', 'fade=1,height=30px')
animatedcollapse.addDiv('700t', 'fade=1,height=30px')
animatedcollapse.addDiv('7004', 'fade=1,height=30px')

animatedcollapse.addDiv('740', 'fade=1,height=30px')
animatedcollapse.addDiv('740h', 'fade=1,height=30px')
animatedcollapse.addDiv('740n', 'fade=1,height=30px')
animatedcollapse.addDiv('740p', 'fade=1,height=30px')
animatedcollapse.addDiv('7405', 'fade=1,height=30px')


animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}


animatedcollapse.init()

</script>
<script type="text/javascript">
   function loadHelp()
    {
        window.status="Press F10 for Help";

    }

</script>
<script type="text/javascript">
var description=new Array()
description[0]='Type of personal name entry element-<b> 0 - Forename, 1 - Surname, 3 - Family name'
description[1]='Type of added entry- <b># - No information provided, 2 - Analytical entry'
description[2]='e.g. <b>Jung, C. G.'
description[3]=''
description[4]='e.g. <b>surveyor'
description[5]='e.g. <b>1894-1973,'
description[6]='e.g. <b>production.'
description[7]='e.g. <b>1976.'
description[8]='e.g. <b>Sound recording.'
description[9]='e.g. <b>Selections.'
description[10]='e.g. <b>German.'
description[11]='e.g. <b>piano, winds, strings,'
description[12]='e.g. <b>No. 6.'
description[13]='e.g. <b>Frühlingslied.'
description[14]='e.g. <b>A ♭ major.'
description[15]='e.g. <b>9th ed.'
description[16]='e.g. <b>Dawn, the beginning.'
description[17]='e.g. <b>org'

description[18]='Nonfiling characters- <b>0 - No nonfiling characters, 1-9 - Number of nonfiling characters '
description[19]='Type of added entry- <b># - No information provided, 2 - Analytical entry, '
description[20]='Parenthetical data which may appear as part of the title should not be separately subfield coded. e.g. <b>Dissolution of the family unit.'
description[21]='Media qualifier. e.g <b>Media qualifier.'
description[22]='Number designation for a part/section of a work used in a title. '
description[23]='Name designation of a part/section of work in a title. e.g <b>Divorce, separation, and annulment. '
description[24]=''
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/tooltip.js">

/***********************************************
* ToolTip layer - (c) muhammad zeeshan
***********************************************/

</script>

    </head>
    <body onload="loadHelp()">
        <layer name="nsviewer" bgcolor="#FDF5E6" style="border-width:thin;z-index:1"></layer>
<script type="text/javascript">
if (iens6){
document.write("<div id='viewer' style='background-color:#FDF5E6;visibility:hidden;position:absolute;left:0;width:0;height:0;z-index:1;overflow:hidden;border:0px ridge white'></div>")
}
if (ns4){
	hideobj = eval("document.nsviewer")
	hideobj.visibility="hidden"
}

function func1(t){

     document.getElementById("zclick").value= t;

     func2(t);

    }

function func2(t){
    alert(t);
    if(t.value!=7){

        document.getElementById("cat7").submit();
     alert("submitted! ");
}
}


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
                                          <h2 align="center">MARC Based Bibliographic Cataloging</h2>

<div id="ddtabs3" class="solidblockmenu">
<ul>
<li><a href="<%=request.getContextPath()%>/cataloguing/catlcontrol.jsp" onclick="func1(10)"  rel="sb10">Control Fields</a></li>
    <li><a href="<%=request.getContextPath()%>/cataloguing/catl0.jsp" onclick="func1(0)"  rel="sb0">0 (01X-09X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/catl1.jsp" onclick="func1(1)" rel="sb1">1 (1XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/catl2.jsp" onclick="func1(2)" rel="sb2">2 (20X-28X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/catl3.jsp" onclick="func1(3)" rel="sb3">3 (3XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/catl4.jsp" onclick="func1(4)" rel="sb4">4 (4XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/catl5.jsp" onclick="func1(5)" rel="sb5">5 (5XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/catl6.jsp" onclick="func1(6)" rel="sb6">6 (6XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/catl7.jsp" onclick="func1(7)" rel="sb7">7 (70X-78X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/catl8.jsp" onclick="func1(8)" rel="sb8">8 (80X-88X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/catl9.jsp" onclick="func1(9)" rel="sb9">9 (9XX)</a></li>
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

                                      <!-- Marc entries Starts from here . -->
<div style="position:absolute;left:80%;top:18%;">
                                         <table>
                                     <tr><td></td><td align="right"><a href="<%=request.getContextPath() %>/marccommit.do"><input type="submit" value="Commit Data" /></a></td></tr>
                                     </table>
                                     </div>
<div style="position:absolute;left:5%;top:25%;width:90%;border:1px #C0C0C0 solid;background: #f5fffa;">
<html:form styleId="cat7" action="/cataction7.do" method="post">
<table width="100%" cellspacing="5" >
  <tr><input type="hidden" value="" name="zclick" id="zclick" /></tr>
<tr>
    <td>Added Entry-Personal Name (R)(700) : <a href="javascript:animatedcollapse.toggle('700')">ind</a> <div id="700" style="width: 150px; display:none" >ind1<input type="text" value="" name="in7001" maxlength="1" size="1" onFocus="statwords(description[0],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="" name="in7002" maxlength="1" size="1" onFocus="statwords(description[1],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Personal name (NR) <input type="text" value="" name="z700a" id="700a" onFocus="statwords(description[2],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('700b')">$b </a>

<div id="700b" style=" background: #FDF5E6; display:none">
Numeration (NR) <input type="text" value="" name="z700b" id="700b" onFocus="statwords(description[3],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700c')">$c </a>

<div id="700c" style=" background: #FDF5E6; display:none">
Titles and other words associated with a name (R)<input type="text" value="" name="z700c" id="700c" onFocus="statwords(description[4],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700d')">$d </a>

<div id="700d" style=" background: #FDF5E6; display:none">
Dates associated with a name (NR) <input type="text" value="" name="z700d" id="700d" onFocus="statwords(description[5],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700e')">$e </a>

<div id="700e" style=" background: #FDF5E6; display:none">
Relator term (R) <input type="text" value="" name="z700e" id="700e" onFocus="statwords(description[6],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700f')">$f </a>

<div id="700f" style=" background: #FDF5E6; display:none">
Date of a work (NR) <input type="text" value="" name="z700f" id="700f" onFocus="statwords(description[7],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700h')">$h </a>

<div id="700h" style=" background: #FDF5E6; display:none">
Medium (NR) <input type="text" value="" name="z700h" id="700h" onFocus="statwords(description[8],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700k')">$k </a>

<div id="700k" style=" background: #FDF5E6; display:none">
Form subheading (R) <input type="text" value="" name="z700k" id="700k" onFocus="statwords(description[9],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700l')">$l </a>

<div id="700l" style=" background: #FDF5E6; display:none">
Language of a work (NR) <input type="text" value="" name="z700l" id="700l" onFocus="statwords(description[10],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700m')">$m </a>

<div id="700m" style=" background: #FDF5E6; display:none">
Medium of performance for music (R) <input type="text" value="" name="z700m" id="700m" onFocus="statwords(description[11],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700n')">$n </a>

<div id="700n" style=" background: #FDF5E6; display:none">
Number of part/section of a work (R) <input type="text" value="" name="z700n" id="700n" onFocus="statwords(description[12],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700p')">$p </a>

<div id="700p" style=" background: #FDF5E6; display:none">
Name of part/section of a work (R) <input type="text" value="" name="z700p" id="700p" onFocus="statwords(description[13],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700r')">$r </a>

<div id="700r" style=" background: #FDF5E6; display:none">
Key for music (NR) <input type="text" value="" name="z700r" id="700r" onFocus="statwords(description[14],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700s')">$s </a>

<div id="700s" style=" background: #FDF5E6; display:none">
Version (NR) <input type="text" value="" name="z700s" id="700s" onFocus="statwords(description[15],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700t')">$t </a>

<div id="700t" style=" background: #FDF5E6; display:none">
Title of a work (NR) <input type="text" value="" name="z700t" id="700t" onFocus="statwords(description[16],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('7004')">$4 </a>

<div id="7004" style=" background: #FDF5E6; display:none">
Relator code (R) <input type="text" value="" name="z7004" id="7004" onFocus="statwords(description[17],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>Added Entry-Uncontrolled Related/Analytical Title (R)(740) : <a href="javascript:animatedcollapse.toggle('740')">ind</a> <div id="740" style="width: 150px; display:none" >ind1<input type="text" value="" name="in7401" maxlength="1" size="1" onFocus="statwords(description[18],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="" name="in7402" maxlength="1" size="1" onFocus="statwords(description[19],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Uncontrolled related/analytical title (NR) <input type="text" value="" name="z740a" id="740a" onFocus="statwords(description[20],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('740h')">$h </a>

<div id="740h" style=" background: #FDF5E6; display:none">
Medium (NR) <input type="text" value="" name="z740h" id="740h" onFocus="statwords(description[21],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('740n')">$n </a>

<div id="740n" style=" background: #FDF5E6; display:none">
Number of part/section of a work (R) <input type="text" value="" name="z740n" id="740n" onFocus="statwords(description[22],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('740p')">$p </a>

<div id="740p" style=" background: #FDF5E6; display:none">
Name of part/section of a work (R) <input type="text" value="" name="z740p" id="740p" onFocus="statwords(description[23],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

</font></td></tr>

</table></html:form>
</div>
    </body>
</html>
