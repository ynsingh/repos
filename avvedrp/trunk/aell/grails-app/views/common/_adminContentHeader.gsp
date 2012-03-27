<g:if test="${controllerName == 'users'||controllerName == 'role'}">
    <g:if test="${actionName == 'changePassword'}">
          <div  style="margin: 8px;	padding: 0px 50px 0px 25px;letter-spacing: -1px;margin-left:0px;">${grailsApplication.config.account}</div>
    </g:if>
    <g:else>
         <div  style="margin: 8px;	padding: 0px 50px 0px 25px;letter-spacing: -1px;margin-left:0px;">${grailsApplication.config.admin_dashboard_portal}</div>
    </g:else>
</g:if>
<g:else>
          <div  style="margin: 8px;	padding: 0px 50px 0px 25px;letter-spacing: -1px;margin-left:0px;">${grailsApplication.config.admindashboard_title}</div>
</g:else>

