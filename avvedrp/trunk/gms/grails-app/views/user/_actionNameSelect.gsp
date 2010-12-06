<g:if test="${actionNameList}">
<div class="list">
	<table width="46%" height="195" border="1">
  		<tr>
    		<td width="42%">
    		
						<table>
							<thead>
								<tr>
									<th><g:message code="default.ActionName.label"/></th>
									<th><g:checkBox name="All" onclick="checkAllActions()" value="${All}" checked="false"/><g:message code="default.SelectAll.label" /></th>
								</tr>
							</thead>
							<tbody>
							<g:each in="${actionNameList}" status="i" var="actionNameList">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td>
								${actionNameList}
								</td>
								<td><g:checkBox name="actionName" value="${actionNameList}" checked="false"/></td>
								</tr>
							</g:each>
							</tbody>
						</table>
					
      		</td>
      		<td width="13%">
      			<g:submitToRemote action="save" value="${message(code: 'default.Add.button')}" update="[success:'actionNameSel',failure:'error']" before="if(!validateActions()) return false"/>
                <g:submitToRemote action="delete" value="${message(code: 'default.Delete.button')}" update="[success:'actionNameSel',failure:'error']" before="if(!validateActionForDelete()) return false"/></td><td width="45%">
      			
    					<table>
							<thead>
								<tr>
									<th><g:message code="default.ActionName.label"/></th>
									<th><g:checkBox name="AllDel" onclick="checkAllActionDelete()" value="${AllDel}" checked="false"/><g:message code="default.SelectAll.label" /></th>
								</tr>
							</thead>
							<tbody>
								<g:each in="${rolePrivilegesInstance}" status="i" var="rolePrivilegesInstance">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td>
									${rolePrivilegesInstance.actionName}
								</td>
								<td><g:checkBox name="deleteAction" value="${rolePrivilegesInstance.actionName}" checked="false"/></td>
							</tr>
							</g:each>
							</tbody>
						</table>
						
			</td>
  		</tr>
	</table>
</div>
	</g:if>
	<g:else>
    <g:select 																	       
       name="actionName" 
       value="" 
	   disabled="true" noSelection="['null':'-Select-']"  >
	</g:select>
</g:else>