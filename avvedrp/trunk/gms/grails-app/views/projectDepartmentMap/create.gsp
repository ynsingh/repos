

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ProjectDepartmentMap</title>         
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProjectDepartmentMap List</g:link></span>
        </div>
        <div class="body">
            <h1>Create ProjectDepartmentMap</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectDepartmentMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectDepartmentMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projects">Projects:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectDepartmentMapInstance,field:'projects','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${projectsList}" name="projects.id" value="${projectDepartmentMapInstance?.projects?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="partyDepartment">Department:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectDepartmentMapInstance,field:'partyDepartment','errors')}">
                                    <g:select optionKey="id" optionValue="departmentCode" from="${partyDepartmentList}" name="partyDepartment.id" value="${projectDepartmentMapInstance?.partyDepartment?.id}" ></g:select>
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
      	</div>
    </body>
</html>
