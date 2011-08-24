<meta name="layout" content="main" />
<!-- ##################################  Layout body starts here  ###########################################-->
	<div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		      		<g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_ADMIN'}">
		  <g:menu/>
		</g:if >
		</div>

	<div id="content"> <!-- Start of content div -->

<!-- Middle area starts here -->	
		<br />
       <div align="center"><h3>&nbsp;&nbsp;&nbsp;Authority List</h3></div>
		<div align="right" style="padding-right:100px;">
		<g:link class="create" action="create"><strong>Add New Authority&nbsp;&gt;&gt;</strong></g:link></div>
		<br />
		<div align="center">						
						<table width="100%">
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
						</td>
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