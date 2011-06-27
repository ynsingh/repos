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
		
		<br /><h4>Authority Details</h4><br />
	<div id="content"> 	
<!-- Middle area starts here -->	
		<g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_ADMIN'}">
		<g:menu/>
		</g:if >	
		<div style="padding-left:400px;">						
				    <table align="center">
					<tbody>

					<tr class="prop">
					<td valign="top" class="name">ID:</td>
					<td valign="top" class="value">${authority.id}</td>
					</tr>

					<tr class="prop">
					<td valign="top" class="name">Authority Name:</td>
					<td valign="top" class="value">${authority.authority}</td>
					</tr>

					<tr class="prop">
					<td valign="top" class="name">Description:</td>
					<td valign="top" class="value">${authority.description}</td>
					</tr>

					<tr class="prop">
					<td valign="top" class="name" colspan="2">
					<div class="buttons">
					<g:form>
					<input type="hidden" name="id" value="${authority?.id}" />
					<span><g:actionSubmit class="edit" value="Edit" /></span>
					<span><g:actionSubmit class="delete"
					onclick="return confirm('Are you sure?');" value="Delete" /></span>
					</g:form>
					</div>
					</td>
					</tr>

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