<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <title><g:message code="default.HeadAllocation.title" /></title>         
    </head>
     <script>
    function validateGrantAllocationSplit()
{
    if( ( (document.getElementById("grantPeriod.id").value) == 'null') || ( (document.getElementById("grantPeriod.id").value) == '') )
    {
         alert("Please enter the Grant Period ");
         return false;
    }
    
    if( ( (document.getElementById("accountHead.id").value) == 'null') || ( (document.getElementById("accountHead.id").value) == '') )
    {
         alert("Please enter the Account Head ");
         return false;
    }
   
    if(isNaN(document.getElementById("amount").value))
    {
	    alert("Invalid Amount  ");
	    document.getElementById("amount").focus
	    return false;
    }
    if((document.getElementById("amount").value)=='')
    {
	    alert("Please enter Proper Amount  ");
	    return false;
    }
    if(eval(document.getElementById("amount").value)<=0)
    {
	    alert("Please enter Proper Amount  ");
	    return false;
    }
    
    if(parseFloat(document.getElementById("amount").value) > parseFloat(document.getElementById("UnAll").value))
	{
	alert("Please Enter Amount Less Than Or Equal To UnAllocated Amount");
	document.getElementById("amount").focus();
	return false;
	}
}   
    </script>
    <style>
	    .tablewrapperpopup
		{ 
			padding: 0px;
			text-align:left;
			width: 650px; 
		}
	</style>
	
	
	    <body>
	        
	    	<div class="tablewrapperpopup"> 
	    	<g:subMenuList/>
	    	
	        	<div class="proptable"> 
	        		<table  align="left">
	                   <tr>
		                    <td valign="top">
		                       <label for="project"><g:message code="default.Project.label"/>:</label>
		                    </td>
		                    <td valign="top" >
		                   		<strong>  ${fieldValue(bean:grantAllocationSplitInstance.projects,field:'code')} 
		                   		</strong>   
		                    </td>
		                    <td valign="top" >
                            	<label for="party"> <g:message code="default.HeadAllocation.GrantAmount.label"/>:</label>
                            </td>
                            <td valign="top" >
                            	<strong>
                                 	<span style ="font-family:rupee">R</span> 
                                 	${currencyFormat.ConvertToIndainRS(grantAllocationSplitInstance.projects.totAllAmount)}
                             	</strong>
                         	</td>
		                    
		                    </tr> 
		                     <tr>  
	                         	<td valign="top" >
			                      <label for="party"> <g:message code="default.Institution.label"/> :</label>
			                    </td>
			                    <td valign="top" >
				                    <strong>  
				                    	${fieldValue(bean:grantAllocationSplitInstance.grantAllocation.party,field:'code')} 
			                    	</strong>
			                    </td>  
		                        <td valign="top" >
		                            	<label for="party"> <g:message code="default.HeadAllocation.UnallocatedAmount(Rs).label"/>:</label>
		                            </td>
		                            <td valign="top" >
		                            	<strong>
		                                 	<span style ="font-family:rupee">R</span> 
		                                 	${currencyFormat.ConvertToIndainRS(unAllocatedAmount)}
		                             	</strong>
		                         	</td>
                        </tr> 
                    </table>
                </div>
                
        		<table  class="tablewrapperpopup" border="0" cellspacing="0" cellpadding="0" >
    				<tr>
    					<td scope="col"><div >
              				<h1 ><g:message code="default.HeadAllocation.AddMoreHeads.head"/></h1>
            				<g:if test="${flash.message}">
            					<div class="message">${flash.message}</div>
            				</g:if>
            				<g:hasErrors bean="${grantAllocationSplitInstance}">
            					<div class="errors">
                				<g:renderErrors bean="${grantAllocationSplitInstance}" as="list" />
            					</div>
            				</g:hasErrors>
            				<g:form action="save" method="post" onsubmit="refreshParentGrantAllocationSplit()" >
                			<div class="dialog">
                    			<table >
                        			<tbody>
                          				<tr>
                                			<td valign="top" class="name">
                                    			<label for="grantPeriod"><g:message code="default.GrantPeriod.label"/>:</label>
                                    			<input type="hidden" id="projectId" name="projectId" 
                                    				value="${fieldValue(bean:grantAllocationSplitInstance.projects, field:'id')}"/>
                                     			<input type="hidden" id="grantAllotId" name="grantAllotId" 
                                     				value="${fieldValue(bean:grantAllocationSplitInstance.grantAllocation, field:'id')}"/>
                                     			<input type="hidden" id="UnAll" name="UnAll" value="${params.UnAll}"/>  
                                			</td>
                                			<td valign="top" 
                                				class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'grantPeriod','errors')}">
                                    			<g:select optionKey="id" optionValue="name" 
                                    				from="${GrantPeriod.findAll('from GrantPeriod GP where GP.activeYesNo=\'Y\'order by defaultYesNo desc')}"  
                                    				name="grantPeriod.id" value="${grantAllocationSplitInstance?.grantPeriod?.id}"  >
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
                            	 		<tr >
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
                        	<tr >
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="default.Amount.label"/>:</label>
                                    <label for="amount" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationSplitInstance,field:'amount','errors')}">
                                    <input type="text" id="amount" name="amount" value="${fieldValue(bean:grantAllocationSplitInstance,field:'amount')}" 
                                    	style="text-align: right"/>
                                </td>
                            </tr> 
                        
                            
                            <tr >
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
                    <span class="button">
                    	<input class="save" type="submit" onClick="return validateGrantAllocationSplit()" value="${message(code: 'default.Create.button')}"/>
                	</span>
                 </div>
        	</g:form>
        </div>
        </td>
    </tr>
    <tr>
     <td scope="row">
    </body>
</html>
