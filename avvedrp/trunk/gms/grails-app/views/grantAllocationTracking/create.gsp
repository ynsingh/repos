

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Grant Allocation Status</title>         
    </head>
    <body>
     <div class="wrapper"> 
     
        <div class="nav">
         <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            
        </div>
        
        
        <div class="proptable">
        <table >
        
        <tr >
	        <td valign="top" >Grant Code:</td>
	        <td valign="top" ><strong>${fieldValue(bean:grantAllocationInstance, field:'code')}</strong></td>
                               
            <td valign="top" >Project Code:</td>
            <td valign="top" ><strong>${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</strong></td>
            
            <td valign="top" >Institution:</td>
            <td valign="top" ><strong>${fieldValue(bean:grantAllocationInstance, field:'party.code')}</strong></td>
            
            <td valign="top" >Amount Allocated:</td>
            <td valign="top" ><strong><g:formatNumber number="${grantAllocationInstance.amountAllocated}" format="###,##0.00" /></strong></td>
                    
        </tr>  
      	</table>  
   </div>
<table class="tablewrapper" cellspacing="0" cellpadding="0">
  <tr>
    <td>
        <div class="body">
            <h1>Grant Allocation Status</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantAllocationTrackingInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantAllocationTrackingInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                        	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="grantAllocationStatus">Grant Allocation Status:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'grantAllocationStatus','errors')}">
                                	<g:if test="${grantAllocationTrackingInstance.trackType == 'surrender' }">
                                    	<g:select name="grantAllocationStatus" from="${['Surrender','Closed']}"  value="${fieldValue(bean:grantAllocationTrackingInstance,field:'grantAllocationStatus')}" />
                                    </g:if>
                                    <g:if test="${grantAllocationTrackingInstance.trackType == 'withdraw' }">
                                    	<g:select name="grantAllocationStatus" from="${['Withdrawal','Closed']}"  value="${fieldValue(bean:grantAllocationTrackingInstance,field:'grantAllocationStatus')}" />
                                    </g:if>
                                    <g:hiddenField name="grantAllocation.id" value="${grantAllocationTrackingInstance?.grantAllocation?.id}" />
                                    <input type="hidden" name="id" value="${grantAllocationTrackingInstance?.id}" />
                                    <g:hiddenField name="trackType" value="${grantAllocationTrackingInstance.trackType}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateOfTracking">Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'dateOfTracking','errors')}">
                                    <calendar:datePicker name="dateOfTracking" defaultValue="${new Date()}" value="${grantAllocationTrackingInstance?.dateOfTracking}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks">Remarks:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'remarks','errors')}">
                                    <g:textArea name="remarks" value="${fieldValue(bean:grantAllocationTrackingInstance,field:'remarks')}" rows="3" cols="30"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
        </div>
         </td>
                            </tr> 
                        
                       
                    </table>
         </div>
    </body>
</html>
