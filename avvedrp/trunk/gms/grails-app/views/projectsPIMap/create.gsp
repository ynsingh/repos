

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.projectsPIMap.AddProjectsToPI.head"/></title>         
    </head>
    <body>
    <g:subMenuProjects/>
    <div class="wrapper">
          <div class="body">
            <h1><g:message code="default.projectsPIMap.AddProjectsToPI.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsPIMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsPIMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                           
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="investigator"><g:message code="default.Investigator.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsPIMapInstance,field:'investigator','errors')}">
                                    <g:select optionKey="id" optionValue="name" from="${investigatorList}" name="investigator.id" value="${projectsPIMapInstance?.investigator?.id}" ></g:select>
                                </td>
                            </tr> 
                          
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projects"><g:message code="default.Projects.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsPIMapInstance,field:'projects','errors')}">
                                    <strong>  ${projectsInstance.code} </strong> 
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="role"><g:message code="default.Role.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsPIMapInstance,field:'role','errors')}">
                                    <g:select id="role" name="role"  from="${['PI','CO-PI']}" value="${projectsPIMapInstance?.role}" ></g:select>
                                </td>
                            </tr> 
	                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo"><g:message code="default.Active.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsPIMapInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:projectsPIMapInstance,field:'activeYesNo')}" />
                                </td>
	                       	</tr>    
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" /></span>
                </div>
            </g:form>
        </div>
         </td>
         </tr>
        
        
        
       <tr>
    <td>    <div class="body">
            <h1></h1>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        	 <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
                   	        
                   	        <th><g:message code="default.Investigator.label"/></th>
                   	    
                   	        <th><g:message code="default.Projects.label"/></th>
                   	    
                   	        <g:sortableColumn property="role" title="${message(code: 'default.Role.label')}"/>
                        
                        	<g:sortableColumn property="activeYesNo" title="${message(code: 'default.Active.label')}"/>
                        	
                        	<th><g:message code="default.Edit.label"/></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectsPIMapInstanceList}" status="i" var="projectsPIMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1} </td>
                            <td>${fieldValue(bean:projectsPIMapInstance, field:'investigator.name')}</td>
                        
                            <td>${fieldValue(bean:projectsPIMapInstance, field:'projects.code')}</td>
                        
                            <td>${fieldValue(bean:projectsPIMapInstance, field:'role')}</td>
                            
                            <td>
                	             <g:if test="${fieldValue(bean:projectsPIMapInstance, field:'activeYesNo') == 'Y'}">
    							 	<g:message code="default.YES.label"/>
    							 </g:if>
								 <g:else>
	    							 <g:message code="default.NO.label"/>
	    						 </g:else>
                        	 </td>
                        	 
                            <td><g:link action="edit" id="${projectsPIMapInstance.id}"><g:message code="default.Edit.label"/></g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
      </div>
    </body>
</html>
