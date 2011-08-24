<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'default.evalScaleOptions.head')}" />
        <title><g:message code="default.evalScaleOptions.head" args="[entityName]" /></title>
    </head>
    <body>
      <div class="wrapper"> 
           <div class="body">
	            <h1 align="left"><g:message code="default.evalScaleOptions.head" args="[entityName]" /></h1>
		            <g:if test="${flash.message}">
		            	<div class="message">${flash.message}</div>
		            </g:if>
	            <g:hasErrors bean="${evalScaleOptionsInstance}">
		            <div class="errors">
		                <g:renderErrors bean="${evalScaleOptionsInstance}" as="list" />
		            </div>
	            </g:hasErrors>
	            <g:form action="save" method="post" >
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        
	                           <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="evalScale"><g:message code="default.evalScaleOptions.evalScale.label"/>:</label>
	                                    <label for="evalScale" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: evalScaleOptionsInstance, field: 'evalScale', 'errors')}">
	                                   <g:select optionKey="id" optionValue="scaleTitle" from="${(evalScaleInstanceList)}" name="evalScale.id" value="${fieldValue(bean: evalScaleOptionsInstance, field: 'evalScale')}" noSelection="['null':'-Select-']" ></g:select>
	                                </td>
	                                
	                                <td valign="top" class="name">
	                                    <label for="scaleOption"><g:message code="default.evalScaleOptions.scaleOption.label"/>:</label>
	                                    <label for="scaleOption" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: evalScaleOptionsInstance, field: 'scaleOption', 'errors')}">
	                                    <g:textField name="scaleOption" value="${evalScaleOptionsInstance?.scaleOption}" />
	                                </td>
	                            </tr>
	                       
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="scaleOptionIndex"><g:message code="default.evalScaleOptions.scaleOptionIndex.label"/>:</label>
	                                    <label for="scaleOptionIndex" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: evalScaleOptionsInstance, field: 'scaleOptionIndex', 'errors')}">
	                                    <g:textField name="scaleOptionIndex" value="${fieldValue(bean: evalScaleOptionsInstance, field: 'scaleOptionIndex')}" />
	                                </td>
	                           </tr>
	                        </tbody>
	                    </table>
	                </div>
	                <div align="left" class="buttons">
	                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.Create.button')}" onClick="return validateEvalScaleOptions();" /></span>
	                </div>
	            </g:form>
	        </div>
	        
	        <div class="body">
	          <g:if test="${evalScaleOptionsInstanceList}">
	            <h1 align="left"><g:message code="default.evalScaleOptionsList.head" args="[entityName]" /></h1>
		        
	            <div class="list">
	               <table>
	                    <thead>
	                        <tr>
	                        
	                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
	                   		    
	                   		    <th><g:message code="default.evalScaleOptions.evalScale.label"/></th>
	                   		    
	                   		    <th><g:message code="default.evalScaleOptions.scaleOption.label"/></th> 
	                   		    
	                   		    <th><g:message code="default.evalScaleOptions.scaleOptionIndex.label"/></th>
	                   		                       
	                            <th><g:message code="default.Edit.label" default="Edit" /></th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    <g:each in="${evalScaleOptionsInstanceList}" status="i" var="evalScaleOptionsInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                         
	                            <td>${i+1}</td>
	                           
	                            <td>${fieldValue(bean: evalScaleOptionsInstance, field: 'evalScale.scaleTitle')}</td>
	                                             
	                            <td>${fieldValue(bean: evalScaleOptionsInstance, field: 'scaleOption')}</td>
	     
	                            <td>${fieldValue(bean: evalScaleOptionsInstance, field: 'scaleOptionIndex')}</td>
	 
	                            <td><g:link action="edit" id="${fieldValue(bean:evalScaleOptionsInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
	                        
	                       </tr>
	                    </g:each>
	                    </tbody>
	                </table>
	             </g:if>
            </div>
        </div>
     </body>
</html>
