
<table width="46%" height="195" border="0">
<tr><td colspan="3"><g:if test="${flash.message}">
            <div class="errors">${flash.message}</div>
            </g:if></td></tr>
  		<tr>
    		<td width="38%">
    		
    		<div class="list">
						<table>
							<tr>
									<th width="15%"><g:checkBox name="All" id="All" onclick="checkAllBox(document.evalItemNotification.All,document.evalItemNotification['evalItem.id'])" value="${All}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
									<th width="50%"><g:message code="default.EvaluationQuestions.label"/></th>
									<th><g:message code="default.EvaluationScale.label"/></th>
							</tr>
						</table>
							<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
							<g:each in="${evalItemInstance}" status="i" var="evalItemInstance">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
									<td width="15%">
										<g:checkBox name="evalItem.id" id="evalItem.id" value="${evalItemInstance.id}" checked="false"/>
									</td>
									<td width="50%">
										${evalItemInstance.item}
									</td>
									<td>
										${evalItemInstance.evalScale.scaleTitle}
									</td>
								</tr>
							</g:each>
							<input type="hidden" name="evalItem.id" id="evalItem.id"/>
						</table>
					</div></div>
      		</td>
      		<td width="13%">
      		<table>
      		<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>
      			<g:submitToRemote class="addbutton" action="save" value="	    	     " update="[success:'evalItemSel',failure:'error']" before="if(!validateEvalItemNotificationMap(document.evalItemNotification['notification.id'],document.evalItemNotification['evalItem.id'],'You did not check a box. Please check a box.')) return false" />
                <g:submitToRemote class="removebutton" action="delete" value="           " update="[success:'evalItemSel',failure:'error']" before="if(!validateEvalItemNotificationMap(document.evalItemNotification['notification.id'],document.evalItemNotification['evalItemNotificationMap.id'],'You did not check a box. Please check a box.')) return false"/>
                 </td></tr></table>
                </td>
                <td width="45%">
      			<div class="list">
    					<table>
							
								<tr>
									<th width="15%"><g:checkBox name="AllDel" onclick="checkAllBox(document.evalItemNotification.AllDel,document.evalItemNotification['evalItemNotificationMap.id'])" value="${AllDel}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
									<th width="50%"><g:message code="default.AssignedQuestions.label"/></th>
									<th><g:message code="default.AssignedScale.label"/></th>
								</tr>
							</table>
							<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
								<g:each in="${evalItemNotificationMapInstance}" status="i" var="evalItemNotificationMapInstance">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td width="15%"><g:checkBox name="evalItemNotificationMap.id" id="evalItemNotificationMap.id" value="${evalItemNotificationMapInstance.id}" checked="false"/></td>
								<td width="50%">
									${evalItemNotificationMapInstance.evalItem.item}
								</td>
								<td>
									${evalItemNotificationMapInstance.evalItem.evalScale.scaleTitle}
								</td>
								</tr>
							</g:each>
							<input type="hidden" name="evalItemNotificationMap.id" id="evalItemNotificationMap.id"/>
						</table>
					</div></div>	
			</td>
  		</tr>
	</table>

	
