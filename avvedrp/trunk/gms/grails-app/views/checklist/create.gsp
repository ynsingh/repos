


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'checklist.label', default: 'Checklist')}" />
        <title><g:message code="default.checklist.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="wrapper">
         <div class="body">
         <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
            <h1><g:message code="default.checklist.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${checklistInstance}">
            <div class="errors">
                <g:renderErrors bean="${checklistInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="field"><g:message code="default.Field.label" /></label>
                                    <label for="field" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: checklistInstance, field: 'field', 'errors')}">
                                    <g:textField name="field" value="${checklistInstance?.field}" />
                                </td>
                            </tr>
                           
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label')}" onClick="return validateCheckLists()"/></span>
                </div>
            </g:form>
        </div>
        
        <div class="body">
         <h1><g:message code="default.CheckListList.label" args="[entityName]" /></h1>
         
         <div class="list">
          <g:if test="${checkListList}">
                <table>
                    <thead>
                        <tr>
                     
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                           
                            <th><g:message code="default.Field.label"/></th>
	                            
	                        <g:sortableColumn property="edit" title="${message(code: 'default.Edit.label')}" />
                         
                        </tr>
                    </thead>
                    <tbody>
                   
                    <g:each in="${checkListList}" status="i" var="checkListInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                            
                            <td>${fieldValue(bean: checkListInstance, field: "field")}</td>
                            
                            <td><g:link action="edit" id="${fieldValue(bean:checkListInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
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
