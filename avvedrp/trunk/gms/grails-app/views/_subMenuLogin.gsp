<html>
	<head>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'jquery.megamenu.css')}" type="text/css" media="screen" />
        <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'hro.css')}" type="text/css" media="screen" /> 
    </head>
 	<body>
		<div class="hrmenu-container" >	
			<ul id="topnav"> 
				<li><g:link controller="login" action="auth"><g:message code="default.Home.label"/></g:link></li> 
		        </ul> 
		</div> 
	</body>	
</html>