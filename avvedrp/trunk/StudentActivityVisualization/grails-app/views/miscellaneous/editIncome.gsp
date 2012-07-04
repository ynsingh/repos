<div id="head">
<meta name="layout" content="main" />
</div>
<div class="body">
<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${financialInfoInstance}">
		<div class="errors">
			<g:renderErrors bean="${financialInfoInstance}" as="list" />
		</div>
	</g:hasErrors>
	<g:form controller="miscellaneous" method="post">
	<center>
	    <input type="hidden" name="id" value="${financialInfoInstance?.id}" />
	    <input type="hidden" name="version" value="${financialInfoInstance?.version}" />
		<div class="dialog">
		<br /><br /><h3><g:message code="Edit Income"/></h3> <br />
			<table >
				<tbody>
				   <tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Select your Institution"/>:</label>
						</td>
						<td valign="top"  class="value">
							<g:select from= "${institutionDetailsInstanceList}" id="institutionDetails" optionKey="id" name="institutionDetails"  noSelection="['null':'-Select-']" value="${financialInfoInstance?.institutionDetails?.id}"></g:select>
						</td>
				    </tr>
				    <tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Select item"/>:</label>
						</td>
						<td valign="top"  class="value" >
							<g:select from= "${itemList}" id="item" optionKey="id" name="item"  noSelection="['null':'-Select-']" value="${itemInstance?.id}"></g:select>
						</td>
					</tr>
					<tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Amount Spent"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:financialInfoInstance,field:'amount','errors')}">
							<input type="text" size="8" id="amount" name="amount" value="${fieldValue(bean:financialInfoInstance,field:'amount')}"/>
						</td>
				   </tr>
				</tbody>      
			</table>
		</div>
		<div>
			<g:actionSubmit class="pushbutton" value="Update" action="updateIncome" />
			<g:actionSubmit class="pushbutton" value="Delete"  action="deleteIncome"/>
		</div>
		</center>
	</g:form>
</div>