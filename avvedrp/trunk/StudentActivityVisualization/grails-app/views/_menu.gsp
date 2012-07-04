<div id="menus_wrapper">
    <div id="sec_menu">
  
	  <ul>
				<!--
				<g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_UNIVERSITY'}"  >
				<li><a class="house" href="${createLink(action:'listInstitutes',controller:'courseActivity')}">
				<g:message code="default.menuopt1.label"/></a></li>
				</g:if>
				-->
				
				<g:if test="${session.ROLE == 'ROLE_STAFF'}"  >
				<li><a  class="house" href="${createLink(action:'listStaffCourses',controller:'courseActivity')}"><g:message code="default.menuopt1.label"/></a></li>
				</g:if>
				
				<g:if test="${session.ROLE == 'ROLE_STUDENT'}"  >
				<li><a class="house" href="${resource(dir:'')}"><g:message code="default.menuopt1.label"/></a></li>
				</g:if>
				
				
				<g:if test="${session.ROLE == 'ROLE_INSTITUTE'}"  >
				<li><a href="${createLink(action:'edit',controller:'lmsSettings')}" class="settings"><g:message code="default.menuopt7.label"/></a></li>
				</g:if>
				
				<g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_ADMIN' || session.ROLE == 'ROLE_UNIVERSITY'}"  >
				<li><a  class="dashboard" href="${createLink(action:'admindashboard',controller:'dashboard')}"><g:message code="default.menuopt2.label"/></a></li>
				</g:if>
				
				<g:if test="${session.ROLE == 'ROLE_INSTITUTE'}"  >
				<li><a class="dashboard"  href="${createLink(action:'institutedashboard',controller:'dashboard')}"><g:message code="default.menuopt2.label"/></a></li>
				</g:if>
				
				<g:if test="${session.ROLE == 'ROLE_STAFF'}"  >
				<li><a  class="dashboard" href="${createLink(action:'staffdashboard',controller:'dashboard')}"><g:message code="default.menuopt2.label"/></a></li>
				</g:if>
				
				<g:if test="${session.ROLE == 'ROLE_SUPERADMIN'}"  >
				<li><a href="${createLink(action:'list',controller:'authority')}" class="manage_page"><g:message code="default.menuopt3.label"/></a></li>
				<li><a href="${createLink(action:'list',controller:'person')}" class="useradd"><g:message code="default.menuopt4.label"/></a></li>
				</g:if>
				
				<g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_ADMIN' || session.ROLE == 'ROLE_INSTITUTE'}"  >
				<li><a class="datacapture"  href="${createLink(action:'mainMenu',controller:'institutionDetails')}"><g:message code="default.menuopt9.label"/></a></li>
				</g:if>
				
				<li><a href="../helpdoc/divehelp.html" class="help" target="_blank"><g:message code="default.menuopt8.label"/></a></li>
				
				<li><a href="${resource(dir:'/logout')}" class="logout"><g:message code="default.menuopt6.label"/></a></li>
				</ul>
				
				<g:isLoggedIn>
				<div  align="right"  style="padding:10px 10px 0px 0px; font-size:12px; font-family:Arial"><font color="#FFFFFF">Logged in as       <strong>${session.UserId}</strong></font></div>
				</g:isLoggedIn>
    </div>
</div>

