


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projectRefund.label', default: 'ProjectRefund')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectRefundInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectRefundInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="refundAmount"><g:message code="projectRefund.refundAmount.label" default="Refund Amount" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectRefundInstance, field: 'refundAmount', 'errors')}">
                                    <g:textField name="refundAmount" value="${fieldValue(bean: projectRefundInstance, field: 'refundAmount')}" />
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
                                    <label for="remarks"><g:message code="projectRefund.remarks.label" default="Remarks" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectRefundInstance, field: 'remarks', 'errors')}">
                                    <g:textField name="remarks" value="${projectRefundInstance?.remarks}" />
                                </td>
                            </tr>
                        
                        
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
