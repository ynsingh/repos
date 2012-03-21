


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fundAdvance.label', default: 'FundAdvance')}" />
        <title><g:message code="default.EditAdvance.label" /></title>
    </head>
    <body>
        <div class="wrapper"> 
	        <g:subMenuList/>  
	        <div class="proptable">    
	            <table width="100%" align="left">
	              <tr>
	             	<td><g:message code="default.ProjectCode.label"/>:</td>
		            <td><strong>${fieldValue(bean:projectsInstance, field:'code')}</strong></td>
		            <td><g:message code="default.AmountAllocated.label"/>:</td>
		            <td><strong>
		            		<g:message code="default.Rs.label" />
		            		${currencyFormat.ConvertToIndainRS(projectsInstance?.totAllAmount)}
	            		</strong>
	        		</td>
		          </tr> 
	          </table> 
      	 	</div> 
        
	    	<div class="body">
	            <h1><g:message code="default.EditAdvance.label" /></h1>
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
	            <g:form method="post" >
	                <g:hiddenField name="id" value="${fundAdvanceInstance?.id}" />
	                <g:hiddenField name="version" value="${fundAdvanceInstance?.version}" />
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        
	                        	<tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="dateOfAdvance"><g:message code="default.dateOfAdvance.label" /></label>
	                                  <label for="dateOfAdvance" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'dateOfAdvance', 'errors')}">
	                                    <calendar:datePicker name="dateOfAdvance" defaultValue="${new Date()}" value="${fundAdvanceInstance?.dateOfAdvance}" dateFormat= "%d/%m/%Y"/>
	                                </td>
	                                
	                                <td valign="top" class="name">
	                                  <label for="advanceAmount"><g:message code="default.advanceAmount.label" /></label>
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
	                                    <g:select name="grantAllocation.id" optionValue="grantCode" from="${grantAllocationInstanceList}" optionKey="id" value="${fundAdvanceInstance?.grantAllocation?.id}"  />
	                                </td>
	                                
	                                <td valign="top" class="name">
	                                  <label for="advanceDescription"><g:message code="default.advanceDescription.label" /></label>
	                                  <label for="advanceDescription" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'advanceDescription', 'errors')}">
	                                    <g:textArea name="advanceDescription" value="${fundAdvanceInstance?.advanceDescription}" rows="3" cols="30"/>
	                                </td>
	                            </tr>
	                            
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="modeOfPayment"><g:message code="default.ModeOfPayment.label" /></label>
	                                  <label for="modeOfPayment" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'modeOfPayment', 'errors')}">
	                                  <g:select name="modeOfPayment" from="${['DD','Cheque','Bank Transfer','Cash']}"  
                                       onchange="${remoteFunction(controller:'grantExpense',action:'updateModeOfPayment',update:'fieldSelect',  params:'\'modeOfPayment=\' + this.value' )}"
                                       value="${fieldValue(bean:fundAdvanceInstance,field:'modeOfPayment')}"></g:select>
                                    </td>
                                          
	                                  <g:if test="${(fundAdvanceInstance.modeOfPayment=='Bank Transfer' || fundAdvanceInstance.modeOfPayment=='Cash')}">
			                           <td valign="top" class="name">
			                                <label for="ddNo"><g:message code="default.DD/ChequeNo.label"/></label>:
			                                <label for="ddNo" style="color:red;font-weight:bold"> * </label>
						                </td>
						                <td id="fieldSelect" valign="top" class="value ${hasErrors(bean:fundAdvanceInstance,field:'ddNo','errors')}">
						             	 <input type="text" id="ddNo" name="ddNo" value="" disabled="true" />
			                           </td>
									  </g:if>
									  
									  <g:else>
									   <td valign="top" class="name">
			                                <label for="ddNo"><g:message code="default.DD/ChequeNo.label"/></label>:
			                                <label for="ddNo" style="color:red;font-weight:bold"> * </label>
						                </td>
						                <td id="fieldSelect" valign="top" class="value ${hasErrors(bean:fundAdvanceInstance,field:'ddNo','errors')}">
						                     <input type="text" id="ddNo" name="ddNo" value="${fieldValue(bean:fundAdvanceInstance,field:'ddNo')}" style="text-align: right" />
						                </td>
									 </g:else>
					 			   </tr> 
	                              
	                            	                                                   
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="ddDate"><g:message code="default.DD/ChequeDate.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'ddDate', 'errors')}">
	                                    <calendar:datePicker name="ddDate" defaultValue="${new Date()}" value="${fundAdvanceInstance?.ddDate}" dateFormat= "%d/%m/%Y"/>
	                                </td>
	                                
	                                <td valign="top" class="name">
	                                  <label for="bankName"><g:message code="default.BankName.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'bankName', 'errors')}">
	                                    <g:textField name="bankName" value="${fundAdvanceInstance?.bankName}" />
	                                </td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="ddBranch"><g:message code="default.Branch.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: fundAdvanceInstance, field: 'ddBranch', 'errors')}">
	                                    <g:textField name="ddBranch" value="${fundAdvanceInstance?.ddBranch}" />
	                                </td>
	                            </tr>
	                         </tbody>
	                    </table>
	                </div>
	                <g:if test="${fundAdvanceInstance.status == 'Closed'}">
			        	<g:message code="default.AlreadyClosedCantUpdate.message"/>
                 	</g:if>	
	     			<g:else>
		                <div class="buttons">
		                    <span class="button"><g:actionSubmit class="save" action="update" onClick="return validateFundAdvance()" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
		                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
		                </div>
	                </g:else>
	            </g:form>
	        </div>
    </body>
</html>
