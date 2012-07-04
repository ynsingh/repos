<table frame="box">
			<thead>
				<tr>
					<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; "/>&nbsp
					
					<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="faculty.id" title="${message(code: 'Faculty')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="department.id" title="${message(code: 'Department')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="mode" title="${message(code: 'Mode')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="level" title="${message(code: 'Level')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="programmeName" title="${message(code: 'Programme Name')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="programmeCode" title="${message(code: 'Programme Code')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="disciplineName" title="${message(code: 'Discipline Name')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="disciplineCode" title="${message(code: 'Discipline Code')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="broadDisciplineName" title="${message(code: 'Broad Discipline Name')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="broadDIsciplineCode" title="${message(code: 'Broad Discipline Code')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="intakeCapacity" title="${message(code: 'Intake Capacity')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="numberOfApplicants" title="${message(code: 'Number of Applicants')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="programmeDuration" title="${message(code: 'Duration')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="programmeType" title="${message(code: 'Type')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="examinationSystem" title="${message(code: 'Examination System')}" style="font-size:12px;"/>
					
					<g:sortableColumn property="university" title="${message(code: 'University')}" style="font-size:12px;"/>
					
					
					<th><g:message code="Edit" style="font-size:12px;"/></th>
				   
				</tr>
			</thead>
		    
			<tbody>
			
                    <g:each in="${programmeDetailsInstanceList}" status="i" var="programmeDetailsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td class="listing">${i+1}</td>
                            
                            <td class="listing">${fieldValue(bean:institutionDetailsInstance, field:'institutionName')}</td>
                            
                            <td class="listing">${fieldValue(bean:facultyInstance, field:'facultyName')}</td>
                            
                            <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'department.deptName')}</td>
                            
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'mode')}</td>
                             
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'level')}</td>
                             
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'programmeName')}</td>
                             
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'programmeCode')}</td>
                             
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'disciplineName')}</td>
                             
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'disciplineCode')}</td>
                             
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'broadDisciplineName')}</td>
                             
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'broadDIsciplineCode')}</td>
                             
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'intakeCapacity')}</td>
                             
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'numberOfApplicants')}</td>
                             
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'programmeDuration')}</td>
                             
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'programmeType')}</td>
                             
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'examinationSystem')}</td>
                             
                             <td class="listing">${fieldValue(bean:programmeDetailsInstance, field:'university')}</td>
                             
                             <td class="listing"><g:link action="editProgramme" id="${programmeDetailsInstance.id}"><g:message code="Edit"/></g:link></td>
                        	
                        </tr>
                    </g:each>
                    	
                    </tbody>
			</table>
