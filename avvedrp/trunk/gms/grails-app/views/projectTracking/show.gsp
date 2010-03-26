

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show ProjectTracking</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProjectTracking List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ProjectTracking</g:link></span>
        </div>
        <div class="body">
            <h1>Show ProjectTracking</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectTrackingInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Remarks:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectTrackingInstance, field:'remarks')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Created By:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectTrackingInstance, field:'createdBy')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Created Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectTrackingInstance, field:'createdDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Date Of Tracking:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectTrackingInstance, field:'dateOfTracking')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Modified By:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectTrackingInstance, field:'modifiedBy')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Modified Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectTrackingInstance, field:'modifiedDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Perc Of Completion:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectTrackingInstance, field:'percOfCompletion')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Project:</td>
                            
                            <td valign="top" class="value"><g:link controller="projects" action="show" id="${projectTrackingInstance?.project?.id}">${projectTrackingInstance?.project?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Project Status:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:projectTrackingInstance, field:'projectStatus')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${projectTrackingInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
