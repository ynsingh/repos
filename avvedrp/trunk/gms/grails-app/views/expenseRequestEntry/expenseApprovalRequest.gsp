<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="default.ExpenseApprovalRequestList.label"/></title>
    </head>
    <body>
    	<div class="wrapper"> 
	    	<div class="body">
	    		<h1><g:message code="default.ExpenseApprovalRequestList.label"/></h1>
     			<g:if test="${flash.message}">
            		<div class="message">${flash.message}</div>
            	</g:if>
            	<g:if test="${flash.error}">
	            	<div class="errors">${flash.error}</div>
            	</g:if>
	     			<div class="list">
	     				<g:if test="${proposalApprovalAuthorityMapInstanceList}">
		     		 		<table cellspacing="0" cellpadding="0">
			                    <thead>
			                        <tr>
			                        	<th><g:message code="default.SINo.label"/></th>
			                   	        <th><g:message code="default.Project.label"/></th>
			                   	        <th><g:message code="default.ApprovalAuthority.label"/></th>
			                   	        <th><g:message code="default.ExpenseDescription.label"/></th>
			                   	        <th><g:message code="default.Approve/Reject.label"/></th>
			                   	        
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<g:each in="${proposalApprovalAuthorityMapInstanceList}" status="i" var="proposalApprovalAuthorityMapInstance">
					                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
					                        	<td>${i+1}</td>
					                        	<td>${expenseRequestEntryInstanceList[i].projects.code}</td>
					                        						                        	
				                        		<td>${proposalApprovalAuthorityMapInstance.approvalAuthority.name}</td>
					                        	
					                        	<td>${expenseRequestEntryInstanceList[i].expenseDescription}</td>
					                    		<td> 
					                        	<g:link action="approveReject" id="${proposalApprovalAuthorityMapInstance.id}">
					                     			<g:message code="default.Approve/Reject.label"/> 
					                     		</g:link>
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