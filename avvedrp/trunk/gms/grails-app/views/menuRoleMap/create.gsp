<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'menuRoleMap.label', default: 'MenuRoleMap')}" />
        <title><g:message code="default.addMenu.label" args="[entityName]" /></title>
    </head>
    <body>
    	<div class="wrapper">
	        <div class="body">
	            <h1><g:message code="default.addMenu.label"/></h1>
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${menuRoleMapInstance}">
		            <div class="errors">
		                <g:renderErrors bean="${menuRoleMapInstance}" as="list" />
		            </div>
	            </g:hasErrors>
	            <g:form action="save" method="post" name="menuSelection">
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        
	                            <tr class="prop">
	                                
	                                <td valign="top" class="name">
	                                    <label for="role"><g:message code="menuRoleMap.role.label" default="Role"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: menuRoleMapInstance, field: 'role', 'errors')}">
	                                    <g:select name="role.id" from="${authorityList}" optionKey="id" optionValue="authority" value="${menuRoleMapInstance?.role?.id}" 
	                                    onchange="${remoteFunction(controller:'menuRoleMap',action:'listMenu',update:'actionMenuSel',params:'\'roleId=\'+this.value')};"
	                                    noSelection="['null':'-Select-']"/>
	                                </td>
	                            </tr>
	                        </tbody>
	                    </table>
						<div id="actionMenuSel">
					    	<table width="46%" height="195" border="0">
					  			<tr>
					    			<td width="35%">
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
																	
																</td>
																<td width="50%">
																<strong>
																	<g:message code="${menuInstance.menuName}"/> (<g:message code="default.mainMenu.label"/>)
																	</strong>
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
					      							<g:submitToRemote class="addbutton" action="save" value="	    	     " update="[success:'actionMenuSel',failure:'error']" before="if(!validateMenuRoleMap(document.menuSelection['role.id'],document.menuSelection['menuSel'],'You did not check a box. Please check a box.')) return false" />
					                				<g:submitToRemote class="removebutton" action="delete" value="           " update="[success:'actionMenuSel',failure:'error']" before="if(!validateMenuRoleMap(document.menuSelection['role.id'],document.menuSelection['menuSelt'],'You did not check a box. Please check a box.')) return false"/>
					               				</td></tr></table>
					                		</td>
					                	</td><td width="35%">
						      				<div class="list">
						    					<table>
													<tr>
														<th width="5%"><g:checkBox name="AllDel" onclick="checkAllBox(document.menuSelection.AllDel,document.menuSelection['menuSelt'])" value="${AllDel}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
														<th width="50%"><g:message code="default.assignMenu.label"/></th>
														
													</tr>
												</table>
												<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
						  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
													    <g:each in="${roleMenuMapInstanceList}" status="i" var="menuInstance">
															<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
																<td width="7%">
																	<g:checkBox name="menuSelt" value="${menuInstance}" checked="false"/></td>
																
															</tr>
														</g:each>
													</table>
												</div>
											</div>	
								 		</td>
					  		 		</tr>
								</table>
							</div>
					    </div>
				</g:form>
	        </div>
        </div>
    </body>
</html>
