

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Requestmap</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Requestmap List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Requestmap</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${requestmapInstance}">
            <div class="errors">
                <g:renderErrors bean="${requestmapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="url">Url:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:requestmapInstance,field:'url','errors')}">
                                    <input type="text" id="url" name="url" value="${fieldValue(bean:requestmapInstance,field:'url')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="configAttribute">Config Attribute:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:requestmapInstance,field:'configAttribute','errors')}">
                                    <input type="text" id="configAttribute" name="configAttribute" value="${fieldValue(bean:requestmapInstance,field:'configAttribute')}"/>
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
