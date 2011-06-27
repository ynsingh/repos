<meta name="layout" content="main" />
<g:javascript src="jquery.js"/>
<g:javascript src="ddaccordion.js"/>
<script>	
function validate(thisform)
{
  var uname=$('#username').val().split("@"); 	 
  $('#userRealName').val(uname[0]);
  thisform.submit();
}
</script>
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
		
		<br /><h4>Edit User</h4><br />
	<div id="content"> 	
<!-- Middle area starts here -->	
		<g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_ADMIN'}">
		<g:menu/>
		</g:if >	
		<div style="padding-left:350px;">						
			<g:if test="${flash.message}">
					<div class="message">${flash.message}</div>
					</g:if>
					<g:hasErrors bean="${person}">
					<div class="errors">
					<g:renderErrors bean="${person}" as="list" />
					</div>
					</g:hasErrors>

					<div class="prop">					
					<g:form name="edituser">
					<input type="hidden" name="id" value="${person.id}" />
					<input type="hidden" name="version" value="${person.version}" />
					<table style="padding-left: 40px;">
					<tbody>

					<tr class="prop">
					<td valign="top" class="name"><label for="username">User Name:</label></td>
					<td valign="top" class="value ${hasErrors(bean:person,field:'username','errors')}">
					<input type="text" id="username" name="username" value="${person.username?.encodeAsHTML()}"/>
					</td>
					</tr>

					<tr class="prop">
					<td valign="top" class="name"><label for="passwd">Password:</label></td>
					<td valign="top" class="value ${hasErrors(bean:person,field:'passwd','errors')}">
					<input type="password" id="passwd" name="passwd" value="${person.passwd?.encodeAsHTML()}"/>
					</td>
					</tr>

					<tr class="prop">
					<td valign="top" class="name"><label for="enabled">Enabled:</label></td>
					<td valign="top" class="value ${hasErrors(bean:person,field:'enabled','errors')}">
					<g:checkBox name="enabled" value="${person.enabled}"/>
					</td>
					</tr>

					<tr class="prop">
					<td valign="top" class="name"><label for="description">Description:</label></td>
					<td valign="top" class="value ${hasErrors(bean:person,field:'description','errors')}">
					<input type="text" id="description" name="description" value="${person.description?.encodeAsHTML()}"/>
					</td>
					</tr>

					

					<tr class="prop">
					<td valign="top" class="name"><label for="authorities">Roles:</label></td>
					<td valign="top" class="value ${hasErrors(bean:person,field:'authorities','errors')}">
					<ul>
					<g:each var="entry" in="${roleMap}">
					<li>${entry.key.authority.encodeAsHTML()}
					<g:checkBox name="${entry.key.authority}" value="${entry.value}"/>
					</li>
					</g:each>
					</ul>
					</td>
					</tr>
					<tr class="prop">
					<td colspan="3">
					<div class="buttons">
					<span><g:actionSubmit class="save" value="Update"  onclick="return validate(document.edituser);"/></span>
					<span><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
					</div>

					</td>

					</tr>

					</tbody>
					</table>
					<input type="hidden" name="userRealName" id="userRealName" value=""/>
					</g:form>
     
		</div>
		
		<div style="clear: both;">&nbsp;</div>
		<br />
<!-- Middle area ends here -->		
  </div> <!-- End of content div -->
</div>
<g:footer/>
<!-- ##################################  Layout body ends here  ###########################################-->