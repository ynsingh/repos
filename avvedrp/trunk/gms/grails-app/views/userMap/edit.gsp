<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit UserMap</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
             <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
                    </div>
        <div class="body">
            <h1>Edit UserMap</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${userMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${userMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${userMapInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                               
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="user">User:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:userMapInstance,field:'user','errors')}">
                                    <g:select optionKey="id"  optionValue="username" from="${User.list()}" name="user.id" value="${userMapInstance?.user?.id}" ></g:select>
                                </td>
                            </tr> 
                            
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projects">Projects:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:userMapInstance,field:'projects','errors')}">
                                    <g:select optionKey="id"  optionValue="code" from="${Projects.list()}" name="projects.id" value="${userMapInstance?.projects?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Institution:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:userMapInstance,field:'party','errors')}">
                                    <g:select optionKey="id" from="${Party.list()}" optionValue="code" name="party.id" value="${userMapInstance?.party?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" action="update"/></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
              </div>
        </div>
    </body>
</html>
