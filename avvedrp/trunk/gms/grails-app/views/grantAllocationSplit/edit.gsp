<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
       <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <title><g:message code="default.HeadAllocation.EditHeadAllocation.head"/> </title>
    </head>
	<style>
	    .tablewrapperpopup
		{ 
		padding: 0px;
		text-align:left;
		width: 500px; 
		}
	</style>
    <body>
        <div class="tablewrapperpopup">
        	<div class="tablewrapperpopup">
	            <h1><g:message code="default.HeadAllocation.EditHeadAllocation.head"/></h1>
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${grantAllocationSplitInstance}">
		            <div class="errors">
		                <g:renderErrors bean="${grantAllocationSplitInstance}" as="list" />
		            </div>
	            </g:hasErrors>
	            <g:form method="post" >
	                <input type="hidden" name="id" value="${grantAllocationSplitInstance?.id}" />
	                <div class="dialog">
	                    <table>
	                        <tbody>
  								<g:javascript library="scriptaculous" />
                             	<tr>
	                                <td valign="top" class="name">
	                                    <label for="grantPeriod"><g:message code="default.GrantPeriod.label"/>:</label>
	                                </td>
	                                <td valign="top" 
	                                	class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'grantPeriod','errors')}">
	                                    <g:select optionKey="id" optionValue="name" from="${GrantPeriod.list()}" 
	                                    	name="grantPeriod.id" value="${grantAllocationSplitInstance?.grantPeriod?.id}" >
	                                    </g:select>
	                                </td>
	                            </tr> 
                            
	                            <tr >
	                                <td valign="top" class="name">
	                                    <label for="accountHead"><g:message code="default.AccountHead.label"/>:</label>
	                                </td>
	                                <g:if test="${grantAllocationSplitInstance.accHead != null}">
		                                <td valign="top" class=" " value ${hasErrors(bean:grantAllocationSplitInstance,field:'accHead','errors')}">
		                                    <g:select optionKey="id" 
			                                    from="${AccountHeads.findAll('from AccountHeads AH where AH.parent.id is NULL and AH.activeYesNo=\'Y\' order by AH.name')}" 
			                                    optionValue="name" name="accountHead.id" value="${grantAllocationSplitInstance?.accHead?.id}" 
			                                    onchange="${remoteFunction(controller:'grantAllocationSplit',action:'updateSubAccount',update:'subAccountHead',  params:'\'accountHead=\' + this.value' )}">
		                                    </g:select>
	                                	</td>
	                                </g:if>
	                                <g:else>
                                		<td valign="top" class=" " value ${hasErrors(bean:grantAllocationSplitInstance,field:'accountHead','errors')}">
	                                    	<g:select optionKey="id" 
		                                    	from="${AccountHeads.findAll('from AccountHeads AH where AH.parent.id is NULL and AH.activeYesNo=\'Y\' order by AH.name')}"
		                                     	optionValue="name" name="accountHead.id" value="${grantAllocationSplitInstance?.accountHead?.id}" 
		                                     	onchange="${remoteFunction(controller:'grantAllocationSplit',action:'updateSubAccount',update:'subAccountHead',  params:'\'accountHead=\' + this.value' )}">
	                                     	</g:select>
                                		</td>
                                	</g:else>
                               	</tr> 
                            	<tr >
                             		<td valign="top" class="name" value ${hasErrors(bean:grantAllocationSplitInstance,field:'subAccHead','errors')}">
                             			 <label for="subaccountHead"><g:message code="default.HeadAllocation.SubAccountHead.label"/>:</label>
                             		</td>
                                	<td valign="top" class="name">
                            			<div id="subAccountHead">
							    			<g:select 																	       
										       name="subAccountHead" 
										       value="" 
											   optionKey="id" optionValue="name" from="${accountHeadInstanceList}" noSelection="['null':'-Select-']"  
											   value="${grantAllocationSplitInstance?.subAccHead?.id}" >
											</g:select>
										</div>
									</td>
								</tr> 
                            <tr >
                            	<td valign="top" class="name">
                                    <label for="amount"><g:message code="default.Amount.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'amount','errors')}">
                                    <input type="text" id="amount" name="amount" 
                                    value="${amount}" style="text-align: right" />
                                </td>
                            </tr> 
                            <tr >
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="default.Description.label"/>:</label>
                                </td>
                                <td valign="top" 
                                	class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'description','errors')}">
                                 	<g:textArea name="description" value="${fieldValue(bean:grantAllocationSplitInstance,field:'description')}" 
                                 	rows="3" cols="30"/>
                                </td>
                            </tr> 
                         </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" 
	                    value="${message(code: 'default.Update.button')}" 
	                    onClick="return validateGrantAllocationSplit()"  />
                    </span>
                    <span class="button"><g:actionSubmit class="delete" 
	                    onclick="return confirm('Are you sure?');" 
	                    value="${message(code: 'default.Delete.button')}" />
                    </span>
                </div>
            </g:form>
        </div>
      </div>
    </body>
</html>
