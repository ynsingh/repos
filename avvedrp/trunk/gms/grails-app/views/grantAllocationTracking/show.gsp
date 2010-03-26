

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show GrantAllocationTracking</title>
    </head>
    <body>
        <div class="nav">
           <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">GrantAllocationTracking List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New GrantAllocationTracking</g:link></span>
        </div>
        <div class="body">
            <h1>Show GrantAllocationTracking</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationTrackingInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Remarks:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationTrackingInstance, field:'remarks')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Created By:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationTrackingInstance, field:'createdBy')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Created Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationTrackingInstance, field:'createdDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Date Of Tracking:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationTrackingInstance, field:'dateOfTracking')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Grant Allocation:</td>
                            
                            <td valign="top" class="value"><g:link controller="grantAllocation" action="show" id="${grantAllocationTrackingInstance?.grantAllocation?.id}">${grantAllocationTrackingInstance?.grantAllocation?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Grant Allocation Status:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationTrackingInstance, field:'grantAllocationStatus')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Modified By:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationTrackingInstance, field:'modifiedBy')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Modified Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationTrackingInstance, field:'modifiedDate')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${grantAllocationTrackingInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
