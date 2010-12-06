

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.projects.Search.head"/></title>         
    </head>
    <body>
    <div class="wrapper">
      
        <div class="body">
            <h1><g:message code="default.projects.Search.head"/>&nbsp;<img src="${createLinkTo(dir:'images/themesky',file:'search_img.png')}"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="searchProjects" method="post" >
                <fieldset>
                
                <p>&nbsp;</p>
                    <table>
                        <tbody>
                        
                        <tr class="prop">
                        	<td valign="top">
                                   &nbsp;
                                </td>
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
                                <input type="hidden"  id="projectType" name="projectType.id" value="${projectsInstance?.projectType?.id}"/>
                                <input type="hidden"  id="investigator.id" name="investigator.id" value="${projectsInstance?.investigator?.id}"/>
                                <input type="hidden"  id="projectStartDate" name="projectStartDate" value="${projectsInstance?.projectStartDate}"/>
                                <input type="hidden"  id="projectStartDateTo" name="projectStartDateTo" value="${projectsInstance?.projectStartDate}"/>
                                <input type="hidden"  id="projectEndDate" name="projectEndDate" value="${projectsInstance?.projectEndDate}"/>
                                <input type="hidden"  id="projectEndDateTo" name="projectEndDateTo" value="${projectsInstance?.projectEndDate}"/>
                                <input type="hidden"  id="projectStatus" name="projectStatus" value="null"/>
                            </tr> 
                        	
                        	                                                        
                        </tbody>
                    </table>
                    		
                    		<p>&nbsp;</p>
			      			<p ALIGN=CENTER>&nbsp;<g:submitToRemote class="searchButton" value="                              " onClick="" action="searchProjects" update="search" />
			      			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			      			<g:actionSubmit value="${message(code: 'default.AdvancedSearch.button')}" action="advancedSearchProjects" id="1" />
			      			<g:actionSubmit value="${message(code: 'default.GrantSearch.button')}" action="grantSearch" id="1" /></p>
			    			<p ALIGN=RIGHT>&nbsp;&nbsp;</p>
               
                </fieldset>
             </g:form>
        </div>
        <div id="search">
        <g:if test="${grantAllocationInstanceList}">
        <div class="body">
             <div class="list">
                  <table>
                    <thead>
  						<tr>
                        
                   	        <th><g:message code="default.SINo.label"/></th>
                   	                              
                   	        <th><g:message code="default.Name.label"/></th>
                                              
                   	        <th><g:message code="default.Code.label"/></th>
                	       
                   	       	<th><g:message code="default.ProjectType.label"/></th>
                   	       	
                   	       	<th><g:message code="default.StartDate.label"/></th>
                   	       	 
                   	       	<th><g:message code="default.EndDate.label"/></th>

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
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
           <div class="paginateButtons">
               
            </div> 
        </div>
         </g:if>
        </div></div>
    </body>
</html>
