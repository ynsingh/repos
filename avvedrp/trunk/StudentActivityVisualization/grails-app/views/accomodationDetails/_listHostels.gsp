<table frame="box">
			<thead>
				<tr>
					<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; "/>&nbsp
					
					<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="hostelType" title="${message(code: 'Hostel Type')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="name" title="${message(code: 'Name of Hostel')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="intakeCapacity" title="${message(code: 'Intake Capacity')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="numberOfStudents" title="${message(code: 'Number of Students residing')}" style="font-size:12px;"/>
					
					
					<th><g:message code="Edit" style="font-size:12px;"/></th>
				   
				</tr>
			</thead>
		    
			<tbody>
			
                    <g:each in="${studentHostelsinstanceList}" status="i" var="studentHostelsinstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td class="listing">${i+1}</td>
                            
                            <td class="listing">${fieldValue(bean:studentHostelsinstance, field:'institutionDetails.institutionName')}</td>
                            
                            <td class="listing">${fieldValue(bean:studentHostelsinstance, field:'hostelType')}</td>
                            
                            <td class="listing">${fieldValue(bean:studentHostelsinstance, field:'name')}</td>
                            
                            <td class="listing">${fieldValue(bean:studentHostelsinstance, field:'intakeCapacity')}</td>
                            
                            <td class="listing">${fieldValue(bean:studentHostelsinstance, field:'numberOfStudents')}</td>
                         
                             <td class="listing"><g:link action="editStudentsHostel" id="${studentHostelsinstance.id}"><g:message code="Edit"/></g:link></td>
                        	
                        </tr>
                    </g:each>
                    	
                    </tbody>
   </table>
