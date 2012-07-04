<div id="head">
<meta name="layout" content="main" />
</div>
<div class="body">
<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${studentHostelsinstance}">
		<div class="errors">
			<g:renderErrors bean="${studentHostelsinstance}" as="list" />
		</div>
	</g:hasErrors>
	<g:form controller="accomodationDetails" method="post">
	<center>
	    <input type="hidden" name="id" value="${studentHostelsinstance?.id}" />
	    <input type="hidden" name="version" value="${studentHostelsinstance?.version}" />
		<div class="dialog">
			<table >
			<br /><br /><h3><g:message code="Edit Student Hostel Availability"/></h3> <br />
				<tbody>
				<tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Select your Institution"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentHostelsinstance,field:'institutionDetails','errors')}">
						<g:select from= "${institutionDetailsInstanceList}" id="institutionDetails" optionKey="id" name="institutionDetails"   noSelection="['null':'-Select-']" value="${studentHostelsinstance?.institutionDetails?.id}"></g:select>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Intake capacity of Hostel"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentHostelsinstance,field:'intakeCapacity','errors')}">
							<input type="text" size="25" id="intakeCapacity" name="intakeCapacity" value="${fieldValue(bean:studentHostelsinstance,field:'intakeCapacity')}"/>
						</td>
				    </tr>
					<tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Hostel Type"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentHostelsinstance,field:'hostelType','errors')}">
							<g:select from= "${generalLookupList}" id="hostelType" optionKey="id" name="hostelType" noSelection="['null':'-Select-']" value="${hostelTypeInstance?.id}"></g:select>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Number of Students residing"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentHostelsinstance,field:'numberOfStudents','errors')}">
							<input type="text" size="25" id="numberOfStudents" name="numberOfStudents" value="${fieldValue(bean:studentHostelsinstance,field:'numberOfStudents')}"/>
						</td>
					 </tr>
					<tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Name of Hostel"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentHostelsinstance,field:'name','errors')}">
							<input type="text" size="25" id="name" name="name" value="${fieldValue(bean:studentHostelsinstance,field:'name')}"/>
						</td>
					</tr>
					<input type="hidden" id="institutionDetails.id" name="institutionDetails.id" value="${fieldValue(bean:institutionDetailsInstance,field:'id')}"/>
				</tbody>      
			</table>
		</div>
		<div>
			<g:actionSubmit class="pushbutton" value="Update" action="updateStudentsHostel"/>
			<g:actionSubmit class="pushbutton" value="Delete"  action="deleteStudentsHostel"/>
		</div>
		</center>
	</g:form>
</div>