<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'default.proposalReview.label')}" />
        <title><g:message code="default.proposalReview.label"/></title>
        <script language="javascript" type="text/javascript">
    
        function changeCssClass(objDivID,buttonId)
        {
            if(document.getElementById(objDivID).className=='contentDiv')
            {
                document.getElementById(objDivID).className = 'contentFullDiv';
                document.getElementById(buttonId).value = '${message(code: 'default.Minimize.button')}';
            }
            else
            {
                document.getElementById(objDivID).className = 'contentDiv';
                document.getElementById(buttonId).value = '${message(code: 'default.Maximize.button')}';
            }
        }

    </script>
    </head>
    <body>
           <div class="wrapper">
            <div class="body">
            <h1><g:message code="default.ProposalDetails.label"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${flash.error}">
            <div class="errors">${flash.error}</div>
            </g:if>
            <g:hasErrors bean="${evalAnswerInstanceList}">
            <div class="errors">
                <g:renderErrors bean="${evalAnswerInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form name="evalForm" method="post" >
               	<g:hiddenField name="proposalId" value="${proposalId}" />	 
            	<g:hiddenField name="notificationId" value="${notificationId}" />	
            	
            	 <div id="proposalDiv" class="contentDiv">
                <g:render template="proposalApplicationData" model="['proposalApplicationExtInstance':proposalApplicationExtInstance,'proposalApplicationInstance':proposalApplicationInstance,'attachmentsInstanceGetCV':attachmentsInstanceGetCV,'attachmentsInstanceGetDPR':attachmentsInstanceGetDPR]" />
                </div>
                <div style="text-align:right">
                <input type="button" id="prmaxmin" onClick="changeCssClass('proposalDiv','prmaxmin')"  value="${message(code: 'default.Maximize.button')}"/>
                </div>
                </div>
                <g:if test="${evalItemInstanceList}">
                <div class="body">
                <h1><g:message code="default.proposalReview.label"/></h1>
                <div id="reviewDiv" class="contentDiv">
                <div class="dialog">
                    <table>
                            <g:each in="${evalItemInstanceList}" status="i" var="evalItemInstance">
		                      <g:hiddenField name="id${i}" value="${evalAnswerInstanceList[i]?.id}" />
		                        <tr>
		                        	<td>${i+1}. ${evalItemInstance?.item}</td>
		                        	<g:hiddenField name="evalItemId${i}" value="${evalItemInstance?.id}" />	
		                        </tr>
		                        <tr>
			                        <td>
		                           		<g:radioGroup id="scaleOptions${i}" name="scaleOptions${i}" labels="${evalScaleOptionsList[i].scaleOption}" values="${evalScaleOptionsList[i].id}" value="${evalAnswerInstanceList[i]?.evalScaleOptions?.id}">
										 ${it.radio} ${it.label}</g:radioGroup>
									</td>
								</tr>
								<tr>
									<td><label for="remarks${i}"><g:message code="default.ReviewRemarks.label"/></td></label>
								</tr>
								<tr>
	                                <td valign="top" class="value ${hasErrors(bean: evalAnswerInstanceList[i], field: 'remarks', 'errors')}">
	                                    <g:textArea name="remarks${i}" value="${evalAnswerInstanceList[i]?.remarks}"  style='width: 500px; height: 75px;' />
	                                </td>
                               </tr>
	                    	</g:each>      
                    </table>
                </div>
                </div>
                
                <div style="text-align:right">
                <input type="button" id="remaxmin" onClick="changeCssClass('reviewDiv','remaxmin')"  value="${message(code: 'default.Maximize.button')}"/>
                </div>
               
                <g:if test="${proposalInstance.proposalStatus == 'Submitted'}">
                <div align="left">
                
                    <g:if test="${evalAnswerInstanceList}">
                    	<span class="button"><g:actionSubmit class="inputbutton" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" onClick="return validateEvalForm(${evalItemInstanceList.size()});" /></span>
                    </g:if> 
                    <g:else>
                    	<span class="button"><g:actionSubmit class="inputbutton" action="save" value="${message(code: 'default.Save.button')}" onClick="return validateEvalForm(${evalItemInstanceList.size()});"/></span>
                    </g:else>
                    <span class="button"><g:actionSubmit class="inputbutton" action="submitResult" value="${message(code: 'default.Submit.button')}" onClick="return validateEvalForm(${evalItemInstanceList.size()});"/></span>
				</div>
            	</g:if>
            	
              </g:if> 
                <g:else>
               <div class="body">
               <div class="errors">
                <g:message code="default.NoEvalItemForNotification.label"/>
                </div></div>
                </g:else>
            </g:form>
        </div>
        </div>
    </body>
</html>
