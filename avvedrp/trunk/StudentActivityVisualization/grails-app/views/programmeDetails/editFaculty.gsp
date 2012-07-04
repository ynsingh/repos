<div id="head">
<meta name="layout" content="main" />
</div>
<div class="body">
<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${facultyInstance}">
		<div class="errors">
			<g:renderErrors bean="${facultyInstance}" as="list" />
		</div>
	</g:hasErrors>
	<g:form controller="programmeDetails" method="post">
	<center>
	    <input type="hidden" name="id" value="${facultyInstance?.id}" />
	    <input type="hidden" name="version" value="${facultyInstance?.version}" />
		<div class="dialog">
		<br /><br /><h3><g:message code="Edit Faculty"/></h3> <br />
			<table >
				<tbody>
				<tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Select your Institution"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:facultyInstance,field:'institutionDetails','errors')}">
						<g:select from= "${institutionDetailsInstanceList}" id="institutionDetails" optionKey="id" name="institutionDetails"  noSelection="['null':'-Select-']" value="${facultyInstance?.institutionDetails?.id}"></g:select>
						</td>
					 </tr>
					 <tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Faculty Name"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:facultyInstance,field:'facultyName','errors')}">
							<input type="text" size="25" id="facultyName" name="facultyName" value="${fieldValue(bean:facultyInstance,field:'facultyName')}"/>
						</td>
					 </tr>
					 <tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Faculty code"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:facultyInstance,field:'facultyCode','errors')}">
							<input type="text" size="25" id="facultyCode" name="facultyCode" value="${fieldValue(bean:facultyInstance,field:'facultyCode')}"/>
						</td>
					</tr>
						<input type="hidden" id="institutionDetails.id" name="institutionDetails.id" value="${fieldValue(bean:institutionDetailsInstance,field:'id')}"/>
				</tbody>      
			</table>
		</div>
		<div>
			<g:actionSubmit class="pushbutton" value="Update" action="updateFaculty" />
			<g:actionSubmit class="pushbutton" value="Delete"  action="deleteFaculty"/>
		</div>
		</center>
	</g:form>
</div>