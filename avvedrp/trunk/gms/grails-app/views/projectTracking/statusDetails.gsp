
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ProjectStatusDetails.head"/></title>   
         <script type="text/javascript" src="/gms/js/applicationValidation.js" ></script>
		
	</script>      
    </head>
   
    <body>
      <g:subMenuList/>  
        <div class="wrapper"> 
            <div class="body">
                    <g:if test="${flash.message}">
                       <div class="message">${flash.message}</div>
                    </g:if>
                   <g:if test="${flash.error}">
           			 <div class="errors">${flash.error}</div>
            	   </g:if>
                    <g:hasErrors bean="${projectTrackingInstance}">
                       <div class="errors">
                          <g:renderErrors bean="${projectTrackingInstance}" as="list" />
                       </div>
                    </g:hasErrors>
                     <g:if test="${params.projectStatus != 'Surrender'}"> 
                      <g:form name="statusDet" method="post" >
                         <div class="dialog">
                         <h1><g:message code="default.ProjectStatusDetails.head"/></h1>
                            <table>
                              <td><input type="hidden" name="prjctId" id="prjctId" value="${projectsInstance?.id}">
                               <tr class="prop">
	                           		<g:checkBox name="subPrjt" onclick = "validateSubPrjt(document.getElementById('prjctId').value)" checked="false" /><g:link controller="projectTracking" action="subPrjtDetails" id="${fieldValue(bean:projectsInstance, field:'id')}"><g:message code="default.SubProjectDetails.label"/></g:link> <br>
	                           	</tr>
	                           	<tr class="prop">
	                               <g:checkBox name="fundAdvnce" onclick = "validateAdvnceDetails(document.getElementById('prjctId').value)" checked="false" /> <g:link controller="projectTracking" action="fundAdvnceDetails" id="${fieldValue(bean:projectsInstance, field:'id')}"><g:message code="default.FundAdvanceDetails.label"/></g:link> <br>
	                            </tr> 
	                            <tr class="prop">
	                               <g:checkBox name="expnseRequst" onclick = "validateExpenseReqstDetails(document.getElementById('prjctId').value)" checked="false" /> <g:link controller="projectTracking" action="expenseRequestDetails" id="${fieldValue(bean:projectsInstance, field:'id')}"><g:message code="default.ExpenseRequestDetails.label"/></g:link> <br>
	                            </tr> 
	                            <g:if test="${projectsInstance.parent !=null}">
		                           	<tr class="prop">
		                           	   <g:checkBox name="utilizationDet" onclick = "validateUtilizationDetails(document.getElementById('prjctId').value)" checked="false" /> <g:link controller="projectTracking" action="utilizationDetails" id="${fieldValue(bean:projectsInstance, field:'id')}"><g:message code="default.UtilizationDetails.label"/></g:link> <br>
		                            </tr> 
                           	    </g:if>
                           	</table>
                         <input type=hidden id="refund" name="refund" value="">
                         <input type=hidden id="amt" name="amt" value="NotRefund">
                         <g:if test="${projectsInstance.parent !=null}">
                        <div class="buttons">
                          <span class="button"><g:actionSubmit  value="${message(code: 'default.NeedtoRefund.button')}" onClick="return validateProjectTracking(document.getElementById('prjctId').value)" action="checkFundDetails" />
	                        <span class="button"><g:actionSubmit value="${message(code: 'default.NoneedtoRefund.button')}" onClick="(document.getElementById('refund').value = document.getElementById('amt').value); return validateProjectTracking(document.getElementById('prjctId').value);" action="checkFundDetails" />  
                       </div>
                       </g:if>
                        <g:else>
                        <div class="buttons">
                             <span class="button"><g:actionSubmit value="${message(code: 'default.CheckFundDetails.button')}" onClick="(document.getElementById('refund').value = document.getElementById('amt').value); return validateProjectTracking(document.getElementById('prjctId').value);" action="fundDetails" />  
                       </div>
                       </g:else>
                       </div>
                     </g:form>
                     </g:if>
                     <g:else>
                          <g:form name="chkselt"  method="post" >
	               			  <div class="dialog">
	               			  <h1><g:message code="default.checklistDetails.head"/></h1>
	               			   <g:if test="${checklistmap}">
	               				 <table>
	               				 <td><input type="hidden" name="prjctId" id="prjctId" value="${projectsInstance?.id}">
			                            <g:each in="${checklistmap}" status="i" var="checklistmapInstance">
			                             <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				                             <td>
												 <g:checkBox name="chkselt" id="check" value="${checklistmapInstance.id}" checked="false"/>
												 ${checklistmapInstance?.checklist.field}
												 <g:if test="${checklistmapInstance.compulsory !='Y'}">
												 </g:if>
												 <g:else>
												 <label for="compulsory" style="color:red;font-weight:bold"> * </label> 
												 </g:else>
											 </td>
										 </tr>
				                    	</g:each>      
			                    </table> 
			                     <input type=hidden id="refund" name="refund" value="">
		                         <input type=hidden id="amt" name="amt" value="NotRefund">
		                         <div class="buttons">
		                            <span class="button"><g:actionSubmit  value="${message(code: 'default.NeedtoRefund.button')}"  action="checkFundDetails" />
			                        <span class="button"><g:actionSubmit value="${message(code: 'default.NoneedtoRefund.button')}" onClick="(document.getElementById('refund').value = document.getElementById('amt').value);" action="checkFundDetails" />  
		                        </div>
		                         </g:if>
					            <g:else>
					    	 	 <td><g:message code="default.NoDetails.label"/></td>
					      	    </g:else> 
	             	 		</div>
	           			 </g:form>   
	      			 </g:else>
               </div>
        </div>
    </body>
</html>