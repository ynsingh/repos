

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit PartyDepartment</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">PartyDepartment List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New PartyDepartment</g:link></span>
        </div>
        <div class="body">
            <h1>Edit PartyDepartment</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyDepartmentInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyDepartmentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${partyDepartmentInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="departmentCode">Department Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyDepartmentInstance,field:'departmentCode','errors')}">
                                    <input type="text" id="departmentCode" name="departmentCode" value="${fieldValue(bean:partyDepartmentInstance,field:'departmentCode')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyDepartmentInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:partyDepartmentInstance,field:'name')}"/>
                                </td>
                            </tr>                         
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Party:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyDepartmentInstance,field:'party.code','errors')}">
                                    <g:select optionKey="code" optionValue="code" from="${Party.list()}" name="party.code" value="${partyDepartmentInstance?.party?.code}" ></g:select>
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
