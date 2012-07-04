<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="default.ExpenseRequestList.label"/></title>
    </head>
    <body>
    	<div class="wrapper"> 
	    	
			
            <div class="body">
            <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
	     		<h1><g:message code="default.ExpenseRequestList.label"/></h1>
	     		<g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${flash.error}">
	            <div class="errors">${flash.error}</div>
            </g:if> 
	     		 <div class="list">
	     		 	<g:if test="${expenseRequestEntryInstanceList}">
		                <table cellspacing="0" cellpadding="0">
		                    <thead>
		                        <tr>
		                        	<th><g:message code="default.SINo.label"/></th>
		                   	        <th><g:message code="default.Projects.label"/></th>
		                   	        <th><g:message code="default.ExpenseDate.label"/></th>
		                   	        <th><g:message code="default.ExpenseDescription.label"/></th>
		                   	        <th><g:message code="default.RequestedAmount.label"/></th>
		                   	        <th><g:message code="delault.RequestDetails.label"/></th>
		                   	        <th><g:message code="default.ApprovalStatus.label"/></th>
		                   	        <th><g:message code="default.PaymentEntry.label"/></th>
		                   	        
		                        </tr>
		                    </thead>
		                    <tbody>
		                    	<g:each in="${expenseRequestEntryInstanceList}" status="i" var="expenseRequestInstance">
				                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				                        
				                            <td>${i+1}</td>
				                    		<td>${fieldValue(bean: expenseRequestInstance, field: "projects.code")}</td>
				                    		<td><g:formatDate format="dd MMM yyyy" date="${expenseRequestInstance.dateOfExpense}"/></td>
				                    		<td>${expenseRequestInstance.expenseDescription}</td>
				                    		<td>${currencyFormat.ConvertToIndainRS(expenseRequestInstance.expenseAmount)}</td>
		                         			<td>
					                        	<g:link action="expenseRequestDetails" id="${expenseRequestInstance.id}">
					                     			<g:message code="default.View.label"/> 
					                     		</g:link>
					                 		</td>
					                 		
					                 		<td>
					                        	<g:link params="[financeSide:'financeSide']" action="approvalStatus" id="${expenseRequestInstance.id}">
					                     			<g:if test="${expenseRequestInstance.status != 'Pending'}">
					                     				${expenseRequestInstance.status}
				                     				</g:if>
				                     				<g:else>
				                     					<g:if test="${proposalApprovalAuthorityMapInstanceList[i] != null}">
					                     					<g:message code="default.PendingAt.label"/> ${proposalApprovalAuthorityMapInstanceList[i].approvalAuthority.name}
				                     					</g:if>
				                     					<g:else>
				                     						<g:message code="default.RequestNotSent.message"/>
				                     					</g:else>
			                     					</g:else>
					                     		</g:link>
					                 		</td>
					                 		
					                 		<td>
					                 		<g:if test="${expenseRequestInstance.status == 'Approved'}">
					                        	<g:link action="expenseEntry" id="${expenseRequestInstance.id}">
					                     			<g:message code="default.Record.label"/> 
					                     		</g:link>
				                     		 </g:if>
				                     		 <g:else>
				                     		 	<g:message code="default.Record.label"/>
				                     		 </g:else>
					                 		</td>
		                         		</tr>
			                    </g:each>                  
		                    </tbody>
		                </table>
	                </g:if>
					<g:else>
						<br><g:message code="default.NoRecordsAvailable.label"/></br>
					</g:else>
	            </div>
	     	</div>
     	</div>
     </body>
</html>