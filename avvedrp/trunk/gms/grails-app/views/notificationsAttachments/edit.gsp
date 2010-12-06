

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit NotificationsAttachments</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">NotificationsAttachments List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New NotificationsAttachments</g:link></span>
        </div>
        <div class="body">
            <h1>Edit NotificationsAttachments</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${notificationsAttachmentsInstance}">
            <div class="errors">
                <g:renderErrors bean="${notificationsAttachmentsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${notificationsAttachmentsInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="notification">Notification:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationsAttachmentsInstance,field:'notification','errors')}">
                                    <g:select optionKey="id" from="${Notification.list()}" name="notification.id" value="${notificationsAttachmentsInstance?.notification?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type">Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationsAttachmentsInstance,field:'attachmentType','errors')}">
                                    <g:select optionKey="id" from="${AttachmentType.list()}" name="attachmentType.id" value="${notificationsAttachmentsInstance?.attachmentType?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attachmentpath">Attachmentpath:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationsAttachmentsInstance,field:'attachmentPath','errors')}">
                                    <input type="text" id="attachmentPath" name="attachmentPath" value="${fieldValue(bean:notificationsAttachmentsInstance,field:'attachmentPath')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createdBy">Created By:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationsAttachmentsInstance,field:'createdBy','errors')}">
                                    <input type="text" id="createdBy" name="createdBy" value="${fieldValue(bean:notificationsAttachmentsInstance,field:'createdBy')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createdDate">Created Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationsAttachmentsInstance,field:'createdDate','errors')}">
                                    <g:datePicker name="createdDate" value="${notificationsAttachmentsInstance?.createdDate}" noSelection="['':'']"></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifiedBy">Modified By:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationsAttachmentsInstance,field:'modifiedBy','errors')}">
                                    <input type="text" id="modifiedBy" name="modifiedBy" value="${fieldValue(bean:notificationsAttachmentsInstance,field:'modifiedBy')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifiedDate">Modified Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationsAttachmentsInstance,field:'modifiedDate','errors')}">
                                    <g:datePicker name="modifiedDate" value="${notificationsAttachmentsInstance?.modifiedDate}" noSelection="['':'']"></g:datePicker>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
