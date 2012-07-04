<meta name="layout" content="main" />
<div id="wrapper">
<div id="head">
<div id="logo_user_details">&nbsp;</div>
<g:render template="/sideMenu" />
</div>
<div id="content">
<div class="demo"><!-- Tab Area Start -->
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
                                                var refresh_url='${createLink(controller:'accomodationDetails',action:'firstTab')}';
                                                //alert(refresh_url);
                                                jQuery('#frame1').attr('src',refresh_url);
                                              break; 
                                              case 1:
                                                var refresh_url='${createLink(controller:'accomodationDetails',action:'secondTab')}';
                                                //alert(refresh_url);
                                                jQuery('#frame2').attr('src',refresh_url);
                                              break;                
                                                           
                                              }
                              });
			} )
                        
	      </script>
                    
                        <div id="tabs">
                        <ul>
                        <li><a href="#tabs-1">STAFF QUARTERS</a></li>
                        <li><a href="#tabs-2">STUDENTS' HOSTEL</a></li>
                        </ul>

                        <div id="tabs-1">
                        <iframe  id="frame1" frameborder="0"  allowTransparency="true" STYLE="background-color: transparent" src="${createLink(controller:'accomodationDetails',action:'firstTab')}" width="900" height="425" scrolling=no>                  
                        </iframe>   
                        </div>

                        <div id="tabs-2">
                        <iframe id="frame2" frameborder="0"  allowTransparency="true" STYLE="background-color: transparent"  src="" width="900" height="425" scrolling=no>                  
                        </iframe> 
                        </div>

                         </div> <!-- End of Tabs -->

                  </div><!-- Tab Area End -->
</div>



</div>
<g:render template="/footer" />
