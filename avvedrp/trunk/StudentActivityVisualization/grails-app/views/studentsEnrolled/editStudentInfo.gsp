<div id="head">
<meta name="layout" content="main" />
</div>
<div class="body">
<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${studentsEnrolledInstance}">
		<div class="errors">
			<g:renderErrors bean="${studentsEnrolledInstance}" as="list" />
		</div>
	</g:hasErrors>
	<g:form controller="studentsEnrolled" method="post">
	<center>
	    <input type="hidden" name="id" value="${studentsEnrolledInstance?.id}" />
	    <input type="hidden" name="version" value="${studentsEnrolledInstance?.version}" />
		<div class="dialog">
			<table >
			<br /><br /><h3><g:message code="Edit Enrolled Students' Details"/></h3> <br />
				<tbody>
				   <tr class="prop">
				        <td valign="top" class="name">
							<label for="name" ><g:message code="Select your Institution"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${institutionDetailsInstanceList}" id="institutionDetails" optionKey="id" name="institutionDetails" onchange="${remoteFunction(controller:'studentsEnrolled',action:'facultyNameList',update:'listFacultyName',params:'\'facultyNameList=\'+this.value')};"  noSelection="['null':'-Select-']" value="${institutionDetailsInstance?.id}"></g:select>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Faculty"/>:</label>
						</td>
						<td valign="top"  class="value">
							<div id="listFacultyName">
							<g:select from= "${facultyInstanceList}" id="faculty" optionKey="id" name="faculty" onchange="${remoteFunction(controller:'studentsEnrolled',action:'departmentNameList',update:'listDepartmentName',params:'\'departmentNameList=\'+this.value')};" noSelection="['null':'-Select-']" value="${facultyInstance?.id}"></g:select>
						    </div>
						 </td>
						 <td valign="top" class="name">
							<label for="name" ><g:message code="Department"/>:</label>
						</td>
						<td valign="top"  class="value">
							<div id="listDepartmentName">
							<g:select from= "${departmentInstanceList}" id="department" optionKey="id" name="department" onchange="${remoteFunction(controller:'studentsEnrolled',action:'programmeNameList',update:'listProgrammeName',params:'\'programmeNameList=\'+this.value')};" noSelection="['null':'-Select-']" value="${departmentInstance?.id}"></g:select>
						    </div>
						</td>
					</tr>
					<tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Select Programme"/>:</label>
						</td>
					    <td valign="top"  class="value">
							<div id="listProgrammeName">
							<g:select from= "${programmeDetailsInstanceList}" id="programmeDetails" optionKey="id" name="programmeDetails" onchange="${remoteFunction(controller:'studentsEnrolled',action:'studentsEnrolledList',update:'listStudentsEnrolled',params:'\'studentsEnrolledList=\'+this.value')};" noSelection="['null':'-Select-']" value="${studentsEnrolledInstance?.programmeDetails?.id}"></g:select>
						    </div>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Year"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentsEnrolledInstance,field:'year','errors')}">
							<input type="text" size="8" id="year" name="year" value="${fieldValue(bean:studentsEnrolledInstance,field:'year')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Category"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${categoryList}" id="category" optionKey="id" name="category"   noSelection="['null':'-Select-']" value="${categoryInstance?.id}"></g:select>
						</td>
				 </tr>
					 <tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Total Strength"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentsEnrolledInstance,field:'totalStudents','errors')}">
							<input type="text" size="8" id="totalStudents" name="totalStudents" value="${fieldValue(bean:studentsEnrolledInstance,field:'totalStudents')}"/>
						</td>
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Female Strength"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentsEnrolledInstance,field:'femaleStudents','errors')}">
							<input type="text" size="8" id="femaleStudents" name="femaleStudents" value="${fieldValue(bean:studentsEnrolledInstance,field:'femaleStudents')}"/>
						</td>
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Total Strength of PWD Students"/>:</label>
						</td>
					    <td valign="top"  class="value ${hasErrors(bean:studentsEnrolledInstance,field:'totalPwdStudents','errors')}">
							<input type="text" size="8" id="totalPwdStudents" name="totalPwdStudents" value="${fieldValue(bean:studentsEnrolledInstance,field:'totalPwdStudents')}"/>
						</td>
						
					<tr class="prop">
					   <td valign="top" class="name">
							<label for="name" ><g:message code="Female Strength of PWD Students"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentsEnrolledInstance,field:'femalePwdStudents','errors')}">
							<input type="text" size="8" id="femalePwdStudents" name="femalePwdStudents" value="${fieldValue(bean:studentsEnrolledInstance,field:'femalePwdStudents')}"/>
						</td>
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Total Strength of Muslim Students"/>:</label>
						</td>
					    <td valign="top"  class="value ${hasErrors(bean:studentsEnrolledInstance,field:'totalMuslimStudents','errors')}">
							<input type="text" size="8" id="totalMuslimStudents" name="totalMuslimStudents" value="${fieldValue(bean:studentsEnrolledInstance,field:'totalMuslimStudents')}"/>
						</td>
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Female Strength of Muslim Students"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentsEnrolledInstance,field:'femaleMuslimStudents','errors')}">
							<input type="text" size="8" id="femaleMuslimStudents" name="femaleMuslimStudents" value="${fieldValue(bean:studentsEnrolledInstance,field:'femaleMuslimStudents')}"/>
						</td>
				     </tr>
					 <tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Total Strength of Other Minorities Students"/>:</label>
						</td>
					    <td valign="top"  class="value ${hasErrors(bean:studentsEnrolledInstance,field:'totalOthMinStudents','errors')}">
							<input type="text" size="8" id="totalOthMinStudents" name="totalOthMinStudents" value="${fieldValue(bean:studentsEnrolledInstance,field:'totalOthMinStudents')}"/>
						</td>
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Female Strength of Other Minorities Students"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentsEnrolledInstance,field:'femaleOthMinStudents','errors')}">
							<input type="text" size="8" id="femaleOthMinStudents" name="femaleOthMinStudents" value="${fieldValue(bean:studentsEnrolledInstance,field:'femaleOthMinStudents')}"/>
						</td>
					</tr>
				</tbody>      
			</table>
		</div>
		<div>
			<g:actionSubmit class="pushbutton" value="Update" action="updateStudentInfo" />
			<g:actionSubmit class="pushbutton" value="Delete"  action="deleteStudentInfo"/>
		</div>
		<center>
	</g:form>
</div>