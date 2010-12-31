

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit StudentSessionLog</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">StudentSessionLog List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New StudentSessionLog</g:link></span>
        </div>
        <div class="body">
            <h1>Edit StudentSessionLog</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${studentSessionLogInstance}">
            <div class="errors">
                <g:renderErrors bean="${studentSessionLogInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${studentSessionLogInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="loginDateTime">Login Date Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentSessionLogInstance,field:'loginDateTime','errors')}">
                                    <input type="text" id="loginDateTime" name="loginDateTime" value="${fieldValue(bean:studentSessionLogInstance,field:'loginDateTime')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="logoffDateTime">Logoff Date Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentSessionLogInstance,field:'logoffDateTime','errors')}">
                                    <input type="text" id="logoffDateTime" name="logoffDateTime" value="${fieldValue(bean:studentSessionLogInstance,field:'logoffDateTime')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="studentId">Student Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentSessionLogInstance,field:'studentId','errors')}">
                                    <input type="text" id="studentId" name="studentId" value="${fieldValue(bean:studentSessionLogInstance,field:'studentId')}"/>
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
