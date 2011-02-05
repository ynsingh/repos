


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="default.editEligibility.label"/></title>
    </head>
    <body>
        <div class="wrapper">
        	<div class="body">
            	<h1><g:message code="default.editEligibility.label" /></h1>
            	<g:if test="${flash.message}">
            		<div class="message">${flash.message}</div>
           	 	</g:if>
            	<g:hasErrors bean="${eligibilityCriteriaInstance}">
		            <div class="errors">
		                <g:renderErrors bean="${eligibilityCriteriaInstance}" as="list" />
		            </div>
	            </g:hasErrors>
            	<g:form method="post" >
	                <g:hiddenField name="id" value="${eligibilityCriteriaInstance?.id}" />
	                <g:hiddenField name="version" value="${eligibilityCriteriaInstance?.version}" />
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        
	                           <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="eligibilityCriteria"><g:message code="default.eligibilityCriteria.eligibilityCriteria.label" />:</label>
	                                  <label for="eligibilityCriteria" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: eligibilityCriteriaInstance, field: 'eligibilityCriteria', 'errors')}">
	                                    <g:textField name="eligibilityCriteria" value="${eligibilityCriteriaInstance?.eligibilityCriteria}" />
	                                </td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="mandatoryYesNo"><g:message code="default.Mandatory.label" />:</label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: eligibilityCriteriaInstance, field: 'mandatoryYesNo', 'errors')}">
	                                <g:select name="mandatoryYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean: eligibilityCriteriaInstance, field: 'mandatoryYesNo')}"/>
	                                
	                                </td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="remarks"><g:message code="default.Remarks.label" />:</label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: eligibilityCriteriaInstance, field: 'remarks', 'errors')}">
	                                   <g:textArea name="remarks" value="${eligibilityCriteriaInstance?.remarks}" rows="3" cols="30"/>
	                                    
	                                </td>
	                            </tr>
	                        
	                        </tbody>
	                    </table>
	                </div>
	                <div class="buttons">
	                    <span class="button"><g:actionSubmit class="save" action="update" onClick="return validateEligibilityCriteria()"  value="${message(code: 'default.Update.button')}" /></span>
	                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.Delete.button')}" 
	                    	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	                </div>
            	</g:form>
        	</div>
        </div>
    </body>
</html>
