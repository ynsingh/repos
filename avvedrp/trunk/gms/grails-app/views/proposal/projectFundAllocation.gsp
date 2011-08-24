<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.HeadwiseAllocation.label"/></title>         
    </head>
    <body>
      <div class="wrapper"> 
          <div class="proptable"> 
            <table width="100%" align="left" border="0">
                  <tr>
		                    <td valign="top">
		                       <label for="project"><g:message code="default.Project.label"/>:</label>
		                    </td>
		                    <td valign="top" >
		                   		<strong>  ${projectsInstance.code} 
		                   		</strong>   
		                   		<input type="hidden" name="projects.id" value="${grantAllocationSplitInstance?.projects?.id}">
		                    </td>
		                    <td valign="top" >
                            	<label for="party"> <g:message code="default.HeadAllocation.GrantAmount.label"/>:</label>
                            </td>
                           <td valign="top" >
                           <strong> <span style ="font-family:rupee">R</span> 
                           ${currencyFormat.ConvertToIndainRS(projectsInstance.totAllAmount)}
                           </strong>
                            </td>
		                    
		                    </tr> 
		                     <tr>  
	                         	<td valign="top" >
			                      <label for="party"> <g:message code="default.Institution.label"/> :</label>
			                    </td>
			                    <td valign="top" >
				                    <strong>  
				                    	${fieldValue(bean:grantAllocationInstance.party,field:'code')} 
			                    	</strong>
			                    </td>  
		                        <td valign="top" >
		                            	<label for="party"> <g:message code="default.HeadAllocation.UnallocatedAmount(Rs).label"/>:</label>
		                            </td>
		                            <td valign="top" >
		                            <strong><span style ="font-family:rupee">R</span> 
		                            ${currencyFormat.ConvertToIndainRS(balanceAmount)}</td>
		                            </strong>
		                            
                        </tr> 
            </table>
         </div>
 
         <div class="body">
            <h1><g:message code="default.HeadwiseAllocation.label"/></h1>
            <g:if test="${flash.error}">
            <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            
            <g:form method="post" >
                     <div class="dialog">
                    			<table border="0">
                        			<tbody>
                        				
                          				<tr>
                                			<td valign="top" class="name">
                                    			<label for="grantPeriod"><g:message code="default.GrantPeriod.label"/>:</label>
                                    			
                                			</td>
                                			<td valign="top" 
                                				class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'grantPeriod','errors')}">
                                    			<g:select optionKey="id" optionValue="name" 
                                    				from="${GrantPeriod.findAll('from GrantPeriod GP where GP.activeYesNo=\'Y\'order by defaultYesNo desc')}"  
                                    				name="grantPeriod.id" value="${grantAllocationSplitInstance?.grantPeriod?.id}" noSelection="['null':'-Select-']"  >
                                				</g:select>
                                			</td>
                            			</tr>
                            			<tr >
                                			<td valign="top" class="name">
                                    			<label for="accountHead"><g:message code="default.AccountHead.label"/>:</label>
                                    			<label for="accountHead" style="color:red;font-weight:bold"> * </label>
                                			</td>
                                			<td valign="top" class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'accountHead','errors')}">
                                    			<g:select optionKey="id" 
                                    				from="${accountHeadList}" 
                                    				optionValue="accHeadCode" name="accountHead.id" value="${grantAllocationSplitInstance?.accountHead?.id}" 
                                    				onchange="${remoteFunction(controller:'grantAllocationSplit',action:'updateSubAccount',update:'subAccountHead',  params:'\'accountHead=\' + this.value' )}" 
                                    				onFocus="${remoteFunction(controller:'grantAllocationSplit',action:'updateSubAccount',update:'subAccountHead',  params:'\'accountHead=\' + this.value' )}" noSelection="['null':'-Select-']" >
                                    			</g:select>
                         					</td>
                            	 		</tr>
                            	 		<tr>
                              				<td valign="top" class="name">
                              					<label for="subaccountHead"><g:message code="default.HeadAllocation.SubAccountHead.label"/>:</label>
                             				</td>
                            				<td valign="top" class="name">
                            				<div id="subAccountHead">
							    			<g:select 
							       				name="subAccountHead" 
							       				value="" 
								   				disabled="true" noSelection="['null':'-Select-']"  value="${grantAllocationSplitInstance?.accountHead?.id}" >
											</g:select>
										</div>
									</td>
                         		</tr> 
                        	<tr>
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="default.Amount.label"/>:</label>
                                    <label for="amount" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'amount','errors')}">
                                    <input type="text" id="amount" name="amount" value="${amount}" style="text-align: right"/>
                                </td>
                            </tr> 
                        
                            
                            <tr>
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="default.Description.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'description','errors')}">
                                 <g:textArea name="description" value="${fieldValue(bean:grantAllocationSplitInstance,field:'description')}" 
                                 	rows="3" cols="30"/>
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                <g:form>
                   				 <input type="hidden" name="projectId" value="${projectsInstance?.id}" />
                   				 
                    <span class="button"><g:actionSubmit class="save" action="saveProjectFundAllocation" value="${message(code: 'default.Create.button')}" onClick="return validateProjectFundAllocation()" /></span>
                    <span class="button"><g:actionSubmit class="save" action="cancel" value="${message(code: 'default.Cancel.button')}"  /></span>
                 </g:form> 
                </div>
            </g:form>
         </div>

    </body>
</html>
