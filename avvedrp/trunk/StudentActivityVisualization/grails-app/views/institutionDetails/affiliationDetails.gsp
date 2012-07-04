<div id="head">
		<meta name="layout" content="main" />
</div>
<div class="body">
		<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${affiliationDetailsInstance}">
				<div class="errors">
						<g:renderErrors bean="${affiliationDetailsInstance}" as="list" />
				</div>
		</g:hasErrors>
		<g:form  method="post" >
				<center>
						<div class="dialog">
								<br /><br /><h3><g:message code="Affiliation Details"/></h3><br />
								<table >
										<tbody>
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name" ><g:message code="University Name"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:affiliationDetailsInstance,field:'universityName','errors')}">
																<input type="text" size="25" id="universityName" name="universityName" value="${fieldValue(bean:affiliationDetailsInstance,field:'universityName')}"/>
														</td>
												</tr> 
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name"><g:message code="Code"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:affiliationDetailsInstance,field:'universityCode','errors')}">
																<input type="text" size="10" id="universityCode" name="universityCode" value="${fieldValue(bean:affiliationDetailsInstance,field:'universityCode')}"/>
														</td>
												</tr>
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name" ><g:message code="Year of Affiliation"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:affiliationDetailsInstance,field:'yearOfAffiliation','errors')}">
																<input type="number" size="15" id="yearOfAffiliation" name="yearOfAffiliation" value="${fieldValue(bean:affiliationDetailsInstance,field:'yearOfAffiliation')}"/>
														</td>
												</tr>
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
																<input type="hidden" name="InstId" value="${session.InstId}" />
										</tbody>      
								</table>
						</div>
						<div>
								<g:actionSubmit class="pushbutton" value="Save" action="saveAffiliationDetails" />
								<input class="pushbutton" type='reset' value='Reset' />
						</div>
				</center>
		</g:form>
</div>
<div class="body">
		<div class="list">
				<center>
				<fieldset style="width:55%; border-color:transparent; padding:0;">
						<table frame="box">
								<thead>
												<tr>
														<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; color:#FFF0F5; "/>&nbsp
														<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
														<g:sortableColumn property="universityName" title="${message(code: 'University Name')}" style="font-size:12px;"/>
														<g:sortableColumn property="universityCode" title="${message(code: 'Code')}" style="font-size:12px;"/>
														<g:sortableColumn property="yearOfAffiliation" title="${message(code: 'Year of Affiliation')}" style="font-size:12px;"/>
														<th><g:message code="Edit" style="font-size:12px;"/></th>
												</tr>
								</thead>
								
								<tbody>
										<g:each in="${affiliationDetailsInstanceList}" status="i" var="affiliationDetailsInstance">
												<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
												
														<td class="listing">${i+1}</td>
														
														<td class="listing">${fieldValue(bean:affiliationDetailsInstance, field:'institutionDetails.institutionName')}</td>
														
														<td class="listing">${fieldValue(bean:affiliationDetailsInstance, field:'universityName')}</td>
														
														<td class="listing">${fieldValue(bean:affiliationDetailsInstance, field:'universityCode')}</td>
														
														<td class="listing">${fieldValue(bean:affiliationDetailsInstance, field:'yearOfAffiliation')}</td>
														
														<td class="listing"><g:link action="editAffiliation" id="${affiliationDetailsInstance.id}"><g:message code="Edit"/></g:link></td>
												</tr>
										</g:each>
								</tbody>
						</table>
						</fieldset>
				</center>
		</div>
</div>
