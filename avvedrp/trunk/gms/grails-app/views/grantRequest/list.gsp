

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>GrantRequest List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="listGrantDetails">GrantDetails List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create" id="${grantRequestInstance.grantDetails.id}">New GrantRequest</g:link></span>
        </div>
        <div class="body">
            <h1>Grant Request List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="grantDetails.grantMaster.code" title="Grant Code" />
                        
                   	        <g:sortableColumn property="grantDetails.grantMaster.title" title="Grant Title" />
                   	        
                   	        <g:sortableColumn property="grantDetails.financialYear" title="Financial Year" />
                   	        
                   	        <g:sortableColumn property="projects.projectName" title="Project Name" />
                        
                   	        <g:sortableColumn property="dateOfRequest" title="Date Of Request" />
                   	        
                   	        <g:sortableColumn property="amountRequested" title="Amount Requested" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                   	        
                   	        <g:sortableColumn property="requestStatus" title="Request Status" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantRequestInstanceList}" status="i" var="grantRequestInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${grantRequestInstance.id}">${fieldValue(bean:grantRequestInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:grantRequestInstance, field:'grantDetails.grantMaster.code')}</td>
                        
                            <td>${fieldValue(bean:grantRequestInstance, field:'grantDetails.grantMaster.title')}</td>
                            
                            <td>${fieldValue(bean:grantRequestInstance, field:'grantDetails.financialYear')}</td>
                            
                            <td>${fieldValue(bean:grantRequestInstance, field:'projects.projectName')}</td>
                        
                            <td>${fieldValue(bean:grantRequestInstance, field:'dateOfRequest')}</td>
                            
                            <td>${fieldValue(bean:grantRequestInstance, field:'amountRequested')}</td>
                        
                            <td>${fieldValue(bean:grantRequestInstance, field:'description')}</td>
                            
                            <td>${fieldValue(bean:grantRequestInstance, field:'requestStatus')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${GrantRequest.count()}" />
            </div>
        </div>
    </body>
</html>
