<div id="head">
<meta name="layout" content="main" />
</div>
<div class="body">
	<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${affiliationDetailsInstance}">
		<div class="errors">
			<g:renderErrors bean="${affiliationDetailsInstance}" as="list" />
		</div>
	</g:hasErrors>
	<g:form controller="institutionDetails" method="post">
	<center>
	    <input type="hidden" name="id" value="${affiliationDetailsInstance?.id}" />
	    <input type="hidden" name="version" value="${affiliationDetailsInstance?.version}" />
		<div class="dialog">
		<br /><br /><h3><g:message code="Edit Affiliation Details"/></h3> <br />
	       <table >
				<tbody>
					<tr class="prop">
						<td valign="top" class="name">
								<label for="name" ><g:message code="University Name"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:affiliationDetailsInstance,field:'universityName','errors')}">
								<input type="text" size="25" id="universityName" name="universityName" value="${fieldValue(bean:affiliationDetailsInstance,field:'universityName')}"/>
						</td>
					</tr>
					<tr class="prop">
						<td valign="top" class="name">
								<label for="name"><g:message code="Code"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:affiliationDetailsInstance,field:'universityCode','errors')}">
								<input type="text" size="10" id="universityCode" name="universityCode" value="${fieldValue(bean:affiliationDetailsInstance,field:'universityCode')}"/>
						</td>
					</tr>
					<tr class="prop">
						<td valign="top" class="name">
								<label for="name" ><g:message code="Year of Affiliation"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:affiliationDetailsInstance,field:'yearOfAffiliation','errors')}">
								<input type="number" size="15" id="yearOfAffiliation" name="yearOfAffiliation" value="${fieldValue(bean:affiliationDetailsInstance,field:'yearOfAffiliation')}"/>
						</td>
					</tr>
					<input type="hidden" name="InstId" value="${session.InstId}" />
				</tbody>      
			</table>
		</div>
		<div>
			<g:actionSubmit class="pushbutton" value="Update" action="updateAffiliation" />
			<g:actionSubmit class="pushbutton" value="Delete"  action="deleteAffiliation"/>
		</div>
		</center>
	</g:form>
</div>