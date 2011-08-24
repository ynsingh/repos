<meta name="layout" content="main" />	
<!-- ##################################  Layout body starts here  ###########################################-->
	  <div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		       <g:menu/>
		</div>		
	<div id="content"> 
		<div align="center">


             	<div align="center"><h3>Course : ${sel_course}</h3></div>
                                        <br />   <br />
				<table width="723" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
				<td width="284"   height="117">
				<img src="${resource(dir:'images/links',file:'studentPerformanceTitle.gif')}"
	
				</td>
				<td width="104">						
				<g:link action="showStaffVisualDetails"><img src="${resource(dir:'images/links',file:'visualDetails.jpg')}" border="0" /></g:link>				
				</td>
				<td width="116">					
				<g:link action="showStaffTimeUtilization"><img src="${resource(dir:'images/links',file:'timeUtilization.jpg')}" border="0" /></g:link>
				</td>
				<td width="104">
				<g:link action="showStaffSummary"><img src="${resource(dir:'images/links',file:'summary.jpg')}" border="0" /></g:link>
				</td>
				<td width="104">				
				<g:link url="../analytics.jsp?query=master"><img src="${resource(dir:'images/links',file:'adhocTool.jpg')}" border="0" /></g:link>				
				</td>
				</tr>
				<tr>
				<td height="117">
				<img src="${resource(dir:'images/links',file:'courseAnalyiticTitle.gif')}"
				</td>
				<td>
				<g:link url="staffCourseSummary"><img src="${resource(dir:'images/links',file:'summaryCourseAnalyitic.jpg')}" border="0" /></g:link>				
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td width="104"></td>
				<td width="11"><g:link action="showStaffSummary"></g:link></td>	
				</tr>			
						
				</table>


		</div>
		
		<div style="clear: both;">&nbsp;</div>
		<br />
<!-- Middle area ends here -->		
  </div> <!-- End of content div -->
</div>
<g:footer/>
<!-- ##################################  Layout body ends here  ###########################################-->