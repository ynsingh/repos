

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ListExpenses.ListGrantExpense.head"/></title>         
    </head>
    <body>
      <div class="wrapper"> 
      <g:subMenuList/>       
        <div class="body">
          <table>
          
            <tr>
	           <td valign="top" ><g:message code="default.ProjectCode.label"/>:</td>
               <td valign="top" ><strong>${fieldValue(bean:projectsInstance, field:'code')}</strong></td>
               <td valign="top" ><g:message code="default.AmountAllocated.label"/>:</td>
               <td><strong>
               			<g:message code="default.Rs.label" />
               			${currencyFormat.ConvertToIndainRS(projectsInstance.totAllAmount)}
           			</strong>
       			</td>
            </tr>
              
      	  </table>   
          <table width="100%" class="tablewrapper"   cellspacing="0" cellpadding="0">
            <tr>
               <td scope="col">
                 <h1><g:message code="default.ListExpenses.ExpenseList.head"/></h1>
                 <g:if test="${flash.message}">
                    <div class="message">${flash.message}</div>
                 </g:if>
                 <g:hasErrors bean="${grantExpenseInstance}">
                   <div class="errors">
                     <g:renderErrors bean="${grantExpenseInstance}" as="list" />
                   </div>
                 </g:hasErrors>
                 <g:form action="listExpenses" method="post" >
                    <div class="dialog">
                       <table>
                	     <tbody>
                	
                          <tr>
                             <td valign="top" class="name">
                                <label for="dateOfExpense"><g:message code="default.DateOfExpenseFrom.label"/>:</label>
                             </td>
                             <td valign="top" >
                                <calendar:datePicker name="dateOfExpenseFrom" defaultValue="${new Date()}" value="" dateFormat= "%d/%m/%Y"/>
                                
                                 <g:hiddenField name="id" value="${grantExpenseInstance?.projects?.id}" /> 
                             </td>
                             <td valign="top" class="name">
                                <label for="dateOfExpense"><g:message code="default.DateOfExpenseTo.label"/>:</label>
                             </td>
                             <td valign="top" >
                                <calendar:datePicker name="dateOfExpenseTo" defaultValue="${new Date()}" value="" dateFormat= "%d/%m/%Y"/>
                             </td>
                             <td valign="top" >
                                <input class="inputbutton" name="listExpenses"  type="submit" value="${message(code: 'default.ListExpenses.button')}" />
                            </td>
                          </tr>
                        
                         </tbody>
                       </table>
                    </div>
                 </g:form>
               </td>
            </tr>
            
            <tr>
              <td scope="row">
                <div class="list">
                  <h1><g:message code="default.ListExpenses.CurrentExpenses.head"/></h1>
                  <table cellspacing="0" cellpadding="0">
            	   <thead>
            		 <tr>
            			                        
               	        <th><g:message code="default.SINo.label"/></th>
                   	    <th><g:message code="default.ExpenseDate.label"/></th>
               	        <th><g:message code="default.AccountHeads.label"/></th>
               	        <th><g:message code="default.ExpenseAmount(Rs).label"/></th>
                        <th><g:message code="default.Description.label"/></th>
                   	       
                     </tr>
                   </thead>
                   <tbody>
                   <% int j=0 %>
                    <g:each in="${grantExpenseInstanceList}" status="i" var="grantExpenseInstance">
                      <% j++ %>
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                          <td>${j}</td>
                          <td><g:formatDate format="dd/MM/yyyy" date="${grantExpenseInstance.dateOfExpense}"/></td>
                          <td>${fieldValue(bean:grantExpenseInstance, field:'grantAllocationSplit.accountHead.code')}</td>
                          <td>${currencyFormat.ConvertToIndainRS(grantExpenseInstance.expenseAmount)}</td>
                          <td>${fieldValue(bean:grantExpenseInstance, field:'description')}</td>
                        </tr>
                    </g:each>
                   </tbody>
                  </table>
                </div>
              </td>
            </tr>
          </table>     	
        </div>     
      </div> 
    </body>
</html>
