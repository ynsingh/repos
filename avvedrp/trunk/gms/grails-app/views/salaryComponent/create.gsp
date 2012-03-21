<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="wrapper"> 
	        <div class="body">
	            <h1>
	            	<g:message code = "default.CreateSalaryComponent.create.head"/>
	            </h1>
	            <g:if test="${flash.message}">
            	   <div class="message">${flash.message}</div>
                </g:if>
                <g:hasErrors bean="${salaryComponentInstance}">
		            <div class="errors">
		                <g:renderErrors bean="${salaryComponentInstance}" as="list" />
		            </div>
	            </g:hasErrors>
	            <g:form action="save" method="post" >
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="name"><g:message code="default.SalaryComponent.label" />:</label>
	                                    <label for="name" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                
	                                <td valign="top" class="value ${hasErrors(bean: salaryComponentInstance, field: 'name', 'errors')}">
	                                    <input type="text" id="name" name="name" value="${salaryComponentInstance?.name}" />
	                                </td>
	                            </tr>
	                            
	                        </tbody>
	                    </table>
	                </div>
	                <div class="buttons">
	                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.Create.button')}" onClick="return validateSalaryComponent()"/>
                    	</span>
	                </div>
	            </g:form>
	        </div>
        </div>
        <div class="wrapper">
            <div class="body">
		     <g:if test="${salaryComponentInstanceList}">
            	   <div class="list">
            	      <table>
                    	<thead>
                        	<tr>
                              	<g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
	                        
	                            <g:sortableColumn property="name" title="${message(code: 'default.Name.label')}" />
	                          <th>
	                            <g:message code="default.Edit.label"/>
	                          </th>
                        	 </tr>
                    	</thead>
	                    <tbody>
	                    	<g:each in="${salaryComponentInstanceList}" status="i" var="salaryComponentInstance">
	                        	<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                        
                            		<td>${i+1}</td>
                            		                       
                            		<td>${fieldValue(bean: salaryComponentInstance, field: "name")}</td>
                        
                        			<td><g:link action="edit" id="${salaryComponentInstance.id}">
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
    </body>
</html>
