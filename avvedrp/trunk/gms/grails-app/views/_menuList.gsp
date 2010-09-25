<html>
       <head>
 
       
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
     
        
          <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'hro.css')}" type="text/css" media="screen" /> 
        <script src="${createLinkTo(dir:'images',file:'jquery-1.3.2.js')}"></script>
        <script  src="${createLinkTo(dir:'images',file:'jquery.event.hover.js')}" type="text/javascript" > 
        </script> 
     
             
        
        <script type="text/javascript"> 
            $(document).ready(function(){
                        
            
			
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
	        
	       <div class="Container"> 
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
                                           
                                               
                                                 <g:link controller="grantAllocation" action="projectDash" id="${grantAllocation[i].projects.id}">${grantAllocation[i].projects.code}</g:link>   
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
                            <g:link controller="projects" action="create"><img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New Projects" alt="New Projects"/>New Projects</g:link> 
                           
                        </li> 
                        <li> 
                            <g:link controller="projectDepartmentMap" action="create"><img src="${createLinkTo(dir:'images',file:'icons_07.gif')}" title="Projects to Department" alt="Projects to Department"/>Add Projects to Department</g:link> 
                        </li> 
						<li> 
                            <g:link controller="projectType/create"><img src="${createLinkTo(dir:'images',file:'icons_11.gif')}" title="Project Type" alt="Project Type"/>Project Type</g:link> 
                        </li> <li> 
                            <g:link controller="investigator/create"><img src="${createLinkTo(dir:'images',file:'icons_13.gif')}" title="PI" alt="PI"/>PI</g:link> 
                        </li> <li> 
                            <g:link controller="projectsPIMap/create"><img src="${createLinkTo(dir:'images',file:'icons_15.gif')}" title="Add project to PI" alt="Add project to PI"/>Add project to PI</g:link>  
                        </li> 
                        <li> 
                            <g:link controller="grantAllocation" action="mainDash"><img src="${createLinkTo(dir:'images',file:'icons_15.gif')}" title="DashBoard" alt="DashBoard"/>Dashboard</g:link>  
                        </li>
                        <li> 
                               <g:link controller="projects">Project List</g:link>  
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
                               			 <g:link controller="notificationsEmails/partyNotificationsList"><img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New Proposal" alt="New Proposal"/>Proposal</g:link>
                            		</li> 
                            	 <li> 
                               		 <g:link controller="attachmentType/create"> <img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New Attachment Type" alt="New Attachment Type"/>Attachment Type</g:link>
                            	</li> 
                     		</ul> 
					</div>	
					<a class="MegaMenuLink" href="javascript:void(0)">Institution</a> 
					<div class="MegaMenuContent"> 
						 <ul class="MegaMenuListsRight"> 
                           		 <g:if test="${session.Role == 'ROLE_ADMIN'}">  
                           		 <li> 
                               			<g:link controller="party/create"><img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New Institution" alt="New Institution"/>New Institution</g:link> 
                            		</li> 
                            	<li> 
                                	<g:link controller="partyDepartment/create">Institution Department</g:link>
                           		 </li> 
                           		 </g:if>
                           		  <g:if test="${session.Role == 'ROLE_SITEADMIN'}">
                           		  <li> 
                                	<g:link controller="partyDepartment/create">Institution Department</g:link>
                           		 </li> 
                           		  </g:if>
                           		 <li> 
                                	<g:link controller="party">Institution List</g:link>
                           		 </li> 

                           		  <li> 
                                	<g:link controller="partyDepartment">Department List</g:link> 
                           		 </li>                           		 
                     		</ul> 
						</div>	
					<a class="MegaMenuLink" href="javascript:void(0)">Grant Agency</a> 
					<div class="MegaMenuContent"> 
						 <ul class="MegaMenuListsRight"> 
                           	 <g:if test="${session.Role != 'ROLE_USER'}"> 
                           		 <li> 
                               			 <g:link controller="partyGrantAgency/create"><img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New Grant Agency" alt="New Grant Agency"/>New Grant Agency</g:link>
                            		</li> 
                               </g:if>
                           		 <li> 
                                	<g:link controller="partyGrantAgency">Grant Agency List</g:link>
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
                                	<g:link controller="accountHeads">Account Head List</g:link> 
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
                                	<g:link controller="grantPeriod">Grant Period List</g:link> 
                           		 </li> 
                     		</ul> 
					</div>	
					<a class="MegaMenuLink" href="javascript:void(0)">Fund Alloc</a>
					
					<div class="MegaMenuContent"> 
							 <ul class="MegaMenuListsRight"> 
                           		 <li> 
                               			<g:link controller="grantAllocation" action="fundAllot">Fund Allocation</g:link>
                            		</li> 
                     		</ul> 
					</div>	
					<a class="MegaMenuLink" href="javascript:void(0)">Users</a> 
					<div class="MegaMenuContent"> 
							 <ul class="MegaMenuListsRight"> 
							 <g:if test="${(session.Role == 'ROLE_SITEADMIN')}">
                           		 <li> 
                               			<g:link controller="user" action="create"> <img src="${createLinkTo(dir:'images',file:'icons_03.gif')}" title="New User" alt="New User"/>New User</g:link>
                            		</li> 
                             </g:if>
                           		 <li> 
                                	<g:link controller="user" action="list">Users List</g:link> 
                           		 </li> 
                           		 <g:if test="${session.Role == 'ROLE_ADMIN'}"> 
                           		 	 <li> 
                                	<g:link controller="user" action="newUserCreate">New Site Admin</g:link>
                           		 </li>
                           		  </g:if>
                           		  <li> 
                                	<g:link controller="user" action="accessControlEntry">Project Permission</g:link>
                           		 </li>
                           		  <li> 
                                	<g:link controller="rolePrivileges" action="create">Access Permission</g:link>
                           		 </li>
                     		</ul> 
					</div>	
					<a class="MegaMenuLink" href="javascript:void(0)">Reports</a> 
					<div class="MegaMenuContent"> 
							 <ul class="MegaMenuListsRight"> 

                            	 <li> 
                               			<g:link class="list" action="grantAllocationTrackingReports" controller="grantAllocationTracking">Grant Allocation/Project Status Reports</g:link>
                            	</li>
                            	<li>
                            		<g:link  controller='grantAllocation' action="reports">More ...</g:link>
                            	</li>
                     		</ul> 
					</div>	
					
	                
	            </div> 
	         	      </td>
	    </tr> 
	</table>  
	
	
    <!-- Menu end -->

    
       	
    </body>	
</html>