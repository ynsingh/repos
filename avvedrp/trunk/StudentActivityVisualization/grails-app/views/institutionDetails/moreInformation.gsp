<div id="head">
		<meta name="layout" content="main" />
</div>
<div class="body">
		<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${institutionDetailsInstance}">
				<div class="errors">
						<g:renderErrors bean="${institutionDetailsInstance}" as="list" />
				</div>
		</g:hasErrors>
		<g:form method="post" >
				<center>
						<div class="dialog">
								<br /><br /><h3><g:message code="More Information"/></h3><br />
								<table > 
										<tbody>
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name" ><g:message code="Location"/>:</label>
																<label for="name" style="color:red;font-weight:bold">*</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'location','errors')}">
																<g:select name="location" id="location" from="${locationInstanceList}" optionKey="id"  noSelection="['null':'-Select-']"  value="${locationInstance?.id}"/>
														</td>
														
														<td valign="top" class="name">
																<label for="name" ><g:message code="Statutory Body through which recognized"/>:</label>
																<label for="name" style="color:red;font-weight:bold">*</label>

														</td>
														<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'statutoryBody','errors')}">
																<g:select from="${statutoryBodyInstanceList}" name="statutoryBody" optionKey="id" noSelection="['null':'-Select-']"  value="${statutoryBodyInstance?.id}"/>
														</td>
												</tr> 
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name" ><g:message code="Type of College/Institution"/>:</label>
																<label for="name" style="color:red;font-weight:bold">*</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'institutionType','errors')}">
																<g:select name="institutionType" id="institutionType" from="${institutionTypeInstanceList}" optionKey="id"  noSelection="['null':'-Select-']" value="${institutionTypeInstance?.id}"></g:select>
														</td>
														
														<td valign="top" class="name">
																<label for="name" ><g:message code="Management of College/Institution"/>:</label>
																<label for="name" style="color:red;font-weight:bold">*</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'management','errors')}">
																<g:select name="management" id="management" from="${managementInstanceList}" optionKey="id"  noSelection="['null':'-Select-']" value="${managementInstance?.id}"></g:select>                          
														</td>                               
												</tr>
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
												<tr class="prop">
												
														<td valign="top" class="name">
																<label for="name" ><g:message code="Whether the College/Institution is Autonomous?"/>:</label>
																<label for="name" style="color:red;font-weight:bold">*</label>
														</td>
														<g:if test="${session.status=='update'}">	
																<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'autonomous','errors')}" >
																		<g:if test="${institutionDetailsInstance.autonomous=='YES'}">
																				<g:radio name="autonomous" id="autonomous" value="YES" checked="checked"/><label for="autonomous" ><g:message code="YES"/></label>
																				<g:radio name="autonomous" id="autonomous" value="NO"/><label for="autonomous" ><g:message code="NO"/></label>
																		</g:if>
							                    						<g:elseif test="${institutionDetailsInstance.autonomous=='NO'}">
									                    						<g:radio name="autonomous" id="autonomous" value="YES"/><label for="autonomous" ><g:message code="YES"/></label>
																				<g:radio name="autonomous" id="autonomous" value="NO" checked="checked"/><label for="autonomous" ><g:message code="NO"/></label>
																		</g:elseif>
																		<g:else>
																				<g:radio name="autonomous" id="autonomous" value="YES"/><label for="autonomous" ><g:message code="YES"/></label>
																				<g:radio name="autonomous" id="autonomous" value="NO"/><label for="autonomous" ><g:message code="NO"/></label>
																		</g:else>
																</td>
														</g:if>
					                    				<g:else>
					                    						<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'autonomous','errors')}" >
																		<g:radio name="autonomous" id="autonomous" value="YES"/><label for="autonomous" ><g:message code="YES"/></label>
																		<g:radio name="autonomous" id="autonomous" value="NO"/><label for="autonomous" ><g:message code="NO"/></label>
																</td>
														</g:else>
														
														<td valign="top" class="name">
																<label for="name" ><g:message code="Specialization, if any"/>:</label>
																<label for="name" style="color:red;font-weight:bold">*</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'specialization','errors')}">
																<g:select name="specialization" id="specialization" from="${specializationInstanceList}" optionKey="id" noSelection="['null':'-Select-']" value="${specializationInstance?.id}"></g:select>                          
														</td>
												</tr>
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name" ><g:message code="Is it Evening College/Institution?"/>:</label>
																<label for="name" style="color:red;font-weight:bold">*</label>
														</td>
														<g:if test="${session.status=='update'}">
																<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'eveningCollege','errors')}" >
																		<g:if test="${institutionDetailsInstance.eveningCollege=='YES'}">
																				<g:radio name="eveningCollege" id="eveningCollege" value="YES" checked="checked"/><label for="eveningCollege" ><g:message code="YES"/></label>
																				<g:radio name="eveningCollege" id="eveningCollege" value="NO"/><label for="eveningCollege" ><g:message code="NO"/></label>
																		</g:if>
							                    						<g:elseif test="${institutionDetailsInstance.eveningCollege=='NO'}">
									                    						<g:radio name="eveningCollege" id="eveningCollege" value="YES"/><label for="eveningCollege" ><g:message code="YES"/></label>
																				<g:radio name="eveningCollege" id="eveningCollege" value="NO" checked="checked"/><label for="eveningCollege" ><g:message code="NO"/></label>
																		</g:elseif>
																		<g:else>
																				<g:radio name="eveningCollege" id="eveningCollege" value="YES"/><label for="eveningCollege" ><g:message code="YES"/></label>
																		<g:radio name="eveningCollege" id="eveningCollege" value="NO"/><label for="eveningCollege" ><g:message code="NO"/></label>
																		</g:else>
																</td>
														</g:if>
					                    				<g:else>
																<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'eveningCollege','errors')}" >
																		<g:radio name="eveningCollege" id="eveningCollege" value="YES"/><label for="eveningCollege" ><g:message code="YES"/></label>
																		<g:radio name="eveningCollege" id="eveningCollege" value="NO"/><label for="eveningCollege" ><g:message code="NO"/></label>
																</td>
														</g:else>
														
														<td valign="top" class="name">
																<label for="name" ><g:message code="Whether the College/Institution is exclusively meant for girls?"/>:</label>
																<label for="name" style="color:red;font-weight:bold">*</label>
														</td>
														<g:if test="${session.status=='update'}">
																<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'girlsExclusive','errors')}" >
																		<g:if test="${institutionDetailsInstance.girlsExclusive=='YES'}">
																				<g:radio name="girlsExclusive" id="girlsExclusive" value="YES" checked="checked"/><label for="girlsExclusive" ><g:message code="YES"/></label>
																				<g:radio name="girlsExclusive" id="girlsExclusive" value="NO"/><label for="girlsExclusive" ><g:message code="NO"/></label>
																		</g:if>
							                    						<g:elseif test="${institutionDetailsInstance.girlsExclusive=='NO'}">
									                    						<g:radio name="girlsExclusive" id="girlsExclusive" value="YES"/><label for="girlsExclusive" ><g:message code="YES"/></label>
																				<g:radio name="girlsExclusive" id="girlsExclusive" value="NO" checked="checked"/><label for="girlsExclusive" ><g:message code="NO"/></label>
																		</g:elseif>
																		<g:else>
																				<g:radio name="girlsExclusive" id="girlsExclusive" value="YES"/><label for="girlsExclusive" ><g:message code="YES"/></label>
																				<g:radio name="girlsExclusive" id="girlsExclusive" value="NO"/><label for="girlsExclusive" ><g:message code="NO"/></label>
																		</g:else>
																</td>
														</g:if>
					                    				<g:else>
																<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'girlsExclusive','errors')}" >
																		<g:radio name="girlsExclusive" id="girlsExclusive" value="YES"/><label for="girlsExclusive" ><g:message code="YES"/></label>
																		<g:radio name="girlsExclusive" id="girlsExclusive" value="NO"/><label for="girlsExclusive" ><g:message code="NO"/></label>
																</td>
														</g:else>
												</tr> 
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
																<input type="hidden" name="InstId" value="${session.InstId}">
										</tbody>      
								</table>
						</div>
						<div>
								<g:if test="${session.status=='update'}">
					                    <g:actionSubmit class="pushbutton" value="Update" action="saveInfo" />
										<input class="pushbutton" type='reset' value='Reset' />
			                    </g:if>
			                    <g:else>
					                    <g:actionSubmit class="pushbutton" value="Save" action="saveInfo" />
										<input class="pushbutton" type='reset' value='Reset' />
			                    </g:else>
						</div>
				</center>
		</g:form>
</div>