

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <title>Upload Proposal</title>
    </head>
    <style>
    .tablewrapperpopup
{ 
padding: 0px;
text-align:left;
width: 500px; 
}
</style>
<script>
	function refreshParent() {
  window.opener.location.href = window.opener.location.href;

  if (window.opener.progressWindow)
		
 {
    window.opener.progressWindow.close()
  }
  top.close();
}
</script>
    <body>
        
        <div class="tablewrapperpopup">
            <h1>Upload Proposal</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalInstance}" as="list" />
            </div>
           
            </g:hasErrors>
                <g:form controller="proposal" method="post" action="saveProposal" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${proposalInstance.id}" />
                <input type="hidden" name="notificationId" value="${proposalInstance?.notificationId}">
                 <input type="hidden" name="partyId" value="${proposalInstance?.partyId}">
                <div class="dialog">
                    <table class="tablewrapperpopup">
                        <tbody>
                        
    					<input type="file" name="myfile" size="60"  />
   						 <input class="save" type="submit" value="Upload"/>
							                                                                       
                        </tbody>
                    </table>
                </div>
                
            </g:form>
        </div>
    </body>
</html>
