<%-- 
    Document   : catl1
    Created on : Mar 29, 2011, 6:21:22 PM
    Author     : zeeshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.myapp.struts.hbm.Biblio"%>
<html>
    <head>
       <title> Bibliographic Cataloging According to MARC21 -- 1XX</title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ddtabmenufiles/ddtabmenu.js"></script>



<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/animatedcollapse.js">

/***********************************************
* Animated Collapsible DIV- (c) muhammad zeeshan
***********************************************/

</script>
<% HashMap hm1 = new HashMap();
    Biblio bib1=new Biblio();
    Biblio bib2=new Biblio();
    Biblio bib3=new Biblio();
%>
<%
 String msg1=(String)request.getAttribute("msg1");
 hm1 = (HashMap)session.getAttribute("hsmp");

  if(hm1.containsKey("6")){
       bib1=(Biblio)hm1.get("6");
        }
   if(hm1.containsKey("7")){
       bib2=(Biblio)hm1.get("7");
        }
   if(hm1.containsKey("8")){
       bib3=(Biblio)hm1.get("8");
        }
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ddtabmenufiles/solidblocksmenu.css" />
<script type="text/javascript">

ddtabmenu.definemenu("ddtabs3", 2) //initialize Tab Menu #3 with 2nd tab selected
</script>


<script type="text/javascript">

animatedcollapse.addDiv('100', 'fade=1,height=30px')
animatedcollapse.addDiv('100d', 'fade=1,height=30px')
animatedcollapse.addDiv('100q', 'fade=1,height=30px')
animatedcollapse.addDiv('100c', 'fade=1,height=30px')
animatedcollapse.addDiv('100u', 'fade=1,height=30px')
animatedcollapse.addDiv('100j', 'fade=1,height=30px')
animatedcollapse.addDiv('1000', 'fade=1,height=30px')

animatedcollapse.addDiv('110', 'fade=1,height=30px')
animatedcollapse.addDiv('110d', 'fade=1,height=30px')
animatedcollapse.addDiv('110b', 'fade=1,height=30px')
animatedcollapse.addDiv('110c', 'fade=1,height=30px')
animatedcollapse.addDiv('110u', 'fade=1,height=30px')
animatedcollapse.addDiv('110k', 'fade=1,height=30px')
animatedcollapse.addDiv('1100', 'fade=1,height=30px')
animatedcollapse.addDiv('1104', 'fade=1,height=30px')
animatedcollapse.addDiv('110n', 'fade=1,height=30px')
animatedcollapse.addDiv('110p', 'fade=1,height=30px')
animatedcollapse.addDiv('110t', 'fade=1,height=30px')

animatedcollapse.addDiv('130', 'fade=1,height=30px')
animatedcollapse.addDiv('130d', 'fade=1,height=30px')
animatedcollapse.addDiv('130r', 'fade=1,height=30px')
animatedcollapse.addDiv('130m', 'fade=1,height=30px')
animatedcollapse.addDiv('130f', 'fade=1,height=30px')
animatedcollapse.addDiv('130k', 'fade=1,height=30px')
animatedcollapse.addDiv('130h', 'fade=1,height=30px')
animatedcollapse.addDiv('130l', 'fade=1,height=30px')
animatedcollapse.addDiv('130n', 'fade=1,height=30px')
animatedcollapse.addDiv('130p', 'fade=1,height=30px')
animatedcollapse.addDiv('130s', 'fade=1,height=30px')
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
description[0]='Type of personal name entry element. 0 - Forename, 1 - Surname, 3 - Family name'
description[1]='# - Undefined '
description[2]='ex- Adams, Henry,'
description[3]='ex- the Baptist, Saint.'
description[4]='ex- 1837-1913,'
description[5]='ex- Follower of '
description[6]='ex- (Hilda Doolittle),'
description[7]='ex- Chemistry Dept., American University. '
description[8]='ex- (DE-101c)310008891'

description[9]='Type of corporate name entry element. 0 - Inverted name, 1 - Jurisdiction name, 2 - Name in direct order '
description[10]='# - Undefined '
description[11]='ex- Pennsylvania.'
description[12]='ex- State Board of Examiners of Nursing Home Administrators. '
description[13]='ex- Rome, Italy'
description[14]='ex- 1899'
description[15]='ex- Manuscript'
description[16]='ex- (10th :'
description[17]='ex- Aulendorf Codex.'
description[18]='ex-  (DE-101b)200568-2 '
description[19]='ex- Ley que aprueba el plan regional urbano de Guadalajara, 1979-1983. '
description[20]='ex- 5205 Port Royal Road, Springfield, VA 22161. '
description[21]='ex- pop'

description[22]='Nonfiling characters. 0-9 - Number of nonfiling characters'
description[23]='ex- Chanson de Roland. '
description[24]='ex- (1950)'
description[25]='ex- 1970. '
description[26]='ex- Sound recording. '
description[27]='ex- Selections.'
description[28]='ex- violin,string orchestra'
description[29]='ex- Serie A2,'
description[30]='ex- Rgveda.'
description[31]='ex- Italian & Sanskrit.'
description[32]='ex- D major. '
description[33]='ex- Revised Standard.'
</script>

<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/tooltip.js">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/page.css" />
    

/***********************************************
* Animated Collapsible DIV- (c) muhammad zeeshan
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
     //alert("func1"+t);
     document.getElementById("zclick").value= t;

     func2(t);

    }

function func2(t){
    //alert(t);
    if(t.value!=1){
        //alert("about to submit");
        document.getElementById("cat1").submit();
       // alert("submitted! ");
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
<li><a href="<%=request.getContextPath()%>/cataloguing/catl9.jsp" onclick="func1(9)" rel="sb9">9</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/marchome.do"  rel="home">HOME</a></li>
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


<div style="position:absolute;left:5%;top:25%;width:90%;border:1px #C0C0C0 solid;background: #f5fffa;">
    <html:form styleId="cat1" action="/cataction1.do" method="post">
<table width="100%" cellspacing="5"  >
    <tr><input type="hidden" value="" name="zclick" id="zclick" /></tr>
 <tr>
     <td>Main Entry : Personal Name(NR) (100) :<a href="javascript:animatedcollapse.toggle('100')">ind</a> <div id="100" style="width: 150px; display:none" >ind1<input type="text" name="in1001" value="<% if(bib1.getIndicator1()!=null){%><%= bib1.getIndicator1() %><%}%>"  maxlength="1" size="1" onFocus="statwords(description[0],650,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="in1002" value="#" maxlength="1" size="1" onFocus="statwords(description[1],550,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>$a *Personal name (NR)<input type="text" value="<% if(bib1.get$a()!=null){%><%= bib1.get$a() %><%}%>" name="z100" onFocus="statwords(description[2],550,30)" onBlur="clearTimeout(openTimer);stopIt()" id="100" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('100c')">$c </a>

<div id="100c" style="width: 500px; background: #FDF5E6; display:none">
Titles and words associated with a name (R)<input type="text" value="<% if(bib1.get$c()!=null){%><%= bib1.get$c() %><%}%>" name="z100c" id="100c" onFocus="statwords(description[3],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('100d')">$d </a>

<div id="100d" style="width: 500px; background: #FDF5E6; display:none">
Dates associated with name(NR) <input type="text" value="<% if(bib1.get$d()!=null){%><%= bib1.get$d() %><%}%>" name="z100d" id="100d" onFocus="statwords(description[4],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('100j')">$j </a>

<div id="100j" style="width: 500px; background: #FDF5E6; display:none">
Attribution qualifier (R) <input type="text" value="<% if(bib1.get$j()!=null){%><%= bib1.get$j() %><%}%>" name="z100j" id="100j" onFocus="statwords(description[5],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('100q')">$q </a>
<div id="100q" style="width: 500px; background: #FDF5E6; display:none">
Fuller form of name (NR)<input type="text" value="<% if(bib1.get$q()!=null){%><%= bib1.get$q() %><%}%>" name="z100q" id="100q"  onFocus="statwords(description[6],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('100u')">$u </a>
<div id="100u" style="width: 500px; background: #FDF5E6; display:none">
Affiliation (NR) <input type="text" value="<% if(bib1.get$u()!=null){%><%= bib1.get$u() %><%}%>" name="z100u" id="100u" onFocus="statwords(description[7],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('1000')">$0 </a>
<div id="1000" style="width: 500px; background: #FDF5E6; display:none">
Authority record control number (R)<input type="text" value="<% if(bib1.get$0()!=null){%><%= bib1.get$0() %><%}%>" name="z1000" id="1000" onFocus="statwords(description[8],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font>

</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr>
    <td>Main Entry-Corporate Name (NR) (110) :<a href="javascript:animatedcollapse.toggle('110')">ind</a> <div id="110" style="width: 150px; display:none" >ind1<input type="text" value="<% if(bib2.getIndicator1()!=null){%><%= bib2.getIndicator1() %><%}%>" name="in1101" maxlength="1" size="1" onFocus="statwords(description[9],750,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="in1102" value="#" maxlength="1" size="1" onFocus="statwords(description[10],550,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Corporate name or jurisdiction name as entry element (NR)<input type="text" value="<% if(bib2.get$a()!=null){%><%= bib2.get$a() %><%}%>" name="z110" id="110" onFocus="statwords(description[11],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">

<a href="javascript:animatedcollapse.toggle('110b')">$b </a>

<div id="110b" style="width: 500px; background: #FDF5E6; display:none">
Subordinate unit (R)<input type="text" value="<% if(bib2.get$b()!=null){%><%= bib2.get$b() %><%}%>" name="z110b" id="110b" onFocus="statwords(description[12],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('110c')">$c </a>

<div id="110c" style="width: 500px; background: #FDF5E6; display:none">
Location of meeting (NR) <input type="text" value="<% if(bib2.get$c()!=null){%><%= bib2.get$c() %><%}%>" name="z110c" id="110c" onFocus="statwords(description[13],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('110d')">$d </a>

<div id="110d" style="width: 500px; background: #FDF5E6; display:none">
Date of meeting or treaty signing (R)<input type="text" value="<% if(bib2.get$d()!=null){%><%= bib2.get$d() %><%}%>" name="z110d" id="110d" onFocus="statwords(description[14],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('110k')">$k </a>

<div id="110k" style="width: 500px; background: #FDF5E6; display:none">
Form subheading (R)<input type="text" value="<% if(bib2.get$k()!=null){%><%= bib2.get$k() %><%}%>" name="z110k" id="110k" onFocus="statwords(description[15],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('110n')">$n </a>
<div id="110n" style="width: 500px; background: #FDF5E6; display:none">
Number of part/section/meeting (R)<input type="text" value="<% if(bib2.get$n()!=null){%><%= bib2.get$n() %><%}%>" name="z110n" id="110n" onFocus="statwords(description[16],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('110p')">$p </a>
<div id="110p" style="width: 500px; background: #FDF5E6; display:none">
Name of part/section of a work (R)<input type="text" value="<% if(bib2.get$p()!=null){%><%= bib2.get$p() %><%}%>" name="z110p" id="110p" onFocus="statwords(description[17],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>


<a href="javascript:animatedcollapse.toggle('1100')">$0 </a>
<div id="1100" style="width: 500px; background: #FDF5E6; display:none">
Authority record control number (R)<input type="text" value="<% if(bib2.get$0()!=null){%><%= bib2.get$0() %><%}%>" name="z1100" id="1100" onFocus="statwords(description[18],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>


<a href="javascript:animatedcollapse.toggle('110t')">$t </a>
<div id="110t" style="width: 500px; background: #FDF5E6; display:none">
Title of a work (NR)<input type="text" value="<% if(bib2.get$t()!=null){%><%= bib2.get$t() %><%}%>" name="z110t" id="110t" onFocus="statwords(description[19],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('110u')">$u </a>
<div id="110u" style="width: 500px; background: #FDF5E6; display:none">
Affiliation (NR) <input type="text" value="<% if(bib2.get$u()!=null){%><%= bib2.get$u() %><%}%>" name="z110u" id="110u" onFocus="statwords(description[20],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('1104')">$4 </a>
<div id="1104" style="width: 500px; background: #FDF5E6; display:none">
Relator code (R) <input type="text" value="<% if(bib2.get$4()!=null){%><%= bib2.get$4() %><%}%>" name="z1104" id="1104" onFocus="statwords(description[21],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font>
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>


<tr>
    <td>Main Entry-Uniform Title (NR)(130) :<a href="javascript:animatedcollapse.toggle('130')">ind</a> <div id="130" style="width: 150px; display:none" >ind1<input type="text" name="in1301" value="<% if(bib3.getIndicator1()!=null){%><%= bib3.getIndicator1() %><%}%>" maxlength="1" size="1" onFocus="statwords(description[22],550,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="in1302" value="#" maxlength="1" size="1" onFocus="statwords(description[10],550,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Uniform title (NR) <input type="text" value="<% if(bib3.get$a()!=null){%><%= bib3.get$a() %><%}%>" name="z130" id="130" onFocus="statwords(description[23],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">

<a href="javascript:animatedcollapse.toggle('130d')">$d </a>

<div id="130d" style="width: 500px; background: #FDF5E6; display:none">
Date of treaty signing (R) <input type="text" value="<% if(bib3.get$d()!=null){%><%= bib3.get$d() %><%}%>" name="z130d" id="130d" onFocus="statwords(description[24],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('130f')">$f </a>

<div id="130f" style="width: 500px; background: #FDF5E6; display:none">
Date of a work (NR) <input type="text" value="<% if(bib3.get$f()!=null){%><%= bib3.get$f() %><%}%>" name="z130f" id="130f" onFocus="statwords(description[25],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('130h')">$h </a>

<div id="130h" style="width: 500px; background: #FDF5E6; display:none">
 Medium (NR)<input type="text" value="<% if(bib3.get$h()!=null){%><%= bib3.get$h() %><%}%>" name="z130h" id="130h" onFocus="statwords(description[26],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>


<a href="javascript:animatedcollapse.toggle('130k')">$k </a>

<div id="130k" style="width: 500px; background: #FDF5E6; display:none">
 Form subheading (R) <input type="text" value="<% if(bib3.get$k()!=null){%><%= bib3.get$k() %><%}%>" name="z130k" id="130k" onFocus="statwords(description[27],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('130m')">$m </a>
<div id="130m" style="width: 500px; background: #FDF5E6; display:none">
Medium of performance for music (R)<input type="text" value="<% if(bib3.get$m()!=null){%><%= bib3.get$m() %><%}%>" name="z130m" id="130m" onFocus="statwords(description[28],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('130n')">$n </a>
<div id="130n" style="width: 500px; background: #FDF5E6; display:none">
Number of part/section of a work (R)<input type="text" value="<% if(bib3.get$n()!=null){%><%= bib3.get$n() %><%}%>" name="z130n" id="130n" onFocus="statwords(description[29],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('130p')">$p </a>
<div id="130p" style="width: 500px; background: #FDF5E6; display:none">
Name of part/section of a work (R)<input type="text" value="<% if(bib3.get$p()!=null){%><%= bib3.get$p() %><%}%>" name="z130p" id="130p" onFocus="statwords(description[30],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>


<a href="javascript:animatedcollapse.toggle('130l')">$l </a>
<div id="130l" style="width: 500px; background: #FDF5E6; display:none">
Language of a work (NR) <input type="text" value="<% if(bib3.get$l()!=null){%><%= bib3.get$l() %><%}%>" name="z130l" id="130l" onFocus="statwords(description[31],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>


<a href="javascript:animatedcollapse.toggle('130r')">$r </a>
<div id="130r" style="width: 500px; background: #FDF5E6; display:none">
Key for music (NR) <input type="text" value="<% if(bib3.get$r()!=null){%><%= bib3.get$r() %><%}%>" name="z130r" id="130r" onFocus="statwords(description[32],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('130s')">$s </a>
<div id="130s" style="width: 500px; background: #FDF5E6; display:none">
Version (NR) <input type="text" value="<% if(bib3.get$s()!=null){%><%= bib3.get$s() %><%}%>" name="z130s" id="130s" onFocus="statwords(description[33],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

</font>
</td></tr>
<tr><td><%
if(msg1!=null)
       {%>
       <p class="err"><%=msg1%></p><%}
%></td></tr>
</table></html:form>
</div>
    </body>
</html>
