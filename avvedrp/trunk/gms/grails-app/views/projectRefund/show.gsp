

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projectRefund.label', default: 'ProjectRefund')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectRefund.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectRefundInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectRefund.modifiedBy.label" default="Modified By" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectRefundInstance, field: "modifiedBy")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectRefund.modifiedDate.label" default="Modified Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${projectRefundInstance?.modifiedDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectRefund.createdBy.label" default="Created By" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectRefundInstance, field: "createdBy")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectRefund.createdDate.label" default="Created Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${projectRefundInstance?.createdDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectRefund.remarks.label" default="Remarks" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectRefundInstance, field: "remarks")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectRefund.dateOfRefund.label" default="Date Of Refund" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${projectRefundInstance?.dateOfRefund}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectRefund.projects.label" default="Projects" /></td>
                            
                            <td valign="top" class="value"><g:link controller="projects" action="show" id="${projectRefundInstance?.projects?.id}">${projectRefundInstance?.projects?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectRefund.refundAmount.label" default="Refund Amount" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectRefundInstance, field: "refundAmount")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${projectRefundInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
