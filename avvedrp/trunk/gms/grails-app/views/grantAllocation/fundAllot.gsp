

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.FundAllocation.CreateFundAllocation.head"/></title>         
    </head>
    <body>
    	<g:subMenuProjects/>
        <div class="wrapper">
          <div class="body">
            <h1><g:message code="default.FundAllocation.CreateFundAllocation.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantAllocationInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantAllocationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <form action="funtSave" method="post" >
                <div class="dialog">
                <g:hiddenField name="ProjectStartDate" 
		    					value="${fieldValue(bean:projectsInstance, field:'projectStartDate')}"/>
				<g:hiddenField name="ProjectEndDate" value="${projectsInstance?.projectEndDate}"/>
		    
                    <table>
                        <tbody>
                        
                         <tr >
                                <td valign="top" class="name">
                                    <label for="projects"><g:message code="default.Project.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'projects','errors')}">
                                   <strong> ${projectsInstance.code} </strong>
                                </td>
                                
                                
                                <td align="left" class="name">
                                <g:if test="${projectsInstance.parent!=null}"
                                    <label for="party"><g:message code="default.GrantAgency.label"/>:</label>
                                </td>
                                <td>
                                     <strong>  ${partyinstance[0].code} </strong>
                                       
                                </td>
                                </tr>
                                </g:if>
                                <g:else>
                                </tr>
                                </g:else>
                             
                        
                           
                           <tr>
                                <!--<td valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:grantAllocationInstance,field:'code')}"/>
                                </td>-->
                          <td valign="top" class="name">
                                    <label for="dateOfAllocation"><g:message code="default.DateOfAllocation.label"/>:</label>
                                </td>
                                <td valign="top" 
                                	class="value ${hasErrors(bean:grantAllocationInstance,field:'dateOfAllocation','errors')}">
                                    <calendar:datePicker name="dateOfAllocation" defaultValue="${new Date()}" 
                                    	value="${grantAllocationInstance?.dateOfAllocation}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            
                                <td valign="top" class="name">
                                    <label for="amountAllocated"><g:message code="default.AmountAllocated(Rs).label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'amountAllocated','errors')}">
                                    <input type="text" id="amountAllocated" name="amountAllocated" value="${fieldValue(bean:grantAllocationInstance,field:'amountAllocated')}" style="text-align: right" />
                                </td>
                            </tr> 
                        
                      
                            <tr>
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="default.Remarks.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'remarks','errors')}">
                                      <g:textArea name="remarks" value="${fieldValue(bean:grantAllocationInstance,field:'remarks')}" rows="1" cols="30"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                 <input  class="inputbutton" type="submit" name="funtSave" value="${message(code: 'default.Save.button')}"  onClick="return validateFundAllot()" >
            </form>
               </div>
                          
        <div class="body">       
            <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                                                                                 
                   	        <g:sortableColumn property="projects.code" title="${message(code: 'default.ProjectCode.label')}" />
                   	        <g:sortableColumn property="investigator.name" title="${message(code: 'default.PIName.label')}"/>
                            <g:sortableColumn property="projects.projectStartDate" title="${message(code: 'default.ProjectStartDate.label')}" />
                            <g:sortableColumn property="projects.projectEndDate" title="${message(code: 'default.ProjectEndDate.label')}" />
                            <th><g:message code="default.DateOfAllocation.label"/></th>
                   	        <g:sortableColumn property="amountAllocated" title="${message(code: 'default.AmountAllocated.label')}"/>
                           
							<!--<th><g:message code="default.FundTransfer.label"/></th>-->                           
                           <g:sortableColumn property="Remarks" title="${message(code: 'default.Remarks.label')}"/>     
                   	       
                        
                   	       <th><g:message code="default.Edit.label"/></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationInstanceList}" status="i" var="grantAllocationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${(i + 1)}</td>
                            <td>${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</td>
                             <td>${fieldValue(bean:projectsPIMapInstance, field:'investigator.name')}</td>
                               <td><g:formatDate format="dd-MM-yyyy" date="${grantAllocationInstance.projects.projectStartDate}"/></td>
                            <td><g:formatDate format="dd-MM-yyyy" date="${grantAllocationInstance.projects.projectEndDate}"/></td>
                        
                            <td><g:formatDate format="dd-MM-yyyy" date="${grantAllocationInstance.dateOfAllocation}"/></td>
                             <td>${currencyFormat.ConvertToIndainRS(grantAllocationInstance.amountAllocated)}</td>
    	                     
                       <!-- <td>
                        	 <g:link action="create" controller="fundTransfer" id="${grantAllocationInstance.id}" params="[subMenu:'fundAllot']">
                        		<g:message code="default.FundTransfer.label"/>
                        	</g:link>
                        </td>-->
                        <td>${fieldValue(bean:grantAllocationInstance, field:'remarks')}</td>
                            
                        <td><g:link action="edit" id="${grantAllocationInstance.id}"><g:message code="default.Edit.label"/></g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
             
</div>
    </body>
</html>
