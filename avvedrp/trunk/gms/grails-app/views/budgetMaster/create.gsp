


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'default.budgetMaster.label')}" />
        <title><g:message code="default.Budgetmastercreate.label" args="[entityName]" /></title>
    </head>
    <body>
       <div class ="wrapper">
        <div class="body">
            <h1><g:message code="default.Budgetmastercreate.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${budgetMasterInstance}">
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                         
                           
                            <tr class="prop">
                              <td valign="top" class="name">
                                    <label for="party"><g:message code="default.budgetMaster.party.label" /></label>
                                </td>
                                 <td valign="top" class="value ${hasErrors(bean:budgetMasterInstance, field:'party', 'errors')}">
                                     <strong>  ${partyInstance.code} </strong>
                                     <input type="hidden" id="party.id" name="party.id" value="${fieldValue(bean:partyInstance,field:'id')}"/>
                                 </td>  
                            
                               <td valign="top" class="name">
                                    <label for="financialYear"><g:message code="default.budgetMaster.financialYear.label" /></label>
                                    <label for="financialYear" style="color:red;font-weight:bold"> * </label> 
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetMasterInstance, field: 'financialPeriod', 'errors')}">
                                    <g:select id="financialYear.id" name="financialYear.id" from="${FinancialYear.list()}" optionKey="id" optionValue="financialPeriod" value="${budgetMasterInstance?.financialYear?.id}" noSelection="['null': '-Select-']" />
                                </td>
                               
                            </tr>
                       
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="budgetCode"><g:message code="default.budgetMaster.budgetCode.label" /></label>
                                    <label for="budgetCode" style="color:red;font-weight:bold"> * </label> 
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetMasterInstance, field: 'budgetCode', 'errors')}">
                                    <g:textField id="budgetCode" name="budgetCode" value="${budgetMasterInstance?.budgetCode}" />
                                </td>
                                
                               <td valign="top" class="name">
                                    <label for="title"><g:message code="default.budgetMaster.title.label" /></label>
                                    <label for="title" style="color:red;font-weight:bold"> * </label> 
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetMasterInstance, field: 'title', 'errors')}">
                                    <g:textField id="title" name="title" value="${budgetMasterInstance?.title}" />
                                </td>
                                
                            </tr>
                        
                            <tr class="prop">
                                 <td valign="top" class="name">
                                    <label for="totalBudgetAmount"><g:message code="default.budgetMaster.totalBudgetAmount(Rs).label" /></label>
                                    <label for="totalBudgetAmount" style="color:red;font-weight:bold"> * </label>
                                 </td>
                                 <td valign="top" class="value ${hasErrors(bean: budgetMasterInstance, field: 'totalBudgetAmount', 'errors')}">
                                  <input type="text" id="totalBudgetAmount" name="totalBudgetAmount" value="${amount}" style="text-align: right" />
                                    <!--<g:textField id="totalBudgetAmount" name="totalBudgetAmount" value="${fieldValue(bean: budgetMasterInstance, field: 'totalBudgetAmount')}" value="${amount}" style="text-align: right" />-->
                                 </td>
                                
                                <td valign="top" class="name">
                                    <label for="budgetDescription"><g:message code="default.budgetMaster.budgetDescription.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetMasterInstance, field: 'budgetDescription', 'errors')}">
                                    <g:textArea name="budgetDescription" value="${budgetMasterInstance?.budgetDescription}" style='width: 200px; height:75px;'/>
                                </td>
                            </tr>
                                                                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                 <span class="button"><g:submitButton name="create" class="save" onClick="return validateMaster()" value="${message(code: 'default.Create.button')}" /></span>
                </div>
            </g:form>
        </div>
         <div class="body">
         <h1><g:message code="default.Budgetmasterlist.label" args="[entityName]" /></h1>
         
         <div class="list">
            <g:if test="${budgetMasterInstanceList}">
                <table>
                    <thead>
                        <tr>
                     
                           <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                           
                           <th><g:message code="default.budgetMaster.party.label" /></th>
                        
                            <th><g:message code="default.budgetMaster.financialYear.label" /></th>
                         
                            <g:sortableColumn property="budgetCode" title="${message(code: 'default.budgetMaster.budgetCode.label')}" />
                        
                            <g:sortableColumn property="title" title="${message(code: 'default.budgetMaster.title.label')}" />
                            
                            <g:sortableColumn property="totalBudgetAmount" title="${message(code: 'default.budgetMaster.totalBudgetAmount(Rs).label')}" />
                        
                            <g:sortableColumn property="budgetDescription" title="${message(code: 'default.budgetMaster.budgetDescription.label')}" />
                            
                            <g:sortableColumn property="edit" title="${message(code: 'default.budgetMaster.budgetAllocate.label')}" />
                            
                            <g:sortableColumn property="edit" title="${message(code: 'default.Edit.label')}" />
                         
                        </tr>
                    </thead>
                    <tbody>
                   
                    <g:each in="${budgetMasterInstanceList}" status="i" var="budgetMasterInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                            
                            <td>${fieldValue(bean: budgetMasterInstance, field: "party.code")}</td>
                        
                            <td>${fieldValue(bean: budgetMasterInstance, field: "financialYear.financialPeriod")}</td>
                        
                            <td>${fieldValue(bean: budgetMasterInstance, field: "budgetCode")}</td>
                            
                            <td>${fieldValue(bean: budgetMasterInstance, field: "title")}</td>
                            
                            <td>${currencyFormat.ConvertToIndainRS(budgetMasterInstance.totalBudgetAmount)}</td>
                        
                            <td>${fieldValue(bean: budgetMasterInstance, field: "budgetDescription")}</td>
                            <td>
                            <input type="hidden" name="budgetId${i}" id="budgetId${i}" value="${budgetMasterInstance?.id}">
                            <g:link controller ="budgetDetails" action="create" params="['budgetMasterId':budgetMasterInstance.id]" onclick="return validateConfirm(document.getElementById('budgetId${i}').value)"><g:message code="default.budgetMaster.budgetAllocate.label"/></g:link></td>
                            <!--<g:link controller ="budgetDetails" action="create" params="['budgetMasterId':budgetMasterInstance.id]" onclick="var retValue=true;${remoteFunction(controller:'budgetMaster',action:'confirm',params:'\'id=\'+document.getElementById(\'budgetId'+i+'\').value',asynchronous:true ,onFailure :'returnConfirm();')};return retValue;"><g:message code="default.budgetMaster.budgetAllocate.label"/></g:link></td>-->
                            
                            <td><g:link action="edit" id="${fieldValue(bean:budgetMasterInstance, field:'id')}" onclick="return validateConfirm(document.getElementById('budgetId${i}').value)"><g:message code="default.Edit.label"/></g:link></td>
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
