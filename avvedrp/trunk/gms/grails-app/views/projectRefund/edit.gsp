


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projectRefund.label', default: 'ProjectRefund')}" />
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
            <g:hasErrors bean="${projectRefundInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectRefundInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${projectRefundInstance?.id}" />
                <g:hiddenField name="version" value="${projectRefundInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="modifiedBy"><g:message code="projectRefund.modifiedBy.label" default="Modified By" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectRefundInstance, field: 'modifiedBy', 'errors')}">
                                    <g:textField name="modifiedBy" value="${projectRefundInstance?.modifiedBy}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="modifiedDate"><g:message code="projectRefund.modifiedDate.label" default="Modified Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectRefundInstance, field: 'modifiedDate', 'errors')}">
                                    <g:datePicker name="modifiedDate" precision="day" value="${projectRefundInstance?.modifiedDate}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="createdBy"><g:message code="projectRefund.createdBy.label" default="Created By" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectRefundInstance, field: 'createdBy', 'errors')}">
                                    <g:textField name="createdBy" value="${projectRefundInstance?.createdBy}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="createdDate"><g:message code="projectRefund.createdDate.label" default="Created Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectRefundInstance, field: 'createdDate', 'errors')}">
                                    <g:datePicker name="createdDate" precision="day" value="${projectRefundInstance?.createdDate}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="remarks"><g:message code="projectRefund.remarks.label" default="Remarks" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectRefundInstance, field: 'remarks', 'errors')}">
                                    <g:textField name="remarks" value="${projectRefundInstance?.remarks}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="dateOfRefund"><g:message code="projectRefund.dateOfRefund.label" default="Date Of Refund" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectRefundInstance, field: 'dateOfRefund', 'errors')}">
                                    <g:datePicker name="dateOfRefund" precision="day" value="${projectRefundInstance?.dateOfRefund}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="projects"><g:message code="projectRefund.projects.label" default="Projects" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectRefundInstance, field: 'projects', 'errors')}">
                                    <g:select name="projects.id" from="${Projects.list()}" optionKey="id" value="${projectRefundInstance?.projects?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="refundAmount"><g:message code="projectRefund.refundAmount.label" default="Refund Amount" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectRefundInstance, field: 'refundAmount', 'errors')}">
                                    <g:textField name="refundAmount" value="${fieldValue(bean: projectRefundInstance, field: 'refundAmount')}" />
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
