<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
		<script src="${createLinkTo(dir:'images',file:'jquery.tools.min.js')}"></script>
		<g:javascript library="application" />
	</head>
	<script type="text/javascript">
		function noBack(){ window.history.forward(); }
	</script>
	<script>
		$(function() {
		 
			// if the function argument is given to overlay,
			// it is assumed to be the onBeforeLoad event listener
			$("a[rel]").overlay({
		 
				expose: 'white',
				effect: 'apple',
		 
				onBeforeLoad: function() {
		 
					// grab wrapper element inside content
					var wrap = this.getContent().find(".contentWrap");
		 
					// load the page specified in the trigger
					wrap.load(this.getTrigger().attr("href"));
				}
		 
			});
		});
    </script>
	<body   onload="noBack();" >
		<table cellspacing="0" cellpadding="0"  class="headerTable" width="100%" height="100%" border="0" >
			<tr>
				<td class="headerBg" valign="top" align="center" width="100%" height="100%" >
					<table cellspacing="0" cellpadding="0" class="headerTable" style=" padding:0;" align="center" width="100%" height="100%" border="0">
			  			<tr>
			   				 <td height="116" width="1024" valign="top"  style=" padding:0;">
									<div class="innnerBanner">
										<div class="loginLink">
											<span>
												<a href="${createLinkTo(dir:'/user/changePassword')}" target="right"><img src="${createLinkTo(dir:'images/themesky',file:'key.gif')}"  title="Change Password" alt="Change Password"  />&nbsp;&nbsp;|</a>&nbsp;&nbsp; 
												
												<img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}" onClick="window.open('../images/HELPDOC/UntitledFrameset-15.html','mywindow','width=800,height=500,left=0,top=100,screenX=0,screenY=100')" title="Help" alt="Help" />  
												
												</a> &nbsp;&nbsp;|</a>&nbsp;&nbsp;
												
												<img src="${createLinkTo(dir:'images/themesky',file:'aboutUs.jpg')}"  onClick="window.open('../images/aboutUs/AboutUs_MGMS_new.html','mywindow','width=600,height=300,left=0,top=100,screenX=0,screenY=100')" title="AboutUs" alt="AboutUs" /> 
												</a> &nbsp;&nbsp;|</a>&nbsp;&nbsp;
												
												<img src="${createLinkTo(dir:'images/themesky',file:'icon_glossary.gif')}"  onClick="window.open('../images/Glossary/GlossaryFrameset.html','mywindow','width=800,height=500,left=0,top=100,screenX=0,screenY=100')" title="Glossary" alt="Glossary" /> 
												</a> &nbsp;&nbsp;|</a>&nbsp;&nbsp;
												
												<font face="verdana" color:#01518e; font-weight:bold; text-decoration: none>
									   			<b>${session.UserLogin}</b> (<g:link  controller='logout' target="_parent"><g:message code="default.Logout.label"/></g:link>)
								   			</span>
										</div>
									</div>
									<div class="apple_overlay" id="overlay"> 
				 						<!-- the external content is loaded inside this tag --> 
										<div class="contentWrap"></div> 
				 					</div> 
				 			 </td>
			  			</tr>
					</table>
				</td>
			</tr>
		</table>	
	</body>
</html>
