<div id="head">
<meta name="layout" content="main" />
</div>
<div class="body">
<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${staffDetailsInstance}">
		<div class="errors">
			<g:renderErrors bean="${staffDetailsInstance}" as="list" />
		</div>
	</g:hasErrors>
	<g:form controller="staffDetails" method="post">
	<center>
	    <input type="hidden" name="id" value="${staffDetailsInstance?.id}" />
	    <input type="hidden" name="version" value="${staffDetailsInstance?.version}" />
		<div class="dialog">
			<table >
			<br /><br /><h3><g:message code="Edit Teaching Staff Details"/></h3> <br />
				<tbody>
				 <tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Select your Institution"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${institutionDetailsInstanceList}" id="institutionDetails" optionKey="id" name="institutionDetails" onchange="${remoteFunction(controller:'staffDetails',action:'facultyNameList',update:'listFacultyName',params:'\'facultyNameList=\'+this.value')};"  noSelection="['null':'-Select-']" value="${institutionDetailsInstance?.id}"></g:select>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Faculty"/>:</label>
						</td>
						<td valign="top"  class="value">
							<div id="listFacultyName">
							<g:select from= "${facultyInstanceList}" id="faculty" optionKey="id" name="faculty" onchange="${remoteFunction(controller:'staffDetails',action:'departmentNameList',update:'listDepartmentName',params:'\'departmentNameList=\'+this.value')};" noSelection="['null':'-Select-']" value="${facultyInstance?.id}"></g:select>
						    </div>
						 </td>
						 <td valign="top" class="name">
							<label for="name" ><g:message code="Department"/>:</label>
						</td>
						<td valign="top"  class="value">
							<div id="listDepartmentName">
							<g:select from= "${departmentInstanceList}" id="department" optionKey="deptName" name="department"  noSelection="['null':'-Select-']" value="${staffDetailsInstance?.department}"></g:select>
						    </div>
						</td>
					</tr>
					<tr class="prop">
					<td valign="top" class="name">
							<label for="name" ><g:message code="Designation"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${designationList}" id="designation" optionKey="id" name="designation" noSelection="['null':'-Select-']" value="${designationInstance?.id}"></g:select>
						</td>
					     <td valign="top" class="name">
							<label for="name" ><g:message code="Grade Pay"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:staffDetailsInstance,field:'gradePay','errors')}">
							<input type="text" size="8" id="gradePay" name="gradePay" value="${fieldValue(bean:staffDetailsInstance,field:'gradePay')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Sanctioned Stength"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:staffDetailsInstance,field:'sanctionedStrength','errors')}">
							<input type="text" size="8" id="sanctionedStrength" name="sanctionedStrength" value="${fieldValue(bean:staffDetailsInstance,field:'sanctionedStrength')}"/>
						</td>
					</tr>
					<tr class="prop">
					   <td valign="top" class="name">
							<label for="name" ><g:message code="Selection Mode"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${selectionModeList}" id="selectionMode" optionKey="id" name="selectionMode"   noSelection="['null':'-Select-']" value="${selectionModeInstance?.id}"></g:select>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Category"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${categoryList}" id="category" optionKey="id" name="category"   noSelection="['null':'-Select-']" value="${categoryInstance?.id}"></g:select>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Number of posts reserved for PWD"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:staffDetailsInstance,field:'pwdReservation','errors')}">
							<input type="text" size="8" id="pwdReservation" name="pwdReservation" value="${fieldValue(bean:staffDetailsInstance,field:'pwdReservation')}"/>
						</td>
					 </tr>
					<tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Total Strength"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:staffDetailsInstance,field:'tsOrNtsTotal','errors')}">
							<input type="text" size="8" id="tsOrNtsTotal" name="tsOrNtsTotal" value="${fieldValue(bean:staffDetailsInstance,field:'tsOrNtsTotal')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Female Strength"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:staffDetailsInstance,field:'tsOrNtsFemale','errors')}">
							<input type="text" size="8" id="tsOrNtsFemale" name="tsOrNtsFemale" value="${fieldValue(bean:staffDetailsInstance,field:'tsOrNtsFemale')}"/>
						</td>
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Total Strength of PWD Teachers"/>:</label>
						</td>
					    <td valign="top"  class="value ${hasErrors(bean:staffDetailsInstance,field:'tsOrNtsPwdTotal','errors')}">
							<input type="text" size="8" id="tsOrNtsPwdTotal" name="tsOrNtsPwdTotal" value="${fieldValue(bean:staffDetailsInstance,field:'tsOrNtsPwdTotal')}"/>
						</td>
					 </tr>
					<tr class="prop">
					   <td valign="top" class="name">
							<label for="name" ><g:message code="Female Strength of PWD Teachers"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:staffDetailsInstance,field:'tsOrNtsPwdFemale','errors')}">
							<input type="text" size="8" id="tsOrNtsPwdFemale" name="tsOrNtsPwdFemale" value="${fieldValue(bean:staffDetailsInstance,field:'tsOrNtsPwdFemale')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Total Strength of Muslim Teachers"/>:</label>
						</td>
					    <td valign="top"  class="value ${hasErrors(bean:staffDetailsInstance,field:'tsOrNtsMusTotal','errors')}">
							<input type="text" size="8" id="tsOrNtsMusTotal" name="tsOrNtsMusTotal" value="${fieldValue(bean:staffDetailsInstance,field:'tsOrNtsMusTotal')}"/>
						</td>
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Female Strength of Muslim Teachers"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:staffDetailsInstance,field:'tsOrNtsMusFemale','errors')}">
							<input type="text" size="8" id="tsOrNtsMusFemale" name="tsOrNtsMusFemale" value="${fieldValue(bean:staffDetailsInstance,field:'tsOrNtsMusFemale')}"/>
						</td>
					 </tr>
					 <tr class="prop">
					 <td valign="top" class="name">
							<label for="name" ><g:message code="Total Strength of Other Minorities Teachers"/>:</label>
						</td>
					    <td valign="top"  class="value ${hasErrors(bean:staffDetailsInstance,field:'tsOrNtsOthMinTotal','errors')}">
							<input type="text" size="8" id="tsOrNtsOthMinTotal" name="tsOrNtsOthMinTotal" value="${fieldValue(bean:staffDetailsInstance,field:'tsOrNtsOthMinTotal')}"/>
						</td>
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Female Strength of Other Minorities Teachers"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:staffDetailsInstance,field:'tsOrNtsOthMinFemale','errors')}">
							<input type="text" size="8" id="tsOrNtsOthMinFemale" name="tsOrNtsOthMinFemale" value="${fieldValue(bean:staffDetailsInstance,field:'tsOrNtsOthMinFemale')}"/>
						</td>
					</tr>
						<input type="hidden" id="institutionDetails.id" name="institutionDetails.id" value="${fieldValue(bean:institutionDetailsInstance,field:'id')}"/>
				</tbody>      
			</table>
		</div>
		<div>
			<g:actionSubmit class="pushbutton" value="Update" action="updateTeachingStaff" />
			<g:actionSubmit class="pushbutton" value="Delete"  action="deleteTeachingStaff"/>
		</div>
		</center>
	</g:form>
</div>