<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projectEmployee.label', default: 'ProjectEmployee')}" />
        <title><g:message code="default.projectEmployees.title" /></title>
   		<script type="text/javascript">
	    </script>
    </head>
     
    <body>
	    <div class="wrapper">   
	        <div class="body">
				<h1><g:message code="default.projectEmployees.create.head" /></h1>
	            <g:if test="${flash.message}">
	           		<div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${projectEmployeeInstance}"></g:hasErrors>
	            <g:form action="save" method="post" >
	                <div class="dialog">
	                    <table>
	                    	<tbody>
				  				<tr>
						    		<td><label><g:message code="default.ProjectName.label" /></label></td>
						    		<td><strong>${projectInstance.name}</strong></td>
					    			<input type="hidden" name="projectId" value="${projectInstance.id}"/>
					    		
						    		<td><label><g:message code="default.EmployeeName.label" /></label></td>
						    		<td><g:textField name="empName" value="${projectEmployeeInstance?.empName}"/></td>
					 		    </tr>
					  			<tr>
						  		    <td ><label><g:message code="default.projectEmployees.EmployeeNumber.label"/></label></td>
						    		<td><g:textField name="empNo" value="${projectEmployeeInstance?.empNo}"/></td>
						    		<td><label><g:message code="default.projectEmployees.DateOfBirth.label"/></label></td>
						    		<td><calendar:datePicker name="DOB" value="${projectEmployeeInstance?.dOB}" /></td>
					  			</tr>
					  			<tr>
						    		<td><label><g:message code="default.JoiningDate.label"/></label></td>
						    		<td><calendar:datePicker name="joiningDate" value="${projectEmployeeInstance?.joiningDate}" 
						    			defaultValue="${new Date()}" dateFormat= "%d/%m/%Y"/></td>
						   			<td><label><g:message code="default.RelievingDate.label"/></label></td>
						      		<td><calendar:datePicker name="relievingDate" value="${projectEmployeeInstance?.relievingDate}" 
						      			defaultValue="${new Date()}" dateFormat= "%d/%m/%Y"/></td>
					  			</tr>
					  			<tr>
						   			<td> <label><g:message code="default.Designation.label"/></label></td>
						   			<td><g:select name='employeeDesignation.id' optionKey="id" optionValue="Designation" 
						   				from="${employeeDesignationInstance}" value="${projectEmployeeInstance?.employeeDesignation?.id}" 
						   				noSelection="['null':'select']"></g:select></td>
						   			<td><label><g:message code="default.Active.label"/></label></td>
						   			<td><g:select name="status" from="${['Y','N']}" /> </td>			      		
					       		</tr>
	             			</tbody>
	  					</table>
	  				</div>
		  			<div class="buttons">
						<span class="button"><input class="save" type="submit"  onClick="return validateProjectEmployee()"  
							value ="${message(code: 'default.Save.button')}"/></span>
		            </div>
	  			</g:form>
			</div>

  		  	<div class="body">
            	<div class="list">
            		<g:if test="${projectEmployeeInstanceList}">
	                	<table>
	                   	 	<thead>
	  							<tr>
									<g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
		                       
		                   	        <g:sortableColumn property="empNo" title="${message(code: 'default.EmpNo.label')}" />
		                                              
		                   	        <g:sortableColumn property="empName" title="${message(code: 'default.Name.label')}" />
		                	       
		                   	       	<g:sortableColumn property="employeeDesignation.Designation" title="${message(code: 'default.Designation.label')}" />
		                   	       	
		                   	       	<th><g:message code="default.Edit.label"/></th>
								    
								    <th><g:message code="default.Action.label"/></th>
								    
								    <th><g:message code="default.Action.label"/></th>
								    
								    <th><g:message code="default.Action.label"/></th>
		                   	      
		                        </tr>
							</thead>
							<tbody>	
								<g:each in="${projectEmployeeInstanceList}" status="i" var="projectEmployeeInstance">
							 		<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
										<td>${i+1}</td>
								        
								        <td>${fieldValue(bean: projectEmployeeInstance, field: "empNo")}</td>
								        
								        <td>${fieldValue(bean: projectEmployeeInstance, field: "empName")}</td>
								        
								        <td>${projectEmployeeInstance.employeeDesignation.Designation}</td>
			        			 		<g:hiddenField name="id" value="${projectEmployeeInstance?.id}" />
			       
			        					<td><g:link controller="projectEmployee" action="edit" id="${projectEmployeeInstance.id}"><g:message code="default.Edit.label" /></g:link></td>
							
								        <td><g:link controller="projectEmployeeQualification" action="create" id="${projectEmployeeInstance.id}">
								        <g:message code="default.Qualification.label" /></g:link></td>
										
										<td><g:link controller="projectEmployeeExperience" action="create" id="${projectEmployeeInstance.id}">
										<g:message code="default.Experience.label" /></g:link></td>
			        					
			        					<td><g:link controller="projectEmployeeSalaryDetails" action="create" id="${projectEmployeeInstance.id}">
			        					<g:message code="default.Salary.label" /></g:link></td>
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
