<%--
    Document   : scroll
    Created on : Jul 2, 2010, 9:42:09 PM
    Author     : Asif
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import = "java.sql.*" %>
<%! String notice,subject;
    String[] colors = {"lightGrey", "lightRed", "lightpink"};
    int i=0;
    ResultSet rs=null;
%>

<html><title></title>
<head>  <style type="text/css">
<!--
 /* FONT COLORS */
TABLE		{ COLOR: #000000; FONT: 12px arial, sans-serif; font-weight: bold }
#NewsDiv	{ position: absolute; left: 0; top: 0; width: 100% }

 /* PAGE LINK COLORS */

a:link		{ color: #0033FF; text-decoration: underline; }

a:visited	{ color: #6633FF; text-decoration: underline; }

a:active	{ color: #0033FF; text-decoration: underline; }

a:hover		{ color: #6699FF; text-decoration: none; }

-->
</style></head>
<BODY style="width:500px" BGCOLOR="#F0F0F0" TEXT="#000000" OnLoad="NewsScrollStart();">
<div id="NewsDiv">
         <%
  try
 {

       rs=(ResultSet)request.getAttribute("noticeRs");
       if (rs!=null){
       while(rs.next())
          { if(i>=colors.length){i=0;}
            subject=rs.getString(2);
            notice=rs.getString(3);

 %>
 
 <table style="width:500px" align="center" onMouseover="scrollspeed=0" onMouseout="scrollspeed=current" >
     <tr> <td bgcolor=""><font size="3" color="blue" face="Times New Roman"><strong><%=subject%></strong></font></td><tr>
  <tr>  <td bgcolor=""><font size="3" color="#FF666" face="Times New Roman"><strong><%=notice%></strong></font></td></tr>
</table>
<%       i++; }

      }
else{%>
           <tr>
               <td align="center" bgcolor="white"><font color="RED"><b><font size="2" face="Arial, Helvetica,sans-serif" >No Notice available.</font></b></font></td>
    <%}
 }catch(Exception e){
%>
<font size="3" color="teal" >
      <%
        {%>

          No Records Found...&nbsp;&nbsp;&nbsp;&nbsp;
         <%out.println(e);
        }
                    }
finally
  {
    rs.close();

  }
     %></font></div>
<script language="JavaScript" type="text/javascript">
<!-- HIDE CODE

var scrollspeed		= "1"		// SET SCROLLER SPEED 1 = SLOWEST
var speedjump		= "30"		// ADJUST SCROLL JUMPING = RANGE 20 TO 40
var startdelay 		= "1" 		// START SCROLLING DELAY IN SECONDS
var nextdelay		= "0" 		// SECOND SCROLL DELAY IN SECONDS 0 = QUICKEST
var topspace		= "2px"		// TOP SPACING FIRST TIME SCROLLING
var frameheight		= "200px"	// IF YOU RESIZE THE WINDOW EDIT THIS HEIGHT TO MATCH



current = (scrollspeed)


function HeightData(){
AreaHeight=dataobj.offsetHeight
if (AreaHeight==0){
setTimeout("HeightData()",( startdelay * 1000 ))
}
else {
ScrollNewsDiv()
}}

function NewsScrollStart(){
dataobj=document.all? document.all.NewsDiv : document.getElementById("NewsDiv")
dataobj.style.top=topspace
setTimeout("HeightData()",( startdelay * 1000 ))
}

function ScrollNewsDiv(){
dataobj.style.top=parseInt(dataobj.style.top)-(scrollspeed)
if (parseInt(dataobj.style.top)<AreaHeight*(-1)) {
dataobj.style.top=frameheight
setTimeout("ScrollNewsDiv()",( nextdelay * 1000 ))
}
else {
setTimeout("ScrollNewsDiv()",speedjump)
}}



// END HIDE CODE -->
</script>
</BODY></HTML>

