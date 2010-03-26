

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>GrantPeriod List</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
           <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Grant Period</g:link></span>
        </div>
        <div class="body">
            <h1>GrantPeriod List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="SlNo" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        
                        
                   	        <g:sortableColumn property="activeYesNo" title="Active" />
                        
                   	        <th>Edit</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantPeriodInstanceList}" status="i" var="grantPeriodInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                             <td>${(i + 1)}</td>
                         
                            <td>${fieldValue(bean:grantPeriodInstance, field:'name')}</td>
                        
                            
                            <td>${fieldValue(bean:grantPeriodInstance, field:'activeYesNo')}</td>
                        
                       <td><g:link action="edit" id="${fieldValue(bean:grantPeriodInstance, field:'id')}">Edit</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${GrantPeriod.count()}" />
            </div>
        </div>
         </div>
    </body>
</html>
