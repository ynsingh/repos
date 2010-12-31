

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit LmsUsage</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">LmsUsage List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New LmsUsage</g:link></span>
        </div>
        <div class="body">
            <h1>Edit LmsUsage</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${lmsUsageInstance}">
            <div class="errors">
                <g:renderErrors bean="${lmsUsageInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${lmsUsageInstance?.id}" />
                <input type="hidden" name="version" value="${lmsUsageInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="session_minutes">Sessionminutes:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:lmsUsageInstance,field:'session_minutes','errors')}">
                                    <input type="text" id="session_minutes" name="session_minutes" value="${fieldValue(bean:lmsUsageInstance,field:'session_minutes')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="student_id">Studentid:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:lmsUsageInstance,field:'student_id','errors')}">
                                    <input type="text" id="student_id" name="student_id" value="${fieldValue(bean:lmsUsageInstance,field:'student_id')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="week">Week:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:lmsUsageInstance,field:'week','errors')}">
                                    <input type="text" id="week" name="week" value="${fieldValue(bean:lmsUsageInstance,field:'week')}"/>
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
