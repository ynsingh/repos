<div id="menus_wrapper">
    <div id="sec_menu">
  
	  <ul>
	      <g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_ADMIN' || session.ROLE == 'ROLE_UNIVERSITY'}"  >
				<li><a  class="dashboard" href="${createLink(action:'admindashboard',controller:'dashboard')}"><g:message code="default.menuopt2.label"/></a></li>
		  </g:if>
				
		  <g:if test="${session.ROLE == 'ROLE_INSTITUTE'}"  >
				<li><a class="dashboard"  href="${createLink(action:'institutedashboard',controller:'dashboard')}"><g:message code="default.menuopt2.label"/></a></li>
		  </g:if>
				
		  <g:if test="${session.ROLE == 'ROLE_STAFF'}"  >
				<li><a  class="dashboard" href="${createLink(action:'staffdashboard',controller:'dashboard')}"><g:message code="default.menuopt2.label"/></a></li>
		  </g:if>
	  
	     <li><a class="form" href="${createLink(action:'basicInformationOfInstitution',controller:'institutionDetails')}">
				<g:message code="Basic Information"/></a></li>
				
		<li><a class="form" href="${createLink(action:'index',controller:'accomodationDetails')}">
				<g:message code="Accomodation Facilities"/></a></li>
				
		<li><a class="form" href="${createLink(action:'index',controller:'programmeDetails')}">
				<g:message code="Programmes"/></a></li>
				
		<li><a class="form" href="${createLink(action:'index',controller:'staffDetails')}">
				<g:message code="Staff Strength"/></a></li>
				
		<li><a class="form" href="${createLink(action:'index',controller:'studentsEnrolled')}">
				<g:message code="Students Enrolled"/></a></li>
				
		<li><a class="form" href="${createLink(action:'index',controller:'miscellaneous')}">
				<g:message code="Miscellaneous"/></a></li>
		<li><a href="${resource(dir:'/logout')}" class="logout"><g:message code="default.menuopt6.label"/></a></li>
		</ul>
	
 </div>
</div>