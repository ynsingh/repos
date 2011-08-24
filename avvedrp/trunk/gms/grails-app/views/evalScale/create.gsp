<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'default.evalScale.head')}" />
        <title><g:message code="default.evalScale.head" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
          <div class="wrapper"> 
            <h1 align="left"><g:message code="default.evalScale.head" args="[entityName]" /></h1>
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
            <g:hasErrors bean="${evalScaleInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${evalScaleInstance}" as="list" />
	            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
	                        <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="scaleTitle"><g:message code="default.evalScale.scaleTitle.label"/>:</label>
	                                    <label for="scaleTitle" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: evalScaleInstance, field: 'scaleTitle', 'errors')}">
	                                    <g:textField name="scaleTitle" value="${evalScaleInstance?.scaleTitle}" />
	                                </td>
	                         </tr> 
                       </tbody>
                    </table>
                </div>
                <div align="left" class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.Create.button')}" onClick="return validateEvalScale();" /></span>
                </div>
            </g:form>
        </div>
        
          <div class="body">
               <g:if test="${evalScaleInstanceList}">
          			<h1 align="left"><g:message code="default.evaluationScaleList.head" args="[entityName]" /></h1>
	        		<div class="list">
	                	<table>
		                    <thead>
		                        <tr>
		                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
		                            <g:sortableColumn property="scaleTitle" title="${message(code: 'default.evalScale.scaleTitle.label')}" />
		                           	<th><g:message code="default.Edit.label"/></th>
		                        </tr>
		                    </thead>
                            <tbody>
			                    <g:each in="${evalScaleInstanceList}" status="i" var="evalScaleInstance">
			                         <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			                            <td>${i+1}</td>
			                            <td>${fieldValue(bean: evalScaleInstance, field: "scaleTitle")}</td>
			                            <td><g:link action="edit" id="${fieldValue(bean:evalScaleInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
			                        </tr>
			                    </g:each>
                    		</tbody>
                		</table>
            	  </div>
                </g:if>
             </div>  
          </div> 
    </body>
</html>
