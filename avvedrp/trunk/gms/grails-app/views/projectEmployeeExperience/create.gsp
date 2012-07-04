<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" 
        	value="${message(code: 'projectEmployeeExperience.label', default: 'ProjectEmployeeExperience')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <script type="text/javascript">
		</script>
	</head>
    <body>
    	<div class="wrapper"> 
    	<g:subMenuList/>   
			<div class="body">
      	<img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
        		<h1><g:message code="default.projectEmployeeExperience.create.head" /></h1>
	           	<g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${projectEmployeeExperienceInstance}">
		            <div class="errors">
		               	<g:renderErrors bean="${projectEmployeeExperienceInstance}" as="list" />
		            </div>
	            </g:hasErrors>
	            <g:form action="save" method="post" >
	            	<div class="dialog">
	        			<table>
	        				<tbody>
  								<tr>
			    					<td><label><g:message code="default.EmployeeName.label" /></label>:</td>
			    					<td>${fieldValue(bean: projectEmployeeInstance,field: "empName")}
			    					    | ${fieldValue(bean: projectEmployeeInstance,field: "empNo")}
								     </td>
			    					 <input type="hidden" name="projectEmployee.id" id="projectEmployee.id" 
			    						value="${projectEmployeeInstance.id}"/>
			    					<td><label><g:message code="default.Designation.label" /></label>:</td>
									<td>${projectEmployeeInstance.employeeDesignation.Designation}</td>
			 		    		</tr>
 		   						<tr>
			  		    			<td><label><g:message code="default.projectEmployeeExperience.OrganizationName.label"/>
			  		    				</label>:
			  		    				<label for="OrganizationName" style="color:red;font-weight:bold"> * </label>
			  		    				</td>
			    		 			<td valign="top" class="value ${hasErrors(bean: projectEmployeeExperienceInstance,
					 					field: 'organizationName', 'errors')}">
			                         	<g:textField name="organizationName" 
			                         	value="${projectEmployeeExperienceInstance?.organizationName}" />
			                        </td>
			    					<td><label><g:message code="default.JoiningDate.label" /></label>:</td>
			    					<td><calendar:datePicker name="joiningDate" defaultValue="${new Date()}" 
			    						dateFormat="%d/%m/%Y" />
		    						</td>
			  					</tr>
			  					<tr>
			    					<td><label><g:message code="default.RelievingDate.label" /></label>:</td>
			    					<td><calendar:datePicker name="relievingDate" defaultValue="${new Date()}" 
			    						dateFormat="%d/%m/%Y"/>
		    						</td>
			   						<td><label><g:message code="default.Designation.label" /></label>:
			   						<label for="Designation" style="color:red;font-weight:bold"> * </label>
			   						</td>
			      					<td><g:textField style='width: 180px; ' name="designation" 
			      						value="${projectEmployeeExperienceInstance?.designation}" /></td>
			  					</tr>
			  					<!---============================15-11-2010=============================
			  					<tr>
			   						<td><label><g:message code="default.Active.label" /></label></td>
			   						<td><g:select name="status" from="${['Y','N']}" /> </td>
			   					</tr>
			   					=================================================================--->
				   			</tbody>
			   			</table>
		   			</div>	
    		  		<div class="buttons">
			   			<span class="button"><g:submitButton name="create" 
			   				class="save" onClick="return validateProjectEmployeeExperience()" 
			   				value="${message(code: 'default.Save.button')}" />
		   				</span>
       				</div>
				</g:form>
			</div>
		
		<div class="body">
            <div class="list">
                <g:if test="${projectEmployeeExperienceInstanceList}">	
                	<table> 
            			<thead>
	  						<tr>
	  							<g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
	                       
	                   	        <g:sortableColumn property="organizationName" 
	                   	        	title="${message(code: 'default.projectEmployeeExperience.OrganizationName.label')}" />
	                   	        
	                   	        <g:sortableColumn property="designation" 
	                   	        	title="${message(code: 'default.Designation.label')}" />
	                                              
	                   	        <g:sortableColumn property="projectEmployeeExperienceInstance.joiningDate" 
	                   	        	title="${message(code: 'default.JoiningDate.label')}" />
	                	       
	                   	       	<g:sortableColumn property="projectEmployeeExperienceInstance.relievingDate" 
	                   	       		title="${message(code: 'default.RelievingDate.label')}"/>
	                   	       	
	                   	       	<th><g:message code="default.Edit.label" /></th>
                   	       	</tr>
       	   				</thead>
               			<tbody>	
	   						<g:each in="${projectEmployeeExperienceInstanceList}" 
	   								status="i" var="projectEmployeeExperienceInstance">
                				<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td>${i+1}</td>      
                        	<td>${fieldValue(bean: projectEmployeeExperienceInstance, field: "organizationName")}</td>
                        	
                        	<td>${fieldValue(bean: projectEmployeeExperienceInstance, field: "designation")}</td>
                            
                            <td><g:formatDate format="dd/MM/yyyy" date="${projectEmployeeExperienceInstance.joiningDate}"/> </td>
                            
                            <td><g:formatDate format="dd/MM/yyyy" date="${projectEmployeeExperienceInstance.relievingDate}"/> </td>
                        	<g:hiddenField name="id" value="${projectEmployeeExperienceInstance?.id}" />
	         	         	<input type="hidden" name="projectEmployee.id" id="projectEmployee.id" 
	         	         	value="${projectEmployeeInstance.id}"/>
	         				
	         				<td><g:link  action="edit" params="['projectEmployee.id':projectEmployeeInstance.id]" 
	        					id="${fieldValue(bean:projectEmployeeExperienceInstance,field:'id')}">
	        						<g:message code="default.Edit.label" /></g:link>
        					</td>
                        </tr>
                      </g:each>
                    </tbody>	
				</table>
              </g:if>
     		  <g:else>
        		<br><g:message code="default.NoRecordsAvailable.label"/></br>
         	 </g:else>
	 	  </div>
		</div>
	 </div>
  </body>
</html>
