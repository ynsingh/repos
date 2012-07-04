<html>
	<head>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'jquery.megamenu.css')}" type="text/css" media="screen" />
        <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'hro.css')}" type="text/css" media="screen" /> 
    </head>
 	<body>
		<div class="hrmenu-container" >	
			<ul id="topnav"> 
			
				<g:each in="${menuRoleMapParentList}" var="menuRoleMapParentInstance">
				<%def childList=MenuRoleMap.findAll("from MenuRoleMap MRM where MRM.role.id in "+roleIds+" and MRM.menu.parentId="+menuRoleMapParentInstance.menu.id+" and MRM.activeYesNo='Y'group by MRM.menu.id order by MRM.menu.menuOrder asc")%>
					<g:each in="${childList}" var="menuRoleMapChildInstance">
						<li><a href="${createLinkTo(dir:menuRoleMapChildInstance.menu.menuPath)}"><g:message code="${menuRoleMapChildInstance.menu.menuName}"/></a></li>
					</g:each>
				</g:each>
			</ul> 
		</div> 
	</body>	
</html>