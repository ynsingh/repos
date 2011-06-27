<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:javascript library="jquery" /> 
              <g:javascript library="applicationValidation" />
        <g:set var="entityName" value="${message(code: 'approvalAuthorityDetail.label', default: 'ApprovalAuthorityDetail')}" />
        <title><g:message code="default.AddMembers.head"/></title>
    </head>
    <body>
        
        <div class="body">
            <h1 align="left"><g:message code="default.AddMembers.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${approvalAuthorityDetailInstance}">
            
            </g:hasErrors>
            <g:form name="appAuthority" action="save" method="post" >
                <div class="dialog">  
                    <table width="40%" height="34" >
                        <tbody>
                        
                        <tr class="prop">
                                <td width="20%" height="34" >
                                    <label for="approvalAuthority"><g:message code="default.ApprovalAuthority.label" />:</label>
                                </td>
                                <td align="left" class="value ${hasErrors(bean: approvalAuthorityDetailInstance, field: 'approvalAuthority', 'errors')}">
                                    ${fieldValue(bean: approvalAuthorityInstance,field: "name")}
                                    <input type=hidden name="approvalAuthority.id" value="${approvalAuthorityInstance.id}"> 
                                </td>

                        </tr>                           
                    </tbody>
                    </table>
                
                
       <div id="facultyReviewer">
        
    <table width="100%">
  		<tr>
    		<td>
    		     
    		<h1 align="left"><g:message code="default.Reviewer.label" /></h1>
    		<div class="list">
 
                           
                            <table style="width: 100%;" cellpadding="0" cellspacing="0">
 								<tr>
								    <th><g:checkBox name="AllMembers" onclick="checkAllMembers()" value="${All}" checked="false"/></th>
									<th>Name</th>
								</tr>
								<g:each in="${userInstanceList}" status="i" var="userInstanceList">
									<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
									<td>
										<g:checkBox name="person" id="person" value="${userInstanceList.id}" checked="false"/>
									</td>
									<td>
										${userInstanceList.username}
									</td>
						            </tr>
								</g:each>
					      </table>
					      </div>	
			
			</td> 
			<td>
			<table>
			   <tr><td> &nbsp </td></tr><tr><td>&nbsp</td></tr><tr><td> &nbsp </td></tr>
			    <tr>
      		    <td>
 	            <g:submitToRemote class="addbutton" controller="approvalAuthorityDetail" action="add"  value="        " update="[success:'facultyReviewer',failure:'error']" before="if(!validateAppAuthorityMembers()) return false" />
                </td>
                </tr>
                <tr>
                <td>
                <g:submitToRemote class="removebutton" controller="approvalAuthorityDetail" action="remove" value="        " update="[success:'facultyReviewer',failure:'error']" before="if(!validateAppAuthorityPersonForDelete()) return false"/>
                </td>
                </tr>
            
            </table>
             </td>
            <td>
            	<h1 align="left"><g:message code="default.AssignedMembers.label" /></h1>
      			<div class="list">
    					    <table style="width: 100%;" cellpadding="0" cellspacing="0">
			                     <tr>
								    <th align="center"><g:checkBox name="AllapprovalAuthorityDetail" onclick="checkAllApprovalAuthorityDetails()" value="${All}" checked="false"/></th>
									<th>Approval Authority</th>
									<th>Person</th>
							     </tr>		
								<g:each in="${approvalAuthorityDetailInstance}" status="i" var="approvalAuthorityInstance">
								  
									<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
									<td>
										<g:checkBox name="approvalAuthority" id="approvalAuthority" value="${approvalAuthorityInstance.id}" checked="false"/>
									    <input type="hidden" id="deleteAction" name="deleteAction" value="${approvalAuthorityInstance.id}"/>
									</td>
									<td>
									   ${approvalAuthorityInstance.approvalAuthority.name}
									</td>
									<td>
										${approvalAuthorityInstance.person.username}
									</td>
						            </tr>
								</g:each>
							
							</table>
					</div></div>
					
					<td>
					</tr>
	</table>
	</div>	
        </g:form>
        </div>
         </div>
    </body>
</html>
