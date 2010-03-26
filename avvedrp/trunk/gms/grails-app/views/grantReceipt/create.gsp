

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Grant Reception</title>         
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
	   		alert("Please Enter Amount Less Than Or Equal To Balance Amount");
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
          </div>
        <div class="proptable"> 
          <table>
                   <tr >
                               <td valign="top">
                                    <label for="project">Projects:</label>
                                </td>
                                <td valign="top" >
                                 <strong>  ${fieldValue(bean:grantReceiptInstance.projects,field:'code')} </strong>
                                    
                                    
                                  </td>
                                  
                                  
                                  <td valign="top" >
                                    <label for="party"> Institution :</label>
                                    <strong>  ${fieldValue(bean:grantReceiptInstance.grantAllocation.party,field:'code')} </strong>
                                </td>
                               
                            
                              <td valign="top" >
                                    <label for="party"> Allocated Amount :</label>
                                </td>
                                <td valign="top" >
                                    <strong>
                                    <g:formatNumber number="${grantReceiptInstance.projects.totAllAmount}" format="###,##0.00" />
                                     </strong>
                                     <input type="hidden" name="balanceAmt" value="${grantReceiptInstance?.balanceAmt}" />
                                   </td>
                                 
                            </tr> 
     </table>
   </div>
   
   
 <table class="tablewrapper" width="100%" cellspacing="0"  cellpadding="0">
  <tr>
 

    <td scope="col">
    <div class="body">
            <h1>Receive Grant</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantReceiptInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantReceiptInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table >
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
			                                        <g:select optionKey="id" optionValue="${{it.accountHead.code.toUpperCase()}}"  from="${GrantAllocationSplit.findAllByProjects(grantReceiptInstance.projects)}" name="grantAllocationSplit.id" value="${grantReceiptInstance?.grantAllocationSplit?.id}" noSelection="['null':'select']"></g:select>
			                                    </td>
			                                </tr> 
                        
                            <tr class="prop">
                                 <td valign="top" class="name">
                                    <label for="grantAllocation">Grant Allocation:</label>
                                   
                                </td>
                                <td valign="top" class="value ${hasErrors(grantReceiptInstance,field:'grantAllocation','errors')}">
                                    <g:select optionKey="id" optionValue="grantCode" from="${grantAllocationInstanceList}" name="grantAllocation.id" value="${grantReceiptInstance?.grantAllocation?.id}" noSelection="['null':'Select']"></g:select>
                                </td>
                            </tr> 

                           <tr class="prop">

                                <td valign="top" class="name">
                                    <label for="referenceId">Funds received order No:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'referenceId','errors')}">
                                    <input type="text" id="referenceId" name="referenceId" value="${fieldValue(bean:grantReceiptInstance,field:'referenceId')}" style="text-align: right" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount">Amount(Rs):</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantReceiptInstance,field:'amount','errors')}">
                                    <input type="text" id="amount" name="amount" value="${fieldValue(bean:grantReceiptInstance,field:'amount')}" style="text-align: right" />
                                       <input type="hidden" id="projectId" name="projectId" value="${fieldValue(bean:grantReceiptInstance.projects, field:'id')}"/>
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
                    <span class="button"><input class="save" type="submit" value="Create" onClick="return validate()" /></span>
                </div>
            </g:form>
        </div></td>
  </tr>
 
                            
   <tr>                       
                
     <td scope="row">

<table width="100%" cellspacing="0">
                            <tr>
                            <td>
                            <div id="ss" class="list" style="overflow:auto ;height:160px; width:100%">
         <h1>Received Grants</h1>
          <table cellspacing="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="SlNo" />
                   	          <g:sortableColumn property="dateOfReceipt" title="Receipt Date" />

                         <g:sortableColumn property="grantAllocationSplit.accountHead.code" title="Account Heads" />

                   	          <g:sortableColumn property="referenceId" title="Funds received order No" />
 	                            	    
                   	        <g:sortableColumn property="amount" title="Amount" />
                   	        <th>Edit</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantReceiptInstanceList}" status="i" var="grantReceiptListInstance" >
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                          
                            <td>${(i+1)}</td>
                            <td><g:formatDate format="dd/MM/yyyy" date="${grantReceiptListInstance.dateOfReceipt}"/></td>

                              <td>${fieldValue(bean:grantReceiptListInstance, field:'grantAllocationSplit.accountHead.code')}</td>                                               

                               <td>${fieldValue(bean:grantReceiptListInstance, field:'referenceId')}</td>                                               

                            <td><g:formatNumber number="${grantReceiptListInstance.amount}" format="###,##0.00" /></td>
                          <td><g:link action="edit" id="${grantReceiptListInstance.id}">Edit</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
           
             </td>
                
             </tr>
             </table>
         




</td>
  </tr>       
         
 </table>         
         
         
         
         
         
         
         
         
        
        </div>
    </body>
</html>
