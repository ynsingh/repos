<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" 
        	value="${message(code: 'employeeDesignation.label', default: 'EmployeeDesignation')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
       <div class="wrapper"> 
	       <div class="body">
	        	<h1><g:message code="default.CreateEmployeeDesignation.create.head"/></h1>
	            <g:hasErrors bean="${employeeDesignationInstance}">
	            	<div class="errors">
	                	<g:renderErrors bean="${employeeDesignationInstance}" as="list" />
	            	</div>
	            </g:hasErrors>
	            <g:form action="save" method="post" >
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="designation"><g:message code="default.Designation.label" />
	                                	</label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: employeeDesignationInstance, 
	                                	field: 'designation', 'errors')}">
	                                    <g:textField id="designation" name="designation" 
	                                    	value="${employeeDesignationInstance?.designation}" />
	                                </td>
	                        	</tr>
	                        </tbody>
	                    </table>
	            	 </div>
	        	
                 	<div class="buttons">
	                    <span class="button"><g:submitButton name="create" class="save" 
	                    	value="${message(code: 'default.Create.button')}" onClick="return validateEmployeeDesignation()" />
	                	</span>
                	</div>
                 </div>
             </g:form>
       		
       		<div class="body">
       		<g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
       		<g:if test="${employeeDesignationInstanceList}">
	       		<div class="list">
	                <table>
	                    <thead>
	                        <tr>
	                        	<g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
	                        	
	                            <g:sortableColumn property="designation" 
	                            	title="${message(code: 'default.Designation.label')}" />
	                        
	                        	<th><g:message code="default.Edit.label"/></th>
	                        </tr>
	                    </thead>
	                    <tbody>
		                    <g:each in="${employeeDesignationInstanceList}" status="i" var="employeeDesignationInstance">
		                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
		                        	
		                        	<td>${i+1}</td>
		                                                                         
		                            <td>${fieldValue(bean: employeeDesignationInstance, field: "designation")}</td>
		                            
		                            <td><g:link action="edit" id="${employeeDesignationInstance.id}">
		                            		<g:message code="default.Edit.label"/>
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
    </body>
</html>
