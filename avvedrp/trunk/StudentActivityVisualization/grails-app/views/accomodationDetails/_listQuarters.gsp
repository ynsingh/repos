
                    <table frame="box">
			<thead>
				<tr>
					<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; "/>&nbsp
					<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
					<g:sortableColumn property="categoryType" title="${message(code: 'Category')}" style="font-size:12px;"/>
					<g:sortableColumn property="numberOfQuarters" title="${message(code: 'Number Of Quarters')}" style="font-size:12px;"/>
					
					
					<th><g:message code="Edit" style="font-size:12px;"/></th>
				   
				</tr>
			</thead>
		    
			<tbody>
                    <g:each in="${staffQuartersInstanceList}" status="i" var="staffQuartersInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td class="listing">${i+1}</td>
                        
                            <td class="listing">${fieldValue(bean:staffQuartersInstance, field:'institutionDetails.institutionName')}</td>
                            
                            <td class="listing">${fieldValue(bean:staffQuartersInstance, field:'categoryType')}</td>
                            
                            <td class="listing">${fieldValue(bean:staffQuartersInstance, field:'numberOfQuarters')}</td>
                        
                            <td class="listing"><g:link action="editStaffQuarters" id="${staffQuartersInstance.id}"><g:message code="Edit"/></g:link></td>
                        	
                        </tr>
                    </g:each>
                    </table>
