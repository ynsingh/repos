

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>InstitutionMaster List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New InstitutionMaster</g:link></span>
        </div>
        <div class="body">
            <h1>InstitutionMaster List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="code" title="Code" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                   	        
                   	        <g:sortableColumn property="address" title="Address" />
                        
                   	        <g:sortableColumn property="phone" title="Phone" />
                   	        
                   	        <g:sortableColumn property="activeYesNo" title="Active Yes No" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${institutionMasterInstanceList}" status="i" var="institutionMasterInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${institutionMasterInstance.id}">${fieldValue(bean:institutionMasterInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:institutionMasterInstance, field:'code')}</td>
                        
                            <td>${fieldValue(bean:institutionMasterInstance, field:'name')}</td>
                            
                            <td>${fieldValue(bean:institutionMasterInstance, field:'address')}</td>
                        
                            <td>${fieldValue(bean:institutionMasterInstance, field:'phone')}</td>
                            
                            <td>${fieldValue(bean:institutionMasterInstance, field:'activeYesNo')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${InstitutionMaster.count()}" />
            </div>
        </div>
    </body>
</html>
