<table frame="box">
			<thead>
										<tr>
												<g:sortableColumn property="id" title="${message(code: 'SINo')}" style="font-size:12px; "/>&nbsp
												
												<g:sortableColumn property="institutionDetails.id" title="${message(code: 'Institution')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="ntsType" title="${message(code: 'Type Of Staff')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="designation" title="${message(code: 'Designation')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="sanctionedStrength" title="${message(code: 'Sanctioned Stength')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="category" title="${message(code: 'Category')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="pwdReservation" title="${message(code: 'PWD Reservation')}" style="font-size:12px;"/>
												
												<g:sortableColumn property="strength" title="${message(code: 'Strength')}" style="font-size:12px; text-align: center;" colspan="2"/>
												
												<g:sortableColumn property="pwdStrength" title="${message(code: 'PWD Strength')}" style="font-size:12px; text-align: center;" colspan="2"/>
												
												<g:sortableColumn property="muslimStrength" title="${message(code: 'Muslims Strength')}" style="font-size:12px; text-align: center;" colspan="2"/>
												
												<g:sortableColumn property="minoritiesStrength" title="${message(code: 'Other MInorities Strength')}" style="font-size:12px; text-align: center;" colspan="2"/>
												
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
												<td style=" text-align: center;">total</td>
												<td style=" text-align: center;">female</td>
												<td style=" text-align: center;">total</td>
												<td style=" text-align: center;">female</td>
												<td style=" text-align: center;">total</td>
												<td style=" text-align: center;">female</td>
												<td style=" text-align: center;">total</td>
												<td style=" text-align: center;">female</td>
												<td>&nbsp;</td>
										</tr>
								</thead>
		    
			<tbody>
			
                    <g:each in="${staffDetailsInstanceList}" status="i" var="staffDetailsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td class="listing">${i+1}</td>
                            
                            <td class="listing">${fieldValue(bean:institutionDetailsInstance, field:'institutionName')}</td>
                            
                            <td class="listing">${fieldValue(bean:staffDetailsInstance, field:'ntsType')}</td>
                            
                             <td class="listing">${fieldValue(bean:staffDetailsInstance, field:'designation')}</td>
                             
                             <td class="listing">${fieldValue(bean:staffDetailsInstance, field:'sanctionedStrength')}</td>
                         
                             <td class="listing">${fieldValue(bean:staffDetailsInstance, field:'category')}</td>
                         
                             <td class="listing">${fieldValue(bean:staffDetailsInstance, field:'pwdReservation')}</td>
                         
                             <td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsTotal')}</td>
                         
                             <td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsFemale')}</td>
                         
                             <td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsPwdTotal')}</td>
                         
                             <td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsPwdFemale')}</td>
                         
                             <td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsMusTotal')}</td>
                         
                             <td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsMusFemale')}</td>
                         
                             <td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsOthMinTotal')}</td>
                         
                             <td class="listing">${fieldValue(bean:staffDetailsInstance, field:'tsOrNtsOthMinFemale')}</td>
                         
                             <td class="listing"><g:link action="editNonteachingStaff" id="${staffDetailsInstance.id}"><g:message code="Edit"/></g:link></td>
                        	
                        </tr>
                    </g:each>
                    	
                    </tbody>
			</table>