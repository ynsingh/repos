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

                                 <div style="padding-left: 380px;"><h3>Authorities</h3></div>
				<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
				</g:if>
				<g:hasErrors bean="${authority}">
				<div class="errors">
				<g:renderErrors bean="${authority}" as="list" />
				</div>
				</g:hasErrors>

				<g:form action="save">
				<table align="center">
				<tbody>
				<tr class="prop">
				<td valign="top" class="name"><label for="authority">Authority Name:</label></td>
				<td valign="top" class="value ${hasErrors(bean:authority,field:'authority','errors')}">
				<input type="text" id="authority" name="authority" value="${authority?.authority?.encodeAsHTML()}"/>
				</td>
				</tr>

				<tr class="prop">
				<td valign="top" class="name"><label for="description">Description:</label></td>
				<td valign="top" class="value ${hasErrors(bean:authority,field:'description','errors')}">
				<input type="text" id="description" name="description" value="${authority?.description?.encodeAsHTML()}"/>
				</td>
				</tr>
				<tr class="prop">
				<td valign="top" class="name" colspan="3">
				<div class="buttons">
				<span><input class="save" type="submit" value="Create" /></span>
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