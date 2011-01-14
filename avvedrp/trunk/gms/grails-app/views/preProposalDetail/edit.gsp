


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'preProposalDetail.label', default: 'PreProposalDetail')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
       
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${preProposalDetailInstance}">
            <div class="errors">
                <g:renderErrors bean="${preProposalDetailInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${preProposalDetailInstance?.id}" />
                <g:hiddenField name="version" value="${preProposalDetailInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="field"><g:message code="preProposalDetail.field.label" default="Field" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalDetailInstance, field: 'field', 'errors')}">
                                    <g:textField name="field" value="${preProposalDetailInstance?.field}" />
                                </td>
                          
                                <td valign="top" class="name">
                                  <label for="value"><g:message code="preProposalDetail.value.label" default="Value" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalDetailInstance, field: 'value', 'errors')}">
                                    <g:textField name="value" value="${preProposalDetailInstance?.value}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="preProposalSubmittedDate"><g:message code="preProposalDetail.preProposalSubmittedDate.label" default="Pre Proposal Submitted Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalDetailInstance, field: 'preProposalSubmittedDate', 'errors')}">
                                    <g:datePicker name="preProposalSubmittedDate" precision="day" value="${preProposalDetailInstance?.preProposalSubmittedDate}" noSelection="['': '']" />
                                </td>
                          
                                <td valign="top" class="name">
                                    <label for="defaultYesNo"><g:message code="default.Active.label" default="Active"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:preProposalDetailInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:preProposalDetailInstance,field:'activeYesNo')}" />
                                </td>   
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="preProposal"><g:message code="preProposalDetail.preProposal.label" default="Pre Proposal" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalDetailInstance, field: 'preProposal', 'errors')}">
                                    <g:select name="preProposal.id" from="${PreProposal.list()}" optionKey="id" value="${preProposalDetailInstance?.preProposal?.id}"  />
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
