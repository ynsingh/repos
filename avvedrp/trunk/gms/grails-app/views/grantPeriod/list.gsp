

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>GrantPeriod List</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
           <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Grant Period</g:link></span>
        </div>
        <div class="body">
            <h1>GrantPeriod List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${grantPeriodInstanceList}">
            <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="SlNo" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="startDate" title="Start Date" />
                   	         
                   	        <g:sortableColumn property="endDate" title="End Date" />
                        
                   	        <g:sortableColumn property="activeYesNo" title="Active" />
                             <g:sortableColumn property="defaultYesNo" title="Default" />
                   	        <th>Edit</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantPeriodInstanceList}" status="i" var="grantPeriodInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                             <td>${(i + 1)}</td>
                         
                            <td>${fieldValue(bean:grantPeriodInstance, field:'name')}</td>
                            
                            <td><g:formatDate format="dd/MM/yyyy" date="${grantPeriodInstance.startDate}"/></td>
                            
                            <td><g:formatDate format="dd/MM/yyyy" date="${grantPeriodInstance.endDate}"/></td>
                        	
                            <td>
                	             <g:if test="${fieldValue(bean:grantPeriodInstance, field:'activeYesNo') == 'Y'}">
    							 ${'YES'}
    							 </g:if>
    							 <g:else>
    							 ${'NO'}
    							 </g:else>
                        	 </td>
                        <td>
	                        <g:if test="${fieldValue(bean:grantPeriodInstance, field:'defaultYesNo') == 'Y'}">
	    							 ${'YES'}
	    							 </g:if>
	    							 <g:else>
	    							 ${'NO'}
	    							 </g:else>
                       </td>
                       <td><g:link action="edit" id="${fieldValue(bean:grantPeriodInstance, field:'id')}">Edit</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            </g:if>
            <g:else>
            <br>No Records Available</br>
            </g:else>
            <div class="paginateButtons">
                <g:paginate total="${GrantPeriod.count()}" />
            </div>
        </div>
         </div>
    </body>
</html>
