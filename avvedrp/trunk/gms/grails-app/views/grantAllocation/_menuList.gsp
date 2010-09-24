<html>
       <head>
 
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <calendar:resources lang="en" theme="tiger"/>
        <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'jquery.megamenu.css')}" type="text/css" media="screen" />
          <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'hro.css')}" type="text/css" media="screen" /> 
       <script src="${createLinkTo(dir:'images',file:'jquery.tools.min.js')}"></script>  
        <script  src="${createLinkTo(dir:'images',file:'jquery.event.hover.js')}" type="text/javascript" > 
        </script> 
        <script  src="${createLinkTo(dir:'images',file:'jquery.megamenu.js')}"  type="text/javascript"> 
        </script> 
        <script type="text/javascript"> 
            $(document).ready(function(){
                        $(".MegaMenuLink").megamenu(".MegaMenuContent");
            
			
		$("ul#topnav li").hover(function() { //Hover over event on list item
			$(this).css({ 'background' : '#fcdfa6 url(../../images/hr-menu-hover.jpg) repeat-x'}); //Add background color + image on hovered list item
			$(this).find("span").show(); //Show the subnav
		} , function() { //on hover out...
			$(this).css({ 'background' : 'none'}); //Ditch the background
			$(this).find("span").hide(); //Hide the subnav
		});
			
		});
		</script>		
     
    </head>
    
    
    
    

  
<body >


  <!-- Menu -->
   <table id="Container"> 
	    <tr> 
	        <td valign="top"> 
	        
	       
	            <div class="MegaMenu"> 
	                
	                <a class="MegaMenuLink" href="javascript:void(0)">Projects</a> 
	                <div class="MegaMenuContent"> 
					<div class="left">
	                    <table class="MegaMenuTable" border="0"> 
	                        <tr> 
	                            <th> 
	                                <div class="MegaMenuHead"> 
	                                    Project List
	                                </div> 
	                            </th> 
	                        </tr> 
	                        <tr> 
	                            <td> 
	                                <ul class="MegaMenuLists"> 
	                                    
	                                    <%
                                        int k=0;
                                        if(grantAllocation.size()>4)
                                         k=4;
                                         else
                                         k=grantAllocation.size();
                                         
                                        for( int i=0; i<k;i++)
                                        {
                                        %>
                                           
                                            <li> 
                                                <g:link action="../grantAllocation/projectDash" id="${grantAllocation[i].projects.id}"   >${grantAllocation[i].projects.code}</g:link>  
                                            </li> 
                                            
                                             <%
                                              }
                                              %>
                                               <%
                                              if(k==4)
                                              {
                                            %>
                                     
                                              <li> 
                                                   <g:link controller="projects">More Projects</g:link>  
                                            </li> 
                                            <%
                                              }
                                             %>  
                                            
                                        </ul> 
                                                           
                                    </td> 


	                        </tr> 
	                    </table> 
						
						
				</div>
				<div class="right">
					 <ul class="MegaMenuListsRight"> 
                        <li> 
                            <a href="#"><img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New Projects" alt="New Projects"/>New Projects</a> 
                           
                        </li> 
                        <li> 
                            <a href="#"><img src="${createLinkTo(dir:'images',file:'icons_07.gif')}" title="Add Projects to Department" alt="Add Projects to Department"/>Add Projects to Department</a> 
                        </li> 
						<li> 
                            <a href="#"><img src="${createLinkTo(dir:'images',file:'icons_11.gif')}" title="Project Type" alt="Project Type"/>Project Type</a> 
                        </li> <li> 
                            <a href="#"><img src="${createLinkTo(dir:'images',file:'icons_13.gif')}" title="PI" alt="PI"/>PI</a> 
                        </li> <li> 
                            <a href="#"><img src="${createLinkTo(dir:'images',file:'icons_15.gif')}" title="Add project to PI" alt="Add project to PI"/>Add project to PI</a> 
                        </li> 
                      </ul> 
					</div>	
	          </div> 
					
	                <a class="MegaMenuLink" href="javascript:void(0)">Notifications</a> 
	                <div class="MegaMenuContent"> 
						<ul class="MegaMenuListsRight"> 
                                <li> 
                                    <img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New Notification" alt="New Notification"/><g:link controller="notification/create">New Notification</g:link> 
                                </li> 
                                <li> 
                                    <g:link controller="notification">Notification List</g:link>
                                </li> 
                            </ul> 
						</div>		
                    
            
					<a class="MegaMenuLink" href="javascript:void(0)">Proposal</a> 
	                <div class="MegaMenuContent"> 
							 <ul class="MegaMenuListsRight"> 
                           		 <li> 
                               			 <g:link controller="proposal"><img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New Proposal" alt="New Proposal"/>New Proposal</g:link>
                            		</li> 
                           		 <li> 
                                	<g:link controller="proposal"><img src="icons_07.gif" />Proposal List</g:link>
                           		 </li> 
                     		</ul> 
					</div>	
					<a class="MegaMenuLink" href="javascript:void(0)">Institution</a> 
					<div class="MegaMenuContent"> 
						 <ul class="MegaMenuListsRight"> 
                           		 <li> 
                               			<g:link controller="party/create"><img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New Institution" alt="New Institution"/>New Institution</g:link> 
                            		</li> 
                           		 <li> 
                                	<g:link controller="party"><img src="icons_07.gif" />Institution List</g:link>
                           		 </li> 
                           		  <li> 
                                	<g:link controller="partyDepartment/create"><img src="icons_07.gif" />Institution Department</g:link>
                           		 </li> 
                           		  <li> 
                                	<g:link controller="partyDepartment"><img src="icons_07.gif" />Department List</g:link> 
                           		 </li>                           		 
                     		</ul> 
						</div>	
					<a class="MegaMenuLink" href="javascript:void(0)">Grant Agency</a> 
					<div class="MegaMenuContent"> 
						 <ul class="MegaMenuListsRight"> 
                           		 <li> 
                               			 <g:link controller="partyGrantAgency/create"><img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New Grant Agency" alt="New Grant Agency"/>New Grant Agency</g:link>
                            		</li> 
                           		 <li> 
                                	<g:link controller="partyGrantAgency"><img src="icons_07.gif" />Grant Agency List</g:link>
                           		 </li> 
                     		</ul> 
						</div>	
					<a class="MegaMenuLink" href="javascript:void(0)">Account Head</a> 
					<div class="MegaMenuContent"> 
						 <ul class="MegaMenuListsRight"> 
                           		 <li> 
                               			 <g:link controller="accountHeads/create"> <img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New Account Head" alt="New Account Head"/>New Account Head</g:link>
                            		</li> 
                           		 <li> 
                                	<g:link controller="accountHeads"><img src="icons_07.gif" />Account Head List</g:link> 
                           		 </li> 
                     		</ul> 
					</div>	
					<a class="MegaMenuLink" href="javascript:void(0)">Grant Period</a> 
					<div class="MegaMenuContent"> 
							 <ul class="MegaMenuListsRight"> 
                           		 <li> 
                               			 <g:link controller="grantPeriod/create"><img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New Grant Period" alt="New Proposal"/>New Grant Period</g:link> 
                            		</li> 
                           		 <li> 
                                	<g:link controller="grantPeriod"><img src="icons_07.gif" />Grant Period List</g:link> 
                           		 </li> 
                     		</ul> 
					</div>	
					<a class="MegaMenuLinkOff" href="#">Fund Alloc</a> 
					<a class="MegaMenuLink" href="javascript:void(0)">Users</a> 
					<div class="MegaMenuContent"> 
							 <ul class="MegaMenuListsRight"> 
                           		 <li> 
                               			<g:link controller="user/create"> <img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New User" alt="New User"/>New User</g:link>
                            		</li> 
                           		 <li> 
                                	<g:link controller="user/list"><a href="#"><img src="icons_07.gif" />Users List</g:link> 
                           		 </li> 
                           		 	 <li> 
                                	<g:link controller="user/newUserCreate"><a href="#"><img src="icons_07.gif" />New Site Admin</g:link>
                           		 </li>
                     		</ul> 
					</div>	
					<a class="MegaMenuLinkOff" href="#">Reports</a> 
	                
	            </div> 
	         	      </td>
	    </tr> 
	</table>  
    <!-- Menu end -->

    
       	
    </body>	
</html>