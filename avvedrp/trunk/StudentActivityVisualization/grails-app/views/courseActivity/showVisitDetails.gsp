<head>
	<meta name="layout" content="main" />
	<title>LMS List</title>
       </head>
<body>
	  <div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		       <g:menu/>
		</div>		
	<div id="content"> 
<!-- Middle area starts here -->	
	   <br /><h3 align="center">${institute} - ${year}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LMS - ${lms_used}</h3>
		
		<div>
		            <g:javascript src="swfobject.js"/>
                        <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="600">
                        <param name="movie" value="${resource(dir:'charts',file:'visit_details.swf')}" />
                        <param name="quality" value="high" />
                        <param name="bgcolor" value="#ffffff" />
                        <param name="allowScriptAccess" value="sameDomain" />
                        <param name="allowFullScreen" value="true" />
                        <!--[if !IE]>-->
                        <object type="application/x-shockwave-flash" data="${resource(dir:'charts',file:'visit_details.swf')}" width="100%" height="600">
                        <param name="quality" value="high" />
                        <param name="bgcolor" value="#ffffff" />
                        <param name="allowScriptAccess" value="sameDomain" />
                        <param name="allowFullScreen" value="true" />
                        <!--<![endif]-->
                        <p>
                        Either scripts and active content are not permitted to run or Adobe Flash Player version
                        9.0.28 or greater is not installed.
                        </p>
                        <a href="http://www.adobe.com/go/getflashplayer">
                        <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
                        </a>
                        <!--[if !IE]>-->
                        </object>
                        <!--<![endif]-->
                        </object>
		       </div> <!-- End of content div -->
	</div>
	 <g:footer/>
</body>
<!-- ##################################  Layout body ends here  ###########################################-->