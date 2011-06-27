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
	
	
	</a>&nbsp;&nbsp;  
<img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}" onClick="window.open('../images/HELPDOC/UntitledFrameset-15.html','mywindow','width=800,height=500,left=0,top=100,screenX=0,screenY=100')" title="Help" alt="Help" />  
</a> &nbsp;&nbsp;|</a>&nbsp;&nbsp;<a href="#"><img src="${createLinkTo(dir:'images/themesky',file:'aboutUs.jpg')}" onClick="window.open('../images/aboutUs/AboutUs_MGMS_new.html','mywindow','width=600,height=300,left=0,top=100,screenX=0,screenY=100')" title="AboutUs" alt="AboutUs" />&nbsp;&nbsp;</a>
	
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
       <b><g:message code='default.successfullycreatedsiteadmin.message'/></td></tr>
                    <tr><td>
       <b><g:message code='default.confirmationemailhasbeensent.message'/>.</b>
                           </td></tr>
                           <tr><td>
       <b><g:message code='default.PleaseActivateYourAccount.message'/></b>
                           </td></tr>
                  <tr><td>  <g:link action="auth" controller="login" ><g:message code="default.login.label"/></g:link></td></tr>
                  <tr><td><HR></td></tr>
				</table><div></div>
				</div>

			

		</g:form>
	</div>
	</div>
</body>
