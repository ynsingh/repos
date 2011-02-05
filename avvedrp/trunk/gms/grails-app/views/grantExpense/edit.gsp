

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.GrantExpense.EditGrantExpense.head"/></title>         
    </head>
    <body>
        <div class="wrapper"> 
        <g:subMenuList/>
          <div class="proptable">    
            <table width="100%" align="left">
        
             <tr class="prop">
               <td valign="top" ><g:message code="default.ProjectCode.label"/>:</td>
               <td valign="top" ><strong>${fieldValue(bean:projectsInstance, field:'code')}</strong></td>
               <td valign="top" ><g:message code="default.AmountAllocated.label"/>:</td>
               <td valign="top" ><strong>
               						<g:message code="default.Rs.label"/>
               						${currencyFormat.ConvertToIndainRS(projectsInstance.totAllAmount)}</strong></td>
             </tr> 
            </table> 
          </div>

          <br>
            <div class="body">
              <h1><g:message code="default.GrantExpense.EditGrantExpense.head"/></h1>
              <g:if test="${flash.message}">
                  <div class="message">${flash.message}</div>
              </g:if>
              <g:hasErrors bean="${grantExpenseInstance}">
                <div class="errors">
                   <g:renderErrors bean="${grantExpenseInstance}" as="list" />
                </div>
              </g:hasErrors>
              <g:form method="post" >
                  <input type="hidden" name="id" value="${grantExpenseInstance?.id}" />
                  <div class="dialog">
                    <table>
                      <tbody>
                    
                    	<tr class="prop">
                            <td valign="top" class="name">
                                <label for="dateOfExpense"><g:message code="default.ExpenseDate.label"/></label>: 
                            </td>
                            <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'dateOfExpense','errors')}">
                                <calendar:datePicker name="dateOfExpense" defaultValue="${new Date()}" value="${grantExpenseInstance?.dateOfExpense}" dateFormat= "%d/%m/%Y"/>
                            <g:hiddenField name="projects.id" value="${grantExpenseInstance?.projects?.id}" /> 
                            </td>
                        </tr> 
                        
                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="grantAllocationSplit"><g:message code="default.AccountHeads.label"/></label>: 
                                <label for="grantAllocationSplit" style="color:red;font-weight:bold"> * </label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'grantAllocationSplit','errors')}">
                            	<g:select optionKey="id" optionValue="accHeadPeriod" from="${accountHeadList}" noSelection="['null':'-Select-']" name="grantAllocationSplit.id" value="${grantExpenseInstance?.grantAllocationSplit?.id}" ></g:select>
                            </td>
                        </tr>  
                    
                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="expenseAmount"><g:message code="default.ExpenseAmount(Rs).label"/></label>:
                                <label for="expenseAmount" style="color:red;font-weight:bold"> * </label> 
                            </td>
                            <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'expenseAmount','errors')}">
                                <input type="text" id="expenseAmount" name="expenseAmount" value="${amount}" style="text-align: right" />
                                <g:hiddenField name="grantAllocation.id" value="${grantExpenseInstance?.grantAllocation?.id}" />
                            </td>
                        </tr> 
                        
                        <tr class="prop">
			                <td valign="top" class="name">
                                <label for="modeOfPayment"><g:message code="default.ModeOfPayment.label"/></label>:
                                <label for="modeOfPayment" style="color:red;font-weight:bold"> * </label> 
			                </td>
			                <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'modeOfPayment','errors')}">
			                    <g:select name="modeOfPayment" from="${['DD','Cheque','BankTransfer']}"  value="${fieldValue(bean:grantExpenseInstance,field:'modeOfPayment')}" />
			                </td>
			            </tr>      
			                    
			            <tr class="prop">
			                <td valign="top" class="name">
                                <label for="ddNo"><g:message code="default.DD/ChequeNo.label"/></label>:
                                <label for="ddNo" style="color:red;font-weight:bold"> * </label>
			                </td>
			                <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'ddNo','errors')}">
			                     <input type="text" id="ddNo" name="ddNo" value="${fieldValue(bean:grantExpenseInstance,field:'ddNo')}" style="text-align: right" />
			                </td>
			            </tr> 
			                       
			            <tr class="prop">
			                <td valign="top" class="name">
                                <label for="ddDate"><g:message code="default.DD/ChequeDate.label"/></label>:
			                </td>
			                <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'ddDate','errors')}">
			                     <calendar:datePicker name="ddDate" defaultValue="${new Date()}" value="${grantExpenseInstance?.ddDate}" dateFormat= "%d/%m/%Y"/>
			                </td>
			            </tr> 
			             
			            <tr class="prop">
			                <td valign="top" class="name">
                                <label for="bankName"><g:message code="default.BankName.label"/></label>:
                                <label for="bankName" style="color:red;font-weight:bold"> * </label>
			                </td>
			                <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'bankName','errors')}">
			                     <input type="text" id="bankName" name="bankName" value="${fieldValue(bean:grantExpenseInstance,field:'bankName')}" style="text-align: right" />
			                </td>
			            </tr> 
			                     
			            <tr class="prop">
			                <td valign="top" class="name">
                                <label for="ddBranch"><g:message code="default.Branch.label"/></label>:
                                <label for="ddBranch" style="color:red;font-weight:bold"> * </label>
			                </td>
			                <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'ddBranch','errors')}">
			                     <input type="text" id="ddBranch" name="ddBranch" value="${fieldValue(bean:grantExpenseInstance,field:'ddBranch')}" style="text-align: right" />
			                </td>
                        </tr> 
                    
                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="description"><g:message code="default.Description.label"/></label>: 
                            </td>
                            <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'description','errors')}">
                                <g:textArea name="description" value="${fieldValue(bean:grantExpenseInstance,field:'description')}" rows="3" cols="30"/>
                            </td>
                        </tr> 
               
                      </tbody>
                    </table>
                  </div>
                  <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" onClick="return validateGrantExpense()"  /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" onclick="return confirm('Are you sure?');" value="${message(code: 'default.Delete.button')}" /></span>
                  </div>
              </g:form>
            </div>
          </br>
        </div>
    </body>
</html>
