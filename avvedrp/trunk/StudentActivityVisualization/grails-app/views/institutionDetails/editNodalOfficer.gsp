<div id="head">
<meta name="layout" content="main" />
</div>
<div class="body">
	<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${nodalOfficerDetailsInstance}">
		<div class="errors">
			<g:renderErrors bean="${nodalOfficerDetailsInstance}" as="list" />
		</div>
	</g:hasErrors>
	<g:form controller="institutionDetails" method="post">
	<center>
	    <input type="hidden" name="id" value="${nodalOfficerDetailsInstance?.id}" />
	    <input type="hidden" name="version" value="${nodalOfficerDetailsInstance?.version}" />
		<div class="dialog">
		<br /><br /><h3><g:message code="Edit Nodal Officer Details"/></h3> <br />
           <table >
				<tbody>
					<tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Nodal Officer Name"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:nodalOfficerDetailsInstance,field:'name','errors')}">
							<input type="text" size="25" id="name" name="name" value="${fieldValue(bean:nodalOfficerDetailsInstance,field:'name')}"/>
						</td>
					</tr>
					<tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Designation"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:nodalOfficerDetailsInstance,field:'designation','errors')}">
							<input type="text" size="25" id="designation" name="designation" value="${fieldValue(bean:nodalOfficerDetailsInstance,field:'designation')}"/>
						</td>
					</tr>
					<tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Contact Number"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:nodalOfficerDetailsInstance,field:'contactNumber','errors')}">
							<input type="text" size="25" id="contactNumber" name="contactNumber" value="${fieldValue(bean:nodalOfficerDetailsInstance,field:'contactNumber')}"/>
						</td>
					</tr>
					<tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Email Id"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:nodalOfficerDetailsInstance,field:'emailId','errors')}">
							<input type="text" size="25" id="emailId" name="emailId" value="${fieldValue(bean:nodalOfficerDetailsInstance,field:'emailId')}"/>
						</td>
					</tr>
						<input type="hidden" name="InstId" value="${session.InstId}" />
				</tbody>      
			</table>
		</div>
		<div>
			<g:actionSubmit class="pushbutton" value="Update" action="updateNodalOfficer" />
			<g:actionSubmit class="pushbutton" value="Delete"  action="deleteNodalOfficer"/>
		</div>
		</center>
	</g:form>
</div>