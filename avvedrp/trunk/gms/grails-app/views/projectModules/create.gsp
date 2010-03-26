

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ProjectModules</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProjectModules List</g:link></span>
        </div>
        <div class="body">
            <h1>Create ProjectModules</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectModulesInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectModulesInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projects">Projects:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectModulesInstance,field:'projects','errors')}">
                                    <g:select optionKey="id" optionValue="projectName" from="${Projects.list()}" name="projects.id" value="${projectModulesInstance?.projects?.id}" ></g:select>
                                </td>
                            </tr>  
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="institutionMaster">Institution Master:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectModulesInstance,field:'institutionMaster','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${InstitutionMaster.list()}" name="institutionMaster.id" value="${projectModulesInstance?.institutionMaster?.id}" ></g:select>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectName">Project Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectModulesInstance,field:'projectName','errors')}">
                                    <input type="text" id="projectName" name="projectName" value="${fieldValue(bean:projectModulesInstance,field:'projectName')}"/>
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="principalInvestigator">Principal Investigator:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectModulesInstance,field:'principalInvestigator','errors')}">
                                	<g:select optionKey="id" optionValue="userRealName" from="${Role.findByAuthority('ROLE_MODULEINVESTIGATOR').people}" name="principalInvestigator.id" value="${projectModulesInstance?.principalInvestigator?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectSubmissionDate">Project Submission Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectModulesInstance,field:'projectSubmissionDate','errors')}">
                                    <g:datePicker name="projectSubmissionDate" value="${projectModulesInstance?.projectSubmissionDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectModulesInstance,field:'description','errors')}">
                                    <g:textArea name="description" value="${fieldValue(bean:projectModulesInstance,field:'description')}" rows="2" cols="20"/>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active Yes No:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectModulesInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}" value="${fieldValue(bean:projectModulesInstance,field:'activeYesNo')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
