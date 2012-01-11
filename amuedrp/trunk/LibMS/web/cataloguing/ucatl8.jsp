<%-- 
    Document   : ucatl8
    Created on : Jul 16, 2011, 12:51:35 PM
    Author     : zeeshan
--%>


<%@page import="java.util.HashMap"%>
<%@page import="com.myapp.struts.hbm.Biblio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
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


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ddtabmenufiles/solidblocksmenu.css" />
<script type="text/javascript">

ddtabmenu.definemenu("ddtabs3", 8) //initialize Tab Menu #3 with 8th tab selected
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
    <body>
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
   // alert(t);
    if(t.value!=8){

        document.getElementById("ucat8").submit();
  //   alert("submitted! ");
}
}
</script>
                                          <h2 align="center">MARC Based Bibliographic Cataloging</h2>

<div id="ddtabs3" class="solidblockmenu">
<ul>
     <li><a href="<%=request.getContextPath()%>/cataloguing/updatecatlcontrol.jsp" onclick="func1(10)"  rel="sb10">Control Fields</a></li>
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
</div></FONT>
</DIV>

                                      <!-- Marc entries Starts from here . -->
                                      <%!
 HashMap hm1 = new HashMap();
                                                                           Biblio marc800=new Biblio();
                                          Biblio marc830=new Biblio();
                                          Biblio marc850=new Biblio();
                                          Biblio marc852=new Biblio();
                                          Biblio marc856=new Biblio();

                                       %>
  <%
   hm1 = (HashMap)session.getAttribute("hsmp");
  //   if(!hm1.isEmpty()){

  if(hm1.containsKey("30")){
       marc800=(Biblio)hm1.get("9");
        }
   if(hm1.containsKey("31")){
       marc830=(Biblio)hm1.get("10");
        }
   if(hm1.containsKey("32")){
       marc850=(Biblio)hm1.get("11");
        }
  if(hm1.containsKey("33")){
       marc852=(Biblio)hm1.get("12");
        }
  if(hm1.containsKey("34")){
       marc856=(Biblio)hm1.get("34");
        }
  
  
  // }else{
    
  
  
     if(request.getAttribute("800")!=null){
             marc800=(Biblio)request.getAttribute("800");}
     if(request.getAttribute("830")!=null){
             marc830=(Biblio)request.getAttribute("830");}
     if(request.getAttribute("850")!=null){
             marc850=(Biblio)request.getAttribute("850");}
      if(request.getAttribute("852")!=null){
             marc852=(Biblio)request.getAttribute("852");}
     if(request.getAttribute("856")!=null){
             marc856=(Biblio)request.getAttribute("856");}
  // }
     %>
<div style="position:absolute;left:5%;top:25%;width:90%;border:1px #C0C0C0 solid;background: #f5fffa;">
<html:form styleId="ucat8" action="/ucataction8.do" method="post">
<table width="100%" cellspacing="5" >
  <tr><input type="hidden" value="" name="zclick" id="zclick" /></tr>
<tr>
    <td>Series Added Entry- Personal Name (R)(800) : <a href="javascript:animatedcollapse.toggle('800')">ind</a> <div id="800" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc800.getIndicator1()==null?"":marc800.getIndicator1() %>" name="in8001" maxlength="1" size="1" onFocus="setObj(description[0],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc800.getIndicator2()==null?"":marc800.getIndicator2() %>" name="in8002" maxlength="1" size="1" onFocus="setObj(description[1],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Personal name (NR) <input type="text" value="<%=marc800.get$a()==null?"":marc800.get$a() %>" name="z800a" id="800a" onFocus="setObj(description[2],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('800b')">$b </a>

<div id="800b" style=" background: #FDF5E6; display:none">
Numeration (NR) <input type="text" value="<%=marc800.get$b()==null?"":marc800.get$b() %>" name="z800b" id="800b" onFocus="setObj(description[3],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('800c')">$c </a>

<div id="800c" style=" background: #FDF5E6; display:none">
Titles and other words associated with a name (R)<input type="text" value="<%=marc800.get$c()==null?"":marc800.get$c() %>" name="z800c" id="800c" onFocus="setObj(description[4],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('800d')">$d </a>

<div id="800d" style=" background: #FDF5E6; display:none">
Dates associated with a name (NR) <input type="text" value="<%=marc800.get$d()==null?"":marc800.get$d() %>" name="z800d" id="800d" onFocus="setObj(description[5],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('800f')">$f </a>

<div id="800f" style=" background: #FDF5E6; display:none">
Date of a work (NR) <input type="text" value="<%=marc800.get$f()==null?"":marc800.get$f() %>" name="z800f" id="800f" onFocus="setObj(description[6],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('800l')">$l </a>

<div id="800l" style=" background: #FDF5E6; display:none">
Language of a work (NR)<input type="text" value="<%=marc800.get$l()==null?"":marc800.get$l() %>" name="z800l" id="800l" onFocus="setObj(description[7],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('800t')">$t </a>

<div id="800t" style=" background: #FDF5E6; display:none">
Title of a work (NR) <input type="text" value="<%=marc800.get$t()==null?"":marc800.get$t() %> name="z800t" id="800t" onFocus="setObj(description[8],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('800v')">$v </a>

<div id="800v" style=" background: #FDF5E6; display:none">
Volume/sequential designation (NR) <input type="text" value="<%=marc800.get$v()==null?"":marc800.get$v() %>" name="z800v" id="800v" onFocus="setObj(description[9],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('800s')">$s </a>

<div id="800s" style=" background: #FDF5E6; display:none">
Version (NR) <input type="text" value="<%=marc800.get$s()==null?"":marc800.get$s() %>" name="z800s" id="800s" onFocus="setObj(description[10],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('8004')">$4 </a>

<div id="8004" style=" background: #FDF5E6; display:none">
Relator code (R) <input type="text" value="<%=marc800.get$4()==null?"":marc800.get$4() %>" name="z8004" id="8004" onFocus="setObj(description[11],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>Series Added Entry-Uniform Title (R)(830) : <a href="javascript:animatedcollapse.toggle('830')">ind</a> <div id="830" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc830.getIndicator1()==null?"":marc830.getIndicator1() %>" name="in8301" maxlength="1" size="1" onFocus="setObj(description[1],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc830.getIndicator2()==null?"":marc830.getIndicator2() %>" name="in8302" maxlength="1" size="1" onFocus="setObj(description[12],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Uniform title (NR) <input type="text" value="<%=marc830.get$a()==null?"":marc830.get$a() %>" name="z830a" id="830a" onFocus="setObj(description[13],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('830h')">$h </a>

<div id="830h" style=" background: #FDF5E6; display:none">
Medium (NR) <input type="text" value="<%=marc830.get$h()==null?"":marc830.get$h() %>" name="z830h" id="830h" onFocus="setObj(description[14],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('830n')">$n </a>

<div id="830n" style=" background: #FDF5E6; display:none">
Number of part/section of a work (R) <input type="text" value="<%=marc830.get$n()==null?"":marc830.get$n() %>" name="z830n" id="830n" />
</div>

<a href="javascript:animatedcollapse.toggle('830p')">$p </a>

<div id="830p" style=" background: #FDF5E6; display:none">
Name of part/section of a work (R) <input type="text" value="<%=marc830.get$p()==null?"":marc830.get$p() %>" name="z830p" id="830p" />
</div>

<a href="javascript:animatedcollapse.toggle('830v')">$v </a>

<div id="830v" style=" background: #FDF5E6; display:none">
Volume/sequential designation (NR) <input type="text" value="<%=marc830.get$v()==null?"":marc830.get$v() %>" name="z830v" id="830v" onFocus="setObj(description[15],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('830x')">$x </a>

<div id="830x" style=" background: #FDF5E6; display:none">
International Standard Serial Number (NR) <input type="text" value="<%=marc830.get$x()==null?"":marc830.get$x() %>" name="z830x" id="830x" onFocus="setObj(description[16],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('8303')">$3 </a>

<div id="8303" style=" background: #FDF5E6; display:none">
Materials specified (NR) <input type="text" value="<%=marc830.get$3()==null?"":marc830.get$3() %>" name="z8303" id="8303" onFocus="setObj(description[17],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('8305')">$5 </a>

<div id="8305" style=" background: #FDF5E6; display:none">
Institution to which field applies (R) <input type="text" value="<%=marc830.get$5()==null?"":marc830.get$5() %>" name="z8305" id="8305" onFocus="setObj(description[18],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>Series Added Entry-Uniform Title (R)(850) : <a href="javascript:animatedcollapse.toggle('850')">ind</a> <div id="850" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc850.getIndicator1()==null?"":marc850.getIndicator1() %>" name="in8501" maxlength="1" size="1" onFocus="setObj(description[19],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc850.getIndicator2()==null?"":marc850.getIndicator2() %>" name="in8502" maxlength="1" size="1" onFocus="setObj(description[19],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Uniform title (NR) <input type="text" value="<%=marc850.get$a()==null?"":marc850.get$a() %>" name="z850a" id="850a" onFocus="setObj(description[20],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</td></tr>

<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>Location (R) (852) : <a href="javascript:animatedcollapse.toggle('852')">ind</a> <div id="852" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc852.getIndicator1()==null?"":marc852.getIndicator1() %>" name="in8521" maxlength="1" size="1" onFocus="setObj(description[21],'override',800,70)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc852.getIndicator2()==null?"":marc852.getIndicator2() %>" name="in8522" maxlength="1" size="1" onFocus="setObj(description[22],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Location (NR)  <input type="text" value="<%=marc852.get$a()==null?"":marc852.get$a() %>" name="z852a" id="852a" onFocus="setObj(description[23],'override',800,70)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('852b')">$b </a>

<div id="852b" style=" background: #FDF5E6; display:none">
Sublocation or collection (R) <input type="text" value="<%=marc852.get$b()==null?"":marc852.get$b() %>" name="z852b" id="852b" onFocus="setObj(description[24],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852c')">$c </a>

<div id="852c" style=" background: #FDF5E6; display:none">
Shelving location (R) <input type="text" value="<%=marc852.get$c()==null?"":marc852.get$c() %>" name="z852c" id="852c" onFocus="setObj(description[25],'override',800,70)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852e')">$e </a>

<div id="852e" style=" background: #FDF5E6; display:none">
Address (R)  <input type="text" value="" name="z852e" id="852e" onFocus="setObj(description[26],'override',800,70)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852f')">$f </a>

<div id="852f" style=" background: #FDF5E6; display:none">
Coded location qualifier (R) <input type="text" value="<%=marc852.get$f()==null?"":marc852.get$f() %>" name="z852f" id="852f" onFocus="setObj(description[27],'override',800,150)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852h')">$h </a>

<div id="852h" style=" background: #FDF5E6; display:none">
Classification part (NR) <input type="text" value="<%=marc852.get$h()==null?"":marc852.get$h() %>" name="z852h" id="852h" onFocus="setObj(description[28],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852i')">$i </a>

<div id="852i" style=" background: #FDF5E6; display:none">
Item part (R) <input type="text" value="<%=marc852.get$i()==null?"":marc852.get$i() %>" name="z852i" id="852i" onFocus="setObj(description[29],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852n')">$n </a>

<div id="852n" style=" background: #FDF5E6; display:none">
Country code (NR) <input type="text" value="<%=marc852.get$n()==null?"":marc852.get$n() %>" name="z852n" id="852n" onFocus="setObj(description[30],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852t')">$t </a>

<div id="852t" style=" background: #FDF5E6; display:none">
Copy number (NR) <input type="text" value="<%=marc852.get$t()==null?"":marc852.get$t() %>" name="z852t" id="852t" onFocus="setObj(description[31],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('852u')">$u </a>

<div id="852u" style=" background: #FDF5E6; display:none">
Uniform Resource Identifier (R) <input type="text" value="<%=marc852.get$u()==null?"":marc852.get$u() %>" name="z852u" id="852u" onFocus="setObj(description[32],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>


<tr><td colspan="2"><hr width="90%" size="2" color="green"/></td></tr>
<tr>
    <td>Electronic Location and Access (R) (856) : <a href="javascript:animatedcollapse.toggle('856')">ind</a> <div id="856" style="width: 150px; display:none" >ind1<input type="text" value="<%=marc856.getIndicator1()==null?"":marc856.getIndicator1() %>" name="in8561" maxlength="1" size="1" onFocus="setObj(description[33],'override',800,60)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" value="<%=marc856.getIndicator2()==null?"":marc856.getIndicator2() %>" name="in8562" maxlength="1" size="1" onFocus="setObj(description[34],'override',800,70)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
<td>
$a Host name (R)  <input type="text" value="<%=marc856.get$a()==null?"":marc856.get$a() %>" name="z856a" id="856a" onFocus="setObj(description[35],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('856b')">$b </a>

<div id="856b" style=" background: #FDF5E6; display:none">
Access number (R) <input type="text" value="<%=marc856.get$b()==null?"":marc856.get$b() %>" name="z856b" id="856b" onFocus="setObj(description[36],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856c')">$c </a>

<div id="856c" style=" background: #FDF5E6; display:none">
Compression information (R) <input type="text" value="<%=marc856.get$c()==null?"":marc856.get$c() %>" name="z856c" id="856c" onFocus="setObj(description[37],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856d')">$d </a>

<div id="856d" style=" background: #FDF5E6; display:none">
Path (R)  <input type="text" value="<%=marc856.get$d()==null?"":marc856.get$d() %>" name="z856d" id="856d" onFocus="setObj(description[38],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856f')">$f </a>

<div id="856f" style=" background: #FDF5E6; display:none">
Electronic name (R) <input type="text" value="<%=marc856.get$f()==null?"":marc856.get$f() %>" name="z856f" id="856f" onFocus="setObj(description[39],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856h')">$h </a>

<div id="856h" style=" background: #FDF5E6; display:none">
Processor of request (NR) <input type="text" value="<%=marc856.get$h()==null?"":marc856.get$h() %>" name="z856h" id="856h" onFocus="setObj(description[40],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856i')">$i </a>

<div id="856i" style=" background: #FDF5E6; display:none">
Instruction (R) <input type="text" value="<%=marc856.get$i()==null?"":marc856.get$i() %>" name="z856i" id="856i" onFocus="setObj(description[41],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856j')">$j </a>

<div id="856j" style=" background: #FDF5E6; display:none">
Bits per second (NR)  <input type="text" value="<%=marc856.get$j()==null?"":marc856.get$j() %>" name="z856j" id="856j" onFocus="setObj(description[42],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856k')">$k </a>

<div id="856k" style=" background: #FDF5E6; display:none">
Password (NR) <input type="text" value="<%=marc856.get$k()==null?"":marc856.get$k() %>" name="z856k" id="856k" onFocus="setObj(description[43],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856l')">$l </a>

<div id="856l" style=" background: #FDF5E6; display:none">
Logon (NR) <input type="text" value="<%=marc856.get$l()==null?"":marc856.get$l() %>" name="z856l" id="856l" onFocus="setObj(description[44],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856m')">$m </a>

<div id="856m" style=" background: #FDF5E6; display:none">
Contact for access assistance (R) <input type="text" value="<%=marc856.get$m()==null?"":marc856.get$m() %>" name="z856m" id="856m" onFocus="setObj(description[45],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856n')">$n </a>

<div id="856n" style=" background: #FDF5E6; display:none">
Name of location of host (NR) <input type="text" value="<%=marc856.get$n()==null?"":marc856.get$n() %>" name="z856n" id="856n" onFocus="setObj(description[46],'override',800,60)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856o')">$o </a>

<div id="856o" style=" background: #FDF5E6; display:none">
Operating system (NR) <input type="text" value="<%=marc856.get$o()==null?"":marc856.get$o() %>" name="z856o" id="856o" onFocus="setObj(description[47],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856p')">$p </a>

<div id="856p" style=" background: #FDF5E6; display:none">
Port (NR) <input type="text" value="<%=marc856.get$p()==null?"":marc856.get$p() %>" name="z856p" id="856p" onFocus="setObj(description[48],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856q')">$q </a>

<div id="856q" style=" background: #FDF5E6; display:none">
Electronic format type (NR) <input type="text" value="<%=marc856.get$q()==null?"":marc856.get$q() %>" name="z856q" id="856q" onFocus="setObj(description[49],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856t')">$t </a>

<div id="856t" style=" background: #FDF5E6; display:none">
Terminal emulation (R) <input type="text" value="<%=marc856.get$t()==null?"":marc856.get$t() %>" name="z856t" id="856t" onFocus="setObj(description[50],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856u')">$u </a>

<div id="856u" style=" background: #FDF5E6; display:none">
Uniform Resource Identifier (R) <input type="text" value="<%=marc856.get$u()==null?"":marc856.get$u() %>" name="z856u" id="856u" onFocus="setObj(description[51],'override',800,70)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856s')">$s </a>

<div id="856s" style=" background: #FDF5E6; display:none">
File size (R) <input type="text" value="<%=marc856.get$s()==null?"":marc856.get$s() %>" name="z856s" id="856s" onFocus="setObj(description[52],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856x')">$x </a>

<div id="856x" style=" background: #FDF5E6; display:none">
Non-public note (R) <input type="text" value="<%=marc856.get$x()==null?"":marc856.get$x() %>" name="z856x" id="856x" onFocus="setObj(description[53],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('856z')">$z </a>

<div id="856z" style=" background: #FDF5E6; display:none">
Public note (R) <input type="text" value="<%=marc856.get$z()==null?"":marc856.get$z() %>" name="z856z" id="856z" onFocus="setObj(description[54],'override',800,70)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>
</table></html:form>
</div>
    </body>
</html>
