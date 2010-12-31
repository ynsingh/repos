

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>LmsUsage List</title>
    </head>
    
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New LmsUsage</g:link></span>
        </div>
        
        <div class="body">
            <h1>LmsUsage List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                   	         <th>Expenditure</th>
                      	         <th>Week </th>                      
                   	        <th>Expenditure</th>
                   	        
                        
                        </tr>
                    </thead>
                   <tbody>
		                       <g:each in="${lmsUsageInstanceList}" status="i" var="lmsUsageInstance">
		                           <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
		                           
		                     
		                               <td>${(i + 1)}</td>
		                           
		                               <td>${fieldValue(bean:lmsUsageInstance, field:'student_id ')}</td>
		                           
		                               <td>${fieldValue(bean:lmsUsageInstance, field:'week')}</td>
		                           
		                               <td>${fieldValue(bean:lmsUsageInstance, field:'session_minutes')}</td>
		                             	                                                      
		                           </tr>
		                       </g:each>
                    </tbody> 
          
                    <td>
                      <g:link action="show" id="${lmsUsageInstance.id}">${fieldValue(bean:lmsUsageInstance, field:'id')}</g:link></td>
                                           
                      </g:each>
                    </tbody>
                </table>
             </div>
               <div class="paginateButtons">
                <g:paginate total="${lmsUsageInstanceTotal}" />
               </div>
            </div>
    </body>
</html>
