

<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
        <title><g:message code="default.User.CreateProjectPermission.head"/></title>   
		<g:javascript library="jquery" />       
    </head>
    <body>
    	<div class="wrapper">
        	<div class="body">
            	<h1><g:message code="default.User.CreateProjectPermission.head"/></h1>
            	<g:if test="${flash.message}">
            		<div class="message">${flash.message}</div>
            	</g:if>
            <g:hasErrors bean="${grantAllocationInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantAllocationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form name="accessPermission" action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                <label for="controllerName"><g:message code="default.UserName.label"/>:</label>
                                </td>
                                <td>
                                <g:select from="${userMapInstance.user}" name="user.id" id="user.id" optionValue="username" optionKey="id"
                                onchange="${remoteFunction(controller:'user',action:'getProjectName',update:'projectNameSel',params:'\'user=\'+this.value')};"
                                value="${fieldValue(bean:rolePrivilegesInstance,field:'controllerName')}" noSelection="['null':'-Select-']"></g:select>
                                </td>                                
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                               	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                               	<td>&nbsp;&nbsp;&nbsp;</td>
                            </tr> 
                                                 
                            <tr class="prop">
                                <td valign="top" class="name">
                                </td>                        
                            </tr>                                                
                            <tr class="prop">                                
                            </tr> 
                            <tr class="prop">                                
                            </tr>                        
                        </tbody>
                    </table>
    <div id="projectNameSel">                    
	<table width="46%" height="195" border="0">
  		<tr>
    		<td width="42%">
    		<div class="list">
						<table>						
							<tr>
								<th width="65%"><g:message code="default.ProjectName.label"/></th>
								<th><g:checkBox name="All" onclick="checkAll()" value="${All}" checked="false"/><g:message code="default.SelectAll.label"/></th>
							</tr>
						</table>
							<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
							<g:each in="${grantAllocationInstance}" status="i" var="grantAllocationInstance">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td width="65%">
								${grantAllocationInstance.projects.name}
								</td>
								<td width="40%"><g:checkBox name="projectName" value="${grantAllocationInstance.id}" checked="false"/>
								<input type="hidden" id="projectId" name="projectId" value="${grantAllocationInstance.id}" /> </td>
								</tr>
							</g:each>							
	</table>
	</div></div>
    </td>
      		<td>&nbsp;&nbsp;</td>
      		<td width="13%">
      		<table>
      		<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>
      		
      			<g:submitToRemote class="addbutton" action="getandSaveAccessPermission" value="	  		     " update="[success:'actionNameSel',failure:'error']" before="if(!validate()) return false"/>
                <g:submitToRemote class="removebutton" action="deleteProjectFromAccespermision" value="          " update="[success:'actionNameSel',failure:'error']" before="if(!validateForDelete()) return false"/>
              </td></tr></table>  
                </td>
                <td width="45%">
      			
      			<div class="list">
    					<table>
							
								<tr>
									<th width="65%"><g:message code="default.ProjectName.label"/></th>
									<th width="40%"><g:checkBox name="AllDelete" onclick="checkAllDelete()" value="${AllDel}" checked="false"/> <g:message code="default.SelectAll.label"/></th>
								</tr>
							</table>
							<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
							
								<g:each in="${projectInstance}" status="i" var="projectInstance">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td width="65%">
									${projectInstance.name}
								</td>
								<td width="40%"><g:checkBox name="projectId" value="${projectInstance.id}" checked="false"/></td>
							</tr>
							</g:each>
							
						</table>
						</div></div>
			</td>
  		</tr>
	</table>
	</div>
</div>


                    
                </div>
                
            </g:form>
        </div>
         </div>
    </body>
</html>
