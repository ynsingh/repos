

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
     </head>
    <body>
    <g:javascript library="application"/>
    <modalbox:modalIncludes/>
    <div class="wrapper">
     
      <div class="body">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
            <h1><g:message code="default.assignedBudgetDetail.label"/><h1>
        	<g:if test="${budgetMasterInstanceList}">
        	   <table>
                 <thead>
                    <tr>
                       <th><g:message code="default.budget.label"/></th>
	                   
	                   <th><g:message code="default.totalBudgetAmount(Rs).label"/></th>
	                </tr>
	              </thead>
               <tbody>
                   
	          <g:each in="${budgetMasterInstanceList}" status="i" var="budgetMasterInstance">
	            <tr>
	        	   <td>${budgetMasterInstanceList[i].title}</td>
	            	   
	               <td>${currencyFormat.ConvertToIndainRS(budgetMasterInstanceList[i].totalBudgetAmount)}</td>
	            </tr>
	         </g:each>
  			 </tbody>
  		    </table>
             </g:if>
             <div class="list">
        	 <g:if test="${budgetDetInstanceList}">
              <table>
                    <thead>
                        <tr>
                           <th><g:message code="default.SINo.label" /></th>
                           
                           <th><g:message code="default.budget.label"/></th>
                           
                           <th><g:message code="default.budgetDetailsAccountHead.label" /></th>
							
						   <th><g:message code="default.budgetDetails.allocatedAmount(Rs).label" /></th>
                        </tr>
                    </thead>
                    <tbody>
                   
                    <g:each in="${budgetDetInstanceList}" status="i" var="budgetDetailsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                           <td>${i+1}</td>
                           
                           <td>${budgetDetailsInstance.budgetMaster.title}</td>
                           
                           <td>${fieldValue(bean: budgetDetailsInstance, field: "accountHeads.code")}</td>
                                                      
                           <td>${currencyFormat.ConvertToIndainRS(budgetDetailsInstance.allocatedAmount)}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
           </g:if>
           <g:else>
    		<td><g:message code="default.NoDetails.label"/></td>
      	   </g:else>
           </div> 
       </div>         
      </div>
    </div>
    </body>
</html>
                    
                    
               