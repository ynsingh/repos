<div id="head">
<meta name="layout" content="main" />
</div>
<div class="body">
<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${infrastructureInstance}">
		<div class="errors">
			<g:renderErrors bean="${infrastructureInstance}" as="list" />
		</div>
	</g:hasErrors>
	<g:form controller="miscellaneous" method="post">
	<center>
	    <input type="hidden" name="id" value="${infrastructureInstance?.id}" />
	    <input type="hidden" name="version" value="${infrastructureInstance?.version}" />
		<div class="dialog">
			<table >
			<br /><br /><h3><g:message code="Edit Infrastructure"/></h3> <br />
				<tbody>
				   <tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Select your Institution"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${institutionDetailsInstanceList}" id="institutionDetails" optionKey="id" name="institutionDetails"  noSelection="['null':'-Select-']" value="${infrastructureInstance?.institutionDetails?.id}"></g:select>
						</td>
				    </tr>
				    <tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Infrastructure"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:infrastructureInstance,field:'infrastructure','errors')}">
							<input type="text" size="20" id="infrastructure" name="infrastructure" value="${fieldValue(bean:infrastructureInstance,field:'infrastructure')}"/>
						</td>
					</tr>
					<tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Description"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:infrastructureInstance,field:'description','errors')}">
							<textarea name="description" id="description" cols="20" rows="2"  >${infrastructureInstance?.description}</textarea>
						</td>
				   </tr>
				</tbody>      
			</table>
		</div>
		<div>
			<g:actionSubmit class="pushbutton" value="Update" action="updateInfrastructure" />
			<g:actionSubmit class="pushbutton" value="Delete"  action="deleteInfrastructure"/>
		</div>
		</center>
	</g:form>
</div>