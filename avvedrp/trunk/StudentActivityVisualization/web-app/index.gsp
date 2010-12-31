<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- DW6 -->

 
<head>
<!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
<title>Home Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="mm_travel2.css" type="text/css" />
<script language="JavaScript" type="text/javascript">
//--------------- LOCALIZEABLE GLOBALS ---------------
var d=new Date();
var monthname=new Array("January","February","March","April","May","June","July","August","September","October","November","December");
//Ensure correct for language. English is "January 1, 2004"
var TODAY = monthname[d.getMonth()] + " " + d.getDate() + ", " + d.getFullYear();
//---------------   END LOCALIZEABLE   ---------------
</script>
</head>
<style>

.table{

border-style:solid;
border-width:2px;
border-color:#FFFFFF  ;

 
}


</style>
<body background="bg.jpg">

    <g:isNotLoggedIn>
			<%response.sendRedirect("login/auth")%>
	
			 </g:isNotLoggedIn>
			 
			 <g:isLoggedIn>
			<%response.sendRedirect("login/index")%>
			 </g:isLoggedIn>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#3366CC">
    <td><img src="header.jpg" alt="Header" width="100%" border="0"></td>
  </tr>

<!--
  <tr bgcolor="#CCFF99">
  	<td id="dateformat" height="25">&nbsp;&nbsp;<script language="JavaScript" type="text/javascript">
      document.write(TODAY);	</script>	</td>
  </tr>
 -->
 
 <tr>
 
     <td>
 
<table  width="100%" height="583" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="479" align="center" valign="top" ><table width="59%" class="table"  cellspacing="0"  cellpadding="0">
      <tr>
        <td height="149" class="table"  colspan="4" valign="top"><img src="dasboardImagesFaces.jpg"   /></td>
      </tr>
      <tr>
        <td height="55" class="table" valign="top" colspan="3" ><img src="dasboardImages2.jpg"   /></td>
        <td  height="55" class="table" valign="top" ><img src="dasboardImages3.jpg"   /></td>
      </tr>
      <tr >
        <td width="137" height="90" class="table" ><g:link url="[action:'visit',controller:'perStudentSummary']">
        <img src="visualDetails.jpg" border="0" /></g:link></td>
        <td  width="137" height="90" class="table" >
        <g:link url="[action:'timeSpent',controller:'perStudentSummary']">
        <img src="timeUtilZ.jpg" border="0"/>
        </g:link>
        </td>
        <td  width="136" height="90" class="table">
        <g:link url="[action:'list',controller:'perStudentSummary']">
        <img src="summary.jpg"  border="0"/></g:link></td>
        <td  width="184"  height="90"  class="table">
        <g:link url="[action:'list',controller:'courseActivity']">
        <img src="dasboardImages4.jpg" border="0" /></g:link></td>
      </tr>
      <tr>
        <td height="43" colspan="4" class="table" valign="top"  background="dasboardImages5Bg.jpg " ></td>
      </tr>
      <tr>
        <td width="137" height="90" class="table"  > <g:link url="analytics.jsp?query=PerStudentSummaryTotal"><img src="perStSummary.jpg"  border="0"/></g:link></td>
        <td width="137" height="90" class="table"   ><g:link url="analytics.jsp?query=studentlearningtool"><img src="courseSummary.jpg"  border="0"/></g:link></td>
        <td width="136" height="90" class="table"  ><img src="dasboardImages6Bg.jpg"  /></td>
        <td  width="184" height="90" class="table" ><img src="dasboardImages7Bg.jpg"  /></td>
      </tr>
      <tr>
        <td height="37" class="table" colspan="4">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="104">&nbsp;</td>
  </tr>
</table>

				
			</td>
			</tr>
			
			
			
        
      </table>
	    </td>
    <td ><img src="mm_spacer.gif" alt="" width="50" height="1" border="0" /></td>
        <td width="190" valign="top"><br />
		&nbsp;<br />
			</td>
	<td width="100%">&nbsp;</td>
  </tr>
  <tr>
    <td width="165">&nbsp;</td>
    <td width="50">&nbsp;</td>
    <td width="167">&nbsp;</td>
    <td width="138">&nbsp;</td>
    <td width="50">&nbsp;</td>
    <td width="190">&nbsp;</td>
	<td width="100%">&nbsp;</td>
  </tr>
</table>

</body>
</html>
