

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ListSummaryExpenses.GrantExpenseSummary.head"/></title>         
    </head>
    <body>
      <div class="wrapper">
      <g:subMenuList/> 
        <div class="proptable">
           <table width="100%" align="left">
        
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
        </div>
        <div class="body">
          <div class="list">
            <h1><g:message code="default.ListSummaryExpenses.SummaryOfExpenses.head"/></h1>
            <table cellspacing="0" cellpadding="0">
              <thead>
              
                 <tr>
                     <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
	                 <g:sortableColumn property="grantAllocationSplit.accountHead.code" title="${message(code: 'default.AccountHeads.label')}" />
	       	         <g:sortableColumn property="expenseAmount" title="${message(code: 'default.AllocatedAmount.label')}" />
	                 <g:sortableColumn property="expenseAmount" title="${message(code: 'default.ExpenseAmount(Rs).label')}" />
	                 <g:sortableColumn property="balanceAmount" title="${message(code: 'default.BalanceAmount.label')}" />
                 </tr>
              </thead>
              <tbody>
                <% int j=0 %>
                <g:each in="${grantExpenseSummaryList}" status="i" var="grantExpenseInstance">
                  <% j++ %>
                  <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                      <td>${j}</td>
                      <td>${fieldValue(bean:grantExpenseInstance, field:'grantAllocationSplit.accountHead.code')}</td>
                      <td>${currencyFormat.ConvertToIndainRS(grantExpenseInstance.expenseAmount)}</td>
                      <td>${currencyFormat.ConvertToIndainRS((grantExpenseInstance.expenseAmount)-(grantExpenseInstance.balanceAmount))}</td>   
                      <td>${currencyFormat.ConvertToIndainRS(grantExpenseInstance.balanceAmount)}</td>
                  </tr>
                </g:each>
              </tbody>
            </table>
          </div> 
        </div>       
      </div>
    </body>
</html>
