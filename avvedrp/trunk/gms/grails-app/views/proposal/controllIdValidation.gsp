

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.projects.Search.head"/></title>         
    </head>
    <body>
    <div class="wrapper">
      
        <div class="body">
            <h1></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="default.PleaseenteravalidcontrollerId.message"/></div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="validateControllId" method="post" >
                <fieldset>
                
                <p>&nbsp;</p>
                <div ALIGN="center">
                    <table>
                        <tbody>
                        <tr class="prop">
                        <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                  <b><g:message code="default.PleaseenteryourControllerId.message"/></b>
                                </td>
                        </tr>
                        <tr class="prop">
                        	
                                
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <input type="text" size="25" id="controllerId" name="controllerId" value=""/>
                                </td>
                           
                               
                                <input type="hidden"  id="notification.id" name="notification.id" value="${id}"/>
                                <input type="hidden"  id="proposal.id" name="proposal.id" value="${proposalId}"/>
                                
                         </tr> 
                         <tr class="prop">
                        <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                  <g:actionSubmit value="${message(code: 'default.Submit.button')}" onClick="" action="validateControllId" />
                                </td>
                        </tr>
                        	                                                        
                        </tbody>
                    </table>
                    		</div>
                    		<p>&nbsp;</p>
			      			<p ALIGN=CENTER></p>
			    			<p ALIGN=RIGHT>&nbsp;&nbsp;</p>
               
                </fieldset>
             </g:form>
        </div>
        </div>
    </body>
</html>
