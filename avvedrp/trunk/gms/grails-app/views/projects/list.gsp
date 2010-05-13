

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Projects List</title>
    </head>
    <body>
    <div class="wrapper"> 
        <div class="nav">
        
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
             <g:if test="${(session.Role != 'ROLE_PI')}">
            <span class="menuButton"><g:link class="create" action="create">New Projects</g:link></span>
            <g:if test="${(session.Role == 'ROLE_SITEADMIN') || (session.Role == 'ROLE_ADMIN')}"> 
            <span class="menuButton"><g:link controller="projectDepartmentMap" class="create" action="create">Add Projects To Department</g:link></span>
            <span class="menuButton"><g:link controller="projectType" class="create" action="create">Project Type</g:link></span>
            <span class="menuButton"><g:link controller="investigator" class="create" action="create">PI</g:link></span>
            <span class="menuButton"><g:link controller="projectsPIMap" class="create" action="create">Add Projects To PI</g:link></span>
            </g:if>
            </g:if>
        </div>
        
        <div class="body">
            <h1>Projects List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${grantAllocationWithprojectsInstanceList}">
            <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="SlNo" />
                   	              
                       		<%-- <g:sortableColumn property="parent" title="Main Project" /> --%>
                                 
                       
                   	        <g:sortableColumn property="projects.name" title="Name" />
                                              
                   	        <g:sortableColumn property="projects.code" title="Code" />
                   	        
                   	        <g:sortableColumn property="projects.projectType.type" title="Project Type" />
                                             
                   	          <g:sortableColumn property="projects.activeYesNo" title="Active" />
                   	          <th>Sub Projects</th>
                   	          <th>Project Dash Board</th>
                   	          <th>Project Closure</th>
                   	          <th>Edit</th>
                   	          
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationWithprojectsInstanceList}" status="i" var="grantAllocationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                           <td>${i+1}</td>
                                
                                 <%-- <td>${fieldValue(bean:grantAllocationInstance, field:'projects.parent.code')}</td>--%>
                               
                        
                           <td>${fieldValue(bean:grantAllocationInstance, field:'projects.name')}</td>
                           <td>${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</td>
                           <td>${fieldValue(bean:grantAllocationInstance, field:'projects.projectType.type')}</td>
                           <td><g:if test="${fieldValue(bean:grantAllocationInstance, field:'projects.activeYesNo') == 'Y'}">
    							 ${'YES'}
    							 </g:if>
    							 <g:else>
    							 ${'NO'}
    							 </g:else>
                           </td>
                           <td><g:link  action="showSubProjects"  id="${grantAllocationInstance.projects.id}">Sub Projects</g:link></td>
                              <td><g:link action="projectDash" controller='grantAllocation' id="${grantAllocationInstance.projects.id}">View</g:link></td>
               
                           <td><g:link  action="create" controller='projectTracking'  id="${grantAllocationInstance.projects.id}">Project Closure</g:link></td>
                        <td><g:link action="edit" id="${grantAllocationInstance.projects.id}">Edit</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            </g:if>
            <g:else>
            <br>No Records Available</br>
            </g:else>
          </div>  
        </div>
        
    </body>
</html>
