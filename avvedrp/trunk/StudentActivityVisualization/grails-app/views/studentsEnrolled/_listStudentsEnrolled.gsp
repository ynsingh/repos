<table frame="box">
			<thead>
				<tr>
					<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; "/>&nbsp
												
												<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="faculty.id" title="${message(code: 'Faculty')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="department.id" title="${message(code: 'Department')}" style="font-size:12px;"/>
													
												<g:sortableColumn property="programmeDetails.id" title="${message(code: 'Programme')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="year" title="${message(code: 'Year')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="category" title="${message(code: 'Category')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="totalStudents" title="${message(code: 'Strength')}" style="font-size:12px; text-align: center;" colspan="2"/>
												
												<g:sortableColumn property="totalStudents" title="${message(code: 'PWD Strength')}" style="font-size:12px; text-align: center;" colspan="2"/>
												
												<g:sortableColumn property="totalStudents" title="${message(code: 'Muslims Strength')}" style="font-size:12px; text-align: center;" colspan="2"/>
												
												<g:sortableColumn property="totalStudents" title="${message(code: 'Other MInorities Strength')}" style="font-size:12px; text-align: center;" colspan="2"/>
												
												<th><g:message code="Edit" style="font-size:12px;"/></th>
					</tr>
												   
					<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>total</td>
												<td>female</td>
												<td>total</td>
												<td>female</td>
												<td>total</td>
												<td>female</td>
												<td>total</td>
												<td>female</td>
												<td>&nbsp;</td>
					</tr>
										      
			</thead>
		    
			<tbody>
			
                    <g:each in="${studentsEnrolledInstanceList}" status="i" var="studentsEnrolledInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td class="listing">${i+1}</td>
                            
                            <td class="listing">${fieldValue(bean:institutionDetailsInstance, field:'institutionName')}</td>
                            
                            <td class="listing">${fieldValue(bean:facultyInstance, field:'facultyName')}</td>
                            
                            <td class="listing">${fieldValue(bean:departmentInstance, field:'deptName')}</td>
                            
                             <td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'programmeDetails.programmeName')}</td>
                             
                             <td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'year')}</td>
                             
                             <td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'category')}</td>
                             
                             <td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'totalStudents')}</td>
                             
                             <td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'femaleStudents')}</td>
                             
                             <td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'totalPwdStudents')}</td>
                             
                             <td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'femalePwdStudents')}</td>
                             
                             <td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'totalMuslimStudents')}</td>
                             
                             <td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'femaleMuslimStudents')}</td>
                             
                             <td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'totalOthMinStudents')}</td>
                             
                             <td class="listing">${fieldValue(bean:studentsEnrolledInstance, field:'femaleOthMinStudents')}</td>
                             
                             <td class="listing"><g:link action="editStudentInfo" id="${studentsEnrolledInstance.id}"><g:message code="Edit"/></g:link></td>
                        	
                        </tr>
                    </g:each>
                    	
                    </tbody>
			</table>