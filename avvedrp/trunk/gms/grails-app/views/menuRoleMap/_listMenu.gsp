<table width="46%" height="195" border="0">
	<tr>
		<td width="35%">
			<g:if test="${flash.error}">
				<div class="errors">${flash.error}</div>
			</g:if>
			<div class="list">
				<table>							
					<tr>
						<th width="5%"><g:checkBox name="All" onclick="checkAllBox(document.menuSelection.All,document.menuSelection.menuSel)" value="${All}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
						<th width="50%"><g:message code="default.unAssignMenu.label"/></th>
						
					</tr>
				</table>
				<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
					<table style="width: 100%;" cellpadding="0" cellspacing="0">
						<g:each in="${menuInstanceList}" status="i" var="menuInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<g:if test="${menuInstance.parentId == -1}">
									<td width="7%">
										<g:if test="${menuInstance.menuPath!=null}">
											<g:checkBox name="menuSel" value="${menuInstance.id}" checked="false"/>
										</g:if>
									</td>
									<td width="50%">
									<strong><g:message code="${menuInstance.menuName}"/> (<g:message code="default.mainMenu.label"/>)</strong>
									</g:if>	
 									<g:else>
     								<td width="7%">
										<g:checkBox name="menuSel" value="${menuInstance.id}" checked="false"/>
									</td>
									<td width="50%">
     									<g:message code="${menuInstance.menuName}"/>
     								</g:else>
								</td>
							</tr>
						</g:each>
					</table>
				</div>
			</td>
			<td width="10%">
				<table>
					<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>
						<g:submitToRemote class="addbutton" action="save" value="	    	     " update="[success:'actionMenuSel',failure:'error']" before="if(!validateMenuRoleMap(document.menuSelection['role.id'],document.menuSelection.menuSel,'You did not check a box. Please check a box.')) return false" />
        				<g:submitToRemote class="removebutton" action="delete" value="           " update="[success:'actionMenuSel',failure:'error']" before="if(!validateMenuRoleMap(document.menuSelection['role.id'],document.menuSelection.menuSelt,'You did not check a box. Please check a box.')) return false"/>
       				</td></tr></table>
        		</td>
        	</td><td width="35%">
  				<div class="list">
					<table>
						<tr>
							<th width="5%"><g:checkBox name="AllDel" onclick="checkAllBox(document.menuSelection.AllDel,document.menuSelection.menuSelt)" value="${AllDel}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
							<th width="50%"><g:message code="default.assignMenu.label"/></th>
						</tr>
					</table>
					<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
						<table style="width: 100%;" cellpadding="0" cellspacing="0">
						<% int k=0 %>
						    <g:each in="${parentList}" status="i" var="menuRoleMapInstance">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
									<td width="7%">
										<g:if test="${menuRoleMapInstance.menu.menuPath!=null}">
											<g:checkBox name="menuSelt" value="${menuRoleMapInstance.id}" checked="false"/>
										</g:if>
									</td>
									<td width="50%">
										<strong><g:message code="${menuRoleMapInstance.menu.menuName}"/> (<g:message code="default.mainMenu.label"/>) </strong>
									</td>
									</tr>
									<g:each var="j" in="${ (0..<sizeList[i]) }">
										<g:if test="${sizeList[i]!=0}">
											<tr class="${(j % 2) == 0 ? 'odd' : 'even'}">
												<td width="7%">
												<g:checkBox name="menuSelt" value="${allChildList[k].id}" checked="false"/></td>
												<td width="50%">
													<g:message code="${allChildList[k].menu.menuName}"/>
												</td>
												<%  k++ %>
											</tr>
										 </g:if>
									 </g:each>
							  </g:each>
						</table>
					</div>
				</div>	
	 		</td>
 		</tr>
	</table>