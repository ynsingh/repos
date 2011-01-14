


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'preProposalCoPI.label', default: 'PreProposalCoPI')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${preProposalCoPIInstance}">
            <div class="errors">
                <g:renderErrors bean="${preProposalCoPIInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${preProposalCoPIInstance?.id}" />
                <g:hiddenField name="version" value="${preProposalCoPIInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="coPiCommitments"><g:message code="preProposalCoPI.coPiCommitments.label" default="Co Pi Commitments" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalCoPIInstance, field: 'coPiCommitments', 'errors')}">
                                    <g:textField name="coPiCommitments" value="${preProposalCoPIInstance?.coPiCommitments}" />
                                </td>
                           
                                <td valign="top" class="name">
                                  <label for="faculty"><g:message code="preProposalCoPI.faculty.label" default="Faculty" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalCoPIInstance, field: 'faculty', 'errors')}">
                                    <g:select name="faculty.id" from="${Faculty.list()}" optionKey="id" value="${preProposalCoPIInstance?.faculty?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="preProposal"><g:message code="preProposalCoPI.preProposal.label" default="Pre Proposal" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalCoPIInstance, field: 'preProposal', 'errors')}">
                                    <g:select name="preProposal.id" from="${PreProposal.list()}" optionKey="id" value="${preProposalCoPIInstance?.preProposal?.id}"  />
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
