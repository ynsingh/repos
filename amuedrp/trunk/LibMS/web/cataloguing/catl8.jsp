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
       <title> Bibliographic Cataloging According to MARC21 -- 8XX</title>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ddtabmenufiles/ddtabmenu.js"></script>

<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">

<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/animatedcollapse.js">

/***********************************************
* Animated Collapsible DIV- (c) muhammad zeeshan
***********************************************/

</script>
<% session.setAttribute("tag8", "1");%>
<% HashMap hm1 = new HashMap();
    Biblio bib1=new Biblio();
    Biblio bib2=new Biblio();
    Biblio bib3=new Biblio();
    Biblio bib4=new Biblio();
    Biblio bib5=new Biblio();
%>
<%
 hm1 = (HashMap)session.getAttribute("hsmp");
if(hm1!=null){
  if(hm1.containsKey("30")){
       bib1=(Biblio)hm1.get("30");
        }
   if(hm1.containsKey("31")){
       bib2=(Biblio)hm1.get("31");
        }
   if(hm1.containsKey("32")){
       bib3=(Biblio)hm1.get("32");
        }
   if(hm1.containsKey("33")){
       bib4=(Biblio)hm1.get("33");
        }
    if(hm1.containsKey("34")){
       bib5=(Biblio)hm1.get("34");
        }
  }
 %>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ddtabmenufiles/solidblocksmenu.css" />
<script type="text/javascript">

ddtabmenu.definemenu("ddtabs3", 9) //initialize Tab Menu #3 with 8th tab selected
</script>


<script type="text/javascript">
animatedcollapse.addDiv('800', 'fade=1,height=30px')
animatedcollapse.addDiv('800b', 'fade=1,height=30px')
animatedcollapse.addDiv('800c', 'fade=1,height=30px')
animatedcollapse.addDiv('800d', 'fade=1,height=30px')
animatedcollapse.addDiv('800t', 'fade=1,height=30px')
animatedcollapse.addDiv('800l', 'fade=1,height=30px')
animatedcollapse.addDiv('800f', 'fade=1,height=30px')
animatedcollapse.addDiv('800s', 'fade=1,height=30px')
animatedcollapse.addDiv('800v', 'fade=1,height=30px')
animatedcollapse.addDiv('8004', 'fade=1,height=30px')

animatedcollapse.addDiv('830', 'fade=1,height=30px')
animatedcollapse.addDiv('830h', 'fade=1,height=30px')
animatedcollapse.addDiv('830n', 'fade=1,height=30px')
animatedcollapse.addDiv('830p', 'fade=1,height=30px')
animatedcollapse.addDiv('830v', 'fade=1,height=30px')
animatedcollapse.addDiv('830x', 'fade=1,height=30px')
animatedcollapse.addDiv('8303', 'fade=1,height=30px')
animatedcollapse.addDiv('8305', 'fade=1,height=30px')

animatedcollapse.addDiv('850', 'fade=1,height=30px')

animatedcollapse.addDiv('852', 'fade=1,height=30px')
animatedcollapse.addDiv('852b', 'fade=1,height=30px')
animatedcollapse.addDiv('852c', 'fade=1,height=30px')
animatedcollapse.addDiv('852e', 'fade=1,height=30px')
animatedcollapse.addDiv('852f', 'fade=1,height=30px')
animatedcollapse.addDiv('852h', 'fade=1,height=30px')
animatedcollapse.addDiv('852i', 'fade=1,height=30px')
animatedcollapse.addDiv('852n', 'fade=1,height=30px')
animatedcollapse.addDiv('852t', 'fade=1,height=30px')
animatedcollapse.addDiv('852u', 'fade=1,height=30px')

animatedcollapse.addDiv('856', 'fade=1,height=30px')
animatedcollapse.addDiv('856b', 'fade=1,height=30px')
animatedcollapse.addDiv('856c', 'fade=1,height=30px')
animatedcollapse.addDiv('856d', 'fade=1,height=30px')
animatedcollapse.addDiv('856f', 'fade=1,height=30px')
animatedcollapse.addDiv('856h', 'fade=1,height=30px')
animatedcollapse.addDiv('856i', 'fade=1,height=30px')
animatedcollapse.addDiv('856j', 'fade=1,height=30px')
animatedcollapse.addDiv('856k', 'fade=1,height=30px')
animatedcollapse.addDiv('856l', 'fade=1,height=30px')
animatedcollapse.addDiv('856m', 'fade=1,height=30px')
animatedcollapse.addDiv('856n', 'fade=1,height=30px')
animatedcollapse.addDiv('856o', 'fade=1,height=30px')
animatedcollapse.addDiv('856p', 'fade=1,height=30px')
animatedcollapse.addDiv('856q', 'fade=1,height=30px')
animatedcollapse.addDiv('856s', 'fade=1,height=30px')
animatedcollapse.addDiv('856t', 'fade=1,height=30px')
animatedcollapse.addDiv('856u', 'fade=1,height=30px')
animatedcollapse.addDiv('856x', 'fade=1,height=30px')
animatedcollapse.addDiv('856z', 'fade=1,height=30px')

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
description[0]='Type of personal name entry element- <b>0 - Forename, 1 - Surname, 3 - Family name '
description[1]='<b>Undefined and contains a blank (#).'
description[2]='e.g. <b>Berenholtz, Jim,'
description[3]=''
description[4]=''
description[5]='e.g. <b>1809-1849'
description[6]='e.g. <b>1922.'
description[7]='e.g. <b>German.'
description[8]='e.g. <b>James Joyce archive. '
description[9]='e.g. <b>bk. 1.'
description[10]=''
description[11]='e.g. <b>prf'

description[12]='Nonfiling characters- <b>0 - No nonfiling characters, 1-9 - Number of nonfiling characters '
description[13]='e.g. <b>Sport (International Union of Students. Physical Education and Sports Dept.)'
description[14]='e.g. <b>[Videorecording] '
description[15]='e.g <b>tape 14. '
description[16]='e.g. <b>0090-0206. '
description[17]='e.g. <b><May 1986->'
description[18]='e.g. <b>ICU '

description[19]='<b>Both indicator positions are undefined; each contains a blank (#).'
description[20]='MARC code or the name of the institution holding the item. e.g. <b>AAP'
description[21]='Shelving scheme- <b># - No information provided, 0 - Library of Congress classification, 1 - Dewey Decimal classification, 2 - National Library of Medicine classification, 3 - Superintendent of Documents classification, 4 - Shelving control number, 5 - Title, 6 - Shelved separately, 7 - Source specified in subfield $2, 8 - Other scheme '
description[22]='Shelving order- <b># - No information provided, 0 - Not enumeration, 1 - Primary enumeration, 2 - Alternative enumeration'
description[23]='Institution or person holding the item or from which access is given. e.g. <b>CLU '
description[24]='Specific department, library, etc. e.g. <b>Sci'
description[25]='Shelving location of the item within the collection of the holding organization.e.g. <b>mezzanine stacks'
description[26]='Street address, city, state, zip code, and country information for the current physical location of the item. e.g. <b>James Madison Memorial Building, 1st & Independence Ave., S.E., Washington, DC USA'
description[27]='Three-character code that identifies the specific issues of the item that are located apart from the main holdings of the same item\n\
l, p - Qualifier type <b>l - Latest, p - Previous\n\
1-9 - Number of units\n\
# - No information provided\n\
m, w, y, e, i, s - Unit type\n\
m - Month(s) time, w - Week(s) time, y - Year(s) time, e - Edition(s) part, i - Issue(s) part, s - Supplement(s) part\n\
e.g. l2y'
description[28]='Classification portion of the call number used as the shelving scheme for an item. e.g. <b>QE653'
description[29]='Cutter, date, or term that is added to the classification contained in subfield $h. e.g. <b>L7 '
description[30]='Two- or three-character MARC code for the principal location contained in subfield $a (Location). Code from: MARC Code List for Countries. e.g. <b>fr'
description[31]='Copy number or a range of numbers for copies that have the same location and call number. e.g. <b>2-3 '
description[32]='a URL or URN, which provides electronic access data in a standard syntax. e.g. <b>http://hdl.loc.gov/loc.pnp/pp.print '

description[33]='Access method\n\
# - No information provided, 0 - Email, 1 - FTP, 2 - Remote login (Telnet), 3 - Dial-up, 4 - HTTP, 7 - Method specified in subfield $2 '
description[34]='Relationship\n\
# - No information provided, 0 - Resource, 1 - Version of resource, 2 - Related resource, 8 - No display constant generated '
description[35]='Fully qualified domain (host name) of the electronic location. e.g. <b>anthrax.micro.umn.edu'
description[36]='Access number associated with a host. It can contain the Internet Protocol (IP) numeric address or Telephone number. e.g <b>128.101.95.23'
description[37]='Information about the compression of a file. e.g. <b>Must be decompressed with PKUNZIP'
description[38]='Path, which is the series of logical directory and subdirectory names that indicate where a file is stored. e.g. <b>/pub/EIS/vol*no*/adobe'
description[39]='Electronic name of a file as it exists in the directory/subdirectory indicated in subfield $d. e.g. <b>*.pdf'
description[40]='Username, or processor of the request. e.g. <b>Listserv '
description[41]='Instruction or command needed for the remote host to process a request.'
description[42]='Lowest and highest number of bits (binary units) of data that can be transmitted per second when connected to a host. '
description[43]='Password required to access the electronic resource'
description[44]='Characters needed to connect (i.e., logon, login, etc.) to an electronic resource or FTP site. e.g. <b>anonymous'
description[45]='Name of a contact for assistance in accessing a resource at the host specified in subfield $a'
description[46]='Conventional name of the location of the host in subfield $a, including its physical (geographic) location. e.g. <b>University of Michigan Weather Underground'
description[47]='For informational purposes, the operating system used by the host specified in subfield $a'
description[48]='Portion of the address that identifies a process or service in the host.'
description[49]='Identification of the electronic format type. e.g. <b>application/pdf '
description[50]='Terminal emulation is usually specified for remote login (first indicator contains value 2 (Remote login (Telnet))). e.g. 3270'
description[51]='Uniform Resource Identifier (URI), which provides standard syntax for locating an object using existing Internet protocols. e.g. <b>http://www.jstor.org/journals/0277903x.html '
description[52]='Size of the file as stored under the filename indicated in subfield $f. e.g. <b>16874 bytes '
description[53]='Note relating to the electronic location of the source identified in the field. e.g. <b>cannot verify because of transfer difficulty '
description[54]='Note relating to the electronic location of the source identified in the field. e.g. <b>FTP access to PostScript version includes groups of article files with .pdf extension'

</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/tooltip.js">

/***********************************************
* ToolTip layer - (c) muhammad zeeshan
***********************************************/

</script>

    </head>
  <jsp:include page="/admin/header.jsp"></jsp:include>
<body onload="loadHelp()">
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


function func1(t){

     document.getElementById("zclick").value= t;

     func2(t);

    }

function func2(t){
  //  alert(t);
    if(t.value!=8){

        document.getElementById("cat8").submit();
   //  alert("submitted! ");
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

                                      <!-- Marc entries Starts from here . -->
You are on MARC Page : 8 Tag Page

<div>

<html:form styleId="cat8" action="/cataction8.do" method="post">
    <table height="400px"><tr><td valign="top" >&nbsp;&nbsp;&nbsp;
<table width="100%" cellspacing="5" >
  <tr><input type="hidden" value="" name="zclick" id="zclick" /></tr>
<tr>
    <td>&nbsp;&nbsp;&nbsp;Series Added Entry- Personal Name (R)(800) : <a href="javascript:animatedcollapse.toggle('800')">ind</a> <div id="800" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" value="<% if(bib1.getIndicator1()!=null){%><%= bib1.getIndicator1() %><%}%>" name="in8001" maxlength="1" size="1" onFocus="statwords(description[0],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<% if(bib1.getIndicator2()!=null){%><%= bib1.getIndicator2() %><%}%>" name="in8002" maxlength="1" size="1" onFocus="statwords(description[1],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Personal name (NR) <input type="text" value="<% if(bib1.get$a()!=null){%><%= bib1.get$a() %><%}%>" name="z800a" id="800a" onFocus="statwords(description[2],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('800b')">$b </a>

<div id="800b" style=" background: #FDF5E6; display:none">
Numeration (NR) <input type="text" value="<% if(bib1.get$b()!=null){%><%= bib1.get$b() %><%}%>" name="z800b" id="800b" onFocus="statwords(description[3],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('800c')">$c </a>

<div id="800c" style=" background: #FDF5E6; display:none">
Titles and other words associated with a name (R)<input type="text" value="<% if(bib1.get$c()!=null){%><%= bib1.get$c() %><%}%>" name="z800c" id="800c" onFocus="statwords(description[4],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('800d')">$d </a>

<div id="800d" style=" background: #FDF5E6; display:none">
Dates associated with a name (NR) <input type="text" value="<% if(bib1.get$d()!=null){%><%= bib1.get$d() %><%}%>" name="z800d" id="800d" onFocus="statwords(description[5],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('800f')">$f </a>

<div id="800f" style=" background: #FDF5E6; display:none">
Date of a work (NR) <input type="text" value="<% if(bib1.get$f()!=null){%><%= bib1.get$f() %><%}%>" name="z800f" id="800f" onFocus="statwords(description[6],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('800l')">$l </a>

<div id="800l" style=" background: #FDF5E6; display:none">
Language of a work (NR)<input type="text" value="<% if(bib1.get$l()!=null){%><%= bib1.get$l() %><%}%>" name="z800l" id="800l" onFocus="statwords(description[7],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('800t')">$t </a>

<div id="800t" style=" background: #FDF5E6; display:none">
Title of a work (NR) <input type="text" value="<% if(bib1.get$t()!=null){%><%= bib1.get$t() %><%}%>" name="z800t" id="800t" onFocus="statwords(description[8],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('800v')">$v </a>

<div id="800v" style=" background: #FDF5E6; display:none">
Volume/sequential designation (NR) <input type="text" value="<% if(bib1.get$v()!=null){%><%= bib1.get$v() %><%}%>" name="z800v" id="800v" onFocus="statwords(description[9],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('800s')">$s </a>

<div id="800s" style=" background: #FDF5E6; display:none">
Version (NR) <input type="text" value="<% if(bib1.get$s()!=null){%><%= bib1.get$s() %><%}%>" name="z800s" id="800s" onFocus="statwords(description[10],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('8004')">$4 </a>

<div id="8004" style=" background: #FDF5E6; display:none">
Relator code (R) <input type="text" value="<% if(bib1.get$4()!=null){%><%= bib1.get$4() %><%}%>" name="z8004" id="8004" onFocus="statwords(description[11],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>&nbsp;&nbsp;&nbsp;Series Added Entry-Uniform Title (R)(830) : <a href="javascript:animatedcollapse.toggle('830')">ind</a> <div id="830" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" value="<% if(bib2.getIndicator1()!=null){%><%= bib2.getIndicator1() %><%}%>" name="in8301" maxlength="1" size="1" onFocus="statwords(description[1],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<% if(bib2.getIndicator2()!=null){%><%= bib2.getIndicator2() %><%}%>" name="in8302" maxlength="1" size="1" onFocus="statwords(description[12],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Uniform title (NR) <input type="text" value="<% if(bib2.get$a()!=null){%><%= bib2.get$a() %><%}%>" name="z830a" id="830a" onFocus="statwords(description[13],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('830h')">$h </a>

<div id="830h" style=" background: #FDF5E6; display:none">
Medium (NR) <input type="text" value="<% if(bib2.get$h()!=null){%><%= bib2.get$h() %><%}%>" name="z830h" id="830h" onFocus="statwords(description[14],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('830n')">$n </a>

<div id="830n" style=" background: #FDF5E6; display:none">
Number of part/section of a work (R) <input type="text" value="<% if(bib2.get$n()!=null){%><%= bib2.get$n() %><%}%>" name="z830n" id="830n" />
</div>

<a href="javascript:animatedcollapse.toggle('830p')">$p </a>

<div id="830p" style=" background: #FDF5E6; display:none">
Name of part/section of a work (R) <input type="text" value="<% if(bib2.get$p()!=null){%><%= bib2.get$p() %><%}%>" name="z830p" id="830p" />
</div>

<a href="javascript:animatedcollapse.toggle('830v')">$v </a>

<div id="830v" style=" background: #FDF5E6; display:none">
Volume/sequential designation (NR) <input type="text" value="<% if(bib2.get$v()!=null){%><%= bib2.get$v() %><%}%>" name="z830v" id="830v" onFocus="statwords(description[15],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('830x')">$x </a>

<div id="830x" style=" background: #FDF5E6; display:none">
International Standard Serial Number (NR) <input type="text" value="<% if(bib2.get$x()!=null){%><%= bib2.get$x() %><%}%>" name="z830x" id="830x" onFocus="statwords(description[16],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('8303')">$3 </a>

<div id="8303" style=" background: #FDF5E6; display:none">
Materials specified (NR) <input type="text" value="<% if(bib2.get$3()!=null){%><%= bib2.get$3() %><%}%>" name="z8303" id="8303" onFocus="statwords(description[17],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('8305')">$5 </a>

<div id="8305" style=" background: #FDF5E6; display:none">
Institution to which field applies (R) <input type="text" value="<% if(bib2.get$5()!=null){%><%= bib2.get$5() %><%}%>" name="z8305" id="8305" onFocus="statwords(description[18],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>&nbsp;&nbsp;&nbsp;Series Added Entry-Uniform Title (R)(850) : <a href="javascript:animatedcollapse.toggle('850')">ind</a> <div id="850" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" value="<% if(bib3.getIndicator1()!=null){%><%= bib3.getIndicator1() %><%}%>" name="in8501" maxlength="1" size="1" onFocus="statwords(description[19],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="in8502" value="<% if(bib3.getIndicator2()!=null){%><%= bib3.getIndicator2() %><%}%>" maxlength="1" size="1" onFocus="statwords(description[19],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Uniform title (NR) <input type="text" value="<% if(bib3.get$a()!=null){%><%= bib3.get$a() %><%}%>" name="z850a" id="850a" onFocus="statwords(description[20],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>&nbsp;&nbsp;&nbsp;Location (R) (852) : <a href="javascript:animatedcollapse.toggle('852')">ind</a> <div id="852" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" value="<% if(bib4.getIndicator1()!=null){%><%= bib4.getIndicator1() %><%}%>" name="in8521" maxlength="1" size="1" onFocus="statwords(description[21],800,70)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<% if(bib4.getIndicator2()!=null){%><%= bib4.getIndicator2() %><%}%>" name="in8522" maxlength="1" size="1" onFocus="statwords(description[22],800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Location (NR)  <input type="text" value="<% if(bib4.get$a()!=null){%><%= bib4.get$a() %><%}%>" name="z852a" id="852a" onFocus="statwords(description[23],800,70)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('852b')">$b </a>

<div id="852b" style=" background: #FDF5E6; display:none">
Sublocation or collection (R) <input type="text" value="<% if(bib4.get$b()!=null){%><%= bib4.get$b() %><%}%>" name="z852b" id="852b" onFocus="statwords(description[24],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852c')">$c </a>

<div id="852c" style=" background: #FDF5E6; display:none">
Shelving location (R) <input type="text" value="<% if(bib4.get$c()!=null){%><%= bib4.get$c() %><%}%>" name="z852c" id="852c" onFocus="statwords(description[25],800,70)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852e')">$e </a>

<div id="852e" style=" background: #FDF5E6; display:none">
Address (R)  <input type="text" value="<% if(bib4.get$e()!=null){%><%= bib4.get$e() %><%}%>" name="z852e" id="852e" onFocus="statwords(description[26],800,70)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852f')">$f </a>

<div id="852f" style=" background: #FDF5E6; display:none">
Coded location qualifier (R) <input type="text" value="<% if(bib4.get$f()!=null){%><%= bib4.get$f() %><%}%>" name="z852f" id="852f" onFocus="statwords(description[27],800,150)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852h')">$h </a>

<div id="852h" style=" background: #FDF5E6; display:none">
Classification part (NR) <input type="text" value="<% if(bib4.get$h()!=null){%><%= bib4.get$h() %><%}%>" name="z852h" id="852h" onFocus="statwords(description[28],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852i')">$i </a>

<div id="852i" style=" background: #FDF5E6; display:none">
Item part (R) <input type="text" value="<% if(bib4.get$i()!=null){%><%= bib4.get$i() %><%}%>" name="z852i" id="852i" onFocus="statwords(description[29],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852n')">$n </a>

<div id="852n" style=" background: #FDF5E6; display:none">
Country code (NR) <input type="text" value="<% if(bib4.get$h()!=null){%><%= bib4.get$h() %><%}%>" name="z852n" id="852n" onFocus="statwords(description[30],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852t')">$t </a>

<div id="852t" style=" background: #FDF5E6; display:none">
Copy number (NR) <input type="text" value="<% if(bib4.get$t()!=null){%><%= bib4.get$t() %><%}%>" name="z852t" id="852t" onFocus="statwords(description[31],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852u')">$u </a>

<div id="852u" style=" background: #FDF5E6; display:none">
Uniform Resource Identifier (R) <input type="text" value="<% if(bib4.get$u()!=null){%><%= bib4.get$u() %><%}%>" name="z852u" id="852u" onFocus="statwords(description[32],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>


<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>&nbsp;&nbsp;&nbsp;Electronic Location and Access (R) (856) : <a href="javascript:animatedcollapse.toggle('856')">ind</a> <div id="856" style="width: 150px; display:none" >&nbsp;&nbsp;&nbsp;ind1<input type="text" value="<% if(bib5.getIndicator1()!=null){%><%= bib5.getIndicator1() %><%}%>" name="in8561" maxlength="1" size="1" onFocus="statwords(description[33],800,60)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<% if(bib5.getIndicator2()!=null){%><%= bib5.getIndicator2() %><%}%>" name="in8562" maxlength="1" size="1" onFocus="statwords(description[34],800,70)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Host name (R)  <input type="text" value="<% if(bib5.get$a()!=null){%><%= bib5.get$a() %><%}%>" name="z856a" id="856a" onFocus="statwords(description[35],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('856b')">$b </a>

<div id="856b" style=" background: #FDF5E6; display:none">
Access number (R) <input type="text" value="<% if(bib5.get$b()!=null){%><%= bib5.get$b() %><%}%>" name="z856b" id="856b" onFocus="statwords(description[36],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856c')">$c </a>

<div id="856c" style=" background: #FDF5E6; display:none">
Compression information (R) <input type="text" value="<% if(bib5.get$c()!=null){%><%= bib5.get$c() %><%}%>" name="z856c" id="856c" onFocus="statwords(description[37],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856d')">$d </a>

<div id="856d" style=" background: #FDF5E6; display:none">
Path (R)  <input type="text" value="<% if(bib5.get$d()!=null){%><%= bib5.get$d() %><%}%>" name="z856d" id="856d" onFocus="statwords(description[38],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856f')">$f </a>

<div id="856f" style=" background: #FDF5E6; display:none">
Electronic name (R) <input type="text" value="<% if(bib5.get$f()!=null){%><%= bib5.get$f() %><%}%>" name="z856f" id="856f" onFocus="statwords(description[39],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856h')">$h </a>

<div id="856h" style=" background: #FDF5E6; display:none">
Processor of request (NR) <input type="text" value="<% if(bib5.get$h()!=null){%><%= bib5.get$h() %><%}%>" name="z856h" id="856h" onFocus="statwords(description[40],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856i')">$i </a>

<div id="856i" style=" background: #FDF5E6; display:none">
Instruction (R) <input type="text" value="<% if(bib5.get$i()!=null){%><%= bib5.get$i() %><%}%>" name="z856i" id="856i" onFocus="statwords(description[41],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856j')">$j </a>

<div id="856j" style=" background: #FDF5E6; display:none">
Bits per second (NR)  <input type="text" value="<% if(bib5.get$j()!=null){%><%= bib5.get$j() %><%}%>" name="z856j" id="856j" onFocus="statwords(description[42],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856k')">$k </a>

<div id="856k" style=" background: #FDF5E6; display:none">
Password (NR) <input type="text" value="<% if(bib5.get$k()!=null){%><%= bib5.get$k() %><%}%>" name="z856k" id="856k" onFocus="statwords(description[43],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856l')">$l </a>

<div id="856l" style=" background: #FDF5E6; display:none">
Logon (NR) <input type="text" value="<% if(bib5.get$l()!=null){%><%= bib5.get$l() %><%}%>" name="z856l" id="856l" onFocus="statwords(description[44],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856m')">$m </a>

<div id="856m" style=" background: #FDF5E6; display:none">
Contact for access assistance (R) <input type="text" value="<% if(bib5.get$m()!=null){%><%= bib5.get$m() %><%}%>" name="z856m" id="856m" onFocus="statwords(description[45],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856n')">$n </a>

<div id="856n" style=" background: #FDF5E6; display:none">
Name of location of host (NR) <input type="text" value="<% if(bib5.get$n()!=null){%><%= bib5.get$n() %><%}%>" name="z856n" id="856n" onFocus="statwords(description[46],800,60)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856o')">$o </a>

<div id="856o" style=" background: #FDF5E6; display:none">
Operating system (NR) <input type="text" value="<% if(bib5.get$o()!=null){%><%= bib5.get$o() %><%}%>" name="z856o" id="856o" onFocus="statwords(description[47],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856p')">$p </a>

<div id="856p" style=" background: #FDF5E6; display:none">
Port (NR) <input type="text" value="<% if(bib5.get$p()!=null){%><%= bib5.get$p() %><%}%>" name="z856p" id="856p" onFocus="statwords(description[48],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856q')">$q </a>

<div id="856q" style=" background: #FDF5E6; display:none">
Electronic format type (NR) <input type="text" value="<% if(bib5.get$q()!=null){%><%= bib5.get$q() %><%}%>" name="z856q" id="856q" onFocus="statwords(description[49],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856t')">$t </a>

<div id="856t" style=" background: #FDF5E6; display:none">
Terminal emulation (R) <input type="text" value="<% if(bib5.get$t()!=null){%><%= bib5.get$t() %><%}%>" name="z856t" id="856t" onFocus="statwords(description[50],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856u')">$u </a>

<div id="856u" style=" background: #FDF5E6; display:none">
Uniform Resource Identifier (R) <input type="text" value="<% if(bib5.get$u()!=null){%><%= bib5.get$u() %><%}%>" name="z856u" id="856u" onFocus="statwords(description[51],800,70)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856s')">$s </a>

<div id="856s" style=" background: #FDF5E6; display:none">
File size (R) <input type="text" value="<% if(bib5.get$s()!=null){%><%= bib5.get$s() %><%}%>" name="z856s" id="856s" onFocus="statwords(description[52],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856x')">$x </a>

<div id="856x" style=" background: #FDF5E6; display:none">
Nonpublic note (R) <input type="text" value="<% if(bib5.get$x()!=null){%><%= bib5.get$x() %><%}%>" name="z856x" id="856x" onFocus="statwords(description[53],800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856z')">$z </a>

<div id="856z" style=" background: #FDF5E6; display:none">
Public note (R) <input type="text" value="<% if(bib5.get$z()!=null){%><%= bib5.get$z() %><%}%>" name="z856z" id="856z" onFocus="statwords(description[54],800,70)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>
</table></td></tr></table></html:form>
</div></div>
    </body>
</html>
