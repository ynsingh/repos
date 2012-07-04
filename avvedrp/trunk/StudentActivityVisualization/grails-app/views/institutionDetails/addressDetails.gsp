<div id="head">
		<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'general.css')}" />
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
				<input type="hidden" name="InstId" value="${session.InstId}" />
						<div class="dialog">
								<br /><br /><h3><g:message code="Address Details"/></h3><br />
								<table>
										<tbody>
										        <input type="hidden" name="InstId" value="${session.InstId}" />
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name"  ><g:message code="default.instname.label"/>:</label>
																<label for="name" style="color:red;font-weight:bold"> * </label>
														</td>
														<td valign="top" class="value ${hasErrors(bean:institutionDetailsInstance,field:'institutionName','errors')}">
																<input type="text" size="20" id="institutionName" name="institutionName" value="${institutionDetailsInstance?.institutionName}"/>
														</td>
												</tr>
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name" ><g:message code="default.instaddr.label"/>:</label>
																<label for="name" style="color:red;font-weight:bold"> * </label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'postalAddress','errors')}">
																<textarea name="postalAddress" id="postalAddress" cols="23" rows="2">${institutionDetailsInstance?.postalAddress}</textarea>
														</td>
														
														<td valign="top" class="name">
																<label for="name"  ><g:message code="default.State.label"/>:</label>
																<label for="name" style="color:red;font-weight:bold"> * </label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'state','errors')}">
																<g:select from= "${stateInstanceList}" id="state" optionKey="id" name="state" noSelection="['null':'-Select-']" value="${stateInstance?.id}"></g:select>
														</td>
														
														<td valign="top" class="name">
																<label for="name"  ><g:message code="default.district.label"/>:</label>
																<label for="name" style="color:red;font-weight:bold"> * </label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'district','errors')}">
															<input type="text" size="20" id="district" name="district" value="${institutionDetailsInstance?.district}"/>
														</td>
												</tr> 
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name"  ><g:message code="default.website.label"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'website','errors')}">
																<input type="text" size="20" id="website" name="website" value="${institutionDetailsInstance?.website}"/>
														</td>
														
														<td valign="top" class="name">
																<label for="name"  ><g:message code="default.totarea.label"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'totalArea','errors')}">
																<input type="number" size="10" id="totalArea" name="totalArea" value="${institutionDetailsInstance?.totalArea}" />
														</td>
														
														<td valign="top" class="name">
																<label for="name"  ><g:message code="default.totconstructedarea.label"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'totalConstructedArea','errors')}">
																<input type="number" size="10" id="totalConstructedArea" name="totalConstructedArea" value="${institutionDetailsInstance?.totalConstructedArea}"/>
														</td>
												</tr>
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
												<tr class="prop">
														<td valign="top" class="name">
																<label for="name"  ><g:message code="default.yearofestablishment.label"/>:</label>
														</td>
														<td valign="top"  class="value ${hasErrors(bean:institutionDetailsInstance,field:'establishedYear','errors')}">
																<input type="number" size="10" id="establishedYear" name="establishedYear" value="${institutionDetailsInstance?.establishedYear}"/>
														</td>
												</tr>
												<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
										</tbody>                           
								</table>
						</div>
						<div>
								<g:if test="${session.status=='update'}">
					                    <g:actionSubmit class="pushbutton" value="Update" action="updateAddress" onClick="return validateAddressForm();"/>
										<input class="pushbutton" type='reset' value='Reset' />
			                    </g:if>
			                    <g:else>
					                    <g:actionSubmit class="pushbutton" value="Create" action="saveAddress" onClick="return validateAddressForm();"/>
										<input class="pushbutton" type='reset' value='Reset' />
			                    </g:else>
	                    </div>
				</center>
		</g:form>
</div>
