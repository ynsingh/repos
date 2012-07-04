<meta name="layout" content="main" />	
<div id="wrapper">
		<div id="head">
		    <div id="logo_user_details">&nbsp;</div>
		    <g:render template="/sideMenu" />
		</div>
<div id="content">
<div class="demo">
<script language="javascript" type="text/javascript">
         jQuery.noConflict();
         jQuery(document).ready(function() {
			 jQuery('#tabs').tabs();
                          jQuery('#tabs').bind('tabsselect', function(event, ui) {
                                   var jQuerytabs = jQuery('#tabs').tabs();
                                 // var selected = $tabs.tabs('option', 'selected');
                                   var selected =ui.index;
                                 // alert(selected);                                  
                                    switch (selected)
                                            {
                                              case 0:
                                                var refresh_url='${createLink(controller:'institutionDetails',action:'addressDetails')}';
                                                //alert(refresh_url);
                                                jQuery('#frame1').attr('src',refresh_url);
                                              break; 
                                              case 1:
                                                var refresh_url='${createLink(controller:'institutionDetails',action:'nodalOfficerDetails')}';
                                                //alert(refresh_url);
                                                jQuery('#frame2').attr('src',refresh_url);
                                              break;                
                                              case 2:
                                                var refresh_url='${createLink(controller:'institutionDetails',action:'affiliationDetails')}';
                                                //alert(refresh_url);
                                                jQuery('#frame3').attr('src',refresh_url);
                                              break;              
                                              case 3:
                                                var refresh_url='${createLink(controller:'institutionDetails',action:'moreInformation')}';
                                                //alert(refresh_url);
                                                jQuery('#frame4').attr('src',refresh_url);
                                              break;                 
                                              }
                              });
			} )
                        
	      </script>
                 
                        <div id="tabs">
			                        <ul>
			                        <li><a href="#tabs-1">ADDRESS</a></li>
			                        <li><a href="#tabs-2">NODAL OFFICER</a></li>
			                        <li><a href="#tabs-3">AFFILIATION</a></li>   
			                        <li><a href="#tabs-4">MORE INFO...</a></li>                     
			                        </ul>
			
			                       <div id="tabs-1">
			                        <iframe  id="frame1" frameborder="0"  allowTransparency="true" STYLE="background-color: transparent" src="${createLink(controller:'institutionDetails',action:'addressDetails')}" width="900" height="425" scrolling=no >                  
			                        </iframe>  
			               			</div>
			
			                        <div id="tabs-2">
			                        <iframe id="frame2" frameborder="0"  allowTransparency="true" STYLE="background-color: transparent"  src="" width="900" height="425" scrolling=no>                  
			                        </iframe> 
			                        </div>
			
			                        <div id="tabs-3">
			                        <iframe id="frame3" frameborder="0"  allowTransparency="true" STYLE="background-color: transparent" src="" width="900" height="425" scrolling=no>                  
			                        </iframe>   
			                        </div>
			                        
			                        <div id="tabs-4">
			                        <iframe id="frame4" frameborder="0"  allowTransparency="true" STYLE="background-color: transparent" src="" width="900" height="425" scrolling=no>                  
			                        </iframe>   
			                        </div>
                       
                         </div>
                   
                  </div>
                
</div>
</div>
<g:render template="/footer" />





