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
		<g:form controller="programmeDetails" >
				<center>
						<div class="dialog">
								<table >
										<br /><br /><h3><g:message code="Programme Details"/></h3> <br />
										<tbody>
												<tr class="prop">
														<input type="hidden" name="InstId" value="${session.InstId}" />
														
														<td valign="top" class="name">
															<label for="name" ><g:message code="Faculty"/>:</label>
														</td>
														<td valign="top"  class="value">
																<g:select from= "${facultyInstanceList}" id="faculty" optionKey="id" name="faculty" onchange="${remoteFunction(controller:'programmeDetails',action:'departmentNameList',update:'listDepartmentName',params:'\'departmentNameList=\'+this.value')};" noSelection="['null':'-Select-']" value="${facultyInstance?.id}"></g:select>
														</td>
														
														<td valign="top" class="name">
															<label for="name" ><g:message code="Department"/>:</label>
														</td>
														<td valign="top"  class="value">
															<div id="listDepartmentName">
																<g:select from= "${departmentInstanceList}" id="department" optionKey="id" name="department" onchange="${remoteFunction(controller:'programmeDetails',action:'programmeList',update:'listProgramme',params:'\'programmeList=\'+this.value')};" noSelection="['null':'-Select-']" value="${programmeDetailsInstance?.department?.id}"></g:select>
															</div>
														</td>
												</tr>
												<tr class="prop">
														<td valign="top" class="name">
															<label for="name" ><g:message code="Select Mode"/>:</label>
														</td>
														<td valign="top"  class="value">
															<g:select from= "${modeList}" id="mode" optionKey="id" name="mode"   noSelection="['null':'-Select-']" value="${programmeDetailsInstance?.mode}"></g:select>
														</td>
														
														<td valign="top" class="name">
															<label for="name" ><g:message code="Select Level"/>:</label>
														</td>
														<td valign="top"  class="value">
															<g:select from= "${levelList}" id="level" optionKey="id" name="level"  noSelection="['null':'-Select-']" value="${programmeDetailsInstance?.level}"></g:select>
														</td>
												</tr>
												<tr class="prop">
														<td valign="top" class="name">
															<label for="name" ><g:message code="Name of the Programme"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'programmeName','errors')}">
															<input type="text" size="22" id="programmeName" name="programmeName" value="${fieldValue(bean:programmeDetailsInstance,field:'programmeName')}"/>
														</td>
														
														<td valign="top" class="name">
															<label for="name" ><g:message code="Programme Code"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'programmeCode','errors')}">
															<input type="text" size="22" id="programmeCode" name="programmeCode" value="${fieldValue(bean:programmeDetailsInstance,field:'programmeCode')}"/>
														</td>
												</tr>
												<tr class="prop">
												        
														<td valign="top" class="name">
															<label for="name" ><g:message code="Name of Discipline"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'disciplineName','errors')}">
															<input type="text" size="22" id="disciplineName" name="disciplineName" value="${fieldValue(bean:programmeDetailsInstance,field:'disciplineName')}"/>
														</td>
														
														<td valign="top" class="name">
															<label for="name" ><g:message code="Discipline Code"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'disciplineCode','errors')}">
															<input type="text" size="22" id="disciplineCode" name="disciplineCode" value="${fieldValue(bean:programmeDetailsInstance,field:'disciplineCode')}"/>
														</td>
												</tr>
												<tr class="prop">
														<td valign="top" class="name">
															<label for="name" ><g:message code="Name of Broad Discipline, if any"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'broadDisciplineName','errors')}">
															<input type="text" size="22" id="broadDisciplineName" name="broadDisciplineName" value="${fieldValue(bean:programmeDetailsInstance,field:'broadDisciplineName')}"/>
														</td>
														
														<td valign="top" class="name">
															<label for="name" ><g:message code="Broad Discipline Code"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'broadDIsciplineCode','errors')}">
															<input type="text" size="22" id="broadDIsciplineCode" name="broadDIsciplineCode" value="${fieldValue(bean:programmeDetailsInstance,field:'broadDIsciplineCode')}"/>
														</td>
												</tr>
												<tr class="prop">
														<td valign="top" class="name">
															<label for="name" ><g:message code="Intake Capacity"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'intakeCapacity','errors')}">
															<input type="text" size="22" id="intakeCapacity" name="intakeCapacity" value="${fieldValue(bean:programmeDetailsInstance,field:'intakeCapacity')}"/>
														</td>
														
														<td valign="top" class="name">
															<label for="name" ><g:message code="Number Of Applicants"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'numberOfApplicants','errors')}">
															<input type="text" size="22" id="numberOfApplicants" name="numberOfApplicants" value="${fieldValue(bean:programmeDetailsInstance,field:'numberOfApplicants')}"/>
														</td>
												</tr>
												<tr class="prop">
														
														<td valign="top" class="name">
															<label for="name" ><g:message code="Duration Of Programme"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'programmeDuration','errors')}">
															<input type="text" size="22" id="programmeDuration" name="programmeDuration" value="${fieldValue(bean:programmeDetailsInstance,field:'programmeDuration')}"/>
														</td>
														
														<td valign="top" class="name">
															<label for="name" ><g:message code="Type of Programme"/>:</label>
														</td>
														<td valign="top"  class="value">
															<g:select from= "${typeList}" id="programmeType" optionKey="id" name="programmeType"   noSelection="['null':'-Select-']" value="${programmeDetailsInstance?.programmeType}"></g:select>
														</td>
												</tr>
												<tr class="prop">
														<td valign="top" class="name">
															<label for="name" ><g:message code="Examination System"/>:</label>
														</td>
														<td valign="top"  class="value">
															<g:select from= "${examSystemList}" id="examinationSystem" optionKey="id" name="examinationSystem"   noSelection="['null':'-Select-']" value="${programmeDetailsInstance?.examinationSystem}"></g:select>
														</td>
														
														<td valign="top" class="name">
															<label for="name" ><g:message code="University, through which approved"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'university','errors')}">
															<input type="text" size="22" id="university" name="university" value="${fieldValue(bean:programmeDetailsInstance,field:'university')}"/>
														</td>
												</tr>
										</tbody>      
								</table>
						</div>
						<div>
								<g:actionSubmit class="pushbutton" value="Save" action="saveProgramme" />
								<input class="pushbutton" type='reset' value='Reset' />
						</div>
				</center>
		</g:form>
</div>
<div class="body">
		<div class="list">
		        <fieldset style="width:200%; border-color:transparent; padding:0;">
				<div id="listProgramme">
						<table frame="box">
								<thead>
										<tr  style="font-size:12px; ">
												<g:sortableColumn property="id" title="${message(code: 'SINo')}"/>&nbsp
												
												<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" />
												
												<g:sortableColumn property="faculty.id" title="${message(code: 'Faculty')}"/>
												
												<g:sortableColumn property="department.id" title="${message(code: 'Department')}" />
												
												<g:sortableColumn property="mode" title="${message(code: 'Mode')}"/>
												
												<g:sortableColumn property="level" title="${message(code: 'Level')}"/>
												
												<g:sortableColumn property="programmeName" title="${message(code: 'Programme Name')}" />
												
												<g:sortableColumn property="programmeCode" title="${message(code: 'Programme Code')}" />
												
												<g:sortableColumn property="disciplineName" title="${message(code: 'Discipline Name')}" />
												
												<g:sortableColumn property="disciplineCode" title="${message(code: 'Discipline Code')}" />
												
												<g:sortableColumn property="broadDisciplineName" title="${message(code: 'Broad Discipline Name')}" />
												
												<g:sortableColumn property="broadDIsciplineCode" title="${message(code: 'Broad Discipline Code')}" />
												
												<g:sortableColumn property="intakeCapacity" title="${message(code: 'Intake Capacity')}" />
												
												<g:sortableColumn property="numberOfApplicants" title="${message(code: 'Number of Applicants')}" />
												
												<g:sortableColumn property="programmeDuration" title="${message(code: 'Duration')}" />
												
												<g:sortableColumn property="programmeType" title="${message(code: 'Type')}" />
												
												<g:sortableColumn property="examinationSystem" title="${message(code: 'Examination System')}"/>
												
												<g:sortableColumn property="university" title="${message(code: 'University')}" />
												
												
												<th><g:message code="Edit" /></th>
												   
										</tr>
								</thead>
								    
								<tbody>
										<g:each in="${programmeDetailsInstanceList}" status="i" var="programmeDetailsInstance">
												<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
												
														<td class="listing">${i+1}</td>
														
														<td class="listing">${fieldValue(bean:institutionDetailsInstance, field:'institutionName')}</td>
														
														<td class="listing">${fieldValue(bean:facultyInstance, field:'facultyName')}</td>
														
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'department.deptName')}</td>
														
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'mode')}</td>
														 
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'level')}</td>
														 
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'programmeName')}</td>
														 
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'programmeCode')}</td>
														 
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'disciplineName')}</td>
														 
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'disciplineCode')}</td>
														 
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'broadDisciplineName')}</td>
														 
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'broadDIsciplineCode')}</td>
														 
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'intakeCapacity')}</td>
														 
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'numberOfApplicants')}</td>
														 
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'programmeDuration')}</td>
														 
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'programmeType')}</td>
														 
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'examinationSystem')}</td>
														 
														<td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'university')}</td>
														 
														<td class="listing"><g:link action="editProgramme" id="${programmeDetailsInstance.id}"><g:message code="Edit"/></g:link></td>
														
												</tr>
										</g:each>
								</tbody>
						</table>
				</div>
				</fieldset>
		</div>
</div>
