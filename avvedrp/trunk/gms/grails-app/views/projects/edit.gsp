<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.projects.projectDetails.head"/></title>
    </head>    
    <body>
   <g:subMenuList/>
   <div class="wrapper"> 
    	<div class="body">
            <h1><g:message code="default.projects.projectDetails.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="rejerrors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${projectsInstance?.id}" />
                <div class="dialog">
                     <table> 
                        <tbody>
                         
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <g:if test="${projectsInstance.parent}">
                                    <label for="name"><g:message code="default.Name.label"/>:</label>
                                    </g:if>
                                    <g:else>
                                    <label for="name"><g:message code="default.Name.label"/>:</label>
                                    <label for="name" style="color:red;font-weight:bold"> * </label>
                                    </g:else>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <g:if test="${projectsInstance.parent}">
										<strong>	
											${projectsInstance.name}
					 					</strong>
				     				</g:if>
				     				<g:else>
                                    	<input type="text" size="45" id="name" name="name" value="${fieldValue(bean:projectsInstance,field:'name')}"/>
                                	</g:else>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <g:if test="${projectsInstance.parent}">
                                    <label for="code"><g:message code="default.Code.label"/>:</label>
                                    </g:if>
                                    <g:else>
                                    <label for="code"><g:message code="default.Code.label"/>:</label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                    </g:else>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <g:if test="${projectsInstance.parent}">
										<strong>	
											${projectsInstance.code}
					 					</strong>
				     				</g:if>
				     				<g:else>
                                    	<input type="text" id="code" name="code" value="${fieldValue(bean:projectsInstance,field:'code')}"/>
                                    	<label for="code" style="color:blue;font-weight:bold"> <g:message code="default.projects.projectCode.label"/></label>
                                	</g:else>
                                </td>
                            </tr> 
                        
                           <tr class="prop">
                                <td valign="top" class="name">
                                     <g:if test="${projectsInstance.parent}">
                                     <label for="code"><g:message code="default.ProjectType.label"/>:</label>
                                     </g:if>
                                     <g:else>
                                     <label for="code"><g:message code="default.ProjectType.label"/>:</label>
                                     <label for="projectyype" style="color:red;font-weight:bold"> * </label>  
                                     </g:else>
                                </td>
                                
                             <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectType','errors')}" >
                                    <g:if test="${projectsInstance.parent}">
										<strong>	
											${projectsInstance.projectType.type}
					 					</strong>
				     				</g:if>
                                    <g:else>
                                    <g:select optionKey="id" optionValue="type" id="projectType" from="${ProjectType.findAll('from ProjectType P where P.activeYesNo=\'Y\' ')}"  name="projectType.id" value="${projectsInstance?.projectType?.id}" ></g:select>
                                    </g:else>
                                </td>
                           </tr>                                                    
							 
							 <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="investigator"><g:message code="default.Investigator.label"/>:</label>
                                    <label for="investigator" style="color:red;font-weight:bold"> * </label>
                               </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'investigator','errors')}">
                                <g:if test="${projectsInstance.parent}">
                                     <strong>
                                     ${projectsInstance?.investigator?.name}
                                     </strong>
                                     <input type="hidden" name="investigator.id" value="${projectsInstance?.investigator?.id}" />
                                     </td>
                           		</g:if>
                           		<g:else>
                           		<g:select optionKey="id" optionValue="name" from="${investigatorList}" name="investigator.id" value="${projectsInstance?.investigator?.id}" ></g:select> 
                           		</g:else>
                            </tr> 
                          
                          <tr class="prop">
                                <td valign="top" class="name">
                                     <g:if test="${projectsInstance.parent}">
                                      <label for="projectStartDate"><g:message code="default.StartDate.label"/>:</label>
                                     </g:if>
                                     <g:else>
                                    <label for="projectStartDate"><g:message code="default.StartDate.label"/>:</label>
                                    <label for="projectStartDate" style="color:red;font-weight:bold"> * </label>  
                                      </g:else>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectStartDate','errors')}">
                                    <g:if test="${projectsInstance.parent}">
										<strong>	
											<g:formatDate format="dd MMM yyyy" date="${projectsInstance.projectStartDate}"/> 
					 					</strong>
				     				</g:if>
				     				<g:else>
					 					<calendar:datePicker name="projectStartDate" value="${projectsInstance?.projectStartDate}" defaultValue="${new Date()}" dateFormat= "%d/%m/%Y" />
				      				</g:else>
                                </td>
                            </tr> 
                            
                            
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <g:if test="${projectsInstance.parent}">
                                     <label for="projectEndDate"><g:message code="default.EndDate.label"/>:</label>
                                     </g:if>
                                    <g:else>
                                    <label for="projectEndDate"><g:message code="default.EndDate.label"/>:</label>
                                    <label for="projectEndDate" style="color:red;font-weight:bold"> * </label>  
                                    </g:else>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectEndDate','errors')}">
                                    <g:if test="${projectsInstance.parent}">
										<strong>	
											<g:formatDate format="dd MMM yyyy" date="${projectsInstance.projectEndDate}"/> 
					 					</strong>
				     				</g:if>
				     				<g:else>
				     					<calendar:datePicker name="projectEndDate" value="${projectsInstance?.projectEndDate}" defaultValue="${new Date()}" dateFormat= "%d/%m/%Y"/>
				     				</g:else>
                                </td>
                            </tr>
                            
                            
                        </tbody>
                    </table>
                 </div>
                   <g:if test="${projectsInstance.parent}"/>
                   <g:else>
               		<div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" onClick="return validateProject();" /></span>
                	</div>
                  </g:else>	
            </g:form>
          </div>
       </div>
    </body>
</html>
