


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'approvalAuthority.label', default: 'ApprovalAuthority')}" />
        <title><g:message code="default.EditApprovalAuthority.label"/></title>
    </head>
    <body>
        <div class ="wrapper">
        <div class="body">
            <h1><g:message code="default.EditApprovalAuthority.label"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${approvalAuthorityInstance}">
            <div class="errors">
                <g:renderErrors bean="${approvalAuthorityInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${approvalAuthorityInstance?.id}" />
                <g:hiddenField name="version" value="${approvalAuthorityInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="default.Name.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: approvalAuthorityInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${approvalAuthorityInstance?.name}" />
                                </td>
                           
                                <td valign="top" class="name">
                                  <label for="email"><g:message code="default.Email.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: approvalAuthorityInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${approvalAuthorityInstance?.email}" />
                                </td>
                            </tr>
                        
                            
                           
                                <td valign="top" class="name">
                                  <label for="approveMandatory"><g:message code="default.approveMandatory.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: approvalAuthorityInstance, field: 'approveMandatory', 'errors')}">
                                    
                                    <g:select  name="approveMandatory" from ="${['Y','N']}"  value="${fieldValue(bean: approvalAuthorityInstance, field: 'approveMandatory')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="approveAll"><g:message code="default.approveAll.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: approvalAuthorityInstance, field: 'approveAll', 'errors')}">
                                    <g:select name="approveAll" from="${['Y','N']}"  value="${fieldValue(bean: approvalAuthorityInstance, field: 'approveAll')}" />
                                </td>
                           
                                <td valign="top" class="name">
                                  <label for="viewAll"><g:message code="default.viewAll.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: approvalAuthorityInstance, field: 'viewAll', 'errors')}">
                                    <g:select name="viewAll" from ="${['Y', 'N']}" value="${fieldValue(bean: approvalAuthorityInstance, field: 'viewAll')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="remarks"><g:message code="default.Remarks.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: approvalAuthorityInstance, field: 'remarks', 'errors')}">
                                    <g:textArea name="remarks" value="${approvalAuthorityInstance?.remarks}" style='width: 200px; height:75px;'/>
                                </td>
                              
                            </tr> 
                        
                           
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" /></span>
                    
                </div>
            </g:form>
        </div>
        </div>
    </body>
</html>
