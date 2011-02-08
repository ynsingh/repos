<div id="menus_wrapper">
    <div id="sec_menu">
          <ul>
          <g:if test="${session.ROLE == 'ROLE_ADMIN'}"  >
          <li><a class="house" href="${createLink(action:'listInstitutes',controller:'courseActivity')}">Home</a></li>
          </g:if>
          <g:if test="${session.ROLE == 'ROLE_STAFF'}"  >
          <li><a class="house" href="${createLink(action:'listSiteForLoginUser',controller:'courseActivity')}">Home</a></li>
          </g:if>
          <g:if test="${session.ROLE == 'ROLE_STUDENT'}"  >
          <li><a class="house" href="${resource(dir:'')}">Home</a></li>
              </g:if>

          <g:if test="${session.ROLE == 'ROLE_ADMIN'}"  >
          <li><a class="dashboard" href="${createLink(action:'admindashboard',controller:'dashboard')}">Dashboard</a></li>
          </g:if>
          <g:if test="${session.ROLE == 'ROLE_STAFF'}"  >
          <li><a class="dashboard" href="${createLink(action:'staffdashboard',controller:'dashboard')}">Dashboard</a></li>
          </g:if>
          <g:if test="${session.ROLE == 'ROLE_ADMIN'}"  >
          <li><a href="${createLink(action:'list',controller:'authority')}" class="manage_page">Roles / Privileges</a></li>
          <li><a href="${createLink(action:'list',controller:'person')}" class="useradd">Users</a></li>
          </g:if>

          <li><a href="${createLink(action:'help',controller:'siteHelp')}" class="help">Technical-Documentation</a></li>
          
          <li><a href="${resource(dir:'/logout')}" class="logout">Logout</a></li>
          </ul>
      <div  align="right"  style="padding:10px 10px 0px 0px; font-size:12px; font-family:Arial"><font color="#FFFFFF">Logged in as  <strong>${session.UserId}</strong></font></div>
    </div>
</div>

