<div id="head">
<meta name="layout" content="main" />
</div>
<div id="body">
<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${studentHostelsinstance}">
		<div class="errors">
			<g:renderErrors bean="${studentHostelsinstance}" as="list" />
		</div>
	</g:hasErrors>
<g:form controller="accomodationDetails" >
<center>
		<div class="dialog">
			<table >
			<br /><br /><h3><g:message code="Students Hostel Availabilty"/></h3> <br />
				<tbody>
				
					<tr class="prop">
					    <input type="hidden" name="InstId" value="${session.InstId}" />
					    <td valign="top" class="name">
							<label for="name" ><g:message code="Name of Hostel"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentHostelsinstance,field:'name','errors')}">
							<input type="text" size="25" id="name" name="name" value="${fieldValue(bean:studentHostelsinstance,field:'name')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Hostel Type"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentHostelsinstance,field:'hostelType','errors')}">
							<g:select from= "${generalLookupList}" id="hostelType" optionKey="id" name="hostelType" noSelection="['null':'-Select-']" value="${staffQuartersInstance?.hostelType}"></g:select>
						</td>
				    </tr>
					<tr class="prop">
						<td valign="top" class="name">
							<label for="name" ><g:message code="Intake capacity of Hostel"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentHostelsinstance,field:'intakeCapacity','errors')}">
							<input type="text" size="25" id="intakeCapacity" name="intakeCapacity" value="${fieldValue(bean:studentHostelsinstance,field:'intakeCapacity')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Number of Students residing"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:studentHostelsinstance,field:'numberOfStudents','errors')}">
							<input type="text" size="25" id="numberOfStudents" name="numberOfStudents" value="${fieldValue(bean:studentHostelsinstance,field:'numberOfStudents')}"/>
						</td>
					 </tr>
										
				</tbody>      
			</table>
		</div>
		<div>
			<g:actionSubmit class="pushbutton" value="Save" action="saveHostel" />
			<input class="pushbutton" type='reset' value='Reset' />
		</div>
		</center>
	</g:form>
	</div>
	
	<div class="body">
	<div class="list">
	  <center>
		<fieldset style="width:80%; border-color:transparent; padding:0;">
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
		</fieldset>
		</center>
	</div>
    

</div>
