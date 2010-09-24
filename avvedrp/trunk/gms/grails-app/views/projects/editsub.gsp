

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Sub Projects</title>
    </head>    
    <body>
   
    <div class="wrapper"> 
    
  <g:hiddenField name="parentProjectStartDate" value="${fieldValue(bean:projectsInstance, field:'parent.projectStartDate')}"/>
    <g:hiddenField name="parentProjectEndDate" value="${fieldValue(bean:projectsInstance, field:'parent.projectEndDate')}"/>
        <div class="body">
            <h1>Edit Sub Projects</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form  action="subupdate" method="post">
                <input type="hidden" name="id" value="${projectsInstance?.id}" />
                <div class="dialog">
                     <table> 
                        <tbody>
                        
                             <tr class="prop">
                               
                           
                            <td valign="top" class="name">
                                    <label for="parent">Main Project:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'parent','errors')}">
                                
                                    <g:select optionKey="id" optionValue="code"  from="${Projects.findById(projectsInstance.parent.id)}" name="parent.id" value="${projectsInstance?.parent?.id}" ></g:select>
                                </td>
                          
                            </tr> 
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <input type="text" size="45" id="name" name="name" value="${fieldValue(bean:projectsInstance,field:'name')}"/>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:projectsInstance,field:'code')}"/>
                                </td>
                            </tr> 
                         
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectStartDate">Start Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectStartDate','errors')}">
                                    <calendar:datePicker name="projectStartDate" value="${projectsInstance?.projectStartDate}" defaultValue="${new Date()}" dateFormat= "%d/%m/%Y" />
                                </td>
                            </tr> 
                            
                            
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectEndDate">End Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectEndDate','errors')}">
                                    <calendar:datePicker name="projectEndDate" value="${projectsInstance?.projectEndDate}" defaultValue="${new Date()}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}" value="${fieldValue(bean:projectsInstance,field:'activeYesNo')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Update" onClick="return validateSubProject()" /></span>
                    
                </div>
            </g:form>
            </div>
             
        </div>
        
       
    </body>
</html>
