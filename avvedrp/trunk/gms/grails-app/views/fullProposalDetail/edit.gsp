


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fullProposalDetail.label', default: 'FullProposalDetail')}" />
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
            <g:hasErrors bean="${fullProposalDetailInstance}">
            <div class="errors">
                <g:renderErrors bean="${fullProposalDetailInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${fullProposalDetailInstance?.id}" />
                <g:hiddenField name="version" value="${fullProposalDetailInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fileName"><g:message code="fullProposalDetail.fileName.label" default="File Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalDetailInstance, field: 'fileName', 'errors')}">
                                    <g:textField name="fileName" value="${fullProposalDetailInstance?.fileName}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="comments"><g:message code="fullProposalDetail.comments.label" default="Comments" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalDetailInstance, field: 'comments', 'errors')}">
                                    <g:textField name="comments" value="${fullProposalDetailInstance?.comments}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="proposalSubmittedDate"><g:message code="fullProposalDetail.proposalSubmittedDate.label" default="Proposal Submitted Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalDetailInstance, field: 'proposalSubmittedDate', 'errors')}">
                                    <g:datePicker name="proposalSubmittedDate" precision="day" value="${fullProposalDetailInstance?.proposalSubmittedDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="activeYesNo"><g:message code="fullProposalDetail.activeYesNo.label" default="Active Yes No" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalDetailInstance, field: 'activeYesNo', 'errors')}">
                                    <g:textField name="activeYesNo" value="${fieldValue(bean: fullProposalDetailInstance, field: 'activeYesNo')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fullProposal"><g:message code="fullProposalDetail.fullProposal.label" default="Full Proposal" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalDetailInstance, field: 'fullProposal', 'errors')}">
                                    <g:select name="fullProposal.id" from="${FullProposal.list()}" optionKey="id" value="${fullProposalDetailInstance?.fullProposal?.id}"  />
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
