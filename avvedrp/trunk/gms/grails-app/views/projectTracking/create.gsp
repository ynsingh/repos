

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ProjectClosure.ProjectClosureEntry.head"/></title> 
		<link rel="stylesheet" href="${resource(dir:'css',file:'custom-theme/jquery-ui-1.8.16.custom.css')}" />
		<g:javascript src="jquery-1.6.2.min.js"/>
		<g:javascript src="jquery-ui-1.8.16.custom.min.js"/>
		<script language="javascript" type="text/javascript">
			$(document).ready(function() {
			  $('#tabs').tabs();
                          $('#tabs').bind('tabsselect', function(event, ui) {
                                   var $tabs = $('#tabs').tabs();
                                 // var selected = $tabs.tabs('option', 'selected');
                                   var selected =ui.tab;
                                 // alert(selected);                                  
                                    switch (selected)
                                            {
                                              case 0:
                                                var refresh_url='${createLink(controller:'projectTracking',action:'closureDetails')}';
                                                //alert(refresh_url);
                                                $('#frame1').attr('src',refresh_url);
                                              break; 
                                              case 1:
                                                var refresh_url='${createLink(controller:'projectTracking',action:'statusDetails')}';
                                                //alert(refresh_url);
                                                $('#frame2').attr('src',refresh_url);
                                              break;                                             
                                              case 2:
                                                var refresh_url='${createLink(controller:'projectTracking',action:'fundDetails')}';
                                                //alert(refresh_url);
                                                $('#frame3').attr('src',refresh_url);
                                              break; 
                                              case 3:
                                                var refresh_url='${createLink(controller:'projectTracking',action:'create')}';
                                                //alert(refresh_url);
                                                $('#frame4').attr('src',refresh_url);
                                              break;
                                              
                                            }
                              });
			} )
                        
	</script>   
    </head>
    <body>
          <g:subMenuList/>  
        <div class="body"> <!-- Start of Main Div -->      

            <div class="info"><strong>Project Closure Details<strong></div>
            <br />
                   
                  <div class="demo"><!-- Tab Area Start -->
                    <link rel="stylesheet" href="${resource(dir:'css',file:'custom-theme/jquery-ui-1.8.16.custom.css')}" />
                        <div id="tabs">
                        <ul>
                        <li><a href="#tabs-1">Project Closure</a></li>
                        <li><a href="#tabs-2">Status Details</a></li>
                        <li><a href="#tabs-3">Fund Details</a></li> 
                        <g:if test="${projectsInstance.parent !=null}">
						<li><a href="#tabs-4">Close Project</a></li>   
						</g:if>                   
                        </ul>

                        <div id="tabs-1">
                        <iframe id="frame1" frameborder="0"  allowTransparency="true" STYLE="background-color:#DFE7FB" src="${createLink(controller:'projectTracking',action:'closureDetails')}"  width="960" height="425">                  
                        </iframe>   
                        </div>

                        <div id="tabs-2">
                        <iframe id="frame2" frameborder="0"  allowTransparency="true" STYLE="background-color:#DFE7FB"  src="" width="960" height="425">                  
                        </iframe> 
                        </div>

                        <div id="tabs-3">
                        <iframe id="frame3" frameborder="0"  allowTransparency="true" STYLE="background-color:#DFE7FB"  src="" width="960" height="425">                  
                        </iframe>   
                        </div>
                        <div id="tabs-4">
                        <iframe id="frame4" frameborder="0"  allowTransparency="true" STYLE="background-color:#DFE7FB"  src="" width="960" height="425">                  
                        </iframe>   
                        </div>
                        
                        </div> <!-- End of Tabs -->

                  </div><!-- Tab Area End -->


          </div> <!-- End of Main Div -->
</body>
</html>
