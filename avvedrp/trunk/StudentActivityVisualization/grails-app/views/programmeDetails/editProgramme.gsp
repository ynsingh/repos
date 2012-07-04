<div id="head">
<meta name="layout" content="main" />
</div>
<div class="body">
<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${programmeDetailsInstance}">
		<div class="errors">
			<g:renderErrors bean="${programmeDetailsInstance}" as="list" />
		</div>
	</g:hasErrors>
	<g:form controller="programmeDetails" method="post">
	<center>
	    <input type="hidden" name="id" value="${programmeDetailsInstance?.id}" />
	    <input type="hidden" name="version" value="${programmeDetailsInstance?.version}" />
		<div class="dialog">
			<table >
			<br /><br /><h3><g:message code="Edit Programme Details"/></h3> <br />
				<tbody>
				 <tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Select your Institution"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${institutionDetailsInstanceList}" id="institutionDetails" optionKey="id" name="institutionDetails" onchange="${remoteFunction(controller:'programmeDetails',action:'facultyNameList1',update:'listFacultyName1',params:'\'facultyNameList1=\'+this.value')};"  noSelection="['null':'-Select-']" value="${institutionDetailsInstance?.id}"></g:select>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Faculty"/>:</label>
						</td>
						<td valign="top"  class="value">
							<div id="listFacultyName1">
							<g:select from= "${facultyInstanceList}" id="faculty" optionKey="id" name="faculty" onchange="${remoteFunction(controller:'programmeDetails',action:'departmentNameList',update:'listDepartmentName',params:'\'departmentNameList=\'+this.value')};" noSelection="['null':'-Select-']" value="${facultyInstance?.id}"></g:select>
						    </div>
						 </td>
						 <td valign="top" class="name">
							<label for="name" ><g:message code="Department"/>:</label>
						</td>
						<td valign="top"  class="value">
							<div id="listDepartmentName">
							<g:select from= "${departmentInstanceList}" id="department" optionKey="id" name="department" noSelection="['null':'-Select-']" value="${programmeDetailsInstance?.department?.id}"></g:select>
						    </div>
						 </td>
						</tr>
						<tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Select Mode"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${modeList}" id="mode" optionKey="id" name="mode"   noSelection="['null':'-Select-']" value="${modeInstance?.id}"></g:select>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Select Level"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${levelList}" id="level" optionKey="id" name="level"  noSelection="['null':'-Select-']" value="${levelInstance?.id}"></g:select>
						 </td>
						</tr>
						<tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Name of the Programme"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'programmeName','errors')}">
							<input type="text" size="20" id="programmeName" name="programmeName" value="${fieldValue(bean:programmeDetailsInstance,field:'programmeName')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Programme Code"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'programmeCode','errors')}">
							<input type="text" size="20" id="programmeCode" name="programmeCode" value="${fieldValue(bean:programmeDetailsInstance,field:'programmeCode')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Name of Discipline"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'disciplineName','errors')}">
							<input type="text" size="20" id="disciplineName" name="disciplineName" value="${fieldValue(bean:programmeDetailsInstance,field:'disciplineName')}"/>
						</td>
						</tr>
						<tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Discipline Code"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'disciplineCode','errors')}">
							<input type="text" size="20" id="disciplineCode" name="disciplineCode" value="${fieldValue(bean:programmeDetailsInstance,field:'disciplineCode')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Name of Broad Discipline, if any"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'broadDisciplineName','errors')}">
							<input type="text" size="20" id="broadDisciplineName" name="broadDisciplineName" value="${fieldValue(bean:programmeDetailsInstance,field:'broadDisciplineName')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Broad Discipline Code"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'broadDIsciplineCode','errors')}">
							<input type="text" size="20" id="broadDIsciplineCode" name="broadDIsciplineCode" value="${fieldValue(bean:programmeDetailsInstance,field:'broadDIsciplineCode')}"/>
						</td>
						</tr>
						<tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Intake Capacity"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'intakeCapacity','errors')}">
							<input type="text" size="20" id="intakeCapacity" name="intakeCapacity" value="${fieldValue(bean:programmeDetailsInstance,field:'intakeCapacity')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Number Of Applicants"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'numberOfApplicants','errors')}">
							<input type="text" size="20" id="numberOfApplicants" name="numberOfApplicants" value="${fieldValue(bean:programmeDetailsInstance,field:'numberOfApplicants')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Duration Of Programme"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'programmeDuration','errors')}">
							<input type="text" size="20" id="programmeDuration" name="programmeDuration" value="${fieldValue(bean:programmeDetailsInstance,field:'programmeDuration')}"/>
						</td>
						</tr>
						<tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Type of Programme"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${typeList}" id="programmeType" optionKey="id" name="programmeType"   noSelection="['null':'-Select-']" value="${typeInstance?.id}"></g:select>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Examination System"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${examSystemList}" id="examinationSystem" optionKey="id" name="examinationSystem"   noSelection="['null':'-Select-']" value="${examSystemInstance?.id}"></g:select>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="University, through which approved"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'university','errors')}">
							<input type="text" size="20" id="university" name="university" value="${fieldValue(bean:programmeDetailsInstance,field:'university')}"/>
						</td>
						</tr>
						<input type="hidden" id="institutionDetails.id" name="institutionDetails.id" value="${fieldValue(bean:institutionDetailsInstance,field:'id')}"/>
				</tbody>      
			</table>
		</div>
		<div>
			<g:actionSubmit class="pushbutton" value="Update" action="updateProgramme" />
			<g:actionSubmit class="pushbutton" value="Delete"  action="deleteProgramme"/>
		</div>
		</center>
	</g:form>
</div>