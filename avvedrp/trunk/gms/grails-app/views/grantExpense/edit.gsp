

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Grant Expense</title>
        
   
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
             <span class="menuButton"><g:link class="create" action="create" id="${grantExpenseInstance.projects.id}">Grant Expense List</g:link></span>
            
        </div>
    <div class="proptable">    
        <table >
        	<tr class="prop">
               
                
                
                <td valign="top" >Project Code:</td>
                <td valign="top" ><strong>${fieldValue(bean:projectsInstance, field:'code')}</strong></td>
                
                
               
                
                
                <td valign="top" >Amount Allocated:</td>
                <td valign="top" ><strong><g:formatNumber number="${projectsInstance.totAllAmount}" format="###,##0.00" /></strong></td>
                    
            </tr> 
        </table> 
        </div>


<br>
  <div class="body">
        
            <h1>Edit Grant Expense</h1>
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
                                <label for="dateOfExpense">Date Of Expense:</label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'dateOfExpense','errors')}">
                                <calendar:datePicker name="dateOfExpense" defaultValue="${new Date()}" value="${grantExpenseInstance?.dateOfExpense}" dateFormat= "%d/%m/%Y"/>
                            </td>
                        </tr> 
                        
                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="grantAllocationSplit">Account Head:</label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'grantAllocationSplit','errors')}">
                            	<g:select optionKey="id" optionValue="accHeadPeriod" from="${accountHeadList}" noSelection="['null':'-Select-']" name="grantAllocationSplit.id" value="${grantExpenseInstance?.grantAllocationSplit?.id}" ></g:select>
                            </td>
                        </tr>  
                    
                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="expenseAmount">Expense Amount:</label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'expenseAmount','errors')}">
                                <input type="text" id="expenseAmount" name="expenseAmount" value="${fieldValue(bean:grantExpenseInstance,field:'expenseAmount')}" style="text-align: right" />
                                <g:hiddenField name="grantAllocation.id" value="${grantExpenseInstance?.grantAllocation?.id}" />
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
                            <td valign="top" class="name">
                                <label for="description">Description:</label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'description','errors')}">
                                <g:textArea name="description" value="${fieldValue(bean:grantExpenseInstance,field:'description')}" rows="3" cols="30"/>
                            </td>
                        </tr> 
                    
                    </tbody>
                </table>
                
                </div>
                
                
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" onClick="return validate()"  /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
        </div>
       
    </body>
</html>
