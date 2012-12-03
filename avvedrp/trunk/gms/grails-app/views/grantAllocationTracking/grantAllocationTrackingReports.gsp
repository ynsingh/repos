<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.GrantAllocationTrackingReports.title" /></title>         
    </head>
    <script>
   	</script>
	<body  onload="setValue()">
    	<div class="wrapper">
        	<g:form action="showReport" method="post" name="frmGrantTrackingReport" >
	    		<div class="body">
	    		<h1><g:message code="default.ProjectStatusReports.label"/></h1>
	    				<div class="dialog">
    						<table >
					    		<tbody>
					    			<tr>
					    				<td >
					                       <table >
					                	       	<tbody>
						                       		<tr>
						                                <td valign="top" class="name">  
						                                    <label for="project"><g:message code="default.Project.label" /> :</label>
						                                </td>
						                                <td valign="top" 
						                                	class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'grantAllocation.projects','errors')}">
						                                    <g:select optionKey="id" optionValue="code" from="${projectInstanceList}" name="projects" 
						                                    	onChange="setValue()" value="${grantAllocationTrackingInstance?.grantAllocation?.projects?.id}">
						                                    </g:select>
						                                </td>
						                                <td valign="top" class="name">
						                                    <label for="grantAllocationStatus">
						                                    	<g:message code="default.GrantAllocationStatus.label"/>:</label>
						                               	</td>
						                                <td valign="top" class="value ${hasErrors(bean:grantAllocationTrackingInstance,field:'grantAllocationStatus','errors')}">
						                                    <g:select name="grantAllocationStatus" from="${['Withdrawal']}" onChange="setValue()"  
						                                    	value="${fieldValue(bean:grantAllocationTrackingInstance,field:'grantAllocationStatus')}">
						                                     </g:select> 
						                                </td>
						                                <td valign="top" class="name">
						                                    <label for="projectStatus"><g:message code="default.GrantAllocationTrackingReports.ProjectStatus.label"/>:</label>
						                               	</td>
						                                <td valign="top" class="value ${hasErrors(bean:projectTrackingInstance,field:'projectStatus','errors')}">
						                                	<g:select name="projectStatus" from="${['Completed','Surrender']}" 
						                                		noSelection="['\'Completed\',\'Surrender\'':'All']" onChange="setValue()"  
						                                		value="${fieldValue(bean:projectTrackingInstance,field:'projectStatus')}">
						                                     </g:select> 
						                                </td>
					                                </tr> 
					                        	</tbody>   
                 	   						</table>
                 	   					</g:form>
            						</td>       
                				</tr>              
                				<tr>
                    				<td valign="top" class="name">
				                    	<g:jasperReport jasper="GrantAllocationStatusReport"  
				                    		name="${message(code: 'default.GrantAllocationTrackingReports.GrantAllocationStatusReport.label')}" format="XLS,PDF,CSV,HTML">
				                    		<input type="hidden" name="id" value="${session.AppFormID}" />
								            <input type="hidden" name="projectID" value="" />
								            <input type="hidden" name="grantStatus" value="" />
							             	<input type="hidden" name="Path" value="${application.getRealPath("reports")}"/>
						  	         	</g:jasperReport>
						            </td>     
				           		</tr>
								<tr>
				                    <td valign="top" class="name">
				                    	<g:jasperReport jasper="ProjectStatusReport"  name="${message(code: 'default.GrantAllocationTrackingReports.ProjectStatusReport.label')}" format="XLS,PDF,CSV,HTML"  >
				                    		<input type="hidden" name="id" value="${session.AppFormID}" />
								            <input type="hidden" name="projectID" value="" />
								            <input type="hidden" name="projectStatus" value="" />
							             	<input type="hidden" name="Path" value="${application.getRealPath("reports")}"/>
						  	         	</g:jasperReport>
						            </td>     
				           		</tr>
       						</tbody>                             
        				</table>  
      				</div>   
  				</div> 
      	
    </body>
</html>