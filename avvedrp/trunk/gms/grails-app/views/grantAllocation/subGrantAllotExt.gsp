

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create GrantAllocation</title>        
    </head>
    <body>
<div class="wrapper"> 
<g:subMenuList/>
<div class="proptable">
 <div class="body">
            <h1>Project Allotment</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantAllocationInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantAllocationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="subGrantSaveExt" method="post" >
                <div class="dialog">
        <table><tbody>
                        <tbody>
                        
                         <tr>
                                <td valign="top" class="name">
                                    <label for="projects">Projects:</label>
                                </td>
                                <td valign="top" >
                                 <strong>  ${fieldValue(bean:projectInstance,field:'code')} </strong>
                                    <input type="hidden" id="project" name="project" value="${fieldValue(bean:projectInstance, field:'id')}"/>
                                    <!--<input type="hidden" id="grantAllotId" name="grantAllotId" value="${fieldValue(bean:grantAllocation, field:'id')}"/>-->
                                  </td>
                                  
                                <td valign="top" class="name">
                                    <label for="party"> Participant:</label>
                                </td>
                                <td valign="top" >
                                  <strong> ${fieldValue(bean:partyInstance,field:'code')} </strong>
                                	<input type="hidden" id="party" name="party" value="${fieldValue(bean:partyInstance, field:'id')}"/>
                                  </td>                                
                                  
                                  <td valign="top" class="name">
                                    <label for="party"> Amount Allocated(Rs):</label>
                                </td>
                                <td valign="top" >
                                <strong>${currencyFormat.ConvertToIndainRS(projectInstance.totAllAmount)}</strong>
                                  
                                  
                                
                                  </td>
                            </tr> 
                        
                        <tr>
                                <!--<td valign="top" class="name">
                                    <label for="project">Sub project</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'projects','errors')}">
                                    <g:select  optionKey="id" optionValue="code" from="${Projects.findAllByParent(projectInstance)}" name="projects.id" value="${grantAllocationInstance?.projects?.id}"></g:select>
                                </td> -->
                          
                                    <td valign="top" class="name">
                                    <label for="party"> Granter :</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'granter','errors')}" >
                                   <g:select id="granter" optionKey="id" optionValue="code" from="${Party.findAll('from Party P where P.id!=\'partyInstance.id\' and P.activeYesNo=\'Y\' ')}" name="granter.id" value="${grantAllocationInstance?.granter?.id}" noSelection="['null':'Select']"></g:select>
                                   <input type="hidden" id="projects.id" name="projects.id" value="${fieldValue(bean:grantAllocationInstance, field:'projects.id')}"/>
                                   <input type="hidden" id="party.id" name="party.id" value="${fieldValue(bean:grantAllocationInstance, field:'party.id')}"/>
                                  </td>
                          
                          
                               <!-- <td valign="top" class="name">
                                    <label for="party">Participant:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'party','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${Party.findAllByIdNotEqual(partyInstance.id)}"  name="party.id" value="${grantAllocationInstance?.party?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            
                        
                        
                         <tr>
                                 <td valign="top" class="name">
                                    <label for="code">Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:grantAllocationInstance,field:'code')}" style="text-align: left"/>
                                </td>   -->
                         
                                <td valign="top" class="name">
                                    <label for="dateOfAllocation">Date Of Allocation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'dateOfAllocation','errors')}">

                                     <calendar:datePicker name="dateOfAllocation" defaultValue="${new Date()}" value="${grantAllocationInstance?.dateOfAllocation}" dateFormat= "%d/%m/%Y" />
                                </td>
                          
                              
                            </tr> 
                        
                      
                            <tr >
                            
                              <td valign="top" class="name">
                                    <label for="amountAllocated">Amount Allocated(Rs):</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'amountAllocated','errors')}">
                                    <input type="text" id="amountAllocated" name="amountAllocated" value="${fieldValue(bean:grantAllocationInstance,field:'amountAllocated')}" style="text-align: right"/>
                                </td>
                                <td valign="top" class="name">
                                    <label for="remarks">Remarks:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'remarks','errors')}">
                                      <g:textArea name="remarks" value="${fieldValue(bean:grantAllocationInstance,field:'remarks')}" rows="3" cols="30"/>
                                </td>
                            </tr> 
                            <tr>
                             <td valign="top" class="name">
                                    <label for="sanctionOrderNo">Sanction Order No:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'amountAllocated','errors')}">
                                    <input type="text" id="sanctionOrderNo" name="sanctionOrderNo" value="${fieldValue(bean:grantAllocationInstance,field:'sanctionOrderNo')}" style="text-align: right"/>
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" onClick="return validateSubGrantAllotExt()"  /></span>
                </div>
              </g:form>
</div>
 <div class="body">
    <g:if test="${grantAllocationInstanceList}">
    <div class="list">
                <table width="97%" align="center" border="0" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="SlNo" />
                            
                          
                             
                   	        <g:sortableColumn property="projects.code" title="Project Code " />
                   	           <g:sortableColumn property="investigator.name" title="PI Name " />
                            <g:sortableColumn property="projects.projectStartDate" title="Project Start Date " />
                            <g:sortableColumn property="projects.projectEndDate" title="Project End date " />
                        
                   	        <g:sortableColumn property="party" title="Participant" />
                   	        
                   	        <g:sortableColumn property="amountAllocated" title="Amount Allocated(Rs)" />
                   	        <g:sortableColumn property="sanctionOrderNo" title="Sanction Order No" />
                   	        <g:sortableColumn property="granter" title="Granter" />
                   	        <th>Fund Type </th>
                   	        
                   	        <g:sortableColumn property="amountAllocated" title="Grant Withdrawal/Closure" />
                        
                   	       
                   	       <th>Edit</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationInstanceList}" status="i" var="grantAllocationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${(i + 1)}</td>
                           
                            
                            
                            <td>${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</td>
                             <td>${fieldValue(bean:projectsPIMapInstanceList[i], field:'investigator.name')}</td>
                               <td><g:formatDate format="dd-MM-yyyy" date="${grantAllocationInstance.projects.projectStartDate}"/></td>
                            <td><g:formatDate format="dd-MM-yyyy" date="${grantAllocationInstance.projects.projectEndDate}"/></td>
                            
 
                        
                            <td>${fieldValue(bean:grantAllocationInstance, field:'party.code')}</td>
                        
                          
                             <td>${currencyFormat.ConvertToIndainRS(grantAllocationInstance.amountAllocated)}</td>
                            
                        	<td>${fieldValue(bean:grantAllocationInstance,field:'sanctionOrderNo')} </td>
                        	<td>${fieldValue(bean:grantAllocationInstance,field:'granter.code')} </td>
                        	<td> <g:if test="${grantAllocationInstance.granter ==null}"> 
				        
				Self Fund			    
				</g:if>
				<g:else>
							
				Grant
			</g:else>
			</td>
                        	
                            <td><g:link action="create"  controller='grantAllocationTracking' id="${grantAllocationInstance.id}" params="[trackType:'withdraw']">Grant Withdrawal/Closure</g:link></td>
                        <td><g:link action="editProAllot" id="${grantAllocationInstance.id}">Edit</g:link></td>
                           
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            </g:if>
     


      </div>
    </div>    
   </div>             
           
      
       
    </body>
</html>
