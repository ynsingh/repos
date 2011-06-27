<div style="float: left; width: 180px; margin: 30px 6px; 0px 0px">
<div id="menu12">
<ul>
<li><div class="bt1"><span class="ht11">&nbsp;&nbsp;</span>
<span class="hw12">Menu</span></div></li>
<g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_UNIVERSITY'}"  >
							<li><a href="${createLink(action:'listInstitutes',controller:'courseActivity')}"><g:message code="default.menuopt1.label"/></a></li>
							</g:if>
							
							<g:if test="${session.ROLE == 'ROLE_STAFF'}"  >
							<li><a  href="${createLink(action:'listStaffCourses',controller:'courseActivity')}">Home</a></li>
							</g:if>
							
							<g:if test="${session.ROLE == 'ROLE_STUDENT'}"  >
							<li><a  href="${resource(dir:'')}">Home</a></li>
							</g:if>
							
							<g:if test="${session.ROLE == 'ROLE_INSTITUTE'}"  >
							<li><a href="${createLink(action:'edit',controller:'lmsSettings')}" class="settings">LMS Settings</a></li>
							</g:if>
							
							<g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_ADMIN' || session.ROLE == 'ROLE_UNIVERSITY'}"  >
							<li><a  href="${createLink(action:'admindashboard',controller:'dashboard')}">Dashboard</a></li>
							</g:if>
							
							<g:if test="${session.ROLE == 'ROLE_INSTITUTE'}"  >
							<li><a  href="${createLink(action:'institutedashboard',controller:'dashboard')}">Dashboard</a></li>
							</g:if>
							
							<g:if test="${session.ROLE == 'ROLE_STAFF'}"  >
							<li><a  href="${createLink(action:'staffdashboard',controller:'dashboard')}">Dashboard</a></li>
							</g:if>
							
							<g:if test="${session.ROLE == 'ROLE_SUPERADMIN'}"  >
							<li><a href="${createLink(action:'list',controller:'authority')}" class="manage_page">Roles / Privileges</a></li>
							<li><a href="${createLink(action:'list',controller:'person')}" class="useradd">Users</a></li>
							</g:if>
							
							<li><a href="${createLink(action:'help',controller:'siteHelp')}" class="help">Technical-Documentation</a></li>
							
							<li><a href="${resource(dir:'/logout')}" class="logout"><g:message code="default.menuopt6.label"/></a></li>
</ul>
</div>
</div>