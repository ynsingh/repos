<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251"/>
        <meta name="layout" content="main" />
        <g:javascript library="jquery"/>
        <title><g:message code="default.UploadProposal.head"/></title>   
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
        
        
            <h1><g:message code="default.UploadProposal.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="validateProposalControllId" method="post" >
                <div class="dialog">
                    <table border='0'>
                        <tbody>
                                  
                         <tr class="prop">
                        <td valign="top" colspan="2" class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                  <b><g:message code="default.PleaseenteryourControllerId.message"/></b>
                                </td>
                        </tr>
                        <tr class="prop">
                        	
                                 <td valign="top" style="width:10px;">
                                    <label for="name"><g:message code="default.ControlNumber.label"/>:</label>
                                </td>
                                <td valign="top" style="width:200px;">
                                    <input type="text" size="25" id="controllerId" name="controllerId" value=""/>
                                </td>
                          </tr>
                               
                                                                    
                          <tr class="prop">
                          <td valign="top" style="width:10px;">
                                    &nbsp;
                                </td>
                        <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <g:actionSubmit value="${message(code: 'default.Submit.button')}" onClick="" action="validateProposalControllId" />
                                &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onClick="Redirect()"  value="${message(code: 'default.Cancel.button')}"/>
                                </td>
                        </tr>           
                         <tr>
            				<td colspan="2"><div style="width:100%; border:#4c99cc 1px solid;"></div></td>
            			</tr>
            			
            			<tr>
            			
            				<td colspan="2">
            				<div id="grantorList"><b><label><g:message code="default.ClickHereStart.label"/></label><g:link action="selectGrantorForProposal" controller='proposal' id="" ><g:message code="default.NewForm.label"/></g:link>
            				</b>
            				</div></td>
            				
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
    </body>
</html>
