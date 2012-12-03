<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'attachments.label', default: 'MessageAttachments')}" />
        <title><g:message code="default.UploadProjectDocuments.head" /></title>
    </head>
    <body>
    	<div class="wrapper">
    	 		<g:subMenuList/>
    			
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
					            <g:form action="saveMessage" method="post" enctype="multipart/form-data">
					                <div class="dialog">
					                    <table>
					                        <tbody>
					                        	<input type="hidden" id="projects" name="projects" value="${projects}"/>
                                                	<tr class="prop">
						                                <td valign="top" class="name">
						                                    <label for="attachmentPath">
						                                    	<g:message code="default.UploadProjectDocuments.File.label"/>
					                                    	</label>
						                                 </td>
						                                 <td valign="top" class="value">
						                                    <input type="file" name="attachmentPath" id="attachmentPath"/>
						                                 </td>
						                             </tr>
                    							  </tbody>
                    							</table>
                							</div>
							                <div class="buttons">
							                    <span class="button">
								                    <g:submitButton name="create" class="save" value="${message(code: 'default.Create.button')}" />
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
                        
                            					<th><g:message code="default.Project.label"/></th>
										    	
                                                <th><g:message code="default.DocumentName.label" /></th>
		                            			<th><g:message code="default.View.label"/></th>
                            					
                            					<th><g:message code="default.Delete.label"/></th>
                        					</tr>
					                    </thead>
					                    <tbody>
					                    <g:each in="${messageAttachmentsInstanceList}" status="i" var="attachmentsInstance">
					                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
					                        
					                            <td>${(i + 1)}</td>
					                        
					                            <td>${(attachmentsInstance.projects.name)}</td>
											       
					                            <td>${fieldValue(bean: attachmentsInstance, field: "attachmentPath")}</td>
					                                                      
					                            <td>
						                            <a href="${g.createLink(action:'downloadAttachments', id:attachmentsInstance.id)}">
						                            	<g:message code="default.View.label" encodeAs="HTML" target="_blank"/>
						                            </a>
				       							</td>	
					       						<g:form>
				                   				 <input type="hidden" name="id" value="${attachmentsInstance?.id}" />
				           				   		 
				                                 <input type="hidden"  name="attachmentPath" 
				                                 	value=""${attachmentsInstance?.attachmentPath}"/>
				                                <td> 
					                                <g:actionSubmit class="delete" action="deleteMessageAttach" onclick="return confirm('Are you sure?');" 
					                                	value="${message(code: 'default.Delete.button')}"/>
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
