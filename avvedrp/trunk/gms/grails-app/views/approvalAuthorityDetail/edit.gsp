


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'approvalAuthorityDetail.label', default: 'ApprovalAuthorityDetail')}" />
        <title><g:message code="default.EditApprovalAuthorityDeatils.label" /></title>
    </head>
    <body>
        <div class ="wrapper">
        <div class="body">
            <h1><g:message code="default.EditApprovalAuthorityDeatils.label" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${approvalAuthorityDetailInstance}">
            <div class="errors">
                <g:renderErrors bean="${approvalAuthorityDetailInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${approvalAuthorityDetailInstance?.id}" />
                <g:hiddenField name="version" value="${approvalAuthorityDetailInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="approvalAuthority"><g:message code="default.ApprovalAuthority.label" default="Approval Authority" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: approvalAuthorityDetailInstance, field: 'approvalAuthority', 'errors')}">
                                    <g:select name="approvalAuthority.id" from="${ApprovalAuthority.list()}" optionKey="id" optionValue="name" value="${approvalAuthorityDetailInstance?.approvalAuthority?.id}"  />
                                </td>
                       
                                <td valign="top" class="name">
                                    <label for="person"><g:message code="default.Users.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: approvalAuthorityDetailInstance, field: 'person', 'errors')}">
                                    <g:select name="person.id" from="${userInstanceList}" optionKey="id" optionValue = "username" value="${approvalAuthorityDetailInstance?.person?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                            
                            <td valign="top" class="name">
                                    <label for="activeYesNo"><g:message code="default.Active.label" default="Active"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:approvalAuthorityDetailInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:approvalAuthorityDetailInstance,field:'activeYesNo')}" />
                                </td>
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="approvalAuthorityDetail.remarks.label" default="Remarks" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: approvalAuthorityDetailInstance, field: 'remarks', 'errors')}">
                                    <g:textArea name="remarks" value="${approvalAuthorityDetailInstance?.remarks}" style='width: 200px; height:75px;'/>
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
        </div>
    </body>
</html>
