<div id="head">
		<meta name="layout" content="main" />
</div>
<div id="body">
		<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${staffDetailsInstance}">
				<div class="errors">
						<g:renderErrors bean="${staffDetailsInstance}" as="list" />
				</div>
		</g:hasErrors>
		<g:form controller="staffDetails" >
				<center>
						<div class="dialog">
								<table >
										<br /><br /><h3><g:message code="Teaching Staff Details"/></h3> <br />
										<tbody>
												<tr class="prop">
														<input type="hidden" name="InstId" value="${session.InstId}" />
														
														<td valign="top" class="name">
																<label for="name" ><g:message code="Faculty"/>:</label>
														</td>
														<td valign="top"  class="value">
																		<g:select from= "${facultyInstanceList}" id="faculty" optionKey="id" name="faculty" onchange="${remoteFunction(controller:'staffDetails',action:'departmentNameList',update:'listDepartmentName',params:'\'departmentNameList=\'+this.value')};" noSelection="['null':'-Select-']" value="${facultyInstance?.id}"></g:select>
														</td>
														
														<td valign="top" class="name">
																<label for="name" ><g:message code="Department"/>:</label>
														</td>
														<td valign="top"  class="value">
																<div id="listDepartmentName">
																		<g:select from= "${departmentInstanceList}" id="department" optionKey="deptName" name="department" onchange="${remoteFunction(controller:'staffDetails',action:'staffDetailsList',update:'listStaffDetails',params:'\'staffDetailsList=\'+this.value')};" onFocus="${remoteFunction(controller:'staffDetails',action:'staffDetailsList',update:'listStaffDetails',params:'\'staffDetailsList=\'+this.value')};" noSelection="['null':'-Select-']" value="${staffDetailsInstance?.department}"></g:select>
																</div>
														</td>
												</tr>
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name" ><g:message code="Designation"/>:</label>
														</td>
														<td valign="top"  class="value">
																<g:select from= "${designationList}" id="designation" optionKey="id" name="designation" noSelection="['null':'-Select-']" value="${staffDetailsInstance?.designation}"></g:select>
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
																<g:select from= "${selectionModeList}" id="selectionMode" optionKey="id" name="selectionMode"   noSelection="['null':'-Select-']" value="${staffDetailsInstance?.selectionMode}"></g:select>
														</td>
														
														<td valign="top" class="name">
																<label for="name" ><g:message code="Category"/>:</label>
														</td>
														<td valign="top"  class="value">
																<g:select from= "${categoryList}" id="category" optionKey="id" name="category"   noSelection="['null':'-Select-']" value="${staffDetailsInstance?.category}" ></g:select>
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
										</tbody>
								</table>
						</div>
						<div>
								<g:actionSubmit class="pushbutton" value="Save" action="saveTeachingStaff" />
								<input class="pushbutton" type='reset' value='Reset' />
						</div>
				</center>
		</g:form>
</div>		
<div class="body">
		<div class="list">
		<fieldset style="width:150%; border-color:transparent; padding:0;">
				<div id="listStaffDetails">
						<table frame="box">
								<thead>
										<tr>
												<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; "/>&nbsp
												
												<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="faculty.id" title="${message(code: 'Faculty')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="department" title="${message(code: 'Department')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="designation" title="${message(code: 'Designation')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="gradePay" title="${message(code: 'Grade Pay')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="sanctionedStrength" title="${message(code: 'Sanctioned Stength')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="selectionMode" title="${message(code: 'Selection Mode')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="category" title="${message(code: 'Category')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="pwdReservation" title="${message(code: 'PWD Reservation')}" style="font-size:12px;"/>
												
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
										
										<g:each in="${staffDetailsInstanceList}" status="i" var="staffDetailsInstance">
												<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
												
														<td class="listing">${i+1}</td>
														
														<td class="listing">${fieldValue(bean:institutionDetailsInstance, field:'institutionName')}</td>
														
														<td class="listing">${fieldValue(bean:facultyInstance, field:'facultyName')}</td>
														    
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'department')}</td>
														    
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'designation')}</td>
														     
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'gradePay')}</td>
														 
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'sanctionedStrength')}</td>
														 
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'selectionMode')}</td>
														 
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'category')}</td>
														 
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'pwdReservation')}</td>
														 
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsTotal')}</td>
														 
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsFemale')}</td>
														 
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsPwdTotal')}</td>
														 
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsPwdFemale')}</td>
														 
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsMusTotal')}</td>
														 
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsMusFemale')}</td>
														 
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsOthMinTotal')}</td>
														 
														<td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsOthMinFemale')}</td>
														 
														<td class="listing"><g:link action="editTeachingStaff" id="${staffDetailsInstance.id}"><g:message code="Edit"/></g:link></td>
												                    	
												</tr>
										</g:each>
								                	
								</tbody>
						</table>
				</div>
				</fieldset>
		</div>
</div>