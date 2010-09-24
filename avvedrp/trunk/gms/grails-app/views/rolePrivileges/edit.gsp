

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit RolePrivileges</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="body">
            <h1>Edit RolePrivileges</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${rolePrivilegesInstance}">
            <div class="errors">
                <g:renderErrors bean="${rolePrivilegesInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${rolePrivilegesInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:rolePrivilegesInstance,field:'description','errors')}">
                                    <input type="text" id="description" name="description" value="${fieldValue(bean:rolePrivilegesInstance,field:'description')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createdBy">Created By:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:rolePrivilegesInstance,field:'createdBy','errors')}">
                                    <input type="text" id="createdBy" name="createdBy" value="${fieldValue(bean:rolePrivilegesInstance,field:'createdBy')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createdDate">Created Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:rolePrivilegesInstance,field:'createdDate','errors')}">
                                    <g:datePicker name="createdDate" value="${rolePrivilegesInstance?.createdDate}" noSelection="['':'']"></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifiedBy">Modified By:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:rolePrivilegesInstance,field:'modifiedBy','errors')}">
                                    <input type="text" id="modifiedBy" name="modifiedBy" value="${fieldValue(bean:rolePrivilegesInstance,field:'modifiedBy')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifiedDate">Modified Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:rolePrivilegesInstance,field:'modifiedDate','errors')}">
                                    <g:datePicker name="modifiedDate" value="${rolePrivilegesInstance?.modifiedDate}" noSelection="['':'']"></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="actionName">Action Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:rolePrivilegesInstance,field:'actionName','errors')}">
                                    <input type="text" id="actionName" name="actionName" value="${fieldValue(bean:rolePrivilegesInstance,field:'actionName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="controllerName">Controller Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:rolePrivilegesInstance,field:'controllerName','errors')}">
                                    <input type="text" id="controllerName" name="controllerName" value="${fieldValue(bean:rolePrivilegesInstance,field:'controllerName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="role">Role:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:rolePrivilegesInstance,field:'role','errors')}">
                                    <g:select optionKey="id" from="${Role.list()}" name="role.id" value="${rolePrivilegesInstance?.role?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
