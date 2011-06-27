<html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <title>Submit ProposalApplication</title> 
        
        <script type="text/javascript" src="/gms/js/appFormValidation.js" ></script>
        <script type="text/javascript" src="/gms/js/applicationValidation.js" ></script>      
    <g:javascript library="appFormValidation" />
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
       <g:message code="default.Youareabouttosubmit.message" default="You are about to submit pre-proposal"/></td></tr>
          <tr> 
      <td><table><tr><td>
      <g:form method="post" controller="proposal" >
 
   <g:actionSubmit class="inputbutton" value="Save" action="preProposalList"/>
   </g:form></td><td>
   <g:form method="post" controller="proposal" >
     
      <g:actionSubmit class="inputbutton" value="Submit" action="submitPreProposal"/>
       </g:form></td></tr></table></td>
      <td colspan="2">
   
      
   </tr>

</table>
	</div>
      
      </div>
    </body>
</html>