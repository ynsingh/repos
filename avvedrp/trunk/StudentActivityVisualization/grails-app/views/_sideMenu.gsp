<div id="sidebar">
  		<ul>
                    <li><h3><a href="#" class="manage">Menu</a></h3>
          	            <ul>
                            <li><a class="house" href="${resource(dir:'')}">Home</a></li>
                            <g:if test="${session.ROLE == 'ROLE_ADMIN'}"  >
                            <li><a href="${createLink(action:'list',controller:'authority')}" class="manage_page">Roles / Privileges</a></li>
                            <li><a href="${createLink(action:'list',controller:'person')}" class="useradd">Users</a></li>
                            </g:if>
                            
                            <li><a href="${createLink(action:'help',controller:'siteHelp')}" class="help">Technical-Documentation</a></li>
                             <li><a href="${resource(dir:'/logout')}" class="logout">Logout</a></li>
                        </ul>
                    </li>
		</ul>
          </div>