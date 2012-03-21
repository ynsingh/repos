<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fundAdvance.label', default: 'FundAdvance')}" />
        <title><g:message code="default.Closed.label"/></title>
    </head>
    <body>
        <div class="wrapper"> 
	        <g:subMenuList/>  
	        
	        <div class="body">
	            <h1><g:message code="default.AdvanceClosure.label"/></h1>
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:if test="${flash.error}">
	            	<div class="errors">${flash.error}</div>
            	</g:if>
	            <g:hasErrors bean="${fundAdvanceInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${fundAdvanceInstance}" as="list" />
	            </div>
	            </g:hasErrors>
	            <g:form method="post">
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        
	                        	<tr class="prop">
	                        		<td valign="top" class="name">
	                                    <label for="advanceDescription"><g:message code="default.advanceDescription.label"/></label>:
	                                    <g:hiddenField name="advanceId" value="${fundAdvanceInstance.id}" />
	                               	</td>
	                                <td>${fundAdvanceInstance.advanceDescription}</td>
	                                <td></td>
	                                <td></td>
                                </tr>
                                </br>
                                <tr class="prop">
	                                <td valign="top" class="name">
	                                     <label for="dateOfAdvance"><g:message code="default.dateOfAdvance.label"/></label>: 
                                     </td>
                                     <td> 
                                     	<g:formatDate date="${fundAdvanceInstance.dateOfAdvance}" 
			                    							format="dd/MMM/yyyy"/> 
                                        
                                    </td>
	                                
	                                <td valign="top" class="name">
	                                    <label for="advanceAmount"><g:message code="default.advanceAmount.label"/></label> :
	                                </td>
	                                <td> 
                                    ${currencyFormat.ConvertToIndainRS(fundAdvanceInstance.advanceAmount)}
                                    </td>
	                                
	                            </tr>
	                            </br>
	                            <tr class="prop">
	                                <td valign="top" class="name"> 
                                         <label for="totalExpense"><g:message code="default.TotalExpense.label"/></label>: 
                                         
                                     </td>
                                     <td> ${currencyFormat.ConvertToIndainRS(totalExpense)}
                                     </td>
                             		
	                                <td valign="top" class="name"> 
                                         <label for="fundRemaining"><g:message code="default.FundRemaining.label"/></label>: 
                                        
                                     </td>
                                     
                                     <td> 
	                                     <g:if test="${fundAdvanceInstance.status == 'Closed'}">
	                                     0.00
	                                     </g:if>	
		     							 <g:else>
	                                          ${currencyFormat.ConvertToIndainRS(fundAdvanceInstance.advanceAmount - totalExpense)}
	                                     </g:else>
                                     </td>
	                            </tr>
	                            
	                            <tr class="prop">
	                            <td valign="top" class="name"> 
                                         <label for="amountRefunded"><g:message code="default.AmountRefunded.label"/></label>: 
                                        
                                     </td>
	                            	<td> 
	                                     <g:if test="${fundAdvanceInstance.status == 'Closed'}">
	                                     ${currencyFormat.ConvertToIndainRS(fundAdvanceInstance.advanceAmount - totalExpense)}
	                                     </g:if>	
		     							 <g:else>
	                                          0.00
	                                     </g:else>
                                     </td>
	                            </tr>
	                            
	                        </tbody>
	                    </table>
	                </div>
	                <g:if test="${fundAdvanceInstance.status == 'Closed'}">
			        	<g:message code="default.AlreadyClosed.message"/>
                 	</g:if>	
	     			<g:else>
	     					<div class="buttons">
		                    <span class="button"><g:actionSubmit name="closeAdvance"  value="${message(code: 'default.CloseAdvance.label')}" onClick="return confirm('${message(code: 'default.button.closeAdvance.confirm.message', default: 'Are you sure?')}');" action="closeAdvance"/> </span>
		                </div>
		            </g:else>
	                
	            </g:form>
	        
	    </div>
    </body>
</html>
