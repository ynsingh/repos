


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'preProposal.label', default: 'PreProposal')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
       <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.EditPreProposal.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalInstance}" as="preProposalList" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${proposalInstance?.id}" />
                <g:hiddenField name="version" value="${proposalInstance?.version}" />
                <div class="dialog">
                     <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectTitle"><g:message code="default.ProposalTitle.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApplicationInstance, field: 'projectTitle', 'errors')}">
                                    <g:textField name="projectTitle" value="${proposalApplicationInstance?.projectTitle}" />
                                </td>
                        </tr>
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="default.Description.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalInstance, field: 'description', 'errors')}">
                                    <g:textArea name="description" value="${proposalInstance?.description}" />
                                </td>
                            </tr>
                       <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proposalCategory"><g:message code="default.ProposalCategory.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApplicationInstance, field: 'proposalCategory', 'errors')}">
                                    <g:select  optionKey="id" optionValue="name" id="proposalCategory" from="${ProposalCategory.findAll('from ProposalCategory PC where PC.activeYesNo=\'Y\' ')}"  name="proposalCategory.id" value="${proposalApplicationInstance?.proposalCategory?.id}" noSelection="['null':'-Select-']" ></g:select>
                                </td>        																											
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="preProposalUpdate" onClick="return validatePreProposal()"  value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    

                </div>
            </g:form>
        </div>
    </body>
</html>
