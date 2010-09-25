<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.GrantReceipt.EditGrantReceipt.head"/></title>         
    </head>
    <body>
         <div class="wrapper"> 
	     <g:subMenuList/>
	      <table class="tablewrapper"> 
           <tr>
              <td>   
                 <div class="body">
                   <h1><g:message code="default.GrantReceipt.EditGrantReceipt.head"/></h1>
                   <g:if test="${flash.message}">
                      <div class="message">${flash.message}</div>
                   </g:if>
                   <g:hasErrors bean="${grantReceiptInstance}">
                      <div class="errors">
                      <g:renderErrors bean="${grantReceiptInstance}" as="list" />
                      </div>
                   </g:hasErrors>
                   <g:form method="post" >
                     <input type="hidden" name="id" value="${grantReceiptInstance?.id}" />
                     <div class="dialog">
                       <table>
                         <tbody>
                                               
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateOfReceipt"><g:message code="default.ReceiptDate.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'dateOfReceipt','errors')}">
                                     <calendar:datePicker name="dateOfReceipt" defaultValue="${new Date()}" value="${grantReceiptInstance?.dateOfReceipt}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
			                    <td valign="top" class="name">
                                    <label for="grantAllocation">
                                    	<g:message code="default.GrantAllocation.label"/>
                                    </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(grantReceiptInstance,field:'grantAllocation','errors')}">
                                    <g:select optionKey="id" optionValue="grantCode" from="${grantAllocationInstanceList}" name="grantAllocation.id" value="${grantReceiptInstance?.grantAllocation?.id}" noSelection="['null':'Select']"></g:select>
                                </td>
                            </tr> 
			                                
			                <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fundTransfer">
                                    	<g:message code="default.FundTransferred.label"/>
                                    </label>
                                </td>
                                <g:if test="${fundTransferInstanceList}">
	                                <td valign="top" class="value ${hasErrors(grantReceiptInstance,field:'grantAllocation','errors')}">
	                                    <g:select optionKey="id" optionValue="amountCode" from="${fundTransferInstanceList}" name="fundTransfer.id" value="${grantReceiptInstance?.fundTransfer?.id}" noSelection="['null':'Select']"></g:select>
	                                    
	                                </td>
	                            </g:if>
	                            <g:else>
	                            	<td>No Fund is Transferred</td>
	                            </g:else>
			                </tr> 
			                                
			                <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="default.FundsReceivedOrderNo.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'referenceId','errors')}">
                                    <input type="text" id="referenceId" name="referenceId" value="${fieldValue(bean:grantReceiptInstance,field:'referenceId')}" style="text-align: right" />
                                </td>
                            </tr>                

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="default.Amount.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'amount','errors')}">
                                    <input type="text" id="amount" name="amount" value="${grantReceiptInstance.amount}" style="text-align: right" />
                                    <input type="hidden" id="grantAllocationID" name="grantAllocationID" value="${fieldValue(bean:grantReceiptInstance.grantAllocation, field:'id')}"/>
                                    <input type="hidden" id="balanceAmt" name="balanceAmt" value="${grantReceiptInstance?.balanceAmt}" />
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modeOfPayment"><g:message code="default.ModeOfPayment.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'modeOfPayment','errors')}">
                                    <g:select name="modeOfPayment" from="${['DD','Cheque','Cash' ,'BankTransfer']}"  value="${fieldValue(bean:grantReceiptInstance,field:'modeOfPayment')}" />
                                </td>
                            </tr>      

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ddDate"><g:message code="default.DD/ChequeDate.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'ddNo','errors')}">
                                    <input type="text" id="ddNo" name="ddNo" value="${fieldValue(bean:grantReceiptInstance,field:'ddNo')}" style="text-align: right" />
                                </td>
                            </tr> 
                   
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ddNo"><g:message code="default.DD/ChequeNo.label"/></label>
                                </td>
                                    <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'ddDate','errors')}">
                                    <calendar:datePicker name="ddDate" defaultValue="${new Date()}" value="${grantReceiptInstance?.ddDate}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="bankName"><g:message code="default.BankName.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'bankName','errors')}">
                                    <input type="text" id="bankName" name="bankName" value="${fieldValue(bean:grantReceiptInstance,field:'bankName')}" style="text-align: right" />
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ddBranch"><g:message code="default.Branch.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'ddBranch','errors')}">
                                    <input type="text" id="ddBranch" name="ddBranch" value="${fieldValue(bean:grantReceiptInstance,field:'ddBranch')}" style="text-align: right" />
                                </td>
                            </tr> 
                       
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="default.Description.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'description','errors')}">
                                  <g:textArea name="description" value="${fieldValue(bean:grantReceiptInstance,field:'description')}" rows="3" cols="30"/>
                                </td>
                            </tr> 
                        
                         </tbody>
                       </table>
                     </div>
                     <div class="buttons">
                       <span class="button"><g:actionSubmit class="save" value="${message(code: 'default.Update.button')}" onClick="return validateGrantReceipt()"  /></span>
                       <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="${message(code: 'default.Delete.button')}" /></span>
                     </div>
                   </g:form>
                 </div>
              </td>
           </tr>
          </table>
         </div>
    </body>
</html>
