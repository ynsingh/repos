<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.GrantAllocationStatus.head"/></title>         
    </head>
    <body>
    	<div class="wrapper"> 
     	   <g:subMenuList/>
           <div class="proptable">
              <table width="100%" align="left">
                <tr >

		            <td valign="top" ><g:message code="default.ProjectCode.label"/>:</td>
		            <td valign="top" >
		            	<strong>${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</strong>
	            	</td>
		            <td valign="top" ><g:message code="default.Institution.label"/>:</td>
		            <td valign="top" >
		            	<strong>${fieldValue(bean:grantAllocationInstance, field:'party.code')}</strong>
	            	</td>
		            <td valign="top" ><g:message code="default.AmountAllocated.label"/>:</td>
		            <td valign="top" >
		            	<strong>
		            		<span style ="font-family:rupee">R</span>
		            		${currencyFormat.ConvertToIndainRS(grantAllocationInstance.amountAllocated)}
		                </strong>
		    		</td>
			<td>
			     <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help"> 
			</td>
	    		</tr>  
      		</table>  
		</div>
		<table class="tablewrapper" cellspacing="0" cellpadding="0">
		  <tr>
		    	<td>
		        	<div class="body">
			            <h1><g:message code="default.GrantAllocationStatus.head"/></h1>
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
			                                    <label for="grantAllocationStatus">
			                                    	<g:message code="default.GrantAllocationStatus.label"/>:
		                                    	</label>
			                                 </td>
			                                 <td valign="top" 
			                                 	class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'grantAllocationStatus','errors')}">
			                                	<g:if test="${grantAllocationTrackingInstance.trackType == 'surrender' }">
			                                    	<g:select name="grantAllocationStatus" from="${['Surrender','Closed']}"  
	                                    				value="${fieldValue(bean:grantAllocationTrackingInstance,field:'grantAllocationStatus')}" />
			                                    </g:if>
			                                    <g:if test="${grantAllocationTrackingInstance.trackType == 'withdraw' }">
			                                    	<g:select name="grantAllocationStatus" from="${['Withdrawal','Closed']}"  
			                                    	value="${fieldValue(bean:grantAllocationTrackingInstance,field:'grantAllocationStatus')}" noSelection="['null':'-Select-']"/>
			                                    </g:if>
                                    			<g:hiddenField name="grantAllocation.id" 
                                    				value="${grantAllocationTrackingInstance?.grantAllocation?.id}" />
				                                    <input type="hidden" name="id" value="${grantAllocationTrackingInstance?.id}" />
				                                    <g:hiddenField name="trackType" value="${grantAllocationTrackingInstance.trackType}" />
			                                	</td>
				                            </tr> 
                        
				                            <tr class="prop">
				                                <td valign="top" class="name">
				                                    <label for="dateOfTracking"><g:message code="default.Date.label"/>:</label>
				                                </td>
				                                <td valign="top" 
				                                	class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'dateOfTracking','errors')}">
				                                    <calendar:datePicker name="dateOfTracking" defaultValue="${new Date()}" 
				                                    	value="${grantAllocationTrackingInstance?.dateOfTracking}" dateFormat= "%d/%m/%Y"/>
				                                </td>
				                            </tr> 
				                        
				                            <tr class="prop">
				                                <td valign="top" class="name">
				                                    <label for="remarks"><g:message code="default.Remarks.label"/>:</label>
				                                </td>
				                                <td valign="top" 
				                                	class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'remarks','errors')}">
				                                    <g:textArea name="remarks" 
				                                    	value="${fieldValue(bean:grantAllocationTrackingInstance,field:'remarks')}" 
				                                    	rows="3" cols="30"/>
			                                	</td>
				                            </tr> 
                    					</tbody>
                    				</table>
                				</div>
				                <div class="buttons">
				                    <span class="button"><input class="save" type="submit" value="${message(code: 'default.Save.button')}" /></span>
				                </div>
				            </g:form>
        				</div>
         			</td>
                </tr> 
             </table>
         </div>
    </body>
</html>
