<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.CreateUtilization.head"/></title>   
    </head>
    <body>
    <div class="wrapper">
    <g:subMenuList/> 
        <div class="proptable">
        	<div class="body">
        	<table class="tablewrapper" cellspacing="0" cellpadding="0">
        	<tr>
        	<td>
            	<h1><g:message code="default.CreateUtilization.head"/></h1>
            	<g:if test="${flash.message}">
            		<div class="message">${flash.message}</div>
            	</g:if>
            	<g:hasErrors bean="${utilizationInstance}">
            		<div class="errors">
                		<g:renderErrors bean="${utilizationInstance}" as="list" />
            		</div>
            	</g:hasErrors>
             	<g:form action="save" method="post" controller="utilization" name="viewStatementOfAccounts"
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
                                    <calendar:datePicker name="startDate" value="${projectInstance.projectStartDate}" defaultValue="${new Date()}"  dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr>
                                         
                              <tr>
		                        
		                        <td valign="top" class ="name">
		                         <label for ="endDate"><g:message code="default.EndDate.label"/></label>
		                         
		                           <td valign="top" class="value ${hasErrors(bean:utilizationInstance,field:'endDate','errors')}">
                                    <calendar:datePicker name="endDate" value="${utilizationInstance?.endDate}" defaultValue="${new Date()}"  dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr>
                        		                      
                        </tbody>
                    </table>
                </div>
              
                <div>
                	<input type="hidden" name="projects.id" value="${projectInstance.id}" />
                	<g:actionSubmit class="inputbutton" action="showReports"  value="${message(code: 'default.StatementOfAccounts.button')}" onclick="return validateReportViewConfirmPrint();"/>
             	    <g:actionSubmit class="inputbutton" action="utilizationCertificate" value="${message(code: 'default.UtilizationCertificate.button')}" onclick="return validateReportViewConfirmPrint();"/>
                    <g:actionSubmit class="inputbutton" value="${message(code: 'default.Submit.button')}" action="save" onclick="return validateSubmitReportConfirmPrint();"/>
               
              
            </g:form>
           </div>
          
        	<div class="body">
            	<h1><g:message code="default.UtilizationCertificateList.head"/></h1>
           		
            	<g:if test="${utilizationCertificateList}">
            		<div class="list">
                		<table>
                    		<thead>
                       	 		<tr>
                        			<g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                   	        		<g:sortableColumn property="projects" title="${message(code: 'default.Project.label')}" />
                   	        		
                   	        		<g:sortableColumn property="startDate" title="${message(code: 'default.StartDate.label')}" />
                   	        		<g:sortableColumn property="endDate" title="${message(code: 'default.EndDate.label')}" />
                   	        		<g:sortableColumn property="submittedDate" title="${message(code: 'default.SubmittedDate.label')}" />
                   	                <th><g:message code="default.UtilizationCertificate.label"/></th>
                   	        		<th><g:message code="default.StatementOfAccounts.label"/></th>
                   	        		
            	                </tr>
                			</thead>
                    		<tbody>
                    			<g:each in="${utilizationCertificateList}" status="i" var="utilizationInstance">
                        			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
			                            <td>${i+1}</td>
			                        
			                            <td>${fieldValue(bean:utilizationInstance, field:'projects.name')}</td>
			                       		<td><g:formatDate format="dd/MM/yyyy" date="${utilizationInstance.startDate}"/></td>
			                            <td><g:formatDate format="dd/MM/yyyy" date="${utilizationInstance.endDate}"/></td>
			                        	<td><g:formatDate format="dd/MM/yyyy" date="${utilizationInstance.submittedDate}"/></td>
			                           	<td><g:link  controller='grantAllocation' action="utilizationCertificate"  
			                           			params="[id:utilizationInstance.id]" 
			                           			target="_blank"><g:message code="default.View.label"/>
		                           			</g:link>                           
	                           			</td>
										<td><g:link  controller='grantAllocation' action="showReports"  
												params="[id:utilizationInstance.id]" 
												target="_blank"><g:message code="default.View.label"/>
											</g:link>                           
										</td>

                        			</tr>
                    			</g:each>
                    		</tbody>
                		</table>
            		</div>
            	</g:if>
	            <g:else>
	            	<br><g:message code="default.NoRecordsAvailable.label"/></br>
	            </g:else>
        	</div>
        </div>
           </td>
          </tr> 
         </table>
       </div>
    </body>
</html>
