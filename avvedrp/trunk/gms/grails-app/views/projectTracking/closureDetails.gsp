

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ProjectClosure.ProjectClosureEntry.head"/></title>         
    </head>
    <body>
      <g:subMenuList/>  
        <div class="wrapper"> 
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
                             	<g:if test="${projectsInstance.parent !=null}">
        						 <tr class="prop">
                                      <td valign="top" class="name">
                                          <label for="projectStatus"><g:message code="default.ProjectStatus.label"/></label>:
                                      </td>
                                      <td valign="top" class="value ${hasErrors(bean:projectTrackingInstance,field:'projectStatus','errors')}">
                                          <g:select name="projectStatus" from="${['Completed','Surrender']}"  value="${fieldValue(bean:projectTrackingInstance,field:'projectStatus')}" noSelection="['null':'-Select-']"/>
                                          <g:hiddenField name="projects.id" value="${projectTrackingInstance?.projects?.id}" />
                                      </td>
                                  </tr> 
               					  </g:if>
               					  <g:else>
               					  <tr class="prop">
                                      <td valign="top" class="name">
                                          <label for="projectStatus"><g:message code="default.ProjectStatus.label"/></label>:
                                      </td>
                                      <td valign="top" class="value ${hasErrors(bean:projectTrackingInstance,field:'projectStatus','errors')}">
                                          <g:select name="projectStatus" from="${['Completed']}"  value="${fieldValue(bean:projectTrackingInstance,field:'projectStatus')}" noSelection="['null':'-Select-']"/>
                                          <g:hiddenField name="projects.id" value="${projectTrackingInstance?.projects?.id}" />
                                      </td>
                                  </tr> 
               					  </g:else>
                            
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
                                          <label for="dateOfTracking"><g:message code="default.DateofCLosing.label"/></label>:
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
                             <span class="button"><input class="save" type="submit" value="${message(code: 'default.CheckProjectStatus.button')}" onClick="return validateProjectClosure()" /></span>
                        </div>
                    </g:form>
           </div>
        </div>
    </body>
</html>
