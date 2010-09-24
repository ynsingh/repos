
<table width="46%" height="195" border="0">
  		<tr>
    		<td width="42%">
    		<div class="list">
						<table>
							
								<tr>
									<th width="65%">Project Name</th>
									<th><g:checkBox name="All" onclick="checkAll();" value="${All}" checked="false"/> Select All</th>
								</tr>
							</table>
							<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
							<g:each in="${grantAllocationInstance}" status="i" var="grantAllocationInstance">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td width="65%">
								${grantAllocationInstance.projects.name}
								</td>
								<td width="40%"><g:checkBox name="projectName" value="${grantAllocationInstance.id}" checked="false"/></td>
								</tr>
							</g:each>
							
						</table>
					</div></div>
      		</td>
      		<td>&nbsp;&nbsp;</td>
      		<td width="13%">
      		<table>
      		<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>
      		
      			<g:submitToRemote class="addbutton" action="getandSaveAccessPermission" value="	  		     " update="[success:'projectNameSel',failure:'error']" before="if(!validate()) return false"/>
                <g:submitToRemote class="removebutton" action="deleteProjectFromAccespermision" value="          " update="[success:'projectNameSel',failure:'error']" before="if(!validateForDelete()) return false"/>
              </td></tr></table>  
                </td>
                <td width="45%">
      			
      			<div class="list">
    					<table>
								<tr>
									<th width="65%">Project Name</th>
									<th width="40%"><g:checkBox name="AllDelete" onclick="checkAllDelete()" value="${AllDel}" checked="false"/> Select All</th>
								</tr>
							</table>
							<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
								<g:each in="${projectInstance}" status="i" var="projectInstance">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td width="65%">
									${projectInstance.name}
								</td>
								<td width="40%"><g:checkBox name="projectId" value="${projectInstance.id}" checked="false"/></td>
							</tr>
							</g:each>
						</table>
						</div></div>
			</td>
  		</tr>
	</table>
	
  
