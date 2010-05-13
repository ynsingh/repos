

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit GrantReceipt</title>
    </head>
     <script>
    function validate()
    {
     
	    if((document.getElementById("amount").value)=='')
	    {
		    alert("Please enter Proper Amount  ");
		    return false;
	    }
     
	    if((document.getElementById("amount").value)=='')
	    {
		    alert("Please enter Proper Amount  ");
		    return false;
	    }
    	if(isNaN(document.getElementById("amount").value))
	    {
		    alert("Invalid Amount  ");
		    document.getElementById("amount").select();
		    return false;
	    }
	    if(eval(document.getElementById("amount").value)<=0)
	    {
		    alert("Please enter Proper Amount  ");
		    return false;
		}
		if(parseFloat(document.getElementById("amount").value) > parseFloat(document.getElementById("balanceAmt").value))
		{
	   		alert("Please Enter Amount Less Than Or Equal To Balance Amount("+document.getElementById("balanceAmt").value+")");
	   		document.getElementById("amount").focus();
	    	return false;
		}
		    
    
    }
     </script>
    <body>
         <div class="wrapper"> 
            <div class="nav">
           <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
           <span class="menuButton"><a class="list"  href="../../grantAllocation/projectDash/${grantReceiptInstance.projects.id}">Project List</a></span>
           <span class="menuButton"><g:link class="create" action="create" id="${grantReceiptInstance.projects.id}">Grant Receipt List</g:link></span>
           
        </div>
        <div class="body">
            <h1>Edit Grant Receipt</h1>
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
                                    <label for="dateOfReceipt">Date Of Receipt:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'dateOfReceipt','errors')}">
                                     <calendar:datePicker name="dateOfReceipt" defaultValue="${new Date()}" value="${grantReceiptInstance?.dateOfReceipt}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr> 
                          <tr class="prop">
			                                    <td valign="top" class="name">
			                                        <label for="grantAllocationSplit">Account Heads:</label>
			                                    </td>
			                                    <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'grantAllocationSplit','errors')}">
			                                        <g:select optionKey="id" optionValue="accHeadPeriod"  from="${accountHeadList}" name="grantAllocationSplit.id" value="${grantReceiptInstance?.grantAllocationSplit?.id}" noSelection="['null':'select']"></g:select>
			                                    </td>
			                                </tr> 
			                                
			                                
			                 <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Funds received order No:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'referenceId','errors')}">
                                  <input type="text" id="referenceId" name="referenceId" value="${fieldValue(bean:grantReceiptInstance,field:'referenceId')}" style="text-align: right" />
                                </td>
                            </tr>                
                        
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount">Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'amount','errors')}">
                                    <input type="text" id="amount" name="amount" value="${fieldValue(bean:grantReceiptInstance,field:'amount')}" style="text-align: right" />
                                       <input type="hidden" id="grantAllocationID" name="grantAllocationID" value="${fieldValue(bean:grantReceiptInstance.grantAllocation, field:'id')}"/>
                                       <input type="hidden" name="balanceAmt" value="${grantReceiptInstance?.balanceAmt}" />
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modeOfPayment">Mode of Payment:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'modeOfPayment','errors')}">
                                    <g:select name="modeOfPayment" from="${['DD','Cheque','Cash' ,'BankTransfer']}"  value="${fieldValue(bean:grantReceiptInstance,field:'modeOfPayment')}" />
                                </td>
                            </tr>      
                    
              
               
                          <tr class="prop">

                                <td valign="top" class="name">
                                    <label for="ddNo">DD/Cheque No:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'ddNo','errors')}">
                                    <input type="text" id="ddNo" name="ddNo" value="${fieldValue(bean:grantReceiptInstance,field:'ddNo')}" style="text-align: right" />
                                </td>
                            </tr> 
                       
                        
                        
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ddDate">DD/Cheque Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'ddDate','errors')}">
                                <calendar:datePicker name="ddDate" defaultValue="${new Date()}" value="${grantReceiptInstance?.ddDate}" dateFormat= "%d/%m/%Y"/>
                                  
                                </td>
                            </tr> 
                            
                            <tr class="prop">

                                <td valign="top" class="name">
                                    <label for="bankName">Bank Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'bankName','errors')}">
                                    <input type="text" id="bankName" name="bankName" value="${fieldValue(bean:grantReceiptInstance,field:'bankName')}" style="text-align: right" />
                                </td>
                            </tr> 
                            
                            <tr class="prop">

                                <td valign="top" class="name">
                                    <label for="ddBranch">Branch:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'ddBranch','errors')}">
                                    <input type="text" id="ddBranch" name="ddBranch" value="${fieldValue(bean:grantReceiptInstance,field:'ddBranch')}" style="text-align: right" />
                                </td>
                            </tr> 
                           
                            
                        	
                        
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'description','errors')}">
                                  <g:textArea name="description" value="${fieldValue(bean:grantReceiptInstance,field:'description')}" rows="3" cols="30"/>
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
