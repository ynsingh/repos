<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'authority.label', default: 'Authority')}" />
        <title><g:message code="default.Role.CreateRole.head"/></title>
   </head>
   <body>
     <div class="wrapper">
       <table class="tablewrapper"> 
        <tr>
         <td> 
           <div class="body">
            <h1><g:message code="default.Role.CreateRole.head" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${authorityInstance}">
              <div class="errors">
                <g:renderErrors bean="${authorityInstance}" as="list" />
              </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="authority"><g:message code="default.Role.label"/></label>
                                    <label for="role" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: authorityInstance, field: 'authority', 'errors')}">
                                    <g:textField id="authority" name="authority" value="${authorityInstance?.authority}" />
                                	 <label for="role" style="color:blue;font-weight:bold"> <g:message code="default.Role.NameFormat.label"/></label>
                                </td>
                                <td valign="left">
                                   
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="default.Description.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: authorityInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${authorityInstance?.description}" />
                                </td>
                            </tr>
                        	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo"><g:message code="default.Active.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:authorityInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:authorityInstance,field:'activeYesNo')}" />
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.Create.button')}" onClick="return validateAuthority();"/></span>
                </div>
            </g:form>
           </div>
           <div class="body">
             <h1></h1>
             <g:if test="${flash.message}">
               <div class="message">${flash.message}</div>
             </g:if>
             <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                        
                            <g:sortableColumn property="authority" title="${message(code: 'default.Role.label')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'default.Description.label')}" />
                        
                        	<g:sortableColumn property="activeYesNo" title="${message(code: 'default.Active.label')}" />
                        	
                        	<th><g:message code="default.Edit.label"/></th>
                        	
                        </tr>
                    </thead>
                    <tbody>
                      <% int j=0 %>
                      <g:each in="${authorityInstanceList}" status="i" var="authorityInstance">
                        <%  j++ %>
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${j}</td>
                        
                            <td>${fieldValue(bean: authorityInstance, field: "authority")}</td>
                        
                            <td>${fieldValue(bean: authorityInstance, field: "description")}</td>
                        	
                        	<td>
                	             <g:if test="${fieldValue(bean:authorityInstance, field:'activeYesNo') == 'Y'}">
    						    	<g:message code="default.YES.label"/>
    							 </g:if>
    							 <g:else>
    							    <g:message code="default.NO.label"/>
    							 </g:else>
                        	</td>
                        	<td><g:link action="edit" id="${authorityInstance.id}"><g:message code="default.Edit.label"/></g:link></td>
                        </tr>
                      </g:each>
                    </tbody>
                </table>
             </div>
           </div>
         </td>
        </tr>
       </table>
     </div>
   </body>
</html>
