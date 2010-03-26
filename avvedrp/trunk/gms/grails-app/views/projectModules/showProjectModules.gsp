

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ProjectModules List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ProjectModules</g:link></span>
        </div>
        <div class="body">
            <h1>ProjectModules List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                   	        
                   	        <th>Project</th>
                        
                   	        <th>Institution</th>
                        
                   	        <g:sortableColumn property="projectName" title="Project Module" />
                        
                   	        <g:sortableColumn property="principalInvestigator" title="Principal Investigator" />
                   	        
                   	        <g:sortableColumn property="projectSubmissionDate" title="Project Submission Date" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                   	        
                   	        <g:sortableColumn property="activeYesNo" title="Active Yes No" />
                   	        
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectModulesInstanceList}" status="i" var="projectModulesInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${projectModulesInstance.id}">${fieldValue(bean:projectModulesInstance, field:'id')}</g:link></td>
                            
                            <td>${fieldValue(bean:projectModulesInstance, field:'projects.projectName')}</td>
                        
                            <td>${fieldValue(bean:projectModulesInstance, field:'institutionMaster.code')}</td>
                        
                            <td>${fieldValue(bean:projectModulesInstance, field:'projectName')}</td>
                        
                            <td>${fieldValue(bean:projectModulesInstance, field:'principalInvestigator.userRealName')}</td>
                            
                            <td>${fieldValue(bean:projectModulesInstance, field:'projectSubmissionDate')}</td>
                        
                            <td>${fieldValue(bean:projectModulesInstance, field:'description')}</td>
                            
                            <td>${fieldValue(bean:projectModulesInstance, field:'activeYesNo')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ProjectModules.count()}" />
            </div>
        </div>
    </body>
</html>
