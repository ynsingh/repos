


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="default.eligibilityCriteria.eligibilityCriteria.label"/></title>
    </head>
    <body>
        <div class="wrapper">
	        <div class="body">
	            <h1><g:message code="default.eligibilityCriteria.eligibilityCriteria.label"/></h1>
	            <g:hasErrors bean="${eligibilityCriteriaInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${eligibilityCriteriaInstance}" as="list" />
	            </div>
	            </g:hasErrors>
	            <g:form action="save" method="post" >
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="eligibilityCriteria"><g:message code="default.eligibilityCriteria.eligibilityCriteria.label"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: eligibilityCriteriaInstance, field: 'eligibilityCriteria', 'errors')}">
	                                    <g:textField name="eligibilityCriteria" value="${eligibilityCriteriaInstance?.eligibilityCriteria}" />
	                                </td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="mandatoryYesNo"><g:message code="default.Mandatory.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: eligibilityCriteriaInstance, field: 'mandatoryYesNo', 'errors')}">
	                                    <g:select name="mandatoryYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean: eligibilityCriteriaInstance, field: 'mandatoryYesNo')}"/>
	                                    
	                                </td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="remarks"><g:message code="default.Remarks.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: eligibilityCriteriaInstance, field: 'remarks', 'errors')}">
	                                <g:textArea name="remarks" value="${eligibilityCriteriaInstance?.remarks}" rows="3" cols="30"/>
	                                   
	                                </td>
	                            </tr>
	                        
	                        </tbody>
	                    </table>
	                </div>
	                <div class="buttons">
	                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.Create.button')}" onClick="return validateEligibilityCriteria()" /></span>
	                </div>
	            </g:form>
	        </div>
	      	<div class="body">
            	<g:if test="${flash.message}">
            		<div class="message">${flash.message}</div>
            	</g:if>
            	<div class="list">
            		<g:if test="${eligibilityCriteriaInstanceList}">
                		<table>
                			<thead>
		                        <tr>
		                        
		                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
		                        
		                            <g:sortableColumn property="eligibilityCriteria" title="${message(code: 'default.eligibilityCriteria.eligibilityCriteria.label')}" />
		                        
		                            <g:sortableColumn property="mandatoryYesNo" title="${message(code: 'default.Mandatory.label')}" />
		                        
		                            <g:sortableColumn property="remarks" title="${message(code: 'default.Remarks.label')}" />
		                        	<th>
			                            <g:message code="default.Edit.label"/>
			                          </th>
		                        </tr>
                    		</thead>
                    		<tbody>
		                    	<g:each in="${eligibilityCriteriaInstanceList}" status="i" var="eligibilityCriteriaInstance">
		                        	<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
		                        		<td>${i+1}</td>
		                            
		                            	<td>${fieldValue(bean: eligibilityCriteriaInstance, field: "eligibilityCriteria")}</td>
		                        
		                            	<td>${fieldValue(bean: eligibilityCriteriaInstance, field: "mandatoryYesNo")}</td>
		                        
		                            	<td>${fieldValue(bean: eligibilityCriteriaInstance, field: "remarks")}</td>
		                        	
		                        		<td><g:link action="edit" id="${eligibilityCriteriaInstance.id}"><g:message code="default.Edit.label"/></g:link></td>
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
