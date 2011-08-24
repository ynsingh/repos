


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'default.budgetModuleMap.label')}" />
        <title><g:message code="default.budgetModuleMap.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.EditAssignBudgettoModuleType.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${budgetModuleMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${budgetModuleMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${budgetModuleMapInstance?.id}" />
                <g:hiddenField name="version" value="${budgetModuleMapInstance?.version}" />
                <div class="dialog">
                    <table>
                            <tbody>
                             <tr class="prop">
                               <td valign="top" class="name">
                                    <label for="moduleType"><g:message code="default.budgetModuleMap.moduleType.label" /></label>
                                 </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetModuleMapInstance, field: 'moduleType', 'errors')}">
                                 <strong>${budgetModuleMapInstance.moduleType}</strong>
                                <input type=hidden name="moduleType" id = "moduleType" value="${budgetModuleMapInstance.moduleType}">
                               
                                <!--<g:select id="moduleType" name="moduleType" from="${['Proposal', 'Notification', 'Projects']}" onchange="${remoteFunction(controller:'budgetModuleMap',action:'submissionType',update:'proposalSelect',params:'\'moduleType=\'+this.value')};" value="${budgetModuleMapInstance?.moduleType}" />-->
                               </td>
                            
                                <td valign="top" class="name">
                                    <label for="moduleTypeId"><g:message code="default.budgetModuleMap.moduleTitle.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetModuleMapInstance, field: 'moduleTypeId', 'errors')}">
                                 <g:if test="${moduleType=='Proposal'}">
                                    <div id="proposalSelect" >
                                		<g:select  name="moduleTypeId" from="${moduleTypeList}"  value="${proposalCodeInstance.code}" noSelection="['null':'-Select-']" />
                               	</div>
                                   </g:if>
                                 <g:if test="${moduleType=='Projects'}">
                                   	 <div id="proposalSelect" >
                              			 <g:select  name="moduleTypeId" from="${moduleTypeList}"  value="${projectCodeInstance.code}" noSelection="['null':'-Select-']" />
                              		</div>
                                 </g:if>
                                 <g:if test="${moduleType=='Notification'}">
                                    <div id="proposalSelect" >
                              			 <g:select  name="moduleTypeId" from="${moduleTypeList}"  value="${notificationTitleInstance.notificationTitle}" noSelection="['null':'-Select-']" />
                              		</div>
                                  </g:if>
                             	  <g:if test="${moduleType=='FullProposal'}">
                             	    <div id="proposalSelect" >
                              			 <g:select  name="moduleTypeId" from="${moduleTypeList}"  value="${proposalApplicationInstance.projectTitle}" noSelection="['null':'-Select-']" />
                              		</div>
                                  </g:if>
								
                               </td>
                            </tr>
                            
                            <tr class="prop">
                            
                               <td valign="top" class="name">
                                    <label for="budgetMaster"><g:message code="default.budgetModuleMap.budgetMaster.label" /></label>
                                    <label for="budgetMaster" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetModuleMapInstance, field: 'budgetMaster', 'errors')}">
                                    <g:select name="budgetMaster.id" from="${budgetMasterInstanceList}" optionKey="id" optionValue="title" value="${budgetModuleMapInstance?.budgetMaster?.id}" />
                                </td>
                           </tr>
                           
                          
                             
                            
                       </tbody>
                    </table>
                </div>
               <div class="buttons">
                  <span class="button"><g:actionSubmit class="save" action="update"  onClick="return validateBudgetModuleMap()" value="${message(code: 'default.Update.button')}" /></span>
		       </div>
            </g:form>
        </div>
       </div>
    </body>
</html>
