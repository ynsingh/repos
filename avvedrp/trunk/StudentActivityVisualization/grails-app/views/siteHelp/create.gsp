

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create SiteHelp</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">SiteHelp List</g:link></span>
        </div>
        <div class="body">
            <h1>Create SiteHelp</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${siteHelpInstance}">
            <div class="errors">
                <g:renderErrors bean="${siteHelpInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contents">Contents:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:siteHelpInstance,field:'contents','errors')}">
                                    <input type="text" id="contents" name="contents" value="${fieldValue(bean:siteHelpInstance,field:'contents')}"/>
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
