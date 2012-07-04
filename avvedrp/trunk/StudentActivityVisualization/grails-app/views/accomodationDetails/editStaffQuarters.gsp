<div id="head">
<meta name="layout" content="main" />
</div>
<div class="body">
<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${staffQuartersInstance}">
		<div class="errors">
			<g:renderErrors bean="${staffQuartersInstance}" as="list" />
		</div>
	</g:hasErrors>
	<g:form controller="accomodationDetails" method="post">
	<center>
	    <input type="hidden" name="id" value="${staffQuartersInstance?.id}" />
	    <input type="hidden" name="version" value="${staffQuartersInstance?.version}" />
		<div class="dialog">
			<table >
			<br /><br /><h3><g:message code="Edit Staff Quarters Availability"/></h3> <br />
				<tbody>
				<tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Select your Institution"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:staffQuartersInstance,field:'institutionDetails','errors')}">
						<g:select from= "${institutionDetailsInstanceList}" id="institutionDetails" optionKey="id" name="institutionDetails" value="${staffQuartersInstance?.institutionDetails?.id}"></g:select>
						</td>
					 </tr>
					 <tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Category"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:staffQuartersInstance,field:'categoryType','errors')}">
							<g:select from= "${generalLookupList}" id="categoryType" optionKey="id" name="categoryType"  noSelection="['null':'-Select-']" value="${categoryTypeInstance?.id}"></g:select>
						</td>
					 </tr>
					 <tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Number Of Quarters"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:staffQuartersInstance,field:'numberOfQuarters','errors')}">
							<input type="text" size="25" id="numberOfQuarters" name="numberOfQuarters" value="${fieldValue(bean:staffQuartersInstance,field:'numberOfQuarters')}"/>
						</td>
					</tr>
						<input type="hidden" id="institutionDetails.id" name="institutionDetails.id" value="${fieldValue(bean:institutionDetailsInstance,field:'id')}"/>
				</tbody>      
			</table>
		</div>
		<div>
			 <g:actionSubmit class="pushbutton" value="Update" action="updateStaffQuarters" />
			 <g:actionSubmit class="pushbutton" value="Delete"  action="deleteStaffQuarters"/>
		</div>
		</center>
	</g:form>
</div>