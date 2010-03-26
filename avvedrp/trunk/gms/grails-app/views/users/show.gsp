

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Users</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Users List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Users</g:link></span>
        </div>
        <div class="body">
            <h1>Show Users</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:usersInstance, field:'id')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">User Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:usersInstance, field:'userName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">User Password:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:usersInstance, field:'userPassword')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:usersInstance, field:'name')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Email:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:usersInstance, field:'email')}</td>
                            
                        </tr>
                        
                         <tr class="prop">
                            <td valign="top" class="name">Phone:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:usersInstance, field:'phone')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Role:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:usersInstance, field:'role')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Active Yes No:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:usersInstance, field:'activeYesNo')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${usersInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
