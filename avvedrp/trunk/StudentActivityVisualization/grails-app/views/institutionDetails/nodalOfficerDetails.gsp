<div id="head">
		<meta name="layout" content="main" />
</div>
<div class="body">
		<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${nodalOfficerDetailsInstance}">
				<div class="errors">
				        <g:renderErrors bean="${nodalOfficerDetailsInstance}" as="list" />
				</div>
		</g:hasErrors>
		<g:form method="post"  >
				<center>
						<div class="dialog">
								<br /><br /><h3><g:message code="Nodal Officer Details"/></h3><br />
								<table >
										<tbody>
										        <input type="hidden" name="InstId" value="${session.InstId}" />
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name" ><g:message code="Nodal Officer Name"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:nodalOfficerDetailsInstance,field:'name','errors')}">
																<input type="text" size="25" id="name" name="name" value="${fieldValue(bean:nodalOfficerDetailsInstance,field:'name')}"/>
														</td>
												</tr>
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name" ><g:message code="Designation"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:nodalOfficerDetailsInstance,field:'designation','errors')}">
																<input type="text" size="25" id="designation" name="designation" value="${fieldValue(bean:nodalOfficerDetailsInstance,field:'designation')}"/>
														</td>
												</tr>
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name" ><g:message code="Contact Number"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:nodalOfficerDetailsInstance,field:'contactNumber','errors')}">
																<input type="text" size="25" id="contactNumber" name="contactNumber" value="${fieldValue(bean:nodalOfficerDetailsInstance,field:'contactNumber')}"/>
														</td>
												</tr>
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name" ><g:message code="Email Id"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:nodalOfficerDetailsInstance,field:'emailId','errors')}">
																<input type="text" size="25" id="emailId" name="emailId" value="${fieldValue(bean:nodalOfficerDetailsInstance,field:'emailId')}"/>
														</td>
												</tr>
										<input type="hidden" id="institutionDetails.id" name="institutionDetails.id" value="${fieldValue(bean:institutionDetailsInstance,field:'id')}"/>
										</tbody>      
								</table>
						</div>
						<div>
								<g:actionSubmit class="pushbutton" value="Save" action="createNodalOfficer" />
								<input class="pushbutton" type='reset' value='Reset' />
						</div>
				</center>
		</g:form>
</div>
<div class="body">
		<div class="list">
				<center>
						<fieldset style="width:80%; border-color:transparent; padding:0;">
						<table frame="box">
								<thead>
												<tr>
														<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; color:#FFF0F5; "/>&nbsp
														<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
														<g:sortableColumn property="name" title="${message(code: 'Nodal Officer Name')}" style="font-size:12px;"/>
														<g:sortableColumn property="designation" title="${message(code: 'Designation')}" style="font-size:12px;"/>
														<g:sortableColumn property="contactNumber" title="${message(code: 'ContactNumber')}" style="font-size:12px;"/>
														<g:sortableColumn property="emailId" title="${message(code: 'EmailId')}" style="font-size:12px;" />
														<th><g:message code="Edit" style="font-size:12px;"/></th>
												</tr>
								</thead>
								
								<tbody>
										<g:each in="${nodalOfficerDetailsInstanceList}" status="i" var="nodalOfficerDetailsInstance">
												<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
												
														<td class="listing">${i+1}</td>
														
														<td class="listing">${fieldValue(bean:nodalOfficerDetailsInstance, field:'institutionDetails.institutionName')}</td>
														
														<td class="listing">${fieldValue(bean:nodalOfficerDetailsInstance, field:'name')}</td>
														
														<td class="listing">${fieldValue(bean:nodalOfficerDetailsInstance, field:'designation')}</td>
														
														<td class="listing">${fieldValue(bean:nodalOfficerDetailsInstance, field:'contactNumber')}</td>
														
														<td class="listing">${fieldValue(bean:nodalOfficerDetailsInstance, field:'emailId')}</td>
														
														<td class="listing"><g:link action="editNodalOfficer" id="${nodalOfficerDetailsInstance.id}"><g:message code="Edit"/></g:link></td>
												</tr>
										</g:each>
								</tbody>
						</table>
						</fieldset>
				</center>
		</div>
</div>
