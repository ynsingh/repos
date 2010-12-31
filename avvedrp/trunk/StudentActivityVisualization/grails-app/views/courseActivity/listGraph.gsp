<head>
	<meta name="layout" content="main" />
	<title>Home Page</title>
      </head>

<body>

	<div id="container">

        <div id="wrapper">
            <div id="content">

                <br />
                <div id="box">
              <h2>&nbsp;&nbsp;&nbsp;&nbsp; ${session.siteName}</h2>




            <table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td width="307"   height="117"><img src="../studentPerformanceTitle.gif" width="260" height="102" /></td>
		    <td width="169"><g:link action="listSite" id="${params.siteId}" params="[siteId:params.siteId,siteName:params.siteName]">
        <img src="../visualDetails.jpg" border="0" /></g:link></td>
		    <td width="164"><g:link action="listSiteEvent" id="${params.siteId}" params="[siteId:params.siteId,siteName:params.siteName]"><img src="../timeUtilization.jpg" border="0" /></g:link></td>
		    <td width="161"><g:link action="listUser" id="${params.siteId}" params="[siteId:params.siteId,siteName:params.siteName]"><img src="../summary.jpg" border="0" /></g:link></td>
		  </tr>
		  <tr>
		    <td height="117"><img src="../courseAnalyiticTitle.gif" width="260" height="102" /></td>
		   <td colspan="3"><g:link action="courseusage" id="${params.siteId}" params="[siteId:params.siteId,siteName:params.siteName]"><img src="../summaryCourseAnalyitic.jpg" border="0" /></g:link></td>
		 
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
            </div>
        
            <g:sideMenu/>
         
      </div>
         <g:styleSwitcher/>
</div>

</body>
