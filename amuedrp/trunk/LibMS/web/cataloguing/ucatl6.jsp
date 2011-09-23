<%-- 
    Document   : ucatl6
    Created on : Jul 14, 2011, 5:55:17 PM
    Author     : zeeshan
--%>

<%@page import="com.myapp.struts.hbm.Biblio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
    <head>
       <title> Bibliographic Cataloguing According to MARC21 -- 6XX</title>

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

ddtabmenu.definemenu("ddtabs3", 6) //initialize Tab Menu #3 with 2nd tab selected
</script>


<script type="text/javascript">
animatedcollapse.addDiv('600', 'fade=1,height=30px')
animatedcollapse.addDiv('600b', 'fade=1,height=30px')
animatedcollapse.addDiv('600c', 'fade=1,height=30px')
animatedcollapse.addDiv('600d', 'fade=1,height=30px')

animatedcollapse.addDiv('650', 'fade=1,height=30px')
animatedcollapse.addDiv('650b', 'fade=1,height=30px')
animatedcollapse.addDiv('650c', 'fade=1,height=30px')
animatedcollapse.addDiv('650d', 'fade=1,height=30px')
animatedcollapse.addDiv('650e', 'fade=1,height=30px')
animatedcollapse.addDiv('6504', 'fade=1,height=30px')
animatedcollapse.addDiv('650v', 'fade=1,height=30px')
animatedcollapse.addDiv('650x', 'fade=1,height=30px')
animatedcollapse.addDiv('650y', 'fade=1,height=30px')
animatedcollapse.addDiv('6502', 'fade=1,height=30px')
animatedcollapse.addDiv('650z', 'fade=1,height=30px')

animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}


animatedcollapse.init()

</script>
<script type="text/javascript">
var description=new Array()
description[0]='Type of personal name entry element-<b> 0 - Forename, 1 - Surname, 3 - Family name '
description[1]='Thesaurus- <b>0 - Library of Congress Subject Headings, 1 - LC subject headings for children`s literature, 2 - Medical Subject Headings, 3 - National Agricultural Library subject authority file, 4 - Source not specified, 5 - Canadian Subject Headings, 6 - Répertoire de vedettes-matière, 7 - Source specified in subfield $2 '
description[2]='ex- <b>Pushkin, Aleksandr Sergeevich,'
description[3]='ex- <b>(Biblical prophet) '
description[4]='ex- <b>1799-1837'

description[5]='Level of subject- <b># - No information provided, 0 - No level specified, 1 - Primary, 2 - Secondary '
description[6]='Topical subject or a geographic name used as an entry element for a topical term. <b>BASIC (Computer program language) '
description[7]='Topical term that is entered under a geographic name contained in subfield $a.ex- <b>$aCaracas.$bBolivar Statue.'
description[8]='Time period during which an event occurred.'
description[9]='Specifies the relationship between the topical heading and the described materials, e.g.<b> depicted.'
description[10]='MARC code that specifies the relationship between a topical heading and the described materials'
description[11]='Form subdivision that designates a specific kind or genre of material as defined by the thesaurus being used. e.g. <b>Periodicals.'
description[12]='Subject subdivision that is not more appropriately contained in subfields $v, $y or $z.ex- $aRacetracks (Horse-racing)$zUnited States<b>$xHistory.'
description[13]='Subject subdivision that represents a period of time'
description[14]='Geographic subject subdivision. e.g. <b>Tunisia.'
description[15]='MARC code that identifies the source list from which the subject added entry was assigned. e.g. <b>lctgm'

</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/tooltip.js">

/***********************************************
* ToolTip layer - (c) muhammad zeeshan
***********************************************/

</script>


    </head>
    <body>
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
    if(t.value!=6){

        document.getElementById("ucat6").submit();
     alert("submitted! ");
}
}
</script>
                                          <h2 align="center">MARC Based Bibliographic Cataloging</h2>

<div id="ddtabs3" class="solidblockmenu">
<ul>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl0.jsp" onclick="func1(0)"  rel="sb0">0 (01X-09X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl1.jsp" onclick="func1(1)" rel="sb1">1 (1XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl2.jsp" onclick="func1(2)" rel="sb2">2 (20X-28X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl3.jsp" onclick="func1(3)" rel="sb3">3 (3XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl4.jsp" onclick="func1(4)" rel="sb4">4 (4XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl5.jsp" onclick="func1(5)" rel="sb5">5 (5XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl6.jsp" onclick="func1(6)" rel="sb6">6 (6XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl7.jsp" onclick="func1(7)" rel="sb7">7 (70X-78X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl8.jsp" onclick="func1(8)" rel="sb8">8 (80X-88X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/ucatl9.jsp" onclick="func1(9)" rel="sb9">9 (9XX)</a></li>
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
</FONT>
</DIV>

                                      <!-- Marc entries Starts from here . -->
                                       <%! Biblio marc600=new Biblio();
                                          Biblio marc650=new Biblio();

                                       %>
  <%
     if(request.getAttribute("600")!=null){
             marc600=(Biblio)request.getAttribute("600");}
     if(request.getAttribute("650")!=null){
             marc650=(Biblio)request.getAttribute("650");}

     %>

<div style="position:absolute;left:80%;top:18%;">
                                         <table>
                                     <tr><td></td><td align="right"><a href="<%=request.getContextPath() %>/marccommit.do"><input type="submit" value="Commit Data" /></a></td></tr>
                                     </table>
                                     </div>
<div style="position:absolute;left:5%;top:25%;width:90%;border:1px #C0C0C0 solid;background: #f5fffa;">

<html:form styleId="ucat6" action="/ucataction6.do" method="post">
<table width="100%" cellspacing="5" >
  <tr><input type="hidden" value="" name="zclick" id="zclick" /></tr>
<tr>
    <td>Subject Added Entry-Personal Name (R)(600) : <a href="javascript:animatedcollapse.toggle('600')">ind</a> <div id="600" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc600.getIndicator1() %>" name="in6001" maxlength="1" size="1" onFocus="setObj(description[0],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc600.getIndicator2() %>" name="in6002" maxlength="1" size="1" onFocus="setObj(description[1],'override',800,60)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Personal name (NR) <input type="text" value="<%=marc600.get$a() %>" name="z600a" id="600a" onFocus="setObj(description[2],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('600b')">$b </a>

<div id="600b" style=" background: #FDF5E6; display:none">
Numeration (NR) <input type="text" value="<%=marc600.get$b() %>" name="z600b" id="600b" />
</div>
<a href="javascript:animatedcollapse.toggle('600c')">$c </a>

<div id="600c" style=" background: #FDF5E6; display:none">
Titles and other words associated with a name (R)<input type="text" value="<%=marc600.get$c() %>" name="z600c" id="600c" onFocus="setObj(description[3],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('600d')">$d </a>

<div id="600d" style=" background: #FDF5E6; display:none">
Dates associated with a name (NR) <input type="text" value="<%=marc600.get$d() %>" name="z600d" id="600d" onFocus="setObj(description[4],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />

</div>
</font></td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>Subject Added Entry-Topical Term (R)(650) : <a href="javascript:animatedcollapse.toggle('650')">ind</a> <div id="650" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc650.getIndicator1() %>" name="in6501" maxlength="1" size="1" onFocus="setObj(description[5],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc650.getIndicator2() %>" name="in6502" maxlength="1" size="1" onFocus="setObj(description[1],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Topical term or geographic name entry element (NR) <input type="text" value="<%=marc650.get$a() %>" name="z650a" id="650a" onFocus="setObj(description[6],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('650b')">$b </a>

<div id="650b" style=" background: #FDF5E6; display:none">
Topical term following geographic name entry element (NR) <input type="text" value="<%=marc650.get$b() %>" name="z650b" id="650b" onFocus="setObj(description[7],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('650c')">$c </a>

<div id="650c" style=" background: #FDF5E6; display:none">
Location of event (NR) <input type="text" value="<%=marc650.get$c() %>" name="z650c" id="650c"  />
</div>

<a href="javascript:animatedcollapse.toggle('650d')">$d </a>

<div id="650d" style=" background: #FDF5E6; display:none">
Active dates (NR) <input type="text" value="<%=marc650.get$d() %>" name="z650d" id="650d" onFocus="setObj(description[8],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('650e')">$e</a>

<div id="650e" style=" background: #FDF5E6; display:none">
Relator term (R) <input type="text" value="<%=marc650.get$e() %>" name="z650e" id="650e" onFocus="setObj(description[9],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('6504')">$4 </a>

<div id="6504" style=" background: #FDF5E6; display:none">
Relator code (R)  <input type="text" value="<%=marc650.get$4() %>" name="z6504" id="6504" onFocus="setObj(description[10],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('650v')">$v </a>

<div id="650v" style=" background: #FDF5E6; display:none">
Form subdivision (R) <input type="text" value="<%=marc650.get$v() %>" name="z650v" id="650v" onFocus="setObj(description[11],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('650x')">$x </a>
<div id="650x" style=" background: #FDF5E6; display:none">
General subdivision (R) <input type="text" value="<%=marc650.get$x() %>" name="z650x" id="650x" onFocus="setObj(description[12],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('650y')">$y </a>
<div id="650y" style=" background: #FDF5E6; display:none">
Chronological subdivision (R) <input type="text" value="<%=marc650.get$y() %>" name="z650y" id="650y" onFocus="setObj(description[13],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>


<a href="javascript:animatedcollapse.toggle('650z')">$z </a>
<div id="650z" style=" background: #FDF5E6; display:none">
Geographic subdivision (R) <input type="text" value="<%=marc650.get$z() %>" name="z650z" id="650z" onFocus="setObj(description[14],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('6502')">$2 </a>
<div id="6502" style=" background: #FDF5E6; display:none">
Source of heading or term (NR) <input type="text" value="<%=marc650.get$2() %>" name="z6502" id="6502" onFocus="setObj(description[15],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

</font></td></tr>


</table></html:form>
</div>
    </body>
</html>
