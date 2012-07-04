<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        
        <title>Upload Proposal Form</title>
    </head>
    <body>
    	<div class="wrapper">
    	
        	<div class="proptable"> 
				<table class="tablewrapper" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
  					<tr>
    					<td scope="col"> 
					        <div class="body">
					        <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
					            <h1><g:message code="default.UploadProposalForm.head"/></h1>
					            <g:if test="${flash.message}">
					            	<div class="message">${flash.message}</div>
					            </g:if>
					            <g:hasErrors bean="${proposalSettingsInstance}">
						            <div class="errors">
						                <g:renderErrors bean="${proposalSettingsInstance}" as="list" />
						            </div>
					            </g:hasErrors>
					            <g:form action="saveUploadedProposalForm" method="post" enctype="multipart/form-data">
					                <div class="dialog">
					                    <table>
					                        <tbody>
					                        	
                                                	                
						                            <tr class="prop">
						                                <td valign="top" class="name">
						                                    <label for="attachmentPath"><g:message code="default.PreProposalApplicationForm.label"/>:</label>
						                         	    <label for="attachmentPath" style="color:red;font-weight:bold"> * </label>
						                                 </td>
						                                 <td valign="top" class="value">
						                                    <input type="file" name="attachmentPath" id="attachmentPath"  /><g:message code="default.ApplicationFormat.label"/> 
						                                 </td>
						                             </tr>
                    							  </tbody>
                    							</table>
                							</div>
							                <div class="buttons">
							                    <span class="button">
								                    <g:submitButton name="create" class="save" onClick="return validateUploadProposalForm()"
								                    	value="${message(code: 'default.Create.button',default: 'Create')}" />
							                    </span>
							                </div>
							            </g:form>
							        </div>
						        </div>
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
