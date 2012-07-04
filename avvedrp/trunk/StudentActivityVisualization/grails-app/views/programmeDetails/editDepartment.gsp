<div id="head">
<meta name="layout" content="main" />
</div>
<div class="body">
<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${departmentInstance}">
		<div class="errors">
			<g:renderErrors bean="${departmentInstance}" as="list" />
		</div>
	</g:hasErrors>
	<g:form controller="programmeDetails" method="post">
	<center>
	    <input type="hidden" name="id" value="${departmentInstance?.id}" />
	    <input type="hidden" name="version" value="${departmentInstance?.version}" />
		<div class="dialog">
		<br /><br /><h3><g:message code="Edit Department Details"/></h3> <br />
			<table >
				<tbody>
				<tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Select your Institution"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:departmentInstance,field:'institutionDetails','errors')}">
							<g:select from= "${institutionDetailsInstanceList}" id="institutionDetails" optionKey="id" name="institutionDetails" onchange="${remoteFunction(controller:'programmeDetails',action:'facultyNameList',update:'listFacultyName',params:'\'facultyNameList=\'+this.value')};" noSelection="['null':'-Select-']" value="${institutionDetailsInstance?.id}"></g:select>
						</td>
					 </tr>
					 <tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Faculty"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:departmentInstance,field:'faculty','errors')}">
							<div id="listFacultyName">
							<g:select from= "${facultyInstanceList}" id="faculty" optionKey="id" name="faculty" noSelection="['null':'-Select-']" value="${departmentInstance?.faculty?.id}"></g:select>
						    </div>
						    </td>
					 </tr>
					 <tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Name of Department"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:departmentInstance,field:'deptName','errors')}">
							<input type="text" size="25" id="deptName" name="deptName" value="${fieldValue(bean:departmentInstance,field:'deptName')}"/>
						</td>
					</tr>
					<tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Department Code"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:departmentInstance,field:'deptCode','errors')}">
							<input type="text" size="25" id="deptCode" name="deptCode" value="${fieldValue(bean:departmentInstance,field:'deptCode')}"/>
						</td>
					</tr>
						<input type="hidden" id="institutionDetails.id" name="institutionDetails.id" value="${fieldValue(bean:institutionDetailsInstance,field:'id')}"/>
				</tbody>      
			</table>
		</div>
		<div>
			<g:actionSubmit class="pushbutton" value="Update" action="updateDepartment" />
			<g:actionSubmit class="pushbutton" value="Delete"  action="deleteDepartment"/>
		</div>
		</center>
	</g:form>
</div>