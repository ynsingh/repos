

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit GrantAllocationTracking</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">GrantAllocationTracking List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New GrantAllocationTracking</g:link></span>
        </div>
        <div class="body">
            <h1>Edit GrantAllocationTracking</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantAllocationTrackingInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantAllocationTrackingInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${grantAllocationTrackingInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks">Remarks:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'remarks','errors')}">
                                    <input type="text" id="remarks" name="remarks" value="${fieldValue(bean:grantAllocationTrackingInstance,field:'remarks')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createdBy">Created By:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'createdBy','errors')}">
                                    <input type="text" id="createdBy" name="createdBy" value="${fieldValue(bean:grantAllocationTrackingInstance,field:'createdBy')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createdDate">Created Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'createdDate','errors')}">
                                    <g:datePicker name="createdDate" value="${grantAllocationTrackingInstance?.createdDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateOfTracking">Date Of Tracking:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'dateOfTracking','errors')}">
                                    <g:datePicker name="dateOfTracking" value="${grantAllocationTrackingInstance?.dateOfTracking}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="grantAllocation">Grant Allocation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'grantAllocation','errors')}">
                                    <g:select optionKey="id" from="${GrantAllocation.list()}" name="grantAllocation.id" value="${grantAllocationTrackingInstance?.grantAllocation?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="grantAllocationStatus">Grant Allocation Status:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'grantAllocationStatus','errors')}">
                                    <input type="text" id="grantAllocationStatus" name="grantAllocationStatus" value="${fieldValue(bean:grantAllocationTrackingInstance,field:'grantAllocationStatus')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifiedBy">Modified By:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'modifiedBy','errors')}">
                                    <input type="text" id="modifiedBy" name="modifiedBy" value="${fieldValue(bean:grantAllocationTrackingInstance,field:'modifiedBy')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifiedDate">Modified Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'modifiedDate','errors')}">
                                    <g:datePicker name="modifiedDate" value="${grantAllocationTrackingInstance?.modifiedDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
