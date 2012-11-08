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
        <title> Bibliographic Cataloguing According to MARC21</title>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ddtabmenufiles/ddtabmenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-1.4.2.min.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">

<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/animatedcollapse.js">

/***********************************************
* Animated Collapsible DIV- (c) muhammad zeeshan
***********************************************/

</script>
<% HashMap hm1 = new HashMap();
    Biblio bib1=new Biblio();
    Biblio bib2=new Biblio();
    Biblio bib3=new Biblio();
    Biblio bib4=new Biblio();
    Biblio bib5=new Biblio();
%>
<%
 String msg1=(String)request.getAttribute("msg1");
 hm1 = (HashMap)session.getAttribute("hsmp");
 if(hm1!=null){

  if(hm1.containsKey("1")){
       bib1=(Biblio)hm1.get("1");
        }
   if(hm1.containsKey("2")){
       bib2=(Biblio)hm1.get("2");
        }
   if(hm1.containsKey("3")){
       bib3=(Biblio)hm1.get("3");
        }
   if(hm1.containsKey("4")){
       bib4=(Biblio)hm1.get("4");
        }
   if(hm1.containsKey("5")){
       bib5=(Biblio)hm1.get("5");
        }
  }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ddtabmenufiles/solidblocksmenu.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/page.css" />
<script type="text/javascript">

ddtabmenu.definemenu("ddtabs3", 1) //initialize Tab Menu #3 with 1st tab selected
</script>


<script type="text/javascript">
animatedcollapse.addDiv('043', 'fade=1,height=30px')
animatedcollapse.addDiv('082', 'fade=1,height=30px')
animatedcollapse.addDiv('020', 'fade=1,height=30px')
animatedcollapse.addDiv('022', 'fade=1,height=30px')
animatedcollapse.addDiv('041', 'fade=1,height=30px')
animatedcollapse.addDiv('020c', 'fade=1,height=30px')
animatedcollapse.addDiv('020z', 'fade=1,height=30px')
animatedcollapse.addDiv('022y', 'fade=1,height=30px')
animatedcollapse.addDiv('022z', 'fade=1,height=30px')
animatedcollapse.addDiv('082b', 'fade=1,height=30px')
animatedcollapse.addDiv('0822', 'fade=1,height=30px')

animatedcollapse.addDiv('041b', 'fade=1,height=30px')
animatedcollapse.addDiv('041d', 'fade=1,height=30px')
animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}


animatedcollapse.init()

</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/helpdemo.js"></script>
<script type="text/javascript">
   function loadHelp()
    {
        window.status="Press F10 for Help";

    }

</script>
<script type="text/javascript">
    var description=new Array()
description[0]='This is tool-tip description 1'
description[1]='Enter ISBN for example 9780060799748 (trade)'
description[2]='Price or a brief statement of availability and any parenthetical qualifying information concerning the item. ex- Rs15.76 ($5.60 U.S.)'
description[3]='Canceled or invalid ISBN and any parenthetical qualifying information. ex- 0835200028 '

description[4]='undefined and contains a blank(#)'
description[5]='Valid ISSN for the continuing resource. <b>ex- 0376-4583'
description[6]='Incorrect ISSN that has been associated with the continuing resource.<b> ex- 0046-2254 '
description[7]='Canceled ISSN that is associated with the continuing resource. <b>ex- 0361-7106 '

description[8]='Language code recorded in 008/35-37 (Language) unless 008/35-37 contains blanks (###) or the code "zxx". <b>ex- $aeng $afre $ager '
description[9]='recorded in English alphabetical order. ex- $aeng$bfre $bger$bspa'
description[10]='for the audible portion of an item, usually the sung or spoken content of a sound recording or computer file. '

description[11]='Both indicator positions are undefined; each contains a blank (#).'
description[12]='Seven-character MARC code for a geographic area. <b>ex-  n-us---'
description[13]='Type of edition: <b>0 - Full edition, 1 - Abridged edition'
description[14]='Source of classification number: <b>  # - No information provided, 0 - Assigned by LC, 4 - Assigned by agency other than LC'
description[15]='for ex <b>343.7306/8 '
description[16]='Item number portion of the number.'
description[17]='Number of the edition of the Dewey classification schedules.<b>ex- 22'
description[18]='Level of international interest <b> # - No level specified,  0 - Continuing resource of international interest, 1 - Continuing resource not of international interest '
description[19]='Translation indication  <b># - No information provided, 0 - Item not a translation/does not include a translation, 1 - Item is or includes a translation'
description[20]='Source of code  <b># - MARC language code, 7 - Source specified in subfield $2'

</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/tooltip.js">

/***********************************************
* ToolTip layer - (c) muhammad zeeshan
***********************************************/

</script>
<% session.setAttribute("tag0", "1");%>
</head>
    <jsp:include page="/admin/header.jsp"></jsp:include>
<body onload="loadHelp()">
    <div
   style="  top:20%;
   left:10%;
   right:10%;border: solid 1px black;
      position: absolute;

      visibility: show;">

        <layer name="nsviewer" bgcolor="#FDF5E6" style="border-width:thin;z-index:1"></layer>
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
   // alert(t);
    if(t.value!=0){
        
        document.getElementById("cat0").submit();
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
You are on MARC Page : 0 Tag Page
                                   

<div>

<html:form styleId="cat0" action="/catl0.do" method="post">
    <table height="400px"><tr><td valign="top" >&nbsp;&nbsp;&nbsp;
<table width="100%"   cellspacing="5"  >
    <tr><input type="hidden" value="" name="zclick" id="zclick" /></tr>

<tr>
<td>&nbsp;&nbsp;&nbsp;International Standard Book Number (020) : <a href="javascript:animatedcollapse.toggle('020')">ind</a> <div id="020" style="width: 150px; display:none;" >&nbsp;&nbsp;&nbsp;ind1<input type="text" value="#" name="in0201" maxlength="1" size="1" onfocus="statwords(description[11],550,30);"  onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="#" name="in0202"  maxlength="1" size="1" onfocus="statwords(description[11],550,30);"  onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
  
$a ISBN (NR) <input type="text" value="<% if(request.getAttribute("isbn")!=null){
                  out.println(request.getAttribute("isbn"));     
        }else if(bib1.get$a()!=null){%> <%= bib1.get$a() %><%}%>" name="z020" id="020" onfocus="statwords(description[1],550,30);"  onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('020c')">$c </a>

<div id="020c" style=" background: #FDF5E6; display:none">
Term of Availability(NR) <input type="text" value="<% if(bib1.get$c()!=null){%><%= bib1.get$c() %><%}%>" name="z020c" id="020c" onFocus="statwords(description[2],900,30);" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('020z')">$z </a>
<div id="020z" style=" background: #FDF5E6; display:none">
Canceled or Invalid number(R) <input type="text" value="<% if(bib1.get$z()!=null){%><%= bib1.get$z() %><%}%>" name="z020z" id="020z" onFocus="statwords(description[3],650,30);" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font>
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>&nbsp;&nbsp;&nbsp;International Standard  Serial Number(022) : <a href="javascript:animatedcollapse.toggle('022')">ind</a> <div id="022" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" name="in0221" value="<% if(bib2.getIndicator1()!=null){%><%= bib2.getIndicator1() %><%}%>" maxlength="1" size="1" onFocus="statwords(description[18],750,50)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="#" name="in0222" maxlength="1" size="1" onFocus="statwords(description[4],650,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a ISSN (NR)<input type="text" value="<% if(bib2.get$a()!=null){%><%= bib2.get$a() %><%}%>" name="z022" id="022" onFocus="statwords(description[5],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('022y')">$y</a>
<div id="022y" style=" background: #FDF5E6; display:none">
Incorrect ISSN(R) <input type="text" value="<% if(bib2.get$y()!=null){%><%= bib2.get$y() %><%}%>" name="z022y" id="022y" onFocus="statwords(description[6],650,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('022z')"> $z</a>
<div id="022z" style=" background: #FDF5E6; display:none">
canceled ISSN(R) <input type="text" value="<% if(bib2.get$z()!=null){%><%= bib2.get$z() %><%}%>" name="z022z" id="022z" onFocus="statwords(description[7],650,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font>
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr><td>&nbsp;&nbsp;&nbsp;Language Code (NR)(041) : <a href="javascript:animatedcollapse.toggle('041')">ind</a> <div id="041" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text"  maxlength="1" value="<% if(bib3.getIndicator1()!=null){%><%= bib3.getIndicator1() %><%}%>" name="in0411" onFocus="statwords(description[19],750,40)" onBlur="clearTimeout(openTimer);stopIt()" id="041i2" size="1" /> ind2<input type="text" value="<% if(bib3.getIndicator1()!=null){%><%= bib3.getIndicator1() %><%}%>"  maxlength="1" name="in0412" onFocus="statwords(description[20],750,30)" onBlur="clearTimeout(openTimer);stopIt()" id="041i2" size="1" /></div></td>
<td>
$a Language code of text/sound track or
separate title(NR)<input type="text" value="<% if(bib3.get$a()!=null){%><%= bib3.get$a() %><%}%>" name="z041" id="041" onFocus="statwords(description[8],650,45)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('041b')">$b</a>

<div id="041b" style=" background: #FDF5E6; display:none">
Language code of summary or abstract/overprinted
title or subtitle
(NR) <input type="text" value="<% if(bib3.get$a()!=null){%><%= bib3.get$b() %><%}%>" name="z041b" id="041b" onFocus="statwords(description[9],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('041d')"> $d</a>
<div id="041d" style=" background: #FDF5E6; display:none">
Language code of sung or spoken text (NR) <input type="text" value="<% if(bib3.get$d()!=null){%><%= bib3.get$d() %><%}%>" name="z041d" id="041d" onFocus="statwords(description[10],750,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font>
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr><td>&nbsp;&nbsp;&nbsp;Geographic Area Code(NR) (043) : <a href="javascript:animatedcollapse.toggle('043')">ind</a> <div id="043" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" value="#" name="in0431" maxlength="1"  size="1" onFocus="statwords(description[11],750,30)" onBlur="clearTimeout(openTimer);stopIt()"  /> ind2<input type="text" value="#" name="in0432" id="in0432" maxlength="1" size="1" onFocus="statwords(description[11],750,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>$a Geographic Area Code(R)<input type="text" value="<% if(bib4.get$a()!=null){%><%= bib4.get$a() %><%}%>" name="z043" id="043" onFocus="statwords(description[12],650,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr><td>&nbsp;&nbsp;&nbsp;Dewey Decimal Classification Number(R)(082) : <a href="javascript:animatedcollapse.toggle('082')">ind</a> <div id="082" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" name="in0821" maxlength="1"  size="1" onFocus="statwords(description[13],650,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<% if(bib5.getIndicator2()!=null){%><%= bib5.getIndicator2() %><%}%>" name="in0822" maxlength="1" size="1" onFocus="statwords(description[14],650,50)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>$a *Classification number(R)<input type="text" value="<% if(bib5.get$a()!=null){%><%= bib5.get$a() %><%}%>" name="z082" id="082" onFocus="statwords(description[15],550,30)" onBlur="clearTimeout(openTimer);stopIt()"  />
<font size="2">
<a href="javascript:animatedcollapse.toggle('082b')">$b</a>
<div id="082b" style=" background: #FDF5E6; display:none">
Item number (NR) <input type="text" value="<% if(bib5.get$b()!=null){%><%= bib5.get$b() %><%}%>" name="z082b" id="082b" onFocus="statwords(description[16],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('0822')"> $2</a>
<div id="0822" style=" background: #FDF5E6; display:none">
Edition number (NR) <input type="text" value="<% if(bib5.get$2()!=null){%><%= bib5.get$2() %><%}%>" name="z0822" id="0822" onFocus="statwords(description[17],550,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font>
</td></tr>
<tr><td><%
if(msg1!=null)
       {%>
       <p class="err"><%=msg1%></p><%}
%></td></tr>
</table></td></tr></table>
</html:form>
</div>
    </div>
    </body>
</html>
