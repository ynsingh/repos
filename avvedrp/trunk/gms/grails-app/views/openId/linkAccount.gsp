<head>
<meta name='layout' content='main'/>
<meta name='layout' content='main'/>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251"/>
<meta name="layout" content="main" />
 <g:javascript library="jquery"/>

<g:javascript library="applicationValidation" />
<g:javascript library="appFormValidation" />
<title>Link Account</title>
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
<img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}" onClick="window.open('../images/HELPDOC/UntitledFrameset-15.html','mywindow','width=800,height=500,left=0,top=100,screenX=0,screenY=100')">  
</a> &nbsp;&nbsp;|</a>&nbsp;&nbsp;<a href="#"><img src="${createLinkTo(dir:'images/themesky',file:'aboutUs.jpg')}" onClick="window.open('../images/aboutUs/AboutUs_MGMS_new.html','mywindow','width=600,height=300,left=0,top=100,screenX=0,screenY=100')">&nbsp;&nbsp;</a>
<b><sec:username/></b> (<g:link  controller='logout' target="_parent"><g:message code="default.Logout.label"/></g:link>)		
   	</span>
   	</div>
	</div>
<div class='body'>

	<h4>Enter your username and password to link with this OpenID</h4>

	<g:hasErrors bean="${command}">
	<div class="errors">
		<g:renderErrors bean="${command}" as="list"/>
	</div>
	</g:hasErrors>

	<g:if test='${flash.error}'>
	<div class="errors">${flash.error}</div>
	</g:if>

	<g:if test='${flash.successMessage}'>
	${flash.successMessage}
	</g:if>

	<g:else>
	<div class="dialog">
	<g:form action='linkAccount'>

		<table>
		<tr>
			<td>Open ID:</td>
			<td><span id='openid'>${openId}</span></td>
		</tr>

		<tr>
			<td><label for='username'>Username:</label></td>
			<td><g:textField name='username' value='${command?.username}'/></td>
		</tr>

		<tr>
			<td><label for='password'>Password:</label></td>
			<td><g:passwordField name='password' value='${command?.password}'/></td>
		</tr>

		</table>

		<input type='submit' class="inputbutton" value='Link'/>

	</g:form>
</div>
	</g:else>

</div>

<script>
(function() { document.getElementById('username').focus(); })();
</script>

</body>
