

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Inactive Projects</title>
    </head>
    <body>
    
    <div class="wrapper"> 
       
        <div class="body">
        
            
            <h1>Inactive Projects</h1>
            <g:if test="${flash.error}">
            <div class="errors">${flash.error}</div>
            </g:if>
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
                   	          
                	        <th>Attachments</th>
                   	        <g:if test="${(session.Role == 'ROLE_SITEADMIN')}">   
                   	       		<th>Edit</th>
                   	        </g:if>  
                        
                        </tr>
                    </thead>
                    <tbody>
                    <% int j=0 %>
                    <g:each in="${grantAllocationWithprojectsInstanceList}" status="i" var="grantAllocationInstance">
                        <g:if test="${fieldValue(bean:grantAllocationInstance, field:'projects.activeYesNo') == 'N'}">
	                       <%  j++ %>
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                        
	                           <td>${j}</td>
	                                
	                                 <%-- <td>${fieldValue(bean:grantAllocationInstance, field:'projects.parent.code')}</td>--%>
	                               
	                        
	                           
	                         <td>${grantAllocationInstance.projects.name}</td>
	                           
	                           <td>${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</td>
	                           <td>${fieldValue(bean:grantAllocationInstance, field:'projects.projectType.type')}</td>
	                          
	                           <td><g:if test="${Attachments.findByDomainId(grantAllocationInstance.projects.id)}">
	                           <g:link action="list" controller="attachments" id="${grantAllocationInstance.projects.id}">Attachments</g:link>
	                           </g:if>
	                           <g:else>
	                           No Attachmnets
	                           </g:else>
	                           </td>
	                        
	                        <g:if test="${(session.Role == 'ROLE_SITEADMIN')}">   
	                       		<td><g:link action="edit" id="${grantAllocationInstance.projects.id}">Edit</g:link></td>
	                        </g:if>
	                        </tr>
	                       </g:if>
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
