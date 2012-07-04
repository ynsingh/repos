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
		<g:form controller="programmeDetails" >
				<center>
						<div class="dialog">
								<br /><br /> <h3><g:message code="Department Details"/></h3><br />
								<table >
										<tbody>
												<tr class="prop">
														<input type="hidden" name="InstId" value="${session.InstId}" />
														
														<td valign="top" class="name">
																<label for="name" ><g:message code="Faculty"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:departmentInstance,field:'faculty','errors')}">
																<div id="listFacultyName">
																		<g:select from= "${facultyInstanceList}" id="faculty" optionKey="id" name="faculty" onchange="${remoteFunction(controller:'programmeDetails',action:'departmentList',update:'listDepartment',params:'\'departmentList=\'+this.value')};" noSelection="['null':'-Select-']" value="${departmentInstance?.faculty?.id}"></g:select>
																</div>
														</td>
												        <td valign="top" class="name">
																<label for="name" ><g:message code="Name of Department"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:departmentInstance,field:'deptName','errors')}">
																<input type="text" size="22" id="deptName" name="deptName" value="${fieldValue(bean:departmentInstance,field:'deptName')}"/>
														</td>
														
														<td valign="top" class="name">
																<label for="name" ><g:message code="Department Code"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:departmentInstance,field:'deptCode','errors')}">
																<input type="text" size="22" id="deptCode" name="deptCode" value="${fieldValue(bean:departmentInstance,field:'deptCode')}"/>
														</td>
												</tr>
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
										</tbody>      
								</table>
						</div>
						<div>
								<g:actionSubmit class="pushbutton" value="Save" action="saveDepartment" />
								<input class="pushbutton" type='reset' value='Reset' />
						</div>
				</center>
		</g:form>
</div>
	
<div class="body">
		<div class="list">
		<center>
				<fieldset style="width:60%; border-color:transparent; padding:0;">
				<div id="listDepartment">
						<table frame="box">
								<thead>
										<tr>
												<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; "/>&nbsp
												
												<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="faculty" title="${message(code: 'Faculty')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="deptName" title="${message(code: 'Name of Department')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="deptCode" title="${message(code: 'Department Code')}" style="font-size:12px;"/>
												
												
												<th><g:message code="Edit" style="font-size:12px;"/></th>
										   
										</tr>
								</thead>
								    
								<tbody>
								
										<g:each in="${departmentInstanceList}" status="i" var="departmentInstance">
												<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
												
														<td class="listing">${i+1}</td>
														    
														<td class="listing">${fieldValue(bean:institutionDetailsInstance, field:'institutionName')}</td>
														    
														<td class="listing">${fieldValue(bean:departmentInstance, field:'faculty.facultyName')}</td>
														    
														<td class="listing">${fieldValue(bean:departmentInstance, field:'deptName')}</td>
														    
														<td class="listing">${fieldValue(bean:departmentInstance, field:'deptCode')}</td>
														 
														<td class="listing"><g:link action="editDepartment" id="${departmentInstance.id}"><g:message code="Edit"/></g:link></td>
													
												</tr>
										</g:each>
								</tbody>
						</table>
				</div>
				</fieldset>
				</center>
		</div>
</div>
	
	
