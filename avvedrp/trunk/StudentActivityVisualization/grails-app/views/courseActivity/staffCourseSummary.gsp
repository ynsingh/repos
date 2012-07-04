<meta name="layout" content="main" />	
<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'tabs/jquery-ui-1.8.16.custom.css')}" />
<g:javascript src="jquery.js"/>
<g:javascript src="ui/jquery.ui.core.js"/>
<g:javascript src="ui/jquery.ui.widget.js"/>
<g:javascript src="ui/jquery.ui.tabs.js"/>
<script>
	$(function() {
	      $("#tabs" ).tabs();	 
	});
</script>
<!-- ##################################  Layout body starts here  ###########################################-->
	  <div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		       <g:menu/>
		</div>		
	<div id="content"  align="center"> 
<!-- Middle area starts here -->	
<div id="tabs" style="width:100%">
	<ul>
		<li><a href="#tabs-1"  id="univ"><strong>RESOURCE ACCESS OVERVIEW</strong></a></li>
		<li><a href="#tabs-2"  id="inst"><strong>COMPONENT ACCESS OVERVIEW</strong></a></li>
		<li><a href="#tabs-3"  id="staff"><strong>STUDENT ACCESS OVERVIEW</strong></a></li>
	</ul>

	<div id="tabs-1">	
			<h4 align="center">Course Name - ${sel_course}</h4>
     <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="600"
			codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
			<param name="movie" value="${resource(dir:'charts',file:'resources_overview.swf')}" />
			<param name="quality" value="high" />
			<param name="bgcolor" value="#ffffff" />
			<param name="allowScriptAccess" value="sameDomain" />
			<!-- <param name="flashvars" value="favColor=Green" /> -->
			<param name="allowFullScreen" value="true" />
			<embed src="${resource(dir:'charts',file:'resources_overview.swf')}" quality="high" bgcolor="#869ca7"
			width="100%" height="600"  align="middle"
			play="true"
			loop="false"
			quality="high"
			allowScriptAccess="sameDomain"
			type="application/x-shockwave-flash"
			pluginspage="http://www.adobe.com/go/getflashplayer"
			flashvars="locale=${curr_locale}" >
			</embed>
			</object>      
			
	</div> <!-- END OF TAB 1 -->
	
	
	<div id="tabs-2">
	<h4 align="center">Course Name - ${sel_course}</h4>
		 <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="600"
			codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
			<param name="movie" value="${resource(dir:'charts',file:'components_overview.swf')}" />
			<param name="quality" value="high" />
			<param name="bgcolor" value="#ffffff" />
			<param name="allowScriptAccess" value="sameDomain" />
			<!-- <param name="flashvars" value="favColor=Green" /> -->
			<param name="allowFullScreen" value="true" />
			<embed src="${resource(dir:'charts',file:'components_overview.swf')}" quality="high" bgcolor="#869ca7"
			width="100%" height="600"  align="middle"
			play="true"
			loop="false"
			quality="high"
			allowScriptAccess="sameDomain"
			type="application/x-shockwave-flash"
			pluginspage="http://www.adobe.com/go/getflashplayer"
			flashvars="locale=${curr_locale}" >
			</embed>
			</object>      
	</div><!-- END OF TAB 2 -->
	
	
	<div id="tabs-3">
		 <h4 align="center">Course Name - ${sel_course}</h4>
		      <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="600"
		 			codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
		 			<param name="movie" value="${resource(dir:'charts',file:'students_overview.swf')}" />
		 			<param name="quality" value="high" />
		 			<param name="bgcolor" value="#ffffff" />
		 			<param name="allowScriptAccess" value="sameDomain" />
		 			<!-- <param name="flashvars" value="favColor=Green" /> -->
		 			<param name="allowFullScreen" value="true" />
		 			<embed src="${resource(dir:'charts',file:'students_overview.swf')}" quality="high" bgcolor="#869ca7"
		 			width="100%" height="600"  align="middle"
		 			play="true"
		 			loop="false"
		 			quality="high"
		 			allowScriptAccess="sameDomain"
		 			type="application/x-shockwave-flash"
		 			pluginspage="http://www.adobe.com/go/getflashplayer"
		 			flashvars="locale=${curr_locale}" >
		 			</embed>
			</object>      
	</div><!-- END OF TAB 3 -->
</div>
		
		
		
<!-- Middle area ends here -->		
  </div> <!-- End of content div -->
  <br />  <br />
</div>
<g:footer/>
<!-- ##################################  Layout body ends here  ###########################################-->