


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
            <g:hasErrors bean="${preProposalInstance}">
            <div class="errors">
                <g:renderErrors bean="${preProposalInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectTitle"><g:message code="default.ProposalTitle.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalInstance, field: 'projectTitle', 'errors')}">
                                    <g:textField name="projectTitle" value="${preProposalInstance?.projectTitle}" />
                                </td>
                        </tr>
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="default.Remarks.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalInstance, field: 'remarks', 'errors')}">
                                    <g:textArea name="remarks" value="${preProposalInstance?.remarks}" />
                                </td>
                            </tr>
                       <input type=hidden name="preProposalForm" value="${proposalApplicationForm}" />
                          
                        
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proposalCategory"><g:message code="default.ProposalCategory.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalInstance, field: 'proposalCategory', 'errors')}">
                                    <g:select  optionKey="id" optionValue="name" id="proposalCategory" from="${ProposalCategory.findAll('from ProposalCategory PC where PC.activeYesNo=\'Y\' ')}"  name="proposalCategory.id" value="${preProposalInstance?.proposalCategory?.id}"  ></g:select>
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
