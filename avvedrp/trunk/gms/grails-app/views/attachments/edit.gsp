<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'attachments.label', default: 'Attachments')}" />
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
            <g:hasErrors bean="${attachmentsInstance}">
            <div class="errors">
                <g:renderErrors bean="${attachmentsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${attachmentsInstance?.id}" />
                <g:hiddenField name="version" value="${attachmentsInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="attachmentType"><g:message code="attachments.attachmentType.label" default="Attachment Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: attachmentsInstance, field: 'attachmentType', 'errors')}">
                                    <g:select name="attachmentType.id" from="${AttachmentType.list()}" optionKey="id" value="${attachmentsInstance?.attachmentType?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="attachmentPath"><g:message code="attachments.attachmentPath.label" default="Attachment Path" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: attachmentsInstance, field: 'attachmentPath', 'errors')}">
                                    <g:textField name="attachmentPath" value="${attachmentsInstance?.attachmentPath}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="createdBy"><g:message code="attachments.createdBy.label" default="Created By" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: attachmentsInstance, field: 'createdBy', 'errors')}">
                                    <g:textField name="createdBy" value="${attachmentsInstance?.createdBy}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="createdDate"><g:message code="attachments.createdDate.label" default="Created Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: attachmentsInstance, field: 'createdDate', 'errors')}">
                                    <g:datePicker name="createdDate" precision="day" value="${attachmentsInstance?.createdDate}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="modifiedBy"><g:message code="attachments.modifiedBy.label" default="Modified By" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: attachmentsInstance, field: 'modifiedBy', 'errors')}">
                                    <g:textField name="modifiedBy" value="${attachmentsInstance?.modifiedBy}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="modifiedDate"><g:message code="attachments.modifiedDate.label" default="Modified Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: attachmentsInstance, field: 'modifiedDate', 'errors')}">
                                    <g:datePicker name="modifiedDate" precision="day" value="${attachmentsInstance?.modifiedDate}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="domain"><g:message code="attachments.domain.label" default="Domain" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: attachmentsInstance, field: 'domain', 'errors')}">
                                    <g:textField name="domain" value="${attachmentsInstance?.domain}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="domainId"><g:message code="attachments.domainId.label" default="Domain Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: attachmentsInstance, field: 'domainId', 'errors')}">
                                    <g:textField name="domainId" value="${attachmentsInstance?.domainId}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="openedYesNo"><g:message code="attachments.openedYesNo.label" default="Opened Yes No" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: attachmentsInstance, field: 'openedYesNo', 'errors')}">
                                    <g:textField name="openedYesNo" value="${attachmentsInstance?.openedYesNo}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" onClick="return validateAttachments()"  value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
