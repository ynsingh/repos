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
                             	<strong>  ${fieldValue(bean:projectInstance,field:'code')} </strong>
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
                                 	${currencyFormat.ConvertToIndainRS(projectInstance.totAllAmount)}
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
					                    <table>
					                        <tbody>
                        						<tr>
					                                <td valign="top" class="name">
					                                    <label for="project">
					                                    	<g:message code="default.GrantAllocation.SubProjects.label"/>
					                                    </label>
			                                           	<input type="hidden" id="project" name="project" 
			                                           		value="${fieldValue(bean:projectInstance, field:'id')}"/>
					                                    <input type="hidden" id="grantAllotId" name="grantAllotId" 
					                                    	value="${fieldValue(bean:grantAllocation, field:'id')}"/>
					                                    <input type="hidden" id="grantor" name="grantor" 
					                                     	value="${fieldValue(bean:partyInstance, field:'id')}"/>
				                                	</td>
                                					<td valign="top" 
						                                class="value ${hasErrors(bean:grantAllocationInstance,field:'projects','errors')}">
					                                    <g:select id="subProject" optionKey="id" optionValue="code" 
						                                    from="${subProjectsList}" name="projects.id" 
					                                    	value="${grantAllocationInstance?.projects?.id}" noSelection="['null':'Select']">
					                                	</g:select>
					                                </td>
                          							<td valign="top" class="name">
                                    					<label for="party">
                                    						<g:message code="default.GrantAllocation.Recipient.label"/>:
                                    					</label>
                                					</td>
					                                <td valign="top" 
					                                	class="value ${hasErrors(bean:grantAllocationInstance,field:'party','errors')}">
					                                    <g:select id="recipient" optionKey="id" optionValue="code" 
						                                    from="${Party.findAll('from Party P where P.activeYesNo=\'Y\' ')}"  
						                                    name="party.id" value="${grantAllocationInstance?.party?.id}" 
						                                    noSelection="['null':'Select']">
					                                    </g:select>
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
													<calendar:datePicker name="dateOfAllocation" defaultValue="${new Date()}" 
				                                     	value="${grantAllocationInstance?.dateOfAllocation}" dateFormat= "%d/%m/%Y"   />
				                                </td>
				                          
				                              	<td valign="top" class="name">
				                                    <label for="amountAllocated">
				                                    	<g:message code="default.AmountAllocated(Rs).label"/>:
			                                    	</label>
				                                </td>
				                                <td valign="top" 
				                                class="value ${hasErrors(bean:grantAllocationInstance,field:'amountAllocated','errors')}">
				                                    <input type="text" id="amountAllocated" name="amountAllocated" 
				                                    value="${fieldValue(bean:grantAllocationInstance,field:'amountAllocated')}" 
				                                    style="text-align: right"/>
				                                </td>
				                            </tr>
                            			<tr >
                            	            <td valign="top" class="name">
                                    			<label for="remarks"><g:message code="default.Remarks.label"/>:</label>
                                			</td>
                                			<td valign="top" 
                                				class="value ${hasErrors(bean:grantAllocationInstance,field:'remarks','errors')}">
                                      			<g:textArea name="remarks" 
                                      			value="${fieldValue(bean:grantAllocationInstance,field:'remarks')}" 
                                      					rows="3" cols="30"/>
                                			</td>
                                            <td valign="top" class="name">
                                    			<label for="sanctionOrderNo">
                                    				<g:message code="default.GrantAllocation.SanctionOrderNo.label"/>:
                                				</label>
                                			</td>
			                                <td valign="top" 
			                                	class="value ${hasErrors(bean:grantAllocationInstance,field:'sanctionOrderNo','errors')}">
			                                    <input type="text" id="sanctionOrderNo" name="sanctionOrderNo" 
			                                    value="${fieldValue(bean:grantAllocationInstance,field:'sanctionOrderNo')}" 
			                                    	style="text-align: right"/>
			                                     <input type="hidden" id="totAllAmount" name="totAllAmount" 
			                                     	value="${projectInstance.totAllAmount}"/>
			                                	<input type="hidden" id="amount" name="amount" 
			                                		value="${grantAllocationInstance.totAllAmount}"/>
			                                </td>
			                            </tr> 
                        			</tbody>
                    			</table>
                			</div>
            				<div class="buttons">
                    			<span class="button"><input class="save" type="submit" 
                    				value="${message(code: 'default.Create.button')}"  onClick="return validateSubGrantAllot()" />
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
		                            id="${grantAllocationInstance.projects.id}" params="[trackType:'withdraw']">
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
