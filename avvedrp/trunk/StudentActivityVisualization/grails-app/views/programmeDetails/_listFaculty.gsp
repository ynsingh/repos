<table frame="box">
			<thead>
				<tr>
					<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; "/>&nbsp
					
					<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="facultyName" title="${message(code: 'Category')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="facultyCode" title="${message(code: 'Number Of Quarters')}" style="font-size:12px;"/>
					
					
					<th><g:message code="Edit" style="font-size:12px;"/></th>
				   
				</tr>
			</thead>
		    
			<tbody>
			
                    <g:each in="${facultyInstanceList}" status="i" var="facultyInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td class="listing">${i+1}</td>
                            
                            <td class="listing">${fieldValue(bean:facultyInstance, field:'institutionDetails.institutionName')}</td>
                            
                            <td class="listing">${fieldValue(bean:facultyInstance, field:'facultyName')}</td>
                            
                            <td class="listing">${fieldValue(bean:facultyInstance, field:'facultyCode')}</td>
                         
                             <td class="listing"><g:link action="editFaculty" id="${facultyInstance.id}"><g:message code="Edit"/></g:link></td>
                        	
                        </tr>
                    </g:each>
                    	
                    </tbody>
			</table>
