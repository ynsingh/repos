<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'itemPurchase.label', default: 'ItemPurchase')}" />
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
                            <td valign="top" class="name"><g:message code="itemPurchase.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: itemPurchaseInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="itemPurchase.assetCode.label" default="Asset Code" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: itemPurchaseInstance, field: "assetCode")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="itemPurchase.billNo.label" default="Bill No" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: itemPurchaseInstance, field: "billNo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="itemPurchase.cost.label" default="Cost" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: itemPurchaseInstance, field: "cost")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="itemPurchase.dateReceived.label" default="Date Received" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${itemPurchaseInstance?.dateReceived}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="itemPurchase.name.label" default="Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: itemPurchaseInstance, field: "name")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="itemPurchase.orderNo.label" default="Order No" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: itemPurchaseInstance, field: "orderNo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="itemPurchase.projectId.label" default="Project Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: itemPurchaseInstance, field: "projectId")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="itemPurchase.remarks.label" default="Remarks" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: itemPurchaseInstance, field: "remarks")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${itemPurchaseInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
