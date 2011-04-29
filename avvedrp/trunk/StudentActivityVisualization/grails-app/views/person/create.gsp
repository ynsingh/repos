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

					   <div style="padding-left: 40px;"><h3>Add User</h3></div>
						<g:if test="${flash.message}">
						<div class="message">${flash.message}</div>
						</g:if>
						<g:hasErrors bean="${person}">
						<div class="errors">
						<g:renderErrors bean="${person}" as="list" />
						</div>
						</g:hasErrors>

						<g:form name="user" action="save">
						<table  style="padding-left: 40px;">
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
						<input type="password" id="passwd" name="passwd" value=""/>
						</td>
						</tr>

						<tr class="prop">
						<td valign="top" class="name"><label for="enabled">Enabled:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'enabled','errors')}">
						<g:checkBox name="enabled" value="${person.enabled}" ></g:checkBox>
						</td>
						</tr>

						<tr class="prop">
						<td valign="top" class="name"><label for="description">Description:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'description','errors')}">
						<input type="text" id="description" name="description" value="${person.description?.encodeAsHTML()}"/>
						</td>
						</tr>

					

						<tr class="prop">
						<td valign="top" class="name" align="left">Assign Roles:</td>
						</tr>

						<g:each in="${authorityList}">
						<tr>
						<td valign="top" class="name" align="left">${it.authority.encodeAsHTML()}</td>
						<td align="left"><g:checkBox name="${it.authority}"/></td>
						</tr>
						</g:each>
						<tr class="prop">
						<td colspan="3">
						<div class="buttons">
						<span><input class="save" type="button" value="Create" onclick="return validate(document.user);" /></span>
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