


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalCategory.label', default: 'ProposalCategory')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
     <div class="wrapper">
       
        <div class="body">
            <h1><g:message code="default.EditProposalCategory.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalCategoryInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalCategoryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${proposalCategoryInstance?.id}" />
                <g:hiddenField name="version" value="${proposalCategoryInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="default.Name.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalCategoryInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${proposalCategoryInstance?.name}" />
                                </td>
                          
                                <td valign="top" class="name">
                                  <label for="remarks"><g:message code="default.Remarks.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalCategoryInstance, field: 'remarks', 'errors')}">
                                    <g:textField name="remarks" value="${proposalCategoryInstance?.remarks}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                 <td valign="top" class="name">
                                    <label for="defaultYesNo"><g:message code="default.Active.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalCategoryInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:proposalCategoryInstance,field:'activeYesNo')}" />
                                </td>  
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                   
                </div>
            </g:form>
        </div>
    </body>
</html>
