


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalApproval.label', default: 'ProposalApproval')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalApprovalInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalApprovalInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${proposalApprovalInstance?.id}" />
                <g:hiddenField name="version" value="${proposalApprovalInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="approvalAuthorityDetail"><g:message code="proposalApproval.approvalAuthorityDetail.label" default="Approval Authority Detail" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalInstance, field: 'approvalAuthorityDetail', 'errors')}">
                                    <g:select name="approvalAuthorityDetail.id" from="${ApprovalAuthorityDetail.list()}" optionKey="id" value="${proposalApprovalInstance?.approvalAuthorityDetail?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="proposalApprovalAuthorityMap"><g:message code="proposalApproval.proposalApprovalAuthorityMap.label" default="Proposal Approval Authority Map" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalInstance, field: 'proposalApprovalAuthorityMap', 'errors')}">
                                    <g:select name="proposalApprovalAuthorityMap.id" from="${ProposalApprovalAuthorityMap.list()}" optionKey="id" value="${proposalApprovalInstance?.proposalApprovalAuthorityMap?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
