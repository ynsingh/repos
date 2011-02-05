

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ProjectType.CreateProjectType.head"/></title>         
    </head>
    <body>
      <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.ProjectType.CreateProjectType.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectTypeInstance}">
              <div class="errors">
                <g:renderErrors bean="${projectTypeInstance}" as="list" />
              </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type"><g:message code="default.Type.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectTypeInstance,field:'type','errors')}">
                                    <input type="text" id="type" name="type" value="${fieldValue(bean:projectTypeInstance,field:'type')}"/>
                                </td>
                            </tr>  
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" onClick="return validateProjectType();" value="${message(code: 'default.Create.button')}" /></span>
                </div>
            </g:form>
        </div>
        
        <div class="body">
            <h1></h1>
            
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                        
                   	        <g:sortableColumn property="type" title="${message(code: 'default.Type.label')}" />

                   	        <th><g:message code="default.Edit.label"/></th>
                        
                   	    </tr>
                    </thead>
                    <tbody>
                    <% int j=0 %>
                      <g:each in="${projectTypeInstanceList}" status="i" var="projectTypeInstance">
                        <%  j++ %>
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${j}</td>
                        
                            <td>${fieldValue(bean:projectTypeInstance, field:'type')}</td>

                            <td><g:link action="edit" id="${projectTypeInstance.id}"><g:message code="default.Edit.label"/></g:link></td>
                        </tr>
                      </g:each>
                    </tbody>
                </table>
            </div>
        </div>
      </div>
    </body>
</html>
