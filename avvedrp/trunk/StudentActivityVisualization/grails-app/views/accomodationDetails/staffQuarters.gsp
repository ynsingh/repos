<div id="head">
		<meta name="layout" content="main" />
</div>
<div id="body">
		<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${staffQuartersInstance}">
				<div class="errors">
						<g:renderErrors bean="${staffQuartersInstance}" as="list" />
				</div>
		</g:hasErrors>
		
		<g:form controller="accomodationDetails" >
		
				<center>
						<div class="dialog">
								<table >
										<br /><br /><h3><g:message code="Staff Quarters Availabilty"/></h3> <br />
										<tbody>
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
												<tr class="prop">
														<input type="hidden" name="InstId" value="${session.InstId}" />
														
														<td valign="top" class="name">
																<label for="name" ><g:message code="Category"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:staffQuartersInstance,field:'categoryType','errors')}">
																<g:select from= "${generalLookupList}" id="categoryType" optionKey="id" name="categoryType" noSelection="['null':'-Select-']" value="${staffQuartersInstance?.categoryType}"></g:select>
														</td>
														
														<td valign="top" class="name">
																<label for="name" ><g:message code="Number Of Quarters"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:staffQuartersInstance,field:'numberOfQuarters','errors')}">
																<input type="text" size="25" id="numberOfQuarters" name="numberOfQuarters" value="${fieldValue(bean:staffQuartersInstance,field:'numberOfQuarters')}"/>
														</td>
												</tr>
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
										</tbody>      
								</table>
						</div>
						<div>
								<g:actionSubmit class="pushbutton" value="Save" action="saveStaff" />
								<input class="pushbutton" type='reset' value='Reset' />
						</div>
				</center>
		</g:form>
</div>
	
<div class="body">
		<div class="list">
		
				<center>
				<fieldset style="width:40%; border-color:transparent; padding:0;">
								<table frame="box">
								<thead>
										<tr>
												<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; "/>&nbsp
												
												<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="categoryType" title="${message(code: 'Category')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="numberOfQuarters" title="${message(code: 'Number Of Quarters')}" style="font-size:12px;"/>
												
												
												<th><g:message code="Edit" style="font-size:12px;"/></th>
												   
										</tr>
								</thead>
								    
								<tbody>
									
										<g:each in="${staffQuartersInstanceList}" status="i" var="staffQuartersInstance">
												<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
												                
														<td class="listing">${i+1}</td>
														    
														<td class="listing">${fieldValue(bean:staffQuartersInstance, field:'institutionDetails.institutionName')}</td>
														    
														<td class="listing">${fieldValue(bean:staffQuartersInstance, field:'categoryType')}</td>
														    
														<td class="listing">${fieldValue(bean:staffQuartersInstance, field:'numberOfQuarters')}</td>
														 
														<td class="listing"><g:link action="editStaffQuarters" id="${staffQuartersInstance.id}"><g:message code="Edit"/></g:link></td>
												                	
												</tr>
										</g:each>
								            	
								</tbody>
						</table>
				</fieldset>
				</center>
				
		</div>
		    

</div>
