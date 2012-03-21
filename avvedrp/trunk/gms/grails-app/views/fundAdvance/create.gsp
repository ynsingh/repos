<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fundAdvance.label', default: 'FundAdvance')}" />
        <title><g:message code="default.FundAdvance.label"/></title>
    </head>
    <body>
        <div class="wrapper"> 
	        <g:subMenuList/>  
	        <div class="proptable">    
	            <table width="100%" align="left">
	              <tr>
	             	<td ><g:message code="default.ProjectCode.label"/>:</td>
		            <td><strong>${fieldValue(bean:projectInstance, field:'code')}</strong></td>
		            <td ><g:message code="default.AmountAllocated.label"/>:</td>
		            <td ><strong>
		            		<g:message code="default.Rs.label" />
		            		${currencyFormat.ConvertToIndainRS(projectInstance?.totAllAmount)}
	            		</strong>
	        		</td>
		          </tr> 
	          </table> 
      	 	</div> 
	        <div class="body">
	            <h1><g:message code="default.FundAdvance.label" /></h1>
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:if test="${flash.error}">
	            	<div class="errors">${flash.error}</div>
            	</g:if>
	            <g:hasErrors bean="${fundAdvanceInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${fundAdvanceInstance}" as="list" />
	            </div>
	            </g:hasErrors>
	            <g:form action="save" method="post" >
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        
	                        	<tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="dateOfAdvance"><g:message code="default.dateOfAdvance.label"/></label>
	                                	<label for="dateOfAdvance" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'dateOfAdvance', 'errors')}">
	                                     <calendar:datePicker name="dateOfAdvance" defaultValue="${new Date()}" value="${fundAdvanceInstance?.dateOfAdvance}" dateFormat= "%d/%m/%Y"/>
	                                     <g:hiddenField name="projectCode" value="${projectInstance?.code}" /> 
	                                </td>
	                                
	                                <td valign="top" class="name">
	                                    <label for="advanceAmount"><g:message code="default.advanceAmount.label"/></label>
	                                    <label for="advanceAmount" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'advanceAmount', 'errors')}">
	                                    <g:textField name="advanceAmount" value="${amount}" />
	                                </td>
	                            </tr>
	                            
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="grantAllocation"><g:message code="default.GrantAllocation.label" /></label>
	                                	<label for="grantAllocation" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'grantAllocation', 'errors')}">
	                                    <g:select optionKey="id" optionValue="grantCode" from="${grantAllocationInstanceList}" name="grantAllocation.id" value="${fundAdvanceInstance?.grantAllocation?.id}"  noSelection="['null':'-Select-']" ></g:select>
	                                </td>
	                                <td valign="top" class="name">
	                                    <label for="advanceDescription"><g:message code="default.advanceDescription.label"/></label>
	                               		<label for="advanceDescription" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'advanceDescription', 'errors')}">
	                                    <g:textArea name="advanceDescription" value="${fundAdvanceInstance?.advanceDescription}" rows="3" cols="30"/>
	                                </td>
	                                
	                            </tr>
	                            
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="modeOfPayment"><g:message code="default.ModeOfPayment.label"/></label>
	                                	<label for="modeOfPayment" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'modeOfPayment', 'errors')}">
	                                  <g:select name="modeOfPayment" from="${['DD','Cheque','Bank Transfer','Cash']}"  
                                      onchange="${remoteFunction(controller:'grantExpense',action:'updateModeOfPayment',update:'fieldSelect',  params:'\'modeOfPayment=\' + this.value' )}"
                                      value="${fieldValue(bean:fundAdvanceInstance,field:'modeOfPayment')}" noSelection="['null':'-Select-']"></g:select>
                                    </td>
	                                <td valign="top" class="name">
	                                    <label for="ddDate"><g:message code="default.DD/ChequeDate.label"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'ddDate', 'errors')}">
	                                    <calendar:datePicker name="ddDate" defaultValue="${new Date()}" value="${fundAdvanceInstance?.ddDate}" dateFormat= "%d/%m/%Y"/>
	                                </td>
	                                
	                            </tr>
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="ddNo"><g:message code="default.DD/ChequeNo.label"/></label>
	                                    <label for="ddNo" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'ddNo', 'errors')}">
	                                 <div id="fieldSelect">
	                                    <g:textField name="ddNo" value="${fundAdvanceInstance?.ddNo}" />
	                                 </div>
	                                </td>
	                                
	                                <td valign="top" class="name">
	                                    <label for="bankName"><g:message code="default.BankName.label"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'bankName', 'errors')}">
	                                    <g:textField name="bankName" value="${fundAdvanceInstance?.bankName}" />
	                                </td>
	                            </tr>
	                        
	                                                  
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="ddBranch"><g:message code="default.Branch.label"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'ddBranch', 'errors')}">
	                                    <g:textField name="ddBranch" value="${fundAdvanceInstance?.ddBranch}" />
	                                </td>
	                            </tr>
	                        </tbody>
	                    </table>
	                </div>
	                <div class="buttons">
	                    <span class="button"><g:submitButton name="create" class="save" onClick="return validateFundAdvance()" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
	                </div>
	            </g:form>
	            
	           	<h1><g:message code="default.AdvanceFundList.head"/></h1> 
		        <div class="list">
		        <g:if test="${advanceFundList}">
	                <table>
	                    <thead>
	                        <tr>
	                        
	                            <th><g:message code="default.SINo.label"/></th>
	                        
	                            <th><g:message code="default.dateOfAdvance.label"/></th>
	                            
	                             <th><g:message code="default.GrantAllocation.label"/></th>
	                            
	                            <th><g:message code="default.advanceDescription.label"/></th>
	                            
	                            <th><g:message code="default.advanceAmount.label"/></th>
	                            
	                            <th><g:message code="default.BalanceAmount.label"/></th>
	                             
	                            <th><g:message code="default.AdvanceClosure.label"/></th>
	                              
	                            <th><g:message code="default.ExpenseDetails.label"/></th>
	                                 
	                            <th><g:message code="default.Edit.label"/></th>
	                         </tr>
                    	</thead>
	                    <tbody>
		                    <g:each in="${advanceFundList}" status="i" var="fundAdvanceInstance">
		                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
		                        	<td>${i+1}</td>
		                        	
		                        	<td><g:formatDate format="dd/MM/yyyy" date="${fundAdvanceInstance.dateOfAdvance}"/></td>
		                        	
		                        	<td>${fundAdvanceInstance.grntAlln}</td>
		                        	
		                        	<td>${fieldValue(bean: fundAdvanceInstance, field: "advanceDescription")}</td>
		                        	
		                        	<td>${currencyFormat.ConvertToIndainRS(fundAdvanceInstance.advanceAmount)}</td>
		                        	
		                        	<td>${currencyFormat.ConvertToIndainRS(fundAdvanceInstance.balanceAmount)}</td>
		                        	<td>
			                        	<g:if test="${fundAdvanceInstance.status == 'Closed'}">
			                         		<g:link action="close" id="${fundAdvanceInstance.id}">
			                         		<g:message code="default.Closed.label"/></g:link>
			                         	</g:if>	
	                     				<g:else>
	                     					<g:link action="close" id="${fundAdvanceInstance.id}">
	                     					<g:message code="default.Close.label"/></g:link>
		                    			</g:else>
		                        	</td>	                        	
		                        	<td><g:link action="expenseDetails" id="${fundAdvanceInstance.id}">
				                     			<g:message code="default.ExpenseDetails.label"/> 
		                        		</g:link> </td>
		                        	
		                        	<td><g:link action="edit" id="${fundAdvanceInstance.id}">
				                     			<g:message code="default.Edit.label"/> 
		                        		</g:link> </td>
		                        </tr>
		                    </g:each>
	                    </tbody>
            		</table>
            	</g:if>
				<g:else>
					<br><g:message code="default.NoRecordsAvailable.label"/></br>
				</g:else>
            </div>
	    </div>
    </body>
</html> 
