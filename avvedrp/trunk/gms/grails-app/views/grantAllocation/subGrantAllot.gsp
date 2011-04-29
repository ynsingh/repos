<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="Create default.GrantAllocation.head"/></title>         
    </head>
    <body>
		<div class="wrapper"> 
    	<g:subMenuList/>
    	    <div class="proptable">
     			<table width="100%" align="left">
     				<tr>
                    	<td valign="top" class="name">
                        	<label for="projects"><g:message code="default.Project.label"/>:</label>
                        </td>
                        <td valign="top" >
                         	<strong>  ${fieldValue(bean:projectInstance,field:'parent.code')} </strong>
                        </td>
                        <td valign="top" class="name">
                            <label for="party"> <g:message code="default.Grantor.label"/> :</label>
                        </td>
                        <td valign="top" class="name" >
                             <strong>  ${fieldValue(bean:partyInstance,field:'code')} </strong>
                        </td>
                        <td valign="top" class="name">
                            <label for="party"> <g:message code="default.AmountAllocated.label"/>:</label>
                        </td>
                        <td valign="top" >
                         	<strong>
                             	<g:message code="default.Rs.label" />
                             	${currencyFormat.ConvertToIndainRS(projectInstance.parent.totAllAmount)}
                             </strong>
                        </td>
                        <td valign="top" class="name">
                            <label for="party"> <g:message code="default.BalanceAmount.label"/>:</label>
                        </td>
                        <td valign="top" >
                         	<strong>
                             	<g:message code="default.Rs.label" />
                             	${currencyFormat.ConvertToIndainRS(grantAllocationInstance.balanceAmount)}
                             </strong>
                        </td>
                     </tr> 
                </table>
            </div>
            <div class="body"> 
				<table class="tablewrapper" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td scope="col">  
				            <h1><g:message code="default.SubProjectAllotment.head"/></h1>
				            <g:if test="${flash.message}">
				            	<div class="message">${flash.message}</div>
				            </g:if>
				            <g:hasErrors bean="${grantAllocationInstance}">
					            <div class="errors">
					                <g:renderErrors bean="${grantAllocationInstance}" as="list" />
					            </div>
				            </g:hasErrors>
				            <g:form action="subGrantSave" method="post" >
				                <div class="dialog">
				                <g:hiddenField name="parentProjectStartDate" 
	    							value="${fieldValue(bean:projectInstance, field:'parent.projectStartDate')}"/>
	   						 	<g:hiddenField name="parentProjectEndDate" 
	    							value="${fieldValue(bean:projectInstance, field:'parent.projectEndDate')}"/>
				                <g:hiddenField id="parentid" name="parent.id" 
                                     	value="${fieldValue(bean:projectInstance, field:'parent.id')}"/>
				                    <table>
				                        <tbody>
                    						<tr>
				                                <td valign="top" class="name">
				                                    <label for="project">
				                                    	<g:message code="default.GrantAllocation.SubProjects.label"/> <g:message code="default.Name.label"/>:
				                                    </label>
				                                    <label for="project" style="color:red;font-weight:bold"> * </label>
		                                           	<input type="hidden" id="project" name="project" 
		                                           		value="${fieldValue(bean:projectInstance, field:'parent.id')}"/>
				                                    <input type="hidden" id="grantAllotId" name="grantAllotId" 
				                                    	value="${fieldValue(bean:grantAllocation, field:'id')}"/>
				                                    <input type="hidden" id="grantor" name="grantor" 
				                                     	value="${fieldValue(bean:partyInstance, field:'id')}"/>
			                                	 </td>
                            					 <td valign="top" 
                                					class="value ${hasErrors(bean:projectInstance,field:'name','errors')}">
                                    				<input type="text" size="35" id="name" name="name" 
                                    					value="${fieldValue(bean:projectInstance,field:'name')}"/>
                             		 			 </td>
                             		 			 <td  valign="top" class="name">
                                    				<label for="code"><g:message code="default.Code.label"/>:</label>
                                    				<label for="code" style="color:red;font-weight:bold"> * </label>
                                				 </td>
                             		 			 <td valign="top" 
                                					class="value ${hasErrors(bean:projectInstance,field:'code','errors')}">
                                    				<input type="text" id="code" name="code" 
                                    					value="${fieldValue(bean:projectInstance,field:'code')}"/>
                               				 	 </td>
                  							</tr> 
			                        		<tr class="prop">
	                   						   <td colspan="3"><div align="left">
			                   						<label for="dateRangeFrom">
			                   							<g:message code="default.projects.MainProjectStartDate.label"/>: 
		                   							</label>
			                    					<strong>
			                    						<g:formatDate date="${projectInstance?.parent?.projectStartDate}" 
			                    							format="dd/MM/yyyy"/> 
		                    						</strong>
			                    					<label for="dateRangeTo"><g:message code="default.EndDate.label"/>: </label>              
			                    					<strong> 
					                    				<g:formatDate date="${projectInstance?.parent?.projectEndDate}" 
					                    				format="dd/MM/yyyy"/> 
				                    				</strong> 
		                    					</td>
	                    					</tr> 
                        	
                        					<tr class="prop">
				                            	<td valign="top" class="name">
						                            <label for="projectStartDate">
						                            	<g:message code="default.StartDate.label"/>:
					                            	</label>
					                            	<label for="projectStartDate" style="color:red;font-weight:bold"> * </label>
				                            	</td>
				                            	<td valign="top" 
					                            	class="value ${hasErrors(bean:projectInstance,field:'projectStartDate','errors')}">
					                            	<calendar:datePicker name="projectStartDate" 
					                            		value="${projectInstance?.projectStartDate}" defaultValue="${new Date()}" 
					                            		dateFormat= "%d/%m/%Y"/>
					                            </td>
	                            
					                            <td valign="top" class="name">
						                            <label for="projectEndDate">
						                            	<g:message code="default.EndDate.label"/>:
					                            	</label>
					                            	<label for="projectEndDate" style="color:red;font-weight:bold"> * </label>
					                            </td>
					                            <td colspan="3"  valign="top" 
					                            	class="value ${hasErrors(bean:projectInstance,field:'projectEndDate','errors')}">
					                            	<calendar:datePicker name="projectEndDate" value="${projectInstance?.projectEndDate}" 
					                            		defaultValue="${new Date()}" dateFormat= "%d/%m/%Y"/>
					                            </td>
			                             	</tr> 
		                             		<tr>
	                         					<td valign="top" class="name">
				                                    <label for="dateOfAllocation">
				                                    	<g:message code="default.DateOfAllocation.label"/>:
				                                    </label>
				                                   
				                                </td>
				                                <td valign="top" 
				                                	class="value ${hasErrors(bean:grantAllocationInstance,field:'dateOfAllocation','errors')}">
													<calendar:datePicker id="dateOfAllocation" name="dateOfAllocation" defaultValue="${new Date()}" 
				                                     	value="${grantAllocationInstance?.dateOfAllocation}" dateFormat= "%d/%m/%Y"   />
				                                </td>
				                                
				                                <td valign="top" class="name">
				                                    <label for="amountAllocated">
				                                    	<g:message code="default.AmountAllocated(Rs).label"/>:
			                                    	</label>
			                                    	<label for="amountAllocated" style="color:red;font-weight:bold"> * </label>
				                                </td>
				                                <td valign="top" 
				                                	class="value ${hasErrors(bean:grantAllocationInstance,field:'amountAllocated','errors')}">
				                                    <input type="text" id="amountAllocated" name="amountAllocated" 
				                                    value="${amount}" 
				                                    style="text-align: right"/>
				                                </td>
				                          	</tr>
				                            <tr>
					                            <td valign="top" class="name">
		                        					<label for="party">
		                        						<g:message code="default.GrantAllocation.Recipient.label"/>:
		                        					</label>
		                        					<label for="party" style="color:red;font-weight:bold"> * </label>
		                    					</td>
				                                <td valign="top" 
				                                	class="value ${hasErrors(bean:grantAllocationInstance,field:'party','errors')}">
				                                    <g:select id="recipient" optionKey="id" optionValue="code" 
					                                    from="${Party.findAll('from Party P where P.activeYesNo=\'Y\' and P.partyType is null ')}"  
					                                    name="party.id" value="${grantAllocationInstance?.party?.id}" 
					                                    noSelection="['null':'-Select-']">
				                                    </g:select>
				                                </td>
					                             
					                            <td valign="top" class="name">
					                                <label for="investigator"><g:message code="default.Investigator.label"/>:</label>
					                                <label for="investigator" style="color:red;font-weight:bold"> * </label>
					                            </td>
					                            <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'investigator','errors')}">
					                                <g:select id="investigator.id" optionKey="id" optionValue="name" from="${Investigator.findAll('from Investigator I where I.activeYesNo=\'Y\' ')}" name="investigator.id" value="" noSelection="['null':'-Select-']"></g:select>
					                            </td>
				                     		</tr>  
								                             
		                        			<tr >
		                        	            <td valign="top" class="name">
		                                			<label for="sanctionOrderNo">
		                                				<g:message code="default.GrantAllocation.SanctionOrderNo.label"/>:
		                            				</label>
		                            				<label for="sanctionOrderNo" style="color:red;font-weight:bold"> * </label>
		                            			</td>
				                                <td valign="top" 
				                                	class="value ${hasErrors(bean:grantAllocationInstance,field:'sanctionOrderNo','errors')}">
				                                    <input type="text" id="sanctionOrderNo" name="sanctionOrderNo" 
				                                    value="${fieldValue(bean:grantAllocationInstance,field:'sanctionOrderNo')}" 
				                                    	style="text-align: right"/>
				                                     <input type="hidden" id="totAllAmount" name="totAllAmount" 
				                                     	value="${projectInstance.parent.totAllAmount}"/>
				                                	<input type="hidden" id="amount" name="amount" 
				                                		value="${grantAllocationInstance.totAllAmount}"/>
				                                </td>
                        					</tr> 
				                            <tr>
				                            	<td valign="top" class="name">
		                                			<label for="remarks"><g:message code="default.Remarks.label"/>:</label>
		                            			</td>
		                            			<td valign="top" 
		                            				class="value ${hasErrors(bean:grantAllocationInstance,field:'remarks','errors')}">
		                                  			<g:textArea name="remarks" 
		                                  			value="${fieldValue(bean:grantAllocationInstance,field:'remarks')}" 
		                                  					rows="3" cols="30"/>
		                            			</td>
			                            	</tr>
                    					</tbody>
                					</table>
            					</div>
		        				<div class="buttons">
		                			<span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}"  onClick="return validateSubProject();return validateSubGrantAllot();" />
		                			</span>
		            			</div>
		          			</g:form>
		       			</td>
					</tr>
					<tr>
						<td scope="row"> <div class="list">
	            			<table width="97%" align="center" border="0" cellspacing="0" cellpadding="0">
	                			<thead>
	                    			<tr>
                    
               	        <g:sortableColumn property="id" 
               	        	title="${message(code:'default.SINo.label')}" />
                        
                        <g:sortableColumn property="projects.code" 
                        	title="${message(code:'default.ProjectCode.label')}"/>
               	        
               	        <g:sortableColumn property="projects.projectStartDate" 
               	        	title="${message(code:'default.ProjectStartDate.label')}"/>
                        
                        <g:sortableColumn property="projects.projectEndDate" 
                        	title="${message(code:'default.ProjectEndDate.label')}"/>
                        
                        <g:sortableColumn property="party"
                        	title="${message(code:'default.GrantAllocation.Recipient.label')}"/> 
                        
                        <g:sortableColumn property="amountAllocated" 
                        	title="${message(code:'default.AmountAllocated(Rs).label')}"/>
               	        
               	        <g:sortableColumn property="sanctionOrderNo" 
               	        	title="${message(code:'default.GrantAllocation.SanctionOrderNo.label')}"/>
               	        
               	        <g:sortableColumn property="granter" 
               	        	title="${message(code:'default.Grantor.label')}"/>
               	        	
               	        	
               	        
               	        <th><g:message code="default.UploadAttachments.label"/></th>
               	        
               	        <g:sortableColumn property="amountAllocated" 
               	        	title="${message(code:'default.GrantAllocation.GrantWithdrawal/Closure.label')}"/>
                    	<th><g:message code="default.FundTransfer.label"/></th>
               	       	<th><g:message code="default.Edit.label"/> </th>
                   </tr>
                </thead>
                <tbody>
                <g:each in="${grantAllocationInstanceList}" status="i" var="grantAllocationInstance">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    
                        <td>${(i + 1)}</td>
                       
                        <td>${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</td>
                         
                        <td><g:formatDate format="dd-MM-yyyy" date="${grantAllocationInstance.projects.projectStartDate}"/></td>
                        <td><g:formatDate format="dd-MM-yyyy" date="${grantAllocationInstance.projects.projectEndDate}"/></td>
                        
                        <td>${fieldValue(bean:grantAllocationInstance, field:'party.code')}</td>
                                              
                        <td>${currencyFormat.ConvertToIndainRS(grantAllocationInstance.amountAllocated)}</td>
                        
                       	<td>${fieldValue(bean:grantAllocationInstance,field:'sanctionOrderNo')} </td>
                       	<td>${fieldValue(bean:grantAllocationInstance,field:'granter.code')} </td>
                        
                        <td>
                            <g:link action="create"  controller='attachments' 
	                            id="${grantAllocationInstance.projects.id}" params="[trackType:'Projects']">
	                            <g:message code="default.Attachments.label"/>
                            </g:link>
                        </td>
	                    <td>
	                    	<g:link action="create"  controller='grantAllocationTracking' 
	                            id="${grantAllocationInstance.id}" params="[trackType:'withdraw']">
	                            <g:message code="default.GrantAllocation.GrantWithdrawal/Closure.label"/>
                            </g:link>
                        </td>
                        <td>
                        	<g:link action="create" controller="fundTransfer" id="${grantAllocationInstance.id}" params="[subMenu:'subGrantAllot']">
                        		<g:message code="default.FundTransfer.label"/>
                        	</g:link>
                        </td>
                        <td>
                        	<g:link action="editProAllot" id="${grantAllocationInstance.id}">
                     			<g:message code="default.Edit.label"/> 
                     		</g:link>
                 		</td>
                     </tr>
                 	</g:each>
            	</tbody>
        	</table>
       	</td>
	  </tr>
	</table>  
 </div>      
</div>   
   </body>
</html>
