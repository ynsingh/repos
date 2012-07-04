

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ProjectClosure.ProjectClosureEntry.head"/></title>         
    </head>
    <body>
        <div class="wrapper"> 
        <g:if test="${projectsInstance?.status =='Closed'}">
    	</g:if>
    	<g:else>
    		<g:subMenuList/>
    	</g:else>
          <div class="body">
             <table width="100%" align="left">
                   <tr>
	                <td valign="top" ><g:message code="default.ProjectCode.label"/>:</td>
	                <td valign="top" ><strong>${fieldValue(bean:projectsInstance, field:'code')}</strong></td>
                    <td valign="top" ><g:message code="default.ProjectName.label"/>:</td>
                    <td valign="top" ><strong>${fieldValue(bean:projectsInstance, field:'name')}</strong></td>
                 	<td>
			       <td> <label for="dateRangeFrom"><g:message code="default.projects.ProjectStartDate.label"/>: </label></td>
			        <td><strong><g:formatDate date="${projectsInstance?.projectStartDate}" format="dd/MM/yyyy"/> </strong>
			</td>
			<td>
			        <td><label for="dateRangeTo"><g:message code="default.EndDate.label"/>: </label>      </td>        
			       <td> <strong><g:formatDate date="${projectsInstance?.projectEndDate}" format="dd/MM/yyyy"/></strong></td>
<td>
      	<img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">   
</td>
                 </tr>
      	    </table> 
            <table class="tablewrapper" cellspacing="0" cellpadding="0">
              <tr>
	             <td>
                    <h1><g:message code="default.ProjectClosure.ProjectClosureEntry.head"/></h1>
                    <g:if test="${flash.message}">
                       <div class="message">${flash.message}</div>
                    </g:if>
                    <g:hasErrors bean="${projectTrackingInstance}">
                       <div class="errors">
                          <g:renderErrors bean="${projectTrackingInstance}" as="list" />
                       </div>
                    </g:hasErrors>
                    <g:form action="save" method="post" >
                        <div class="dialog">
                           <table>
                              <tbody>
                        
                                  <tr class="prop">
                                      <td valign="top" class="name">
                                          <label for="projectStatus"><g:message code="default.ProjectStatus.label"/></label>:
                                      </td>
                                      <td valign="top" class="value ${hasErrors(bean:projectTrackingInstance,field:'projectStatus','errors')}">
                                          <g:select name="projectStatus" from="${['Open','Deadline Passed','Grant Funded','Closed']}"  value="${fieldValue(bean:projectTrackingInstance,field:'projectStatus')}" noSelection="['null':'-Select-']"/>
                                          <g:hiddenField name="projects.id" value="${projectTrackingInstance?.projects?.id}" />
                                      </td>
                                  </tr> 
                        
                                  <tr class="prop">
                                      <td valign="top" class="name">
                                          <label for="percOfCompletion"><g:message code="default.PercentageOfCompletion.label"/></label>:
                                          <label for="percOfCompletion" style="color:red;font-weight:bold"> * </label>
                                      </td>
                                      <td valign="top" class="value ${hasErrors(bean:projectTrackingInstance,field:'percOfCompletion','errors')}">
                                          <input type="text" id="percOfCompletion" name="percOfCompletion" value="${fieldValue(bean:projectTrackingInstance,field:'percOfCompletion')}" />
                                          <input type="hidden" id="projectStartDate" name="projectStartDate" value="${projectsInstance.projectStartDate}"/>  
                                          <input type="hidden" id="projectEndDate" name="projectEndDate" value="${projectsInstance.projectEndDate}"/>  
                                      </td>
                                  </tr>
                        
                                  <tr class="prop">
                                      <td valign="top" class="name">
                                          <label for="dateOfTracking"><g:message code="default.Date.label"/></label>:
                                      </td>
                                      <td valign="top" class="value ${hasErrors(bean:projectTrackingInstance,field:'dateOfTracking','errors')}">
                                          <calendar:datePicker id="dateOfTracking" name="dateOfTracking" defaultValue="${new Date()}" value="${projectTrackingInstance?.dateOfTracking}" dateFormat= "%d/%m/%Y"/>
                                      </td>
                                  </tr>  
                            
                                  <tr class="prop">
                                      <td valign="top" class="name">
                                          <label for="remarks"><g:message code="default.Remarks.label"/></label>:
                                      </td>
                                      <td valign="top" class="value ${hasErrors(bean:projectTrackingInstance,field:'remarks','errors')}">
                                          <g:textArea name="remarks" value="${fieldValue(bean:projectTrackingInstance,field:'remarks')}" rows="3" cols="30"/>
                                      </td>
                                  </tr> 
                            
                              </tbody>
                           </table>
                        </div>
                        <div class="buttons">
                             <span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" onClick="return validateProjectTracking()" /></span>
                        </div>
                    </g:form>
                 </td>
              </tr> 
              <tr>
                 <td>
                    <div class="list">
	                   <table cellspacing="0" cellpadding="0">
	                      <thead>
	                      
	                         <tr>
	                             <th><g:message code="default.SINo.label"/></th>
	                             <g:sortableColumn property="projectStatus" title="${message(code: 'default.ProjectStatus.label')}" />
	                             <g:sortableColumn property="percOfCompletion" title="${message(code: 'default.%OfCompletion.label')}" />
	                             <g:sortableColumn property="dateOfTracking" title="${message(code: 'default.Date.label')}" />
	                             <g:sortableColumn property="remarks" title="${message(code: 'default.Remarks.label')}" />
	           	                 <th><g:message code="default.Edit.label"/></th>
	                         </tr>
	                      </thead>
	                      <tbody>
	                        <% int j=0 %>
	                        <g:each in="${projectTrackingInstanceList}" status="i" var="projectTrackingInstance">
	                          <% j++ %>
	                          <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                              <td>${j}</td>
	                              <td>${fieldValue(bean:projectTrackingInstance, field:'projectStatus')}</td>
	                              <td><g:formatNumber number="${projectTrackingInstance.percOfCompletion}" format="###,##0.00" /></td>
	                              <td><g:formatDate format="dd/MM/yyyy" date="${projectTrackingInstance.dateOfTracking}"/></td>
	                              <td>${fieldValue(bean:projectTrackingInstance, field:'remarks')}</td>
	                              <td><g:link action="edit" id="${projectTrackingInstance?.id}"><g:message code="default.Edit.label"/></g:link></td>
	                          </tr>
	                        </g:each>
	                      </tbody>
	                   </table>
	                </div>
	             </td>
	          </tr>
	        </table>
          </div>
        </div>
    </body>
</html>
