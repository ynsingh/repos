<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>SiteHelp List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New SiteHelp</g:link></span>
        </div>
        <div class="body">
            <h1>SiteHelp List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="contents" title="Contents" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${siteHelpInstanceList}" status="i" var="siteHelpInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${siteHelpInstance.id}">${fieldValue(bean:siteHelpInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:siteHelpInstance, field:'contents')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${siteHelpInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
