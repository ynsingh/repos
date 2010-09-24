

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.projectsDepartmentMap.AddProjectsToDepartment.head"/></title>         
    </head>
    <body>
    <g:subMenuProjects/>
    <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.projectsDepartmentMap.AddProjectsToDepartment.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectDepartmentMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectDepartmentMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projects"><g:message code="default.Projects.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectDepartmentMapInstance,field:'projects','errors')}">
                                   <strong>${projectsInstance.code}</strong>
                                </td>
                                <td></td>
                                <td></td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="partyDepartment"><g:message code="default.Department.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectDepartmentMapInstance,field:'partyDepartment','errors')}">
                                    <g:select optionKey="id" optionValue="departmentCode" from="${partyDepartmentList}" name="partyDepartment.id" value="${projectDepartmentMapInstance?.partyDepartment?.id}" ></g:select>
                                </td>
                                <td></td>
                                <td></td>
                            </tr> 
                        	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo"><g:message code="default.Active.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectDepartmentMapInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:projectDepartmentMapInstance,field:'activeYesNo')}" />
                                </td>
                                <td></td>
                                <td></td>
	                       	</tr>  
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" /></span>
                </div>
            </g:form>
        	</div>
        	  <div class="nav">
        </div>
        	<div class="body">
            <h1></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test='${projectDepartmentMapInstanceList}'>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
                            <th><g:message code="default.Projects.label"/></th>
                   	    	<th><g:message code="default.Department.label"/></th>
                   	    	<th><g:message code="default.Institution.label"/></th>
                   	    	<g:sortableColumn property="activeYesNo" title="${message(code: 'default.Active.label')}" />
                   	    	<th><g:message code="default.Edit.label"/></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectDepartmentMapInstanceList}" status="i" var="projectDepartmentMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${(i + 1)}</td>
                      
                            <td>${fieldValue(bean:projectDepartmentMapInstance, field:'projects.code')}</td>
                                                    	<td>${fieldValue(bean:projectDepartmentMapInstance, field:'partyDepartment.departmentCode')}</td>
                        	<td>${fieldValue(bean:projectDepartmentMapInstance, field:'partyDepartment.party.code')}</td>
                            
                            <td>
                	             <g:if test="${fieldValue(bean:projectDepartmentMapInstance, field:'activeYesNo') == 'Y'}">
    							 	<g:message code="default.YES.label"/>
    							 </g:if>
								 <g:else>
	    							 <g:message code="default.NO.label"/>
	    						 </g:else>
                        	 </td>
                            
                            <td><g:link action="edit" id="${fieldValue(bean:projectDepartmentMapInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                
            </div>
            </g:if>
                <g:else>
                <br>
                <g:message code="default.NoRecordsAvailable.label"/></br>
                </g:else>
            
        </div>
      	</div>
    </body>
</html>
