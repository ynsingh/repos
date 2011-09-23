<%-- 
    Document   : vdcatl4
    Created on : Jul 21, 2011, 12:27:38 PM
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
        <title> Bibliographic Cataloguing According to MARC21 -- 4XX</title>
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

ddtabmenu.definemenu("ddtabs3", 4) //initialize Tab Menu #3 with 2nd tab selected
</script>


<script type="text/javascript">
animatedcollapse.addDiv('490', 'fade=1,height=30px')

animatedcollapse.addDiv('490x', 'fade=1,height=30px')
animatedcollapse.addDiv('490v', 'fade=1,height=30px')
animatedcollapse.addDiv('4903', 'fade=1,height=30px')
animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}


animatedcollapse.init()

</script>
<script type="text/javascript">
var description=new Array()
description[0]='Series tracing policy- 0 - Series not traced, 1 - Series traced'
description[1]='Undefined and contains a blank (#). '
description[2]='Series title that may also contain a statement of responsibility or other title information.ex- Bulletin / U.S. Department of Labor, Bureau of Labor Statistics'
description[3]='Volume number or other sequential designation used in a series statement.ex- <b>ser. 74, no. 11-3</b>'
description[4]='ISSN for a series title given in a series statement. ex- 0023-6721'
description[5]='Part of the described materials to which the field applies.ex- <1981->'
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
    if(t.value!=4){

        document.getElementById("ucat4").submit();
     alert("submitted! ");
}
}
</script>
                                          <h2 align="center">MARC Based Bibliographic Cataloging</h2>

<div id="ddtabs3" class="solidblockmenu">
<ul>
<li><a href="<%=request.getContextPath()%>/cataloguing/vdcatl0.jsp" onclick="func1(0)"  rel="sb0">0 (01X-09X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/vdcatl1.jsp" onclick="func1(1)" rel="sb1">1 (1XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/vdcatl2.jsp" onclick="func1(2)" rel="sb2">2 (20X-28X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/vdcatl3.jsp" onclick="func1(3)" rel="sb3">3 (3XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/vdcatl4.jsp" onclick="func1(4)" rel="sb4">4 (4XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/vdcatl5.jsp" onclick="func1(5)" rel="sb5">5 (5XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/vdcatl6.jsp" onclick="func1(6)" rel="sb6">6 (6XX)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/vdcatl7.jsp" onclick="func1(7)" rel="sb7">7 (70X-78X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/vdcatl8.jsp" onclick="func1(8)" rel="sb8">8 (80X-88X)</a></li>
<li><a href="<%=request.getContextPath()%>/cataloguing/vdcatl9.jsp" onclick="func1(9)" rel="sb9">9</a></li>
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
                                      <%! Biblio marc490=new Biblio();

                                       %>
  <%
     if(request.getAttribute("490")!=null){
             marc490=(Biblio)request.getAttribute("490");}


     %>

<div style="position:absolute;left:5%;top:23%;width:90%;border:1px #C0C0C0 solid;background: #f5fffa;">
 <html:form styleId="ucat4" action="/vdaction.do" method="post">
    <table width="100%" cellspacing="5" >
         <tr><input type="hidden"  name="zclick" id="zclick" /></tr>
    <tr>
        <td>Series Statement (R)(490) : <a href="javascript:animatedcollapse.toggle('490')">ind</a> <div id="490" style="width: 150px; display:none" >ind1<input type="text" name="in4901" value="<%=marc490.getIndicator1() %>" readonly maxlength="1" size="1" onFocus="setObj(description[0],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /> ind2<input type="text" name="in4902" value="<%=marc490.getIndicator2() %>" readonly maxlength="1" size="1" onFocus="setObj(description[1],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" /></div></td>
        <td>$a Series statement (R)<input type="text" value="<%=marc490.get$a() %>" readonly name="z490a" id="490a" onFocus="setObj(description[2],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
<font size="2">
<a href="javascript:animatedcollapse.toggle('490v')">$v </a>

<div id="490v" style=" background: #FDF5E6; display:none">
    Volume/sequential designation (R)<input type="text" value="<%=marc490.get$v() %>" readonly name="z490v" id="490v" onFocus="setObj(description[3],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
<a href="javascript:animatedcollapse.toggle('490x')">$x </a>
<div id="490x" style=" background: #FDF5E6; display:none">
    International Standard Serial Number (R)<input type="text" value="<%=marc490.get$x() %>" readonly name="z490x" id="490x" onFocus="setObj(description[4],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>

<a href="javascript:animatedcollapse.toggle('4903')">$3 </a>
<div id="4903" style=" background: #FDF5E6; display:none">
    Materials specified (NR) <input type="text" value="<%=marc490.get$3() %>" name="z4903" id="4903" readonly onFocus="setObj(description[5],'override',800,30)" onBlur="clearTimeout(openTimer);stopIt()" />
</div>
</font></td></tr>


    </table></html:form>
     <%
if(session.getAttribute("marcbutton").equals("Delete")){
    %>
    <a href="/marcdelete.do"><input type="submit" value="Delete" /></a>
    <% }
    %>
</div>
    </body>
</html>
