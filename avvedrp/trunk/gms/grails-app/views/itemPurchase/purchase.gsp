<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Currentprojects.head"/></title>
    </head>
    <body>
    	<div class="wrapper"> 
       		<div class="body">
	        	<h1><g:message code="default.Currentprojects.head"/></h1>        
            	<g:if test="${flash.error}">
	            	<div class="errors">${flash.error}</div>
	            </g:if>
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:if test="${grandAllocationList}">
	            	<div class="list">
	                	<table cellspacing="0" cellpadding="0">
	                    	<thead>
	                        	<tr>
                        
                   	        		<g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
                   	              
                       				<%-- <g:sortableColumn property="parent" title="Main Project" /> --%>
                                 
                                 	 <g:sortableColumn property="projects.name" title="${message(code: 'default.ProjectName.label')}"/>
                                              
                   	        		<g:sortableColumn property="projects.code" title="${message(code: 'default.ProjectCode.label')}"/>
                   	        
                   	            	<th><g:message code="default.Action.label"/></th>
                   	     		</tr>
                    		</thead>
                    	<tbody>
		                    <% int j=0 %>
		                    <g:each in="${grandAllocationList}" status="i" var="grantAllocationInstance">
		                        <g:if test="${fieldValue(bean:grantAllocationInstance, field:'projects.activeYesNo') == 'Y'}">
			                        <%  j++ %>
			                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			                        
			                           <td>${j}</td>
		                                
	                                 	<%-- <td>${fieldValue(bean:grantAllocationInstance, field:'projects.parent.code')}</td>--%>
	                               
	                        			<td>${fieldValue(bean:grantAllocationInstance, field:'projects.name')}</td>
	                           
	                         			<td>${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</td>
	                           
	                        			<td><g:link action="create" id="${grantAllocationInstance.projects.id}">
	                        					<g:message code="default.Purchase.label" />
                        					</g:link>
                    					</td>
	                        		</tr>
	                       		</g:if>
                    		</g:each>
                    	</tbody>
                	</table>
            	</div>
            </g:if>
            <g:else>
            	<br><g:message code="default.NoRecordsAvailable.label"/></br>
            </g:else>
          </div>  
        </div>
   	</body>
</html>
