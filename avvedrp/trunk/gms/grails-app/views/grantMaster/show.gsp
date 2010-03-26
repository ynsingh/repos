

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show GrantMaster</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">GrantMaster List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New GrantMaster</g:link></span>
        </div>
        <div class="body">
            <h1>Show GrantMaster</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantMasterInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Code:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantMasterInstance, field:'code')}</td>
                            
                        </tr>
                        
                    	<tr class="prop">
                            <td valign="top" class="name">Title:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantMasterInstance, field:'title')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Manager:</td>
                            
                            <td valign="top" class="value"><g:link controller="user" action="show" id="${grantMasterInstance?.manager?.id}">${grantMasterInstance?.manager?.userRealName}</g:link></td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantMasterInstance, field:'description')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Active Yes No:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantMasterInstance, field:'activeYesNo')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${grantMasterInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
