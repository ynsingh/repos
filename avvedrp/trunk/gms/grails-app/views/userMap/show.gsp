

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show UserMap</title>
    </head>
    <body>
        <div class="nav">
             <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
          
        </div>
        <div class="body">
            <h1>Show UserMap</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:userMapInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Account Heads:</td>
                            
                            <td valign="top" class="value"><g:link controller="accountHeads" action="show" id="${userMapInstance?.accountHeads?.id}">${userMapInstance?.accountHeads?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Created Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:userMapInstance, field:'createdDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Modified Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:userMapInstance, field:'modifiedDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Created By:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:userMapInstance, field:'createdBy')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Modified By:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:userMapInstance, field:'modifiedBy')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Party:</td>
                            
                            <td valign="top" class="value"><g:link controller="party" action="show" id="${userMapInstance?.party?.id}">${userMapInstance?.party?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Projects:</td>
                            
                            <td valign="top" class="value"><g:link controller="projects" action="show" id="${userMapInstance?.projects?.id}">${userMapInstance?.projects?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">User:</td>
                            
                            <td valign="top" class="value"><g:link controller="user" action="show" id="${userMapInstance?.user?.id}">${userMapInstance?.user?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${userMapInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
