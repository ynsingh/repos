<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251"/>
        <meta name="layout" content="main" />
        <g:javascript library="jquery"/>
        <title><g:message code="default.UpdateProposal.head"/></title>   
        <script src="${createLinkTo(dir:'js',file:'vk_popup.js?vk_layout=AM Armenian Eastern')}"> </script>
        <script>
    $(document).ready(function(){jQuery.ajax({type:'POST', url:'/gms/proposalApplication/getForm',success:function(data,textStatus){returnFormResult(data);},error:function(XMLHttpRequest,textStatus,errorThrown){}});});
    </script>           
    </head>
    <body>
    	
		<div class="wrapper">  
		<div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	
        
    <div class="innnerBanner">
	<div class="loginLink">
	<span>
	
	
	</a>&nbsp;&nbsp;  
<img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}" onClick="window.open('../images/HELPDOC/UntitledFrameset-15.html','mywindow','width=800,height=500,left=0,top=100,screenX=0,screenY=100')">  
</a> &nbsp;&nbsp;|</a>&nbsp;&nbsp;<a href="#"><img src="${createLinkTo(dir:'images/themesky',file:'aboutUs.jpg')}" onClick="window.open('../images/aboutUs/AboutUs_MGMS_new.html','mywindow','width=600,height=300,left=0,top=100,screenX=0,screenY=100')">&nbsp;&nbsp;</a>
	
   	</span>
   	</div>
	</div> 
		    <div class="body">
        
        
            <h1><g:message code="default.UpdateProposal.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveSelectGrantorForProposal" method="post" >
                <div class="dialog">
                    <table border='0'>
		    <tbody>
		    <tr class="prop">
		        <td valign="top" colspan="2" class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
		                  
		         </td>
		    </tr>
		    <tr class="prop">
		    
		    <td>
		     <g:link controller="proposalApplication" action="proposalApplicationDetailsView" id="${proposalApplicationInstance?.id}"><label for="name"><g:message code="default.DetailsFilledByYou.label"/></label></g:link>
		       </td> 
		       </tr>
		       <tr class="prop">
		    
		    	<td>
		     <g:link controller="proposalApplication" action="revisionOfProposal" id="${proposalApplicationInstance?.id}"><label for="name"><g:message code="default.EnterRevisedProposal.label"/></label></g:link>
		       </td> 
		      </tr>
		      <tr class="prop">
		    
		    	<td>
		     <g:link controller="proposal" action="uploadProposalApplication" id="${session.NotificationId}"><label for="name"><g:message code="default.UploadProposal.head"/></label></g:link>
		       </td> 
		      </tr>
		      
		    </tbody>
</table>
                </div>
                
            </g:form>
        </div>
                        </td>
        </tr><br/>
        
         </table>
         </div>
         <div class="footerdBar">
<br>
<label style="text-align: center;font: bold 9px Verdana;color: #104d6b;"><g:message code="default.footerMsg.label"/></label>
</div>
    </body>
</html>
