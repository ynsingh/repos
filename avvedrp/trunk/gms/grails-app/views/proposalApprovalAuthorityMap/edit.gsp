


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
     <div class="wrapper">
       
        <div class="body">
            <h1><g:message code="default.ProposalApprovalAuthorityMapEdit.label" args="[entityName]" /></h1>
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
                                    <label for="proposalType"><g:message code="proposalApprovalAuthorityMap.proposalType.label" default="Proposal Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'proposalType', 'errors')}">
                                <strong>${proposalApprovalAuthorityMapInstance?.proposalType}</strong>
                                    
                                </td>
                           
                          
                                <td valign="top" class="name">
                                  <label for="proposalId"><g:message code="proposalApprovalAuthorityMap.proposalId.label" default="Proposal Title" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'proposalId', 'errors')}">
                                    <strong>${preProposalInstance?.projectTitle} </strong>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="approveOrder"><g:message code="proposalApprovalAuthorityMap.approveOrder.label" default="Approve Order" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'approveOrder', 'errors')}">
                                    <g:textField name="approveOrder" value="${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: 'approveOrder')}" />
                                </td>
                          
                                <td valign="top" class="name">
                                  <label for="processRestartOrder"><g:message code="proposalApprovalAuthorityMap.processRestartOrder.label" default="Process Restart Order" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'processRestartOrder', 'errors')}">
                                    <g:textField name="processRestartOrder" value="${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: 'processRestartOrder')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="approvalAuthority"><g:message code="proposalApprovalAuthorityMap.approvalAuthority.label" default="Approval Authority" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'approvalAuthority', 'errors')}">
                                    <g:select name="approvalAuthority.id" from="${approvalAuthorityInstance}" optionKey="id" optionValue = "name" value="${proposalApprovalAuthorityMapInstance?.approvalAuthority?.id}"  />
                                </td>
                            
                                <td valign="top" class="name">
                                  <label for="activeYesNo"><g:message code="proposalApprovalAuthorityMap.activeYesNo.label" default="Active Yes No" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'activeYesNo', 'errors')}">
                                     <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(proposalApprovalAuthorityMapInstance,field:'activeYesNo')}" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="remarks"><g:message code="proposalApprovalAuthorityMap.remarks.label" default="Remarks" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'remarks', 'errors')}">
                                    <g:textArea name="remarks" value="${proposalApprovalAuthorityMapInstance?.remarks}" />
                                </td>
                            </tr>
                        
                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                   
            </g:form>
        </div>
    </body>
</html>
