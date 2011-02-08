<head>
	<meta name="layout" content="main" />
	<title>LMS List</title>
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

/*
    $(function(){

         $('div.inner').wrap('<div class="outer"></div>');
	$('div.inner').corner("round 8px").parent().css('padding', '4px').corner("round 10px")
    });
    */


          function getSiteName()
        {
        var selected_index = document.getElementById("siteId").selectedIndex;

      	document.getElementById("courseName").value = document.getElementById("siteId").value;

         }

        function Redirect()
        {

			 if(document.getElementById("siteId").value=="null")
        	{
        	alert("Please Select a Course");
        	return false;
        	}
          var selected_index = document.getElementById("siteId").selectedIndex;
          window.location="../courseActivity/listSite?id="+ document.getElementById("siteId").value+"&siteName="+document.getElementById("siteId").options[selected_index].text;
        }

         function Redirect1()
        {

         if(document.getElementById("siteId").value=="null")
        	{
        	alert("Please Select a Course");
        	return false;
        	}

                var selected_index = document.getElementById("siteId").selectedIndex;
          window.location="../courseActivity/listSiteEvent?id="+ document.getElementById("siteId").value+"&siteName="+document.getElementById("siteId").options[selected_index].text;
        }

       function Redirect2()
        {

         if(document.getElementById("siteId").value=="null")
        	{
        	alert("Please Select a Course");
        	return false;
        	}
         var selected_index = document.getElementById("siteId").selectedIndex;
          window.location="../courseActivity/listUser?id="+ document.getElementById("siteId").value+"&siteName="+document.getElementById("siteId").options[selected_index].text;
        }

       function Redirect3()
        {

         if(document.getElementById("siteId").value=="null")
        	{
        	alert("Please Select a Course");
        	return false;
        	}
          var selected_index = document.getElementById("siteId").selectedIndex;
          window.location="../analytics.jsp?query=PerStudentSummaryTotal&siteName="+document.getElementById("siteId").options[selected_index].text;
        }


</script>
       </head>
<body>
	<div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		      <g:if test="${session.ROLE == 'ROLE_ADMIN'}">
                        <g:menu/>
                        </g:if >
		</div>

	<div id="content"> <!-- Start of content div -->

					<div class="inner" >
					<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">

					<tr>
					<td height="117"><img src="../courseAnalyiticTitle.gif" width="260" height="102" /></td>
					<td><g:link action="listAdmin" id="" params=""><img src="../summaryCourseAnalyitic.jpg" border="0" /></g:link></td>
					<td><g:link action="listSiteVisit" id="${params.siteId}" params="[siteId:params.siteId,siteName:params.siteName]"><img src="../visitdetails.jpg" border="0" /></g:link></td>
					<g:if test="${session.LMS == 'Sakai'}">
					<td><g:link url="../analytics.jsp?query=studentlearningtool"><img src="../adhocTool.jpg" border="0" /></g:link></td>
					</g:if>
					<g:if test="${session.LMS != 'Sakai'}">
					<td><g:link url="../analytics.jsp?query=master"><img src="../adhocTool.jpg" border="0" /></g:link></td>
					</g:if>
					</tr>
					</table>
					</div>

					<div align ="center">
					<h2>Course Wise Analysis </h2>
					<br>
					<label><strong>Select Course:&nbsp;&nbsp;&nbsp;&nbsp; </strong></label>
					<g:select optionKey="siteId" optionValue="siteName" from="${siteList}" id="siteId" name="siteId" noSelection="[null:'------------ Select ----------']" value="${siteId}"   ></g:select>
					<input type="hidden" id="courseName" name="courseName" value=""></input>
					</div>


					<div class="inner">
					<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">

					<td width="307"   height="117"><img src="../studentPerformanceTitle.gif" width="260" height="102" /></td>
					<td width="169">
					<img src="../visualDetails.jpg" border="0" onclick="Redirect()" style="cursor: pointer;"/></td>
					<td width="164"><img src="../timeUtilization.jpg" border="0" onclick="Redirect1()"  style="cursor: pointer;"/></td>
					<td width="161"><img src="../summary.jpg" border="0" onclick="Redirect2()"  style="cursor: pointer;"/></td>

					</tr>

					<g:if test="${session.LMS == 'Sakai'}">
					<tr>
					<td height="117"><img src="../adocReportingTitle.gif" width="260" height="102" /></td>
					<td><img src="../perStudentSummary.jpg" border="0" onclick="Redirect3()"/></td>

					</tr>
					</g:if>

					</table>
					</div>



         </div> <!-- End of content div -->


	</div>
<g:footer/>
</body>