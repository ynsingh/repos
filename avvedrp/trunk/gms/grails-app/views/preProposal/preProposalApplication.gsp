<html>
    <head>
    	
        <title>Create ProposalApplication</title>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <calendar:resources lang="en" theme="tiger"/>
        <g:javascript library="jquery"/>
        <g:javascript library="prototype" />
        <g:javascript library="applicationValidation" />
        <g:javascript library="appFormValidation" />
        <script src="${createLinkTo(dir:'images',file:'jquery-1.3.2.js')}"></script>
        <script src="${createLinkTo(dir:'images',file:'jquery.colorbox.js')}"></script>
        <link rel='stylesheet' href='/gms/plugins/modalbox-0.4/css/modalbox.css' />
        <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'colorbox.css')}" type="text/css" media="screen" /> 
        <script  src="${createLinkTo(dir:'images',file:'jquery.event.hover.js')}" type="text/javascript" ></script> 
       
		<g:javascript library="application" />				
    </head>

    <body topmargin="0" leftmargin="0" onload="${remoteFunction(action:'getForm', controller:'preProposal',onSuccess:'returnResult(e)')};fitThePage();">    	
      <div class="wrapper">
   		<div class="body">
   			<div class="dialog">
   		 		<table>
	   		 	   <tbody>
	   		 	     
		   		 	 <tr class="prop">
		   		 	     <td>
		   		 	     <g:if test="${proposalApplicationForm != null}">
							 <iframe id='editframe' name='editframe' src="${createLinkTo(dir:'preProposal',file:proposalApplicationForm)}"  width="785" height="450" scrolling="auto" frameborder="0">
							 </iframe>
							 </g:if>
						 </td>
					 </tr>
				   </tbody>
				</table>
				
					<div class="buttons">
      					
      					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" onclick="submitForm()"/>
      				</div>
     
			</div>
     	</div>
      </div>
    </body>
</html>