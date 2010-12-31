

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit StudentMaster</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">StudentMaster List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New StudentMaster</g:link></span>
        </div>
        <div class="body">
            <h1>Edit StudentMaster</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${studentMasterInstance}">
            <div class="errors">
                <g:renderErrors bean="${studentMasterInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${studentMasterInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="studentId">Student Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentMasterInstance,field:'studentId','errors')}">
                                    <input type="text" id="studentId" name="studentId" value="${fieldValue(bean:studentMasterInstance,field:'studentId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="studentName">Student Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:studentMasterInstance,field:'studentName','errors')}">
                                    <input type="text" id="studentName" name="studentName" value="${fieldValue(bean:studentMasterInstance,field:'studentName')}"/>
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
