<%-- 
    Document   : ucatl7
    Created on : Jul 16, 2011, 11:11:46 AM
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

ddtabmenu.definemenu("ddtabs3", 7) //initialize Tab Menu #3 with 2nd tab selected
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
    if(t.value!=7){

        document.getElementById("ucat7").submit();
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
                                      <%! Biblio marc700=new Biblio();
                                          Biblio marc740=new Biblio();

                                       %>
  <%
     if(request.getAttribute("700")!=null){
             marc700=(Biblio)request.getAttribute("700");}
     if(request.getAttribute("740")!=null){
             marc740=(Biblio)request.getAttribute("740");}

     %>

     <div style="position:absolute;left:80%;top:18%;">
                                         <table>
                                     <tr><td></td><td align="right"><a href="<%=request.getContextPath() %>/marccommit.do"><input type="submit" value="Commit Data" /></a></td></tr>
                                     </table>
                                     </div>
<div style="position:absolute;left:5%;top:25%;width:90%;border:1px #C0C0C0 solid;background: #f5fffa;">
<html:form styleId="ucat7" action="/ucataction7.do" method="post">
<table width="100%" cellspacing="5" >
  <tr><input type="hidden" value="" name="zclick" id="zclick" /></tr>
<tr>
    <td>Added Entry-Personal Name (R)(700) : <a href="javascript:animatedcollapse.toggle('700')">ind</a> <div id="700" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc700.getIndicator1() %>" name="in7001" maxlength="1" size="1" onFocus="setObj(description[0],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc700.getIndicator2() %>" name="in7002" maxlength="1" size="1" onFocus="setObj(description[1],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Personal name (NR) <input type="text" value="<%=marc700.get$a() %>" name="z700a" id="700a" onFocus="setObj(description[2],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('700b')">$b </a>

<div id="700b" style=" background: #FDF5E6; display:none">
Numeration (NR) <input type="text" value="<%=marc700.get$b() %>" name="z700b" id="700b" onFocus="setObj(description[3],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700c')">$c </a>

<div id="700c" style=" background: #FDF5E6; display:none">
Titles and other words associated with a name (R)<input type="text" value="<%=marc700.get$c() %>" name="z700c" id="700c" onFocus="setObj(description[4],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700d')">$d </a>

<div id="700d" style=" background: #FDF5E6; display:none">
Dates associated with a name (NR) <input type="text" value="<%=marc700.get$d() %>" name="z700d" id="700d" onFocus="setObj(description[5],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700e')">$e </a>

<div id="700e" style=" background: #FDF5E6; display:none">
Relator term (R) <input type="text" value="<%=marc700.get$e() %>" name="z700e" id="700e" onFocus="setObj(description[6],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700f')">$f </a>

<div id="700f" style=" background: #FDF5E6; display:none">
Date of a work (NR) <input type="text" value="<%=marc700.get$f() %>" name="z700f" id="700f" onFocus="setObj(description[7],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700h')">$h </a>

<div id="700h" style=" background: #FDF5E6; display:none">
Medium (NR) <input type="text" value="<%=marc700.get$h() %>" name="z700h" id="700h" onFocus="setObj(description[8],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700k')">$k </a>

<div id="700k" style=" background: #FDF5E6; display:none">
Form subheading (R) <input type="text" value="<%=marc700.get$k() %>" name="z700k" id="700k" onFocus="setObj(description[9],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700l')">$l </a>

<div id="700l" style=" background: #FDF5E6; display:none">
Language of a work (NR) <input type="text" value="<%=marc700.get$l() %>" name="z700l" id="700l" onFocus="setObj(description[10],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700m')">$m </a>

<div id="700m" style=" background: #FDF5E6; display:none">
Medium of performance for music (R) <input type="text" value="<%=marc700.get$m() %>" name="z700m" id="700m" onFocus="setObj(description[11],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700n')">$n </a>

<div id="700n" style=" background: #FDF5E6; display:none">
Number of part/section of a work (R) <input type="text" value="<%=marc700.get$n() %>" name="z700n" id="700n" onFocus="setObj(description[12],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700p')">$p </a>

<div id="700p" style=" background: #FDF5E6; display:none">
Name of part/section of a work (R) <input type="text" value="<%=marc700.get$p() %>" name="z700p" id="700p" onFocus="setObj(description[13],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700r')">$r </a>

<div id="700r" style=" background: #FDF5E6; display:none">
Key for music (NR) <input type="text" value="<%=marc700.get$r() %>" name="z700r" id="700r" onFocus="setObj(description[14],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700s')">$s </a>

<div id="700s" style=" background: #FDF5E6; display:none">
Version (NR) <input type="text" value="<%=marc700.get$s() %>" name="z700s" id="700s" onFocus="setObj(description[15],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('700t')">$t </a>

<div id="700t" style=" background: #FDF5E6; display:none">
Title of a work (NR) <input type="text" value="<%=marc700.get$t() %>" name="z700t" id="700t" onFocus="setObj(description[16],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('7004')">$4 </a>

<div id="7004" style=" background: #FDF5E6; display:none">
Relator code (R) <input type="text" value="<%=marc700.get$4() %>" name="z7004" id="7004" onFocus="setObj(description[17],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>Added Entry-Uncontrolled Related/Analytical Title (R)(740) : <a href="javascript:animatedcollapse.toggle('740')">ind</a> <div id="740" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc740.getIndicator1() %>" name="in7401" maxlength="1" size="1" onFocus="setObj(description[18],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc740.getIndicator2() %>" name="in7402" maxlength="1" size="1" onFocus="setObj(description[19],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Uncontrolled related/analytical title (NR) <input type="text" value="<%=marc740.get$a() %>" name="z740a" id="740a" onFocus="setObj(description[20],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('740h')">$h </a>

<div id="740h" style=" background: #FDF5E6; display:none">
Medium (NR) <input type="text" value="<%=marc740.get$h() %>" name="z740h" id="740h" onFocus="setObj(description[21],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('740n')">$n </a>

<div id="740n" style=" background: #FDF5E6; display:none">
Number of part/section of a work (R) <input type="text" value="<%=marc740.get$n() %>" name="z740n" id="740n" onFocus="setObj(description[22],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('740p')">$p </a>

<div id="740p" style=" background: #FDF5E6; display:none">
Name of part/section of a work (R) <input type="text" value="<%=marc740.get$p() %>" name="z740p" id="740p" onFocus="setObj(description[23],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

</font></td></tr>

</table></html:form>
</div>
    </body>
</html>

