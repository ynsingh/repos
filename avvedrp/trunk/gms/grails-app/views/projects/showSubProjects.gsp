<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.CreateSubProjects.head" /></title>         
    </head>
    <body>
	    <div class="wrapper"> 
			<g:subMenuList/>
		    <g:hiddenField name="parentProjectStartDate" 
		    	value="${fieldValue(bean:projectsInstance, field:'parent.projectStartDate')}"/>
		    <g:hiddenField name="parentProjectEndDate" 
		    	value="${fieldValue(bean:projectsInstance, field:'parent.projectEndDate')}"/>
	        
	        <div class="body">
        		<h1><g:message code="default.CreateSubProjects.head" /></h1>
		        <g:hasErrors bean="${projectsInstance}">
		            <div class="errors">
		                <g:renderErrors bean="${projectsInstance}" as="List" />
		            </div>
				</g:hasErrors>
	            <g:form action="saveSub" method="post" >
	                <div class="dialog">
	                    <table  cellpading="0" cellspacing="0">
	                     	<tbody>
	                     		
	                     		<tr class="prop">
			                        <td valign="top" class="name">
				                        <label for="parent">
				                        	<g:message code="default.projects.MainProject.label" />:
				                        </label>
                         			</td>
                         			<td valign="top" 
	                   					class="value ${hasErrors(bean:projectsInstance,field:'parent','errors')}">
	                    				<strong>${fieldValue(bean:projectsInstance,field:'parent.name')}</strong>
	                           		</td>
				                    
				                    <td  valign="top" class="name">
			                         	<label for="parent">
				                        	<g:message code="default.projects.MainProjectCode.label" />:
				                        </label>
			                        </td>
			                        <td align="left" valign="top" 
		                           		class="value ${hasErrors(bean:projectsInstance,field:'parent','errors')}">
	                                    <strong> ${fieldValue(bean:projectsInstance,field:'parent.code')}</strong>
	                                    <g:hiddenField id="parentid" name="parent.id" 
	                                     	value="${fieldValue(bean:projectsInstance, field:'parent.id')}"/>
	                   				</td>
					                
					                <td  valign="top" class="name">
					                    <g:if test="${grantAllocationInstance.granter!=null}"> 
										    <g:message code="default.projects.Grantor.label" /> : 
										    <strong>${grantAllocationInstance.granter.code}</strong>
										    <br>
									    </g:if>
				                    </td>
			                    </tr> 
                                                 
                           		<tr >
	                                <td valign="top" class="name">
	                                    <label for="name"><g:message code="default.Name.label"/>:</label>
	                                </td>
	                                <td valign="top" 
	                                	class="value ${hasErrors(bean:projectsInstance,field:'name','errors')}">
	                                    <input type="text" size="35" id="name" name="name" 
	                                    	value="${fieldValue(bean:projectsInstance,field:'name')}"/>
                                 	 </td>
                                 	 
                                 	 <td  valign="top" class="name">
	                                    <label for="code"><g:message code="default.Code.label"/>:</label>
	                                </td>
	                                <td valign="top" 
	                                	class="value ${hasErrors(bean:projectsInstance,field:'code','errors')}">
	                                    <input type="text" id="code" name="code" 
	                                    	value="${fieldValue(bean:projectsInstance,field:'code')}"/>
	                                </td>
	                            </tr> 
		                   		
		                   		<tr class="prop">
		                   			<td colspan="3"><div align="left">
			                   			<label for="dateRangeFrom">
			                   				<g:message code="default.projects.MainProjectStartDate.label"/>: 
		                   				</label>
			                    		<strong>
			                    			<g:formatDate date="${projectsInstance.parent.projectStartDate}" 
			                    			format="dd/MM/yyyy"/> 
		                    			</strong>
			                    		<label for="dateRangeTo"><g:message code="default.EndDate.label"/>: </label>              
			                    		<strong> 
			                    			<g:formatDate date="${projectsInstance.parent.projectEndDate}" 
			                    			format="dd/MM/yyyy"/> 
		                    			</strong> 
		                    		</td>
	                    		</tr> 
                            	
                            	<tr class="prop">
		                            <td valign="top" class="name">
			                            <label for="projectStartDate">
			                            	<g:message code="default.StartDate.label"/>:
		                            	</label>
		                            </td>
		                            <td valign="top" 
		                            	class="value ${hasErrors(bean:projectsInstance,field:'projectStartDate','errors')}">
		                            	<calendar:datePicker name="projectStartDate" 
		                            		value="${projectsInstance?.projectStartDate}" defaultValue="${new Date()}" 
		                            		dateFormat= "%d/%m/%Y"/>
		                            </td>
		                            
		                            <td valign="top" class="name">
			                            <label for="projectEndDate">
			                            	<g:message code="default.EndDate.label"/>:
		                            	</label>
		                            </td>
		                            <td colspan="3"  valign="top" 
		                            	class="value ${hasErrors(bean:projectsInstance,field:'projectEndDate','errors')}">
		                            	<calendar:datePicker name="projectEndDate" value="${projectsInstance?.projectEndDate}" 
		                            		defaultValue="${new Date()}" dateFormat= "%d/%m/%Y"/>
		                            </td>
                             	</tr> 
		                       
		                        <tr class="prop">     
									<td valign="top" class="name">
	                            		<label for="activeYesNo"><g:message code="default.Active.label"/>:</label>
	                            	</td>
	                            	<td valign="top" 
	                            		class="value ${hasErrors(bean:projectsInstance,field:'activeYesNo','errors')}">
	                            		<g:select name="activeYesNo" from="${['Y', 'N']}"  
	                            			value="${fieldValue(bean:projectsInstance,field:'activeYesNo')}" />
	                           		<td>
                           		</tr> 
          					</tbody>
						</table>
            		</div>
	                <div class="buttons">
	                    <span class="button">
	                    	<input class="save" type="submit" value="${message(code: 'default.Create.button')}" 
	                    	onClick="return validateSubProject()" />
                    	</span>
	                </div>
            	</g:form>
        	</div>
      		<div class="body">
	            <h1><g:message code="default.projects.SubProjectsList.head"/> 
	            	${fieldValue(bean:projectsInstance,field:'parent.name')}</h1>
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:if test="${projectsInstanceList}">
		            <div class="list">
		            	<table cellspacing="0" cellpadding="0">
                    		<thead>
                    			<tr>
				         	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
				                    <g:sortableColumn property="name" title="${message(code: 'default.Name.label')}" />
				                    <g:sortableColumn property="code" title="${message(code: 'default.Code.label')}" />               
				               	    <g:sortableColumn property="activeYesNo" title="${message(code: 'default.Active.label')}" />
				               	    <th><g:message code="default.Edit.label"/></th>
			                    </tr>
                    		</thead>
	                    	<tbody>
			                    <g:each in="${projectsInstanceList}" status="i" var="projectsInstanceList">
			                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
		                            	<td>${i+1}</td>
				                        <td>${fieldValue(bean:projectsInstanceList, field:'name')}</td>
				                        <td>${fieldValue(bean:projectsInstanceList, field:'code')}</td>
				                        <td>
			                	             <g:if test="${fieldValue(bean:projectsInstanceList, field:'activeYesNo') == 'Y'}">
			    							 	${'YES'}
			    							 </g:if>
			    							 <g:else>
			    							 	${'NO'}
			    							 </g:else>
                         				</td>
                         				<td><g:link  action="editsub" id="${projectsInstanceList.id}" >
                         						<g:message code="default.Edit.label"/>
                     						</g:link>
                 						</td>
                   					</tr>
                    			</g:each>
                			</tbody>
                		</table>
            		</div>
            	</g:if>
            	<g:else>
            		<br><g:message code="default.NoRecordsAvailable.label"/></br>
            	</g:else>
        	</div>
    	</table>
      </div>
    </body>
</html>
