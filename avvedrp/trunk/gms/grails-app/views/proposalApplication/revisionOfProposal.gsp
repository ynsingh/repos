<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251"/>
        <meta name="layout" content="main" />
        <g:javascript library="jquery"/>
        <title><g:message code="default.revisionOfProposal.head"/></title>   
        <script src="${createLinkTo(dir:'js',file:'vk_popup.js?vk_layout=AM Armenian Eastern')}"> </script>
                  
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
        
        
            <h1><g:message code="default.revisionOfProposal.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="revisionStatus" method="post" >
            <input type=hidden id="proposalId" name="id" value="${proposalApplicationInstance?.id}">
                <div class="dialog">
                    <fieldset class="border">
                <p>&nbsp;</p>
                	<p style="text-align:center"><label for="name"><g:message code="default.AfterChoosingAgreeOption.label"/></label></p>
                    <p>&nbsp;</p>
                    <p style="text-align:center"><g:radio name="revisionStatus" value="Y"/>
                    <label for="name"><g:message code="default.IAgree.label"/></label>
					&nbsp;&nbsp;<g:radio name="revisionStatus" value="N" checked="true"/>
					<label for="name"><g:message code="default.NotAgree.label"/></label></p>
                    <p>&nbsp;</p>
                    <p style="text-align:center">
                    <input class="inputbutton" type="submit" value="${message(code: 'default.Submit.button')}" />
                    &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="inputbutton" onClick="RedirectToLogin()"  value="${message(code: 'default.Cancel.button')}"/>
                    </p></fieldset>
               
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
