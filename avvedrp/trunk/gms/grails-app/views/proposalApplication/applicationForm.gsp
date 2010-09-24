<html>
    <head>
        <title>Create ProposalApplication</title>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <calendar:resources lang="en" theme="tiger"/>
        <g:javascript library="jquery"/>
        <g:javascript library="prototype" />
        <g:javascript library="applicationValidation" />
        <g:javascript library="appFormValidation" />
        <script src="${createLinkTo(dir:'images',file:'jquery-1.3.2.js')}"></script>
        <script src="${createLinkTo(dir:'images',file:'jquery.colorbox.js')}"></script>
        <link rel='stylesheet' href='/gms/plugins/modalbox-0.4/css/modalbox.css' />
        <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'colorbox.css')}" type="text/css" media="screen" /> 
        <script  src="${createLinkTo(dir:'images',file:'jquery.event.hover.js')}" type="text/javascript" ></script> 
        <script type="text/javascript"> 
           $(document).ready
           (
            function()
            {
             $("ul#topnav li").hover
             (
              function() 
               { //Hover over event on list item
			     $(this).css({ 'background' : '#fcdfa6 url(../../images/hr-menu-hover.jpg) repeat-x'}); //Add background color + image on hovered list item
			     $(this).find("span").show(); //Show the subnav
		       } , 
		       function() 
		        { //on hover out...
			      $(this).css({ 'background' : 'none'}); //Ditch the background
			      $(this).find("span").hide(); //Hide the subnav
		        }
		     );
		     //Examples of how to assign the ColorBox event to elements
		     $("a[rel='example1']").colorbox();
		     $("a[rel='example2']").colorbox({transition:"fade"});
		     $("a[rel='example3']").colorbox({transition:"none", width:"75%", height:"75%"});
		     $("a[rel='example4']").colorbox({slideshow:true});
		     $(".example5").colorbox();
		     $(".example6").colorbox({iframe:true, innerWidth:600, innerHeight:400});
		     $(".example7").colorbox({width:"80%", height:"90%", iframe:true});
		     $(".example8").colorbox({width:"50%", inline:true, href:"#inline_example1"});
		     $(".example9").colorbox
		     (
		      {
			   onOpen:function(){ alert('onOpen: colorbox is about to open'); },
			   onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
			   onComplete:function(){ alert('onComplete: colorbox has displayed the loaded content'); },
			   onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },
			   onClosed:function(){ alert('onClosed: colorbox has completely closed'); }
			  }
		     );
	        }
		   );
		</script>
		<g:javascript library="application" />				
    </head>

    <body topmargin="0" leftmargin="0" onload="${remoteFunction(action:'getForm', controller:'proposalApplication',onSuccess:'returnResult(e)')};fitThePage();">    	
      <div class="wrapper">
   		<div class="body">
   			<div class="dialog">
   		 		<table>
	   		 	   <tbody>
	   		 	     <g:if test="${params.id}">
	   		 		   <tr>
	   		 		      <td>
	   		 		         <table>
	   		 		            <tr>
	   		 		   
                                   <td valign="top" class="name"><g:message code="default.Projects.label"/></td>
                            
                                   <td valign="top" style="font-size:100%" class="value"><b>${notificationInstance?.project?.name.encodeAsHTML()}</b></td>
                            
                                </tr>
                                <tr>
                                
                                   <td valign="top" class="name"><g:message code="default.NotificationCode.label"/></td>
                            
                                   <td valign="top">${notificationInstance?.notificationCode}</td>
                            
                                </tr>
                                <tr>
                                   <td valign="top" class="name"><g:message code="default.Grantor.label"/></td>
                            
                                   <td>${GrantAllocation.findByProjects(notificationInstance.project).party.nameOfTheInstitution}</td>
                            
                                </tr>
                             </table>
                          </td>
                       </tr>
                     </g:if>
		   		 	 <tr>
		   		 	     <td>
							 <iframe id='editframe' name='editframe' src="${createLinkTo(dir:'proposalApplication',file:session.Appform)}" width="785" height="450" scrolling="auto" frameborder="0">
							 </iframe>
						 </td>
					 </tr>
				   </tbody>
				</table>
				<g:if test="${params.id}">
					<div class="buttons">
      					<input type="submit" name="save" value="${message(code: 'default.Save.button')}" onclick="submitForm()">
      				</div>
      			</g:if>
			</div>
     	</div>
      </div>
    </body>
</html>