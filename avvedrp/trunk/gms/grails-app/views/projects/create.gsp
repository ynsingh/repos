<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.projects.create.head"/></title>               
    </head>
    <body>
    	<g:if test="${projectsInstance.id}">
    		<g:subMenuProjects/>
    	</g:if>
		<div class="wrapper">   
        <div class="body">
        
            <h1><g:message code="default.projects.create.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table >
                        <tbody>
                              <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="default.Name.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <input type="text" size="45" id="name" name="name" value="${fieldValue(bean:projectsInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.Code.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text"  id="code" name="code" value="${fieldValue(bean:projectsInstance,field:'code')}"/>
                                </td>
                            </tr> 
                        	
                        	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.ProjectType.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectType','errors')}">
                                    <g:select optionKey="id" optionValue="type" id="projectType" from="${ProjectType.findAll('from ProjectType P where P.activeYesNo=\'Y\' ')}"  name="projectType.id" value="${projectsInstance?.projectType?.id}" noSelection="['null':'select']" ></g:select>
                                </td>
                            </tr> 
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="investigator"><g:message code="default.Investigator.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'investigator','errors')}">
                                    <g:select optionKey="id" optionValue="name" from="${investigatorList}" name="investigator.id" value="${projectsInstance?.investigator?.id}" ></g:select>
                                 </td>
                            </tr> 
                         </tr>                           
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectStartDate"><g:message code="default.StartDate.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectStartDate','errors')}">
                                <calendar:datePicker name="projectStartDate" defaultValue="${new Date()}" value="${projectsInstance?.projectStartDate}" dateFormat= "%d/%m/%Y"/>
                                    </td>
                            </tr> 
                            
                            
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectEndDate"><g:message code="default.EndDate.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectEndDate','errors')}">
                                    <calendar:datePicker name="projectEndDate" value="${projectsInstance?.projectEndDate}" defaultValue="${new Date()}"  dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr>
                         
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" onClick="return validateProject()" value="${message(code: 'default.Create.button')}" /></span>
                </div>
            </g:form>
        </div>
                        </td>
        </tr>
        
         </table>
         </div>
    </body>
</html>
