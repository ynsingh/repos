

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'evalItem.label', default: 'EvalItem')}" />
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
                            <td valign="top" class="name"><g:message code="evalItem.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: evalItemInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="evalItem.createdDate.label" default="Created Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${evalItemInstance?.createdDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="evalItem.modifiedDate.label" default="Modified Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${evalItemInstance?.modifiedDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="evalItem.activeYesNo.label" default="Active Yes No" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: evalItemInstance, field: "activeYesNo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="evalItem.createdBy.label" default="Created By" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: evalItemInstance, field: "createdBy")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="evalItem.evalScale.label" default="Eval Scale" /></td>
                            
                            <td valign="top" class="value"><g:link controller="evalScale" action="show" id="${evalItemInstance?.evalScale?.id}">${evalItemInstance?.evalScale?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="evalItem.item.label" default="Item" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: evalItemInstance, field: "item")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="evalItem.modifiedBy.label" default="Modified By" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: evalItemInstance, field: "modifiedBy")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="evalItem.proposal.label" default="Proposal" /></td>
                            
                            <td valign="top" class="value"><g:link controller="proposal" action="show" id="${evalItemInstance?.proposal?.id}">${evalItemInstance?.proposal?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${evalItemInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
