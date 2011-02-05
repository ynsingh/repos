<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>HrAssetReports</title>         
    </head>
    <script>
    </script>
    <body  onload="setValue()">
	    <div class="wrapper">
	    <g:subMenuList/> 
		    <g:form action="listReport" method="post" name="hrassetReports" >
		    	<div class="body">
		    	<h1><g:message code="default.AssetReports.label"/></h1>
			    	<div class="dialog">
			    		<table width="75%">
	                        <tbody>
                        		<tr>
                            		<td >
                            		
                               			<table>
                            				<tbody>
                        						<tr>
					                               
					                                 <td> 
					                                 <input type="hidden" name="projects" value="${projectInstance.id}" />
                        							 </td>
					                            </tr> 
					                            	    
				                            </tbody>   
                         				</table>
                        			 </g:form>
                                 </td>
                        	</tr>    
                            <tr>
                                <td valign="top" class="name">
                                	<g:jasperReport jasper="EmployeeDetailsForProject" format="XLS,PDF,CSV,HTML"  
	                                	name="${message(code: 'default.EmployeeDetailsReport.label')}" > 
	                                    <input type="hidden" name="id" value="${session.AppFormID}" />
							            <input type="hidden" name="projectID" value="" />
							            <input type="hidden" name="Path" value="${application.getRealPath("reports")}"/>
						  	         </g:jasperReport>
					            </td>
				            </tr>
				            <tr>
	                            <td valign="top" class="name">
	                               <g:jasperReport jasper="ProjectItemPurchase" format="XLS,PDF,CSV,HTML" 
		                               name="${message(code: 'default.ItemPurchaseDetailsReport.label')}" >
							           <input type="hidden" name="id" value="${session.AppFormID}" />
							           <input type="hidden" name="projectID" value="" />
							           <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
						  	         </g:jasperReport>
				           		 </td>
			           		 </tr>
					         <tr>
                                <td valign="top" class="name">
                                   <g:jasperReport jasper="QualificationOfEmployees" format="XLS,PDF,CSV,HTML" 
	                                   name="${message(code: 'default.EmployeeQualificationReports.label')}" >
							            <input type="hidden" name="id" value="${session.AppFormID}" />
							            <input type="hidden" name="projectID" value="" />
							            <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
					  	         	</g:jasperReport>
				           		</td>
				             </tr>
					         <tr>
					           <td valign="top" class="name">
                                   <g:jasperReport jasper="ExperienceDetailsForEmployees" format="XLS,PDF,CSV,HTML" 
                                   	name="${message(code:'default.EmployeeExperienceReports.label')}" >
							            <input type="hidden" name="id" value="${session.AppFormID}" />
							            <input type="hidden" name="projectID" value="${session.ProjectID}" />
							            <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
						  	         </g:jasperReport>
				           		 </td>
					           </tr>
					           <tr>
						          <td valign="top" class="name">
	                                   <g:jasperReport jasper="SalaryDetailsOfEmployees" format="XLS,PDF,CSV,HTML" 
	                                   	name="${message(code:'default.EmployeeSalaryDetails.label')}" >
								            <input type="hidden" name="id" value="${session.AppFormID}" />
								            <input type="hidden" name="projectID" value="${session.ProjectID}" />
								            <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
							  	         </g:jasperReport>
					           	   </td>
				           		</tr>
					           
				           </table>  
                  	 </div>   
              	</div> 
          </div> 
    </body>
</html>