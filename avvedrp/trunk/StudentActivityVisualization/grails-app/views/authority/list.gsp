<meta name="layout" content="main" />
<g:javascript src="jquery.js"/>
<g:javascript src="ddaccordion.js"/>

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
		
		<br /><h4>Authority</h4><br />
	<div id="content"> 	
<!-- Middle area starts here -->	
		<g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_ADMIN'}">
		<g:menu/>
		</g:if >		
		<div style="float: left; width: 790px; margin-right: 5px;">						
						<table width="100%"\ >
						<thead>
						<tr>
						<th>SI.No</th>
						<g:sortableColumn property="authority" title="Authority Name" />
						<g:sortableColumn property="description" title="Description" />
						<th>Action</th>
						</tr>
						</thead>
						<tbody>
						<g:each in="${authorityList}" status="i" var="authority">
						<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
						<td style="padding-left:15px;">${i+1}</td>
						<td>${authority.authority?.encodeAsHTML()}</td>
						<td>${authority.description?.encodeAsHTML()}</td>
						<td class="actionButtons">
						<span class="actionButton">
						<g:link action="show" id="${authority.id}">View</g:link>
						<g:link action="edit" id="${authority.id}">&nbsp;/&nbsp;Edit</g:link>
						</span>
						</td>
						</tr>
						</g:each>
						
						<tr><td colspan="6">
						<div class="paginateButtons">
						<g:paginate total="${Authority.count()}" />
						</div>
						
						</td></tr>
						<tr><td colspan="6">
						<span class="menuButton"><g:link class="create" action="create"><strong>New Authority</strong></g:link></span></td></tr>
						</tbody>
						</table>             
		</div>
		
		<div style="clear: both;">&nbsp;</div>
		<br />
<!-- Middle area ends here -->		
  </div> <!-- End of content div -->
</div>
<g:footer/>
<!-- ##################################  Layout body ends here  ###########################################-->