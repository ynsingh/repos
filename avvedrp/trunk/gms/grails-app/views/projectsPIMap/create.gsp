

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.projectsPIMap.AddProjectsToPI.head"/></title>         
    </head>
    <body>
    <g:subMenuList/>
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
                                    <g:select optionKey="id" optionValue="fullName" from="${investigatorList}" name="investigator.id" value="${projectsPIMapInstance?.investigator?.id}" noSelection="['null':'-Select-']" ></g:select>
                                </td>
                            </tr> 
                          
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projects"><g:message code="default.Projects.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsPIMapInstance,field:'projects','errors')}">
                                    <strong>  ${projectsInstance.code} </strong> 
                                    <input type="hidden" name="id" value="${projectsInstance.id}">
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="role"><g:message code="default.Role.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsPIMapInstance,field:'role','errors')}">
                                    <g:select id="role" name="role"  from="${['CO-PI','PI']}" value="${projectsPIMapInstance?.role}" noSelection="['null':'-Select-']" ></g:select>
                                </td>
                            </tr> 
                           
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" onClick="return validateProjectPIMap()"/></span>
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
                   	        
                             
                        	<th><g:message code="default.Delete.label"/></th>
                        	
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectsPIMapInstanceList}" status="i" var="projectsPIMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1} </td>
                            <td>${fieldValue(bean:projectsPIMapInstance, field:'investigator.name')}&nbsp;${projectsPIMapInstance.investigator.userSurName}</td>
                        
                            <td>${fieldValue(bean:projectsPIMapInstance, field:'projects.code')}</td>
                        
                            <td>${fieldValue(bean:projectsPIMapInstance, field:'role')}</td>
                            
                             <g:if test="${projectsPIMapInstance.role == 'CO-PI'}" >
                            <g:form>
			                        	<input type="hidden" name="id" value="${projectsPIMapInstance?.id}" />
			                        	
			                        	<td> <g:actionSubmit class="delete" action="delete" onclick="return confirm('Are you sure?');" value="${message(code: 'default.Delete.button')}" /></td>
			                        	</g:form>
                          
                          </g:if>
                          <g:else>
                          <td></td>
                          </g:else>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
      </div>
    </body>
</html>
