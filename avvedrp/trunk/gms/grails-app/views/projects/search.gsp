

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.projects.Search.head"/></title>         
    </head>
    <body>
    <div class="wrapper">
      
        <div class="body">
            <h1><g:message code="default.projects.Search.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="searchProjects" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="default.Name.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <input type="text" size="45" id="name" name="name" value="${fieldValue(bean:projectsInstance,field:'name')}"/>
                                </td>
                           
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.Code.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text"  id="code" name="code" value="${fieldValue(bean:projectsInstance,field:'code')}"/>
                                </td>
                            </tr> 
                        	
                        	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.ProjectType.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectType','errors')}">
                                    <g:select optionKey="id" optionValue="type" id="projectType" from="${ProjectType.findAll('from ProjectType P where P.activeYesNo=\'Y\' ')}"  name="projectType.id" value="${projectsInstance?.projectType?.id}" noSelection="['null':'select']" ></g:select>
                                </td>
                           
                                <td valign="top" class="name">
                                    <label for="investigator"><g:message code="default.Investigator.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'investigator','errors')}">
                                    <g:select optionKey="id" optionValue="name" from="${Investigator.findAll('from Investigator I where I.activeYesNo=\'Y\' ')}" name="investigator.id" value="${projectsInstance?.investigator?.id}" noSelection="['null':'select']"></g:select>
                                </td>
                         </tr>                                                              
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" onClick="" value="Search" /></span>
                </div>
            </g:form>
        </div>
        
        <div class="body">
            <div class="list">
                <table>
                    <thead>
  						<tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
                       
                   	        <g:sortableColumn property="projects.name" title="${message(code: 'default.Name.label')}" />
                                              
                   	        <g:sortableColumn property="projects.code" title="${message(code: 'default.Code.label')}" />
                	       
                   	       	<g:sortableColumn property="projects.projectType.type" title="${message(code: 'default.ProjectType.label')}"/>
                   	       	
                   	       	<g:sortableColumn property="projects.projectStartDate" title="${message(code: 'default.StartDate.label')}" />
                   	       	 
                   	       	<g:sortableColumn property="projects.projectEndDate" title="${message(code: 'default.EndDate.label')}" />
                   	       	
                   	       	<g:sortableColumn property="projects.activeYesNo" title="${message(code: 'default.Active.label')}"/>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationInstanceList}" status="i" var="projectsInstance">
					 <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                        
                            <td>
                            <g:link action="projectDash" controller='grantAllocation' id="${projectsInstance.projects.id}">
                            ${fieldValue(bean:projectsInstance, field:'projects.name')}
                            </g:link>
                            </td>
                        	 
                        	<td>${fieldValue(bean:projectsInstance, field:'projects.code')}</td>
                        	 
                        	<td>${fieldValue(bean:projectsInstance, field:'projects.projectType.type')}</td>
                        	 
                        	<td><g:formatDate date="${projectsInstance.projects.projectStartDate}" format="dd/MM/yyyy"/></td>
                        	  
                        	<td><g:formatDate date="${projectsInstance.projects.projectEndDate}" format="dd/MM/yyyy"/></td>
                        	
                        	<td>
	                        <g:if test="${fieldValue(bean:projectsInstance, field:'projects.activeYesNo') == 'Y'}">
	    							 <g:message code="default.YES.label"/>
	    							 </g:if>
	    							 <g:else>
	    							 <g:message code="default.NO.label"/>
	    							 </g:else>
                       		</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
           <div class="paginateButtons">
               
            </div> 
        </div>
        </div>
    </body>
</html>
