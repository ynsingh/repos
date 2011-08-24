<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title><g:message code="default.GrantManagementSystem.title"/></title>
	 
	<script src="${createLinkTo(dir:'images',file:'jquery-1.3.2.min.js')}"></script>
	<script src="${createLinkTo(dir:'images',file:'ddaccordion.js')}"></script>
	<script src="${createLinkTo(dir:'images',file:'jquery.tools.min.js')}"></script>
<g:javascript library="application" />
	<link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'accordion.css')}" type="text/css" media="screen" />
	
	<script type="text/javascript">
	ddaccordion.init({
		headerclass: "submenuheader", //Shared CSS class name of headers group
		contentclass: "submenu", //Shared CSS class name of contents group
		revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
		mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
		collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
		defaultexpanded: [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
		onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
		animatedefault: false, //Should contents open by default be animated into view?
		persiststate: true, //persist state of opened contents within browser session?
		toggleclass: ["", ""], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
		togglehtml: ["suffix", "<img src='../images/accordion/plus.gif' class='statusicon' />", "<img src='../images/accordion/minus.gif' class='statusicon' />"], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
		animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
		oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
			//do nothing
		},
		onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
			//do nothing
		}
	})
	</script>
	</head>
	<body  class="glossymenubody">
		<div class="glossymenu">
			<g:each in="${menuRoleMapParentList}" var="menuRoleMapParentInstance">
				<a class="menuitem submenuheader" href="#" ><g:message code="${menuRoleMapParentInstance.menu.menuName}"/></a>
					<%def childList=MenuRoleMap.findAll("from MenuRoleMap MRM where MRM.role.id in "+roleIds+" and MRM.menu.parentId="+menuRoleMapParentInstance.menu.id+" and MRM.activeYesNo='Y'group by MRM.menu.id order by MRM.menu.menuOrder asc")%>
					<div class="submenu">
						<ul>
							<g:each in="${childList}" var="menuRoleMapChildInstance">
								<li><a href="${createLinkTo(dir:menuRoleMapChildInstance.menu.menuPath)}" target="right"><g:message code="${menuRoleMapChildInstance.menu.menuName}"/></a></li>
							</g:each>
						</ul>
					</div>
				</g:each>
				<div><a class="menuitem" href="${createLinkTo(dir:'user/changePassword')}" target="right"><g:message code="default.ChangePassword.label"/></a></div>	
				<div><a class="menuitem" href="${createLinkTo(dir:'logout')}" target="_parent"><g:message code="default.Logout.label"/></a></div>
				<div><a class="menuitem" href="javascript: void(0)" 
   				onclick="window.open('../SakshatAmritaMGMS/MGMSDoc-Home.html', 
  				'windowname1', 
  				'width=800, height=500,left=0,top=100,screenX=0,screenY=100,scrollbars = 1'); 
   				return false;"><g:message code="default.Documentation.menu.label" /></a></div>
			</div>
		</body>
</html>