

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit StudentLearning</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">StudentLearning List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New StudentLearning</g:link></span>
        </div>
        <div class="body">
            <h1>Edit StudentLearning</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${studentLearningInstance}">
            <div class="errors">
                <g:renderErrors bean="${studentLearningInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${studentLearningInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="courseElement">Course Element:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentLearningInstance,field:'courseElement','errors')}">
                                    <input type="text" id="courseElement" name="courseElement" value="${fieldValue(bean:studentLearningInstance,field:'courseElement')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="courseId">Course Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentLearningInstance,field:'courseId','errors')}">
                                    <input type="text" id="courseId" name="courseId" value="${fieldValue(bean:studentLearningInstance,field:'courseId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="durationActual">Duration Actual:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentLearningInstance,field:'durationActual','errors')}">
                                    <input type="text" id="durationActual" name="durationActual" value="${fieldValue(bean:studentLearningInstance,field:'durationActual')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="studentId">Student Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentLearningInstance,field:'studentId','errors')}">
                                    <input type="text" id="studentId" name="studentId" value="${fieldValue(bean:studentLearningInstance,field:'studentId')}"/>
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
