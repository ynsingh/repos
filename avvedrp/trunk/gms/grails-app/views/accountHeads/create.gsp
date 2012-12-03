

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.AccountHeads.CreateAccountHeads.head"/></title>    
     </head>
    <body>
     <div class="wrapper">
        <div class="body">
		<img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')"  >  
            <h1><g:message code="default.AccountHeads.CreateAccountHeads.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${accountHeadsInstance}">
            <div class="errors">
              <g:renderErrors bean="${accountHeadsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table width="950">
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="default.Name.label"/>:</label>
                                    <label for="name" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'name','errors')}">
                                    <input type="text" size="45" id="name" name="name" value="${fieldValue(bean:accountHeadsInstance,field:'name')}"/>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.Code.label"/>:</label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:accountHeadsInstance,field:'code')}"/>
                                    <label for="code" style="color:blue;font-weight:bold"> <g:message code="default.accounthead.code.label"/>:</label>
                                </td>
                            </tr>                         
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" onClick="return validateAccountHead()" /></span>
                </div>
            </g:form>
        </div>
        <div class="body">
            <h1><g:message code="default.AccountHeads.AccountHeadsList.head"/></h1>
           
            <g:if test="${accountHeadsInstanceList}">
              <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                   	        <g:sortableColumn property="name" title="${message(code: 'default.Name.label')}" />
                   	        <g:sortableColumn property="code" title="${message(code: 'default.Code.label')}" />
                   	        <th><g:message code="default.SubAccountHeads.label"/></th>
                   	        <g:if test="${flag == 0}"></g:if>	
				            <g:else> 
                   	        	<th><g:message code="default.Edit.label"/></th>
                            </g:else>
                        </tr>
                    </thead>
                    <tbody>
                     <% int j=0 %>
                     <g:each in="${accountHeadsInstanceList}" status="i" var="accountHeadsInstance">
                     <%  j++ %>
                     <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                           <td>${j}</td>
                           <td>${fieldValue(bean:accountHeadsInstance, field:'name')}</td>
                           <td>${fieldValue(bean:accountHeadsInstance, field:'code')}</td>
                           <td><g:link  action="showSubAccountHeads"  id="${accountHeadsInstance.id}"><g:message code="default.AddSubAccountHeads.label"/></g:link></td>
                           <g:if test="${flag == 0}"></g:if>	
				            <g:else> 
                   	        	<td><g:link action="edit" id="${accountHeadsInstance.id}"><g:message code="default.Edit.label"/></g:link></td>
                            </g:else>
                      </tr>
                     </g:each>
                    </tbody>
                </table>
              </div>
            </g:if>
            <g:else>
              <br><g:message code="default.NoRecordsAvailable.label"/></br>
            </g:else>
        </div>  
     </div>
    </body>
</html>
