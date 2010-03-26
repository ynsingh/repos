

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Grant Expense Summary</title>         
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
             <span class="menuButton"><a class="list"  href="../../grantAllocation/projectDash/${projectsInstance.id}">Project List</a></span>
        </div>
        
        <table class="proptable">
        
        <tr >
            
            <td valign="top" >Project Code:</td>
            <td valign="top" ><strong>${fieldValue(bean:projectsInstance, field:'code')}</strong></td>
            
            
            <td valign="top" >Amount Allocated:</td>
            <td valign="top" ><strong><g:formatNumber number="${projectsInstance.totAllAmount}" format="###,##0.00" /></strong></td>
                    
        </tr> 
               
      	</table>   
        
        
        
        <div class="list">
        <h1>Summary of Expenses</h1>
            
        <table cellspacing="0" cellpadding="0">
            <thead>
                <tr>
                        
	       	        <g:sortableColumn property="id" title="SlNo" />
	       	        
	       	        <g:sortableColumn property="grantAllocationSplit.accountHead.code" title="Account Head" />
	       	        
	       	        <g:sortableColumn property="expenseAmount" title="Allocated Amount" />
	       	        
	       	        <g:sortableColumn property="expenseAmount" title="Expense Amount" />
	            
	       	        <g:sortableColumn property="balanceAmount" title="Balance Amount" />
                        
                </tr>
            </thead>
            <tbody>
                <g:each in="${grantExpenseSummaryList}" status="i" var="grantExpenseInstance">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                    	<td>${(i + 1)}</td>
                    	
                    	<td>${fieldValue(bean:grantExpenseInstance, field:'grantAllocationSplit.accountHead.code')}</td>
                    
                        <td><g:formatNumber number="${grantExpenseInstance.expenseAmount}" format="###,##0.00" /></td>
                            
                     	<td><g:formatNumber number="${(grantExpenseInstance.expenseAmount)-(grantExpenseInstance.balanceAmount)}" format="###,##0.00" /></td>
                        
                        <td><g:formatNumber number="${grantExpenseInstance.balanceAmount}" format="###,##0.00" /></td>
                        
                    </tr>
                </g:each>
            </tbody>
        </table>
         </div>       
        </div>
    </body>
</html>
