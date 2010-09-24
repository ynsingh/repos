

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Proposal</title>
        <ckeditor:resources /> 
    </head>
    <body>
    <div class="wrapper">
        <div class="body">
            <h1>Submit Proposal</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${proposalInstance?.id}" />
                   <input type="hidden" id="proposalSubmissionLastDate" name="proposalSubmissionLastDate" value="${fieldValue(bean:proposalInstance, field:'notification.proposalSubmissionLastDate')}"/>
                <div class="dialog">
                    <table width="97%" align="center" border="0" cellspacing="0" cellpadding="0">
                        <tbody>
                        
                            <tr class="prop">
			         <td valign="top" class="name">
			            <label for="code">Code:</label>
			         </td>
			         <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'code','errors')}">
			            <input type="text" id="code" name="code" value="${fieldValue(bean:proposalInstance,field:'code')}"disabled="true"/>
                                </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Party:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'party','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${Party.list()}" name="party.id" value="${proposalInstance?.party?.id}" ></g:select>
                                </td>
                            </tr> 
                        <tr>
			                 <td valign="top" class="name">
			                <label for="proposalSubmitteddate">Submitted Date:</label>
			               </td>
			              <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'proposalSubmitteddate','errors')}">
			               <calendar:datePicker name="proposalSubmitteddate" defaultValue="${new Date()}" value="${proposalInstance?.proposalSubmitteddate}" dateFormat= "%d/%m/%Y" dateFormat= "%d/%m/%Y"/>
			               </td>
                              </tr>
                    <tr class="prop">
                            <td valign="top" class="name">
                                <label for="description">Description:</label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'description','errors').encodeAsHTML()}">

			                    <fckeditor:editor name="description" width="100%" height="300" fileBrowser="default">
									${proposalInstance.description}
								</fckeditor:editor>  
							</td>	
                     </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" onClick="return validateProposal()" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="edit" action="submitProposal" onClick="return validateProposal()" value="Submit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
        </div>
    </body>
</html>
