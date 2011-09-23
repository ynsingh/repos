<%-- 
    Document   : ucatl5
    Created on : Jul 14, 2011, 4:52:51 PM
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
       <title> Bibliographic Cataloguing According to MARC21 -- 5XX</title>
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

ddtabmenu.definemenu("ddtabs3", 5) //initialize Tab Menu #3 with 2nd tab selected
</script>


<script type="text/javascript">
animatedcollapse.addDiv('500', 'fade=1,height=30px')
animatedcollapse.addDiv('502', 'fade=1,height=30px')
animatedcollapse.addDiv('504', 'fade=1,height=30px')
animatedcollapse.addDiv('546', 'fade=1,height=30px')
animatedcollapse.addDiv('505', 'fade=1,height=30px')
animatedcollapse.addDiv('520', 'fade=1,height=30px')

animatedcollapse.addDiv('5003', 'fade=1,height=30px')

animatedcollapse.addDiv('502b', 'fade=1,height=30px')
animatedcollapse.addDiv('502c', 'fade=1,height=30px')
animatedcollapse.addDiv('502d', 'fade=1,height=30px')
animatedcollapse.addDiv('502g', 'fade=1,height=30px')
animatedcollapse.addDiv('502o', 'fade=1,height=30px')

animatedcollapse.addDiv('504b', 'fade=1,height=30px')

animatedcollapse.addDiv('505r', 'fade=1,height=30px')
animatedcollapse.addDiv('505g', 'fade=1,height=30px')
animatedcollapse.addDiv('505t', 'fade=1,height=30px')
animatedcollapse.addDiv('505u', 'fade=1,height=30px')

animatedcollapse.addDiv('520b', 'fade=1,height=30px')
animatedcollapse.addDiv('520c', 'fade=1,height=30px')
animatedcollapse.addDiv('520u', 'fade=1,height=30px')
animatedcollapse.addDiv('5202', 'fade=1,height=30px')
animatedcollapse.addDiv('5203', 'fade=1,height=30px')

animatedcollapse.addDiv('546b', 'fade=1,height=30px')
animatedcollapse.addDiv('5463', 'fade=1,height=30px')

animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}


animatedcollapse.init()

</script>
<script type="text/javascript">
var description=new Array()
description[0]='<b>Both indicator positions are undefined; each contains a blank (#).'
description[1]='Note that provides general information.ex- <b>Translated from German.'
description[2]='Part of the described material to which the field applies.'

description[3]='Entire text of the note.ex- <b>Thesis (M.A.)--University College, London, 1969.'
description[4]='Degree for which author was candidate. ex- <b>Ph.D'
description[5]='ex- <b>International Faith Theological Seminary, London'
description[6]='ex- <b>1972'
description[7]='Data that is not more appropriately contained in another defined subfield.ex- <b>Karl Schmidt`s thesis'
description[8]='Identifier assigned to a dissertation or theses for identification purposes. May be a local or global identifier. ex- <b>U 58.4033.'

description[9]='Entire text of the note.ex- <b>Bibliography: p. 238-239.'
description[10]='Number of references contained in the bibliography. Used to indicate the significance of a bibliography.ex- <b>19 '

description[11]='Display constant controller- <b>0 - Contents, 1 - Incomplete contents, 2 - Partial contents, 8 - No display constant generated '
description[12]='Level of content designation- <b># - Basic, 0 - Enhanced'
description[13]='Formatted contents note, whether complete, incomplete, or partial when the second indicator is value #. ex-<b> Future land use plan -- Recommended capital improvements -- Existing land use -- Existing zoning. '
description[14]='Any information other than the statement of responsibility or title.ex- <b>(16:35) --'
description[15]='Statement of responsibility of the article or part in the coding-enhanced contents note (second indicator is value 0).ex- <b>by L. H. Fellows.'
description[16]='Title used in the coding-enhanced contents note (second indicator is value 0).ex- <b>Region Neusiedlersee --'
description[17]='for example a URL or URN, which provides electronic access data in a standard syntax.ex- <b>http://lcweb.loc.gov/catdir/toc/99176484.html '

description[18]='Display constant controller-<b> # - Summary, 0 - Subject, 1 - Review, 2 - Scope and content, 3 - Abstract, 4 - Content advice, 8 - No display constant generated'
description[19]='<b>Undefined and contains a blank (#). '
description[20]='Text of the summary, abstract, review, etc. ex- <b>Describes associations made between different animal species for temporary gain or convenience as well as more permanent alliances formed for mutual survival. '
description[21]='Expansion of the brief summary recorded in subfield $a.ex- <b>ncludes films on control of rats, prairie dogs and porcupines; fish culture in the United States and pearl culture in Japan; inspection trip to Alaska by Service officials; life in a Boy Scout camp and Air Service bombing techniques in 1921. '
description[22]='Organization code or name of the agency or other source that supplied the data recorded in subfield $a. ex- <b>[Revealweb organization code] '
description[23]='a URL or URN, which provides electronic access data in a standard syntax.ex- <b>http://www.ojp.usdoj.gov/bjs/abstract/cchrie98.htm '
description[24]='Source code for the particular classification system used.ex- <b>[Source code for the content advice classification system used]  '
description[25]='Part of the described materials to which the field applies'

description[26]='<b>In Hungarian; summaries in French, German, or Russian. '
description[27]='Name of the alphabet, script, or information code that is used to record the language.ex- <b>Phonetic alphabet.'
description[28]='Part of the described materials to which the language note applies.ex- <b>John P. Harrington field notebooks'
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
    if(t.value!=5){

        document.getElementById("ucat5").submit();
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
                                      <%! Biblio marc500=new Biblio();
                                          Biblio marc502=new Biblio();
                                          Biblio marc504=new Biblio();
                                          Biblio marc505=new Biblio();
                                          Biblio marc520=new Biblio();
                                          Biblio marc546=new Biblio();
                                       %>
  <%
     if(request.getAttribute("500")!=null){
             marc500=(Biblio)request.getAttribute("500");}
     if(request.getAttribute("502")!=null){
             marc502=(Biblio)request.getAttribute("502");}
     if(request.getAttribute("504")!=null){
             marc504=(Biblio)request.getAttribute("504");}
      if(request.getAttribute("505")!=null){
             marc505=(Biblio)request.getAttribute("505");}
     if(request.getAttribute("520")!=null){
             marc520=(Biblio)request.getAttribute("520");}
     if(request.getAttribute("546")!=null){
             marc546=(Biblio)request.getAttribute("546");}

     %>

     <div style="position:absolute;left:80%;top:18%;">
                                         <table>
                                     <tr><td></td><td align="right"><a href="<%=request.getContextPath() %>/marccommit.do"><input type="submit" value="Commit Data" /></a></td></tr>
                                     </table>
                                     </div>
<div style="position:absolute;left:5%;top:25%;width:90%;border:1px #C0C0C0 solid;background: #f5fffa;">
<html:form styleId="ucat5" action="/ucataction5.do" method="post">
<table width="100%" cellspacing="5" >
  <tr><input type="hidden" value="" name="zclick" id="zclick" /></tr>
    <tr>
    <td>General Note (R)(500) : <a href="javascript:animatedcollapse.toggle('500')">ind</a> <div id="500" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc500.getIndicator1() %>" name="in5001" maxlength="1" size="1" onFocus="setObj(description[0],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc500.getIndicator2() %>" name="in5002" maxlength="1" size="1" onFocus="setObj(description[0],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>$a General note (NR)<input type="text" value="<%=marc500.get$a() %>" name="z500a" id="500a" onFocus="setObj(description[1],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('5003')">$3 </a>

<div id="5003" style=" background: #FDF5E6; display:none">
Materials specified (NR)<input type="text" value="<%=marc500.get$3() %>" name="z5003" id="5003" onFocus="setObj(description[2],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>
<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr>
    <td>Dissertation note (R)(502) : <a href="javascript:animatedcollapse.toggle('502')">ind</a> <div id="502" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc502.getIndicator1() %>" name="in5021" maxlength="1" size="1" onFocus="setObj(description[0],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc502.getIndicator2() %>" name="in5022" maxlength="1" size="1" onFocus="setObj(description[0],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Dissertation note (R)<input type="text" value="<%=marc502.get$a() %>" name="z502a" id="502a" onFocus="setObj(description[3],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />

<a href="javascript:animatedcollapse.toggle('502b')">$b </a>

<div id="502b" style=" background: #FDF5E6; display:none">
Degree type (NR)<input type="text" value="<%=marc502.get$b() %>" name="z502b" id="502b" onFocus="setObj(description[4],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('502c')">$c </a>

<div id="502c" style=" background: #FDF5E6; display:none">
Name of granting institution (NR)<input type="text" value="<%=marc502.get$c() %>" name="z502c" id="502c" onFocus="setObj(description[5],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('502d')">$d </a>

<div id="502d" style=" background: #FDF5E6; display:none">
Year degree granted (NR)<input type="text" value="<%=marc502.get$d() %>" name="z502d" id="502d" onFocus="setObj(description[6],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('502g')">$g </a>

<div id="502g" style=" background: #FDF5E6; display:none">
Miscellaneous information (R)<input type="text" value="<%=marc502.get$g() %>" name="z502g" id="502g" onFocus="setObj(description[7],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('502o')">$o </a>

<div id="502o" style=" background: #FDF5E6; display:none">
Dissertation identifier (R)<input type="text" value="<%=marc502.get$o() %>" name="z502o" id="502o" onFocus="setObj(description[8],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr>
    <td>Bibliography, Etc. Note (R)(504) : <a href="javascript:animatedcollapse.toggle('504')">ind</a> <div id="504" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc504.getIndicator1() %>" name="in5041" maxlength="1" size="1" onFocus="setObj(description[0],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc504.getIndicator2() %>" name="in5042" maxlength="1" size="1" onFocus="setObj(description[0],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Bibliography, etc. note (NR) <input type="text" value="<%=marc504.get$a() %>" name="z504a" id="504a" onFocus="setObj(description[9],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />

<a href="javascript:animatedcollapse.toggle('504b')">$b </a>

<div id="504b" style=" background: #FDF5E6; display:none">
Number of references (NR) <input type="text" value="<%=marc504.get$b() %>" name="z504b" id="504b" onFocus="setObj(description[10],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</td></tr>
<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr>
    <td>Formatted Contents Note (R)(505) : <a href="javascript:animatedcollapse.toggle('505')">ind</a> <div id="505" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc505.getIndicator1() %>" name="in5051" maxlength="1" size="1" onFocus="setObj(description[11],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc505.getIndicator2() %>" name="in5052" maxlength="1" size="1" onFocus="setObj(description[12],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Formatted contents note (NR) <input type="text" value="<%=marc505.get$a() %>" name="z505a" id="505a" onFocus="setObj(description[13],'override',800,50)" onBlur="clearTimeout(openTimer);stopIt()" />

<a href="javascript:animatedcollapse.toggle('505g')">$g </a>

<div id="505g" style=" background: #FDF5E6; display:none">
Miscellaneous information (R) <input type="text" value="<%=marc505.get$g() %>" name="z505g" id="505g" onFocus="setObj(description[14],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('505r')">$r </a>

<div id="505r" style=" background: #FDF5E6; display:none">
Statement of responsibility (R) <input type="text" value="<%=marc505.get$r() %>" name="z505r" id="505r" onFocus="setObj(description[15],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('505t')">$t </a>
<div id="505t" style=" background: #FDF5E6; display:none">
Title (R) <input type="text" value="<%=marc505.get$t() %>" name="z505t" id="505t" onFocus="setObj(description[16],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('505u')">$u </a>

<div id="505u" style=" background: #FDF5E6; display:none">
Uniform Resource Identifier (R) <input type="text" value="<%=marc505.get$u() %>" name="z505u" id="505u" onFocus="setObj(description[17],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>Summary, etc. (R)(520) : <a href="javascript:animatedcollapse.toggle('520')">ind</a> <div id="520" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc520.getIndicator1() %>" name="in5201" maxlength="1" size="1" onFocus="setObj(description[18],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc520.getIndicator2() %>" name="in5202" maxlength="1" size="1" onFocus="setObj(description[19],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Summary, etc. (NR) <input type="text" value="<%=marc520.get$a() %>" name="z520a" id="520a" onFocus="setObj(description[20],'override',800,90)" onBlur="clearTimeout(openTimer);stopIt()" />

<a href="javascript:animatedcollapse.toggle('520b')">$b </a>

<div id="520b" style=" background: #FDF5E6; display:none">
Expansion of summary note (NR) <input type="text" value="<%=marc520.get$b() %>" name="z520b" id="520b" onFocus="setObj(description[21],'override',800,90)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('520c')">$c </a>

<div id="520c" style=" background: #FDF5E6; display:none">
Assigning source (NR) <input type="text" value="<%=marc520.get$c() %>" name="z520c" id="520c" onFocus="setObj(description[22],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('520u')">$u </a>

<div id="520u" style=" background: #FDF5E6; display:none">
Uniform Resource Identifier (R) <input type="text" value="<%=marc520.get$u() %>" name="z520u" id="520u"onFocus="setObj(description[23],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('5202')">$2 </a>
<div id="5202" style=" background: #FDF5E6; display:none">
Source (NR) <input type="text" value="<%=marc520.get$2() %>" name="z5202" id="5202" onFocus="setObj(description[24],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" >
</div>

<a href="javascript:animatedcollapse.toggle('5203')">$3 </a>
<div id="5203" style=" background: #FDF5E6; display:none">
Materials specified (NR) <input type="text" value="<%=marc520.get$3() %>" name="z5203" id="5203" onFocus="setObj(description[25],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr>
    <td>Language Note (R)(546) : <a href="javascript:animatedcollapse.toggle('546')">ind</a> <div id="546" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc546.getIndicator1() %>" name="in5461" maxlength="1" size="1" onFocus="setObj(description[0],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc546.getIndicator2() %>" name="in5462" maxlength="1" size="1" onFocus="setObj(description[0],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Language Note (NR) <input type="text" value="<%=marc546.get$a() %>" name="z546a" id="546a" onFocus="setObj(description[26],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />

<a href="javascript:animatedcollapse.toggle('546b')">$b </a>

<div id="546b" style=" background: #FDF5E6; display:none">
Information code or alphabet (R) <input type="text" value="<%=marc546.get$b() %>" name="z546b" id="546b" onFocus="setObj(description[27],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('5463')">$3 </a>

<div id="5463" style=" background: #FDF5E6; display:none">
Materials specified (NR) <input type="text" value="<%=marc546.get$3() %>" name="z5463" id="5463" onFocus="setObj(description[28],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</td></tr>

</table></html:form>
</div>
    </body>
</html>
