<html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <title>Create ProposalApplication</title> 
        
        <script type="text/javascript" src="/gms/js/appFormValidation.js" ></script>
        <script type="text/javascript" src="/gms/js/applicationValidation.js" ></script>      
    </head>
    </head>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />

    <body onLoad="breakOut()" bgcolor="#ecddd8">
    <div class="wrapper">
     <div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	
        
        
 <br><br><br><br><br>
 
  <div class="body">
 
        <table width="70%" align="center" class="table" cellspacing="5" cellpadding="5">
         <tr><td>
       <g:message code="default.Youareabouttosubmit.message"/></td></tr>
          <tr> 
      <td><table><tr><td>
      <g:form method="post" controller="proposal" >
      <input type=hidden id="id" name="id" value="${params.id}">
 <input type=hidden id="proposalId" name="proposalId" value="${params.proposalId}">
   <g:actionSubmit class="inputbutton" value="${message(code: 'default.Submit.button')}" action="submitProposal"/>
   </g:form></td><td>
   <g:form method="post" controller="notificationsEmails" >
      <input type=hidden id="id" name="id" value="${params.id}">
 <input type=hidden id="proposalId" name="proposalId" value="${params.proposalId}">
      <g:actionSubmit class="inputbutton" value="${message(code: 'default.Cancel.button')}" action="partyNotificationsList"/>
       </g:form></td></tr></table></td>
      <td colspan="2">
   
      
   </tr>

</table>
	</div>
      
      </div>
    </body>
</html>