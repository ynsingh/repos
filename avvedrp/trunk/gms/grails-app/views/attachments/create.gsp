<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'attachments.label', default: 'Attachments')}" />
        <title><g:message code="default.UploadProjectDocuments.head" /></title>
    </head>
    <body>
    	<div class="wrapper">
    	<g:if test="${(trackType == 'Projects')}">
    		<g:subMenuList />
    	</g:if>
		
        	<div class="proptable"> 
				<table class="tablewrapper" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
  					<tr>
    					<td scope="col"> 
					        <div class="body">
					            <h1><g:message code="default.UploadProjectDocuments.head" /></h1>
					            <g:if test="${flash.message}">
					            	<div class="message">${flash.message}</div>
					            </g:if>
					            <g:hasErrors bean="${attachmentsInstance}">
						            <div class="errors">
						                <g:renderErrors bean="${attachmentsInstance}" as="list" />
						            </div>
					            </g:hasErrors>
					            <g:form action="save" method="post" enctype="multipart/form-data">
					                <div class="dialog">
					                    <table>
					                        <tbody>
					                        	<input type="hidden" id="projects" name="projects" value="${projects}"/>
                                                	<tr class="prop">
						                                <td valign="top" class="name">
						                                    <label for="attachmentType">
						                                    	<g:message code="default.AttachmentType.label" />
					                                    	</label>
						                                 </td>
						                                 <td valign="top" 
						                                 	class="value ${hasErrors(bean: attachmentsInstance, field: 'attachmentType', 'errors')}">
						                                    <g:select name="attachmentType.id" optionValue="type" from="${attachmentTypeList}" 
						                                    	optionKey="id" value="${attachmentsInstance?.attachmentType?.id}" noSelection="['null':'-Select-']" />
						                                 </td>
						                            </tr>                   
						                            <tr class="prop">
						                                <td valign="top" class="name">
						                                    <label for="attachmentPath">
						                                    	<g:message code="default.UploadProjectDocuments.File.label" />
					                                    	</label>
						                                 </td>
						                                 <td valign="top" class="value">
						                                    <input type="file" name="attachmentPath" id="attachmentPath"  />
						                                 </td>
						                             </tr>
                    							  </tbody>
                    							</table>
                							</div>
							                <div class="buttons">
							                    <span class="button">
								                    <g:submitButton name="create" class="save" onClick="return validateAttachments()" 
								                    	value="${message(code: 'default.Create.button')}" />
							                    </span>
							                </div>
							            </g:form>
							        </div>
						        </div>
				              </td>
						  </tr>
						  <tr>
							  <td scope="row">
						        <div class="list">
					                <table width="97%" align="center" border="0" cellspacing="0" cellpadding="0">
					                    <thead>
					                        <tr>
												<th><g:message code="default.SINo.label" /></th>
                        
                            					<th><g:message code="default.AttachmentType.label" /></th>
                        						<g:if test="${(trackType == 'expenseRequestEntry')}">
    												<th><g:message code="default.ExpenseDescription.label"/></th>
										    	</g:if>
												<g:else>
										    		<th><g:message code="default.Project.label"/></th>
										    	</g:else>
                        						
                                                <th><g:message code="default.DocumentName.label" /></th>
		                            			<th><g:message code="default.View.label"/></th>
                            					
                            					<th><g:message code="default.Delete.label"/></th>
                        					</tr>
					                    </thead>
					                    <tbody>
					                    <g:each in="${attachmentsInstanceList}" status="i" var="attachmentsInstance">
					                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
					                        
					                            <td>${(i + 1)}</td>
					                        
					                            <td>${fieldValue(bean: attachmentsInstance, field: "attachmentType.type")}</td>
					                        
					                             <g:if test="${(trackType == 'expenseRequestEntry')}">
    													 <td>${(ExpenseRequestEntry.get(attachmentsInstance.domainId)).expenseDescription}</td>
    												</g:if>
													<g:else>
														<g:if test="${(trackType == 'grantExpense')}">
    													 <td>${(GrantExpense.get(attachmentsInstance.domainId)).projects.name}</td>
    													</g:if>
    													<g:else>
											    	 <td>${(Projects.get(attachmentsInstance.domainId)).name}</td>
											    	</g:else>     
											    	</g:else>                      
						                           
					                            <td>${fieldValue(bean: attachmentsInstance, field: "attachmentPath")}</td>
					                                                      
					                            <td>
						                            <a href="${g.createLink(action:'downloadAttachments', id:attachmentsInstance.id)}">
						                            	<g:message code="default.View.label" encodeAs="HTML" target="_blank"/>
						                            </a>
				       							</td>	
					       						<g:form>
					                   				 <input type="hidden" name="id" value="${attachmentsInstance?.id}" />
					           				   		 <input type="hidden"  name="attachmentType" 
					           				   		 	value="${attachmentsInstance?.attachmentType?.id}"/> 
					                                 <input type="hidden"  name="attachmentPath" 
					                                 	value=""${attachmentsInstance?.attachmentPath}"/>
					                                 <input type="hidden" id="domainId" name="domainId" 
					                                 	value="${attachmentsInstance.domainId}"/> 
					                                 <input type="hidden" id="domainId" name="domain" 
					                                 	value="${attachmentsInstance.domain}"/> 
					                                 <td> <g:actionSubmit class="delete" action="delete" onclick="return confirm('Are you sure?');" 
					                                 		value="${message(code: 'default.Delete.button')}" />
			                                 		 </td>
			                         			  </g:form>
                    						   </tr>
                    						 </g:each>
                    					 </tbody>
                					  </table>
        						   </td>
  							   </tr>
						    </table> 
					     </td>
				     </tr>
			     </table>  
		      </div>     
	      </div>      
       </div>
   </body>
</html>
