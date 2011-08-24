


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'budgetModuleMap.label', default: 'BudgetModuleMap')}" />
        <title><g:message code="default.budgetModuleMap.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.AssignBudgettoModuleType.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${budgetModuleMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${budgetModuleMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="budgetMaster"><g:message code="default.budgetModuleMap.budgetMaster.label" /></label>
                                     <label for="budgetMaster" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetModuleMapInstance, field: 'budgetMaster', 'errors')}">
                                    <g:select id="budgetMaster.id"  name="budgetMaster.id" from="${budgetMasterInstanceList}" optionKey="id" optionValue="title" value="${budgetModuleMapInstance?.budgetMaster?.id}" noSelection="['null': '-Select-']" />
                                </td>
                                
                                 <td valign="top" class="name">
                                    <label for="moduleType"><g:message code="default.budgetModuleMap.moduleType.label" /></label>
                                    <label for="moduleType" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetModuleMapInstance, field: 'moduleType', 'errors')}">
                                <g:select id="moduleType" name="moduleType" from="${['Notification', 'Projects','FullProposal']}" onchange="${remoteFunction(controller:'budgetModuleMap',action:'submissionType',update:'proposalSelect',params:'\'moduleType=\'+this.value')};" value="${budgetModuleMapInstance?.moduleType}" noSelection="['null':'-Select-']" />
                               </td>
                            </tr>
                           
                            <tr class="prop">
                              <td valign="top" class="name">
                                    <label for="moduleTypeId"><g:message code="default.budgetModuleMap.moduleTitle.label" /></label>
                                     <label for="moduleTypeId" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetModuleMapInstance, field: 'moduleTypeId', 'errors')}">
                                <div id="proposalSelect" >
                                <g:select id="moduleTypeId" name="moduleTypeId" from="${moduleTypeList}" optionKey="id" value="${fieldValue(bean: budgetModuleMapInstance, field: 'moduleTypeId')}" noSelection="['null':'-Select-']" />
								</div>
                               </td>
                            </tr>
                            
                       </tbody>
                    </table>
                </div>
                <div class="buttons">
                      <span class="button"><g:submitButton name="create" class="save" onClick="return validateBudgetModuleMap()" value="${message(code: 'default.Create.button')}" /></span>
                </div>
            </g:form>
        </div>
          <div class="body">
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                        
                            <th><g:message code="default.budgetModuleMap.budgetMaster.label" /></th>
                        
                            <g:sortableColumn property="moduleType" title="${message(code: 'default.budgetModuleMap.moduleType.label')}" />
                            
                            <th><g:message code="default.budgetModuleMap.moduleTitle.label" /></th>
                        
                            <g:sortableColumn property="edit" title="${message(code: 'default.Edit.label')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${budgetModuleMapInstanceList}" status="i" var="budgetModuleMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                             <td>${i+1}</td>
                        
                             <td>${fieldValue(bean: budgetModuleMapInstance, field: "budgetMaster.title")}</td>
                             
                             <td>${fieldValue(bean: budgetModuleMapInstance, field: "moduleType")}</td>
                              <g:if test="${(budgetModuleMapInstance?.moduleType == 'Projects')}">  
			                        	<%def projectTrackingInstanceCheck=Projects.find("from Projects P where P.id= '"+budgetModuleMapInstance.moduleTypeId+"'")%>
	                              		<td>${projectTrackingInstanceCheck?.code}</td>
                        		   </g:if>
                               <g:if test="${(budgetModuleMapInstance?.moduleType == 'Projects')}">  
			                        	<%def projectTrackingInstanceCheck=ProjectTracking.find("from ProjectTracking PT where PT.projectStatus='Closed'and PT.projects.id= '"+budgetModuleMapInstance.moduleTypeId+"'")%>
	                                 		<g:if test="${projectTrackingInstanceCheck}">
	                                 		 <td>Closed</td>
											</g:if>
											  <g:else>
			                                    <td><g:link action="edit" id="${fieldValue(bean:budgetModuleMapInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                                              </g:else>
			                       </g:if>
			                       <g:if test="${(budgetModuleMapInstance?.moduleType == 'FullProposal')}">  
			                        <%def proposal = ProposalApplication.find("from ProposalApplication P where P.proposal.id='"+budgetModuleMapInstance.moduleTypeId+"'")%>
			                        	<td>${proposal?.projectTitle}</td>
                        		    </g:if> 
			                       <g:if test="${(budgetModuleMapInstance?.moduleType == 'FullProposal')}">
			                        	<%def chkFullProposalInstance=Proposal.find("from Proposal P where (P.proposalStatus='Submitted' OR P.proposalStatus='Approved') and P.id ='"+budgetModuleMapInstance.moduleTypeId+"'")%>
	                                		 <g:if test="${chkFullProposalInstance}">
	                                  			<td>Submitted</td>
											 </g:if>
											 <g:else>
			                                     <td><g:link action="edit" id="${fieldValue(bean:budgetModuleMapInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                                             </g:else>
			                        </g:if>
			                        
			                         <g:if test="${(budgetModuleMapInstance?.moduleType == 'Notification')}">  
			                       		    <%def notificationInstanceCheck= Notification.find("from Notification N where  N.id ='"+budgetModuleMapInstance.moduleTypeId+"'")%>
			                        		<td>${notificationInstanceCheck?.notificationTitle}</td>
                        		 	    </g:if> 
			                           <g:if test="${(budgetModuleMapInstance?.moduleType == 'Notification')}">  
			                           	<%def notificationInstanceCheck= Notification.find("from Notification N where N.publishYesNo='Y'and N.id ='"+budgetModuleMapInstance.moduleTypeId+"'")%>
	                                 			<g:if test="${notificationInstanceCheck}">
	                                 		 		<td>Published</td>
												</g:if>
											  <g:else>
			                                    <td><g:link action="edit" id="${fieldValue(bean:budgetModuleMapInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                                              </g:else>
			                          </g:if>
			                       
			                         <g:if test="${(budgetModuleMapInstance?.moduleType == 'Proposal')}">  
			                                   <td><g:link action="edit" id="${fieldValue(bean:budgetModuleMapInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                                     </g:if>
                                   
			            </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
       </div>
    </body>
</html>
