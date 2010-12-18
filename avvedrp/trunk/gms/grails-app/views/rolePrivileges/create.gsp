<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.User.CreateAccessPermission.head"/></title>     
        <g:javascript library="jquery" />    
    </head>
    <body>
        <div class="wrapper">
         <div class="body">
            <h1><g:message code="default.User.CreateAccessPermission.head"/></h1>
            <g:if test="${flash.error}">
            <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${rolePrivilegesInstance}">
            <div class="errors">
                <g:renderErrors bean="${rolePrivilegesInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form name="rolePrivi" action="save" method="post" >
                <div class="dialog">
                    <table>
                       <tbody>                       
                          <tr class="prop">
                            	<td valign="top" class="name">
                                    <label for="role"><g:message code="default.Role.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:rolePrivilegesInstance,field:'role','errors')}">
                                    <g:select optionKey="id" optionValue="authority" from="${authorityInstanceList}" name="role.id" 
                                    onchange="${remoteFunction(controller:'rolePrivileges',action:'getActionName',update:'actionNameSel',params:'\'filename=\'+document.getElementById(\'controllerName\').value+\'&role=\'+this.value')};"
                                    value="${rolePrivilegesInstance?.role?.id}" ></g:select>
                                </td>
                                <td valign="top" class="name">
                                    <label for="controllerName"><g:message code="default.Privilegesgroup.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:rolePrivilegesInstance,field:'controllerName','errors')}">
                                	<g:select from="${filename}" name="controllerName" id="controllerName"
                                	onchange="${remoteFunction(controller:'rolePrivileges',action:'getActionName',update:'actionNameSel',params:'\'filename=\'+this.value+\'&role=\'+document.getElementById(\'role.id\').value')};"
                                	value="${fieldValue(bean:rolePrivilegesInstance,field:'controllerName')}" noSelection="['null':'-Select-']"></g:select>
                                </td>                                
                                <td>&nbsp;&nbsp;&nbsp;</td>
                                <td valign="top" class="name">
                                    &nbsp;&nbsp;&nbsp;
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:rolePrivilegesInstance,field:'description','errors')}">
                                    &nbsp;&nbsp;&nbsp;
                                </td>
                          </tr>                                                  
                          <tr class="prop">
                                <td valign="top" class="name">
                                    
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:rolePrivilegesInstance,field:'actionName','errors')}">                                                                      
                                </td>
                          </tr>                                        
                       </tbody>
                    </table>
	<div id="actionNameSel">
    <table width="46%" height="195" border="0">
  				<tr>
    				<td width="42%">
    					<div class="list">
							<table>							
								<tr>
									<th><g:message code="default.ActionName.label"/></th>
									<th><g:checkBox name="All" onclick="checkAllActions()" value="${All}" checked="false"/> <g:message code="default.SelectAll.label"/></th>
								</tr>
							</table>
							<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
							
							<g:each in="${actionNameList}" status="i" var="actionNameList">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td>
								${actionNameList}
								</td>
								<td><g:checkBox name="actionName" value="${actionNameList}" checked="false"/></td>
								</tr>
							</g:each>
						
						</table>
					</div>
      		</td>
      		<td width="13%">
      		<table>
      		<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>
      			<g:submitToRemote class="addbutton" action="save" value="           " update="[success:'actionNameSel',failure:'error']" before="if(!validateActions()) return false"/>
                <g:submitToRemote class="removebutton" action="delete" value="           " update="[success:'actionNameSel',failure:'error']" before="if(!validateActionForDelete()) return false"/>
                </td></tr></table>
                </td>
                </td><td width="45%">
      			<div class="list">
    					<table>
								<tr>
									<th><g:message code="default.ActionName.label"/></th>
									<th><g:checkBox name="AllDel" onclick="checkAllActionDelete()" value="${AllDel}" checked="false"/> <g:message code="default.SelectAll.label"/></th>
								</tr>
						</table>
						<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
  						<table style="width: 100%;" cellpadding="0" cellspacing="0">
							    <g:each in="${rolePrivilegesInstance}" status="i" var="rolePrivilegesInstance">
								<input type="hidden" id="deleteAction" name="deleteAction" value="${rolePrivilegesInstance.actionName}"/> 
							    </g:each>
						</table>
					</div></div>	
			 </td>
  		 </tr>
	</table></div>
                </div>
                
            </g:form>
        </div>
        </div>
    </body>
</html>
