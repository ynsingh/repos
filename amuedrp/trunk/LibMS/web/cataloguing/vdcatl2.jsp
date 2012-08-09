<%@page import="com.myapp.struts.hbm.Biblio"%>
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

ddtabmenu.definemenu("ddtabs3", 2) //initialize Tab Menu #3 with 2nd tab selected
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
     <jsp:include page="/admin/header.jsp"></jsp:include>
    <body onload="search()"><div
   style="  top:15%;
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


function func1(t){

     document.getElementById("zclick").value= t;

     func2(t);

    }

function func2(t){
  //  alert(t);
    if(t.value!=2){

        document.getElementById("ucat2").submit();
    // alert("submitted! ");
}
}
</script>
                                           <h2 align="center"  class="headerStyle" style="height: 25px;">Delete MARC Based Bibliographic Cataloging</h2>

<div id="ddtabs3" class="header1" style="background-color: cyan;line-height: 26px;font-size: 13px;vertical-align: bottom" >


<a href="<%=request.getContextPath()%>/cataloguing/vdcatl0.jsp" style="text-decoration:none" onclick="func1(0)"  rel="sb0">0 (01X-09X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl1.jsp" style="text-decoration:none" onclick="func1(1)" rel="sb1">1 (1XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl2.jsp"  style="text-decoration:none" onclick="func1(2)" rel="sb2">2 (20X-28X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl3.jsp"  style="text-decoration:none" onclick="func1(3)" rel="sb3">3 (3XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl4.jsp" style="text-decoration:none" onclick="func1(4)" rel="sb4">4 (4XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl5.jsp" style="text-decoration:none" onclick="func1(5)" rel="sb5">5 (5XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl6.jsp" style="text-decoration:none" onclick="func1(6)" rel="sb6">6 (6XX)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl7.jsp" style="text-decoration:none" onclick="func1(7)" rel="sb7">7 (70X-78X)</a>&nbsp;|&nbsp;
<a href="<%=request.getContextPath()%>/cataloguing/vdcatl8.jsp" style="text-decoration:none" onclick="func1(8)" rel="sb8">8 (80X-88X)</a>&nbsp;|&nbsp;

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
</FONT>
</DIV>

                                      <!-- Marc entries Starts from here . -->
                                      <%! Biblio marc210=new Biblio();
                                          Biblio marc245=new Biblio();
                                          Biblio marc250=new Biblio();
                                          Biblio marc256=new Biblio();
                                          Biblio marc260=new Biblio();
                                          Biblio marc263=new Biblio();
                                       %>
  <%
     if(request.getAttribute("210")!=null){
             marc210=(Biblio)request.getAttribute("210");}
     if(request.getAttribute("245")!=null){
             marc245=(Biblio)request.getAttribute("245");}
     if(request.getAttribute("250")!=null){
             marc250=(Biblio)request.getAttribute("250");}
      if(request.getAttribute("256")!=null){
             marc256=(Biblio)request.getAttribute("256");}
     if(request.getAttribute("260")!=null){
             marc260=(Biblio)request.getAttribute("260");}
     if(request.getAttribute("263")!=null){
             marc263=(Biblio)request.getAttribute("263");}

     %>
<!-- Title statements fields 20x-24x -->
<div>
    <html:form styleId="ucat2" action="/vdaction.do" method="post">
          <table height="400px"><tr><td valign="top" >&nbsp;&nbsp;&nbsp;
<table width="100%" cellspacing="5" >
    <tr><input type="hidden"  name="zclick" id="zclick" /></tr>
<tr><td>&nbsp;&nbsp;&nbsp;Abbreviated Title (210) : <a href="javascript:animatedcollapse.toggle('210')">ind</a> <div id="210" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" name="in2101" readonly value="<%=marc210.getIndicator1()==null?"":marc210.getIndicator1() %>" maxlength="1" size="1" onFocus="setObj(description[0],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="in2102" readonly value="<%=marc210.getIndicator2()==null?"":marc210.getIndicator2()  %>" maxlength="1" size="1" onFocus="setObj(description[1],'override',550,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
    $a Abbreviated Title (NR)<input type="text" readonly value="<%=marc210.get$a()==null?"":marc210.get$a() %>" name="z210a" id="210a" onFocus="setObj(description[2],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('210b')">$b </a>

<div id="210b" style="width: 500px; background: #FDF5E6; display:none">
    Qualifying Information (NR) <input type="text" readonly value="<%=marc210.get$b()==null?"":marc210.get$b() %>" name="z210b" id="210b" onFocus="setObj(description[3],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('2102')">$2 </a>
<div id="2102" style="width: 500px; background: #FDF5E6; display:none">
    Source (R) <input type="text" readonly value="<%=marc210.get$2()==null?"":marc210.get$2() %>" name="z2102" id="2102"  onFocus="setObj(description[4],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>


<tr>
    <td>&nbsp;&nbsp;&nbsp;Title Statement (245) :<a href="javascript:animatedcollapse.toggle('245')">ind</a> <div id="245" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" readonly name="in2451" value="<%=marc245.getIndicator1()==null?"":marc245.getIndicator1() %>" maxlength="1" size="1" onFocus="setObj(description[5],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="2452" readonly value="<%=marc245.getIndicator2()==null?"":marc245.getIndicator2() %>" maxlength="1" size="1" onFocus="setObj(description[6],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
    <td>$a Title (NR)<input type="text" readonly value="<% if(session.getAttribute("title")!=null){
                  out.println(session.getAttribute("title"));
        }%>" name="z245a" id="245a" onFocus="setObj(description[7],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('245b')">$b</a>
<div id="245b" style="width: 500px; background: #FDF5E6; display:none">
    Remainder of title (NR)<input type="text" readonly value="<%=marc245.get$b()==null?"":marc245.get$b() %>" name="z245b" id="245b" onFocus="setObj(description[8],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('245c')"> $c</a>
<div id="245c" style="width: 500px; background: #FDF5E6; display:none">
    Statement of responsibility (NR)<input type="text" readonly value="<%=marc245.get$c()==null?"":marc245.get$c() %>" name="z245c" id="245c" onFocus="setObj(description[9],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('245n')"> $n</a>
<div id="245n" style="width: 500px; background: #FDF5E6; display:none">
    Number of Part/Section of Work(NR)<input type="text" readonly  value="<%=marc245.get$n()==null?"":marc245.get$n() %>" name="z245n" id="245n" onFocus="setObj(description[10],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('245k')"> $k</a>
<div id="245k" style="width: 500px; background: #FDF5E6; display:none">
    Form (NR)<input type="text" value="<%=marc245.get$k()==null?"":marc245.get$k() %>" readonly name="z245k" id="245k" onFocus="setObj(description[11],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

</font></td></tr>



<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<!-- 25X-28X - Edition, Imprint, Etc. Fields-General Information-->
<tr>
    <td>&nbsp;&nbsp;&nbsp;Edition Statement (250) :<a href="javascript:animatedcollapse.toggle('250')">ind</a> <div id="250" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" name="in2501" value="#" readonly maxlength="1" size="1" onFocus="setObj(description[12],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()"  /> ind2<input type="text" name="in2502" readonly value="#" maxlength="1" size="1" onFocus="setObj(description[12],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
    <td>$a Edition Statement(NR)<input type="text" readonly value="<%=marc250.get$a()==null?"":marc250.get$a() %>" name="z250a" id="250a" onFocus="setObj(description[13],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()"  />
<font size="2">
<a href="javascript:animatedcollapse.toggle('250b')">$b</a>

<div id="250b" style="width: 500px; background: #FDF5E6; display:none">
    Remainder of edition statement(NR)<input type="text" readonly value="<%=marc250.get$b()==null?"":marc250.get$b() %>" name="z250b" id="250b" onFocus="setObj(description[14],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()"  />
</div>
</font></td></tr>



<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr>
    <td>&nbsp;&nbsp;&nbsp;Computer File Characteristics (NR)(256) :<a href="javascript:animatedcollapse.toggle('256')">ind</a> <div id="256" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" name="in2561" readonly value="<%=marc256.getIndicator1()==null?"":marc256.getIndicator1() %>" maxlength="1" size="1" onFocus="setObj(description[12],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="in2562" readonly value="<%=marc256.getIndicator2()==null?"":marc256.getIndicator2() %>" maxlength="1" size="1" onFocus="setObj(description[12],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
    <td>$a Computer file characteristics (NR) <input type="text" readonly value="<%=marc256.get$a()==null?"":marc256.get$a()  %>" name="z256a" id="256a" onFocus="setObj(description[15],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()"  />
<font size="2">
</font></td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr>
    <td>&nbsp;&nbsp;&nbsp;Publication, Distribution, etc(Imprint) (260) :<a href="javascript:animatedcollapse.toggle('260')">ind</a> <div id="260" style="width: 150px; display:none">&nbsp;&nbsp;&nbsp;ind1<input type="text" name="2601" value="<%=marc260.getIndicator1()==null?"":marc260.getIndicator1() %>" readonly maxlength="1" size="1" onFocus="setObj(description[16],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="2602" value="#" readonly maxlength="1" size="1" onFocus="setObj(description[17],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
    <td>$a Place of Publication, Distribution, etc(R)<input type="text" value="<%=marc260.get$a()==null?"":marc260.get$a() %>" readonly name="z260a" id="260a" onFocus="setObj(description[18],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('260b')">$b</a>

<div id="260b" style="width: 500px; background: #FDF5E6; display:none">
    Name of Publisher, Distributor, etc (R)<input type="text" value="<%=marc260.get$b()==null?"":marc260.get$b() %>" readonly name="z260b" id="260b" onFocus="setObj(description[19],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('260c')">$c</a>
<div id="260c" style="width: 500px; background: #FDF5E6; display:none">
    Date of Publication,Distribution etc(R)<input type="text" value="<%=marc260.get$c()==null?"":marc260.get$c() %>" readonly name="z260c" id="260c" onFocus="setObj(description[20],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('260e')">$e</a>
<div id="260e" style="width: 500px; background: #FDF5E6; display:none">
    Place of Manufacturer(NR)<input type="text" value="<%=marc260.get$e()==null?"":marc260.get$e() %>" name="z260e" readonly id="260e" onFocus="setObj(description[21],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('260f')">$f</a>
<div id="260f" style="width: 500px; background: #FDF5E6; display:none">
    Manufacturer(NR)<input type="text" value="<%=marc260.get$f()==null?"":marc260.get$f() %>" name="z260f" readonly id="260f" onFocus="setObj(description[22],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font>
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>

<tr>
    <td>&nbsp;&nbsp;&nbsp;Projected Publication Date (NR)(263) :<a href="javascript:animatedcollapse.toggle('263')">ind</a> <div id="263" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" name="in2631" readonly value="<%=marc263.getIndicator1()==null?"":marc263.getIndicator1() %>" maxlength="1" size="1" onFocus="setObj(description[12],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="in2632" readonly value="<%=marc263.getIndicator2()==null?"":marc263.getIndicator2() %>" maxlength="1" size="1" onFocus="setObj(description[12],'override',650,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
    <td>$a Projected publication date (NR) <input type="text" value="<%=marc263.get$a()==null?"":marc263.get$a() %>" readonly name="z263a" id="263a" onFocus="setObj(description[23],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
</font></td></tr>
</table>
                    <br/><br> &nbsp;&nbsp;&nbsp;You are on MARC Page : Tag 2
    <br>&nbsp; <br> &nbsp;&nbsp;&nbsp;
                    <%
if(session.getAttribute("marcbutton").equals("Delete")){
    %>
    <a style="text-decoration: none;" href="<%=request.getContextPath() %>/marcdelete.do"><input type="button" value="Delete" /></a>
    <% }
    %>

                  </td></tr></table></html:form>
   
</div>
        </div>
    </body>
</html>
