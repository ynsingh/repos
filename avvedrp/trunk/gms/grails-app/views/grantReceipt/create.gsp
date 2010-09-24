

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.GrantReceipt.GrantReceipt.head"/></title>         
    </head>
    <body>
      <div class="wrapper"> 
         <g:subMenuList/>
         <div class="proptable"> 
            <table width="100%" align="left">
                 <tr>
                      
                      <td valign="top">
                          <label for="project"><g:message code="default.Projects.label"/>:</label>
                      </td>
                      <td valign="top" >
                          <strong>  ${fieldValue(bean:grantReceiptInstance.projects,field:'code')} </strong>
                      </td>
                                  
                      <td valign="top" >
                          <label for="party"><g:message code="default.Institution.label"/>:</label>
                          <strong>  ${fieldValue(bean:grantReceiptInstance.grantAllocation.party,field:'code')} </strong>
                      </td>
                            
                      <td valign="top" >
                          <label for="party"> <g:message code="default.AllocatedAmount.label"/>:</label>
                      </td>
                      <td valign="top" >
                          <td ><strong>
                      			<g:message code="default.Rs.label" />
                      			${currencyFormat.ConvertToIndainRS(grantReceiptInstance.projects.totAllAmount)}
                  			</strong>
              			</td>
                           <input type="hidden" id="balanceAmt" name="balanceAmt" value="${grantReceiptInstance?.balanceAmt}" />
                      </td>
                                 
                 </tr> 
            </table>
         </div>
 
         <div class="body">
            <h1><g:message code="default.GrantReceipt.CreateGrantReceipt.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantReceiptInstance}">
              <div class="errors">
                <g:renderErrors bean="${grantReceiptInstance}" as="list" />
              </div>
            </g:hasErrors>
            <g:form method="post" >
                <div class="dialog">
                    <table >
                        <tbody>
                        
                            <tr class="prop">
                            
                                <td valign="top" class="name">
                                    <label for="dateOfReceipt"><g:message code="default.ReceiptDate.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'dateOfReceipt','errors')}">
                                <calendar:datePicker name="dateOfReceipt" defaultValue="${new Date()}" value="${grantReceiptInstance?.dateOfReceipt}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            
                                <td valign="top" class="name">
                                    <label for="referenceId"><g:message code="default.FundsReceivedOrderNo.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'referenceId','errors')}">
                                    <input type="text" id="referenceId" name="referenceId" value="${fieldValue(bean:grantReceiptInstance,field:'referenceId')}" style="text-align: right" />
                                </td>
                                
                            </tr> 
                            
                            <tr class="prop">
                            
                                <td valign="top" class="name">
                                    <label for="grantAllocation"><g:message code="default.GrantAllocation.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(grantReceiptInstance,field:'grantAllocation','errors')}">
                                    <g:select optionKey="id" optionValue="grantCode" from="${grantAllocationInstanceList}" name="grantAllocation.id" value="${grantReceiptInstance?.grantAllocation?.id}" noSelection="['null':'Select']"></g:select>
                                </td>
                            
                                <td valign="top" class="name">
			                        <label for="grantAllocationSplit"><g:message code="default.AccountHeads.label"/></label>
			                    </td>
			                    <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'grantAllocationSplit','errors')}">
			                        <g:select optionKey="id" optionValue="accHeadPeriod"  from="${accountHeadList}" name="grantAllocationSplit.id" value="${grantReceiptInstance?.grantAllocationSplit?.id}" noSelection="['null':'select']"></g:select>
			                    </td>
			                    
			                </tr> 
                      
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="default.Amount.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'amount','errors')}">
                                    <input type="text" id="amount" name="amount" value="${grantReceiptInstance.amount}" style="text-align: right" />
                                       <input type="hidden" id="projectId" name="projectId" value="${fieldValue(bean:grantReceiptInstance.projects, field:'id')}"/>
                                </td>
                         
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
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'ddDate','errors')}">
                                <calendar:datePicker name="ddDate" defaultValue="${new Date()}" value="${grantReceiptInstance?.ddDate}" dateFormat= "%d/%m/%Y"/>
                                </td>
 
                                <td valign="top" class="name">
                                    <label for="ddNo"><g:message code="default.DD/ChequeNo.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'ddNo','errors')}">
                                    <input type="text" id="ddNo" name="ddNo" value="${fieldValue(bean:grantReceiptInstance,field:'ddNo')}" style="text-align: right" />
                                </td>
                            
                            </tr>
                            
                            <tr class="prop">

                                <td valign="top" class="name">
                                    <label for="bankName"><g:message code="default.BankName.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'bankName','errors')}">
                                    <input type="text" id="bankName" name="bankName" value="${fieldValue(bean:grantReceiptInstance,field:'bankName')}" style="text-align: right" />
                                </td>
 
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
                    <span class="button"><g:actionSubmit class="save" action="save" value="${message(code: 'default.Create.button')}" onClick="return validateGrantReceipt()"  /></span>
                    <span class="button"><span class="button"><g:actionSubmit class="delete" action="clear" value="${message(code: 'default.Clear.button')}" /></span></span> 
                </div>
            </g:form>
         </div>

         <div class="body">
            <div id="ss" class="list" style="overflow:auto ;height:160px; width:100%">
               <h1><g:message code="default.GrantReceipt.GrantReceiptList.head"/></h1>
               <table cellspacing="0">
                  <thead>
                       <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                   	        <g:sortableColumn property="dateOfReceipt" title="${message(code: 'default.ReceiptDate.label')}" />
                            <g:sortableColumn property="grantAllocationSplit.accountHead.code" title="${message(code: 'default.AccountHeads.label')}" />
                   	        <g:sortableColumn property="referenceId" title="${message(code: 'default.FundsReceivedOrderNo.label')}" />
                   	        <g:sortableColumn property="amount" title="${message(code: 'default.Amount.label')}" />
                   	        <g:sortableColumn property="modeOfPayment" title="${message(code: 'default.ModeOfPayment.label')}" />
                   	        <g:sortableColumn property="ddNo" title="${message(code: 'default.DD/ChequeNo.label')}" />
                   	        <g:sortableColumn property="ddDate" title="${message(code: 'default.DD/ChequeDate.label')}" />
                   	        <g:sortableColumn property="bankName" title="${message(code: 'default.BankName.label')}" />
                   	        <g:sortableColumn property="ddBranch" title="${message(code: 'default.Branch.label')}" />
                   	        <th><g:message code="default.Edit.label"/></th>
                   	        
                       </tr>
                  </thead>
                  <tbody>
                    <% int j=0 %>
                    <g:each in="${grantReceiptInstanceList}" status="i" var="grantReceiptListInstance" >
                      <% j++ %>
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                 
                            <td>${j}</td>
                            <td><g:formatDate format="dd/MM/yyyy" date="${grantReceiptListInstance.dateOfReceipt}"/></td>
                            <td>${fieldValue(bean:grantReceiptListInstance, field:'grantAllocationSplit.accountHead.code')}</td>                                               
                            <td>${fieldValue(bean:grantReceiptListInstance, field:'referenceId')}</td>                                               
                            <td>${currencyFormat.ConvertToIndainRS(grantReceiptListInstance.amount)}</td>
                            <td>${fieldValue(bean:grantReceiptListInstance, field:'modeOfPayment')}</td>
                            <td>${fieldValue(bean:grantReceiptListInstance, field:'ddNo')}</td>
                            <td><g:formatDate format="dd/MM/yyyy" date="${grantReceiptListInstance.ddDate}"/></td> 
                            <td>${fieldValue(bean:grantReceiptListInstance, field:'bankName')}</td>
                            <td>${fieldValue(bean:grantReceiptListInstance, field:'ddBranch')}</td>
                            <td><g:link action="edit" id="${grantReceiptListInstance.id}"><g:message code="default.Edit.label"/></g:link></td>
                        
                        </tr>
                    </g:each>
                  </tbody>
               </table>
            </div>
	     </div>
      </div>
    </body>
</html>
