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
			<g:if test="${(session.Role == 'ROLE_STAFF')}">
				<a class="menuitem submenuheader" href="#" ><g:message code="default.PreProposal.label"/></a>
				<div class="submenu">
					<ul>
					<li><a href="${createLinkTo(dir:'preProposal/create')}" target="right"><g:message code="default.AddNewProposal.label"/></a></li>
					<li><a href="${createLinkTo(dir:'preProposal/list')}" target="right"><g:message code="default.PreProposalList.label"/></a></li>
					</ul>
				</div>
				<a class="menuitem submenuheader" href="#" ><g:message code="default.FullProposal.label"/></a>
				<div class="submenu">
					<ul>
					<li><a href="${createLinkTo(dir:'fullProposal/list')}" target="right"><g:message code="default.FullProposalList.label"/></a></li>
					</ul>
				</div>
			</g:if>	
			
			<g:if test="${(session.Role == 'ROLE_REVIEWER')}">
				<a class="menuitem submenuheader" href="#" ><g:message code="default.PreProposal.label"/></a>
				<div class="submenu">
					<ul>
					<li><a href="${createLinkTo(dir:'proposalApproval/list')}" target="right"><g:message code="default.PreProposalApproval.label"/></a></li>
					<li><a href="${createLinkTo(dir:'proposalApproval/reviewerStatus')}" target="right"><g:message code="default.ViewReview.label"/></a></li>
					</ul>
				</div>
				<a class="menuitem submenuheader" href="#" ><g:message code="default.FullProposal.label"/></a>
				<div class="submenu">
					<ul>
					<li><a href="${createLinkTo(dir:'proposalApproval/fullProposalList')}" target="right"><g:message code="default.FullProposalApproval.label"/></a></li>
					<li><a href="${createLinkTo(dir:'proposalApproval/fullProposalReview')}" target="right"><g:message code="default.ViewReview.label"/></a></li>
					</ul>
				</div>
				<a class="menuitem submenuheader" href="#" ><g:message code="default.ProposalList.label"/></a>
				<div class="submenu">
					<ul>
					<li><a href="${createLinkTo(dir:'proposal/proposalApplicationList')}" target="right"><g:message code="default.ProposalList.label"/></a></li>
					</ul>
				</div>
			</g:if>	
			<g:if test="${(session.Role != 'ROLE_USER') && (session.Role != 'ROLE_STAFF')&& (session.Role != 'ROLE_REVIEWER')&& (session.Role != 'ROLE_FINANCE')&& (session.Role != 'ROLE_PI')}">
				<a class="menuitem submenuheader" href="#" ><g:message code="default.Projects.label"/></a>
				<div class="submenu">
					<ul>
						<g:if test="${session.Role == 'ROLE_SITEADMIN'}">
							<li><a href="${createLinkTo(dir:'projects/create')}" target="right"><g:message code="default.AddProjects.label"/></a></li>
							<li><a href="${createLinkTo(dir:'projects/list')}" target="right"><g:message code="default.ProjectList.label"/></a></li>
							<li><a href="${createLinkTo(dir:'projects/search')}" target="right"><g:message code="default.SearchProjects.label"/></a></li>
							<!-- <li><a href="${createLinkTo(dir:'projects/inactiveProjectsList')}" target="right">Inactive Projects</a></li> -->
						</g:if>	
						<g:if test="${(session.Role == 'ROLE_PROJECTADMIN') || (session.Role == 'ROLE_SITEADMIN') || (session.Role == 'ROLE_PROPOSALADMIN')}">		  
							<li><a href="${createLinkTo(dir:'notification/list')}" target="right"><g:message code="default.CallForProposal.label"/></a></li>
							<li><a href="${createLinkTo(dir:'notificationsEmails/partyNotificationsList')}" target="right"><g:message code="default.ProposalManagement.label"/></a></li>
						</g:if>	
					</ul>
				</div>
			</g:if>
			<g:if test="${(session.Role != 'ROLE_SITEADMIN') && (session.Role != 'ROLE_STAFF')&& (session.Role != 'ROLE_REVIEWER')&& (session.Role != 'ROLE_FINANCE')&& (session.Role != 'ROLE_PROPOSALADMIN')}">
				<a class="menuitem submenuheader" href="#" ><g:message code="default.Award.label"/></a>
				<div class="submenu">
					<ul>
						<li><a href="${createLinkTo(dir:'projects/list')}" target="right"><g:message code="default.ProjectList.label"/></a></li>
						<li><a href="${createLinkTo(dir:'projects/search')}" target="right"><g:message code="default.SearchProjects.label"/></a></li>
					</ul>
				</div>
			</g:if>	
			<g:if test="${(session.Role == 'ROLE_PROJECTADMIN') || (session.Role == 'ROLE_SITEADMIN')}">
				<a class="menuitem submenuheader" href="#"><g:message code="default.Institution.label"/></a>
				<div class="submenu">
					<ul>
						<li><a href="${createLinkTo(dir:'partyDepartment/create')}" target="right"><g:message code="default.AddDepartment.label"/></a></li>				 
				        <li><a href="${createLinkTo(dir:'party/list')}" target="right"><g:message code="default.InstitutionList.label"/></a></li>         
					</ul>
				</div>
			</g:if>
			<g:if test="${session.Role == 'ROLE_SITEADMIN'}">
				<a class="menuitem submenuheader" href="#"><g:message code="default.Users.label"/></a>
				<div class="submenu">
					<ul>
						<li><a href="${createLinkTo(dir:'user/create')}" target="right"><g:message code="default.AddUser.label"/></a></li>				
						<li><a href="${createLinkTo(dir:'user/list')}" target="right"><g:message code="default.UsersList.label"/></a></li>
						<li><a href="${createLinkTo(dir:'user/accessControlEntry')}" target="right"><g:message code="default.ProjectPermission.label"/></a></li>
						<li><a href="${createLinkTo(dir:'rolePrivileges/create')}" target="right"><g:message code="default.AccessPermission.label"/></a></li>
						<li><a href="${createLinkTo(dir:'rolePrivileges/newRolePrivileges')}" target="right"><g:message code="default.RolePrivileges.label"/></a></li>
					</ul>
				</div>
			</g:if>
			<g:if test="${session.Role == 'ROLE_SITEADMIN' || (session.Role == 'ROLE_PROJECTADMIN')}">
				<a class="menuitem submenuheader" href="#"><g:message code="default.Masters.label"/></a>
				<div class="submenu">
					<ul>
						<li><a href="${createLinkTo(dir:'partyGrantAgency/create')}" target="right"><g:message code="default.GrantAgency.label"/></a></li>
						<li><a href="${createLinkTo(dir:'accountHeads/create')}" target="right"><g:message code="default.AccountHead.label"/></a></li>
						<li><a href="${createLinkTo(dir:'grantPeriod/create')}" target="right"><g:message code="default.GrantPeriod.label"/></a></li>
						<li><a href="${createLinkTo(dir:'projectType/create')}" target="right"><g:message code="default.ProjectType.label"/></a></li>
						<li><a href="${createLinkTo(dir:'attachmentType/create')}" target="right"><g:message code="default.AttachmentType.label"/></a></li>
						<li><a href="${createLinkTo(dir:'investigator/create')}" target="right"><g:message code="default.PI.label"/></a></li>
						<li><a href="${createLinkTo(dir:'authority/create')}" target="right"><g:message code="default.AddRole.label"/></a></li>
						<li><a href="${createLinkTo(dir:'employeeDesignation/create')}" target="right"><g:message code="default.EmployeeDesignation.label"/></a></li>
						<li><a href="${createLinkTo(dir:'salaryComponent/create')}" target="right"><g:message code="default.SalaryComponent.label"/></a></li>
						<!--<li><a href="${createLinkTo(dir:'eligibilityCriteria/create')}" target="right"><g:message code="default.eligibilityCriteria.eligibilityCriteria.label"/></a></li> -->
						<li><a href="${createLinkTo(dir:'proposalCategory/create')}" target="right"><g:message code="default.ProposalCategory.label"/></a></li>
						<li><a href="${createLinkTo(dir:'preProposal/uploadProposalForm')}" target="right"><g:message code="default.UploadProposalForm.label"/></a></li>
					</ul>
				</div>
			</g:if>
			<g:if test="${(session.Role == 'ROLE_PROPOSALADMIN')}">
			
			<a class="menuitem submenuheader" href="#" ><g:message code="default.ProposalEvaluationPre-Settings.label"/></a>
					<div class="submenu">
						<ul>
							<li><a href="${createLinkTo(dir:'evalScale/create')}" target="right"><g:message code="default.EvalScale.label"/></a></li>
							<li><a href="${createLinkTo(dir:'evalScaleOptions/create')}" target="right"><g:message code="default.EvalScaleOptions.label"/></a></li>
							<li><a href="${createLinkTo(dir:'evalItem/create')}" target="right"><g:message code="default.EvalItem.label"/></a></li>
							
						</ul>
					</div>
			</g:if>
			<g:if test="${(session.Role == 'ROLE_FINANCE')}">
				<div><a class="menuitem" href="${createLinkTo(dir:'expenseRequestEntry/financeLogin')}" target="right"><g:message code="default.ExpenseRequestList.label"/></a></div>
			</g:if>
			<g:if test="${(session.Role == 'ROLE_FINANCE')||(session.Role == 'ROLE_SITEADMIN') || (session.Role == 'ROLE_PROPOSALADMIN')}">
			<a class="menuitem submenuheader" href="#" ><g:message code="default.ApprovalAuthority.label"/></a>
				<div class="submenu">
					<ul>
						<li><a href="${createLinkTo(dir:'approvalAuthority/create')}" target="right"><g:message code="default.AddApprovalAuthority.label"/></a></li>
					</ul>
				</div>
			</g:if>
			<g:if test="${session.Role == 'ROLE_SITEADMIN'}">
				<a class="menuitem submenuheader" href="#" ><g:message code="default.AssignProposal.label"/></a>
					<div class="submenu">
						<ul>
							<li><a href="${createLinkTo(dir:'proposalApprovalAuthorityMap/create')}" target="right"><g:message code="default.AssignProposaltoApprovalAuthority.label"/></a></li>
							
						</ul>
					</div>
			    </g:if>
			
			<g:if test="${(session.Role == 'ROLE_PI')}">
				<div><a class="menuitem" href="${createLinkTo(dir:'expenseRequestEntry/create')}" target="right"><g:message code="default.ExpenseRequestEntry.label"/></a></div>
			</g:if>
			
			

				<g:if test="${(session.Role != 'ROLE_STAFF') && (session.Role != 'ROLE_REVIEWER')}">
					<a class="menuitem submenuheader" href="#"><g:message code="default.Reports.label"/></a>
					<div class="submenu">
						<ul>
						    <li><a href="${createLinkTo(dir:'grantAllocation/reports')}" target="right"><g:message code="default.ProjectReports.label"/></a></li>
							<li><a href="${createLinkTo(dir:'grantAllocationTracking/grantAllocationTrackingReports')}" target="right"><g:message code="default.ProjectStatusReports.label"/></a></li>
							<li><a href="${createLinkTo(dir:'utilization/list')}" target="right"><g:message code="default.UtilizationCertificates.label"/></a></li>      	
							<li><a href="${createLinkTo(dir:'grantAllocation/grantReports')}" target="right"><g:message code="default.StatisticalReports.label"/></a></li>
							<li><a href="${createLinkTo(dir:'notification/granteeReports')}" target="right"><g:message code="default.GrantAgencyReports.label"/></a></li>
							<li><a href="${createLinkTo(dir:'grantAllocation/auditLoggingReport')}" target="right"><g:message code="default.AuditLoggingReport.label"/></a></li>
										
						</ul>
					</div>
				</g:if>
				<div><a class="menuitem" href="${createLinkTo(dir:'expenseRequestEntry/expenseApprovalRequest')}" target="right"><g:message code="default.ExpenseApprovalRequestList.label"/></a></div>
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
