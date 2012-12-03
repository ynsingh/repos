<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        
        <title><g:message code="default.UploadProposalForm.head"/></title>
    </head>
    <body>
    	<div class="wrapper">
    	
        	<div class="proptable"> 
				<table class="tablewrapper" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
  					<tr>
    					<td scope="col"> 
					        <div class="body">
					        <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
					            <h1><g:message code="default.ImportExcelSheet.head"/></h1>
					            <g:if test="${flash.message}">
					            	<div class="message">${flash.message}</div>
					            </g:if>
					            <g:hasErrors bean="${proposalSettingsInstance}">
						            <div class="errors">
						                <g:renderErrors bean="${proposalSettingsInstance}" as="list" />
						            </div>
					            </g:hasErrors>
					            <g:form action="saveUploadedxls" method="post" enctype="multipart/form-data">
					                <div class="dialog">
					                    <table>
					                        <tbody>
					                        	
                                                	                
						                            <tr class="prop">
						                                <td valign="top" class="name">
						                                    <label for="attachmentPath"><g:message code="default.UploadexcelSheet.label"/>:</label>
						                         	    <label for="attachmentPath" style="color:red;font-weight:bold"> * </label>
						                                 </td>
						                                 <td valign="top" class="value">
						                                    <input type="file" name="attachmentPath" id="attachmentPath"  />
						                                 </td>
						                                 <input type="hidden" name="formType" value="PreProposal" />
						                             </tr>
                    							  </tbody>
                    							</table>
                							</div>
							                <div class="buttons">
							                    <span class="button">
								                    <g:submitButton name="create" class="save" value="${message(code: 'default.Create.button',default: 'Create')}" />
							                    </span>
							                </div>
							            </g:form>
							        </div>
						        </div>
						        
						     <g:if test="${partyProposalFormOldInstanceList}">
						        <div class="body">
									            <h1><g:message code="default.UploadPreProposalFormDetails.head"/></h1>
									           
									            <div class="list">
									                <table>
									                    <thead>
									                        <tr>
									                    	    <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
									                    	    <g:sortableColumn property="name" title="Excel Sheet" />
									                    	     <g:sortableColumn property="activeYesNo" title="Import" />
														       </tr>
									                    </thead>
									                    <tbody>
									                    <g:each in="${partyProposalFormOldInstanceList}" status="i" var="partyProposalFormOldInstance">
									                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
									                        
									                        	<td>${i+1}</td>
									                        	<td><g:link action="downloadUploadProposalForm" controller="attachments" id="${partyProposalFormOldInstance.id}">${fieldValue(bean:partyProposalFormOldInstance, field:'value')}</g:link></td>
									                        	
									                        	<td><g:link action="importExcel" controller="user" id="${partyProposalFormOldInstance.id}">Import</g:link></td>
											                     <!--<span class="button"><g:actionSubmit class="save" action="importExcel" value="${message(code: 'default.Update.button')}"/></span>-->
											                   
									                         </tr> 
									                    </g:each>
									                    </tbody>
									                </table>
									            </div>
									            
								</div>
						       </g:if>  
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
