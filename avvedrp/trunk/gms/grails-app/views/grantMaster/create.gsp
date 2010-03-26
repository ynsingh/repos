

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create GrantMaster</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">GrantMaster List</g:link></span>
        </div>
        <div class="body">
            <h1>Create GrantMaster</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantMasterInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantMasterInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantMasterInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:grantMasterInstance,field:'code')}"/>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title">Title:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantMasterInstance,field:'title','errors')}">
                                    <input type="text" id="title" name="title" value="${fieldValue(bean:grantMasterInstance,field:'title')}"/>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="manager">Manager:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantMasterInstance,field:'manager','errors')}">
                                	<g:select optionKey="id" optionValue="userRealName" from="${Role.findByAuthority('ROLE_GRANTMANAGER').people}" name="manager.id" value="${grantMasterInstance?.manager?.id}" ></g:select>
                                </td>
                            </tr> 
                            
                            
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantMasterInstance,field:'description','errors')}">
                                    <g:textArea name="description" value="${fieldValue(bean:grantMasterInstance,field:'description')}" rows="2" cols="20"/>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active Yes No:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantMasterInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}" value="${fieldValue(bean:grantMasterInstance,field:'activeYesNo')}" />
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
    </body>
</html>
