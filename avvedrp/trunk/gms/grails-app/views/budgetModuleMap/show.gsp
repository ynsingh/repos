

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'budgetModuleMap.label', default: 'BudgetModuleMap')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="budgetModuleMap.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: budgetModuleMapInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="budgetModuleMap.budgetMaster.label" default="Budget Master" /></td>
                            
                            <td valign="top" class="value"><g:link controller="budgetMaster" action="show" id="${budgetModuleMapInstance?.budgetMaster?.id}">${budgetModuleMapInstance?.budgetMaster?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="budgetModuleMap.moduleType.label" default="Module Type" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: budgetModuleMapInstance, field: "moduleType")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="budgetModuleMap.moduleTypeId.label" default="Module Type Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: budgetModuleMapInstance, field: "moduleTypeId")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="budgetModuleMap.activeYesNo.label" default="Active Yes No" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: budgetModuleMapInstance, field: "activeYesNo")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${budgetModuleMapInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
