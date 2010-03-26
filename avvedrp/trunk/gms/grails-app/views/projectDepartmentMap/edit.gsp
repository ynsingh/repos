

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit ProjectDepartmentMap</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProjectDepartmentMap List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ProjectDepartmentMap</g:link></span>
        </div>
        <div class="body">
            <h1>Edit ProjectDepartmentMap</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectDepartmentMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectDepartmentMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${projectDepartmentMapInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="partyDepartment">Party Department:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectDepartmentMapInstance,field:'partyDepartment','errors')}">
                                    <g:select optionKey="id" from="${PartyDepartment.list()}" name="partyDepartment.id" value="${projectDepartmentMapInstance?.partyDepartment?.id}" ></g:select>
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
        </div>
    </body>
</html>
