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
		<g:form controller="studentsEnrolled" >
				<center>
						<div class="dialog">
								<table>
										<br /><br /><h3><g:message code="Students Enrolled"/></h3> <br />
										<tbody>
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
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name" ><g:message code="Select Programme"/>:</label>
														</td>
														<td valign="top"  class="value">
																<div id="listProgrammeName">
																		<g:select from= "${programmeDetailsInstanceList}" id="programmeDetails" optionKey="id" name="programmeDetails" onchange="${remoteFunction(controller:'studentsEnrolled',action:'studentsEnrolledList',update:'listStudentsEnrolled',params:'\'studentsEnrolledList=\'+this.value')};" noSelection="['null':'-Select-']" value="${studentsEnrolledInstance?.programmeDetails}"></g:select>
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
																<g:select from= "${categoryList}" id="category" optionKey="id" name="category"   noSelection="['null':'-Select-']" value="${studentsEnrolledInstance?.category}"></g:select>
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
												</tr>
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
								<g:actionSubmit class="pushbutton" value="Save" action="saveStudentInfo" />
								<input class="pushbutton" type='reset' value='Reset' />
						</div>
				</center>
		</g:form>
</div>
<div class="body">
		<div class="list">
		
				<div id="listStudentsEnrolled">
						<table frame="box">
								<thead>
										<tr>
												<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; "/>&nbsp
												
												<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="faculty.id" title="${message(code: 'Faculty')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="department.id" title="${message(code: 'Department')}" style="font-size:12px;"/>
													
												<g:sortableColumn property="programmeDetails.id" title="${message(code: 'Programme')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="year" title="${message(code: 'Year')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="category" title="${message(code: 'Category')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="strength" title="${message(code: 'Strength')}" style="font-size:12px; text-align: center;" colspan="2"/>
												
												<g:sortableColumn property="pwdStrength" title="${message(code: 'PWD Strength')}" style="font-size:12px; text-align: center;" colspan="2"/>
												
												<g:sortableColumn property="muslimStrength" title="${message(code: 'Muslims Strength')}" style="font-size:12px; text-align: center;" colspan="2"/>
												
												<g:sortableColumn property="minoritiesStrength" title="${message(code: 'Other MInorities Strength')}" style="font-size:12px; text-align: center;" colspan="2"/>
												
												<th><g:message code="Edit" style="font-size:12px;"/></th>
										</tr>
												   
										<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td style=" text-align: center;">total</td>
												<td style=" text-align: center;">female</td>
												<td style=" text-align: center;">total</td>
												<td style=" text-align: center;">female</td>
												<td style=" text-align: center;">total</td>
												<td style=" text-align: center;">female</td>
												<td style=" text-align: center;">total</td>
												<td style=" text-align: center;">female</td>
												<td>&nbsp;</td>
										</tr>
										      
										
								</thead>
								
								<tbody>
								
										<g:each in="${studentsEnrolledInstanceList}" status="i" var="studentsEnrolledInstance">
												<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
												            
														<td class="listing">${i+1}</td>
														
														<td class="listing">${fieldValue(bean:institutionDetailsInstance, field:'institutionName')}</td>
														
														<td class="listing">${fieldValue(bean:facultyInstance, field:'facultyName')}</td>
														
														<td class="listing">${fieldValue(bean:departmentInstance, field:'deptName')}</td>
														
														<td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'programmeDetails.programmeName')}</td>
														 
														<td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'year')}</td>
														 
														<td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'category')}</td>
														 
														<td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'totalStudents')}</td>
														 
														<td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'femaleStudents')}</td>
														 
														<td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'totalPwdStudents')}</td>
														 
														<td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'femalePwdStudents')}</td>
														 
														<td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'totalMuslimStudents')}</td>
														 
														<td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'femaleMuslimStudents')}</td>
														 
														<td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'totalOthMinStudents')}</td>
														 
														<td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'femaleOthMinStudents')}</td>
														 
														<td class="listing"><g:link action="editStudentInfo" id="${studentsEnrolledInstance.id}"><g:message code="Edit"/></g:link></td>
												            	
												</tr>
										</g:each>
										        	
								</tbody>
						</table>
				</div>
			
		</div>
</div>
