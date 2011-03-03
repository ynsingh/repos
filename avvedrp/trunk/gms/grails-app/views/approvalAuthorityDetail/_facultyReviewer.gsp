<div class="wrapper"> 
	<g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
	            </g:if>
    <g:if test="${flash.error}">
	            <div class="errors">${flash.error}</div>
        	  </g:if>
	 <table width="100%">
	  		<tr>
	    		<td>
	    			<h1 align="left"><g:message code="default.Reviewer.label" /></h1>
	    		<div class="list">
	 
	                            <table style="width: 100%;" cellpadding="0" cellspacing="0">
	 								<tr>
									    <th><g:checkBox name="AllMembers" onclick="checkAllMembers()" value="${All}" checked="false" /></th>
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
				</div></div>	
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
	                <g:submitToRemote class="removebutton" controller="approvalAuthorityDetail" action="remove" params="person.id" value="        " update="[success:'facultyReviewer',failure:'error']" before="if(!validateAppAuthorityPersonForDelete()) return false"/>
	               </td>
	                 </tr>
	           </table>
	            </td>
	            <td>
	            	<h1 align="left"><g:message code="default.AssignedMembers.label" /></h1>
	      			<div class="list">
	    					     <table style="width: 100%;" cellpadding="0" cellspacing="0">
				                     <tr>
									    <th><g:checkBox name="AllapprovalAuthorityDetail" onclick="checkAllApprovalAuthorityDetails()" value="${All}" checked="false"/></th>
										<th>ApprovalAuthority</th>
										<th>Person</th>
								     </tr>		
									 <g:each in="${approvalAuthorityDetailInstance}" status="i" var="approvalAuthorityDetailInstance">
										 <input type="hidden" id="deleteAction" name="deleteAction" value="${approvalAuthorityDetailInstance.id}"/>
										<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
										<td>
											<g:checkBox name="approvalAuthority" id="approvalAuthority" value="${approvalAuthorityDetailInstance.id}" checked="false"/>
										</td>
										<td>
										   ${approvalAuthorityDetailInstance.approvalAuthority.name}
										</td>
										<td>
											${approvalAuthorityDetailInstance.person.username}
										</td>
							            </tr>
									</g:each>
								
								</table>
						</div></div>	
						<td>
						</tr>
	</table>
</div>					
			
			