<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fundTransfer.label', default: 'FundTransfer')}" />
        <title><g:message code="default.FundTransfer.head"/></title>
    </head>
    
    <body>
        <g:if test="${params.fund=='ExternalAgency'}">
            <g:subMenuList/>
     </g:if>
    	 <div class="body">
        <div class="wrapper">
            <h1><g:message code="default.FundTransfer.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${fundTransferInstance}">
            <div class="errors">
                <g:renderErrors bean="${fundTransferInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                     <table>
                        <tbody>
                                  
                     <tr class="prop">
						     <td>
							     <label for="party">
								 <g:message code="default.Grantor.label"/>:</label>
							 </td>
							 <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance?.party,field:'party','errors')}">
							     <strong>${grantAllocationInstance?.granter.code}</strong> 
								 <input type=hidden name="granter" id = "granter" value="${grantAllocationInstance?.granter.id}">
						     </td>
						     <td valign="top" class="name">
					             <label for="projects"><g:message code="externalFundAllocation.Projects.label" default="Projects" /></label>
					         </td>
				             <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance?.projects,field:'projects','errors')}">
					             <strong>${grantAllocationInstance.projects.code}</strong>
					             <input type=hidden name="projects" id = "projects" value="${grantAllocationInstance.projects.id}">
				             </td>
				     </tr>
				     <tr>
						     <td align="left" class="name">
		                     	<label for="Grantor"><g:message code="default.GrantAllocation.Recipient.label"/>:</label>
		                     </td>
		                     <td>
			                     <strong> ${grantAllocationInstance.party.code}  </strong>
			                     <input type="hidden" name="Recepient" id="Recepient" value="${grantAllocationInstance.party.id}"/>
		                     </td>
		                     <td valign="top" class="name">
							     <label for="amountAllocated"><g:message code="default.AmountAllocated(Rs).label"/>:</label>
							 </td>
							 <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance?.amountAllocated,field:'amountAllocated','errors')}">
							 <strong>${currencyFormat.ConvertToIndainRS(grantAllocationInstance.amountAllocated)}</strong> 
							 	<input type="hidden" id="amountAllocated" name="amountAllocated" value="${grantAllocationInstance?.amountAllocated}" style="text-align: right"/>
							 </td>
					 </tr> 
					  
					  <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="fundTransfer.amounttransferred.label" />:</label>
                                    <label for="amount" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fundTransferInstance, field: 'amount', 'errors')}">
                                    <g:textField id="amount" name="amount" value="${amount}" />
                                </td>
                                <td valign="top" class="name">
                                    <label for="dateOfTransfer"><g:message code="fundTransfer.dateOfTransfer.label" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fundTransferInstance, field: 'dateOfTransfer', 'errors')}">
                                	<calendar:datePicker name="dateOfTransfer" defaultValue="${new Date()}" value="${fundTransferInstance?.dateOfTransfer}" dateFormat= "%d/%m/%Y"/>
                                </td>
                                 <input type=hidden name="id" id = "id" value="${fundTransferInstance.id}">
                                  <input type=hidden name="grantAllocationId" id = "grantAllocationId" value="${grantAllocationInstance.id}">
                                  <input type=hidden name="fund"  value="${params.fund}">  
                      </tr> 
                                       
                                                               
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                	<span class="button"><g:actionSubmit class="save" action="updateTranferFund" onClick="return validateTransferFund()" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                </div>
                </div>
      
       </g:form>
        </div>
    </body>
</html>
                