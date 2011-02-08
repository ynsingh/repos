<head>
	<meta name="layout" content="main" />
	<title>LMS List</title>
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

                       		<br /><br />
				<div style="padding-left: 370px;"><h3>Edit Authority</h3></div>
				<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
				</g:if>
				<g:hasErrors bean="${authority}">
				<div class="errors">
				<g:renderErrors bean="${authority}" as="list" />
				</div>
				</g:hasErrors>

				
				<g:form>
				<input type="hidden" name="id" value="${authority.id}" />
				<input type="hidden" name="version" value="${authority.version}" />

				<table align="center">
				<tbody>
				<tr class="prop">
				<td valign="top" class="name"><label for="authority">Authority Name:</label></td>
				<td valign="top" class="value ${hasErrors(bean:authority,field:'authority','errors')}">
				<input type="text" id="authority" name="authority" value="${authority.authority?.encodeAsHTML()}"/>
				</td>
				</tr>

				<tr class="prop">
				<td valign="top" class="name"><label for="description">Description:</label></td>
				<td valign="top" class="value ${hasErrors(bean:authority,field:'description','errors')}">
				<input type="text" id="description" name="description" value="${authority.description?.encodeAsHTML()}"/>
				</td>
				</tr>

				
				<tr class="prop">
				<td colspan="3">
				<div class="buttons">
				<span><g:actionSubmit class="save" value="Update" /></span>
				<span><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
				</div>

				</td>

				</tr>
				</tbody>
				</table>
				</g:form>
				

         </div> <!-- End of content div -->


	</div>
<g:footer/>
</body>