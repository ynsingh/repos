  

	<table width="46%" height="195" border="0">
  		<tr>
    		<td width="42%">
    		
    		<div class="list">
						<table>
							<tr>
									<th width="65%"><g:message code="default.ActionName.label"/></th>
									<th><g:checkBox name="All" id="All" onclick="checkAllActions()" value="${All}" checked="false"/><g:message code="default.SelectAll.label" /></th>
								</tr>
							</table>
							<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
							<g:each in="${actionNameList}" status="i" var="actionNameList">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td width="65%">
								${actionNameList}
								</td>
								<td><g:checkBox name="actionName" id="actionName" value="${actionNameList}" checked="false"/></td>
								</tr>
							</g:each>
							
						</table>
					</div></div>
      		</td>
      		<td width="13%">
      		<table>
      		<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>
      			<g:submitToRemote class="addbutton" action="save" value="	    	     " update="[success:'actionNameSel',failure:'error']" before="if(!validateActions()) return false"/>
                <g:submitToRemote class="removebutton" action="delete" value="           " update="[success:'actionNameSel',failure:'error']" before="if(!validateActionForDelete()) return false"/>
                 </td></tr></table>
                </td>
                <td width="45%">
      			<div class="list">
    					<table>
							
								<tr>
									<th width="65%"><g:message code="default.ActionName.label"/></th>
									<th width="40%"><g:checkBox name="AllDel" onclick="checkAllActionDelete()" value="${AllDel}" checked="false"/><g:message code="default.SelectAll.label" /></th>
								</tr>
							</table>
							<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
							
						
								<g:each in="${rolePrivilegesInstance}" status="i" var="rolePrivilegesInstance">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								
								<td width="65%">
									${rolePrivilegesInstance.actionName}
								</td>
								<td width="40%"><g:checkBox name="deleteAction" id="deleteAction" value="${rolePrivilegesInstance.actionName}" checked="false"/></td>
							</tr>
							</g:each>
							
						</table>
					</div></div>	
			</td>
  		</tr>
	</table>