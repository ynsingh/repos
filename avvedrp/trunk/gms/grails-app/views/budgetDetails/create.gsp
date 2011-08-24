


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'default.budgetDetails.label')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        
        <div class ="wrapper">
        <div class="body">
      
            <h1><g:message code="default.budgetDetailCreate.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${budgetDetailsInstance}">
            <div class="errors">
                <g:renderErrors bean="${budgetDetailsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                         
                            <tr class="prop">
                              <td valign="top" class="name">
                                  <label for="budgetMaster"><g:message code="default.budgetDetails.budgetMaster.label" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetDetailsInstance, field: 'budgetMaster', 'errors')}">
                                  <strong>${fieldValue(bean: budgetMasterInstance,field: "title")}</strong>
                                  <input type="hidden" id="budgetMasterInstance.id" name="budgetMasterInstance.id" value="${budgetMasterInstance.id}"> 
                               </td>
                           <td>
                             <label for="BalanceAmount"><g:message code="default.budgetMaster.totalBudgetAmount(Rs).label" />:</label>
                          </td>
                           <td>
                              <strong>${currencyFormat.ConvertToIndainRS(budgetMasterInstance.totalBudgetAmount)}</strong>
                           </td>
                           <td>
                              <input type="hidden" id="totalBudgetAmount" name="totalBudgetAmount" value="${currencyFormat.ConvertToIndainRS(budgetMasterInstance.totalBudgetAmount)}"> 
                           </td>            
                            <tr class="prop">
                              <td valign="top" class="name">
                                 <label for="accountHead"><g:message code="default.budgetDetailsAccountHead.label" />:</label>
                                 <label for="accountHead" style="color:red;font-weight:bold"> * </label>
                              </td>
                               <td valign="top" class="value ${hasErrors(bean:budgetDetailsInstance,field:'accountHeads','errors')}">
                                  <g:select optionKey="id" from="${accountHeadList}" optionValue="accHeadCode" name="accountHead.id" value="${budgetDetailsInstance?.accountHeads?.id}" 
                                  onchange="${remoteFunction(controller:'budgetDetails',action:'updateSubAccount',update:'subAccountHead',  params:'\'accountHead=\' + this.value' )}" 
                                  onFocus="${remoteFunction(controller:'budgetDetails',action:'updateSubAccount',update:'subAccountHead',  params:'\'accountHead=\' + this.value' )}" noSelection="['null':'-Select-']" >
                                 </g:select>
                              </td>
                              
                                <td valign="top" class="name">
                                  <label for="allocatedAmount"><g:message code="default.budgetDetails.allocatedAmount(Rs).label" />:</label>
                                  <label for="allocatedAmount" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetDetailsInstance, field: 'allocatedAmount', 'errors')}">
   								  <input type="text" id="allocatedAmount" name="allocatedAmount" value="${amount}" style="text-align: right" />
                                <!-- <g:textField name="allocatedAmount" value="${fieldValue(bean: budgetDetailsInstance, field: 'allocatedAmount')}" />-->
                                </td>
                              
					       </tr>
                            
                            <tr>
                               <td valign="top" class="name">
                              	<label for="subaccountHead"><g:message code="default.budgetDetails.SubAccountHead.label" />:</label>
                               </td>
                              <td valign="top" class="name">
                              <div id="subAccountHead">
							    <g:select name="subAccountHead"  value=""  disabled="true" noSelection="['null':'-Select-']"  value="${budgetDetailsInstance?.accountHeads?.id}" >
								</g:select>
							  </div>
							  </td> 					  
							 
                            
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="default.budgetDetails.remarks.label" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetDetailsInstance, field: 'remarks', 'errors')}">
                                    <g:textArea name="remarks" value="${budgetDetailsInstance?.remarks}" style='width: 150px; height:65px;'/>
                                </td>
                           </tr>
                         
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                     <span class="button"><g:submitButton name="create" class="save"   onClick="return validateBudgetDetails()" value="${message(code: 'default.Create.button')}" /></span>
                     <span class="button"><g:actionSubmit class="cancel" action="cancel" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}" /></span>
			  </div>
            </g:form>
             </div>
             <div class="body">
         <h1><g:message code="default.BudgetDetaillist.label" args="[entityName]" /></h1>
         
         <div class="list">
            <g:if test="${budgetDetailsInstanceList}">
                <table>
                    <thead>
                        <tr>
                     
                            
                            <th><g:message code="default.SINo.label" /></th>
                        
							<th><g:message code="default.budgetDetailsAccountHead.label" /></th>
							
                            <th><g:message code="default.budgetDetails.allocatedAmount(Rs).label" /></th>
                            
                            <th><g:message code="default.budgetDetails.remarks.label" /></th>
                            
                            <th><g:message code="default.Edit.label" /></th>
                            
                        </tr>
                    </thead>
                    <tbody>
                   
                    <g:each in="${budgetDetailsInstanceList}" status="i" var="budgetDetailsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                        
                            <td>${fieldValue(bean: budgetDetailsInstance, field: "accountHeads.name")}</td>
                           
                            <td>${currencyFormat.ConvertToIndainRS(budgetDetailsInstance.allocatedAmount)}</td>
                            
                            <td>${fieldValue(bean: budgetDetailsInstance, field: "remarks")}</td>
                            
                            <td><g:link action="edit" params="[budgetMasterId:budgetMasterInstance.id]"  id="${fieldValue(bean:budgetDetailsInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                           
                        </tr>
                    </g:each>
                    </tbody>
                </table>
           </g:if>
            </div>
           </div>
        </div>
    </body>
</html>
