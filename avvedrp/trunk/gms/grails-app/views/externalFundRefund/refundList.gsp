<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'externalFundRefund.label', default: 'ExternalFundRefund')}" />
        <title><g:message code="default.RefundList.head" /></title>
    </head>
    <body>
    <g:if test="${params.fund=='ExternalAgency'}">
            <g:subMenuList/>
     </g:if>
    	 <div class="wrapper"> 
    	        <div class="proptable">    
	            <table width="100%" align="left">
	              <tr>
	             	<td ><g:message code="default.ProjectCode.label"/>:</td>
		            <td><strong>${grantAllocationInstance.projects.code}</strong></td>
		            
	             	<td ><g:message code="default.RefundingStatus.label"/>:</td>
		            <td><strong>${externalFundAllocationInstance.status}</strong></td>
		             <input type=hidden name="fund"  value="${params.fund}">    
        		</tr> 
        		 
	          </table> 
      	 	</div> 
	        <div class="body">
	            <h1><g:message code="default.RefundList.head" /></h1>
	            <g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${externalFundRefundInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${externalFundRefundInstance}" as="list" />
	            </div>
	            </g:hasErrors>
	            <div class="list">
			        <g:if test="${RefundList}">
		                <table>
		                    <thead>
		                        <tr>
		                        
		                            <th><g:message code="default.SINo.label"/></th>
		                        
		                            <th><g:message code="default.DateOfRefund.label"/></th>
		                            
		                            <th><g:message code="default.RefundedAmount.label"/></th>
		                            
		                            <th><g:message code="default.Remarks.label"/></th>
		                            
	                            </tr>
	                    	</thead>
		                    <tbody>
			                    <g:each in="${RefundList}" status="i" var="externalRefundInstance">
			                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			                        	<td>${i+1}</td>
			                        	
			                        	<td><g:formatDate format="dd/MM/yyyy" date="${externalRefundInstance.dateOfRefund}"/></td>
			                        	
			                        	<td>${externalRefundInstance.refundAmount}</td>
			                        	
			                        	<td>${externalRefundInstance.remarks}</td>
			                        	
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
