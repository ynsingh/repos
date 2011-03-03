 <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="default.ApprovalStatus.label"/></title>
    </head>
    <body>
    	<div class="wrapper"> 
	    	 
	     	<div class="body">
	     		<h1><g:message code="default.ApprovalStatus.label"/></h1> 
	     		 <div class="dialog">
	     		 <g:if test="${proposalApprovalAuthorityMapInstanceList}">
	                    <table>
	                        <tbody>
	                        	<tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="projects"><g:message code="default.Project.label" />:</label>
	                                </td>
	                                <td>${expenseRequestEntryInstance.projects.name}</td>
                                </tr>
                                <tr>
                                	<td valign="top" class="name">
	                                    <label for="expenseDescription"><g:message code="default.ExpenseDescription.label" />:</label>
	                                </td>
	                                <td>${expenseRequestEntryInstance.expenseDescription}</td>
	                            </tr>   
                             </tbody>
	                      </table>
	                	  <div class="list">
	     		 		     <table cellspacing="0" cellpadding="0">
		                     	<thead>
		                        	<tr>
			                        	<th><g:message code="default.SINo.label"/></th>
			                   	        <th><g:message code="default.ApprovalAuthority.label"/></th>
		                   	        </tr>
		                    	</thead>
		                    	<tbody>
			                   		<% int k=0 %>
			                   		<% int l=0 %>
			                    	<g:each in="${proposalApprovalAuthorityMapInstanceList}" status="i" var="expenseRequestApprovalInstance">
				                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				                        	<td>${i+1}</td>
			                    			<td><b>${approvalAuthorityInstanceList[i].name}</b></td>
			                    		</tr>
		                         		<g:each var="j" in="${ (0..<sizeList[i]) }">
			                         		<tr>
			                         			<td></td>
		                         				<g:if test="${proposalApprovalDetailInstanceList[k] == null}">
		                         					<td>&nbsp;&nbsp;&nbsp;&nbsp;${approvalAuthorityDetailList[l].person.userRealName}&nbsp;-&nbsp;<g:message code="default.Pending.label"/></td>
		                         					<%  l++ %>
		                         				</g:if>	
		                         				<g:else>
		                         					<td>&nbsp;&nbsp;&nbsp;&nbsp;${proposalApprovalDetailInstanceList[k].proposalApproval.approvalAuthorityDetail.person.userRealName}&nbsp;-&nbsp;${proposalApprovalDetailInstanceList[k].proposalStatus}</td>
				                    			</g:else>
	                         				</tr>
		                         			<%  k++ %>
		                         		 </g:each>
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
     	</div>
    </body>
</html>