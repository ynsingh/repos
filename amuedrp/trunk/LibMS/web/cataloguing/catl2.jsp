<%-- 
    Document   : catl2
    Created on : Mar 29, 2011, 6:27:42 PM
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
       <title> Bibliographic Cataloguing According to MARC21 -- 2XX</title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ddtabmenufiles/ddtabmenu.js"></script>



<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/animatedcollapse.js">

/***********************************************
* Animated Collapsible DIV- (c) muhammad zeeshan
***********************************************/

</script>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ddtabmenufiles/solidblocksmenu.css" />
<script type="text/javascript">

ddtabmenu.definemenu("ddtabs3", 3) //initialize Tab Menu #3 with 2nd tab selected
</script>


<script type="text/javascript">
animatedcollapse.addDiv('210', 'fade=1,height=30px')
animatedcollapse.addDiv('260', 'fade=1,height=30px')
animatedcollapse.addDiv('250', 'fade=1,height=30px')
animatedcollapse.addDiv('245', 'fade=1,height=30px')

animatedcollapse.addDiv('256', 'fade=1,height=30px')



animatedcollapse.addDiv('210b', 'fade=1,height=30px')
animatedcollapse.addDiv('2102', 'fade=1,height=30px')


animatedcollapse.addDiv('245b', 'fade=1,height=30px')
animatedcollapse.addDiv('245c', 'fade=1,height=30px')
animatedcollapse.addDiv('245n', 'fade=1,height=30px')
animatedcollapse.addDiv('245k', 'fade=1,height=30px')



animatedcollapse.addDiv('250b', 'fade=1,height=30px')

animatedcollapse.addDiv('260b', 'fade=1,height=30px')
animatedcollapse.addDiv('260c', 'fade=1,height=30px')
animatedcollapse.addDiv('260e', 'fade=1,height=30px')
animatedcollapse.addDiv('260f', 'fade=1,height=30px')

animatedcollapse.addDiv('263', 'fade=1,height=30px')


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
description[0]='Title added entry. 0 - No added entry, 1 - Added entry '
description[1]='Type- # - Abbreviated key title, 0 - Other abbreviated title'
description[2]='Complete abbreviated title.ex- <b>Manage. improv. cost reduct. goals</b> for Management improvement and cost reduction goals '
description[3]='Abbreviated form of the parenthetical qualifying information. ex- (Faridabad) '
description[4]='MARC code that identifies the source list for the abbreviated title, ex- dnlm'

description[5]='Title added entry- 0 - No added entry, 1 - Added entry'
description[6]='Nonfiling characters- 0 - No nonfiling characters, 1-9 - Number of nonfiling characters'
description[7]='Title proper and alternative title, excluding the designation of the number or name of a part. ex- Oklahoma'
description[8]='Data includes parallel titles, titles subsequent to the first and other title information.ex- Carousel ; South Pacific '
description[9]='First statement of responsibility and/or remaining data.ex- Gary Snyder ; preface by Nathaniel Tarn ; edited by Donald Allen.'
description[10]='ex- Part I '
description[11]='ex- diaries, Case Files etc.'

description[12]='Both indicator positions are undefined; each contains a blank (#).'
description[13]='Edition statement that usually consists of numeric and alphabetic characters and accompanying words and/or abbreviations. ex- 2nd ed.'
description[14]='a statement of personal or corporate responsibility and/or a parallel edition statement. ex- 4th ed., Canadian ed. etc'
description[15]='May include the type of file and the number of records, etc. ex- <b>Computer data (2 files : 876,000, 775,000 records).</b> '

description[16]='Sequence of publishing statements: # - Not applicable/No information provided/Earliest available publisher, 2 - Intervening publisher, 3 - Current/latest publisher '
description[17]='Undefined and contains a blank (#).'
description[18]='Place of publication and any additions to the name of a place, including an address. ex- Cambridge [Cambridgeshire]'
description[19]='Name of the publisher or distributor and any qualifying terms. ex- American Statistical Association'
description[20]='May contain multiple dates (e.g., dates of publication and copyright).ex- 1984'
description[21]='Place of manufacture and any additions to the place name.ex- Moscow '
description[22]='Name of the manufacturer and any qualifying terms.ex- CTD Printers'

description[23]='Six-digit date recorded in the pattern yyyymm, a hyphen(-) is used for unknown portion of date. ex- 200011, 1999-- etc'
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
    if(t.value!=2){

        document.getElementById("cat2").submit();
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
<!-- Title statements fields 20x-24x -->
<div style="position:absolute;left:80%;top:18%;">
                                         <table>
                                     <tr><td></td><td align="right"><a href="<%=request.getContextPath() %>/marccommit.do"><input type="submit" value="Commit Data" /></a></td></tr>
                                     </table>
                                     </div>
<div style="position:absolute;left:5%;top:25%;width:90%;border:1px #C0C0C0 solid;background: #f5fffa;">
    <html:form styleId="cat2" action="/cataction2.do" method="post">
<table width="100%" cellspacing="5" >
    <tr><input type="hidden" value="" name="zclick" id="zclick" /></tr>
 <tr><td>Abbreviated Title (210) : <a href="javascript:animatedcollapse.toggle('210')">ind</a> <div id="210" style="width: 150px; display:none" >ind1<input type="text" name="in2101" value="" maxlength="1" size="1" onFocus="statwords(description[0],650,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="in2102" value="" maxlength="1" size="1" onFocus="statwords(description[1],550,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Abbreviated Title (NR)<input type="text" value="" name="z210a" id="210a" onFocus="statwords(description[2],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('210b')">$b </a>

<div id="210b" style="width: 500px; background: #FDF5E6; display:none">
Qualifying Information (NR) <input type="text" value="" name="z210b" id="210b" onFocus="statwords(description[3],650,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('2102')">$2 </a>
<div id="2102" style="width: 500px; background: #FDF5E6; display:none">
Source (R) <input type="text" value="" name="z2102" id="2102"  onFocus="statwords(description[4],650,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>


<tr>
    <td>Title Statement (245) :<a href="javascript:animatedcollapse.toggle('245')">ind</a> <div id="245" style="width: 150px; display:none" >ind1<input type="text" name="in2451" value="" maxlength="1" size="1" onFocus="statwords(description[5],650,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="2452" value="" maxlength="1" size="1" onFocus="statwords(description[6],650,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>$a Title (NR)<input type="text" value="<% if(session.getAttribute("title")!=null){
                  out.println(session.getAttribute("title"));
        }%>" name="z245a" id="245a" onFocus="statwords(description[7],650,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('245b')">$b</a>
<div id="245b" style="width: 500px; background: #FDF5E6; display:none">
 Remainder of title (NR)<input type="text" value="" name="z245b" id="245b" onFocus="statwords(description[8],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('245c')"> $c</a>
<div id="245c" style="width: 500px; background: #FDF5E6; display:none">
Statement of responsibility (NR)<input type="text" value="" name="z245c" id="245c" onFocus="statwords(description[9],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('245n')"> $n</a>
<div id="245n" style="width: 500px; background: #FDF5E6; display:none">
Number of Part/Section of Work(NR)<input type="text" value="" name="z245n" id="245n" onFocus="statwords(description[10],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('245k')"> $k</a>
<div id="245k" style="width: 500px; background: #FDF5E6; display:none">
Form (NR)<input type="text" value="" name="z245k" id="245k" onFocus="statwords(description[11],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

</font></td></tr>



<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<!-- 25X-28X - Edition, Imprint, Etc. Fields-General Information-->
<tr>
    <td>Edition Statement (250) :<a href="javascript:animatedcollapse.toggle('250')">ind</a> <div id="250" style="width: 150px; display:none" >ind1<input type="text" name="in2501" value="#" maxlength="1" size="1" onFocus="statwords(description[12],650,30)" onBlur="clearTimeout(openTimer);stopIt()"  /> ind2<input type="text" name="in2502" value="#" maxlength="1" size="1" onFocus="setObj(description[12],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>$a Edition Statement(NR)<input type="text" value="" name="z250a" id="250a" onFocus="statwords(description[13],800,30)" onBlur="clearTimeout(openTimer);stopIt()"  />
<font size="2">
<a href="javascript:animatedcollapse.toggle('250b')">$b</a>

<div id="250b" style="width: 500px; background: #FDF5E6; display:none">
Remainder of edition statement(NR)<input type="text" value="" name="z250b" id="250b" onFocus="statwords(description[14],800,30)" onBlur="clearTimeout(openTimer);stopIt()"  />
</div>
</font></td></tr>



<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr>
    <td>Computer File Characteristics (NR)(256) :<a href="javascript:animatedcollapse.toggle('256')">ind</a> <div id="256" style="width: 150px; display:none" >ind1<input type="text" name="in2561" value="" maxlength="1" size="1" onFocus="statwords(description[12],650,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="in2562" value="" maxlength="1" size="1" onFocus="statwords(description[12],650,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>$a Computer file characteristics (NR) <input type="text" value="" name="z256a" id="256a" onFocus="statwords(description[15],800,30)" onBlur="clearTimeout(openTimer);stopIt()"  />
<font size="2">
</font></td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr>
    <td>Publication, Distribution, etc(Imprint) (260) :<a href="javascript:animatedcollapse.toggle('260')">ind</a> <div id="260" style="width: 150px; display:none">ind1<input type="text" name="2601" value="" maxlength="1" size="1" onFocus="statwords(description[16],650,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="2602" value="#" maxlength="1" size="1" onFocus="setObj(description[17],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>$a Place of Publication, Distribution, etc(R)<input type="text" value="" name="z260a" id="260a" onFocus="statwords(description[18],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('260b')">$b</a>

<div id="260b" style="width: 500px; background: #FDF5E6; display:none">
Name of Publisher, Distributor, etc (R)<input type="text" value="" name="z260b" id="260b" onFocus="statwords(description[19],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('260c')">$c</a>
<div id="260c" style="width: 500px; background: #FDF5E6; display:none">
Date of Publication,Distribution etc(R)<input type="text" value="" name="z260c" id="260c" onFocus="statwords(description[20],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('260e')">$e</a>
<div id="260e" style="width: 500px; background: #FDF5E6; display:none">
Place of Manufacturer(NR)<input type="text" value="" name="z260e" id="260e" onFocus="statwords(description[21],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('260f')">$f</a>
<div id="260f" style="width: 500px; background: #FDF5E6; display:none">
Manufacturer(NR)<input type="text" value="" name="z260f" id="260f" onFocus="statwords(description[22],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font>
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr>
    <td>Projected Publication Date (NR)(263) :<a href="javascript:animatedcollapse.toggle('263')">ind</a> <div id="263" style="width: 150px; display:none" >ind1<input type="text" name="in2631" value="" maxlength="1" size="1" onFocus="statwords(description[12],650,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="in2632" value="" maxlength="1" size="1" onFocus="(description[12],650,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>$a Projected publication date (NR) <input type="text" value="" name="z263a" id="263a" onFocus="(description[23],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
</font></td></tr>
</table></html:form>
</div>

    </body>
</html>
