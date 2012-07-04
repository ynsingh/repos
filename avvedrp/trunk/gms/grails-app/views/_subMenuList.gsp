<html>
	<head>
		<link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
		<link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'hro.css')}" type="text/css" media="screen" /> 
		<script src="${createLinkTo(dir:'images',file:'jquery-1.3.2.js')}"></script>
	</head>
	<body >
	   <div class="hrmenu-container" >	
	   		<ul id="topnav"> 

	   		<g:each in="${menuRoleMapParentList}" var="menuRoleMapParentInstance">
		   		<li>
			   		<g:if test="${menuRoleMapParentInstance.menu.menuPath!=null}">
			   			<a href="${createLinkTo(dir:menuRoleMapParentInstance.menu.menuPath)}?id=${session.ProjectID}"><g:message code="${menuRoleMapParentInstance.menu.menuName}"/></a>
		    		</g:if>
		    		<g:else>
		   				<a href="#" ><g:message code="${menuRoleMapParentInstance.menu.menuName}"/></a>
		   			</g:else>
					<%def childList=MenuRoleMap.findAll("from MenuRoleMap MRM where MRM.role.id in "+roleIds+" and MRM.menu.parentId="+menuRoleMapParentInstance.menu.id+" and MRM.activeYesNo='Y'group by MRM.menu.id order by MRM.menu.menuOrder asc")%>
					<span> 
						<g:each in="${childList}" var="menuRoleMapChildInstance">
							<g:if test="${menuRoleMapChildInstance.menu.menuName == 'default.FundAllocation.label'}">
								<g:if test="${projectsInstance.parent}"></g:if>
								<g:else> 
									<a href="${createLinkTo(dir:menuRoleMapChildInstance.menu.menuPath)}?id=${session.ProjectID}"><g:message code="${menuRoleMapChildInstance.menu.menuName}"/></a>
								</g:else>
							</g:if>
							<g:else> 
							
								<g:if test="${menuRoleMapChildInstance.menu.menuPath == 'grantAllocationSplit/list'}">
									<g:if test="${projectsInstance.parent}">
										<a href="${createLinkTo(dir:menuRoleMapChildInstance.menu.menuPath)}?id=${session.ProjectID}"><g:message code="${menuRoleMapChildInstance.menu.menuName}"/></a> |
									</g:if>
									<g:else> 
										<a href="${createLinkTo(dir:menuRoleMapChildInstance.menu.menuPath)}?id=${session.ProjectID}"><g:message code="default.HeadwiseAllocation.label"/></a> |
									</g:else>
								</g:if>
								<g:else>
										<a href="${createLinkTo(dir:menuRoleMapChildInstance.menu.menuPath)}?id=${session.ProjectID}"><g:message code="${menuRoleMapChildInstance.menu.menuName}"/></a> |
								</g:else>
							</g:else>
						</g:each>
					</span> 
				 </li>
			  </g:each> 
	   		
		       
    		</ul> 
		</div> 
    </body>	
</html>
