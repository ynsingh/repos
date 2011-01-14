<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'default.Eligibility.label')}" />
        <title><g:message code="default.Eligibility.label" /></title>
    </head>
    <body>
    	<div class="wrapper">
        <div class="body">
            <h1><g:message code="default.Eligibility.label" /></h1>
            <table>
            	<tr>
                    	<td valign="top" class="name">
                        	<label for="projects"><g:message code="default.ProposalCode.label"/>:</label>
                        </td>
                        <td valign="top" >
                         	<strong>${fieldValue(bean:proposalInstance,field:'code')} </strong>
                        </td>
                </tr>
            </table>
            <g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${eligibilityCheckList}">
	            <div class="errors">
	                <g:renderErrors bean="${eligibilityCheckList}" as="list" />
	            </div>
            </g:hasErrors>
            <g:form>
                <div class="list">
                	<table>
	                    <thead>
	                        <tr>
	                        
	                        	<g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
	                        
	                            <g:sortableColumn property="eligibilityCriteria" title="${message(code: 'default.eligibilityCriteria.eligibilityCriteria.label')}" />
	                           
	                            <g:sortableColumn property="qualifiedYesNo" title="${message(code: 'default.Qualified.label')}" />
	                            
	                            <g:sortableColumn property="description" title="${message(code: 'default.Description.label')}" />
	                        </tr>
	                    </thead>
                    	<tbody>
                   			<g:each var="i" in="${ (0..< eligibilityCriteriaInstanceList?.size()) }">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}"> 
                                         
                            		<td>${i+1}</td>
                        			
                        			<td>${eligibilityCriteriaInstanceList[i]?.eligibilityCriteria}</td>
                        			<input type="hidden" name="eligibilityCheck${i}.eligibilityCriteria" value="${eligibilityCriteriaInstanceList[i]?.id}"/>
                            
                            		<g:if test="${eligibilityCheckList[i] !=null}">
	                            		<td><g:select name="eligibilityCheck${i}.qualifiedYesNo" from="${['YES', 'NO']}"  value="${eligibilityCheckList[i]?.qualifiedYesNo}"/></td>
	                            
	                            		<td><g:textField name="eligibilityCheck${i}.description" value="${eligibilityCheckList[i]?.description}"/></td>
                         			</g:if>
                         			<g:else>
	                            		<td><g:select name="eligibilityCheck${i}.qualifiedYesNo" from="${['YES', 'NO']}"  value="${eligibilityCheckInstance?.qualifiedYesNo}"/></td>
	                            
	                            		<td><g:textField name="eligibilityCheck${i}.description" value="${eligibilityCheckInstance?.description}"/></td>
                         			
                         			</g:else>
                         			<input type="hidden" name="eligibilityCheck${i}.proposal" value="${proposalInstance?.id}"/>
                        		</tr>
                    		</g:each>
                    	</tbody>
               		 </table>
        		</div>
        		
               <g:if test="${eligibilityStatus == 'Review'}">
       			<div class="dialog">
      
       				<table width="100%">
        				<tr class="prop"> 
				        	<td valign="top" class="name"> <label for="status"><g:message code="default.Status.label"/>:</label></td>
				            
				            <td valign="top" class='value ${hasErrors(bean:eligibilityStatusInstance,field:'eligibilitysStatus','errors')}'>
				   			
					   		<g:radio name="status" value="Accepted"/> <label for=' Accepted '><g:message code="default.Accepted.label"/></label>
					  		<br>
					  		<br>	
					  		<g:radio name="status" value="Rejected" /> <label for=' Rejected '><g:message code="default.Rejected.label"/></label>
				  			</td>
				
				         	<td valign="top" class="name">
				                <label for="description"><g:message code="default.Description.label"/>:</label>
				            </td>
				            
				            <td valign="top" class="value ${hasErrors(bean: eligibilityStatusInstance, field: 'description', 'errors')}">
				            <g:textArea name="description" value="${eligibilityStatusInstance?.description}" rows="3" cols="30"/></td>
				            <input type="hidden" name="eligibilityCheck.proposal" value="${proposalInstance?.id}"/>
				        </tr>
         			</table>
         		</div>
         		
        		<div class="buttons">
        		<input type="hidden" name="eligibilityStatus" value="Review"/>
                     <g:actionSubmit value="${message(code: 'default.Submit.button')}" action="submit"/>
                </div>
                </g:if>
                <g:if test="${eligibilityStatus == 'Reviewed'}">
               	
                </g:if>
    		</g:form>
          </div>
        </div>
    </body>
</html>
