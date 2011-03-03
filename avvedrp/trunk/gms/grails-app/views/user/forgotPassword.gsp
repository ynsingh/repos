<head>
	<meta name="layout" content="main" />
	<title><g:message code="default.CreateSiteAdmin.head"/></title>
	
</head>
<body>
	<div class="wrapper">
	<div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	
        
    <div class="innnerBanner">
	<div class="loginLink">
	<span>
	
	
	</a>&nbsp;&nbsp;  
<img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}" onClick="window.open('../images/HELPDOC/UntitledFrameset-15.html','mywindow','width=800,height=500,left=0,top=100,screenX=0,screenY=100')" title="Help" alt="Help" />  
</a> &nbsp;&nbsp;|</a>&nbsp;&nbsp;<a href="#"><img src="${createLinkTo(dir:'images/themesky',file:'aboutUs.jpg')}" onClick="window.open('../images/aboutUs/AboutUs_MGMS_new.html','mywindow','width=600,height=300,left=0,top=100,screenX=0,screenY=100')" title="AboutUs" alt="AboutUs" />&nbsp;&nbsp;</a>
	
   	</span>
   	</div>
	</div>  
<div id="messageBox">
		</div>
	<div class="body">
		<div style="background-color:#386890;width:100%;height: 5%"> </div>
		<h1></h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${person}">
			<div class="errors">
				<g:renderErrors bean="${person}" as="list" />
			</div>
		</g:hasErrors>
		 <table cellspacing="0" border="0px" width=75%>
			<tbody>
		            
					<g:form action="sendNewPassword">
					<tr>
              			<td id="content" class="round-left column wide">
							<div style="margin:1;padding:0;border:2px">
							<h2><g:message code="default.Forgotyourpassword.head"/></h2>
						</td>
					</tr>
              		<tr>
              			<td id="content" class="round-left column wide">
							<p><g:message code="default.Forgotyourpassword.AckMessage.head"/></p>
						</td>
					</tr>
					<tr>
              			<td valign="center">
				  <div>
			  				<div>
					    
							</div>
			  			<div >
			    			<fieldset>
			    			<p>&nbsp;</p>
			      			<p>&nbsp;<label for="email"><g:message code="default.Forgotyourpassword.InputMessage.label"/></label></p>
			      			<p>&nbsp;</p>
			      			<p>&nbsp;<input class="text_field" id="email" name="email" type="text" /></p>
			
			      			<p>&nbsp;</p>
			      			<p>&nbsp;<input id="submit" name="commit" type="submit" value="${message(code: 'default.Forgotyourpassword.Send.button')}"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onClick="Redirect()"  value="${message(code: 'default.Cancel.button')}"/> </p>
			      			<!--<p>&nbsp;<input id="submit" name="commit" type="submit" value="Send" onClick='displayAlertMessage("Please wait");' />&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onClick="Redirect()"  value="Cancel" /></p>-->
			    			<p>&nbsp;</p>
			    			</fieldset>
			  		</div>
				 </div>
				</td>
					</tr>
			</div>
			

		</g:form>
		
	</div>
	 </td>
	              
	            </tr>
	          </tbody>
        </table>
</body>
