


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap')}" />
        <title><g:message code="default.EditAssignProposalToApprovalAuthority.label" args="[entityName]" /></title>
    </head>
    <body>
     <div class="wrapper">
       
        <div class="body">
            <h1><g:message code="default.EditAssignProposalToApprovalAuthority.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalApprovalAuthorityMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalApprovalAuthorityMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${proposalApprovalAuthorityMapInstance?.id}" />
                <g:hiddenField name="version" value="${proposalApprovalAuthorityMapInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proposalType"><g:message code="default.ProposalType.label" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'proposalType', 'errors')}">
                                <strong>${proposalApprovalAuthorityMapInstance?.proposalType}</strong>
                                 <input type=hidden name="proposalType" id = "proposalType" value="${proposalApprovalAuthorityMapInstance.proposalType}">   
                                </td>
                           
                          
                                <td valign="top" class="name">
                                  <label for="proposalId"><g:message code="default.ProposalTitle.label" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'proposalId', 'errors')}">
                                    <strong>${projectTitleInstance?.projectTitle}</strong>
                                    <input type=hidden name="proposalId" id = "proposalId" value="${proposalApprovalAuthorityMapInstance.proposalId}">
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="approveOrder"><g:message code="default.ApproveOrder.label" />:</label>
                                  <label for="approveOrder" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'approveOrder', 'errors')}">
                                    <g:textField name="approveOrder" value="${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: 'approveOrder')}" />
                                </td>
                          
                                <td valign="top" class="name">
                                  <label for="processRestartOrder"><g:message code="default.ProcessRestartOrder.label" />:</label>
                                  <label for="processRestartOrder" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'processRestartOrder', 'errors')}">
                                    <g:textField name="processRestartOrder" value="${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: 'processRestartOrder')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                              
                                <td valign="top" class="name">
                                  <label for="remarks"><g:message code="default.Remarks.label" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'remarks', 'errors')}">
                                    <g:textArea name="remarks" value="${proposalApprovalAuthorityMapInstance?.remarks}" />
                                </td>
                                
                                 <td valign="top" class="name">
                                  <label for="approvalAuthority"><g:message code="default.ApprovalAuthority.label" />:</label>
                                  <label for="approvalAuthority" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'approvalAuthority', 'errors')}">
                                    <g:select name="approvalAuthority.id" from="${approvalAuthorityInstance}" optionKey="id" optionValue = "name" value="${proposalApprovalAuthorityMapInstance?.approvalAuthority?.id}"  />
                                </td>
                                
                            </tr>
                        
                            
                        </tbody>
                    </table>
                </div>
              
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update"  onClick="return validateEditApprovalAuthorityMap()"  value="${message(code: 'default.Update.button')}" /></span>
                  </div> 
            </g:form>
        </div>
        </div>
    </body>
</html>
