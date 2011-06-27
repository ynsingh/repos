


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'preProposal.label', default: 'PreProposal')}" />
        <title><g:message code="default.AddNewProposal.label"/></title>
    </head>
    <body>
       
        <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.AddNewProposal.label"/></h1>
           <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="preProposalSave" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectTitle"><g:message code="default.ProposalTitle.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ProposalApplicationInstance, field: 'projectTitle', 'errors')}">
                                    <g:textField name="projectTitle" value="${ProposalApplicationInstance?.projectTitle}" />
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
                       <input type=hidden name="proposalDocumentationPath" value="${proposalApplicationForm}" />
                          
                        
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proposalCategory"><g:message code="default.ProposalCategory.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ProposalApplicationInstance, field: 'proposalCategory', 'errors')}">
                                    <g:select  optionKey="id" optionValue="name" id="proposalCategory" from="${ProposalCategory.findAll('from ProposalCategory PC where PC.activeYesNo=\'Y\' ')}"  name="proposalCategory.id" value="${ProposalApplicationInstance?.proposalCategory?.id}" noSelection="['null':'-Select-']" ></g:select>
                                </td>        																											
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" onClick="return validatePreProposal()" class="save" value="${message(code: 'default.Create.button')}"  /></span>
                </div>
         </g:form>
        </div>
       </div>
           
           
    </body>
</html>
