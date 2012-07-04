<div id="head">
<meta name="layout" content="main" />
</div>
<div id="body">
<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${infrastructureInstance}">
		<div class="errors">
			<g:renderErrors bean="${infrastructureInstance}" as="list" />
		</div>
	</g:hasErrors>
<g:form controller="miscellaneous" >
<center>
		<div class="dialog">
			<table >
			<br /><br /><h3><g:message code="Infrastructure Details"/></h3> <br />
				<tbody>
				  <tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
				  <tr class="prop">
						<input type="hidden" name="InstId" value="${session.InstId}" />
						<td valign="top" class="name">
							<label for="name" ><g:message code="Infrastructure"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:infrastructureInstance,field:'infrastructure','errors')}">
							<input type="text" size="20" id="infrastructure" name="infrastructure" value="${fieldValue(bean:infrastructureInstance,field:'infrastructure')}"/>
						</td>
						<td valign="top" class="name">
							<label for="name" ><g:message code="Description"/>:</label>
						</td>
						<td valign="top"  class="value ${hasErrors(bean:infrastructureInstance,field:'description','errors')}">
							<textarea name="description" id="description" cols="20" rows="2"  value="${fieldValue(bean:infrastructureInstance,field:'description')}"></textarea>
						</td>
					</tr>
					<tr style="background-color: transparent;"><td colspan="4">&nbsp;</td></tr>
					</tbody>
			</table>
		<div>
		<div>
			<g:actionSubmit class="pushbutton" value="Save" action="saveInfrastructure" />
			<input class="pushbutton" type='reset' value='Reset' />
	    </div>
	    </center>
</g:form>
</div>		
<div class="body">
	<div class="list">
	<center>
	<fieldset style="width:40%; border-color:transparent; padding:0;">
	  <div id="listinfrastructure">
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
		</div>
		</fieldset>
		</center>
	</div>
    

</div>
