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


<script type="text/javascript" src="../jquery-1.3.1.js"></script>
<script type="text/javascript" src="../jquery.corner.js"></script>

<script type="text/javascript">
   
    
    $(function(){
			
         $('div.inner').wrap('<div class="outer"></div>');
	$('div.inner').corner("round 8px").parent().css('padding', '4px').corner("round 10px")
    });
</script>




</head>
<style>
html * {
    margin: 0;
    /*padding: 0; SELECT NOT DISPLAYED CORRECTLY IN FIREFOX */
}
body {
    background-repeat:repeat-x;
    background-color: #fafafa;
    background-image:url(../bg.jpg);
    font: 11px verdana, arial, helvetica, sans-serif;
 
}
#bread { width:100%; height:15px;  background: #ecedef;    }

div.outer { margin-top:50px; margin-left:auto; margin-right:auto; background: #d0d0d0; width:750px; }

div.inner {  margin-left:auto; margin-right:auto;background: #fff;width:750px;   }

a:link, a:visited, a:hover {
color:#33333;
font-weight:bold;
text-decoration:none;
}
.nav {
    	
    background: #ecedef;
    
    padding: 7px 50px;
}

.menuButton {
    font-size: 10px;
    padding: 0 5px;
}
.menuButton a {
    color: #333;
    padding: 4px 6px;
}
.menuButton a.home {
    background: url(../images/skin/house.png) center left no-repeat;
    color: #333;
    padding-left: 25px;
}
.menuButton a.list {
    background: url(../images/skin/database_table.png) center left no-repeat;
    color: #333;
    padding-left: 25px;
}
.menuButton a.create {
    background: url(../images/skin/database_add.png) center left no-repeat;
    color: #333;
    padding-left: 25px;
}

</style>




<body >
    <img src="${createLinkTo(dir:'',file:'header.jpg')}"/>
	<div class ="nav">
		
	</div>
	<div align ="center">
	 	<h2>LMS: ${session.LMS} &nbsp;&nbsp;&nbsp;&nbsp; Site: ${session.siteName}</h2>
	 </div>
	<div class="inner" >

		<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td width="307"   height="117"><img src="../studentPerformanceTitle.gif" width="260" height="102" /></td>
		    <td width="169"><g:link action="listSite" id="${params.siteId}" params="[siteId:params.siteId,siteName:params.siteName]">
        <img src="../visualDetails.jpg" border="0" /></g:link></td>
		    <td width="164"><g:link action="listSiteEvent" id="${params.siteId}" params="[siteId:params.siteId,siteName:params.siteName]"><img src="../timeUtilization.jpg" border="0" /></g:link></td>
		    <td width="161"><g:link action="listUser" id="${params.siteId}" params="[siteId:params.siteId,siteName:params.siteName]"><img src="../summary.jpg" border="0" /></g:link></td>
		    <td width="39">&nbsp;</td>
		  </tr>
		  <tr>
		    <td height="117"><img src="../courseAnalyiticTitle.gif" width="260" height="102" /></td>
		    <td><g:link action="list" id="${params.siteId}" params="[siteId:params.siteId,siteName:params.siteName]"><img src="../summaryCourseAnalyitic.jpg" border="0" /></g:link></td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		 <g:if test="${session.LMS == 'Sakai'}">
		  <tr>
		    <td height="117"><img src="../adocReportingTitle.gif" width="260" height="102" /></td>
		    <td><g:link url="../analytics.jsp?query=PerStudentSummaryTotal" ><img src="../perStudentSummary.jpg" border="0" /></g:link></td>
		    <td><g:link url="../analytics.jsp?query=studentlearningtool"><img src="../courseUsageSummary.jpg" border="0" /></g:link></td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		  </tr>
		  </g:if>
		</table>
	</div>
</body>

</html>
