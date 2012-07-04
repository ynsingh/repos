<div id="head">
<meta name="layout" content="main" />
</div>
<div id="body">
<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${financialInfoInstance}">
		<div class="errors">
			<g:renderErrors bean="${financialInfoInstance}" as="list" />
		</div>
	</g:hasErrors>
<g:form controller="miscellaneous" >
<center>
		<div class="dialog">
			<table >
			<br /><br /><h3><g:message code="Income Details"/></h3> <br />
				<tbody>
				  <tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
				  <tr class="prop">
						<input type="hidden" name="InstId" value="${session.InstId}" />
						<td valign="top" class="name">
							<label for="name" ><g:message code="Select item"/>:</label>
						</td>
						<td valign="top"  class="value" >
							<g:select from= "${itemList}" id="item" optionKey="id" name="item"  noSelection="['null':'-Select-']" value="${financialInfoInstance?.item}"></g:select>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Amount Recieved"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:financialInfoInstance,field:'amount','errors')}">
							<input type="text" size="8" id="amount" name="amount" value="${fieldValue(bean:financialInfoInstance,field:'amount')}"/>
						</td>
				   </tr>
				   <tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
					</tbody>
			</table>
		<div>
		<div>
			<g:actionSubmit class="pushbutton" value="Save" action="saveIncome" />
			<input class="pushbutton" type='reset' value='Reset' />
	    </div>
	    </center>
</g:form>
</div>		
<div class="body">
	<div class="list">
	  <center>
	  <fieldset style="width:50%; border-color:transparent; padding:0;">
	  <div id="listIncome">
		<table frame="box">
			<thead>
				<tr  style="font-size:12px; ">
					<g:sortableColumn property="id" title="${message(code: 'SINo')}"/>&nbsp
					
					<g:sortableColumn property="institutionDetails" title="${message(code: 'Institution')}" />
					
					<g:sortableColumn property="item" title="${message(code: 'Item')}"/>
					
					<g:sortableColumn property="amount" title="${message(code: 'Amount')}" />
					
					<th><g:message code="Edit" /></th>
				   
				</tr>
			</thead>
		    
			<tbody>
			        <g:each in="${financialInfoInstanceList}" status="i" var="financialInfoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td class="listing">${i+1}</td>
                            
                            <td class="listing">${fieldValue(bean:financialInfoInstance, field:'institutionDetails.institutionName')}</td>
                            
                            <td class="listing">${fieldValue(bean:financialInfoInstance, field:'item')}</td>
                            
                            <td class="listing">${fieldValue(bean:financialInfoInstance, field:'amount')}</td>
                            
                             <td class="listing"><g:link action="editIncome" id="${financialInfoInstance.id}"><g:message code="Edit"/></g:link></td>
                        	
                        </tr>
                    </g:each>
                    	
                    </tbody>
			</table>
		</div>
		</fieldset>
		</center>
	</div>
    

</div>
