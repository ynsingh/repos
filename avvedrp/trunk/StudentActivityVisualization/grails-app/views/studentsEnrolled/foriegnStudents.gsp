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
<g:form controller="studentsEnrolled" >
<center>
		<div class="dialog">
			<table >
			<br /><br /><h3><g:message code="Foriegn Students Enrolled"/></h3> <br />
				<tbody>
				   <tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
				   <tr class="prop">
				        <input type="hidden" name="InstId" value="${session.InstId}" />
						
						<td valign="top" class="name">
							<label for="name" ><g:message code="Faculty"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${facultyInstanceList}" id="faculty" optionKey="id" name="faculty" onchange="${remoteFunction(controller:'studentsEnrolled',action:'departmentNameList',update:'listDepartmentName',params:'\'departmentNameList=\'+this.value')};" noSelection="['null':'-Select-']" value="${facultyInstance?.id}"></g:select>
						 </td>
						 <td valign="top" class="name">
							<label for="name" ><g:message code="Department"/>:</label>
						</td>
						<td valign="top"  class="value">
							<div id="listDepartmentName">
							<g:select from= "${departmentInstanceList}" id="department" optionKey="id" name="department" onchange="${remoteFunction(controller:'studentsEnrolled',action:'programmeNameList',update:'listProgrammeName',params:'\'programmeNameList=\'+this.value')};" noSelection="['null':'-Select-']" value="${departmentInstance?.id}"></g:select>
						    </div>
						</td>
						 <td valign="top" class="name">
							<label for="name" ><g:message code="Select Programme"/>:</label>
						</td>
					    <td valign="top"  class="value">
							<div id="listProgrammeName">
							<g:select from= "${programmeDetailsInstanceList}" id="programmeDetails" optionKey="id" name="programmeDetails"  noSelection="['null':'-Select-']" value="${programmeDetailsInstance?.id}"></g:select>
						    </div>
						</td>
					</tr>
					<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
					<div id="foriegnStudentsDetails">
					<tr class="prop">
					   
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Country Name"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'fsCountryName','errors')}">
							<input type="text" size="20" id="fsCountryName" name="fsCountryName" value="${programmeDetailsInstance?.fsCountryName}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Country Code"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'fsCountryCode','errors')}">
							<input type="text" size="20" id="fsCountryCode" name="fsCountryCode" value="${programmeDetailsInstance?.fsCountryCode}"/>
						</td>
				   </tr>
				   <tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
				   <tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Total Strength"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'totalFs','errors')}">
							<input type="text" size="20" id="totalFs" name="totalFs" value="${programmeDetailsInstance?.totalFs}"/>
						</td>
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Female Strength"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'femaleFs','errors')}">
							<input type="text" size="20" id="femaleFs" name="femaleFs" value="${programmeDetailsInstance?.femaleFs}"/>
						</td>
					</tr>
					</div>
					<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
					</tbody>      
			</table>
		</div>
		<div>
			<g:actionSubmit class="pushbutton" value="Save" action="saveForgnStudents" />
			<input class="pushbutton" type='reset' value='Reset' />
		</div>
		</center>
	</g:form>
</div>
