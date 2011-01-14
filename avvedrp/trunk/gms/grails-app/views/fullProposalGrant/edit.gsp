


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fullProposalGrant.label', default: 'FullProposalGrant')}" />
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
            <g:hasErrors bean="${fullProposalGrantInstance}">
            <div class="errors">
                <g:renderErrors bean="${fullProposalGrantInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${fullProposalGrantInstance?.id}" />
                <g:hiddenField name="version" value="${fullProposalGrantInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="grantAgency"><g:message code="fullProposalGrant.grantAgency.label" default="Grant Agency" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalGrantInstance, field: 'grantAgency', 'errors')}">
                                    <g:textField name="grantAgency" value="${fullProposalGrantInstance?.grantAgency}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="grantName"><g:message code="fullProposalGrant.grantName.label" default="Grant Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalGrantInstance, field: 'grantName', 'errors')}">
                                    <g:textField name="grantName" value="${fullProposalGrantInstance?.grantName}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="appliedDate"><g:message code="fullProposalGrant.appliedDate.label" default="Applied Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalGrantInstance, field: 'appliedDate', 'errors')}">
                                    <g:datePicker name="appliedDate" precision="day" value="${fullProposalGrantInstance?.appliedDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="remarks"><g:message code="fullProposalGrant.remarks.label" default="Remarks" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalGrantInstance, field: 'remarks', 'errors')}">
                                    <g:textField name="remarks" value="${fullProposalGrantInstance?.remarks}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="activeYesNo"><g:message code="fullProposalGrant.activeYesNo.label" default="Active Yes No" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalGrantInstance, field: 'activeYesNo', 'errors')}">
                                    <g:textField name="activeYesNo" value="${fieldValue(bean: fullProposalGrantInstance, field: 'activeYesNo')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fullProposal"><g:message code="fullProposalGrant.fullProposal.label" default="Full Proposal" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalGrantInstance, field: 'fullProposal', 'errors')}">
                                    <g:select name="fullProposal.id" from="${FullProposal.list()}" optionKey="id" value="${fullProposalGrantInstance?.fullProposal?.id}"  />
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
