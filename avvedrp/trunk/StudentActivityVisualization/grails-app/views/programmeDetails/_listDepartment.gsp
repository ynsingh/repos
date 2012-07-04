<table frame="box"> 
			<thead>
				<tr>
					<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; "/>&nbsp
					
					<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="faculty" title="${message(code: 'Faculty')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="deptName" title="${message(code: 'Name of Department')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="deptCode" title="${message(code: 'Department Code')}" style="font-size:12px;"/>
					
					
					<th><g:message code="Edit" style="font-size:12px;"/></th>
				   
				</tr>
			</thead>
		    
			<tbody>
			
                    <g:each in="${departmentInstanceList}" status="i" var="departmentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td class="listing">${i+1}</td>
                            
                            <td class="listing">${fieldValue(bean:institutionDetailsInstance, field:'institutionName')}</td>
                            
                            <td class="listing">${fieldValue(bean:departmentInstance, field:'faculty.facultyName')}</td>
                            
                            <td class="listing">${fieldValue(bean:departmentInstance, field:'deptName')}</td>
                            
                            <td class="listing">${fieldValue(bean:departmentInstance, field:'deptCode')}</td>
                         
                             <td class="listing"><g:link action="editDepartment" id="${departmentInstance.id}"><g:message code="Edit"/></g:link></td>
                        	
                        </tr>
                    </g:each>
                    	
                    </tbody>
			</table>
