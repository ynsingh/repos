<html>
    <head>
    	
        <title>Create ProposalApplication</title>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <calendar:resources lang="en" theme="tiger"/>
 
        <g:javascript library="prototype" />
        <g:javascript library="applicationValidation" />
        <g:javascript library="appFormValidation" />
    
        <link rel='stylesheet' href='/gms/plugins/modalbox-0.4/css/modalbox.css' />
       
		<g:javascript library="application" />				
    </head>

    <body topmargin="0" leftmargin="0" onload="${remoteFunction(action:'getForm', controller:'proposal',onSuccess:'returnResult(e)')};fitThePage();">    	
      <div class="wrapper">
   		<div class="body">
   			<div class="dialog">
   		 		<table>
	   		 	   <tbody>
	   		 	     
		   		 	 <tr class="prop">
		   		 	     <td>
		   		 	     <g:if test="${proposalApplicationForm != null}">
							 <iframe id='editframe' name='editframe' src="${createLinkTo(dir:'proposal',file:proposalApplicationForm)}"  width="785" height="450" scrolling="auto" frameborder="0">
							 </iframe>
							 </g:if>
						 </td>
					 </tr>
				   </tbody>
				</table>
				
					<div class="buttons">
      					<g:if test="${proposalInstance.version == 0}">
      						<g:submitButton name="create" class="save" value="${message(code: 'button.create.label', default: 'Create')}" onclick="submitForm()"/>
      					</g:if>
      					
      					<g:else>
      						<g:submitButton name="create" class="save" value="${message(code: 'button.update.label', default: 'Update')}" onclick="submitForm()"/>
      					</g:else>
      					
      				</div>
     
			</div>
     	</div>
      </div>
    </body>
</html>