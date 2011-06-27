<meta name="layout" content="main" />
<!-- ##################################  Layout body starts here  ###########################################-->
	<div id="wrapper">
		<div id="head">
			<div class="innnerBanner">
			<g:isLoggedIn>
			<div class="loginLink">
			<span>
			<font face="verdana" color:#01518e; font-weight:bold; text-decoration: none>			
			<b>${session.UserId}</b> (<a href="${resource(dir:'/logout')}" class="logout">Logout</a>)
			</span>
			</div>
			</g:isLoggedIn>
			</div>		    
		</div>
	<div id="content"> 	
	
<!-- Middle area starts here -->	
		<g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_STAFF'}">
		<g:menu/>
		</g:if >
		<br /><br />
		<div style="float: left; width: 790px;margin-right: 5px;">


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