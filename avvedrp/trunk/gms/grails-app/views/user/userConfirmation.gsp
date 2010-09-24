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

	<div class="body"><div style="background-color:#386890;width:100%;height: 5%"> </div>
		<h1></h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${person}">
		<div class="errors">
			<g:renderErrors bean="${person}" as="list" />
		</div>
		</g:hasErrors>
		<g:form action="saveNewUser">
			<div align="center" style="height: 65%">
			
			<table height="75%">
				<tr align="center"><td> <font size="3" color="#660000"></font></td></tr>
				<tr><td align="center">
       <b>You have successfully activated your account   </td></tr>
                    <tr><td>
       <b>To access your Account  <g:link action="auth" controller="login" >Log In</g:link></b>
                           </td></tr>
                  <tr><td>  After signing in, you can update Institution information.</td></tr>
                  <tr><td><HR></td></tr>
				</table><div></div>
				</div>

			

		</g:form>
	</div>
	</div>
</body>
