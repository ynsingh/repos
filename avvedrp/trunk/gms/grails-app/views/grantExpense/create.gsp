  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Grant Expense</title>         
    </head>
    
    <script>
   function validate()
    {
  
      if((document.getElementById("expenseAmount").value)=='')
    {
    alert("Please enter Proper Amount  ");
    return false;
    }
   
    if(isNaN(document.getElementById("expenseAmount").value))
    {
    alert("Invalid Amount  ");
    document.getElementById("expenseAmount").focus
    return false;
    }
     if(eval(document.getElementById("expenseAmount").value)<=0)
    {
    alert("Please enter Proper Amount  ");
    return false;
    }
    
    
    }
     </script>
    <body> 
    
    
    <div class="wrapper"> 
   
    	<div class="nav">
    	
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
          <g:if test="${params.size()<= 5}">  
            <span class="menuButton"><a class="list"  href="../../grantAllocation/projectDash/${projectsInstance.id}">Project List</a></span>
            </g:if>
              <g:if test="${params.size()> 5}">  
            <span class="menuButton"><a class="list"  href="../grantAllocation/projectDash/${projectsInstance.id}">Project List</a></span>
            </g:if>
        </div>
          <div class="proptable">    
        <table >
         <tr >
	        
	        <td >Project Code:</td>
	        <td><strong>${fieldValue(bean:projectsInstance, field:'code')}</strong></td>
	             <td >Amount Allocated:</td>
	        <td ><strong>${currencyFormat.ConvertToIndainRS(projectsInstance.totAllAmount)}</strong></td>
	        
        </tr> 
      	</table> 
      	</div>  
       <div>
            <g:hasErrors bean="${grantExpenseInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantExpenseInstance}" as="list" />
            </div>
            </g:hasErrors>
        </div>  

<table  class="tablewrapper" >
  <tr>
    <td  rowspan="2">

<table border="0" >
      <tr>
        <td rowspan="2"  class="newTable"> 
        <div > <h1>Expense Entry</h1>
          <g:if test="${flash.message}"> 
          <div class="message">${flash.message}</div>
          </g:if> <g:hasErrors bean="${grantExpenseInstance}"> 
          <div class="errors"> <g:renderErrors bean="${grantExpenseInstance}" as="list" /> 
          </div>
          </g:hasErrors> <g:form action="save" method="post" > 
          
          
          
          <div class="dialog"> 
            <table >
              <tbody>
                <tr class="prop"> 
                  <td valign="top" class="name"> <label for="dateOfExpense">Date 
                    Of Expense:</label> </td>
                  <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'dateOfExpense','errors')}"> 
                    <calendar:datePicker name="dateOfExpense" defaultValue="${new Date()}" value="${grantExpenseInstance?.dateOfExpense}" dateFormat= "%d/%m/%Y"/> 
                    <g:hiddenField name="projects.id" value="${grantExpenseInstance?.projects?.id}" /> 
                    <!--<g:hiddenField name="dateFrom" value="${grantExpenseInstance.dateFrom}" />
                                <g:hiddenField name="dateTo" value="${grantExpenseInstance.dateTo}" />-->
                  </td>
                </tr>
                 <tr class="prop">
                
                                 <td valign="top" class="name">
                                    <label for="grantAllocation">Grant Allocation:</label>
                                   
                                </td>
                                <td valign="top" class="value ${hasErrors(grantExpenseInstance,field:'grantAllocation','errors')}">
                                    <g:select optionKey="id" optionValue="grantCode" from="${grantAllocationInstanceList}" name="grantAllocation.id" value="${grantExpenseInstance?.grantAllocation?.id}" ></g:select>
                                </td>
                            </tr> 
                <tr class="prop"> 
                  <td valign="top" class="name"> <label for="grantAllocationSplit">Account 
                    Head:</label> </td>
                  <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'grantAllocationSplit','errors')}"> 
                    <g:select optionKey="id" optionValue="accHeadPeriod" from="${accountHeadList}" noSelection="['null':'-Select-']" name="grantAllocationSplit.id" value="${grantExpenseInstance?.grantAllocationSplit?.id}" ></g:select> 
                  </td>
                </tr>
                <tr class="prop"> 
                  <td valign="top" class="name"> <label for="expenseAmount">Expense 
                    Amount(Rs):</label> </td>
                  <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'expenseAmount','errors')}"> 
                    <input type="text" id="expenseAmount" name="expenseAmount" value="${fieldValue(bean:grantExpenseInstance,field:'expenseAmount')}" style="text-align: right" /> 
                  </td>
                </tr>
                
                <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modeOfPayment">Mode of Payment:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'modeOfPayment','errors')}">
                                    <g:select name="modeOfPayment" from="${['DD','Cheque','Cash' ,'BankTransfer']}"  value="${fieldValue(bean:grantExpenseInstance,field:'modeOfPayment')}" />
                                </td>
                            </tr>      
                    
              
               
                          <tr class="prop">

                                <td valign="top" class="name">
                                    <label for="ddNo">DD/Cheque No:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'ddNo','errors')}">
                                    <input type="text" id="ddNo" name="ddNo" value="${fieldValue(bean:grantExpenseInstance,field:'ddNo')}" style="text-align: right" />
                                </td>
                            </tr> 
                       
                        
                        
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ddDate">DD/Cheque Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'ddDate','errors')}">
                                <calendar:datePicker name="ddDate" defaultValue="${new Date()}" value="${grantExpenseInstance?.ddDate}" dateFormat= "%d/%m/%Y"/>
                                  
                                </td>
                            </tr> 
                            
                            <tr class="prop">

                                <td valign="top" class="name">
                                    <label for="bankName">Bank Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'bankName','errors')}">
                                    <input type="text" id="bankName" name="bankName" value="${fieldValue(bean:grantExpenseInstance,field:'bankName')}" style="text-align: right" />
                                </td>
                            </tr> 
                            
                            <tr class="prop">

                                <td valign="top" class="name">
                                    <label for="ddBranch">Branch:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'ddBranch','errors')}">
                                    <input type="text" id="ddBranch" name="ddBranch" value="${fieldValue(bean:grantExpenseInstance,field:'ddBranch')}" style="text-align: right" />
                                </td>
                            </tr> 
                           
                <tr class="prop"> 
                  <td valign="top" class="name"> <label for="description">Description:</label> 
                  </td>
                  <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'description','errors')}"> 
                    <g:textArea name="description" value="${fieldValue(bean:grantExpenseInstance,field:'description')}" rows="3" cols="30"/> 
                  </td>
                </tr>
              </tbody>
            </table>
        
        
          </div>
          
          
          <div class="buttons"> <span class="button">
            <input class="save" type="submit"  onClick="return validate()" value="Create" />
            </span> </div>
          </g:form> 
          
          
          </td>
          
          
        <td width="51%" height="50%"  class="newTable"> 
        <g:form action="create" method="post" > 
          <div class="dialog"> 
            <table >
            
              <h1>Period Wise Expenses</h1>
              <tbody>
                <tr class="prop"> 
                  <td class="name"> <label for="dateOfExpense">Date From:</label> 
                  </td>
                  <td> <calendar:datePicker name="dateFrom" defaultValue="${new Date()}" value="${grantExpenseInstance.dateFrom}" dateFormat= "%d/%m/%Y"/> 
                    <g:hiddenField name="id" value="${grantExpenseInstance?.projects?.id}" /> 
                  </td>
                </tr>
                <tr> 
                  <td  class="name"> <label for="dateOfExpense">Date To:</label> 
                  </td>
                  <td  > <calendar:datePicker name="dateTo" defaultValue="${new Date()}" value="${grantExpenseInstance.dateTo}" dateFormat= "%d/%m/%Y"/> 
                  </td>
                  <td> <input class="inputbutton" name="create" type="submit" value="List" /> 
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          </g:form> </td>
      </tr>
        
        
        
      <tr>
        <td>
        <g:if test="${grantExpenseInstanceList}">
        
        <div id="ss" class="list" style="overflow:auto ;height:150px; width:100%">
        <table width="100%" height="" cellspacing="0">
            <thead>
              <tr> 
                                      
               	        <th>SlNo</th>
                   	        
               	         <th>Date Of Expense</th>
               	        
               	       <th>Account Head</th>
               	        
               	        <th>Expense Amount</th>
                        
               	        <th>Description</th>
                
                 <th>Edit</th>
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
                <td><g:link action="edit" id="${fieldValue(bean:grantExpenseInstance, field:'id')}">Edit</g:link></td>
              </tr>
              </g:each> 
            </tbody>
          </table></div>
          </g:if>
          </td>
      </tr>
      
      <tr> 
        <td height="200" colspan="2"> <div class="list" align="center"> 
            <h1>Account Head Wise Expense Summary</h1>
            
            <table width="100%" cellspacing="0" >
            <g:if test="${grantExpenseSummaryList}">
              <thead >
                <tr> <g:sortableColumn property="id" title="SlNo" /> 
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
                
                  <td>${grantExpenseInstance.accountHeadCode}</td>
                  <td>${currencyFormat.ConvertToIndainRS((grantExpenseInstance.expenseAmount)+(grantExpenseInstance.balanceAmount))}</td>
                  <td>${currencyFormat.ConvertToIndainRS(grantExpenseInstance.expenseAmount)}</td>
                  <td>${currencyFormat.ConvertToIndainRS(grantExpenseInstance.balanceAmount)}</td>
                  
                </tr>
                </g:each> 
              </tbody>
              </g:if>
            <g:else>
            No Records Available
            </g:else>
            </table>
            
          </div></td>
      </tr>
    </table>
          
          
     </div>     
    </body>
</html>
