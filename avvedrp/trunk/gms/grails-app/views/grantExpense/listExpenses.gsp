

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>List Grant Expense</title>         
    </head>
    <body>
    <div class="wrapper"> 
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
           
             <g:if test="${params.id != null}">  
            <span class="menuButton"><a class="list"  href="../../grantAllocation/projectDash/${projectsInstance.id}">Project List</a></span>
            </g:if>
              <g:if test="${params.id == null}">  
            <span class="menuButton"><a class="list"  href="../grantAllocation/projectDash/${projectsInstance.id}">Project List</a></span>
            </g:if>
      
        </div>
        

       
       <div class="proptable">
        <table >
        
        <tr >
	        
                               
            <td valign="top" >Project Code:</td>
            <td valign="top" ><strong>${fieldValue(bean:projectsInstance, field:'code')}</strong></td>
            
            
            <td valign="top" >Amount Allocated:</td>
            <td ><strong>${currencyFormat.ConvertToIndainRS(projectsInstance.totAllAmount)}</strong></td>
            
                    
        </tr>  
      	</table>   
      	</div>
      	
 <table width="100%" class="tablewrapper"   cellspacing="0" cellpadding="0">
  <tr>
    <td scope="col">
 <div class="body">
        
            <h1>Expense List</h1>
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
                                <label for="dateOfExpense">Date Of Expense From:</label>
                            </td>
                            <td valign="top" >
                                <calendar:datePicker name="dateOfExpenseFrom" defaultValue="${new Date()}" value="" dateFormat= "%d/%m/%Y"/>
                                
                                 <g:hiddenField name="id" value="${grantExpenseInstance?.projects?.id}" /> 
                            </td>
                            <td valign="top" class="name">
                                <label for="dateOfExpense">Date Of Expense To:</label>
                            </td>
                            <td valign="top" >
                                <calendar:datePicker name="dateOfExpenseTo" defaultValue="${new Date()}" value="" dateFormat= "%d/%m/%Y"/>
                            </td>
                            <td valign="top" >
                          
                    <input class="inputbutton" name="listExpenses"  type="submit" value="List Expenses" />
                            </td>
                        </tr>
                        
                    </tbody>
                </table>
                
                </div>
                
                
            </g:form>
           
        </div> 
        

</td>
  </tr>
  <tr>
    <td scope="row">

<div class="list">
            <h1>Current Expenses</h1>
            
            <table cellspacing="0" cellpadding="0">
            	<thead>
            		<tr>
            			                        
               	        <th>SlNo</th>
                   	        
               	         <th>Date Of Expense</th>
               	        
               	       <th>Account Head</th>
               	        
               	        <th>Expense Amount</th>
                        
               	        <th>Description</th>
                   	       
                        
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${grantExpenseInstanceList}" status="i" var="grantExpenseInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td>${(i + 1)}</td>
                        	
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
         
       

 		
        
    </body>
</html>
