<div id="head">
<meta name="layout" content="main" />
</div>
<div class="body">
<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${examResultsInstance}">
		<div class="errors">
			<g:renderErrors bean="${examResultsInstance}" as="list" />
		</div>
	</g:hasErrors>
<g:form controller="studentsEnrolled" >
<center>
		<div class="dialog">
			<table >
			<br /><br /><h3><g:message code="Examination Results"/></h3> <br />
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
					</tr>
					<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
					<tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Select Programme"/>:</label>
						</td>
					    <td valign="top"  class="value">
							<div id="listProgrammeName">
							<g:select from= "${programmeDetailsInstanceList}" id="programmeDetails" optionKey="id" name="programmeDetails" noSelection="['null':'-Select-']" value="${programmeDetailsInstance?.id}"></g:select>
						    </div>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Total Students Appeared"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:examResultsInstance,field:'totStudAppd','errors')}">
							<input type="text" size="20" id="totStudAppd" name="totStudAppd" value="${fieldValue(bean:examResultsInstance,field:'totStudAppd')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Female Students Appeared"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:examResultsInstance,field:'femStudAppd','errors')}">
							<input type="text" size="20" id="femStudAppd" name="femStudAppd" value="${fieldValue(bean:examResultsInstance,field:'femStudAppd')}"/>
						</td>
				   </tr>
				   <tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
				   <tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Total Students Passed"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:examResultsInstance,field:'totStudPassd','errors')}">
							<input type="text" size="20" id="totStudPassd" name="totStudPassd" value="${fieldValue(bean:examResultsInstance,field:'totStudPassd')}"/>
						</td>
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Female Students Passed"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:examResultsInstance,field:'femStudPassd','errors')}">
							<input type="text" size="20" id="femStudPassd" name="femStudPassd" value="${fieldValue(bean:examResultsInstance,field:'femStudPassd')}"/>
						</td>
				    </tr>
				    <tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
					</tbody>      
			</table>
		</div>
		<div>
			<g:actionSubmit class="pushbutton" value="Save" action="saveExamResults" />
			<input class="pushbutton" type='reset' value='Reset' />
		</div>
		</center>
	</g:form>
</div>
