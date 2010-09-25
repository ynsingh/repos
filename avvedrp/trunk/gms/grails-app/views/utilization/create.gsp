<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.CreateUtilization.head"/></title>   
    </head>
    <body>
    <div class="wrapper">
        <div class="tablewrapper">
        	<div class="body">
            	<h1><g:message code="default.CreateUtilization.head"/></h1>
            	<g:if test="${flash.message}">
            		<div class="message">${flash.message}</div>
            	</g:if>
            	<g:hasErrors bean="${utilizationInstance}">
            		<div class="errors">
                		<g:renderErrors bean="${utilizationInstance}" as="list" />
            		</div>
            	</g:hasErrors>
             	<g:form action="save" method="post" controller="utilization" 
             		id="${fieldValue(bean:projectInstance, field:'id')}">
              		<div class="dialog">
                    	<table>
                        	<tbody>
                        
		                        <tr class="prop">
		                            <td valign="top" class="name"><g:message code="default.ProjectName.label"/>:</td>
		                            
		                            <td valign="top" class="value">${fieldValue(bean:projectInstance, field:'name')}</td>
	                           </tr>
                        
		                        <tr class="prop">
		                            <td valign="top" class="name"><g:message code="default.ProjectStartDate.label"/>:</td>
		                            <td valign="top" class="value"><g:formatDate format="dd/MM/yyyy" 
		                            		date="${projectInstance.projectStartDate}"/></td>
	                            </tr>
		                        <tr class="prop">
		                            <td valign="top" class="name"><g:message code="default.ProjectEndDate.label"/>:</td>
		                            
		                            <td valign="top" class="value"><g:formatDate format="dd/MM/yyyy" 
		                            		date="${projectInstance.projectEndDate}"/></td>
	                            </tr>
	                            
	                            <tr>
		                        
		                        <td valign="top" class ="name">
		                         <label for ="startDate"><g:message code="default.StartDate.label"/></label>
		                         
		                           <td valign="top" class="value ${hasErrors(bean:utilizationInstance,field:'startDate','errors')}">
                                    <calendar:datePicker name="startDate" value="${utilizationInstance?.startDate}" defaultValue="${new Date()}"  dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr>
                                         
                              <tr>
		                        
		                        <td valign="top" class ="name">
		                         <label for ="endDate"><g:message code="default.EndDate.label"/></label>
		                         
		                           <td valign="top" class="value ${hasErrors(bean:utilizationInstance,field:'endDate','errors')}">
                                    <calendar:datePicker name="endDate" value="${utilizationInstance?.endDate}" defaultValue="${new Date()}"  dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr>
                        		<div>    
		                        <tr class="prop">
	                                <td valign="top" class="name">
	                                	<label><g:message code="default.Documents.label"/>:</label>
		                        	</td>
		                        	<td>
		                        		<g:link  controller='grantAllocation' action="reportView"  id="${projectInstance.id}">
		                        			<g:message code="default.View.label"/>
	                        			</g:link>
		                        	</td>
								 </tr>  
					    	</div>                           
                        </tbody>
                    </table>
                </div>
                <div>
                	<input type="hidden" name="projects.id" value="${projectInstance.id}" />
                    <g:actionSubmit class="inputbutton" value="${message(code: 'default.Submit.button')}" action="save"/>
                </div>
            </g:form>
           </div>
        </div>
       </div>
    </body>
</html>
