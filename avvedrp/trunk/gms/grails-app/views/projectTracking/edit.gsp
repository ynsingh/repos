<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ProjectClosure.ProjectClosureEdit.head"/></title>         
    </head>
    <body>
        <div class="wrapper"> 
        <g:if test="${projectsInstance?.status =='Closed'}">
    	</g:if>
    	<g:else>
    		<g:subMenuList/>
    	</g:else>
          <div class="body">
            <table>
        
                <tr>
	                <td valign="top" ><g:message code="default.ProjectCode.label"/></td>
	                <td valign="top" ><strong>${fieldValue(bean:projectsInstance, field:'code')}</strong></td>
                               
                    <td valign="top" ><g:message code="default.ProjectName.label"/></td>
                    <td valign="top" ><strong>${fieldValue(bean:projectsInstance, field:'name')}</strong></td>
                </tr>  
      	    </table> 
            <table class="tablewrapper" cellspacing="0" cellpadding="0">
              <tr>
	             <td>
                    <h1><g:message code="default.ProjectClosure.ProjectClosureEdit.head"/></h1>
                    <g:if test="${flash.message}">
                       <div class="message">${flash.message}</div>
                    </g:if>
                    <g:hasErrors bean="${projectTrackingInstance}">
                       <div class="errors">
                          <g:renderErrors bean="${projectTrackingInstance}" as="list" />
                       </div>
                    </g:hasErrors>
                    <g:form action="save" method="post" >
                     <g:hiddenField name="id" value="${projectTrackingInstance?.id}" />
                        <div class="dialog">
                           <table>
                              <tbody>
                        
                                  <tr class="prop">
                                      <td valign="top" class="name">
                                          <label for="projectStatus"><g:message code="default.ProjectStatus.label"/></label>:
                                      </td>
                                      <td valign="top" class="value ${hasErrors(bean:projectTrackingInstance,field:'projectStatus','errors')}">
                                          <g:select name="projectStatus" from="${['Open','Deadline Passed','Grant Funded','Closed']}"  value="${fieldValue(bean:projectTrackingInstance,field:'projectStatus')}" />
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
                                      </td>
                                  </tr>
                        
                                  <tr class="prop">
                                      <td valign="top" class="name">
                                          <label for="dateOfTracking"><g:message code="default.Date.label"/></label>:
                                      </td>
                                      <td valign="top" class="value ${hasErrors(bean:projectTrackingInstance,field:'dateOfTracking','errors')}">
                                          <calendar:datePicker name="dateOfTracking" defaultValue="${new Date()}" value="${projectTrackingInstance?.dateOfTracking}" dateFormat= "%d/%m/%Y"/>
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
                             <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" onClick="return validateProjectTracking()"  /></span>
                             <span class="button"><g:actionSubmit class="delete" action="delete" onclick="return confirm('Are you sure?');" value="${message(code: 'default.Delete.button')}" /></span>
                        </div>
                    </g:form>
                 </td>
              </tr> 
            </table>
	      </div>
	    </div>
	</body>
</html>    
              