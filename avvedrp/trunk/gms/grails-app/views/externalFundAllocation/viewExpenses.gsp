


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:set var="entityName" value="${message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation')}" />
<title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
  <div class="wrapper"> 
     <g:subMenuList/>
<div class="body">
<h1><g:message code="default.ListExpenses.ExpenseList.head" /></h1>
<g:if test="${flash.message}">
<div class="message">${flash.message}</div>
</g:if>
<g:hasErrors bean="${externalFundAllocationInstance}">
<div class="errors">
<g:renderErrors bean="${externalFundAllocationInstance}" as="list" />
</div>
</g:hasErrors>
          <div class="list">
            <g:if test="${grantExpenseInstanceList}">
                <table>
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
                                         <td>${fieldValue(bean:grantExpenseInstance, field:'expenseAmount')}</td>
                                        <td>${fieldValue(bean:grantExpenseInstance, field:'description')}</td>
                                      </tr>
                    </g:each>
                    </tbody>
                </table>
           </g:if>
          <g:else>
			<br><g:message code="default.NoRecordsAvailable.label"/></br>
		  </g:else>
            </div>
</body>
</html>
