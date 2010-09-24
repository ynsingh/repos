<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.GrantAgencyReports.title" /></title>         
    </head>
    <script>
    </script>
    <body  onload="setValue()">
    	<div class="wrapper">
             <g:form action="listReport" method="post" name="granteeReports" >
		         <div class="body">
		         	<div class="dialog">
		            	<table width="75%">
             				<input type="hidden" id="party" name="party" value="${partyInstance.id}"/>
             	</g:form>
					    	<tr>
					           <td valign="top" class="name">
                                   <g:jasperReport jasper="NotificationReport" format="XLS,PDF,CSV,HTML" 
                                   	name="${message(code: 'default.GrantAgencyReports.NotificationReport.label')}" >
							           <input type="hidden" name="id" value="${session.AppFormID}" />
							           <input type="hidden" name="partyID" value="" />
							           <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
						  	        </g:jasperReport>
						          </td>
					           </tr>
					           <tr>
						           <td valign="top" class="name">
	                                   <g:jasperReport jasper="ProposalReport" format="XLS,PDF,CSV,HTML" 
	                                   	name="${message(code: 'default.GrantAllocationTrackingReports.ReceivedProposalsReport.label')}">
							               <input type="hidden" name="id" value="${session.AppFormID}" />
							               <input type="hidden" name="partyID" value="" />
							               <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
						  	         	</g:jasperReport>
						           	</td>
				           		</tr>
					            <tr>
						           <td valign="top" class="name">
	                                   <g:jasperReport jasper="UnsubmittedProposalReport" format="XLS,PDF,CSV,HTML" 
	                                   	name="${message(code: 'default.GrantAgencyReports.PendingProposalsReport.label')}">
							               <input type="hidden" name="id" value="${session.AppFormID}" />
							               <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
						  	         	</g:jasperReport>
						           	</td>
				           		</tr>
					            <tr>
						           <td valign="top" class="name">
	                                   <g:jasperReport jasper="List_of_grantees" format="XLS,PDF,CSV,HTML" 
	                                   name="${message(code: 'default.GrantAgencyReports.Listofgrantees.label')}" >
							               <input type="hidden" name="id" value="${session.AppFormID}" />
							               <input type="hidden" name="partyID" value="" />
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