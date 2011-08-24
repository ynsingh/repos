<g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
	            </g:if>
    <g:if test="${flash.error}">
	            <div class="errors">${flash.error}</div>
        	  </g:if>
	 <table width="100%">
	  		<tr>
	    		<td>
	    		<div class="list">
						<table>						
							<tr>
								<th width="65%"><g:message code="default.RoleNames.label"/></th>
								<th width="15%"><g:checkBox name="All" onclick="checkAllBox(document.userRoles.All,document.userRoles.roleId)" value="${All}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
							</tr>
						</table>
						<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
								<g:each in="${roleList}" status="i" var="roleInstance">
									<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
									<td width="65%">
										${roleInstance.authority}
									</td>
									<td width="11%"><g:checkBox id="roleId" name="roleId" value="${roleInstance.id}" checked="false"/>
									
									</tr>
								</g:each>							
							</table>
					  </div>
	   </div>
      </td>
  		<td>&nbsp;&nbsp;</td>
  		<td width="13%">
  		<table>
  		<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>
  		
  			<g:submitToRemote class="addbutton" action="assignRolesToUser" value="	  		     " update="[success:'assignUserRol',failure:'error']" before="if(!validateEvalItemNotificationMap(document.userRoles['personMapInstance.user.id'],'Please Select a User',document.userRoles['roleId'],'You did not check a box. Please check a box.')) return false"/>
            <g:submitToRemote class="removebutton" action="removeRolesFromUser" value="          " update="[success:'assignUserRol',failure:'error']" before="if(!validateEvalItemNotificationMap(document.userRoles['personMapInstance.user.id'],'Please Select a User',document.userRoles['userRoleId'],'You did not check a box. Please check a box.')) return false"/>
          </td></tr></table>  
            </td>
            <td width="45%">
  			
  			<div class="list">
					<table>
						
							<tr>
								<th width="68%"><g:message code="default.AssignRoleToUser.label"/></th>
								<th width="11%"><g:checkBox name="AllDel" onclick="checkAllBox(document.userRoles.AllDel,document.userRoles.userRoleId)" value="${AllDel}" checked="false"/> <g:message code="default.evalItem.all.label"/></th>
							</tr>
						</table>
						<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
						<table style="width: 100%;" cellpadding="0" cellspacing="0">
							<g:each in="${userRoleInstanceList}" status="i" var="userRoleInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
							<td width="65%">
								${userRoleInstance.authority}
							</td>
							<td width="11%"><g:checkBox name="userRoleId" value="${userRoleInstance.id}" checked="false"/></td>
						</tr>
						</g:each>
						
					</table>
</div>