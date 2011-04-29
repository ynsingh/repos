<head>
	<meta name="layout" content="main" />
	<title>LMS List</title>
	 <g:javascript src="jquery.js"/>
	<script>	
	function validate(thisform)
	{
	  var uname=$('#username').val().split("@"); 	 
	  $('#userRealName').val(uname[0]);
	  thisform.submit();
	}
	</script>
       </head>
<body>
	<div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		      <g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_ADMIN'}">
                        <g:menu/>
                        </g:if >
		</div>

	<div id="content"> <!-- Start of content div -->

					   <div style="padding-left: 40px;"><h3>Edit User</h3></div>
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



         </div> <!-- End of content div -->


	</div>
<g:footer/>
</body>