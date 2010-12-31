

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create CourseMaster</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">CourseMaster List</g:link></span>
        </div>
        <div class="body">
            <h1>Create CourseMaster</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${courseMasterInstance}">
            <div class="errors">
                <g:renderErrors bean="${courseMasterInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="courseDescription">Course Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:courseMasterInstance,field:'courseDescription','errors')}">
                                    <input type="text" id="courseDescription" name="courseDescription" value="${fieldValue(bean:courseMasterInstance,field:'courseDescription')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="courseElement">Course Element:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:courseMasterInstance,field:'courseElement','errors')}">
                                    <input type="text" id="courseElement" name="courseElement" value="${fieldValue(bean:courseMasterInstance,field:'courseElement')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="courseId">Course Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:courseMasterInstance,field:'courseId','errors')}">
                                    <input type="text" id="courseId" name="courseId" value="${fieldValue(bean:courseMasterInstance,field:'courseId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="durationExpected">Duration Expected:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:courseMasterInstance,field:'durationExpected','errors')}">
                                    <input type="text" id="durationExpected" name="durationExpected" value="${fieldValue(bean:courseMasterInstance,field:'durationExpected')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
