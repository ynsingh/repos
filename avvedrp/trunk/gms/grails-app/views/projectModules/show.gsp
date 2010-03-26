

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show ProjectModules</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProjectModules List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ProjectModules</g:link></span>
        </div>
        <div class="body">
            <h1>Show ProjectModules</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectModulesInstance, field:'id')}</td>
                            
                        </tr>
                        
                    	<tr class="prop">
                            <td valign="top" class="name">Projects:</td>
                            
                            <td valign="top" class="value"><g:link controller="projects" action="show" id="${projectModulesInstance?.projects?.id}">${projectModulesInstance?.projects?.projectName}</g:link></td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Institution Master:</td>
                            
                            <td valign="top" class="value"><g:link controller="institutionMaster" action="show" id="${projectModulesInstance?.institutionMaster?.id}">${projectModulesInstance?.institutionMaster?.code}</g:link></td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Project Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectModulesInstance, field:'projectName')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Principal Investigator:</td>
                            
                            <td valign="top" class="value"><g:link controller="user" action="show" id="${projectModulesInstance?.principalInvestigator?.id}">${projectModulesInstance?.principalInvestigator?.userRealName}</g:link></td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Project Submission Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectModulesInstance, field:'projectSubmissionDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectModulesInstance, field:'description')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Active Yes No:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectModulesInstance, field:'activeYesNo')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${projectModulesInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
