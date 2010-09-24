

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ProjectDepartmentMap List</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="body">
            <h1>ProjectDepartmentMap List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test='${projectDepartmentMapInstanceList}'>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                            <th>Projects</th>
                   	    	<th>Department</th>
                   	    	<th>Institution</th>
                   	    	<th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectDepartmentMapInstanceList}" status="i" var="projectDepartmentMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${(i + 1)}</td>
                      
                            <td>${fieldValue(bean:projectDepartmentMapInstance, field:'projects.code')}</td>
                                                    	<td>${fieldValue(bean:projectDepartmentMapInstance, field:'partyDepartment.departmentCode')}</td>
                        	<td>${fieldValue(bean:projectDepartmentMapInstance, field:'partyDepartment.party.code')}</td>
                               <td><g:link action="edit" id="${fieldValue(bean:projectDepartmentMapInstance, field:'id')}">Edit</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                
            </div>
            </g:if>
                <g:else>
                <br>
                No Records Available</br>
                </g:else>
            
        </div>
        </div>
    </body>
</html>
