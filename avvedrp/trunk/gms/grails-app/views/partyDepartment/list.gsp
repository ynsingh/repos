

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Department List</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="body">
            <h1>Department List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <th>SlNo</th>
                        
                   	        <g:sortableColumn property="departmentCode" title="Department Code" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                   	        
                   	        <g:sortableColumn property="party" title="Party" />
                   	        
                   	        <g:sortableColumn property="activeYesNo" title="Active" />
                   	        
                   	        <th>Edit</th>
                       
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyDepartmentInstanceList}" status="i" var="partyDepartmentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td>${i+1}</td>
                        
                            <td>${fieldValue(bean:partyDepartmentInstance, field:'departmentCode')}</td>
                        
                            <td>${fieldValue(bean:partyDepartmentInstance, field:'name')}</td>
                            
                            <td>${fieldValue(bean:partyDepartmentInstance, field:'party.code')}</td>
                            
                            <td>${fieldValue(bean:partyDepartmentInstance, field:'activeYesNo')}</td>
                        
                        	<td><g:link action="edit" id="${partyDepartmentInstance.id}">Edit</g:link></td>
                        	
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
        </div>
    </body>
</html>
