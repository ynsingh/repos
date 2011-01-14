


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalApprovalDetail.label', default: 'ProposalApprovalDetail')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalApprovalDetailInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalApprovalDetailInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proposalDetailId"><g:message code="proposalApprovalDetail.proposalDetailId.label" default="Proposal Detail Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalDetailInstance, field: 'proposalDetailId', 'errors')}">
                                    <g:textField name="proposalDetailId" value="${fieldValue(bean: proposalApprovalDetailInstance, field: 'proposalDetailId')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proposalStatus"><g:message code="proposalApprovalDetail.proposalStatus.label" default="Proposal Status" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalDetailInstance, field: 'proposalStatus', 'errors')}">
                                    <g:textField name="proposalStatus" value="${proposalApprovalDetailInstance?.proposalStatus}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="approvalDate"><g:message code="proposalApprovalDetail.approvalDate.label" default="Approval Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalDetailInstance, field: 'approvalDate', 'errors')}">
                                    <g:datePicker name="approvalDate" precision="day" value="${proposalApprovalDetailInstance?.approvalDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="moreComments"><g:message code="proposalApprovalDetail.moreComments.label" default="More Comments" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalDetailInstance, field: 'moreComments', 'errors')}">
                                    <g:textField name="moreComments" value="${proposalApprovalDetailInstance?.moreComments}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="proposalApprovalDetail.remarks.label" default="Remarks" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalDetailInstance, field: 'remarks', 'errors')}">
                                    <g:textField name="remarks" value="${proposalApprovalDetailInstance?.remarks}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo"><g:message code="proposalApprovalDetail.activeYesNo.label" default="Active Yes No" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalDetailInstance, field: 'activeYesNo', 'errors')}">
                                    <g:textField name="activeYesNo" value="${fieldValue(bean: proposalApprovalDetailInstance, field: 'activeYesNo')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proposalApproval"><g:message code="proposalApprovalDetail.proposalApproval.label" default="Proposal Approval" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalDetailInstance, field: 'proposalApproval', 'errors')}">
                                    <g:select name="proposalApproval.id" from="${ProposalApproval.list()}" optionKey="id" value="${proposalApprovalDetailInstance?.proposalApproval?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
