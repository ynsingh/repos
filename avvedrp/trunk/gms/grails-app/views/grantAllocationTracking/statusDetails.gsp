
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
                 <h1><g:message code="default.ProjectStatusDetails.head"/></h1>
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
                       <g:form name="chkselt"  method="post" >
                        <g:if test="${checklistmap}">
	               		   <div class="dialog">
	               		   	 <table>
	               				 <td>
	               				 <input type="hidden" name="grantAllotId" id="grantAllotId" value="${grantAllocationInstance?.id}">
	               				 <input type="hidden" name="prjctId" id="prjctId" value="${grantAllocationInstance?.projects?.id}">
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
			                    
                              <div class="buttons">
	                            <span class="button"><g:actionSubmit  value="${message(code: 'default.WithdrawProject.button')}"  action="withdrawProject" />
		                     </div>
	             	 	</div>
	             	  </g:if>
	             	  <g:else>
	             	  No CheckList Assigned
	             	  </g:else>
	           	 </g:form>   
	      	 </div>
         </div>
    </body>
</html>