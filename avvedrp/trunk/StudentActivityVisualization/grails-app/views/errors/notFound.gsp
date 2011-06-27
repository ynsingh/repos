<meta name="layout" content="main" />
<g:javascript src="jquery.js"/>
<g:javascript src="ddaccordion.js"/>

<!-- ##################################  Layout body starts here  ###########################################-->
	<div id="wrapper">
		<div id="head">
			<div class="innnerBanner">
			<g:isLoggedIn>
			<div class="loginLink">
			<span>
			<font face="verdana" color:#01518e; font-weight:bold; text-decoration: none>			
			<b>${session.UserId}</b> (<a href="${resource(dir:'/logout')}" class="logout">Logout</a>)
			</span>
			</div>
			</g:isLoggedIn>
			</div>		    
		</div>
		<br />
	<div id="content"> 	
<!-- Middle area starts here -->	
	    <g:menu/>
		
		<div style="float: left; width: 790px; margin-right: 5px;">
		    <img src="${resource(dir:'images',file:'error_page.jpg')}"/>
		</div>
		
		<div style="clear: both;">&nbsp;</div>
		<br /><br /><br />
<!-- Middle area ends here -->		
  </div> <!-- End of content div -->
</div>
<g:footer/>
<!-- ##################################  Layout body ends here  ###########################################-->