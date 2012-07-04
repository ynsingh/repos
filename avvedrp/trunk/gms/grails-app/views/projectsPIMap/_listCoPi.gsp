<table width="46%" height="195" border="0">
	<tr>
		<td width="35%">
			<g:if test="${flash.error}">
				<div class="errors">${flash.error}</div>
			</g:if>
			<div class="list">
				<table>							
					<tr>
						<th width="5%"><g:checkBox name="All" onclick="checkAllBox(document.copiSelection.All,document.copiSelection.copiselt)" value="${All}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
						<th width="50%"><g:message code="default.unAssignCoPI.label"/></th>
						
					</tr>
				</table>
				<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
					<table style="width: 100%;" cellpadding="0" cellspacing="0">
						<g:each in="${investigatormapList}" status="i" var="investigatorinstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
									<td width="7%">
										<g:checkBox name="copiselt" value="${investigatorinstance.id}" checked="false"/>
									</td>
									<td width="50%">
     									<g:message code="${investigatorinstance.fullName}"/>
     							</td>
							</tr>
						</g:each>
					</table>
				</div>
			</td>
			<td width="10%">
				<table>
					<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>
	                <g:submitToRemote class="addbutton" action="add" value="	    	     " update="[success:'listCoPi',failure:'error']" before="if(!validateCoPi(document.copiSelection.copiselt,'You did not check a box. Please check a box.')) return false" />
	                <g:submitToRemote class="removebutton"  action="remove" value="           " update="[success:'listCoPi',failure:'error']" before="if(!validateCoPi(document.copiSelection.copidisselt,'You did not check a box. Please check a box.')) return false"/>
       				</td></tr></table>
        		</td>
        	</td><td width="35%">
  				<div class="list">
					<table>
						<tr>
							<th width="5%"><g:checkBox name="AllDel" onclick="checkAllBox(document.copiSelection.AllDel,document.copiSelection.copidisselt)" value="${AllDel}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
							<th width="50%"><g:message code="default.assignCopi.label"/></th>
						</tr>
					</table>
					<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
						   <table style="width: 100%;" cellpadding="0" cellspacing="0">
				                     <g:each in="${copiList}" status="i" var="copiList">
										<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
											<td>
												<g:checkBox name="copidisselt" id="copi" value="${copiList.id}" checked="false"/>
											</td>
											<td>
												${copiList.investigator.fullName}
											</td>
										</tr>
									</g:each>
								
								</table>
					</div>
				</div>	
	 		</td>
 		</tr>
	</table>