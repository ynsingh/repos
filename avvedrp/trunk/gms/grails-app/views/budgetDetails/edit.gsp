


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'budgetDetails.label', default: 'BudgetDetails')}" />
        <title><g:message code="default.budgetDetailEdit.label" args="[entityName]" /></title>
    </head>
    <body>
       <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.budgetDetailEdit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${budgetDetailsInstance}">
            <div class="errors">
                <g:renderErrors bean="${budgetDetailsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${budgetDetailsInstance?.id}" />
                <g:hiddenField name="version" value="${budgetDetailsInstance?.version}" />
                <div class="dialog">
                    <table>
                    <tbody>
                         <tr class="prop">
                                <input type="hidden" id="balanceAmount" name="balanceAmount" value="${balance}"/>        
		                 </tr>
		                 <tr class="prop">
                              <input type="hidden" id="totalBudgetAmount" name="totalBudgetAmount" value="${currencyFormat.ConvertToIndainRS(budgetMasterInstance.totalBudgetAmount)}">
                          </tr>
                          <tr class="prop">
                               <td valign="top" class="name">
                                  <label for="budgetMaster"><g:message code="default.budgetDetails.budgetMaster.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetDetailsInstance, field: 'budgetMaster', 'errors')}">
                                  ${fieldValue(bean: budgetMasterInstance,field: "title")}
                                  <input type=hidden id="budgetMasterInstance.id" name="budgetMasterInstance.id" value="${budgetMasterInstance.id}"> 
                                </td>
                             </tr>
                             
                            
                                
                             <td valign="top" class="name">
                                 	<label for="accountHead"><g:message code="default.budgetDetailsAccountHead.label" />:</label>
                                 	<label for="accountHead" style="color:red;font-weight:bold"> * </label>
                              </td>
                              
                              <g:if test="${accountHeadsInstance.parent != null}">
		                         <td valign="top" class="value ${hasErrors(bean:budgetDetailsInstance,field:'accountHeads','errors')}">
                                  <g:select optionKey="id" from="${accountHeadList}" optionValue="accHeadCode" name="accountHead.id" value="${accountHeadsInstance?.parent?.id}" 
                                  onchange="${remoteFunction(controller:'budgetDetails',action:'updateSubAccount',update:'subAccountHead',  params:'\'accountHead=\' + this.value' )}" 
                                  onFocus="${remoteFunction(controller:'budgetDetails',action:'updateSubAccount',update:'subAccountHead',  params:'\'accountHead=\' + this.value' )}" noSelection="['null':'-Select-']" >
                                  </g:select>
                                </td>
	                         </g:if>
	                         <g:else>
                                <td valign="top" class=" " value ${hasErrors(bean:budgetDetailsInstance,field:'accountHeads','errors')}">
	                             <g:select optionKey="id" 
		                         from="${accountHeadList}"
		                         optionValue="accHeadCode" name="accountHead.id" value="${budgetDetailsInstance?.accountHeads?.id}" 
		                         onchange="${remoteFunction(controller:'budgetDetails',action:'updateSubAccount',update:'subAccountHead',  params:'\'accountHead=\' + this.value' )}">
	                             </g:select>
                                </td>
                             </g:else>
                          
                            </tr>
                            
                            <tr class="prop">
                               <td valign="top" class="name">
                              	<label for="subaccountHead"><g:message code="default.budgetDetails.SubAccountHead.label" />:</label>
                               </td>
                              <td valign="top" class="name">
                            			<div id="subAccountHead">
							    			<g:select 																	       
										       name="subAccountHead" 
										       value="" 
											   optionKey="id" optionValue="accHeadCode" from="${subAccountHeadsList}" noSelection="['null':'-Select-']"  
											   value="${budgetDetailsInstance?.accountHeads?.id}" >
											</g:select>
										</div>
									</td>
									
						   <tr class="prop">	  
							   <td valign="top" class="name">
                                  <label for="allocatedAmount"><g:message code="default.budgetDetails.allocatedAmount(Rs).label" /></label>
                                  <label for="allocatedAmount" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetDetailsInstance, field: 'allocatedAmount', 'errors')}">
                                    <g:textField name="allocatedAmount" value="${amount}" style="text-align: right" format="0.00" />
                                </td>
                            </tr>
                            
                            <tr>
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="default.budgetDetails.remarks.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetDetailsInstance, field: 'remarks', 'errors')}">
                                    <g:textArea name="remarks" value="${budgetDetailsInstance?.remarks}" style='width: 200px; height:75px;'/>
                                </td>
                           </tr>
                         
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update"  value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                </div>
            </g:form>
        </div>
        </div>
    </body>
</html>
