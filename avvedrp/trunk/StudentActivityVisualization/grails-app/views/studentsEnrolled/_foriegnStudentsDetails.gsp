	<tr class="prop">
					   
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Country Name"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'fsCountryName','errors')}">
							<input type="text" size="20" id="fsCountryName" name="fsCountryName" value="${programmeDetailsInstance?.fsCountryName}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Country Code"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'fsCountryCode','errors')}">
							<input type="text" size="20" id="fsCountryCode" name="fsCountryCode" value="${programmeDetailsInstance?.fsCountryCode}"/>
						</td>
				   </tr>
				   <tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
				   <tr class="prop">
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Total Strength"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'totalFs','errors')}">
							<input type="text" size="20" id="totalFs" name="totalFs" value="${programmeDetailsInstance?.totalFs}"/>
						</td>
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Female Strength"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:programmeDetailsInstance,field:'femaleFs','errors')}">
							<input type="text" size="20" id="femaleFs" name="femaleFs" value="${programmeDetailsInstance?.femaleFs}"/>
						</td>
					</tr>