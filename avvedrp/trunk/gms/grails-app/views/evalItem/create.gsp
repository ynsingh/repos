<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'default.evalItem.head')}" />
        <title><g:message code="default.evalItem.head" args="[entityName]" /></title>
    </head>
    <body>
      <div class="wrapper">
       	<div class="body">
           	 <h1 align="left"><g:message code="default.evalItem.head" args="[entityName]" /></h1>
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:if test="${flash.error}">
            		<div class="errors">${flash.error}</div>
            	</g:if>
            	<g:hasErrors bean="${evalItemInstance}">
		            <div class="errors">
		                <g:renderErrors bean="${evalItemInstance}" as="list" />
		            </div>
            	</g:hasErrors>
	            <g:form action="save" method="post" >
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                             <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="item"><g:message code="default.evalItem.item.label"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: evalItemInstance, field: 'item', 'errors')}">
	                                    <g:textField name="item" value="${evalItemInstance?.item}" />
	                                </td>
	                           
	                               <td valign="top" class="name">
	                                    <label for="evalScale"><g:message code="default.evalItem.evalScale.label"/></label>
	                               </td>
	                               <td valign="top" class="value ${hasErrors(bean: evalItemInstance, field: 'evalScale', 'errors')}">
	                                    <g:select optionKey="id" optionValue="scaleTitle" from="${(evalScaleInstanceList)}" name="evalScale.id" value="${fieldValue(bean: evalScaleInstance, field: 'scaleTiltle')}" noSelection="['null':'-Select-']" ></g:select>
	                               </td>
	                           </tr>
	                       </tbody>
	                    </table>
	                </div>
	                <div align="left" class="buttons">
	                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.Create.button')}" onClick="return validateEvalItem();"  /></span>
	                </div>
	            </g:form>
         </div>
         <div class="body">
              <g:if test="${evalItemInstanceList}">
	            	<h1 align="left"><g:message code="default.evalItemList.head" args="[entityName]" /></h1>
	            		
	               <div class="list">
	           			<table>
	                    	<thead>
	                        	<tr>
	                        
		                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
		                        
		                            <g:sortableColumn property="item" title="${message(code: 'default.evalItem.item.label')}" />
		                            
		                            <th><g:message code="default.evalItem.evalScale.label"/></th>
		                                                      
		                            <th><g:message code="default.Edit.label" default="Edit" /></th>
		                        
	                        	</tr>
	                       </thead>
	                       <tbody>
		                    	<g:each in="${evalItemInstanceList}" status="i" var="evalItemInstance">
		                        	<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
		                        
			                            <td>${i+1}</td>
			                        
			                            <td>${fieldValue(bean: evalItemInstance, field: "item")}</td>
			                                                    
			                            <td>${fieldValue(bean: evalItemInstance, field: "evalScale.scaleTitle")}</td>
			                        	                            
			                            <td><g:link action="edit" id="${fieldValue(bean:evalItemInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
			                        
		                           </tr>
		                       </g:each>
                    	</tbody>
               	 	</table>
            </g:if>
         </div>
       </div>
     </div>
   </body>
</html>
