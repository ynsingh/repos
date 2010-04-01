

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Utilization List</title>
    </head>
    <body>
    <div class="wrapper"> 
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Utilization</g:link></span>
        </div>
        <div class="tablewrapper">
        <div class="body">
            <h1>Utilization Certificate List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                   	        
                   	        <g:sortableColumn property="projects" title="projects" />
                   	        
                   	        <g:sortableColumn property="grantee" title="grantee" />
                   	                                
                   	        <g:sortableColumn property="attachmentPath" title="Attachment Path" />
                	                               
                   	        <g:sortableColumn property="uploadedDate" title="uploadedDate" />
                   	       	
                             <th>View</th>                	                               
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${utilizationInstanceList}" status="i" var="utilizationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                        
                            <td>${fieldValue(bean:utilizationInstance, field:'projects.name')}</td>
                        
                            <td>${fieldValue(bean:utilizationInstance, field:'grantee.nameOfTheInstitution')}</td>
                        
                            <td>${fieldValue(bean:utilizationInstance, field:'attachmentPath')}</td>
                        	<td><g:formatDate format="dd/mm/yyyy" date="${utilizationInstance.uploadedDate}"/></td>
                          <%--  <td><a href="${g.createLink(controller:'utilization',action:'download',id:utilizationInstance.id}">View</a></td>
                        
                           --%>
                           <td><g:link action="download" controller="utilization" id="${utilizationInstance.id}">View</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Utilization.count()}" />
            </div>
            </div>
            </div>
        </div>
    </body>
</html>
