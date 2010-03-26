

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>PartyDepartment List</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New PartyDepartment</g:link></span>
        </div>
        <div class="body">
            <h1>PartyDepartment List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="departmentCode" title="Department Code" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                   	        
                   	        <g:sortableColumn property="party" title="Party" />
                       
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyDepartmentInstanceList}" status="i" var="partyDepartmentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${partyDepartmentInstance.id}">${fieldValue(bean:partyDepartmentInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:partyDepartmentInstance, field:'departmentCode')}</td>
                        
                            <td>${fieldValue(bean:partyDepartmentInstance, field:'name')}</td>
                            
                            <td>${fieldValue(bean:partyDepartmentInstance, field:'party.code')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${PartyDepartment.count()}" />
            </div>
        </div>
        </div>
    </body>
</html>
