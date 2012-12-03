<table width="46%" height="195" border="0">
	<tr>
		<td width="35%">
			<g:if test="${flash.error}">
				<div class="errors">${flash.error}</div>
			</g:if>
			<div class="list">
				<table>							
					<tr>
						<th width="5%"><g:checkBox name="All" onclick="checkAllBox(document.chklist.All,document.chklist.chkselt)" value="${All}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
						<th width="50%"><g:message code="default.unAssignchecklist.label"/></th>
						
					</tr>
				</table>
				<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
					<table style="width: 100%;" cellpadding="0" cellspacing="0">
							<g:each in="${checkList}" status="i" var="checkList">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
									<td>
									<g:checkBox name="chkselt" id="check" value="${checkList.id}" checked="false"/>
								</td>
								<td>
									${checkList.field}
								</td>
					            </tr>
							</g:each>
					</table>
				</div>
			</td>
			<td width="10%">
			<table>
				<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>
    				<g:submitToRemote class="addbutton" action="add" value="	    	     " update="[success:'checkNameSel',failure:'error']" before="if(!validateCoPi(document.chklist.chkselt,'You did not check a box. Please check a box.')) return false" />
				    <g:submitToRemote class="removebutton"  action="remove" value="           " update="[success:'checkNameSel',failure:'error']" before="if(!validateCoPi(document.chklist.chkdisselt,'You did not check a box. Please check a box.')) return false"/>
   				</td></tr></table>
    		</td>
        	</td><td width="35%">
  				<div class="list">
					<table>
						<tr>
							<th width="5%"><g:checkBox name="AllDel" onclick="checkAllBox(document.chklist.AllDel,document.chklist['chkdisselt'])" value="${AllDel}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
							<th width="50%"><g:message code="default.assignChecklist.label"/></th>
							
						</tr>
					</table>
					<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
						 <table style="width: 100%;" cellpadding="0" cellspacing="0">
						    <g:each in="${mapcheckList}" status="i" var="mapcheckList">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
									<td>
										<g:checkBox name="chkdisselt" id="check" value="${mapcheckList.id}" checked="false"/>
									</td>
									<td>
										${mapcheckList.checklist.field}
									</td>
								</tr>
							</g:each>
						 </table>
			       </div>
			   </div>
		    </div>	
	 	  </td>
 		</tr>
	</table>