<meta name="layout" content="main" />	

<div id="head">
		<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'general.css')}" />
		<g:javascript library="formValidation"/>
		<title>Brihaspati Login</title>
</div>
<div class="body">
		<h1>Login</h1>
		
		<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${institutionDetailsInstance}">
				<div class="errors">
						<g:renderErrors bean="${institutionDetailsInstance}" as="list" />
				</div>
		</g:hasErrors>
		<g:form action="register">
			<div class="dialog">
				<table>
				<tbody>
					<tr class="prop">
						<td valign="top" class="name"><label for="username">Email:</label></td>
						<td>
							<input type="text" id='email' name="email" value="${emailValue}" "/>
						</td>
					</tr>
				</tbody>
				</table>
			</div>

			<div>
				<input type="submit" value="Register" class="save"/>
			</div>

		</g:form>
</div>

