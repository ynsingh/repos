

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Utilization</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Utilization List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Utilization</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Utilization</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${utilizationInstance}">
            <div class="errors">
                <g:renderErrors bean="${utilizationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${utilizationInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attachmentPath">Attachment Path:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:utilizationInstance,field:'attachmentPath','errors')}">
                                    <input type="text" id="attachmentPath" name="attachmentPath" value="${fieldValue(bean:utilizationInstance,field:'attachmentPath')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifiedBy">Modified By:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:utilizationInstance,field:'modifiedBy','errors')}">
                                    <input type="text" id="modifiedBy" name="modifiedBy" value="${fieldValue(bean:utilizationInstance,field:'modifiedBy')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifiedDate">Modified Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:utilizationInstance,field:'modifiedDate','errors')}">
                                    <g:datePicker name="modifiedDate" value="${utilizationInstance?.modifiedDate}" noSelection="['':'']"></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="archiveYesNo">Archive Yes No:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:utilizationInstance,field:'archiveYesNo','errors')}">
                                    <input type="text" id="archiveYesNo" name="archiveYesNo" value="${fieldValue(bean:utilizationInstance,field:'archiveYesNo')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createdBy">Created By:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:utilizationInstance,field:'createdBy','errors')}">
                                    <input type="text" id="createdBy" name="createdBy" value="${fieldValue(bean:utilizationInstance,field:'createdBy')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createdDate">Created Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:utilizationInstance,field:'createdDate','errors')}">
                                    <g:datePicker name="createdDate" value="${utilizationInstance?.createdDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="grantee">Grantee:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:utilizationInstance,field:'grantee','errors')}">
                                    <g:select optionKey="id" from="${Party.list()}" name="grantee.id" value="${utilizationInstance?.grantee?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projects">Projects:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:utilizationInstance,field:'projects','errors')}">
                                    <g:select optionKey="id" from="${Projects.list()}" name="projects.id" value="${utilizationInstance?.projects?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="uploadedDate">Uploaded Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:utilizationInstance,field:'uploadedDate','errors')}">
                                    <g:datePicker name="uploadedDate" value="${utilizationInstance?.uploadedDate}" ></g:datePicker>
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
