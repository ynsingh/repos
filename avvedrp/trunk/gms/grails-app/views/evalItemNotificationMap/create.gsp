


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'QuestionNotificationMap.label', default: 'EvalItemNotificationMap')}" />
        <g:javascript library="jquery" />
        <title><g:message code="default.QuestionNotificationMap.label" args="[entityName]" /></title>
    </head>
    <body>
       
        <div class="body">
        <div class="wrapper"> 
        <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
            <h1><g:message code="default.QuestionNotificationMap.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${evalItemNotificationMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${evalItemNotificationMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" name="evalItemNotification">
                <div class="dialog">
                    <table>
                        <tbody>
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="notification"><g:message code="default.notification.label" default="Notification" />:</label>
                                    <label for="notification" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: evalItemNotificationMapInstance, field: 'notification', 'errors')}">
                                    <g:select name="notification.id" from="${notificationInstance}" optionKey="id" optionValue="notificationTitle" value="${evalItemNotificationMapInstance?.notification?.id}" 
                                    onchange="${remoteFunction(controller:'evalItemNotificationMap',action:'listEvalItem',update:'evalItemSel',params:'\'notification.id=\'+this.value')};" noSelection="['null':'-Select-']"/>
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                    
                    <div id="evalItemSel">
    <table width="46%" height="195" border="0">
  				<tr>
    				<td width="42%">
    					<div class="list">
							<table>							
								<tr>
									<th width="15%"><g:checkBox name="All" onclick="checkAllBox(document.evalItemNotification.All,document.evalItemNotification.evalItem)" value="${All}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
									<th width="50%"><g:message code="default.EvaluationQuestions.label"/></th>
									<th><g:message code="default.EvaluationScale.label"/></th>
								</tr>
							</table>
							<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
  							<table style="width: 100%;" cellpadding="0" cellspacing="0">
							
							<g:each in="${evalItemInstance}" status="i" var="evalItemInstance">
								<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td width="15%">
								<g:checkBox name="actionName" value="${evalItemInstance}" checked="false"/>
								</td>
								<td width="50%">
								${evalItemInstance}
								</td>
								</tr>
							</g:each>
						
						</table>
					</div>
      		</td>
      		<td width="13%">
      		<table>
      		<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>
      			<g:submitToRemote class="addbutton" action="save" value="	    	     " update="[success:'evalItemSel',failure:'error']" before="if(!validateEvalItemNotificationMap(document.evalItemNotification['notification.id'],'Please Select a Notification',document.evalItemNotification['evalItem.id'],'You did not check a box. Please check a box.')) return false" />
                <g:submitToRemote class="removebutton" action="delete" value="           " update="[success:'evalItemSel',failure:'error']" before="if(!validateEvalItemNotificationMap(document.evalItemNotification['notification.id'],'Please Select a Notification',document.evalItemNotification['evalItemNotificationMap.id'],'You did not check a box. Please check a box.')) return false"/>
               </td></tr></table>
                </td>
                </td><td width="45%">
      			<div class="list">
    					<table>
								<tr>
									<th width="15%"><g:checkBox name="AllDel" onclick="checkAllBox(document.evalItemNotification.AllDel,document.evalItemNotification['evalItem.id'])" value="${AllDel}" checked="false"/><g:message code="default.evalItem.all.label"/></th>
									<th width="50%"><g:message code="default.AssignedQuestions.label"/></th>
									<th><g:message code="default.AssignedScale.label"/></th>
								</tr>
						</table>
						<div style="overflow: auto;height: 260px;border-width:5px;border-style:solid;border-width:thin;border-color:#FFFFFF">
  						<table style="width: 100%;" cellpadding="0" cellspacing="0">
							      <g:each in="${evalItemNotificationMapInstance}" status="i" var="evalItemNotificationMapInstance">
								    <input type="hidden" name="evalItemNotificationMap.id" id="evalItemNotificationMap.id"/>
								  </g:each>
						</table>
					</div></div>	
			 </td>
  		 </tr>
	</table></div>
                </div>
                
            </g:form>
        </div>
       </div>
    </body>
</html>
