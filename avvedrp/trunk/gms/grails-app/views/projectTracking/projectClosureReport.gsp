<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.closureReports.title" /></title>         
    </head>
    <script>
    </script>
    <body  onload="setValue()">
    	<div class="wrapper">
             <g:form action="listReport" method="post" name="closureReports" >
		         <div class="body">
		         <h1><g:message code="default.closureReports.label"/></h1>
		         	<div class="dialog">
		            	<table width="75%">
             				<input type="hidden" id="party" name="party" value="${partyInstance.id}"/>
             	</g:form>
					    	<tr>
					           <td valign="top" class="name">
                                   <g:jasperReport jasper="ProjectCompletedReport" format="XLS,PDF,CSV,HTML" 
                                   	name="${message(code: 'default.closureReports.ProjectCompletedReport.label')}" >
							           <input type="hidden" name="id" value="${session.AppFormID}" />
							           <input type="hidden" name="PartyID" value="${session.Party}" />
							           <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
						  	        </g:jasperReport>
						          </td>
					           </tr>
					           <tr>
						           <td valign="top" class="name">
	                                   <g:jasperReport jasper="ProjectSurrenderdReport" format="XLS,PDF,CSV,HTML" 
	                                   	name="${message(code: 'default.closureReports.ProjectSurrenderReport.label')}">
							               <input type="hidden" name="id" value="${session.AppFormID}" />
							               <input type="hidden" name="PartyID" value="${session.Party}" />
							               <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
						  	         	</g:jasperReport>
						           	</td>
				           		</tr>
					            <tr>
						           <td valign="top" class="name">
	                                   <g:jasperReport jasper="ProjectWithdrawReport" format="XLS,PDF,CSV,HTML" 
	                                   	name="${message(code: 'default.closureReports.ProjectWithdrawReport.label')}">
							               <input type="hidden" name="id" value="${session.AppFormID}" />
							               <input type="hidden" name="PartyID" value="${session.Party}" />
							               <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
						  	         	</g:jasperReport>
						           	</td>
				           		</tr>
					            <tr>
						           <td valign="top" class="name">
	                                   <g:jasperReport jasper="ProjectRefundReport" format="XLS,PDF,CSV,HTML" 
	                                   name="${message(code: 'default.closureReports.ProjectRefundReport.label')}" >
							               <input type="hidden" name="id" value="${session.AppFormID}" />
							               <input type="hidden" name="PartyID" value="${session.Party}" />
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
