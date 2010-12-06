<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script type="text/javascript">
function noBack(){ window.history.forward(); }
</script>

	<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
</head>
<body style="background-color:#d5e5ed"  onload="noBack();" >
	<div class="innnerBanner">
	<div class="loginLink">
		<span>
			<a href="${createLinkTo(dir:'/user/changePassword')}" target="right"><img src="${createLinkTo(dir:'images/themesky',file:'key.gif')}"  title="Change Password" alt="Change Password"  />&nbsp;&nbsp;|</a>&nbsp;&nbsp; <a href="${createLinkTo(dir:'/images/help',file:session.Help)}" target="right" title="Help" alt="Help" rel="#overlay" > 
			<img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}"/> 
			</a> &nbsp;&nbsp;|</a>&nbsp;&nbsp;<a href="#"><img src="${createLinkTo(dir:'images/themesky',file:'aboutUs.jpg')}" title="About Us" alt="About Us"/>&nbsp;&nbsp;|</a>
			<font face="verdana" color:#01518e; font-weight:bold; text-decoration: none>
   			<b><sec:username/></b> (<g:link  controller='logout' target="_parent"><g:message code="default.Logout.label"/></g:link>)
	</div>
	</div>			
</body>
</html>