<head>
	<meta name="layout" content="main" />
	<title>Create Site Admin</title>
	
</head>
<body>
	<div class="wrapper">
	<div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	
        
    <div class="innnerBanner">
	<div class="loginLink">
	<span>
	
	
	</a>&nbsp;&nbsp; <a href="${createLinkTo(dir:'/images/help',file:session.Help)}" title="Help" alt="Help" rel="#overlay" > 
<img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}"/> 
</a> &nbsp;&nbsp;|</a>&nbsp;&nbsp;<a href="#"><img src="${createLinkTo(dir:'images/themesky',file:'aboutUs.jpg')}" title="About Us" alt="About Us"/>&nbsp;&nbsp;</a>
	
   	</span>
   	</div>
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
					<h2>Forgot your password?</h2>
						</td>
					</tr>
              		<tr>
              			<td id="content" class="round-left column wide">
					<p>Admin will send password to the email address associated with your account.</p>
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
			      			<p>&nbsp;<label for="email">Please type your <strong>email address</strong> or <strong>User Name</strong> below.</label></p>
			      			<p>&nbsp;</p>
			      			<p>&nbsp;<input class="text_field" id="email" name="email" type="text" /></p>
			
			      
			      			<p>&nbsp;<input id="resend_password_submit" name="commit" type="submit" value="Send" /></p>
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
