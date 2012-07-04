<table frame="box">
			<thead>
						<tr  style="font-size:12px; ">
							<g:sortableColumn property="id" title="${message(code: 'SINo')}"/>&nbsp
							
							<g:sortableColumn property="institutionDetails" title="${message(code: 'Institution')}" />
							
							<g:sortableColumn property="item" title="${message(code: 'Item')}"/>
							
							<g:sortableColumn property="amount" title="${message(code: 'Amount')}" />
							
							<th><g:message code="Edit" /></th>
						   
						</tr>
			</thead>
		    
			<tbody>
			        <g:each in="${financialInfoInstanceList}" status="i" var="financialInfoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td class="listing">${i+1}</td>
                            
                            <td class="listing">${fieldValue(bean:financialInfoInstance, field:'institutionDetails.institutionName')}</td>
                            
                            <td class="listing">${fieldValue(bean:financialInfoInstance, field:'item')}</td>
                            
                            <td class="listing">${fieldValue(bean:financialInfoInstance, field:'amount')}</td>
                            
                             <td class="listing"><g:link action="editExpense" id="${financialInfoInstance.id}"><g:message code="Edit"/></g:link></td>
                        	
                        </tr>
                    </g:each>
                    	
                    </tbody>
			</table>