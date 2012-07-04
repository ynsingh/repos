<table frame="box">
			<thead>
				<tr  style="font-size:12px; ">
					<g:sortableColumn property="id" title="${message(code: 'SINo')}"/>&nbsp
					
					<g:sortableColumn property="institutionDetails" title="${message(code: 'Institution')}" />
					
					<g:sortableColumn property="infrastructure" title="${message(code: 'Infrastructure')}"/>
					
					<g:sortableColumn property="description" title="${message(code: 'Description')}" />
					
					<th><g:message code="Edit" /></th>
				   
				</tr>
			</thead>
		    
			<tbody>
			        <g:each in="${infrastructureInstanceList}" status="i" var="infrastructureInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td class="listing">${i+1}</td>
                            
                            <td class="listing">${fieldValue(bean:infrastructureInstance, field:'institutionDetails.institutionName')}</td>
                            
                            <td class="listing">${fieldValue(bean:infrastructureInstance, field:'infrastructure')}</td>
                            
                            <td class="listing">${fieldValue(bean:infrastructureInstance, field:'description')}</td>
                            
                             <td class="listing"><g:link action="editInfrastructure" id="${infrastructureInstance.id}"><g:message code="Edit"/></g:link></td>
                        	
                        </tr>
                    </g:each>
                    	
                    </tbody>
			</table>