

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show CourseMaster</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">CourseMaster List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New CourseMaster</g:link></span>
        </div>
        <div class="body">
            <h1>Show CourseMaster</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseMasterInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Course Description:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseMasterInstance, field:'courseDescription')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Course Element:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseMasterInstance, field:'courseElement')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Course Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseMasterInstance, field:'courseId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Duration Expected:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:courseMasterInstance, field:'durationExpected')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${courseMasterInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
