

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
<td>
      	<img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">  
</td>
             </tr> 
               
      	   </table>   
        </div>
        <div class="body">
        <h1><g:message code="default.HeadwiseExpense.head"/></h1> 
        <g:if test="${flash.message}">
                    <div class="message">${flash.message}</div>
                 </g:if>
                 <g:hasErrors bean="${grantExpenseInstance}">
                   <div class="errors">
                     <g:renderErrors bean="${grantExpenseInstance}" as="list" />
                   </div>
                 </g:hasErrors>
                
        <g:form action="listHeadwiseExpenses" method="post" >
        	<div class="dialog">
        	   	<table>
                	<tbody>
                		<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="accountHeads"><g:message code="default.AccountHeads.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'grantAllocationSplit','errors')}"> 
                                             <g:select optionKey="id" optionValue="name" from="${accountHeadList}" noSelection="['':'-All-']" name="name" value="${grantExpenseInstance?.grantAllocationSplit?.id}" ></g:select> 
                                         </td>
                                     </tr> 
                                     </tbody>
                    </table>
                </div>
             
                    <input class="inputbutton" name="listExpenses"  type="submit" value="${message(code: 'default.ListExpenses.button')}" />
           
            </g:form>
        </div>
                        
          <div class="body">                
                          
          <div class="list">
            
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
                      <td>${accHeadOfGSplit[i]}</td>
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
